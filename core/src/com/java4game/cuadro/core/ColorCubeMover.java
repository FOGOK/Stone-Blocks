package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.ColoredCube;
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
    int countColors = 0;    ///количество прилепленных цветных

    Vector2[] outerPoints = new Vector2[4]; //типо 4 вершины у ограничивающей области

    //ссылки
    SquareObject[] allObjects;
    Rectangle sqBounds;
    ///


    public ColorCubeMover(SquareObject[] allObjects, Rectangle sqBounds) {
        this.allObjects = allObjects;
        this.sqBounds = sqBounds;
        movedBounds = new Rectangle(0f, 0f, LevelGen.sizeObjects, LevelGen.sizeObjects);
        for (int i = 0; i < outerPoints.length; i++) {
            outerPoints[i] = new Vector2();
        }
    }

    private void settBounds(){
        if (Cube.getDir() == Cube.Dir.RIGHT)
            movedBounds.setWidth(sizCubL * (countColors + 1));
        else if (Cube.getDir() == Cube.Dir.LEFT){
            movedBounds.setWidth(sizCubL * (countColors + 1));
            movedBounds.setX(movedBounds.getX() - sizCubL * (countColors + 1));
        }
        else if (Cube.getDir() == Cube.Dir.UP)
            movedBounds.setHeight(sizCubL * (countColors + 1));
        else if (Cube.getDir() == Cube.Dir.DOWN){
            movedBounds.setHeight(sizCubL * (countColors + 1));
            movedBounds.setY(movedBounds.getY() - sizCubL * (countColors + 1));
        }
    }

    float ottt, ottt2, sizCubL;
    public void match(SpriteBatch batch){
        movedBounds.setPosition(Cube.getSX(),
                Cube.getSY());
        ottt = (LevelSquare.sizOneSq + LevelSquare.otst * 2f);
        ottt2 = (LevelSquare.sizOneSq + LevelSquare.otst * 2f);
        sizCubL = (LevelSquare.otst * 2f + LevelSquare.sizOneSq + LevelGen.otstObjects);

        movedBounds.setSize(LevelGen.sizeObjects);
        if (countColors > 0)
           settBounds();


        //              2   3
        ////            /////
        //              0   1


        for (int i = 0; i < ObjectsGen.iters; i++) {
            if (allObjects[i] instanceof ColoredCube){
                if (allObjects[i].getBounds().overlaps(movedBounds) && !allObjects[i].isWired()){   //проверяем
                    //                                                                    // есть ли цветные рядом с кубиком

                    countColors++;
                    allObjects[i].setKindedHash(countColors);
                    allObjects[i].setWired(true);
                    movedBounds.setPosition(Cube.getSX(),
                            Cube.getSY());
                    movedBounds.setSize(LevelGen.sizeObjects);
                    settBounds();
                }
//                else if (allObjects[i].getKindedHash() == countColors && countColors != 0){       //устанавливаем цветной рядом с кубиком
//
//                }

                if (allObjects[i] instanceof ColoredCube){
                    if (allObjects[i].isWired()){
                        if (Cube.getDir() == Cube.Dir.RIGHT){
                            allObjects[i].setPosition(movedBounds.getX() + ottt * allObjects[i].getKindedHash() + LevelGen.otstObjects * 2f, allObjects[i].getY());
                        } else if (Cube.getDir() == Cube.Dir.LEFT){
                            allObjects[i].setPosition(movedBounds.getX() + movedBounds.getWidth() - LevelGen.sizeObjects - ottt * allObjects[i].getKindedHash() - LevelGen.otstObjects * 2f, allObjects[i].getY());
                        } else if (Cube.getDir() == Cube.Dir.UP){
                            allObjects[i].setPosition(allObjects[i].getX(), movedBounds.getY() + ottt * allObjects[i].getKindedHash() + LevelGen.otstObjects * 2f);
                        } else if (Cube.getDir() == Cube.Dir.DOWN){
                            allObjects[i].setPosition(allObjects[i].getX(), movedBounds.getY() + movedBounds.getHeight() - LevelGen.sizeObjects - ottt * allObjects[i].getKindedHash() - LevelGen.otstObjects * 2f);
                        }
                    }
                }

//                if ()
            }
        }

        outerPoints[0].x = movedBounds.getX();  outerPoints[0].y = movedBounds.getY();
        outerPoints[1].x = movedBounds.getX() + movedBounds.getWidth();  outerPoints[1].y = movedBounds.getY();
        outerPoints[2].x = movedBounds.getX();  outerPoints[2].y = movedBounds.getY() + movedBounds.getHeight();
        outerPoints[3].x = movedBounds.getX() + movedBounds.getWidth();  outerPoints[3].y = movedBounds.getY() + + movedBounds.getHeight();


        if (countColors > 0){
            boolean inv = false;
            for (int i = 0; i < outerPoints.length; i++) {
                if (!sqBounds.contains(outerPoints[i])){
                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q].isWired()){
//                            if (allObjects[q].isNormalPos()){
                                allObjects[q].normalizePos();
                                allObjects[q].setWired(false);
                                allObjects[q].setKindedHash(0);
                                countColors = 0;
//                            }
//                            if (!inv)
                                inv = true;
                        }
                    }
                }
            }
            if (inv)
                Cube.inverseDir();
        }

        DebugDrawer.drawRect(batch, movedBounds);
        Gm.DEBUG_VALUE1 = "" + countColors;
    }

    public void dispose() {

    }
}
