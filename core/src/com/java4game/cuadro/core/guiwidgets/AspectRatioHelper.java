package com.java4game.cuadro.core.guiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by FOGOK on 16.06.2017 16:05.
 */

public class AspectRatioHelper {

    private static float width, height;

                                                                            //value - width/height, true - width, false - height
    public static void setSpriteSize(Sprite sprite, float targetValue, boolean widthHeight){
        if (widthHeight) {
            width = targetValue;
            height = sprite.getHeight() / sprite.getWidth() * width;
        } else {
            height = targetValue;
            width = sprite.getWidth() / sprite.getHeight() * height;
        }
        sprite.setSize(width, height);

    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }
}
