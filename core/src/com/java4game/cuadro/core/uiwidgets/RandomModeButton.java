package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;

/**
 * Created by FOGOK on 14.03.2017 6:02.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class RandomModeButton extends BaseButton {

    public static int RNDLEVEL;
    private int rndLevel;
    private boolean isLocked;

    public RandomModeButton(float x, float y, int back, int lockBack, int rndLevel) {
        super(ButtonActions.All.START_RANDOM_MODE, x, y, 3f, back, lockBack);
        this.rndLevel = rndLevel;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public void draw(SpriteBatch batch) {
        calcM();
        handleTouchAnimation();
        final Sprite drawingTexture = isLocked ? touchedTex : normalTex;
        drawingTexture.draw(batch);
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        normalTex.setPosition(bounds.x, bounds.y);
        touchedTex.setPosition(bounds.x, bounds.y);
    }

    private float btnScale;
    private void handleTouchAnimation(){
        final float scaleMaxDispersion = 0.3f;
        final float scaleSpeed = 0.1f * Gm.mdT;
        if (isTouched){
            if (btnScale < 1f + scaleMaxDispersion)
                btnScale += scaleSpeed;
            else
                btnScale = 1f + scaleMaxDispersion;
        }else{
            if (btnScale > 1f)
                btnScale -= scaleSpeed;
            else
                btnScale = 1f;
        }
        Sprite spr = isLocked ? touchedTex : normalTex;
        spr.setScale(btnScale);
    }

    @Override
    protected void activateAction() {
        super.activateAction();
        RNDLEVEL = rndLevel;
    }
}
