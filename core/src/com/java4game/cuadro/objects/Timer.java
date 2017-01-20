package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 20.01.2017 16:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Timer {

    private Sprite cover;
    private TextBlock starTimeBlock, timeLetter;
    private Blink blinks[];
    private float allSeconds;

    private LevelGen levelGen;
    public Timer(LevelGen levelGen, float allSeconds) {
        this.levelGen = levelGen;
        this.allSeconds = allSeconds;

        initCover();
        initTimeLetter();
        initTimeBlock();
        initBlinks();
    }
    private void initTimeBlock(){
        starTimeBlock = new TextBlock(0f, 0f, true, "000:00");
        starTimeBlock.setCustomCff(cover.getHeight() / 5f);
        starTimeBlock.setPosition(cover.getX() + cover.getWidth() / 2f,
                cover.getY() + cover.getHeight() / 2f);
        starTimeBlock.setPositionToCenter();
    }

    private void initBlinks(){
        blinks = new Blink[5];

        blinks[0] = new Blink(cover.getX() + cover.getWidth() * 0.197f, cover.getY());
        blinks[1] = new Blink(cover.getX() + cover.getWidth() * 0.798f, cover.getY());
        blinks[2] = new Blink(cover.getX(), cover.getY() + cover.getHeight() * 0.615f);
        blinks[3] = new Blink(cover.getX() + cover.getWidth(), cover.getY() + cover.getHeight() * 0.615f);
        blinks[4] = new Blink(cover.getX() + cover.getWidth() * 0.5f, cover.getY() + cover.getHeight());
    }
    
    private void initCover(){
        cover = Assets.getNewSprite(35);
        cover.setSize(3f, 3f);
        cover.setPosition((Gm.WIDTH - cover.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        cover.setOriginCenter();
        coverInterpolation = Interpolation.exp5Out;
    }

    private void initTimeLetter(){
        final String text = "TIME";
        timeLetter = new TextBlock(0f, 0f, true, text);
        timeLetter.setCustomCff(cover.getHeight() / 4f);
        timeLetter.setPosition(cover.getX() + cover.getWidth() * 0.314f - timeLetter.getBounds().getWidth() * 0.53f,
                cover.getY() + cover.getHeight() - timeLetter.getBounds().getHeight() * 0.9f);
        timeLetter.setPositionToCenter();
    }

    private Interpolation coverInterpolation;
    public void handle(float alpha){
        cover.setAlpha(alpha);
        cover.setRotation(coverInterpolation.apply(360f, 0f, alpha / 1f));
        starTimeBlock.setAlpha(alpha);
        timeLetter.setAlpha(alpha);
        timeLetter.setOffsetX((1f - alpha) * (-timeLetter.getBounds().getWidth() / 4f));
        handle();
    }

    public void handle(){
        if (allSeconds - Gdx.graphics.getDeltaTime() < 0f){
            allSeconds = 0f;
            levelGen.lose();
        }
        else
            allSeconds -= Gdx.graphics.getDeltaTime();

        setStarTimeblockText();
    }

    private StringBuilder stringBuilder = new StringBuilder(100);
    private final String twoDots = ":";
    private final String nullS = "0";

    private void setStarTimeblockText(){
        final int seconds = (int) allSeconds;
        final int milliseconds = (int) (new BigDecimal(allSeconds - seconds).setScale(2, BigDecimal.ROUND_FLOOR).floatValue() * 100f);
        stringBuilder.setLength(0);

        if (seconds < 10) stringBuilder.append(nullS);
        stringBuilder.append(String.valueOf(seconds));

        stringBuilder.append(twoDots);

        if (milliseconds < 10) stringBuilder.append(nullS);
        stringBuilder.append(String.valueOf(milliseconds));


        starTimeBlock.setText(stringBuilder.toString());
        starTimeBlock.setPosition(cover.getX() + cover.getWidth() / 2f,
                cover.getY() + cover.getHeight() / 2f);
        starTimeBlock.setPositionToCenter();
    }

    public void draw(SpriteBatch batch) {
        cover.draw(batch);
        timeLetter.draw(batch);

        starTimeBlock.draw(batch);

        for (int i = 0; i < blinks.length; i++) {
            blinks[i].draw(batch);
        }

    }
}
