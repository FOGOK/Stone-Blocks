package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.usie.UI;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Timer;


public class RobotHead {

    private Sprite dialogCover;
    private float sizeH;
    private String text;
    private float sizeText;
    private Rectangle textAreaBounds;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private FloatAnimator headAnimation;

    private static PolygonSpriteBatch polygonSpriteBatch;
    private static SkeletonMeshRenderer skeletonMeshRenderer;
    private Skeleton headSkeleton;
    private AnimationState animationState;


    private int appendedNumber;

    public RobotHead(float y) {
        sizeH = Gm.WIDTH * 0.32f;
        if (sizeH > 3.6f) sizeH = 3.6f;

        dialogCover = Assets.getNewSprite(89);
        dialogCover.setSize(sizeH * 0.829f * 2.67f, sizeH * 0.829f);

        textAreaBounds = new Rectangle();
        textAreaBounds.setSize(dialogCover.getWidth() * 0.866f, dialogCover.getHeight() * 0.853f);

        initSpineAnim();
        setPositionY(y);
    }

    private void initSpineAnim(){
        if (polygonSpriteBatch == null) {
            skeletonMeshRenderer = new SkeletonMeshRenderer();
            polygonSpriteBatch = new PolygonSpriteBatch();
        }

        SkeletonJson json = new SkeletonJson(Assets.getTextureAtlas());
        float scale = 0.012f;
        json.setScale(scale);
        SkeletonData baseData = json.readSkeletonData(Gdx.files.internal("robotHead.json"));

        headSkeleton = new Skeleton(baseData);
        headSkeleton.getData().setWidth(Assets.getNewSprite(90).getWidth() * scale);
        headSkeleton.getData().setHeight(Assets.getNewSprite(90).getHeight() * scale);

        AnimationStateData animationStateData = new AnimationStateData(baseData);
        animationState = new AnimationState(animationStateData);
        animationState.setTimeScale(0.4f);

        animationState.setAnimation(0, "animtion0", true);
    }

    private void renderSpine(SpriteBatch batch){
        animationState.update(Gdx.graphics.getDeltaTime());

        animationState.apply(headSkeleton);
        headSkeleton.updateWorldTransform();

        batch.end();
        polygonSpriteBatch.setProjectionMatrix(Gm.getCamera().combined);
        polygonSpriteBatch.begin();
        skeletonMeshRenderer.draw(polygonSpriteBatch, headSkeleton);
        polygonSpriteBatch.end();
        batch.begin();

    }

    public float getTopY(){
        return headSkeleton.getY() + headSkeleton.getData().getHeight();
    }

    private float dialogCoverX;
    public void setPositionY(float y){
        dialogCoverX = headSkeleton.getData().getWidth() * 0.864f;

        headSkeleton.setY(y);
        refreshHeadAnim();

        dialogCover.setPosition(headSkeleton.getX() + dialogCoverX - headSkeleton.getData().getWidth() / 2f, y);

        textAreaBounds.setPosition(dialogCover.getX() + dialogCover.getWidth() * 0.11f, dialogCover.getY() + dialogCover.getHeight() * 0.159f);
        float otst = 0.1f;
        textAreaBounds.setPosition(textAreaBounds.getX() + otst, textAreaBounds.getY() + otst);
        textAreaBounds.setSize(textAreaBounds.getWidth() - otst * 2f, textAreaBounds.getHeight() - otst * 2f);

//        leftEave.setPosition(head.getWidth() + head.getWidth() * 0.049f, head.getHeight() + head.getHeight() * 0.251f);
//        rightEave.setPosition(head.getWidth() + head.getWidth() * 0.514f, head.getHeight() + head.getHeight() * 0.215f);
    }

    private void refreshHeadAnim(){
        setPositionHead(headSkeleton.getY() + headSkeleton.getData().getHeight() / 2f);
        headAnimation = new FloatAnimator();
        headAnimation.setInterpolation(Interpolation.pow5)
                .setFrom(-headSkeleton.getData().getWidth())
                .setTo(headSkeleton.getX())
                .setAnimationTime(0.3f);
    }

    private void setPositionHead(float y){
        float sizeWidth = dialogCoverX + dialogCover.getWidth();
        headSkeleton.setPosition((Gm.WIDTH - sizeWidth) / 2f + headSkeleton.getData().getWidth() / 2f, y);
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

        headSkeleton.setPosition(headAnimation.current, dialogCover.getY() + headSkeleton.getData().getHeight() / 2f);
        renderSpine(batch);

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

        }

        headAnimation.update(Math.min(Gdx.graphics.getDeltaTime(), 0.016f));


    }

    public static void dispose(){
        polygonSpriteBatch.dispose();
        polygonSpriteBatch = null;
    }
}
