/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tonegod.gui.framework.animation;

/**
 *
 * @author t0neg0d
 */
public class MoveByAction extends RelativeTemporalAction {

    private float initX = -1, initY = -1;
    private float amountX, amountY;
    private float initDuration = -1;
    private boolean autoReverse = false;
    private boolean initAutoReverse = false;
    private int cycles = 0;

    @Override
    protected void begin() {
        super.begin();
        if (initX == -1) {
            initX = getActor().getPosition().x;
        }
        if (initY == -1) {
            initY = getActor().getPosition().y;
        }
        if (autoReverse) {
            if (initDuration == -1) {
                initDuration = getDuration();
                setDuration(initDuration * .5f);
            }
        }
    }

    @Override
    protected void update(float percent) {
        super.update(percent);
        getActor().setPosition(getActor().getPosition().x + (amountX * nextPercent), getActor().getPosition().y + (amountY * nextPercent));
    }

    @Override
    protected void end() {
        if (autoReverse && cycles == 0) {
            autoReverse = false;
            amountX = -amountX;
            amountY = -amountY;
            restart();
            cycles = 1;
        } else if (cycles == 1) {
            getActor().setPosition(initX, initY);
            amountX = -amountX;
            amountY = -amountY;
            autoReverse = initAutoReverse;
            cycles = 0;
        }
    }

    public void setAmount(float x, float y) {
        amountX = x;
        amountY = y;
    }

    public void setAmount(float scale) {
        amountX = scale;
        amountY = scale;
    }

    public float getAmountX() {
        return amountX;
    }

    public void setAmountX(float x) {
        this.amountX = x;
    }

    public float getAmountY() {
        return amountY;
    }

    public void setAmountY(float y) {
        this.amountY = y;
    }

    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
        initAutoReverse = autoReverse;
    }

    @Override
    public MoveByAction clone() {
        MoveByAction mba = new MoveByAction();
        mba.setAmount(amountX, amountY);
        mba.setDuration(getDuration());
        mba.setAutoRestart(getAutoRestart());
        mba.setAutoReverse(autoReverse);
        mba.setInterpolation(getInterpolation());
        mba.setForceJmeTransform(forceJmeTransform);
        return mba;
    }
}
