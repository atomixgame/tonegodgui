package tonegod.gui.framework.animation;

/**
 * Executes a number of actions one at a time.
 *
 * @author Nathan Sweet
 */
public class SequenceAction extends ParallelAction {

    private int index;

    public SequenceAction() {
    }

    public SequenceAction(TemporalAction action1) {
        super(action1);
    }

    public SequenceAction(TemporalAction... actions) {
        super(actions);
    }

    public boolean act(float delta) {
        if (index >= actions.size()) {
            return true;
        }
        if (actions.get(index).act(delta)) {
            if (getActor() == null) {
                return true; // This action was removed.
            }
            index++;
            if (index >= actions.size()) {
                return true;
            }
        }
        return false;
    }

    public void restart() {
        super.restart();
        index = 0;
    }
}
