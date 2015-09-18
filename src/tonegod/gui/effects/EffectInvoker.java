/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.effects;

/**
 *
 * @author cuongnguyen
 */
public interface EffectInvoker {

    /**
     * Associates an Effect with this Element. Effects are not automatically
     * associated with the specified event, but instead, the event type is used
     * to retrieve the Effect at a later point
     *
     * @param effect The Effect to store
     */
    void addEffect(Effect effect);

    //<editor-fold desc="Effects">
    /**
     * Associates an Effect with this Element. Effects are not automatically
     * associated with the specified event, but instead, the event type is used
     * to retrieve the Effect at a later point
     *
     * @param effectEvent The Effect.EffectEvent the Effect is to be registered
     * with
     * @param effect The Effect to store
     */
    void addEffect(Effect.EffectEvent effectEvent, Effect effect);

    /**
     * Hides the element using the current defined Hide effect. If no Hide
     * effect is defined, the Element will hide as usual.
     */
    void hideWithEffect();

    /**
     * Called by controls during construction to prepopulate effects based on
     * Styles.
     *
     * @param styleName The String identifier of the Style
     */
    void populateEffects(String styleName);

    /**
     * For internal use only - DO NOT CALL THIS METHOD
     *
     * @param effect Effect
     * @param callHide boolean
     */
    void propagateEffect(Effect effect, boolean callHide);
    //</editor-fold>

    /**
     * Removes the Effect associated with the Effect.EffectEvent specified
     *
     * @param effectEvent
     */
    void removeEffect(Effect.EffectEvent effectEvent);

    /**
     * Shows the current Element with the defined Show effect. If no Show effect
     * is defined, the Element will show as normal.
     */
    void showWithEffect();

}
