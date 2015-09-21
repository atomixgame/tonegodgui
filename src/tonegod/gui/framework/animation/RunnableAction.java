package tonegod.gui.framework.animation;

/**
 * An action that runs a {@link Runnable}. Alternatively, the {@link #run()}
 * method can be overridden instead of setting a runnable.
 *
 * @author Nathan Sweet
 * @author Atomix
 */
public class RunnableAction extends ExecuteAction {

    private Runnable runnable;
    private boolean ran;

    public boolean act(float delta) {
        if (!ran) {
            ran = true;
            execute();
        }
        return true;
    }

    /**
     * Called to run the runnable.
     */
    public void execute() {
        runnable.run();
    }

    public void restart() {
        ran = false;
    }

    public void reset() {
        super.reset();
        runnable = null;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
