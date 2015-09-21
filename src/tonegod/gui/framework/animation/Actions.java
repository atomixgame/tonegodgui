package tonegod.gui.framework.animation;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cuongnguyen
 */
public class Actions {

    static public <T extends TemporalAction> T action(Class<T> type) {
//        Pool<T> pool = Pools.get(type);
//        T action = pool.obtain();
//        action.setPool(pool);
        T action = null;
        try {
            action = type.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return action;
    }

    public static TemporalAction moveBy(float x, float y, float duration) {
        MoveByAction action = new MoveByAction();
        action.setAmountX(x);
        action.setAmountY(y);
        action.setDuration(duration);
        return action;
    }

    static public SequenceAction sequence(TemporalAction... actions) {
        SequenceAction action = action(SequenceAction.class);
        for (int i = 0, n = actions.length; i < n; i++) {
            action.addAction(actions[i]);
        }
        return action;
    }

    static public SequenceAction sequence() {
        return action(SequenceAction.class);
    }

    static public ParallelAction parallel(TemporalAction action1) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        return action;
    }

    static public ParallelAction parallel(TemporalAction action1, TemporalAction action2) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        return action;
    }

    static public ParallelAction parallel(TemporalAction action1, TemporalAction action2, TemporalAction action3) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        return action;
    }

    static public ParallelAction parallel(TemporalAction action1, TemporalAction action2, TemporalAction action3, TemporalAction action4) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        return action;
    }

    static public ParallelAction parallel(TemporalAction action1, TemporalAction action2, TemporalAction action3, TemporalAction action4, TemporalAction action5) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        action.addAction(action5);
        return action;
    }

    static public ParallelAction parallel(TemporalAction... actions) {
        ParallelAction action = action(ParallelAction.class);
        for (int i = 0, n = actions.length; i < n; i++) {
            action.addAction(actions[i]);
        }
        return action;
    }

    static public ParallelAction parallel() {
        return action(ParallelAction.class);
    }

    static public TemporalAction repeat(int count, TemporalAction repeatedAction) {
        DelegateAction action = new DelegateAction();
        action.setCount(count);
        action.setAction(repeatedAction);
        return action;
    }

    static public TemporalAction forever(TemporalAction repeatedAction) {
        DelegateAction action = new DelegateAction();
        action.setCount(TemporalAction.FOREVER);
        action.setAction(repeatedAction);
        return action;
    }

    static public RunnableAction run(Runnable runnable) {
        RunnableAction action = action(RunnableAction.class);
        action.setRunnable(runnable);
        return action;
    }

}
