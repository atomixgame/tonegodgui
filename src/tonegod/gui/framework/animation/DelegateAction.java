package tonegod.gui.framework.animation;

import tonegod.gui.framework.core.TransformableDisplay;

/**
 * Base class for an action that wraps another action.
 *
 * @author Nathan Sweet
 */
public class DelegateAction extends TemporalAction {

    protected TemporalAction action;

    /**
     * Sets the wrapped action.
     */
    public void setAction(TemporalAction action) {
        this.action = action;
    }

    public TemporalAction getAction() {
        return action;
    }

    public final boolean act(float delta) {
        return action.act(delta);
    }

    public void restart() {
        if (action != null) {
            action.restart();
        }
    }

    public void reset() {
        super.reset();
        action = null;
    }

    public void setActor(TransformableDisplay actor) {
        if (action != null) {
            action.setActor(actor);
        }
        super.setActor(actor);
    }

    public String toString() {
        return super.toString() + (action == null ? "" : "(" + action + ")");
    }

    @Override
    protected void update(float percent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
