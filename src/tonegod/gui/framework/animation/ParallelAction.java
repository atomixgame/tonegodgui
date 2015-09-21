package tonegod.gui.framework.animation;

import java.util.ArrayList;
import java.util.List;
import tonegod.gui.framework.core.TransformableDisplay;

/**
 * Executes a number of actions at the same time.
 *
 * @author Nathan Sweet
 */
public class ParallelAction extends TemporalAction {

    ArrayList<TemporalAction> actions = new ArrayList(4);
    private boolean complete;

    public ParallelAction() {
    }

    public ParallelAction(TemporalAction action1) {
        addAction(action1);
    }

    public ParallelAction(TemporalAction... actions) {
        for (TemporalAction action : actions) {
            addAction(action);
        }
    }

    public boolean act(float delta) {
        if (complete) {
            return true;
        }
        complete = true;
        List<TemporalAction> actions = this.actions;
        for (int i = 0, n = actions.size(); i < n && getActor() != null; i++) {
            if (!actions.get(i).act(delta)) {
                complete = false;
            }
            if (getActor() == null) {
                return true; // This action was removed.
            }
        }
        return complete;
    }

    public void restart() {
        complete = false;
        List<TemporalAction> actions = this.actions;
        for (int i = 0, n = actions.size(); i < n; i++) {
            actions.get(i).restart();
        }
    }

    public void reset() {
        super.reset();
        actions.clear();
    }

    public void addAction(TemporalAction action) {
        actions.add(action);
        if (getActor() != null) {
            action.setActor(getActor());
        }
    }

    public void setActor(TransformableDisplay actor) {
        List<TemporalAction> actions = this.actions;
        for (int i = 0, n = actions.size(); i < n; i++) {
            actions.get(i).setActor(actor);
        }
        super.setActor(actor);
    }

    public List<TemporalAction> getActions() {
        return actions;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(64);
        buffer.append(super.toString());
        buffer.append('(');
        List<TemporalAction> actions = this.actions;
        for (int i = 0, n = actions.size(); i < n; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            buffer.append(actions.get(i));
        }
        buffer.append(')');
        return buffer.toString();
    }

    @Override
    protected void update(float percent) {
    }
}
