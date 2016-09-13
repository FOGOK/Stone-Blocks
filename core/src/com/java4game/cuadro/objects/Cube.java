package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by java4game on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */

public class Cube extends GameObject{           ///класс кубика, который будет двигаться по игровому полю

    Dir dir, nextDir;
    enum Dir{   //направление движения
        RIGHT, DOWN, LEFT, UP
    }
    float speed = 0.12f;
    float otst; //отступ от поля
    boolean povorot = false;
    Random rnd = new Random();

    int SQSIZE = 5;         // 6 - 1, т.к. последняя клетка равна 5 а первая 0

    float sizeSquareF; //размер клетки поля

    Rectangle levSqBounds;

    public Cube(Sprite sprite, LevelSquare levelSquare, float otst, float size) {
        super(sprite);



        setSize(size);
        this.otst = otst;
        this.sprite.setOriginCenter();
        levSqBounds = levelSquare.getBounds();
        sizeSquareF = levelSquare.getW() / (SQSIZE + 1);




        dir = (rnd.nextBoolean()) ? ((rnd.nextBoolean()) ? Dir.RIGHT : Dir.LEFT) : ((rnd.nextBoolean()) ? Dir.UP : Dir.DOWN);
        revers = rnd.nextBoolean();

        if (dir.equals(Dir.RIGHT)){
            setXZA((!revers) ? false : true);
            setYZA((!revers) ? true : false);
        }else if (dir.equals(Dir.LEFT)){
            setXZA((!revers) ? true : false);
            setYZA((!revers) ? false : true);
        }else if (dir.equals(Dir.UP)){
            setXZA((!revers) ? false : true);
            setYZA((!revers) ? false : true);
        }else{
            setXZA((!revers) ? true : false);
            setYZA((!revers) ? true : false);
        }

        sprite.setRotation(0);
        speed = 0.1f + (rnd.nextInt(2) / 10f);
    }




    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        mathPosition();
    }


    int lastpSQX, lastpSQY; ///позиция в клетках относительно левого нижнего угла поля
    boolean chngX, cngTR;
    int chgVal;
    float pX, pY;

    boolean revers = false;

    boolean perch = false;
    private void mathPosition(){

        switch (dir){
            case RIGHT:
                chngX = true;
                chgVal = SQSIZE + 1;
                cngTR = true;
                nextDir = (revers) ? Dir.UP : Dir.DOWN;
                pX = speed * Gm.mdT;
                pY = 0f;

                if (perch){
                    dir = nextDir;
                    setSQX(getSQPosX());
                    pX = pY = 0f;
                    perch = false;
                    povorot = true;
                }
                break;
            case DOWN:
                chngX = false;
                chgVal = -1;
                cngTR = false;
                nextDir = (revers) ? Dir.RIGHT : Dir.LEFT;
                pX = 0f;
                pY = -speed * Gm.mdT;

                if (perch){
                    dir = nextDir;
                    setSQY(getSQPosY());
                    pX = pY = 0f;
                    perch = false;
                    povorot = true;
                }
                break;
            case LEFT:
                chngX = true;
                chgVal = -1;
                cngTR = false;
                nextDir = (revers) ? Dir.DOWN : Dir.UP;
                pX = -speed * Gm.mdT;
                pY = 0f;

                if (perch){
                    dir = nextDir;
                    setSQX(getSQPosX());
                    pX = pY = 0f;
                    perch = false;
                    povorot = true;
                }
                break;
            case UP:
                chngX = false;
                chgVal = SQSIZE + 1;
                cngTR = true;
                nextDir = (revers) ? Dir.LEFT : Dir.RIGHT;
                pX = 0f;
                pY = speed * Gm.mdT;

                if (perch){
                    dir = nextDir;
                    setSQY(getSQPosY());
                    pX = pY = 0f;
                    perch = false;
                    povorot = true;
                }
                break;
        }

        int k = (chngX) ? getSQPosX() : getSQPosY();
        setPosition(getX() + pX, getY() + pY);
        if (k == chgVal){
            povorot = true;
            if ((chngX) ? isXOUT(cngTR) : isYOUT(cngTR)){
                if (chngX)
                    setXZA(cngTR);
                else
                    setYZA(cngTR);

                dir = nextDir;
            }
        }

        if (povorot){
            float delt = -170f * speed * Gm.mdT;
            if (revers) delt *= -1;
            sprite.rotate(delt);
            if (Math.abs(sprite.getRotation() + delt) > limRot){
                sprite.setRotation(0);
                povorot = false;
                speed = 0.1f + (rnd.nextInt(2) / 10f);
            }
        }


        if (Gdx.input.justTouched()){
            perch = true;
            lastpSQX = getSQPosX();
            lastpSQY = getSQPosY();
        }

        Gm.DEBUG_VALUE1 = "" + perch;
    }


    float limRot = 360;


    BigDecimal bigDecimal;



    public int getSQPosX(){
        bigDecimal = new BigDecimal((getX() + getW() / 2 - levSqBounds.getX()) / (levSqBounds.getWidth() / (SQSIZE + 1))).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }

    public int getSQPosY(){
        bigDecimal = new BigDecimal((getY() + getW() / 2 - levSqBounds.getY()) / (levSqBounds.getWidth() / (SQSIZE + 1))).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }



    private void setSQX(int iX){        //устанавливаем x на определённую клетку внутри поля
        setPosition(levSqBounds.getX() + iX * sizeSquareF + (sizeSquareF - getW()) / 2f, getY());
    }

    private void setSQY(int iY){    //устанавливаем y  на определённую клетку внутри поля
        setPosition(getX(), levSqBounds.getY() + iY * sizeSquareF + (sizeSquareF - getW()) / 2f);
    }

    private void setXZA(boolean right){     //устанавливаем кубик за поле по x
        if (right)
            setPosition(levSqBounds.getX() + levSqBounds.getWidth() + otst, getY());
        else
            setPosition(levSqBounds.getX() - getW() - otst, getY());
    }

    private boolean isXOUT(boolean right){
        return (right) ? getX() > levSqBounds.getX() + levSqBounds.getWidth() + otst : getX() < levSqBounds.getX() - getW() - otst;
    }

    private boolean isYOUT(boolean top){
        return (top) ? getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst : getY() < levSqBounds.getY() - getW() - otst;
    }

    private void setYZA(boolean top){     //устанавливаем кубик за поле по y
        if (top)
            setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
        else
            setPosition(getX(), levSqBounds.getY() - getW() - otst);
    }








        ///govno!!!

