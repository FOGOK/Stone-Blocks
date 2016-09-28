package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.ColoredCube;
import com.java4game.cuadro.objects.Cube;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.objects.SquareObject;
import com.java4game.cuadro.utils.DebugDrawer;

import java.util.Random;


/**
 * Created by FOGOK on 19.09.2016 21:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ColorCubeMover {


    Rectangle colorChecker;

    int stackCount = 0;    ///количество прилепленных цветных (размер стэка)
    Random rnd = new Random();

    public static boolean isPregInv;    //если нужно преждевременно развернуть главный кубик




    //ссылки
    SquareObject[] allObjects;
    Rectangle sqBounds;
    ///



    public ColorCubeMover(SquareObject[] allObjects, Rectangle sqBounds) {
        this.allObjects = allObjects;
        this.sqBounds = sqBounds;
        colorChecker = new Rectangle(0f, 0f, LevelGen.sizeObjects, LevelGen.sizeObjects);
        isPregInv = false;
        initHolesCoords();
        //настраиваем отступы
        mnR = LevelGen.otstObjects * 2 + LevelSquare.otst;
        ottt = LevelGen.sizeObjects + LevelGen.otstObjects;
        sizCubL = (LevelSquare.otst * 2f + LevelGen.sizeObjects + LevelGen.otstObjects);
        ///
    }



    ///всё, что относится к лункам
    HolesCords[] holesCords;
    class HolesCords{       //координаты лунок
        int x, y;
        int type;
        protected HolesCords(int x, int y, int type){    //в зависимости от направления выставляется кубик на определённную грань
            set(x, y, type);
        }

        protected void set(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        protected int getX(){
            return x;
        }

        protected int getY() {
            return y;
        }

        protected int getType(){
            return type;
        }
    }
    Rectangle[] holesEnters;        //точки входа типо для определённых типов

    private void initHolesCoords(){
        int count = 2;
        holesCords = new HolesCords[count];
        holesEnters = new Rectangle[count];
        for (int i = 0; i < count; i++) {
//            Cube.Dir qd = (i < 2) ? (i == 0 ? Cube.Dir.DOWN : Cube.Dir.UP) : (i == 2 ? Cube.Dir.LEFT : Cube.Dir.RIGHT);
            int xyq = rnd.nextInt(LevelGen.SQSIZE + 1);
            holesEnters[i] = new Rectangle();


            int x = (i < 2) ? xyq : (i == 2) ? -1 : LevelGen.SQSIZE + 1;
            int y = (i > 1) ? xyq : (i == 0) ? -1 : LevelGen.SQSIZE + 1;
            int minX = (i < 2) ? xyq : (i == 2) ? 0 : LevelGen.SQSIZE;
            int minY = (i > 1) ? xyq : (i == 0) ? 0 : LevelGen.SQSIZE;

            holesCords[i] = new HolesCords(x, y, i);

            ObjectsGen.holes[i].setPosition(sqBounds.x + (x * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                    sqBounds.y + (y * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
            ObjectsGen.holes[i].setSize(LevelGen.sizeObjects, LevelGen.sizeObjects);

            holesEnters[i].setPosition(sqBounds.x + (minX * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                    sqBounds.y + (minY * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
            holesEnters[i].setSize(LevelGen.sizeObjects);
        }
    }

    public void drawHoles(SpriteBatch batch){
        for (int i = 0; i < holesCords.length; i++) {
            ObjectsGen.holes[i].draw(batch);
        }
    }
    ///



    private void setChecker(){      //изменяем размеры проверяющего прямоугольника в соотвстевии с размером текущего стэка

        colorChecker.setPosition(Cube.getSX(), Cube.getSY());
        colorChecker.setSize(LevelGen.sizeObjects);
        float cffP = 0.1f;

        if (Cube.getDir() == Cube.Dir.RIGHT){
            colorChecker.setWidth(sizCubL * (stackCount));
            colorChecker.setX(colorChecker.getX() + colorChecker.getHeight() + cffP);
        }
        else if (Cube.getDir() == Cube.Dir.LEFT){
            colorChecker.setWidth(sizCubL * (stackCount));
            colorChecker.setX(colorChecker.getX() - sizCubL * (stackCount) - cffP);
        }
        else if (Cube.getDir() == Cube.Dir.UP){
            colorChecker.setHeight(sizCubL * (stackCount));
            colorChecker.setY(colorChecker.getY() + colorChecker.getWidth() + cffP);
        }
        else if (Cube.getDir() == Cube.Dir.DOWN){
            colorChecker.setHeight(sizCubL * (stackCount));
            colorChecker.setY(colorChecker.getY() - sizCubL * (stackCount) - cffP);
        }
    }


    private void resetMoveBounds(){     //ставим проверяющий прямоугольник в кубик

        if (Cube.getDir() == Cube.Dir.RIGHT){
            colorChecker.setPosition(Cube.getSX() + LevelGen.sizeObjects * 0.5f, Cube.getSY());
            colorChecker.setSize(LevelGen.sizeObjects * 0.5f, LevelGen.sizeObjects);
        } else if (Cube.getDir() == Cube.Dir.LEFT){
            colorChecker.setPosition(Cube.getSX(), Cube.getSY());
            colorChecker.setSize(LevelGen.sizeObjects * 0.5f, LevelGen.sizeObjects);
        } else if (Cube.getDir() == Cube.Dir.UP){
            colorChecker.setPosition(Cube.getSX(), Cube.getSY() + LevelGen.sizeObjects * 0.5f);
            colorChecker.setSize(LevelGen.sizeObjects, LevelGen.sizeObjects * 0.5f);
        } else if (Cube.getDir() == Cube.Dir.DOWN){
            colorChecker.setPosition(Cube.getSX(), Cube.getSY());
            colorChecker.setSize(LevelGen.sizeObjects, LevelGen.sizeObjects * 0.5f);
        }
    }

    float ottt, sizCubL, mnR;
    boolean inv;


    final float boundIters = 2; //сколько итераций сделать ещё, после того как дотронулись
    float bIters = 0f;
    boolean boundIt = false;                    //говорим, что начинаем операцию boundIters
    public void match(SpriteBatch batch){






        if (stackCount > 0)
            setChecker();        //если в стэке есть цветные, значит указываем размеры и положение проверяющего прямоугольника
        else
            resetMoveBounds();      //ставим ограничивающую линию в одну






        for (int i = 0; i < ObjectsGen.iters; i++) {
            if (allObjects[i] instanceof ColoredCube && !allObjects[i].isCollected()){
                if (allObjects[i].getBounds().overlaps(colorChecker) && !allObjects[i].isWired()){   //проверяем
                    //                                                                    // есть ли цветные рядом с кубиком

                    stackCount++;
                    Cube.setMovedControl(true); //говорим кубику, что можно при удобном случае его развернуть
                    allObjects[i].setKindedHash(stackCount);
                    allObjects[i].setWired(true);

                    setChecker();
                }

                if (allObjects[i].getSQX() < 0 || allObjects[i].getSQX() > LevelGen.SQSIZE  || allObjects[i].getSQY() < 0 || allObjects[i].getSQY() > LevelGen.SQSIZE){
                    allObjects[i].setCollected(true);
                    allObjects[i].normalizePos();
                    Cube.setMovedControl(false);
                    bIters = 0f;
                    boundIt = false;
                    isPregInv = false;
                    stackCount--;
                }

                inv = false;
                if (stackCount > 0){
                    ///мутим тему с коллизиями краёв
                    boolean isInc = false;
                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q] instanceof ColoredCube && !allObjects[q].isCollected() && allObjects[q].isWired() && !isCorrectPos(q)){
                            boundIt = true;
                            break;
                        }
                    }

                    if (boundIt && bIters < boundIters){
                        bIters += 1f * Gm.mdT;
                    }else if (boundIt){
                        isInc = true;
                        bIters = 0f;
                        boundIt = false;
                    }
                    /////////

                    // а тут тип исключение, если на краю есть какая-то цветная темка, то продолжаем двигать цепочку
                    boolean isHole = false;
                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q] instanceof ColoredCube && !allObjects[q].isCollected() && allObjects[q].isWired() && isTouchedPHole(q)){
                            isHole = true;
                            break;
                        }
                    }
                    ////


                    for (int q = 0; q < ObjectsGen.iters; q++) {
                        if (allObjects[q] instanceof ColoredCube && !allObjects[q].isCollected() && allObjects[q].isWired() && ((isInc && !isHole) || isPregInv)){

                            int x = Cube.getSqX();
                            int y = Cube.getSqY();
                            int kindedHash = (Cube.getDir() == Cube.Dir.RIGHT || Cube.getDir() == Cube.Dir.UP) ? allObjects[q].getKindedHash() : -allObjects[q].getKindedHash();

                            x += (Cube.getDir() == Cube.Dir.DOWN || Cube.getDir() == Cube.Dir.UP) ? 0 : kindedHash;
                            y += (Cube.getDir() == Cube.Dir.DOWN || Cube.getDir() == Cube.Dir.UP) ? kindedHash : 0;
                            allObjects[q].setPosition(sqBounds.x + (x * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                                    sqBounds.y + (y * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);


                            allObjects[q].setWired(false);      //разрываем связь
                            allObjects[q].setKindedHash(0);     //говорим, что положение относительно кубика равно нулю
                            allObjects[q].setEndedCorrectPos(false);    ///говорим, что при первом
                            // соприкосновении с краем он не будет останавливаться, т.к. он может
                            // находится на этом крае при начале движения
                            stackCount = 0;
                            inv = true;
                        }
                    }
                    isPregInv = false;

                    if (inv){
                        Cube.setMovedControl(false);
                        Cube.inverseDir();
                        break;
                    }


                    if (allObjects[i] instanceof ColoredCube && !allObjects[i].isCollected()){
                        if (allObjects[i].isWired()){
                            if (Cube.getDir() == Cube.Dir.RIGHT){
                                allObjects[i].setPosition(colorChecker.getX() + mnR + ottt * (allObjects[i].getKindedHash() - 1), allObjects[i].getY());
                            } else if (Cube.getDir() == Cube.Dir.LEFT){
                                allObjects[i].setPosition(colorChecker.getX() + colorChecker.getWidth() - LevelGen.sizeObjects - mnR - ottt * (allObjects[i].getKindedHash() - 1), allObjects[i].getY());
                            } else if (Cube.getDir() == Cube.Dir.UP){
                                allObjects[i].setPosition(allObjects[i].getX(), colorChecker.getY() + mnR + ottt * (allObjects[i].getKindedHash() - 1));
                            } else if (Cube.getDir() == Cube.Dir.DOWN){
                                allObjects[i].setPosition(allObjects[i].getX(), colorChecker.getY() + colorChecker.getHeight() - LevelGen.sizeObjects - mnR - ottt * (allObjects[i].getKindedHash() - 1));
                            }
                        }
                    }


                }
            }
        }


//        for (int i = 0; i < holesEnters.length; i++) {
//            DebugDrawer.drawRect(batch, holesEnters[i]);
//        }
//
        DebugDrawer.drawRect(batch, colorChecker);

        Gm.DEBUG_VALUE1 = "" + isPregInv + " " + stackCount;
    }

    private boolean isTouchedPHole(int i){
        for (int q = 0; q < holesEnters.length; q++) {
            if (holesEnters[q].overlaps(allObjects[i].getBounds()) && holesCords[q].getType() == allObjects[i].getColorType()){
                return true;
            }
        }
        return false;
    }

//    boolean isTouchedPredHole = false;
    private boolean isCorrectPos(int i){        //если касаемся краёв, то значит true, иначе false
        boolean isTouchedSpecificBound = false;
        if (Cube.getDir() == Cube.Dir.RIGHT)
            isTouchedSpecificBound = allObjects[i].getSQX() > LevelGen.SQSIZE;
        else if (Cube.getDir() == Cube.Dir.LEFT)
            isTouchedSpecificBound = allObjects[i].getSQX() < 0;
        else if (Cube.getDir() == Cube.Dir.UP)
            isTouchedSpecificBound = allObjects[i].getSQY() > LevelGen.SQSIZE;
        else if (Cube.getDir() == Cube.Dir.DOWN)
            isTouchedSpecificBound = allObjects[i].getSQY() < 0;


        return !isTouchedSpecificBound;
    }






    public void dispose() {

    }


}
