package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;

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
        initBlinks();
    }

    private void initBlinks(){
        blinks = new Blink[5];

        blinks[0] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.197f, goldStar.getY());
        blinks[1] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.798f, goldStar.getY());
        blinks[2] = new Blink(goldStar.getX(), goldStar.getY() + goldStar.getHeight() * 0.615f);
        blinks[3] = new Blink(goldStar.getX() + goldStar.getWidth(), goldStar.getY() + goldStar.getHeight() * 0.615f);
        blinks[4] = new Blink(goldStar.getX() + goldStar.getWidth() * 0.5f, goldStar.getY() + goldStar.getHeight());
    }
    private void initVars(int goldSteps){
        this.goldSteps = goldSteps;
        silverSteps = 1;
        bronzeSteps = 1;
        allSteps = (int) ((goldSteps + 2) * 1.3f);
        currentStar = Star.Gold;
    }
    private void initStarSprites(){
        noneStar = Assets.getNewSprite(32);
        noneStar.setSize(3f, 3f * 0.9523f);
        noneStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        noneStar.setOriginCenter();

        goldStar = Assets.getNewSprite(35);
        goldStar.setSize(3f, 3f * 0.9521f);
        goldStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        goldStar.setOriginCenter();
        goldStarInterpolation = Interpolation.exp5Out;


        silverStar = Assets.getNewSprite(34);
        silverStar.setSize(3f, 3f * 0.9521f);
        silverStar.setPosition((Gm.WIDTH - noneStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        silverStar.setOriginCenter();

        bronzeStar = Assets.getNewSprite(33);
        bronzeStar.setSize(3f, 3f * 0.9521f);
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

    private Interpolation goldStarInterpolation;
    public void handle(float alpha){
        noneStar.setAlpha(alpha);
        stepsLetter.setAlpha(alpha);
        stepsLetter.setOffsetX((1f - alpha) * (-stepsLetter.getBounds().getWidth() / 4f));
        Sprite curStar;
        switch (currentStar){
            case Gold: curStar = goldStar; break;
            case Silver: curStar = silverStar; break;
            case Bronze: curStar = bronzeStar; break;
            default: curStar = noneStar; break;
        }
        curStar.setAlpha(alpha);
        curStar.setRotation(goldStarInterpolation.apply(360f, 0f, alpha / 1f));
        allStepsBlock.setAlpha(alpha);
        allStepsBlock.setOffsetX((1f - alpha) * (allStepsBlock.getBounds().getWidth() / 4f));
        starStepsBlock.setAlpha(alpha);
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

        handle(thisIters);
    }

    public void drawGlass(SpriteBatch batch){
        if (currentStar == Star.None)
            noneStar.draw(batch);
    }

    public void drawMetalAndText(SpriteBatch batch){
        stepsLetter.draw(batch);
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
        }
        starStepsBlock.draw(batch);
        if (currentStar != Star.None){
            allStepsBlock.draw(batch);
            if (thisIters == 1f){
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
                    starStepsBlock.setText(silverSteps + "");
                    isStartAnimThisIters = true;
                }
                break;
            case Silver:
                if (silverSteps < 1) {
                    currentStar = Star.Bronze;
                    starStepsBlock.setText(bronzeSteps + "");
                    isStartAnimThisIters = true;
                }
                break;
            case Bronze:
                if (bronzeSteps < 1){
                    currentStar = Star.None;
                    starStepsBlock.setText(allSteps + "");
                    isStartAnimThisIters = true;
                }
                break;
            case None:
                if (allSteps < 1){
                    levelGen.lose();
                }
                break;
        }
        starStepsBlock.setPosition(noneStar.getX() + noneStar.getWidth() / 2f,
                noneStar.getY() + noneStar.getHeight() / 2f);
        starStepsBlock.setPositionToCenter();
    }

    public Star getCurrentStar() {
        return currentStar;
    }
}
