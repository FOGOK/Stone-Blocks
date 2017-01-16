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
    private TextBlock stageT, numberT;
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
        stageT = new TextBlock(0, 0, true, "");
        stageT.setCustomCff(size * 0.3f);

        numberT = new TextBlock(0, 0, true, "");
        numberT.setCustomCff(size * 0.3f);
    }

    public void setNew(int level, Color color){
        flyingAnimatorFrom.resetTime();
        flyingAnimatorTo.resetTime();
        flyingAnimatorToTo.resetTime();

        stageT.setText("STAGE");
        stageT.setTextColor(color);

        numberT.setText(level + "");
        numberT.setTextColor(color);

        setPositionX(Gm.WIDTH / 2f);
    }

    private void setPositionY(float y){
        glassCover.setPosition(glassCover.getX(), y - glassCover.getHeight() / 2f);
        refreshPosText();
    }

    private void setPositionX(float x){
        glassCover.setPosition(x - glassCover.getWidth() / 2f, glassCover.getY());
        refreshPosText();
    }

    private void refreshPosText(){
        stageT.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f + stageT.getBounds().getHeight() / 2f);
        stageT.setPositionToCenter();

        numberT.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f - stageT.getBounds().getHeight() * 0.8f);
        numberT.setPositionToCenter();
    }

    public void refreshRefresh(){
        flyingAnimatorTo.setNeedToUpdate(false);
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
        stageT.draw(batch);
        numberT.draw(batch);
    }

    public void dispose() {

    }
}