//    public static int mmmm = 0;
//    String m;


//    m = Integer.toBinaryString(mmmm);
//
//
//    try {
//        rigDown = (m.charAt(0) == '0');
//    }catch (Exception e){}
//
//    try {
//        rigLef = (m.charAt(1) == '0');
//    }catch (Exception e){}
//
//    try {
//        upDown = (m.charAt(2) == '0');
//    }catch (Exception e){}
//
//    try {
//        revers = (m.charAt(3) == '0');
//    }catch (Exception e){}


//    Gm.DEBUG_VALUE1 = "x" + getSQPosX() + "y" + getSQPosY() + ";" + " mmm " + mmmm;
//    Gm.DEBUG_VALUE2 = "" + revers + " " + upDown + " " +  rigLef  + " " + rigDown + "\n" + m;
    //двигаем кубик
//        switch (dir){
//            case RIGHT:
//                setPosition(getX(), getY());
//                if (pSQX == SQSIZE + 1){   //значит, что достигли право-верхнего угла
//                    setXZA(true);
//                    povorot = true;
//                    dir = Dir.DOWN;
//                }
//                break;
//            case DOWN:
//                setPosition(getX(), getY() - speed * Gm.mdT);
//                if (pSQY == -1){                //значит, что достигли право-нижнего угла
//                    setYZA(false);
//                    povorot = true;
//                    dir = Dir.LEFT;
//                }
//                break;
//            case LEFT:
//                setPosition(getX() - speed * Gm.mdT, getY());
//                if (pSQX == -1){                //значит, что достигли лево-нижнего угла
//                    setXZA(false);
//                    povorot = true;
//                    dir = Dir.LEFT;
//                }
//                break;
//            case UP:
//                setPosition(getX(), getY() + speed * Gm.mdT);
//                if (getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst){      //значит, что достигли лево-верхнего угла
//                    setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
//                    setYZA(true);
//                    dir = Dir.RIGHT;
//                    sprite.setRotation(360);
//                }
//                break;
//        }


//    switch (dir){
//        case RIGHT:
//            sprite.setOrigin(0f, 0f);
//            sprite.rotate(-speed * Gm.mdT * 50f);
//            if (sprite.getRotation() < 270){
//                povorot = false;
//                sprite.setOriginCenter();
//                sprite.setRotation(270);
//                setXZA(true);
//                setSQY(SQSIZE);
//                dir = Dir.DOWN;
//            }
//            break;
//        case DOWN:
//            sprite.setOrigin(0f, getH());
//            if (sprite.getRotation() == 270f) sprite.setRotation(360); /// нормализуем поворот кубика из-за смены точки поворота
//            sprite.rotate(-speed * Gm.mdT * 50f);
//            if (sprite.getRotation() < 271f){
//                povorot = false;
//                sprite.setOriginCenter();
//                sprite.setRotation(180);
//                setYZA(false);
//                setSQX(SQSIZE);
//                dir = Dir.LEFT;
//            }
//            break;
//        case LEFT:
//            break;
//        case UP:
//            break;
//
//    }


}
