package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 25.04.2017 16:41.
 */

public class MoverBonus extends FieldObject {

    private boolean isShowing;
    public MoverBonus(Rectangle fieldBounds, int x, int y) {
        super(Assets.getNewSprite(101), fieldBounds, MOVER_BONUS);
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
