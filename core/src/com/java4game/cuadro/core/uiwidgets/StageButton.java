package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 03.11.2016 15:17.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class StageButton extends TextButton {

    public static int LEVEL;

    private int starCount;
    private int stage;
    private boolean isLockedStage;
    private boolean isCompleteStage;

    private Sprite lockedTexture, normalTexture, completeTexture;

    public StageButton(ButtonActions.All action, float h, int stage) {
        super(action, 0f, 0f, h, 25, 28, stage + "");
        this.stage = stage;
        lockedTexture = Assets.getNewSprite(26);
        normalTexture = Assets.getNewSprite(25);
        completeTexture = Assets.getNewSprite(27);
        getTextBlock().setCustomCff(h / 2f);
    }

    @Override
    protected void activateAction() {
        super.activateAction();
        LEVEL = stage;
        MusicCore.play(MusicCore.GAME);
    }

    public void setLockedStage(boolean lockedStage) {
        isLockedStage = lockedStage;
        refreshState();
    }

    public void setCompleteStage(boolean completeStage) {
        isCompleteStage = completeStage;
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
            if (isCompleteStage)
                setFirstTexture(completeTexture);
            else
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
        }
        else
            drawButtonTexture(batch);
    }
}
