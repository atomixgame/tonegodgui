/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.effects;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.texture.Texture;
import tonegod.gui.core.Element;

/**
 *
 * @author t0neg0d
 */
public class Effect implements Cloneable {
	public static enum EffectType {
		FadeIn,
		FadeOut,
		ZoomIn,
		ZoomOut,
		SlideIn,
		SlideOut,
		SlideTo,
		SpinIn,
		SpinOut,
		Pulse,
		ColorSwap,
		PulseColor,
		ImageSwap
	}
	
	public enum EffectEvent {
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
	
	public enum EffectDirection {
		Top,
		Bottom,
		Left,
		Right
	}
	
	private Element element;
	private EffectType type;
	private EffectEvent event;
	private float pass = 0.0f;
	private boolean direction = true;
	private float duration;
	private Vector2f destination;
	private boolean isActive = true;
	private boolean localActive = true;
	private Texture blendImage;
	private ColorRGBA blendColor;
	private boolean init = false;
	private boolean destroyOnHide = false;
	private EffectDirection effectDir = EffectDirection.Top;
	private Vector2f def = new Vector2f();
	private Vector2f diff = new Vector2f();
	private Vector2f fract = new Vector2f();
	private String audioFile = null;
	private float audioVolume = 1;
	private boolean callHide = true;
	
	public Effect(EffectType type, EffectEvent event, float duration) {
		this(type, event, duration, null, 1);
	//	this.type = type;
	//	this.event = event;
	//	this.duration = duration;
	}
	
	public Effect (EffectType type, EffectEvent event, float duration, String audioFile, float audioVolume) {
		this.type = type;
		this.event = event;
		this.duration = duration;
		this.audioFile = audioFile;
		this.audioVolume = audioVolume;
	}
	
	public void setElement(Element element) {
		this.element = element;
	}
	
	public void setBlendImage(Texture blendImage) {
		this.blendImage = blendImage;
	}
	
	public void setColor(ColorRGBA blendColor) {
		this.blendColor = blendColor;
	}
	
	public Element getElement() {
		return this.element;
	}
	
