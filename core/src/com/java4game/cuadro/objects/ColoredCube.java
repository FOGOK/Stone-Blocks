package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.ObjectsGen;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 18.09.2016 17:51.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ColoredCube extends SquareObject{


    float toKindX, toKindY;
    boolean deleteAllFirstes = false;

    public static int[] hashsKinded = new int[ObjectsGen.edge_lenght + 1];
    public static int globalSt = 0;






    //ссылки
    Rectangle levSqBounds;
    SquareObject[] allObjects;
    ///
    public ColoredCube(Sprite sprite, int x, int y, Rectangle levSqBounds,boolean isEdged, SquareObject[] allObjects, int hash) {
        super(sprite, x, y, levSqBounds, hash);
        this.levSqBounds = levSqBounds;
        this.allObjects = allObjects;
        whoI = 0;
        globalSt = 0;

        kindedHash = -1;

        for (int i = 0; i < hashsKinded.length; i++) {
            hashsKinded[i] = -1;
        }


        if (isEdged){

        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
//        moveInKKCube();
    }


    boolean isKmn = false;
    public void moveInKKCube(){


        if (kindedHash == -1){
            kindX = Cube.X;
            kindY = Cube.Y;
            kindDir = Cube.getDir();
        }else{
            SquareObject s = getObjectFromHash(kindedHash);
            kindX = s.getX();
            kindY = s.getY();
            kindDir = Cube.getDir();
        }

        if (isMovevedCoords())
            moveThisWithCube();


    }

    boolean tB = false;
    float ottt;
    private boolean isMovevedCoords(){      //если координаты kind`a находятся возле координат цветного
        ottt = (LevelSquare.otst * 2f + LevelSquare.sizOneSq + LevelGen.otstObjects);



        boolean ret = false;
        if (kindDir == Cube.Dir.RIGHT)
            ret = (kindX > getX() - ottt) && getSQY(kindY) == getSQY(getY());
        else if (kindDir == Cube.Dir.LEFT)
            ret = (kindX < getX() + ottt) && getSQY(kindY) == getSQY(getY());
        else if (kindDir == Cube.Dir.UP)
            ret = (kindY > getY() - ottt) && getSQX(kindX) == getSQX(getX());
        else if (kindDir == Cube.Dir.DOWN)
            ret = (kindY < getY() + ottt) && getSQX(kindX) == getSQX(getX());

//        if (ret)


        if (ret && !tB){
            tB = true;
            setKinds(hash);     //устанавливаем себя в качестве короля для всех остальных

//            boolean b = true;
//            for (int q = 0; q < hashsKinded.length; q++) {
//                if (hashsKinded[q] == hash){
//                    b = false;
//                    break;
//                }
//            }
//            if (b){
//                hashsKinded[globalSt++] = hash;
//
//            }
        }







//            tB = true;
//
////            if (globalSt == 0 && !tB){
////                tB = true;
////                hashsKinded[0] = hash;
////                globalSt++;
////
////            }
//
//        }



        return ret;
    }

    private SquareObject getObjectFromHash(int hash){
        return allObjects[hash];
    }

    private void setKinds(int hash){        ///делает указанный цветной королём для всех остальных, кроме королей
        for (int i = 0; i < ObjectsGen.iters; i++) {
            boolean b = true;
            if (globalSt != 0){
                for (int q = 0; q < hashsKinded.length; q++) {
                    if (hashsKinded[q] == hash){
                        b = false;
                        break;
                    }
                }
            }
//
            if (allObjects[i].hash != hash && b){
                allObjects[i].kindedHash = hash;
            }else if (b){
                hashsKinded[globalSt++] = hash;
            }
        }
    }


    private void moveThisWithCube(){       // двигаем цветной относительно короля (kind)
        ////            true true   //направо
        ////            true false  //налево
        ////            false true  //вверх
        ////            false false //вниз

        float dX = ottt;
        float dY = ottt;

        if (kindDir == Cube.Dir.LEFT || kindDir == Cube.Dir.DOWN) {
            dX *= -1;
            dY *= -1;
        }



        if (kindDir == Cube.Dir.LEFT || kindDir == Cube.Dir.RIGHT) dY = 0f; else dX = 0f;  ///окончательное условие не путаться!!!

        toKindX = kindX + dX;
        toKindY = kindY + dY;

        setPosition(toKindX, toKindY);
    }













    ///

//    private void setKind(){
//        for (int i = 0; i < ObjectsGen.iters; i++) {
//            if (!allObjects[i].isLinking && !allObjects[i].isFirstLinking){
//                allObjects[i].kindDir = kindDir;
//                allObjects[i].kindX = toKindX;
//                allObjects[i].kindY = toKindY;
//            }
//        }
//    }



//    if (ret && whoI == 0){
//        float max = 0f;
//        for (int i = 0; i < ObjectsGen.iters; i++) {
//            if (allObjects[i].getX() == getX() || allObjects[i].getY() == getY() && allObjects[i].whoI != 0){
//                boolean xAxis = (kindDir == Cube.Dir.RIGHT || kindDir == Cube.Dir.LEFT) ?
//                        allObjects[i].getX() > max : allObjects[i].getY() > max;
//
//
//                if (xAxis){
//                    max = (kindDir == Cube.Dir.RIGHT || kindDir == Cube.Dir.LEFT) ? allObjects[i].getX() : allObjects[i].getY();
//
//
//
//                    for (int q = 0; q < VOZ_COUNT.length; q++) {
//                        if (VOZ_COUNT[q] == 0){
//                            allObjects[i].setWhoI(q);
//                            VOZ_COUNT[q] = 1;
//                            break;
//                        }
//                    }
//                }
//
//            }
//        }
//    }



}
