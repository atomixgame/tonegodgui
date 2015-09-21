package tonegod.gui.framework.animation;

/**
 * An EventAction that is complete once it receives X number of events.
 *
 * @author JavadocMD
 * @author Nathan Sweet
 */
public abstract class CountdownEventAction<T> extends EventAction<T> {

    int count, current;

    public CountdownEventAction(Class<? extends T> eventClass, int count) {
        super(eventClass);
        this.count = count;
    }

    public boolean handle(T event) {
        current++;
        return current >= count;
    }
}