	public float getDuration() {
		return this.duration;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void setCallHide(boolean callHide) {
		this.callHide = callHide;
	}
	
	public EffectEvent getEffectEvent() {
		return this.event;
	}
	
	public EffectType getEffectType() {
		return this.type;
	}
	
	public void setEffectDirection(EffectDirection effectDir) {
		this.effectDir = effectDir;
	}
	
	public EffectDirection getEffectDirection() {
		return this.effectDir;
	}
	
	public void setEffectDestination(Vector2f destination) {
		this.destination = destination;
	}
	
	public Vector2f getEffectDestination() {
		return this.destination;
	}
	
	public void setDestroyOnHide(boolean destroyOnHide) {
		this.destroyOnHide = destroyOnHide;
	}
	
	public void setAudioFile(String audioFile) {
		this.audioFile = audioFile;
	}
	
	public String getAudioFile() {
		return this.audioFile;
	}
	
	public void setAudioVolume(float audioVolume) {
		this.audioVolume = audioVolume;
	}
	
	public float getAudioVolume() {
		return this.audioVolume;
	}
	
	public void update(float tpf) {
		switch(type) {
			case ZoomIn:
				updateZoomIn();
				break;
			case ZoomOut:
				updateZoomOut();
				break;
			case SlideIn:
				updateSlideIn();
				break;
			case SlideOut:
				updateSlideOut();
				break;
			case SlideTo:
				updateSlideTo();
				break;
			case SpinIn:
				updateSpinIn();
				break;
			case SpinOut:
				updateSpinOut();
				break;
			case FadeIn:
				updateFadeIn();
				break;
			case FadeOut:
				updateFadeOut();
				break;
			case ImageSwap:
				updateImageSwap();
				break;
			case ColorSwap:
				updateColorSwap();
				break;
		}

		if (isActive) {
			if (type != EffectType.Pulse && type != EffectType.PulseColor) {
				pass += tpf/duration;
				if (pass >= 1.0) {
					pass = 1.0f;
					localActive = false;
				}
			} else {
				if (type == EffectType.Pulse) {
					if (pass >= 1.0f) {
						direction = false;
					} else if (pass <= -1.0f) {
						direction = true;
					}
					if (direction) pass += tpf/duration;
					else pass -= tpf/duration;
					if (!init) {
						element.getElementMaterial().setBoolean("UseEffect", true);
						element.getElementMaterial().setBoolean("EffectFade", false);
						element.getElementMaterial().setBoolean("EffectPulse", true);
						element.getElementMaterial().setBoolean("EffectPulseColor", false);
						element.getElementMaterial().setTexture("EffectMap", blendImage);
						init = true;
					}
					element.getElementMaterial().setFloat("EffectStep", pass);
				} else if (type == EffectType.PulseColor) {
					if (pass >= 1.0f) {
						direction = false;
					} else if (pass <= -1.0f) {
						direction = true;
					}
					if (direction) pass += tpf/duration;
					else pass -= tpf/duration;
					if (!init) {
						element.getElementMaterial().setBoolean("UseEffect", true);
						element.getElementMaterial().setBoolean("EffectFade", false);
						element.getElementMaterial().setBoolean("EffectPulse", false);
						element.getElementMaterial().setBoolean("EffectPulseColor", true);
						element.getElementMaterial().setColor("EffectColor", blendColor);
						init = true;
					}
					element.getElementMaterial().setFloat("EffectStep", pass);
				}
			}
		}
	}
	
	private void destoryElement() {
		if (element.getElementParent() == null) {
			element.getScreen().removeElement(element);
		} else {
			element.getElementParent().removeChild(element);
		}
	}
	
	public void resetShader() {
		element.getElementMaterial().setBoolean("UseEffect", false);
		element.getElementMaterial().setBoolean("EffectFade", false);
		element.getElementMaterial().setBoolean("EffectPulse", false);
		element.getElementMaterial().setTexture("EffectMap", null);
		element.getElementMaterial().setFloat("EffectStep", 0.0f);
	}
	
	@Override
	public Effect clone() {
		Effect effect = new Effect(
			this.type,
			this.event,
			this.duration
		);
		effect.setElement(this.element);
		effect.setEffectDirection(this.effectDir);
		effect.setDestroyOnHide(this.destroyOnHide);
		return effect;
	}
	
	// Effect methods
	private void initSlides() {
		def.set(element.getPosition().clone());
		if (effectDir == EffectDirection.Bottom) {
			diff.set(0,element.getAbsoluteHeight());
		} else if (effectDir == EffectDirection.Top) {
			diff.set(0,element.getScreen().getHeight()-element.getAbsoluteY());
		} else if (effectDir == EffectDirection.Left) {
			diff.set(element.getAbsoluteWidth(),0);
		} else if (effectDir == EffectDirection.Right) {
			diff.set(element.getScreen().getWidth()-element.getAbsoluteX(),0);
		}
	}
	
	private void updateSlideIn() {
		if (!init) {
			initSlides();
			element.show();
			init = true;
		}
		Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
		if (effectDir == EffectDirection.Bottom || effectDir == EffectDirection.Left) {
			element.setPosition(def.subtract(diff.subtract(inc)));
		} else if (effectDir == EffectDirection.Top || effectDir == EffectDirection.Right) {
			element.setPosition(def.add(diff.subtract(inc)));
		}
		if (pass >= 1.0) {
			element.setPosition(def);
			element.setLocalScale(pass);
			isActive = false;
		}
	}
	
	private void updateSlideOut() {
		if (!init) {
			initSlides();
			init = true;
		}
		Vector2f inc = new Vector2f(diff.x*(1-pass),diff.y*(1-pass));
		if (effectDir == EffectDirection.Bottom || effectDir == EffectDirection.Left) {
			element.setPosition(def.subtract(diff.subtract(inc)));
		} else if (effectDir == EffectDirection.Top || effectDir == EffectDirection.Right) {
			element.setPosition(def.add(diff.subtract(inc)));
		}
		if (pass >= 1.0) {
			if (!destroyOnHide) {
				if (callHide) element.hide();
				element.setPosition(def);
			} else {
				destoryElement();
			}
			isActive = false;
		}
	}
	
	private void updateSlideTo() {
		if (!init) {
			def.set(element.getPosition().clone());
			diff.set(element.getX()-destination.getX(),element.getY()-destination.getY());
			init = true;
		}
		
		Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
		float nextX = 0, nextY = 0;
		if (diff.x < 0)			nextX = def.x-inc.x;
		else if (diff.x > 0)	nextX = def.x-inc.x;
		if (diff.y < 0)			nextY = def.y+(diff.y-inc.y);
		else if (diff.y > 0)	nextY = def.y-(diff.y-inc.y);
		element.setPosition(nextX,nextY);
		
		if (pass >= 1.0) {
			element.setPosition(destination);
			isActive = false;
		}
	}
	
	private void initPositions() {
		def.set(element.getPosition().clone());
		diff.set(element.getWidth()/2,element.getHeight()/2);
	}
	
	private void updateZoomIn() {
		if (!init) {
			initPositions();
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(diff.subtract(inc)));
			element.setLocalScale(pass);
			element.show();
			init = true;
		} else if (localActive) {
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(diff.subtract(inc)));
			element.setLocalScale(pass);
		}
		if (pass >= 1.0) {
			element.setPosition(def);
			element.setLocalScale(pass);
			isActive = false;
		}
	}
	
