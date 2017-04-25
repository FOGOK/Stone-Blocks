package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.usie.MenuUI;

/**
 * Created by FOGOK on 21.01.2017 14:03.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameOverButton extends BaseButton {

    private TextBlock textBlock;
    private float scale;
    public GameOverButton(ButtonActions.All action, float x, float y, float h, int back, String text) {//info
        super(action, x, y, h, back, back);
        textBlock = new TextBlock(x + bounds.getWidth() / 2f, y - h * 0.35f, false, text);
        textBlock.setPositionToCenter();
        textBlock.setCustomCff(h * 0.35f);
        scale = 1f;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        textBlock.setAlpha(alpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        textBlock.setPosition(x, y - normalTex.getHeight() * 0.7f);
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        normalTex.setPosition(bounds.getX(), bounds.getY());
        touchedTex.setPosition(bounds.getX(), bounds.getY());

        textBlock.setPosition(bounds.getX() + bounds.getWidth() / 2f, bounds.getY() - bounds.getHeight() * 0.35f);
        textBlock.setPositionToCenter();
    }

    @Override
    protected void activateAction() {
        if (action == ButtonActions.All.NEXT_LEVEL_ACT){
            if (StageButton.LEVEL != MenuUI.COUNTSTAGESINWORLD[TypeGameButton.TOUCHED_B]){
                if (StageButton.LEVEL != StageButton.RANDOM_LEVEL)
                    StageButton.LEVEL++;
                super.activateAction();
                LevelGen.REFRESH_REFRESH = true;
            }else
                ButtonActions.activateAction(ButtonActions.All.TOMAINMENU_PAUSE_ACTION);
        }else
            super.activateAction();
    }

    @Override
    public void draw(SpriteBatch batch) {
        handleTouchAnimation();
        super.draw(batch);
        textBlock.draw(batch);
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
        Sprite spr = isTouched ? touchedTex : normalTex;
        float scl = isTouched ? btnScale : scale;
        spr.setScale(scl);
    }

    public TextBlock getTextBlock() {
        return textBlock;
    }
}
