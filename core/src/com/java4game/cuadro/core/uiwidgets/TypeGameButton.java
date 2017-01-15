package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 14.01.2017 22:40.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TypeGameButton extends BaseButton {

    private static final int STEPS = 0, TIME = 1, ARCADE = 2, RAND = 3, STATS = 4;
    public static int TOUCHED_B;
    private int type;
    private boolean isSelected;
    private Sprite selectedSprite, normalSprite;
    private TextBlock textBlock;
    public TypeGameButton(ButtonActions.All action, float x, float y, float h, int back, int type) {
        super(action, x, y, h, back, back);
        normalSprite = Assets.getNewSprite(back);
        selectedSprite = Assets.getNewSprite(back + 5);
        this.type = type;
        textBlock = new TextBlock(x, y - h * 0.7f, false, "");
        switch (type){
            case STEPS:
                textBlock.setText("STEPS");
                break;
            case TIME:
                textBlock.setText("TIME");
                break;
            case ARCADE:
                textBlock.setText("ARCADE");
                break;
            case RAND:
                textBlock.setText("RAND");
                break;
            case STATS:
                textBlock.setText("STATS");
                break;
        }
        textBlock.setCustomCff(h / 3f);
    }

    public void setSelected(boolean selected) {
        if (selected != isSelected){
            isSelected = selected;
            changeSelected();
        }
    }

    private void changeSelected(){
        setFirstTexture(isSelected ? selectedSprite : normalSprite);
        setSecondTexture(isSelected ? selectedSprite : normalSprite);
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        textBlock.setPositionToCenter();
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
        spr.setScale(btnScale);
    }

    @Override
    protected void activateAction() {
        TOUCHED_B = type;
        super.activateAction();
    }
}
