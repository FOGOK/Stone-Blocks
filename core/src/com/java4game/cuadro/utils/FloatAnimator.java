package com.java4game.cuadro.utils;

/**
 * OVERMY.NET - Make your device live! *
 * <p/>
 * Games: http://play.google.com/store/apps/developer?id=OVERMY
 *
 * @author Andrey Mikheev (cb)
 */


import com.badlogic.gdx.math.Interpolation;


public class FloatAnimator {

    public float current = 0.0f;
    private float from = 0.0f;
    private float to   = 0.0f;

    private float time          = 0.0f;
    private float animationTime = 1.0f;

    private boolean isRevers;

    private Interpolation interp = null;

    private boolean needToUpdate = true;

    private float timer = 0f, endTime = 0f;

    public FloatAnimator() {
        this( 0.0f, 1.0f, 0.35f, Interpolation.fade );
    }

    public FloatAnimator( final float from, final float to, final float time ) {
        this( from, to, time, Interpolation.fade );
    }

    public FloatAnimator( final float from, final float to, final float time, final Interpolation interp ) {
        setFrom( from );
        setTo( to );
        setAnimationTime( time );
        setInterpolation( interp );
        resetTime();
    }

    public FloatAnimator setFrom( final float from ) {
        current = from;
        this.from = from;
        return this;
    }

    public void setTimer(float endTime) {
        this.endTime = endTime;
    }

    public FloatAnimator setTo(final float to ) {
        this.to = to;
        return this;
    }

    public FloatAnimator setAnimationTime( final float time ) {
        animationTime = time;
        return this;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public FloatAnimator setInterpolation(final Interpolation interp ) {
        this.interp = interp;
        return this;
    }

    public FloatAnimator resetTime() {
        return resetTime(true);
    }

    private FloatAnimator resetTime(boolean resetRevers){
        time = 0.0f;
        timer = 0f;
        current = from;
        needToUpdate = true;
        if (resetRevers)
            isRevers = false;
        return this;
    }

    public void update( final float delta ) {

        timer += delta;
        if (!isNeedToUpdate() || timer < endTime)
            return;


        time += delta;

        current = interp.apply( from, to, time / animationTime );

        // Если "отведённое время" минус "прошедшее время" всё ещё больше нуля, то апдейтим
        if (animationTime - time > 0) {
            needToUpdate = true;

        } else {
            needToUpdate = false;
            current = to;
        }
    }

    /**
     * Постоянное повторение петли из FROM в TO, и из TO в FROM
     * */
    public boolean updateLoop( final float delta ) {

        timer += delta;
        if (timer < endTime)
            return false;

        time += delta;
        current = interp.apply( from, to, time / animationTime );

        if ( animationTime - time > 0 ) { needToUpdate = true; }
        else { needToUpdate = false; }


        if ( !isNeedToUpdate() ) {
            float ffrom = from;
            from = to;
            to = ffrom;
            current = from;
            resetTime(false);
            isRevers = !isRevers;
            return true;
        }

        return false;
    }

    public float getProgress(){
        return needToUpdate ? GMUtils.normalizeOneZero(time / animationTime) : 1f;
    }

    public boolean isRevers() {
        return isRevers;
    }

    public boolean isNeedToUpdate() {
        return needToUpdate;
    }

    public boolean isAnimationStarted(){
        return timer >= endTime;
    }

    public FloatAnimator fromCurrent() {
        from = current;
        return this;
    }
}
