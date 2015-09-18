/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.core;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import tonegod.gui.framework.animation.TemporalAction;

/**
 *
 * @author cuongnguyen
 */
public interface TransformableDisplay {

    void addAction(TemporalAction action);

    ColorRGBA getColor();

    boolean getContainsAction(TemporalAction action);

    Vector2f getDimensions();

    float getHeight();

    boolean getIgnoreMouse();

    boolean getIsMovable();

    Vector2f getOrigin();

    Vector2f getPosition();

    Vector2f getScale();

    Vector2f getSkew();

    void setColor(ColorRGBA color);

    void setDimensions(Vector2f dim);

    void setDimensions(float w, float h);

    void setIgnoreMouse(boolean ignoreMouse);

    void setIsMovable(boolean isMovable);

    void setPosition(float x, float y);

    void setPosition(Vector2f pos);

    void setRotation(float rotation);

    void setScale(float x, float y);

    void setScale(Vector2f scale);

    void setSkew(Vector2f skew);

    void setSkew(float x, float y);

    float getPositionZ();

    void setPositionZ(float z);

    float getRotation();

}
