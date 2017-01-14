package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;

import java.util.Random;

/**
 * Created by FOGOK on 12.01.2017 22:22.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class FlyingGlass {

    private Sprite glass;
    private FloatAnimator floatAnimator;
    private Random rnd = new Random();
    private float speedRot;
    private float timer;
    private float size;
    private int number;
    private int all;

    public FlyingGlass(int number, int all, float offset) {
        this.number = number;
        this.all = all;
        timer = 16f + (rnd.nextInt(80) / 10f);

        size = 0.5f + (rnd.nextInt(50) / 100f);
        glass = Assets.getNewSprite(40);
        glass.setSize(size, size);
        glass.setAlpha(0.3f);
        glass.setRotation(rnd.nextInt(360));
        glass.setOriginCenter();
        float cff = (Gm.WIDTH / Gdx.graphics.getWidth());
        glass.setX(cff * rnd.nextInt(Gdx.graphics.getWidth() - (int)(size * (1f / cff))));
        glass.setY(cff * rnd.nextInt(Gdx.graphics.getHeight() - (int)(size * (1f / cff))) + offset);
        refreshAnimator(false);
    }

    public void draw(SpriteBatch batch) {
        if (!floatAnimator.isNeedToUpdate())
            refreshAnimator(true);
        else{
            if (floatAnimator.isAnimationStarted()){
                glass.setY(floatAnimator.current);
                glass.rotate(speedRot);
                glass.draw(batch);
            }
            floatAnimator.update(Math.min(Gdx.graphics.getDeltaTime(), 0.016f));
        }

    }

    private void refreshAnimator(boolean b){
        float animTimeOld = 0f;
        if (b){
            glass.setY(Gm.HEIGHT);
            animTimeOld = floatAnimator.getAnimationTime();
        }

        float max = Gm.HEIGHT + size * 1.3f;


        floatAnimator = new FloatAnimator(glass.getY(), -size * 1.3f, (glass.getY() / max) * timer, Interpolation.linear);
//        floatAnimator = new FloatAnimator(Gm.HEIGHT, -size * 1.3f, timer, Interpolation.pow4In);
        if (b)
            floatAnimator.setTimer(((float) number / all) * animTimeOld);
        speedRot = rnd.nextBoolean() ? -0.3f : 0.3f;
    }
}
