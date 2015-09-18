/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.core;

import com.jme3.math.Vector2f;

/**
 *
 * @author t0neg0d
 */
public interface Transformable extends TransformableDisplay {

    public void setPositionX(float x);

    public void setPositionY(float y);

    public void setScaleX(float scaleX);

    public void setScaleY(float scaleY);

    public void setOrigin(Vector2f origin);

    public void setOrigin(float x, float y);

    public void setOriginX(float originX);

    public void setOriginY(float originY);

    public void setColorR(float r);

    public void setColorG(float g);

    public void setColorB(float b);

    public void setColorA(float a);

    public void setTCOffsetX(float x);

    public void setTCOffsetY(float y);

    public void setWidth(float w);

    public void setHeight(float h);

    public void setSkewX(float x);

    public void setSkewY(float y);

    public float getPositionX();

    public float getPositionY();

    public float getScaleX();

    public float getScaleY();

    public float getOriginX();

    public float getOriginY();

    public float getColorR();

    public float getColorG();

    public float getColorB();

    public float getColorA();

    public float getWidth();

    public Vector2f getTCOffset();

    public float getTCOffsetX();

    public float getTCOffsetY();

    public float getSkewX();

    public float getSkewY();

}
