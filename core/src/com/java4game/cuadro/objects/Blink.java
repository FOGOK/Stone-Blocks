package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Timer;

import java.util.Random;

/**
 * Created by FOGOK on 08.01.2017 3:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Blink {

    private Sprite blink;
    private Timer timer;
    private FloatAnimator flyingAnimatorFrom, flyingAnimatorTo;

    private Random rnd = new Random();

    public Blink(float x, float y) {
        float size = 3f;

        blink = Assets.getNewSprite(6);
        blink.setSize(size, size * 0.7f);
        blink.setOriginCenter();

        timer = new Timer((rnd.nextInt(350) / 100f) + 1f);

        flyingAnimatorFrom = new FloatAnimator(0f, 1f, 0.2f, Interpolation.linear);
        flyingAnimatorTo = new FloatAnimator(1f, 0f, 0.2f, Interpolation.linear);
        setPosition(x, y);
    }

    public void setPosition(float x, float y){
        blink.setCenter(x, y);
    }

    public void draw(SpriteBatch batch){
        if (timer.next()){
            if (flyingAnimatorTo.isNeedToUpdate()){
                if (flyingAnimatorFrom.isNeedToUpdate()){
                    flyingAnimatorFrom.update(Gdx.graphics.getDeltaTime());
                    blink.setScale(flyingAnimatorFrom.current);
                }else{
                    flyingAnimatorTo.update(Gdx.graphics.getDeltaTime());
                    blink.setScale(flyingAnimatorTo.current);
                }

                blink.rotate(10f);
                blink.draw(batch);
            }else{
                reset();
            }
        }
    }

    private void reset(){
        flyingAnimatorFrom.resetTime();
        flyingAnimatorTo.resetTime();
        timer.reset((rnd.nextInt(350) / 100f) + 1f);
    }
}
