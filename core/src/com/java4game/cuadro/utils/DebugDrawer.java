package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by FOGOK on 16.09.2016 15:41.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class DebugDrawer {

    static ShapeRenderer shapeRenderer;
    public DebugDrawer() {
        shapeRenderer = new ShapeRenderer();
    }

    public static void drawRect(SpriteBatch batch, Rectangle rectangle){
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        Gdx.gl.glLineWidth(0.5f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();


        batch.begin();
    }

    public static void dispose(){
        shapeRenderer.dispose();
    }
}
