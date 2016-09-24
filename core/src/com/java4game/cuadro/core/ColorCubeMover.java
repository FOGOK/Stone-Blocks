package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
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

    Logger logger;


    public ColorCubeMover(SquareObject[] allObjects, Rectangle sqBounds) {
        this.allObjects = allObjects;
        this.sqBounds = sqBounds;
        movedBounds = new Rectangle(0f, 0f, LevelGen.sizeObjects, LevelGen.sizeObjects);
        for (int i = 0; i < outerPoints.length; i++) {
            outerPoints[i] = new Vector2();
        }

        logger = new Logger("MMR");
    }

    private void settBounds(){

        movedBounds.setPosition(Cube.getSX(), Cube.getSY());
        movedBounds.setSize(LevelGen.sizeObjects);
        float cffP = 0.0f;

        if (Cube.getDir() == Cube.Dir.RIGHT){
            movedBounds.setWidth(sizCubL * (countColors));
            movedBounds.setX(movedBounds.getX() + movedBounds.getHeight() + cffP);
        }
        else if (Cube.getDir() == Cube.Dir.LEFT){
            movedBounds.setWidth(sizCubL * (countColors));
            movedBounds.setX(movedBounds.getX() - sizCubL * (countColors) - cffP);
        }
        else if (Cube.getDir() == Cube.Dir.UP){
            movedBounds.setHeight(sizCubL * (countColors));
            movedBounds.setY(movedBounds.getY() + movedBounds.getWidth() + cffP);
        }
        else if (Cube.getDir() == Cube.Dir.DOWN){
            movedBounds.setHeight(sizCubL * (countColors));
            movedBounds.setY(movedBounds.getY() - sizCubL * (countColors) - cffP);
        }
    }

//    private void isEnableKray(int i){   ///если i равно -1, значит проверяем относительно кубика, иначе цветной
//        boolean isTouchedSpecificBound = false;
//        if (Cube.getDir() == Cube.Dir.RIGHT)
//            isTouchedSpecificBound = allObjects[i].getSQX() + 1 ;
//        else if (Cube.getDir() == Cube.Dir.LEFT)
//            isTouchedSpecificBound = allObjects[i].getSQX() < 1;
//        else if (Cube.getDir() == Cube.Dir.UP)
//            isTouchedSpecificBound = allObjects[i].getSQY() > LevelGen.SQSIZE - 1;
//        else if (Cube.getDir() == Cube.Dir.DOWN)
//            isTouchedSpecificBound = allObjects[i].getSQY() < 1;
//    }

    private void resetMoveBounds(){

        if (Cube.getDir() == Cube.Dir.RIGHT){
            movedBounds.setPosition(Cube.getSX() + LevelGen.sizeObjects * 0.5f, Cube.getSY());
            movedBounds.setSize(LevelGen.sizeObjects * 0.5f, LevelGen.sizeObjects);
        } else if (Cube.getDir() == Cube.Dir.LEFT){
            movedBounds.setPosition(Cube.getSX(), Cube.getSY());
            movedBounds.setSize(LevelGen.sizeObjects * 0.5f, LevelGen.sizeObjects);
        } else if (Cube.getDir() == Cube.Dir.UP){
            movedBounds.setPosition(Cube.getSX(), Cube.getSY() + LevelGen.sizeObjects * 0.5f);
            movedBounds.setSize(LevelGen.sizeObjects, LevelGen.sizeObjects * 0.5f);
        } else if (Cube.getDir() == Cube.Dir.DOWN){
            movedBounds.setPosition(Cube.getSX(), Cube.getSY());
            movedBounds.setSize(LevelGen.sizeObjects, LevelGen.sizeObjects * 0.5f);
        }
    }

    float ottt, ottt2, sizCubL, mnR, mnL;
    boolean inv;

    public void match(SpriteBatch batch){

        ottt = (LevelSquare.sizOneSq - LevelGen.otstObjects);
        ottt2 = (LevelSquare.sizOneSq + LevelSquare.otst * 2f);

        mnR = LevelGen.otstObjects + LevelSquare.otst * 2f;

        sizCubL = (LevelSquare.otst * 2f + LevelGen.sizeObjects + LevelGen.otstObjects);
        resetMoveBounds();

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


                    settBounds();
                }
//                else if (allObjects[i].getKindedHash() == countColors && countColors != 0){       //устанавливаем цветной рядом с кубиком
//
//                }

                inv = false;
                if (countColors > 0){
                    boolean isInc = false;

                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q] instanceof ColoredCube && allObjects[q].isWired() && !isCorrectPos(q)){
                            isInc = true;
                            break;
                        }
                    }

                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q] instanceof ColoredCube && allObjects[q].isWired() && isInc){
//                            if (allObjects[q].isNormalPos()){
                            allObjects[q].normalizePos();
                            allObjects[q].setWired(false);      //разрываем связь
                            allObjects[q].setKindedHash(0);     //говорим, что положение относительно кубика равно нулю
                            allObjects[q].setEndedCorrectPos(false);    ///говорим, что при первом
                            // соприкосновении с краем он не будет останавливаться, т.к. он может
                            // находится на этом крае при начале движения
                            countColors = 0;
//                            }
//                            if (!inv)
                            inv = true;
                        }
                    }

                    if (inv){
                        Cube.inverseDir();
                        break;
                    }
                }

                if (allObjects[i] instanceof ColoredCube && countColors > 0){
                    if (allObjects[i].isWired()){
                        if (Cube.getDir() == Cube.Dir.RIGHT){
                            allObjects[i].setPosition(movedBounds.getX() + mnR + ottt * (allObjects[i].getKindedHash() - 1), allObjects[i].getY());
                        } else if (Cube.getDir() == Cube.Dir.LEFT){
                            allObjects[i].setPosition(movedBounds.getX() + movedBounds.getWidth() - LevelGen.sizeObjects - mnR - ottt * (allObjects[i].getKindedHash() - 1), allObjects[i].getY());
                        } else if (Cube.getDir() == Cube.Dir.UP){
                            allObjects[i].setPosition(allObjects[i].getX(), movedBounds.getY() + mnR + ottt * (allObjects[i].getKindedHash() - 1));
                        } else if (Cube.getDir() == Cube.Dir.DOWN){
                            allObjects[i].setPosition(allObjects[i].getX(), movedBounds.getY() + movedBounds.getHeight() - LevelGen.sizeObjects - mnR - ottt * (allObjects[i].getKindedHash() - 1));
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




//        DebugDrawer.drawRect(batch, movedBounds);
        Gm.DEBUG_VALUE1 = "" + countColors;
    }


    private void isPochTouch(){

    }

    private boolean isCorrectPos(int i){
        boolean isTouchedSpecificBound = false;
        if (Cube.getDir() == Cube.Dir.RIGHT)
            isTouchedSpecificBound = allObjects[i].getSQX() > LevelGen.SQSIZE - 1;
        else if (Cube.getDir() == Cube.Dir.LEFT)
            isTouchedSpecificBound = allObjects[i].getSQX() < 1;
        else if (Cube.getDir() == Cube.Dir.UP)
            isTouchedSpecificBound = allObjects[i].getSQY() > LevelGen.SQSIZE - 1;
        else if (Cube.getDir() == Cube.Dir.DOWN)
            isTouchedSpecificBound = allObjects[i].getSQY() < 1;

        return !isTouchedSpecificBound;
    }


    public void dispose() {

    }
}
