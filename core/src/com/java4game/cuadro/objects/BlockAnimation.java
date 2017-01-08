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
 * Created by FOGOK on 08.01.2017 3:52.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class BlockAnimation {

    private Sprite blocks[];
    private FloatAnimator blockAnimation;
    private int blockSelect;
    public BlockAnimation() {
        final float blockSize = 1.4f;

        blocks = new Sprite[6];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = Assets.getNewSprite(7 + i);
            blocks[i].setSize(blockSize, blockSize);
        }
        blockSelect = new Random().nextInt(blocks.length);
        blockAnimation = new FloatAnimator(-blockSize * 1.3f, Gm.WIDTH + blockSize * 1.3f, 1.3f, Interpolation.linear);
        blockAnimation.setTimer(0.8f);
    }

    public void setY(float y){
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setY(y);
        }
    }

    public void draw(SpriteBatch batch){
        if (blockAnimation.updateLoop(Gdx.graphics.getDeltaTime()))
            blockSelect = new Random().nextInt(blocks.length);
        blocks[blockSelect].setCenterX(blockAnimation.current);
        blocks[blockSelect].draw(batch);
    }

}
