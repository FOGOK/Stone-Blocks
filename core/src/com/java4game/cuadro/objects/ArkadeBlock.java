package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;

import static com.java4game.cuadro.core.LevelGen.ISGAMEOVER;

/**
 * Created by FOGOK on 26.02.2017 4:09.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ArkadeBlock {

    private Sprite currStar;
    private TextBlock scoreText;
    private Blink blinks[];
    private int score = 0;
    private int currentStar;

    private float posYStar;

    private boolean showAnimation, updateScoreAnimation;
    private int endLevelFaza;

    private Interpolation updateScoreAnimateInter = Interpolation.elasticOut, showAnimateInter = Interpolation.pow3Out;
    private float interpTimer, interpMax = 1.2f;

    public ArkadeBlock(int currentStar) {
        this.currentStar = currentStar;
        initStar();
        initScoreText();
        initBlinks(currStar);
    }

    private void initScoreText(){
        scoreText = new TextBlock(0f, 0f, true, "0");
        scoreText.setCustomCff(currStar.getHeight() / 4f);
        updateScoreTextPosition();
    }


    private void updateScoreTextPosition(){
        scoreText.setPosition(currStar.getX() + currStar.getWidth() / 2f,
                currStar.getY() + currStar.getHeight() / 2f);
        scoreText.setPositionToCenter();
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

    private void initStar(){
        currStar = Assets.getNewSprite(33 + currentStar);
        currStar.setSize(3f, 3f);
        currStar.setPosition((Gm.WIDTH - currStar.getWidth()) / 2f, Gm.HEIGHT - 3.1f);
        posYStar = currStar.getY();
        currStar.setOriginCenter();
    }

    public void draw(SpriteBatch batch) {
        drawStar(batch);
        drawText(batch);
        drawStarBlinks(batch);
    }

    public void showAnimate(){
        showAnimation = true;
        updateScoreAnimation = false;
        interpTimer = 0;
    }



    public void drawStar(SpriteBatch batch){
        if (updateScoreAnimation || showAnimation){
            if (interpTimer + Gdx.graphics.getDeltaTime() < interpMax){
                interpTimer += Gdx.graphics.getDeltaTime();
                if (showAnimation)
                    currStar.setAlpha(showAnimateInter.apply(0f, 1f, interpTimer / interpMax));
                else
                    currStar.setAlpha(1f);
                currStar.setY(posYStar + updateScoreAnimateInter.apply(2, 0f, interpTimer / interpMax));

            }
            else{
                currStar.setAlpha(1f);
                currStar.setY(posYStar);
                interpTimer = interpMax;
            }
        }

        if (ISGAMEOVER){
            if (endLevelFaza == 0){
                showAnimation = updateScoreAnimation = false;
                interpTimer = 0;
                endLevelFaza = 1;
            }

            if (interpTimer + Gdx.graphics.getDeltaTime() < interpMax)
                interpTimer += Gdx.graphics.getDeltaTime();
            else{
                if (endLevelFaza == 1){
                    interpTimer = interpMax;
                    initBlinks(currStar);
                    endLevelFaza = 2;
                }
            }
            currStar.setRotation(showAnimateInter.apply(0f, 360f, interpTimer / interpMax));
            currStar.setScale(showAnimateInter.apply(1f, 1.5f, interpTimer / interpMax));
            currStar.setY(showAnimateInter.apply(posYStar, (Gm.HEIGHT - currStar.getHeight()) / 2f, interpTimer / interpMax));
        }

        currStar.draw(batch);
    }

    public void drawStarBlinks(SpriteBatch batch){
        if (interpTimer == interpMax){
            for (int i = 0; i < blinks.length; i++) {
                blinks[i].draw(batch);
            }
        }
    }

    public void drawText(SpriteBatch batch){
        if (updateScoreAnimation || showAnimation){
            if (showAnimation)
                scoreText.setAlpha(showAnimateInter.apply(0f, 1f, interpTimer / interpMax));
            else
                scoreText.setAlpha(1f);
        }else
            scoreText.setAlpha(1f);

        scoreText.setOffsetY(currStar.getY() - posYStar);
        scoreText.draw(batch);
    }

    public void updateScore(int scoreP){
        score += scoreP;
        scoreText.setText(score + "");
        updateScoreTextPosition();
        updateScoreAnimation = true;
        interpTimer = 0;
        showAnimation = false;
    }

    public int getScore() {
        return score;
    }

    public float getStarSize(){
        return currStar.getHeight();
    }
}
