/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.core;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import java.util.List;
import tonegod.gui.core.Screen;
import tonegod.gui.framework.animation.TemporalAction;
import tonegod.gui.framework.core.util.GameTimer;

/**
 *
 * @author t0neg0d
 */
public class ActionManager extends AbstractControl {

    private Screen screen;

    private List<ActionQueueEntry> queue = new ArrayList();
    private List<ActionQueueEntry> remove = new ArrayList();
    private List<ActionQueueEntry> active = new ArrayList();

    private List<GameTimer> timers = new ArrayList();
    private List<GameTimer> removeTimers = new ArrayList();

    float time;

    public ActionManager(Screen screen) {
        this.screen = screen;
        time = this.screen.getApplication().getTimer().getTimeInSeconds();
    }

    public void addQueuedAction(TemporalAction action, TransformableDisplay item, float startTime) {
        ActionQueueEntry act = new ActionQueueEntry(action, item, time + startTime);
        queue.add(act);
    }

    public void addGameTimer(GameTimer timer) {
        timer.setIsManaged(true);
        timers.add(timer);
        timer.startGameTimer();
    }

    public void removeGameTimer(GameTimer timer) {
        timers.remove(timer);
        timer.setIsManaged(false);
        if (removeTimers.contains(timer)) {
            removeTimers.remove(timer);
        }
    }

    public boolean hasGameTimer(GameTimer timer) {
        return this.timers.contains(timer);
    }

    @Override
    protected void controlUpdate(float tpf) {
        time += tpf;
        for (ActionQueueEntry entry : queue) {
            if (time >= entry.startTime) {
                if (entry.displayItem != null) {
                    entry.displayItem.addAction(entry.action);
                    if (entry.displayItem instanceof QuadData) {
                        ((QuadData) entry.displayItem).show();
                    }
                }
                active.add(entry);
                remove.add(entry);
            }
        }
        if (!remove.isEmpty()) {
            queue.removeAll(remove);
            remove.clear();
        }
        if (!active.isEmpty()) {
            for (ActionQueueEntry entry : active) {
                if (!entry.displayItem.getContainsAction(entry.action)) {
                    remove.add(entry);
                }
            }
        }
        if (!remove.isEmpty()) {
            active.removeAll(remove);
            remove.clear();
        }
        // GameTimers
        for (GameTimer timer : timers) {
            if (timer.isActive()) {
                timer.update(tpf);
            }
            if (!timer.getAutoRestart() && timer.isComplete()) {
                removeTimers.add(timer);
                timer.setIsManaged(false);
            }
        }
        if (!removeTimers.isEmpty()) {
            timers.removeAll(removeTimers);
            removeTimers.clear();
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    public int getActiveTimerCount() {
        return timers.size();
    }

    public int getQueueCount() {
        return this.queue.size();
    }

    public boolean getIsQueueIdle() {
        return this.queue.isEmpty();
    }

    public class ActionQueueEntry {

        TemporalAction action;
        TransformableDisplay displayItem;
        float startTime;

        private ActionQueueEntry(TemporalAction action, TransformableDisplay item, float startTime) {
            this.action = action;
            this.displayItem = item;
            this.startTime = startTime;
        }
    }
}
