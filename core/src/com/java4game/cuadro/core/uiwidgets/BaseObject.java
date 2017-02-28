package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by FOGOK on 03.11.2016 13:40.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public abstract class BaseObject implements BaseDrawingObject{

    Rectangle bounds;
    public BaseObject(final float x, final float y, final float w, final float h){
        setBounds(x, y, w, h);
    }

    @Override
    public void setPositionToCenter() {
        setPosition(bounds.x - bounds.width / 2f, bounds.y - bounds.height / 2f);
    }

    @Override
    public void setPositionToCenter(float realWidth, float realHeight) {
        setPosition(bounds.x - realWidth / 2f, bounds.y - realHeight / 2f);
    }

    @Override
    public void setBounds(float x, float y, float w, float h) {
        bounds = new Rectangle(x, y, w, h);
    }

    @Override
    public void setPosition(float x, float y) {
        bounds.setPosition(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
