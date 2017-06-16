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
import com.java4game.cuadro.utils.Animation;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.PosF;
import com.java4game.cuadro.utils.Timer;

import java.util.Random;


public class RobotHead {

    private Sprite head, dialogCover;
    private Animation<Sprite> eyesAnim;
    private float sizeH;
    private String text;
    private float sizeText;
    private Rectangle textAreaBounds;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private FloatAnimator headAnimation;

    private Timer timer;
    private int appendedNumber;

    public RobotHead(float y) {
        sizeH = Gm.WIDTH * 0.32f;
        if (sizeH > 3.6f) sizeH = 3.6f;

        head = Assets.getNewSprite(90);
        head.setSize(sizeH * 1.04f, sizeH);

        dialogCover = Assets.getNewSprite(89);
        dialogCover.setSize(sizeH * 0.829f * 2.67f, sizeH * 0.829f);

        textAreaBounds = new Rectangle();
        textAreaBounds.setSize(dialogCover.getWidth() * 0.866f, dialogCover.getHeight() * 0.853f);

//        leftEave = Assets.getNewSprite(97);
//        leftEave.setSize(head.getHeight() * 0.473f, head.getHeight() * 0.473f);
//
//        rightEave = new Sprite();
//        rightEave.set(leftEave);

        timer = new Timer(1.3f);

        eyesAnim = new Animation<Sprite>(0.03f,
                Assets.getNewSprite(112),
                Assets.getNewSprite(113),
                Assets.getNewSprite(114));

        eyesAnim.setPlayMode(com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
        for (Sprite sprite : eyesAnim.getKeyFrames()){
            sprite.setSize(head.getWidth() * 0.944f, head.getHeight() * 0.509f);
            sprite.setOriginCenter();
            sprite.setRotation(-5f);
        }



        setPositionY(y);
    }

    public float getTopY(){
        return head.getY() + head.getHeight();
    }

    private float dialogCoverX;
    private PosF positionAnimEyes;
    public void setPositionY(float y){
        dialogCoverX = head.getWidth() * 0.864f;

        head.setY(y);
        refreshHeadAnim();

        dialogCover.setPosition(head.getX() + dialogCoverX, y);

        textAreaBounds.setPosition(dialogCover.getX() + dialogCover.getWidth() * 0.11f, dialogCover.getY() + dialogCover.getHeight() * 0.159f);
        float otst = 0.1f;
        textAreaBounds.setPosition(textAreaBounds.getX() + otst, textAreaBounds.getY() + otst);
        textAreaBounds.setSize(textAreaBounds.getWidth() - otst * 2f, textAreaBounds.getHeight() - otst * 2f);

//        leftEave.setPosition(head.getWidth() + head.getWidth() * 0.049f, head.getHeight() + head.getHeight() * 0.251f);
//        rightEave.setPosition(head.getWidth() + head.getWidth() * 0.514f, head.getHeight() + head.getHeight() * 0.215f);

        positionAnimEyes = new PosF(head.getX() + head.getWidth() * 0.035f, head.getY() + head.getHeight() * 0.215f);
        for (Sprite sprite : eyesAnim.getKeyFrames())
            sprite.setPosition(positionAnimEyes.x, positionAnimEyes.y);

    }

    private void refreshHeadAnim(){
        setPositionHead(head.getY());
        headAnimation = new FloatAnimator();
        headAnimation.setInterpolation(Interpolation.pow5)
                .setFrom(-head.getWidth())
                .setTo(head.getX())
                .setAnimationTime(0.3f);
    }

    private void setPositionHead(float y){
        float sizeWidth = dialogCoverX + dialogCover.getWidth();
        head.setPosition((Gm.WIDTH - sizeWidth) / 2f, y);
    }

    public RobotHead setText(String text, float sizeText){
        return setText(text, -1, false, sizeText);
    }

    public RobotHead setText(String text, int number, boolean isAppend, float sizeText){
        this.text = text;
        this.sizeText = sizeText;
        if (isAppend)
            appendedNumber += number;
        String textFinal = text;
        if (number != -1)
            textFinal += " " + appendedNumber;
        UI.setCff(false, sizeText);
        glyphLayout.setText(UI.getContentFont(), textFinal, Color.valueOf("323232"), textAreaBounds.getWidth(), Align.center, true);
        return this;
    }

    public void show(){
        isShow = true;
        isTimered = false;
        headAnimation.resetTime();
    }

    private boolean isTimered;
    private Timer showingTimer;
    public RobotHead showInTimered(float showTime){
        if (!isShow || headAnimation.isRevers() || showingTimer == null){
            appendedNumber = 0;
            isTimered = true;
            isShow = true;
            showingTimer = new Timer(0);
            refreshHeadAnim();
        }
        showingTimer.setCurrentSeconds(0f);
        showingTimer.appendTargetSeconds(showTime);
        return this;
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
                    timer.reset(2f + rnd.nextInt(40) / 10f);
                    isEyesClosed = false;
                }else{
                    eyesAnim.getKeyFrame(false).draw(batch);
                    if (eyesAnim.isLooped(4)){
                        isEyesClosed = true;
                        eyesAnim.reset();
                    }
                }
            }

        }

        headAnimation.update(Math.min(Gdx.graphics.getDeltaTime(), 0.016f));
    }

    private boolean isEyesClosed;
    private Random rnd = new Random();

}
