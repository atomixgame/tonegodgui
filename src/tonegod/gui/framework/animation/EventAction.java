package tonegod.gui.framework.animation;

/**
 * Adds a listener to the actor for a specific event type and does not complete
 * until {@link #handle(Event)} returns true.
 *
 * @author JavadocMD
 * @author Nathan Sweet
 */
abstract public class EventAction<T> extends ExecuteAction {

    final Class<? extends T> eventClass;
    boolean result, active;

    public EventAction(Class<? extends T> eventClass) {
        this.eventClass = eventClass;
    }

    public void restart() {
        result = false;
        active = false;
    }

    /**
     * Called when the specific type of event occurs on the actor.
     *
     * @return true if the event should be considered
     * {@link Event#handle() handled} and this EventAction considered complete.
     */
    abstract public boolean handle(T event);

    public boolean act(float delta) {
        active = true;
        return result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
