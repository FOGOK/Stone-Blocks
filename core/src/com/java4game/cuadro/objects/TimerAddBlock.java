package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 25.04.2017 12:18.
 */

public class TimerAddBlock extends FieldObject {

    private boolean isShowing;
    public TimerAddBlock(Rectangle fieldBounds, int x, int y) {
        super(Assets.getNewSprite(100), fieldBounds, TIMER_ADD);
        setSQPos(x, y);
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isShowing)
            super.draw(batch);
    }
}
