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

/**
 * Created by FOGOK on 11.01.2017 6:00.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class StarBlock {

    public enum Star{
        None, Bronze, Silver, Gold
    }

    private Star currentStar;
    private Sprite noneStar, bronzeStar, silverStar, goldStar;
    private TextBlock allStepsBlock, starStepsBlock, stepsLetter;
    private int goldSteps, silverSteps, bronzeSteps, allSteps;
    private float startPostion;

    private float thisIters = 1f;
    private boolean isStartAnimThisIters;
    private Blink blinks[];

    private LevelGen levelGen;

    public StarBlock(LevelGen levelGen, int goldSteps) {
        this.levelGen = levelGen;
        initVars(goldSteps);
        initStarSprites();
        initStepsLetter();
        initStarSteps();
        initAllSteps();
        initBlinks(goldStar);
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

    private void initVars(int goldSteps){
        this.goldSteps = goldSteps;
        silverSteps = 1;
        bronzeSteps = 1;
        allSteps = goldSteps + 2;
//        allSteps = (int) ((goldSteps + 3) * 1.2f);
        currentStar = Star.Gold;
    }
    private void initStarSprites(){
        noneStar = Assets.getNewSprite(32);
        noneStar.setSize(3f, 3f);
        noneStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        noneStar.setOriginCenter();
        startPostion = noneStar.getY();

        goldStar = Assets.getNewSprite(35);
        goldStar.setSize(3f, 3f);
        goldStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        goldStar.setOriginCenter();
        goldStarInterpolation = Interpolation.exp5Out;
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
    private void initStepsLetter(){
        final String text = "STEPS";
        stepsLetter = new TextBlock(0f, 0f, true, text);
        stepsLetter.setCustomCff(noneStar.getHeight() / 4f);
        stepsLetter.setPosition(noneStar.getX() + noneStar.getWidth() * 0.314f - stepsLetter.getBounds().getWidth() * 0.53f,
                                noneStar.getY() + noneStar.getHeight() - stepsLetter.getBounds().getHeight() * 0.9f);
        stepsLetter.setPositionToCenter();
    }
    private void initStarSteps(){
        final String text = goldSteps + "";
        starStepsBlock = new TextBlock(0f, 0f, true, text);
        starStepsBlock.setCustomCff(noneStar.getHeight() / 3f);
        starStepsBlock.setPosition(noneStar.getX() + noneStar.getWidth() / 2f,
                noneStar.getY() + noneStar.getHeight() / 2f);
        starStepsBlock.setPositionToCenter();
    }
    private void initAllSteps(){
        final String text = allSteps + "";
        allStepsBlock = new TextBlock(0f, 0f, true, text);
        allStepsBlock.setCustomCff(noneStar.getHeight() / 3f);
        allStepsBlock.setPosition(noneStar.getX() + noneStar.getWidth() * 0.862f,
                noneStar.getY() + noneStar.getHeight() * 0.168f);
    }

    private Interpolation goldStarInterpolation, reversInterpolation;
    private boolean isPlayGoldS;
    private boolean isEndedL;
    private boolean isAnimationQ;
    public void handle(float alpha, boolean isRevers){
        if (!isPlayGoldS && !isRevers){
            MusicCore.playSound(0);
            isPlayGoldS = true;
        }
        noneStar.setAlpha(alpha);
        stepsLetter.setAlpha(alpha);
        stepsLetter.setOffsetX(goldStarInterpolation.apply(-stepsLetter.getBounds().getWidth() / 2f, 0f, alpha / 1f));
        Sprite curStar;
        switch (currentStar){
            case Gold: curStar = goldStar; break;
            case Silver: curStar = silverStar; break;
            case Bronze: curStar = bronzeStar; break;
            default: curStar = noneStar; break;
        }

        if (isRevers){
            curStar.setRotation(reversInterpolation.apply(360f, 0f, alpha));
            curStar.setScale(goldStarInterpolation.apply(1.5f, 1f, alpha));
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
            curStar.setAlpha(alpha);
            curStar.setRotation(goldStarInterpolation.apply(360f, 0f, alpha / 1f));
        }
        allStepsBlock.setAlpha(MenuUI.TEST ? 1f : alpha);
        allStepsBlock.setOffsetX(goldStarInterpolation.apply(stepsLetter.getBounds().getWidth() / 2f, 0f, alpha / 1f));
        starStepsBlock.setAlpha(MenuUI.TEST ? 1f : alpha);
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

    public void draw(SpriteBatch batch){
        stepsLetter.draw(batch);
        starStepsBlock.draw(batch);
        if (currentStar != Star.Bronze && currentStar != Star.None)
            allStepsBlock.draw(batch);
    }

    public void drawBlinks(SpriteBatch batch){
        if (currentStar != Star.None){
            if (thisIters == 1f && !isAnimationQ){
                for (int i = 0; i < blinks.length; i++) {
                    blinks[i].draw(batch);
                }
            }
        }
    }

    public void minusStep(){
        switch (currentStar){
            case Gold:
                goldSteps--;
                starStepsBlock.setText(goldSteps + "");
                break;
            case Silver:
                silverSteps--;
                starStepsBlock.setText(silverSteps + "");
                break;
            case Bronze:
                bronzeSteps--;
                starStepsBlock.setText(bronzeSteps + "");
                break;
        }
        allSteps--;
        if (currentStar != Star.None)
            allStepsBlock.setText(allSteps + "");
        else
            starStepsBlock.setText(allSteps + "");


        starStepsBlock.setPosition(noneStar.getX() + noneStar.getWidth() / 2f,
                noneStar.getY() + noneStar.getHeight() / 2f);
        starStepsBlock.setPositionToCenter();
    }

    public void inspectIsChangeStar(){
        switch (currentStar){
            case Gold:
                if (goldSteps < 1){
                    currentStar = Star.Silver;
                    MusicCore.playSound(2);
                    starStepsBlock.setText(silverSteps + "");
                    isStartAnimThisIters = true;
                }
                break;
            case Silver:
                if (silverSteps < 1) {
                    currentStar = Star.Bronze;
                    MusicCore.playSound(1);
                    starStepsBlock.setText(bronzeSteps + "");
                    isStartAnimThisIters = true;
                }
                break;
            case Bronze:
                if (bronzeSteps < 1){
                    currentStar = Star.None;
//                    starStepsBlock.setText(allSteps + "");
//                    isStartAnimThisIters = true;
                    levelGen.lose();
                }
                break;
            case None:
//                if (allSteps < 1){
//                    levelGen.lose();
//                }
                break;
        }
        starStepsBlock.setPosition(noneStar.getX() + noneStar.getWidth() / 2f,
                noneStar.getY() + noneStar.getHeight() / 2f);
        starStepsBlock.setPositionToCenter();
    }

    public float getStarSize(){
        return goldStar.getHeight();
    }

    public Star getCurrentStar() {
        return currentStar;
    }
}
