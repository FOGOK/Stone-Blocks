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

    private Interpolation interp = null;

    private boolean needToUpdate = true;

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

    public FloatAnimator setTo( final float to ) {
        this.to = to;
        return this;
    }

    public FloatAnimator setAnimationTime( final float time ) {
        animationTime = time;
        return this;
    }

    public FloatAnimator setInterpolation( final Interpolation interp ) {
        this.interp = interp;
        return this;
    }

    public FloatAnimator resetTime() {
        time = 0.0f;
        needToUpdate = true;
        return this;
    }

    public void update( final float delta ) {

        if ( !isNeedToUpdate() ) { return; }

        time += delta;

        current = interp.apply( from, to, time / animationTime );

        // Если "отведённое время" минус "прошедшее время" всё ещё больше нуля, то апдейтим
        if ( animationTime - time > 0 ) { needToUpdate = true; }
        else { needToUpdate = false; }
    }

    /**
     * Постоянное повторение петли из FROM в TO, и опять из FROM в TO
     * */
    public void updateLoop( final float delta ) {

        if ( !isNeedToUpdate() ) {
            current = from;
            resetTime();
        }

        time += delta;
        current = interp.apply( from, to, time / animationTime );

        if ( animationTime - time > 0 ) { needToUpdate = true; }
        else { needToUpdate = false; }
    }

    public boolean isNeedToUpdate() {
        return needToUpdate;
    }

    public FloatAnimator fromCurrent() {
        from = current;
        return this;
    }
}
