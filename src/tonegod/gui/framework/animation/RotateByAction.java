/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.animation;

/**
 *
 * @author t0neg0d
 */
public class RotateByAction extends RelativeTemporalAction {

    private float initRot = -1;
    private float initDuration = -1;
    private float amount;
    private boolean autoReverse = false;
    private boolean initAutoReverse = false;
    private int cycles = 0;

    @Override
    protected void begin() {
        super.begin();
        if (autoReverse) {
            if (initRot == -1) {
                initRot = getActor().getRotation();
            }
            if (initDuration == -1) {
                initDuration = getDuration();
                setDuration(initDuration * .5f);
            }
        }
    }

    @Override
    protected void update(float percent) {
        super.update(percent);
        getActor().setRotation(getActor().getRotation() + (amount * nextPercent));
        //	if (!isReverse())	getActor().rotation += amount*nextPercent;
        //	else				getActor().rotation -= amount*nextPercent;
    }

    @Override
    protected void end() {
        if (autoReverse && cycles == 0) {
            autoReverse = false;
            amount = -amount;
            restart();
            cycles = 1;
        } else if (cycles == 1) {
            getActor().setRotation(initRot);
            amount = -amount;
            autoReverse = initAutoReverse;
            cycles = 0;
        }
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
        initAutoReverse = autoReverse;
    }

    @Override
    public RotateByAction clone() {
        RotateByAction rba = new RotateByAction();
        rba.setAmount(amount);
        rba.setDuration(getDuration());
        rba.setInterpolation(getInterpolation());
        rba.setAutoRestart(getAutoRestart());
        rba.setAutoReverse(autoReverse);
        rba.setForceJmeTransform(forceJmeTransform);
        return rba;
    }
}
