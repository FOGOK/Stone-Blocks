package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.objects.Cube;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.objects.SquareObject;
import com.java4game.cuadro.utils.DebugDrawer;


/**
 * Created by FOGOK on 19.09.2016 21:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ColorCubeMover {


    Rectangle movedBounds;
    int countColors = 1;    ///количество прилепленных цветных

    //ссылки
    SquareObject[] allObjects;
    ///


    public ColorCubeMover(SquareObject[] allObjects) {
        this.allObjects = allObjects;
        movedBounds = new Rectangle(0f, 0f, LevelGen.sizeObjects, LevelGen.sizeObjects);
    }

    float ottt;
    public void match(SpriteBatch batch){
        movedBounds.setPosition(Cube.getSX() - (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                Cube.getSY() - (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
        ottt = (LevelSquare.sizOneSq);

        movedBounds.setSize(LevelSquare.sizOneSq);
        if (countColors > 0){


            if (Cube.getDir() == Cube.Dir.RIGHT)
                movedBounds.setWidth(movedBounds.getWidth() * (countColors + 1));
            else if (Cube.getDir() == Cube.Dir.LEFT){
                movedBounds.setWidth(movedBounds.getWidth() * (countColors + 1));
                movedBounds.setX(movedBounds.getX() - ottt * countColors);
            }
            else if (Cube.getDir() == Cube.Dir.UP)
                movedBounds.setHeight(movedBounds.getHeight() * (countColors + 1));
            else if (Cube.getDir() == Cube.Dir.DOWN){
                movedBounds.setHeight(movedBounds.getHeight() * (countColors + 1));
                movedBounds.setY(movedBounds.getY() - ottt * countColors);
            }




        }


        DebugDrawer.drawRect(batch, movedBounds);
    }

    public void dispose() {

    }
}
