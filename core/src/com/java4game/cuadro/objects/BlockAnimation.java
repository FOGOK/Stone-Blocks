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
    private Sprite blackBlock;
    private FloatAnimator blockAnimation;
    private int blockSelect;
    public BlockAnimation() {
        final float blockSize = 1.4f;

        blocks = new Sprite[5];
        blockSelect = new Random().nextInt(blocks.length);
        blockAnimation = new FloatAnimator(-blockSize * 1.5f, Gm.WIDTH + blockSize * 1.5f, 0.8f, Interpolation.linear);
        blockAnimation.setTimer(0.6f);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = Assets.getNewSprite(7 + i);
            blocks[i].setSize(blockSize, blockSize);
        }

        blackBlock = Assets.getNewSprite(12);
        blackBlock.setSize(blockSize, blockSize);
    }

    public void setY(float y){
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setY(y);
        }
        blackBlock.setY(y);
    }

    public void draw(SpriteBatch batch){
        blocks[blockSelect].setCenterX(blockAnimation.current);
        blocks[blockSelect].draw(batch);
        blackBlock.setCenterX(blockAnimation.isRevers() ? blockAnimation.current + blackBlock.getWidth() * 0.9f : blockAnimation.current - blackBlock.getWidth() * 0.9f);
        blackBlock.draw(batch);

        if (blockAnimation.updateLoop(Math.min(Gdx.graphics.getDeltaTime(), 0.016f)))
            blockSelect = new Random().nextInt(blocks.length);
    }

}
