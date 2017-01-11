package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;

/**
 * Created by FOGOK on 08.01.2017 2:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class FlyingStage {

    private Sprite glassCover;
    private TextBlock textBlock;
    private FloatAnimator flyingAnimatorFrom, flyingAnimatorTo, flyingAnimatorToTo;

    private float size;
    public FlyingStage() {
        size = 2.3f;

        flyingAnimatorFrom = new FloatAnimator(Gm.HEIGHT + size, Gm.HEIGHT / 2f, 1f, Interpolation.exp10Out);
        flyingAnimatorTo = new FloatAnimator(Gm.HEIGHT / 2f, -size, 1f, Interpolation.exp10In);
        flyingAnimatorTo.setTimer(0.5f);

        flyingAnimatorToTo = new FloatAnimator(Gm.WIDTH + size, Gm.WIDTH - size * 0.57f, 0.8f, Interpolation.exp10Out);

        glassCover = Assets.getNewSprite(30);
        glassCover.setSize(size * 1.524f, size);
        textBlock = new TextBlock(0, 0, true, "");
        textBlock.setCustomCff(size * 0.25f);
    }

    public void setNew(int level, Color color){
        flyingAnimatorFrom.resetTime();
        flyingAnimatorTo.resetTime();
        flyingAnimatorToTo.resetTime();

        textBlock.setText("STAGE " + level);
        textBlock.setTextColor(color);
        setPositionX(Gm.WIDTH / 2f);
    }

    private void setPositionY(float y){
        glassCover.setPosition(glassCover.getX(), y - glassCover.getHeight() / 2f);
        textBlock.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f);
        textBlock.setPositionToCenter();
    }

    private void setPositionX(float x){
        glassCover.setPosition(x - glassCover.getWidth() / 2f, glassCover.getY());
        textBlock.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f);
        textBlock.setPositionToCenter();
    }

    public void handle(){
        if (flyingAnimatorTo.isNeedToUpdate()){
            if (flyingAnimatorFrom.isNeedToUpdate()){
                flyingAnimatorFrom.update(Gdx.graphics.getDeltaTime());
                setPositionY(flyingAnimatorFrom.current);
            }else{
                flyingAnimatorTo.update(Gdx.graphics.getDeltaTime());
                setPositionY(flyingAnimatorTo.current);
            }
        }else{
            flyingAnimatorToTo.update(Gdx.graphics.getDeltaTime());
            setPositionY(Gm.HEIGHT - size * 0.4f);
            setPositionX(flyingAnimatorToTo.current);
        }
    }


    public boolean isFlying(){
        return flyingAnimatorTo.isNeedToUpdate();
    }

    public float getProgressEnd(){
        return flyingAnimatorToTo.getProgress();
    }

    public void drawGlass(SpriteBatch batch){
        glassCover.draw(batch);
    }

    public void drawText(SpriteBatch batch){
        textBlock.draw(batch);
    }

    public void dispose() {

    }
}
