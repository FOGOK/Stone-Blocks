package com.java4game.cuadro.core.guiwidgets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by FOGOK on 16.06.2017 15:51.
 */

public class BaseWidget {

    //тут у нас работа ТОЛЬКО с координатами

    public enum Align{
        Center, LeftBottom
    }

    private float startX, startY;     //это координаты, которые задаются изначально, в bounds координаты уже относительно переменной align
    Align align = Align.LeftBottom;
    Rectangle bounds;

    public BaseWidget(Rectangle bounds, Align align) {
        this.bounds = new Rectangle();
        setPosition(align, bounds.getX(), bounds.getY());
        setSize(bounds.getWidth(), bounds.getHeight());
    }


    public void setAlign(Align align) {
        this.align = align;
    }

    public void setPosition(Align align, float x, float y){
        setAlign(align);
        this.startX = x;
        this.startY = y;
        refreshBounds();
    }

    public void setSize(float width, float height) {
        bounds.setSize(width, height);
        refreshBounds();
    }

    void refreshBounds(){     //метод, который вызывается при установке bounds
        switch (align) {
            case Center:
                bounds.setPosition(startX - bounds.getWidth() / 2f, startY - bounds.getHeight() / 2f);
                break;
            case LeftBottom:
                bounds.setPosition(startX, startY);
                break;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
