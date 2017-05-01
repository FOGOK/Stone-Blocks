package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 03.11.2016 15:17.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class StageButton extends TextButton {

    public static int LEVEL;

    public static final int ARKADE_LEVEL = 2000;
    public static final int RANDOM_LEVEL = 3000;

    private int starCount;
    private int stage;
    private boolean isLockedStage;
    private boolean isCompleteStage;
    private StarBlock.Star currentStar;
    private float size;

    private Sprite lockedTexture, normalTexture, completeTexture, starTexture, learnIcon;
    private MenuUI menuUI;

    public StageButton(ButtonActions.All action, float h, int stage, MenuUI menuUI) {
        super(action, 0f, 0f, h, 25, 28, (stage) + "");
        this.menuUI = menuUI;
        this.stage = stage;
        size = h;
        lockedTexture = Assets.getNewSprite(26);
        normalTexture = Assets.getNewSprite(25);
        completeTexture = Assets.getNewSprite(27);
        learnIcon = Assets.getNewSprite(35);
        learnIcon.setSize(h / 2f, h / 2f);
        soundEnabled = false;

        getTextBlock().setCustomCff(h / 3f);
        currentStar = StarBlock.Star.None;
    }

    @Override
    protected void activateAction() {
        super.activateAction();
        if (action == ButtonActions.All.RESTART_PAUSE_ACTION){
            LEVEL = stage;
            MusicCore.play(MusicCore.GAME);
            LevelGen.REFRESH_REFRESH = true;
        }else{ //endLearningDat
            menuUI.refreshStarsData();
            MenuUI.SETSTAGEPROP = true;
        }
    }

    public void setLockedStage(boolean lockedStage) {
        isLockedStage = lockedStage;
        refreshState();
    }

    public void setCompleteStage(boolean completeStage, StarBlock.Star currentStar) {
        isCompleteStage = completeStage;
        this.currentStar = currentStar;
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
            if (isCompleteStage){
                setFirstTexture(completeTexture);
                float sizeTex = size / 3f;
                switch (currentStar){
                    case Gold:
                        starTexture = Assets.getNewSprite(35);
                        starTexture.setSize(sizeTex, sizeTex);
                        break;
                    case Silver:
                        starTexture = Assets.getNewSprite(34);
                        starTexture.setSize(sizeTex, sizeTex);
                        break;
                    case Bronze:
                        starTexture = Assets.getNewSprite(33);
                        starTexture.setSize(sizeTex, sizeTex);
                        break;
                }
            }
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
        if (action == ButtonActions.All.RESTART_PAUSE_ACTION){
            if (!isLockedStage){
                super.draw(batch);
                if (starTexture != null){
                    starTexture.setPosition(completeTexture.getX() + completeTexture.getWidth() * 0.933f - starTexture.getWidth() / 2f,
                            completeTexture.getY() + completeTexture.getHeight() * 0.866f - starTexture.getWidth() / 2f);
                    starTexture.draw(batch);
                }
            }
            else
                drawButtonTexture(batch);
        }else {
            calcM();
            drawButtonTexture(batch);
            drawLearnIcon(batch);
        }
    }

    private void drawLearnIcon(SpriteBatch batch){
        learnIcon.setPosition(normalTex.getX() + (normalTex.getWidth() - learnIcon.getWidth()) / 2f, normalTex.getY() + (normalTex.getHeight() - learnIcon.getHeight()) * 0.6f);
        learnIcon.draw(batch);
    }
}
