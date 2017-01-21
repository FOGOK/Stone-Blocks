package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GMUtils;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 20.01.2017 16:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Timer {

    private StarBlock.Star currentStar;
    private Sprite noneStar, bronzeStar, silverStar, goldStar;
    private TextBlock starTimeBlock, timeLetter, allTimeBlock;
    private Blink blinks[];
    private float goldSeconds, silverSeconds, bronzeSecond, allSeconds;

    private float thisIters = 1f;
    private boolean isStartAnimThisIters;

    private LevelGen levelGen;
    public Timer(LevelGen levelGen, float goldSeconds) {
        this.levelGen = levelGen;
        initVars(goldSeconds);

        initStars();
        initTimeLetter();
        initTimeBlock();
        initAllTimeBlock();
        initBlinks();
    }
    private void initVars(float goldSeconds){
        this.goldSeconds = goldSeconds;
        final float deltaSeconds = 7.85f;
        silverSeconds = deltaSeconds;
        bronzeSecond = deltaSeconds;
        allSeconds = goldSeconds + silverSeconds + bronzeSecond * 1.3f;

        currentStar = StarBlock.Star.Gold;
    }

    private void initTimeBlock(){
        starTimeBlock = new TextBlock(0f, 0f, true, "000:00");
        starTimeBlock.setCustomCff(goldStar.getHeight() / 5f);
        starTimeBlock.setPosition(goldStar.getX() + goldStar.getWidth() / 2f,
                goldStar.getY() + goldStar.getHeight() / 2f);
        starTimeBlock.setPositionToCenter();
    }

    private void initBlinks(){
        blinks = new Blink[5];

        blinks[0] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.197f, goldStar.getY());
        blinks[1] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.798f, goldStar.getY());
        blinks[2] = new Blink(goldStar.getX(), goldStar.getY() + goldStar.getHeight() * 0.615f);
        blinks[3] = new Blink(goldStar.getX() + goldStar.getWidth(), goldStar.getY() + goldStar.getHeight() * 0.615f);
        blinks[4] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.5f, goldStar.getY() + goldStar.getHeight());
    }
    
    private void initStars(){
        noneStar = Assets.getNewSprite(32);
        noneStar.setSize(3f, 3f);
        noneStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        noneStar.setOriginCenter();

        goldStar = Assets.getNewSprite(35);
        goldStar.setSize(3f, 3f);
        goldStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        goldStar.setOriginCenter();
        coverInterpolation = Interpolation.exp5Out;


        silverStar = Assets.getNewSprite(34);
        silverStar.setSize(3f, 3f);
        silverStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        silverStar.setOriginCenter();

        bronzeStar = Assets.getNewSprite(33);
        bronzeStar.setSize(3f, 3f);
        bronzeStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        bronzeStar.setOriginCenter();
    }

    private void initTimeLetter(){
        final String text = "TIME";
        timeLetter = new TextBlock(0f, 0f, true, text);
        timeLetter.setCustomCff(goldStar.getHeight() / 4f);
        timeLetter.setPosition(goldStar.getX() + goldStar.getWidth() * 0.314f - timeLetter.getBounds().getWidth() * 0.53f,
                goldStar.getY() + goldStar.getHeight() - timeLetter.getBounds().getHeight() * 0.9f);
        timeLetter.setPositionToCenter();
    }

    private void initAllTimeBlock(){
        allTimeBlock = new TextBlock(0f, 0f, true, "00:00");
        allTimeBlock.setCustomCff(noneStar.getHeight() / 5f);
        allTimeBlock.setPosition(noneStar.getX() + noneStar.getWidth() * 0.862f,
                noneStar.getY() + noneStar.getHeight() * 0.168f);
    }

    private Interpolation coverInterpolation;
    private boolean isPlayGoldS;
    public void handle(float alpha){
        if (!isPlayGoldS){
            MusicCore.playSound(0);
            isPlayGoldS = true;
        }
        Sprite curStar;
        switch (currentStar){
            case Gold: curStar = goldStar; break;
            case Silver: curStar = silverStar; break;
            case Bronze: curStar = bronzeStar; break;
            default: curStar = noneStar; break;
        }
        curStar.setAlpha(alpha);
        curStar.setRotation(coverInterpolation.apply(360f, 0f, alpha / 1f));
        starTimeBlock.setAlpha(alpha);
        timeLetter.setAlpha(alpha);
        timeLetter.setOffsetX(coverInterpolation.apply(-timeLetter.getBounds().getWidth() / 2f, 0f, alpha));
        allTimeBlock.setAlpha(alpha);
        allTimeBlock.setOffsetX(coverInterpolation.apply(timeLetter.getBounds().getWidth() / 2f, 0f, alpha));
    }

    public void handle(){
        if (thisIters < 1f)
            thisIters += Gdx.graphics.getDeltaTime();
        else
            thisIters = 1f;

        if (isStartAnimThisIters){
            isStartAnimThisIters = false;
            thisIters = 0f;
        }
        handle(GMUtils.normalizeOneZero(thisIters));
    }

    public void handleLogic(){
        switch (currentStar){
            case Gold:
                if (goldSeconds - Gdx.graphics.getDeltaTime() < 0f){
                    goldSeconds = 0f;
                    currentStar = StarBlock.Star.Silver;
                    MusicCore.playSound(2);
                    isStartAnimThisIters = true;
                }
                else
                    goldSeconds -= Gdx.graphics.getDeltaTime();

                setStarTimeblockText(goldSeconds, true);
                break;
            case Silver:
                if (silverSeconds - Gdx.graphics.getDeltaTime() < 0f){
                    silverSeconds = 0f;
                    currentStar = StarBlock.Star.Bronze;
                    MusicCore.playSound(1);
                    isStartAnimThisIters = true;
                }
                else
                    silverSeconds -= Gdx.graphics.getDeltaTime();

                setStarTimeblockText(silverSeconds, true);
                break;
            case Bronze:
                if (bronzeSecond - Gdx.graphics.getDeltaTime() < 0f){
                    bronzeSecond = 0f;
                    currentStar = StarBlock.Star.None;
                    isStartAnimThisIters = true;
                }
                else
                    bronzeSecond -= Gdx.graphics.getDeltaTime();

                setStarTimeblockText(bronzeSecond, true);
                break;
            case None:
                if (allSeconds - Gdx.graphics.getDeltaTime() < 0f){
                    allSeconds = 0f;
                    levelGen.lose();
                }
                else
                    allSeconds -= Gdx.graphics.getDeltaTime();

                setStarTimeblockText(allSeconds, true);
                break;
        }

        if (currentStar != StarBlock.Star.None){
            if (allSeconds - Gdx.graphics.getDeltaTime() < 0f){
                allSeconds = 0f;
                levelGen.lose();
            }
            else
                allSeconds -= Gdx.graphics.getDeltaTime();

            setStarTimeblockText(allSeconds, false);
        }

    }

    private StringBuilder stringBuilder = new StringBuilder(100);
    private final String twoDots = ":";
    private final String nullS = "0";

    private void setStarTimeblockText(float targetSeconds, boolean isStarTimeBlock){
        final int seconds = (int) targetSeconds;
        final int milliseconds = (int) (new BigDecimal(targetSeconds - seconds).setScale(1, BigDecimal.ROUND_FLOOR).floatValue() * 10f);
        stringBuilder.setLength(0);

        if (seconds < 10) stringBuilder.append(nullS);
        stringBuilder.append(String.valueOf(seconds));

        stringBuilder.append(twoDots);

        stringBuilder.append(String.valueOf(milliseconds));
        stringBuilder.append(nullS);


        if (isStarTimeBlock){
            starTimeBlock.setText(stringBuilder.toString());
            starTimeBlock.setPosition(goldStar.getX() + goldStar.getWidth() / 2f,
                    goldStar.getY() + goldStar.getHeight() / 2f);
            starTimeBlock.setPositionToCenter();
        }else{
            allTimeBlock.setText(stringBuilder.toString());
        }

    }

    public void draw(SpriteBatch batch) {
        timeLetter.draw(batch);
        switch (currentStar){
            case Gold:
                goldStar.draw(batch);
                break;
            case Silver:
                silverStar.draw(batch);
                break;
            case Bronze:
                bronzeStar.draw(batch);
                break;
            case None:
                noneStar.draw(batch);
                break;
        }
        starTimeBlock.draw(batch);
        if (currentStar != StarBlock.Star.None){
            allTimeBlock.draw(batch);
            if (thisIters == 1f){
                for (int i = 0; i < blinks.length; i++) {
                    blinks[i].draw(batch);
                }
            }
        }
    }

    public StarBlock.Star getCurrentStar() {
        return currentStar;
    }
}
