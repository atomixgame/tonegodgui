/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.controls.extras.emitter;

import tonegod.gui.controls.extras.emitter.ElementEmitter.ElementParticle;

/**
 *
 * @author t0neg0d
 */
public class AlphaInfluencer implements Influencer {
	private boolean isEnabled = true;
	private float startAlpha = 1.0f;
	private float endAlpha = 0.0f;
	
	@Override
	public void update(ElementParticle particle, float tpf) {
		if (isEnabled) {
			float alpha = (startAlpha-endAlpha);
			alpha *= (1-(1/particle.life));
			alpha += endAlpha;
			particle.particle.getElementMaterial().setFloat("GlobalAlpha",alpha);
		}
	}

	@Override
	public void initialize(ElementParticle particle) {
		particle.particle.setGlobalAlpha(startAlpha);
		particle.particle.getElementMaterial().setFloat("GlobalAlpha",startAlpha);
	}

	@Override
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public boolean getIsEnabled() {
		return this.isEnabled;
	}
	
	public void setStartAlpha(float startAlpha) {
		this.startAlpha = startAlpha;
	}
	
	public void setEndAlpha(float endAlpha) {
		this.endAlpha = endAlpha;
	}
}
