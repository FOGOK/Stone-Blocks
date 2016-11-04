package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by FOGOK on 03.11.2016 15:17.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class StageButton extends TextButton {

    private int starCount;
    private boolean isLockedStage;
    private Sprite lockedTexture, normalTexture;
    private Sprite nullStars, oneStars, twoStars, threeStars;
    private float starsX, starsY, starsW, starsH;

    public StageButton(TextureGen textureGen, ButtonActions.All action, float h, int stage) {
        super(textureGen, action, 0f, 0f, h, Atalas.btnStage, Atalas.btnStageAct, stage + "");
        lockedTexture = textureGen.getSprite(Atalas.btnStageLock);
        normalTexture = textureGen.getSprite(Atalas.btnStage);
        initStars(textureGen);
    }

    private void initStars(TextureGen textureGen){
        nullStars = textureGen.getSprite(Atalas.noStar);
        oneStars = textureGen.getSprite(Atalas.star1);
        twoStars = textureGen.getSprite(Atalas.star2);
        threeStars = textureGen.getSprite(Atalas.star3);
    }
    private void setPositionStars(int starCount){
        starsX = bounds.x + bounds.width * 0.294f;
        starsY = bounds.y + bounds.height * 0.747f;
        starsW = bounds.width * 0.7f;
        starsH = starsW * 0.4955f;

        switch (starCount){
            case 0:
                nullStars.setBounds(starsX, starsY, starsW, starsH);
                break;
            case 1:
                oneStars.setBounds(starsX, starsY, starsW, starsH);
                break;
            case 2:
                twoStars.setBounds(starsX, starsY, starsW, starsH);
                break;
            case 3:
                threeStars.setBounds(starsX, starsY, starsW, starsH);
                break;
        }
    }
    public void setLockedStage(boolean lockedStage) {
        isLockedStage = lockedStage;
        refreshState();
    }
    public boolean isLockedStage(){
        return isLockedStage;
    }
    private void refreshState(){
        if (isLockedStage){
            setFirstTexture(lockedTexture);
            setEnabled(false);
        }else{
            setFirstTexture(normalTexture);
            setEnabled(true);
        }
    }
    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }



    @Override
    public void draw(final SpriteBatch batch) {
        if (!isLockedStage){
            super.draw(batch);
            drawStars(batch);
        }
        else
            drawButtonTexture(batch);
    }

    private void drawStars(final SpriteBatch batch){
        setPositionStars(starCount);
        switch (starCount){
            case 0:
                nullStars.draw(batch);
                break;
            case 1:
                oneStars.draw(batch);
                break;
            case 2:
                twoStars.draw(batch);
                break;
            case 3:
                threeStars.draw(batch);
                break;
        }
    }
}
