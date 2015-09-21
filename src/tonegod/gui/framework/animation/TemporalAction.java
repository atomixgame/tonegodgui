package tonegod.gui.framework.animation;

import tonegod.gui.framework.core.TransformableDisplay;

/**
 * Based on LibGdx TemporalAction.
 *
 * [OLD] This has been altered to work with QuadData representing a single quad
 * from a mesh of x number of quads.
 *
 * [NEW] Work with any TransformableDisplay
 *
 * @author t0neg0d
 */
public abstract class TemporalAction implements Cloneable, SimpleAction {

    public static final int FOREVER = Integer.MIN_VALUE;
    protected float duration, time = 0;
    protected Interpolation interpolation;
    protected boolean reverse, complete;
//	protected AnimElement batch;
    protected TransformableDisplay quad;
    protected boolean forceJmeTransform = false;
    protected boolean autoRestart = false;
    protected int runCount = 0;
    protected int count = 1;

    public TemporalAction() {
    }

    public TemporalAction(float duration) {
        this.duration = duration;
    }

    public TemporalAction(float duration, Interpolation interpolation) {
        this.duration = duration;
        this.interpolation = interpolation;
    }

    public void setTransformable(TransformableDisplay quad) {
        this.quad = quad;
        setActor(quad);
    }

    public TransformableDisplay getTransformable() {
        return this.quad;
    }

    @Override
    public boolean act(float delta) {
        if (complete) {
            return true;
        }
        if (time == 0) {
            begin();
        }
        time += delta;
        float percent = 0;
        if (count > 0 || count == FOREVER) { //Repeat
            if (time >= duration) {
                restart();
            } else {
                percent = time / duration;
                if (interpolation != null) {
                    percent = interpolation.apply(percent);
                }
            }
        } else {
            complete = time >= duration;
            if (complete) {
                percent = 1;
            } else {
                percent = time / duration;
                if (interpolation != null) {
                    percent = interpolation.apply(percent);
                }
            }
        }
        if (complete) {
            end();
        } else {
            update(reverse ? 1 - percent : percent);
        }
        return complete;
    }

    public void setAutoRestart(boolean autoRestart) {
        this.autoRestart = autoRestart;
    }

    public boolean getAutoRestart() {
        return this.autoRestart;
    }

    public int getRunCount() {
        return this.runCount;
    }

    protected void begin() {
    }

    protected void end() {
    }

    abstract protected void update(float percent);

    @Override
    public void finish() {
        complete = true;
        time = duration;
    }

    @Override
    public void restart() {
        if (count == FOREVER) {

        } else {
            if (runCount > count) {
                finish();
                return;
            }
        }
        if (!reverse) {
            runCount++;
        }
        time = 0;
        complete = false;
    }

    @Override
    public void reset() {
        reverse = false;
        runCount = 0;
        //	interpolation = null;
    }

    protected void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    /**
     * Gets the transition time so far.
     */
    @Override
    public float getTime() {
        return time;
    }

    /**
     * Sets the transition time so far.
     */
    @Override
    public void setTime(float time) {
        this.time = time;
    }

    public float getDuration() {
        return duration;
    }

    /**
     * Sets the length of the transition in seconds.
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Interpolation getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
    }

    public boolean isReverse() {
        return reverse;
    }

    /**
     * When true, the action's progress will go from 100% to 0%.
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setForceJmeTransform(boolean forceJmeTransform) {
        this.forceJmeTransform = forceJmeTransform;
    }

    @Override
    public void setActor(TransformableDisplay actor) {
        quad = actor;
    }

    @Override
    public TransformableDisplay getActor() {
        return quad;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
