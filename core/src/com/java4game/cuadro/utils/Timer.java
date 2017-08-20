package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by FOGOK on 07.01.2017 15:22.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Timer {

    private float seconds, targetSeconds;
    private boolean isStarted;
    public Timer(float targetSeconds) {
        this.targetSeconds = targetSeconds;
        seconds = 0f;
        isStarted = false;
    }

    public void set(Timer timer){
        isStarted = timer.isStarted;
        seconds = timer.seconds;
        targetSeconds = timer.targetSeconds;
    }

    public void setCurrentSeconds(float seconds) {
        this.seconds = seconds;
    }

    public void appendTargetSeconds(float targetSeconds) {
        this.targetSeconds += targetSeconds;
    }

    public boolean next(){
        isStarted = true;
        seconds += Math.min(Gdx.graphics.getDeltaTime(), 0.032f);
        return seconds > targetSeconds;
    }

    public float getProgress(){
        return GMUtils.normalizeOneZero(seconds / targetSeconds);
    }

    public void reset(float newTargetSeconds){
        targetSeconds = newTargetSeconds;
        reset();
    }

    public void reset(){
        seconds = 0f;
        isStarted = false;
    }

    public boolean isEnd(){
        return seconds > targetSeconds;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
