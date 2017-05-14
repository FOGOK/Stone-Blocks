package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by FOGOK on 14.05.2017 23:03.
 */

public class Animation<T> extends com.badlogic.gdx.graphics.g2d.Animation<T> {

    private float current;
    private int lastIdx, countFrames;

    public Animation(float frameDuration, T... keyFrames) {
        super(frameDuration, keyFrames);
        reset();
    }

    public T getKeyFrame(boolean pause) {
        int idx = 0;
        if (!pause){
            current += Gdx.graphics.getDeltaTime();
            idx = getKeyFrameIndex(current);
            if (lastIdx != idx){
                lastIdx = idx;
                countFrames++;
            }
        }
        return getKeyFrames()[idx];
    }

    public boolean isLooped(int targetFrames){
        return countFrames >= targetFrames;
    }

    public void reset(){
        countFrames = lastIdx = 0;
        current = 0f;
    }
}
