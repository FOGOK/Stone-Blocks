package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.BlockGenerator;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GameUtils;
import com.java4game.cuadro.utils.Prefers;

/**
 * Created by FOGOK on 21.01.2017 11:17.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameOverUI {

    private float time, allTime = 0.8f;
    private float time2, allTime2 = 0.8f;
    private Sprite blackSprite, arkadeWinSprite;
    private float sizeStar, ySquare;
    private TextBlock loseWinText, arkadeRecord;
    private boolean isWin;
    private int mode;

    private GameOverButton nextLevelB, mainMenuB, restartB;

    private Interpolation showRecord = Interpolation.swingOut;

    public GameOverUI(float sizeStar, float ySquare, int mode) {
        this.sizeStar = sizeStar;
        this.ySquare = ySquare;
        this.mode = mode;

        if (mode > 0){
            arkadeWinSprite = Assets.getNewSprite(mode == 1 ? 62 : 63);
            float sizeArkWinSprite = 1.8f;
            arkadeWinSprite.setSize(sizeArkWinSprite, sizeArkWinSprite);
            arkadeWinSprite.setOriginCenter();

            arkadeRecord = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT - 3.1f, true, "H");
            arkadeRecord.setCustomCff(sizeArkWinSprite * 0.4f);
            arkadeRecord.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT - 3.1f);
            arkadeRecord.setPositionToCenter();
        }

        time = allTime;
        time2 = allTime2;
        blackSprite = new Sprite(GameUtils.generateBlackPic());
        blackSprite.setSize(Gm.WIDTH, Gm.HEIGHT);
        initLoseWinText();
        initButtons();
    }

    private void initButtons(){
        final float sizeB = sizeStar / 2f;

        mainMenuB = new GameOverButton(MenuUI.TEST ? ButtonActions.All.RESTART_PAUSE_ACTION : ButtonActions.All.TOMAINMENU_PAUSE_ACTION,
                (Gm.WIDTH - sizeStar) / 2f,
                ySquare / 2f,   sizeB, 57, "MENU");
        mainMenuB.setPositionToCenter();

        nextLevelB = new GameOverButton(MenuUI.TEST ? ButtonActions.All.RESTART_PAUSE_ACTION : ButtonActions.All.NEXT_LEVEL_ACT,
                (Gm.WIDTH - sizeStar) / 2f + sizeStar,
                ySquare / 2f,   sizeB, 58, "NEXT");
        nextLevelB.setPositionToCenter();

        restartB = new GameOverButton(ButtonActions.All.RESTART_PAUSE_ACTION,
                (Gm.WIDTH - sizeStar) / 2f + sizeStar,
                ySquare / 2f,   sizeB, 56, "RESTART");
        restartB.setPositionToCenter();
    }

    private void initLoseWinText(){
        loseWinText = new TextBlock(0f, 0f, true, "H");
        loseWinText.setCustomCff(sizeStar / 2.9f);
    }

    public void setText(boolean isWin){
        this.isWin = isWin;
        loseWinText.setText(isWin ? "WIN" : "LOSE");
        loseWinText.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT / 2f - sizeStar * 0.075f);
        loseWinText.setPositionToCenter();
    }

    public void setScoreText(int scoreText, boolean isWin){
        this.isWin = isWin;
        loseWinText.setText(scoreText + "");
        loseWinText.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT / 2f - sizeStar * 0.075f);
        loseWinText.setPositionToCenter();
    }

    private int specialText = 0;

    public void setSpecialText(int specialText) {
        this.specialText = specialText;
    }

    public void setRecord(boolean isNewRecord){
        if (isNewRecord){
            if (mode == 1)
                arkadeRecord.setText("NEW RECORD: " + loseWinText.getText() + "!");
            else
                arkadeRecord.setText("GAMES PLAYED: " + specialText);

        }else{
            switch (TypeGameButton.TOUCHED_ARK){
                case 0:
                    arkadeRecord.setText("RECORD: " + Prefers.getInt(Prefers.KeyRecordBronze));
                    break;
                case 1:
                    arkadeRecord.setText("RECORD: " + Prefers.getInt(Prefers.KeyRecordSilver));
                    break;
                case 2:
                    arkadeRecord.setText("RECORD: " + Prefers.getInt(Prefers.KeyRecordGold));
                    break;
            }
        }
        arkadeRecord.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT - 3.1f);
        arkadeRecord.setPositionToCenter();
        arkadeWinSprite.setCenter(arkadeRecord.getBounds().getX() + arkadeRecord.getBounds().getWidth() / 2, arkadeRecord.getBounds().getY() + arkadeWinSprite.getHeight() / 2f + arkadeRecord.getBounds().getHeight() * 1.3f);
    }


    public void drawBack(SpriteBatch batch) {
        blackSprite.setAlpha((1f - time / allTime) * 0.4f);
        blackSprite.draw(batch);
    }

    public void draw(SpriteBatch batch){
        handleTime();
        float alpha = getTime2();
        loseWinText.setAlpha(alpha);
        loseWinText.draw(batch);

        mainMenuB.setAlpha(alpha);
        mainMenuB.draw(batch);
        if (isWin){
            nextLevelB.setAlpha(alpha);
            nextLevelB.draw(batch);
            if (mode == 2)
                topInfShow(batch, alpha);
        }else{
            restartB.setAlpha(alpha);
            restartB.draw(batch);
            if (mode == 1)
                topInfShow(batch, alpha);
        }
    }

    private void topInfShow(SpriteBatch batch, float alpha){
        arkadeWinSprite.setScale(showRecord.apply(0, 1f, alpha / 1f));
        arkadeWinSprite.setRotation(showRecord.apply(0, 360, alpha / 1f));
        arkadeWinSprite.draw(batch, alpha);
        arkadeRecord.setAlpha(alpha);
        arkadeRecord.draw(batch);
    }

    private void handleTime(){
        if (time - Gdx.graphics.getDeltaTime() > 0f)
            time -= Gdx.graphics.getDeltaTime();
        else if (time2 - Gdx.graphics.getDeltaTime() > 0f){
            time = 0f;
            time2 -= Gdx.graphics.getDeltaTime();
        }else
            time2 = 0f;
    }

    public float getTime() {
        return time / allTime;
    }

    public float getTime2() {
        return 1f - time2 / allTime2;
    }

    public void dispose(){
        blackSprite.getTexture().dispose();
    }
}
