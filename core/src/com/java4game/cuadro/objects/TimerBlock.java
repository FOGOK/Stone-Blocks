package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GMUtils;
import com.java4game.cuadro.utils.Localization;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 20.01.2017 16:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TimerBlock {

    private StarBlock.Star currentStar;
    private Sprite noneStar, bronzeStar, silverStar, goldStar;
    private TextBlock starTimeBlock, timeLetter, allTimeBlock;
    private Blink blinks[];
    private float goldSeconds, silverSeconds, bronzeSecond, allSeconds;
    private float startPostion;

    private float testTIMER;

    private float thisIters = 1f;
    private boolean isStartAnimThisIters;

    private LevelGen levelGen;
    public TimerBlock(LevelGen levelGen, float goldSeconds) {
        this.levelGen = levelGen;
        initVars(goldSeconds);

        initStars();
        initTimeLetter();
        initTimeBlock();
        initAllTimeBlock();
        initBlinks(goldStar);
    }
    private void initVars(float goldSeconds){
        this.goldSeconds = goldSeconds;
        final float deltaSeconds = 7f;
        silverSeconds = deltaSeconds;
        bronzeSecond = deltaSeconds;
        allSeconds = goldSeconds + silverSeconds + bronzeSecond;

        currentStar = StarBlock.Star.Gold;
        testTIMER = 0f;
    }

    private void initTimeBlock(){
        starTimeBlock = new TextBlock(0f, 0f, true, "000:00");
        starTimeBlock.setCustomCff(goldStar.getHeight() / 4f);
        starTimeBlock.setPosition(goldStar.getX() + goldStar.getWidth() / 2f,
                goldStar.getY() + goldStar.getHeight() / 2f);
        starTimeBlock.setPositionToCenter();
    }

    private void initBlinks(Sprite currStar){
        blinks = new Blink[5];

        float xDifference = (currStar.getScaleX() - 1f) / 2f * currStar.getWidth();
        float yDifference = (currStar.getScaleY() - 1f) / 2f * currStar.getHeight();

        blinks[0] = new Blink(currStar.getX() + currStar.getWidth() * 0.197f * currStar.getScaleX() - xDifference, currStar.getY() - yDifference);
        blinks[1] = new Blink(currStar.getX() + currStar.getWidth() * 0.798f * currStar.getScaleX() - xDifference, currStar.getY() - yDifference);
        blinks[2] = new Blink(currStar.getX() - xDifference, currStar.getY() + currStar.getHeight() * 0.615f * currStar.getScaleY() - yDifference);
        blinks[3] = new Blink(currStar.getX() + currStar.getWidth() * currStar.getScaleX() - xDifference, currStar.getY() + currStar.getHeight() * 0.615f * currStar.getScaleY() - yDifference);
        blinks[4] = new Blink(currStar.getX() + currStar.getWidth() * 0.5f * currStar.getScaleX() - xDifference, currStar.getY() + currStar.getHeight() * currStar.getScaleY() - yDifference);
    }
    
    private void initStars(){
        noneStar = Assets.getNewSprite(32);
        noneStar.setSize(3f, 3f);
        noneStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        noneStar.setOriginCenter();
        startPostion = noneStar.getY();

        goldStar = Assets.getNewSprite(35);
        goldStar.setSize(3f, 3f);
        goldStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        goldStar.setOriginCenter();
        coverInterpolation = Interpolation.exp5Out;
        reversInterpolation = Interpolation.exp5In;


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
        timeLetter = new TextBlock(0f, 0f, true, Localization.getText(Localization.LettersKey.TIMEMODE));
        timeLetter.setCustomCff(goldStar.getHeight() / 4f);
        timeLetter.setPosition(goldStar.getX() + goldStar.getWidth() * 0.314f - timeLetter.getBounds().getWidth() * 0.53f,
                goldStar.getY() + goldStar.getHeight() - timeLetter.getBounds().getHeight() * 0.9f);
        timeLetter.setPositionToCenter();
    }

    private void initAllTimeBlock(){
        allTimeBlock = new TextBlock(0f, 0f, true, "00:00");
        allTimeBlock.setCustomCff(noneStar.getHeight() / 4f);
        allTimeBlock.setPosition(noneStar.getX() + noneStar.getWidth() * 0.862f,
                noneStar.getY() + noneStar.getHeight() * 0.168f);
    }

    private Interpolation coverInterpolation, reversInterpolation;
    private boolean isPlayGoldS;
    private boolean isEndedL;
    private boolean isAnimationQ;
    public void handle(float alpha, boolean isRevers){
        if (!isPlayGoldS && !isRevers){
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

        if (isRevers){
            curStar.setRotation(reversInterpolation.apply(360f, 0f, alpha));
            curStar.setScale(coverInterpolation.apply(1.5f, 1f, alpha));
            curStar.setY(reversInterpolation.apply(startPostion, (Gm.HEIGHT - curStar.getHeight()) / 2f, 1f - alpha));
            curStar.setAlpha(1f);
            if (!isEndedL && alpha == 0f){
                initBlinks(curStar);
                isEndedL = true;
                isAnimationQ = false;
            }else if (!isEndedL)
                isAnimationQ = true;
        }
        else{
            curStar.setRotation(coverInterpolation.apply(360f, 0f, alpha));
            curStar.setAlpha(alpha);
        }


        starTimeBlock.setAlpha(MenuUI.TEST ? 1f : alpha);
        timeLetter.setAlpha(alpha);
        timeLetter.setOffsetX(coverInterpolation.apply(-timeLetter.getBounds().getWidth() / 2f, 0f, alpha));
        allTimeBlock.setAlpha(alpha);
        allTimeBlock.setOffsetX(coverInterpolation.apply(timeLetter.getBounds().getWidth() / 2f, 0f, alpha));
        if (alpha == 0 && MenuUI.TEST)
            setStarTimeblockText(testTIMER, true, true);
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
        handle(GMUtils.normalizeOneZero(thisIters), false);
    }
    public void handleLogic(){
        float minQ = Math.min(Gdx.graphics.getDeltaTime(), 0.032f);
        
        switch (currentStar){
            case Gold:
                if (goldSeconds - minQ < 1f){
                    silverSeconds += goldSeconds;
                    currentStar = StarBlock.Star.Silver;
                    MusicCore.playSound(2);
                    isStartAnimThisIters = true;
                }
                else
                    goldSeconds -= minQ;

                setStarTimeblockText(goldSeconds, true, false);
                break;
            case Silver:
                if (silverSeconds - minQ < 1f){
                    bronzeSecond += silverSeconds;
                    currentStar = StarBlock.Star.Bronze;
                    MusicCore.playSound(1);
                    isStartAnimThisIters = true;
                }
                else
                    silverSeconds -= minQ;

                setStarTimeblockText(silverSeconds, true, false);
                break;
            case Bronze:
                if (bronzeSecond - minQ < 1f){
                    bronzeSecond = 0f;
                    allSeconds = 0f;
                    currentStar = StarBlock.Star.None;
                    MusicCore.playSound(3);
                    levelGen.lose();
                }
                else
                    bronzeSecond -= minQ;

                setStarTimeblockText(bronzeSecond, true, false);
                break;
            case None:
//                if (allSeconds - minQ < 0f){
//                    allSeconds = 0f;
//                    levelGen.arkadeLose();
//                }
//                else
//                    allSeconds -= minQ;
//
//                setStarTimeblockText(allSeconds, true);
                break;
        }
        
        if (allSeconds - minQ > 0f)
            allSeconds -= minQ;
        setStarTimeblockText(allSeconds, false, false);

        testTIMER += minQ;

//        if (currentStar != StarBlock.Star.None){
//            if (allSeconds - Gdx.graphics.getDeltaTime() < 0f){
//                allSeconds = 0f;
//                levelGen.arkadeLose();
//            }
//            else
//                allSeconds -= Gdx.graphics.getDeltaTime();
//
//
//        }

    }

    private StringBuilder stringBuilder = new StringBuilder(100);
    private final String twoDots = ":";
    private final String nullS = "0";
    private final String secText = " sec";
    private int lastSeconds;
    private void setStarTimeblockText(float targetSeconds, boolean isStarTimeBlock, boolean MEGATEST){
        if (MenuUI.TEST){
            //
            final int seconds = (int) targetSeconds;
            final int milliseconds = (int) (new BigDecimal(targetSeconds - seconds).setScale(1, BigDecimal.ROUND_FLOOR).floatValue() * 10f);
            stringBuilder.setLength(0);

            if (seconds < 10) stringBuilder.append(nullS);
            stringBuilder.append(String.valueOf(seconds));

            if (lastSeconds != seconds && seconds < 4 && seconds > 0 && isStarTimeBlock){
                MusicCore.playSound(13);
                lastSeconds = seconds;
            }

            stringBuilder.append(twoDots);

            stringBuilder.append(String.valueOf(milliseconds));
            stringBuilder.append(nullS);
            //
        }else{
            //
            final int seconds = (int) targetSeconds;
            stringBuilder.setLength(0);
            stringBuilder.append(String.valueOf(seconds));

            if (lastSeconds != seconds && seconds < 4 && seconds > 0 && isStarTimeBlock){
                MusicCore.playSound(13);
                lastSeconds = seconds;
            }
            //
        }

        if (MEGATEST){
            starTimeBlock.setText(Localization.getText(Localization.LettersKey.ALLTIME) + stringBuilder.toString());
            starTimeBlock.setPosition(Gm.WIDTH / 2f,
                    Gm.HEIGHT * 0.9f);
            starTimeBlock.setPositionToCenter();
        }else{
            if (isStarTimeBlock){
                starTimeBlock.setText(stringBuilder.toString());
                starTimeBlock.setPosition(goldStar.getX() + goldStar.getWidth() / 2f,
                        goldStar.getY() + goldStar.getHeight() / 2f);
                starTimeBlock.setPositionToCenter();
            }else{
                allTimeBlock.setText(stringBuilder.toString());
            }
        }

    }

    public float getStarSize(){
        return goldStar.getHeight();
    }

    public void drawStar(SpriteBatch batch){
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
    }

    public void draw(SpriteBatch batch) {
        timeLetter.draw(batch);
        starTimeBlock.draw(batch);
        if (currentStar != StarBlock.Star.Bronze && currentStar != StarBlock.Star.None)
            allTimeBlock.draw(batch);
    }

    public void drawStarBlinks(SpriteBatch batch){
        if (currentStar != StarBlock.Star.None){
            if (thisIters == 1f && !isAnimationQ){
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
