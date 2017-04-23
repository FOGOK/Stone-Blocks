package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.usie.UI;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Timer;

import java.util.Random;


public class RobotHead {

    private Sprite head, dialogCover, leftEave, rightEave;
    private float sizeH;
    private String text;
    private float sizeText;
    private Rectangle textAreaBounds;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private FloatAnimator headAnimation;

    private Timer timer;

    public RobotHead(float y) {
        sizeH = Gm.WIDTH * 0.32f;
        if (sizeH > 3.6f) sizeH = 3.6f;

        head = Assets.getNewSprite(90);
        head.setSize(sizeH * 1.04f, sizeH);

        dialogCover = Assets.getNewSprite(89);
        dialogCover.setSize(sizeH * 0.829f * 2.67f, sizeH * 0.829f);

        textAreaBounds = new Rectangle();
        textAreaBounds.setSize(dialogCover.getWidth() * 0.866f, dialogCover.getHeight() * 0.853f);

        leftEave = Assets.getNewSprite(97);
        leftEave.setSize(head.getHeight() * 0.473f, head.getHeight() * 0.473f);

        rightEave = new Sprite();
        rightEave.set(leftEave);

        timer = new Timer(1.3f);

        setPositionY(y);
    }

    public float getTopY(){
        return head.getY() + head.getHeight();
    }

    private float dialogCoverX;
    public void setPositionY(float y){
        dialogCoverX = head.getWidth() * 0.864f;

        head.setY(y);
        refreshHeadAnim();

        dialogCover.setPosition(head.getX() + dialogCoverX, y);

        textAreaBounds.setPosition(dialogCover.getX() + dialogCover.getWidth() * 0.11f, dialogCover.getY() + dialogCover.getHeight() * 0.159f);
        float otst = 0.1f;
        textAreaBounds.setPosition(textAreaBounds.getX() + otst, textAreaBounds.getY() + otst);
        textAreaBounds.setSize(textAreaBounds.getWidth() - otst * 2f, textAreaBounds.getHeight() - otst * 2f);

        leftEave.setPosition(head.getX() + head.getWidth() * 0.049f, head.getY() + head.getHeight() * 0.251f);
        rightEave.setPosition(head.getX() + head.getWidth() * 0.514f, head.getY() + head.getHeight() * 0.215f);
    }

    private void refreshHeadAnim(){
        setPositionHead(head.getY());
        headAnimation = new FloatAnimator();
        headAnimation.setInterpolation(Interpolation.pow5);
        headAnimation.setFrom(-head.getWidth()).setCurrentFrom().setTo(head.getX()).setAnimationTime(1f).setNeedToUpdate(true).setRevers(false);
    }

    private void setPositionHead(float y){
        float sizeWidth = dialogCoverX + dialogCover.getWidth();
        head.setPosition((Gm.WIDTH - sizeWidth) / 2f, y);
    }

    public RobotHead setText(String text, float sizeText){
        this.text = text;
        this.sizeText = sizeText;
        UI.setCff(false, sizeText);
        glyphLayout.setText(UI.getContentFont(), text, Color.valueOf("323232"), textAreaBounds.getWidth(), Align.center, true);
        return this;
    }

    public void resetAnim(){
        isShow = true;
        isTimered = false;
        headAnimation.resetTime();
    }

    private boolean isTimered;
    private Timer showingTimer;
    public void showInTimered(float showTime){
        isTimered = true;
        isShow = true;
        showingTimer = new Timer(showTime);
        refreshHeadAnim();
    }

    private boolean isShow;

    public void draw(SpriteBatch batch){
        if (!isShow)
            return;

        head.setX(headAnimation.current);
        head.draw(batch);

        if (!headAnimation.isNeedToUpdate()){

            if (isTimered){
                if (headAnimation.isRevers()){
                    isShow = false;
                    return;
                }

                if (showingTimer.next())
                    headAnimation.revers();
            }



            dialogCover.draw(batch);
            UI.setCff(false, sizeText);
            UI.getContentFont().draw(batch, glyphLayout, textAreaBounds.getX(), textAreaBounds.getY() + (textAreaBounds.getHeight() + glyphLayout.height) / 2f);

            if (timer.next()) {
                if (isEyesClosed){
                    timer.reset(rnd.nextInt(20) / 10f);
                    isEyesClosed = false;
                }else{
                    timer.reset(0.2f);
                    isEyesClosed = true;
                }
            }

            if (isEyesClosed){
                leftEave.draw(batch);
                rightEave.draw(batch);
            }
        }

        headAnimation.update(Math.min(Gdx.graphics.getDeltaTime(), 0.016f));
    }

    private boolean isEyesClosed;
    private Random rnd = new Random();

}