	private void updateZoomOut() {
		if (!init) {
			initPositions();
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(inc));
			element.setLocalScale(1-pass);
			init = true;
		} else if (localActive) {
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(inc));
			element.setLocalScale(1-pass);
		}
		if (pass >= 1.0) {
			if (!destroyOnHide) {
				if (callHide) element.hide();
				element.setPosition(def);
				element.setLocalScale(0);
			} else {
				destoryElement();
			}
			isActive = false;
		}
	}
	
	private void disableShaderEffect() {
		element.getElementMaterial().setBoolean("UseEffect", false);
		element.getElementMaterial().setBoolean("EffectFade", false);
		element.getElementMaterial().setBoolean("EffectPulse", false);
		element.getElementMaterial().setBoolean("EffectPulseColor", false);
	}
	
	private void initFades() {
		element.getElementMaterial().setBoolean("UseEffect", true);
		element.getElementMaterial().setBoolean("EffectFade", true);
		element.getElementMaterial().setBoolean("EffectPulse", false);
	}
	
	private void updateFadeIn() {
		if (!init) {
			initFades();
			element.show();
			init = true;
		}
		if (pass >= 1.0) {
			disableShaderEffect();
			isActive = false;
		} else
			element.getElementMaterial().setFloat("EffectStep", pass);
	}
	
	private void updateFadeOut() {
		if (!init) {
			initFades();
			init = true;
		}
		if (pass >= 1.0) {
			if (!destroyOnHide) {
				if (callHide) element.hide();
				disableShaderEffect();
				isActive = false;
			} else {
				destoryElement();
				isActive = false;
			}
		} else
			element.getElementMaterial().setFloat("EffectStep", 1.0f-pass);
	}
	
	private void updateSpinIn() {
		if (!init) {
			initPositions();
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(diff.subtract(inc)));
			element.setLocalScale(pass);
			element.show();
			init = true;
		} else if (localActive) {
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.add(diff.subtract(inc)));
			element.setLocalScale(pass);
			element.setLocalRotation(element.getLocalRotation().fromAngles(0, 0, 360*FastMath.DEG_TO_RAD*pass));
		}
		if (pass >= 1.0) {
			element.setPosition(def);
			element.setLocalScale(pass);
			element.setLocalRotation(element.getLocalRotation().fromAngles(0, 0, 0));
			isActive = false;
		}
	}
	
	private void updateSpinOut() {
		if (!init) {
			initPositions();
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.subtract(inc));
			element.setLocalScale(1-pass);
			init = true;
		} else if (localActive) {
			Vector2f inc = new Vector2f(diff.x*pass,diff.y*pass);
			element.setPosition(def.subtract(inc));
			element.setLocalScale(1-pass);
			element.setLocalRotation(element.getLocalRotation().fromAngles(0, 0, 360*FastMath.DEG_TO_RAD*(1.0f-pass)));
		}
		if (pass >= 1.0) {
			if (!destroyOnHide) {
				if (callHide) element.hide();
				element.setPosition(def);
				element.setLocalScale(1);
				element.setLocalRotation(element.getLocalRotation().fromAngles(0, 0, 0));
			} else {
				destoryElement();
			}
			isActive = false;
		}
	}
	
	private void updateImageSwap() {
		if (!init) {
			disableShaderEffect();
			element.getElementMaterial().setTexture("ColorMap", blendImage);
			element.getElementMaterial().setFloat("EffectStep", 1.0f);
			init = true;
			isActive = false;
		}
	}
	
	private void updateColorSwap() {
		if (!init) {
			disableShaderEffect();
			element.getElementMaterial().setColor("Color", blendColor);
			element.getElementMaterial().setFloat("EffectStep", 1.0f);
			init = true;
			isActive = false;
		}
	}
}
