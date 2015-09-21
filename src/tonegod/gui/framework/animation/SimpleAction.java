/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.animation;

import tonegod.gui.framework.core.TransformableDisplay;

/**
 *
 * @author cuongnguyen
 */
public interface SimpleAction {

    boolean act(float delta);

    void finish();

    TransformableDisplay getActor();

    /**
     * Gets the transition time so far.
     */
    float getTime();

    void reset();

    void restart();

    void setActor(TransformableDisplay actor);

    /**
     * Sets the transition time so far.
     */
    void setTime(float time);

}
