package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.css.Rect;

/**
 * Created by FOGOK on 16.09.2016 15:41.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class DebugDrawer {

    static final float SIZESQ = 0.2f;

    static ShapeRenderer shapeRenderer;
    public DebugDrawer() {
        shapeRenderer = new ShapeRenderer();
    }

    static Rectangle rect = new Rectangle(-20f, -20f, SIZESQ, SIZESQ);
    static Rectangle rect2;


    public static void drawRect(float x, float y){
        rect.setPosition(x - SIZESQ / 2f, y - SIZESQ / 2f);
    }

    public static void drawRect(SpriteBatch batch, Rectangle rect){
        DebugDrawer.rect2 = new Rectangle(rect);
        drawRect(batch, false);
    }

    public static void drawRect(SpriteBatch batch, boolean isCust){
        if (!isCust)
            batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        Gdx.gl.glLineWidth(0.5f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Rectangle rect = (isCust) ? DebugDrawer.rect : rect2;
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.end();


        if (!isCust)
            batch.begin();
    }

    public static void dispose(){
        shapeRenderer.dispose();
    }
}
