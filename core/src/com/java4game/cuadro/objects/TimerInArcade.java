package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.uiwidgets.BaseObject;
import com.java4game.cuadro.core.uiwidgets.RobotHead;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GameUtils;
import com.java4game.cuadro.utils.Localization;

import static com.java4game.cuadro.core.LevelGen.ISGAMEOVER;

/**
 * Created by FOGOK on 24.04.2017 17:37.
 */

public class TimerInArcade extends BaseObject{

    private Sprite cover, content;
    private TextBlock timerSeconds, timeText, minOneText;

    private float allSeconds = 60f;
    private float currentSeconds;

    private RobotHead robotHead;
    private LevelGen levelGen;

    private int lastSeconds;
    public TimerInArcade(LevelGen levelGen, float x, float y, float w, RobotHead robotHead) {
        super(x, y, w, w * 0.041f);
        this.robotHead = robotHead;
        this.levelGen = levelGen;
        float h = getBounds().getHeight();
        cover = Assets.getNewSprite(99);
        cover.setBounds(getBounds().getX(), getBounds().getY(), getBounds().getWidth(), getBounds().getHeight());

        float otstLeft = h * 0.09f, otstTop = h * 0.207f, otstRight = h * 0.06f, otstBot = h * 0.138f;

        content = new Sprite(GameUtils.generateWhitePic());
        content.setBounds(getBounds().getX() + otstLeft, getBounds().getY() + otstBot, getBounds().getWidth() - otstLeft - otstRight, getBounds().getHeight() - otstBot - otstTop);
        content.setOrigin(0f, 0f);
        content.setColor(Color.WHITE);

        float textSize = cover.getHeight() * 1.3f;
        timerSeconds = new TextBlock(0f, 0f,
                false, (allSeconds - 1) + "");
        timerSeconds.setCustomCff(textSize);

        float otst = 0.15f;
        timeText = new TextBlock(0f, 0f, false, Localization.getText(Localization.LettersKey.TIME));
        timeText.setCustomCff(textSize);
        timeText.setPosition(getBounds().getX(), getBounds().getY() + getBounds().getHeight() + otst);

        minOneText = new TextBlock(0f, 0f, false, "1 " + Localization.getText(Localization.LettersKey.MINUTE));
        minOneText.setCustomCff(textSize);
        minOneText.setPosition(getBounds().getX() + getBounds().getWidth() - minOneText.getBounds().getWidth(), getBounds().getY() + getBounds().getHeight() + otst);


        resetTime();
    }

    public void resetTime(){
        currentSeconds = allSeconds;
        timerSeconds.setText(currentSeconds + "");
        content.setScale(1f, content.getScaleY());
        refreshTextPos();
    }

    private void refreshTextPos(){
        timerSeconds.setPosition(
                getBounds().getX() + getBounds().getWidth() / 2f,
                getBounds().getY() + getBounds().getHeight() / 2f);
        timerSeconds.setPositionToCenter();
    }

    public void addTime(float timeAdd){
        currentSeconds += timeAdd;
        if (currentSeconds > allSeconds){
            currentSeconds = allSeconds;
            robotHead.setText(Localization.getText(Localization.LettersKey.MAXTIME), 0.7f).showInTimered(1f);
        }
    }

    public void setAlpha(float alpha){
        content.setAlpha(alpha);
        cover.setAlpha(alpha);
        timerSeconds.setAlpha(alpha);
        timeText.setAlpha(alpha);
        minOneText.setAlpha(alpha);
    }

    public void draw(SpriteBatch batch, boolean isHandle){
        content.draw(batch);
        cover.draw(batch);
        timerSeconds.draw(batch);
        timeText.draw(batch);
        minOneText.draw(batch);
        if (isHandle)
            handle();
    }

    private void handle(){
        if (!Handler.ISPAUSE && !ISGAMEOVER){
            currentSeconds -= Gdx.graphics.getDeltaTime();
            int secondsInt = ((int) currentSeconds);
            timerSeconds.setText(secondsInt + "");
            refreshTextPos();

            if (lastSeconds != secondsInt && secondsInt < 4 && secondsInt > 0){
                MusicCore.playSound(13);
                lastSeconds = secondsInt;
            }

            content.setScale(currentSeconds / allSeconds, content.getScaleY());

            if (currentSeconds < 1f){
                timerSeconds.setText("0");
                content.setScale(0f, content.getScaleY());
                levelGen.arkadeLose();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        draw(batch, false);
    }
}
