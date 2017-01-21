package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.GameUtils;

/**
 * Created by FOGOK on 21.01.2017 11:17.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameOverUI {

    private float time, allTime = 1.3f;
    private float time2, allTime2 = 0.8f;
    private Sprite blackSprite;
    private float sizeStar, ySquare;
    private TextBlock loseWinText;
    private boolean isWin;

    private GameOverButton nextLevelB, mainMenuB, restartB;

    public GameOverUI(float sizeStar, float ySquare) {
        this.sizeStar = sizeStar;
        this.ySquare = ySquare;

        time = allTime;
        time2 = allTime2;
        blackSprite = new Sprite(GameUtils.generateBlackPic());
        blackSprite.setSize(Gm.WIDTH, Gm.HEIGHT);
        initLoseWinText();
        initButtons();
    }

    private void initButtons(){
        final float sizeB = sizeStar / 2f;

        mainMenuB = new GameOverButton(ButtonActions.All.TOMAINMENU_PAUSE_ACTION,
                (Gm.WIDTH - sizeStar) / 2f,
                ySquare / 2f,   sizeB, 57, "MENU");
        mainMenuB.setPositionToCenter();

        nextLevelB = new GameOverButton(ButtonActions.All.NEXT_LEVEL_ACT,
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

    public void drawBack(SpriteBatch batch) {
        blackSprite.setAlpha((1f - time / allTime) * 0.5f);
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
        }else{
            restartB.setAlpha(alpha);
            restartB.draw(batch);
        }
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
