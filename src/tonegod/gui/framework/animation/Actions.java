/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.animation;

/**
 *
 * @author cuongnguyen
 */
public class Actions {

    public static TemporalAction moveBy(float x, float y, float duration) {
        MoveByAction action = new MoveByAction();
        action.setAmountX(x);
        action.setAmountY(y);
        action.setDuration(duration);
        return action;
    }

}
