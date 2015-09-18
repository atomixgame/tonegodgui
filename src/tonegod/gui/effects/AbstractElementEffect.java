/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.effects;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import tonegod.gui.core.Element;
import tonegod.gui.framework.animation.Interpolation;

/**
 *
 * @author cuongnguyen
 */
public interface AbstractElementEffect {

    public static enum EffectEvent {

        GetFocus,
        LoseFocus,
        Show,
        Hide,
        Hover,
        Press,
        Release,
        TabFocus,
        LoseTabFocus
    }

    public static enum EffectDirection {

        Top,
        Bottom,
        Left,
        Right
    }

    String getAudioFile();

    float getAudioVolume();

    float getDuration();

    Vector2f getEffectDestination();

    EffectDirection getEffectDirection();

    EffectEvent getEffectEvent();

    Element getElement();

    Interpolation getInterpolation();

    boolean getIsActive();

    void resetShader();

    void setAudioFile(String audioFile);

    void setAudioVolume(float audioVolume);

    void setCallHide(boolean callHide);

    void setColor(ColorRGBA blendColor);

    void setDestroyOnHide(boolean destroyOnHide);

    void setEffectDestination(Vector2f destination);

    void setEffectDirection(EffectDirection effectDir);

    void setElement(Element element);

    void setInterpolation(Interpolation interpolation);

    void setIsActive(boolean isActive);

    void update(float tpf);

}
