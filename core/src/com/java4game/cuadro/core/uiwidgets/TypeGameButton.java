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

    public static int RNDLEVEL;

    private static final int STEPS = 0, TIME = 1, ARCADE = 2, RAND = 3, STATS = 4;
    public static int TOUCHED_B, TOUCHED_ARK;
    private int type;
    private boolean isSelected;
    private Sprite selectedSprite, normalSprite, lockSprite, targSprite, winSprite;
    private TextBlock textBlock, textBlock2;
    private boolean isTextBlockEnable;
    private boolean isArkade;

    private boolean isLocked;


    public TypeGameButton(ButtonActions.All action, float x, float y, float h, int back, int type, boolean isTextBlockEnable, boolean isArkade) {
        super(action, x, y, h, back, back);
        this.isTextBlockEnable = isTextBlockEnable;

        normalSprite = Assets.getNewSprite(back);
        this.type = type;
        this.isArkade = isArkade;

        if (isTextBlockEnable){
            selectedSprite = Assets.getNewSprite(back + 5);
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
                    textBlock.setText("RECORDS");
                    break;
            }
            textBlock.setCustomCff(h / 4f);
        }else{
            lockSprite = Assets.getNewSprite(61);
            float lockSize = h * 0.28f;
            lockSprite.setSize(lockSize, lockSize);
            lockSprite.setOriginCenter();
            setFirstTexture(normalSprite);
            setSecondTexture(normalSprite);
            lockSprite.setPosition(bounds.getX() + (bounds.getWidth() - lockSprite.getWidth()) / 2f, bounds.getY() + (bounds.getHeight() - lockSprite.getHeight()) / 2f);

            textBlock = new TextBlock(x - h, y, false, "");
            textBlock.setCustomCff(h / 6f);

            textBlock2 = new TextBlock(x + h, y, false, "");
            textBlock2.setCustomCff(h / 6f);

            float minSpriteSize = h * 0.4f;

            if (isArkade){
                winSprite = Assets.getNewSprite(62);
                winSprite.setSize(minSpriteSize, minSpriteSize);
                winSprite.setOriginCenter();

                targSprite = Assets.getNewSprite(63);
                targSprite.setSize(minSpriteSize, minSpriteSize);
                targSprite.setOriginCenter();
            }

            refreshPositionSideInformation();
        }

    }

    public void setText(String text){
        textBlock.setText(text);
    }

    public void setText2(String text){
        textBlock2.setText(text);
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setSelected(boolean selected) {
        if (selected != isSelected && isTextBlockEnable){
            isSelected = selected;
            changeSelected();
        }
    }

    private void changeSelected(){
        if (isTextBlockEnable){
            setFirstTexture(isSelected ? selectedSprite : normalSprite);
            setSecondTexture(isSelected ? selectedSprite : normalSprite);
        }
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        if (!isTextBlockEnable){
            lockSprite.setPosition(bounds.getX() + (bounds.getWidth() - lockSprite.getWidth()) / 2f, bounds.getY() + (bounds.getHeight() - lockSprite.getHeight()) / 2f);
            refreshPositionSideInformation();
        }else
            textBlock.setPositionToCenter();

    }

    private void refreshPositionSideInformation(){

        if (isArkade){
            textBlock.setPosition(bounds.getX() + bounds.getWidth() / 2f - bounds.getWidth(), bounds.getY() + bounds.getHeight() / 2f);
            textBlock.setPositionToCenter(textBlock.getBounds().getWidth(), winSprite.getHeight() + textBlock.getBounds().getHeight() * 1.3f, false);
            textBlock2.setPosition(bounds.getX() + bounds.getWidth() / 2f + bounds.getWidth(), bounds.getY() + bounds.getHeight() / 2f);
            textBlock2.setPositionToCenter(textBlock2.getBounds().getWidth(), targSprite.getHeight() + textBlock2.getBounds().getHeight() * 1.3f, false);
            winSprite.setCenter(textBlock.getBounds().getX() + textBlock.getBounds().getWidth() / 2f, textBlock.getBounds().getY() + winSprite.getHeight() / 2f + textBlock.getBounds().getHeight() * 1.3f);
            targSprite.setCenter(textBlock2.getBounds().getX() + textBlock2.getBounds().getWidth() / 2f, textBlock2.getBounds().getY() + targSprite.getHeight() / 2f + textBlock2.getBounds().getHeight() * 1.3f);
        }else{
            textBlock.setPosition(bounds.getX() + bounds.getWidth() / 2f - bounds.getWidth(), bounds.getY() + bounds.getHeight() / 2f);
            textBlock.setPositionToCenter();
            textBlock2.setPosition(bounds.getX() + bounds.getWidth() / 2f + bounds.getWidth(), bounds.getY() + bounds.getHeight() / 2f);
            textBlock2.setPositionToCenter();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        handleTouchAnimation();
        super.draw(batch);
        if (!isTextBlockEnable && isLocked)
            lockSprite.draw(batch);

        if (!isLocked || (!isArkade && !isTextBlockEnable)){
            if (!isTextBlockEnable){
                if (isArkade){
                    winSprite.draw(batch);
                    targSprite.draw(batch);
                }
                textBlock2.draw(batch);
            }
            textBlock.draw(batch);
        }
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
        if (!isTextBlockEnable){
            lockSprite.setScale(btnScale);
        }
    }

    @Override
    protected void activateAction() {
        if (!isLocked){
            if (isTextBlockEnable)
                TOUCHED_B = type;
            else{
                if (isArkade)
                    TOUCHED_ARK = type;
                else
                    RNDLEVEL = type;
            }
            super.activateAction();
        }
    }
}
