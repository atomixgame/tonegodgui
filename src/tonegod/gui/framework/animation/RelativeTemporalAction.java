package tonegod.gui.framework.animation;

/**
 *
 * @author cuongnguyen
 */
public abstract class RelativeTemporalAction extends TemporalAction {

    protected float nextPercent = 0;
    protected float lastPercent = 0;

    @Override
    protected void begin() {
        lastPercent = 0;
        nextPercent = 0;
    }

    @Override
    protected void update(float percent) {
        nextPercent = percent - lastPercent;
        lastPercent = percent;
    }

    @Override
    protected void end() {
    }

}
