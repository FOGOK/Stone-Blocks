package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by java4game on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */

public class Cube extends GameObject{           ///класс кубика, который будет двигаться по игровому полю

    Dir dir;
    enum Dir{   //направление движения
        RIGHT, DOWN, LEFT, UP
    }
    float speed = 0.12f;
    float otst; //отступ от поля
    boolean povorot = false;

    int SQSIZE = 5;         // 6 - 1, т.к. последняя клетка равна 5 а первая 0

    float sizeSquareF; //размер клетки поля

    Rectangle levSqBounds;


    public Cube(Sprite sprite, LevelSquare levelSquare, float otst) {
        super(sprite);
        this.otst = otst;
        this.sprite.setRotation(360);
        levSqBounds = levelSquare.getBounds();
        sizeSquareF = levelSquare.getW() / SQSIZE;
        dir = Dir.RIGHT;    //изначально двигаемся вправо
    }



    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        mathPosition();
    }

    int pSQX, pSQY; ///позиция в клетках относительно левого нижнего угла поля
    private void mathPosition(){
        pSQX = getSQPosX();
        pSQY = getSQPosY();

        //двигаем кубик
        if (!povorot){              ///если поворот равен false, значит просто двигаемся, если нет, значит поворачиваем
            switch (dir){
                case RIGHT:
                    setPosition(getX() + speed * Gm.mdT, getY());
                    if (pSQX == SQSIZE){   //значит, что достигли право-верхнего угла
                        setXZA(true);
                        povorot = true;
                    }
                    break;
                case DOWN:
                    setPosition(getX(), getY() - speed * Gm.mdT);
                    if (pSQY == -1){                //значит, что достигли право-нижнего угла
                        setYZA(false);
                        povorot = true;
                    }
                    break;
                case LEFT:
                    setPosition(getX() - speed * Gm.mdT, getY());
                    if (getX() < levSqBounds.getX() - getW() - otst){                //значит, что достигли лево-нижнего угла
                        setPosition(levSqBounds.getX() - getW() - otst, getY());
                        dir = Dir.UP;
                    }
                    break;
                case UP:
                    setPosition(getX(), getY() + speed * Gm.mdT);
                    if (getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst){      //значит, что достигли лево-верхнего угла
                        setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
                        dir = Dir.RIGHT;
                        sprite.setRotation(360);
                    }
                    break;
            }
        }else{
            switch (dir){
                case RIGHT:
                    sprite.setOrigin(0f, 0f);
                    sprite.rotate(-speed * Gm.mdT * 50f);
                    if (sprite.getRotation() < 270){
                        povorot = false;
                        sprite.setOriginCenter();
                        sprite.setRotation(270);
                        setXZA(true);
                        setSQY(SQSIZE);
                        dir = Dir.DOWN;
                    }
                    break;
                case DOWN:
                    sprite.setOrigin(0f, getH());
                    if (sprite.getRotation() == 270f) sprite.setRotation(360); /// нормализуем поворот кубика из-за смены точки поворота
                    sprite.rotate(-speed * Gm.mdT * 50f);
                    if (sprite.getRotation() < 271f){
                        povorot = false;
                        sprite.setOriginCenter();
                        sprite.setRotation(180);
                        setYZA(false);
                        setSQX(SQSIZE);
                        dir = Dir.LEFT;
                    }
                    break;
                case LEFT:
                    break;
                case UP:
                    break;

            }
        }


        Gm.DEBUG_VALUE1 = "x" + getSQPosX() + "y" + getSQPosY();


//        setToSquare(2, 1, 1);
    }


    BigDecimal bigDecimal;
    public int getSQPosX(){
        bigDecimal = new BigDecimal(((getX() - levSqBounds.getX()) / (levSqBounds.getWidth() / SQSIZE))).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }

    public int getSQPosY(){
        bigDecimal = new BigDecimal(((getY() + getW() - levSqBounds.getY()) / (levSqBounds.getWidth() / SQSIZE))).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }



    private void setSQX(int iX){        //устанавливаем x на определённую клетку внутри поля
        setPosition(levSqBounds.getX() + iX * sizeSquareF + (sizeSquareF - getW()) / 2f - getW(), getY());
    }

    private void setSQY(int iY){    //устанавливаем y  на определённую клетку внутри поля
        setPosition(getX(), levSqBounds.getY() + iY * sizeSquareF + (sizeSquareF - getW()) / 2f - getW());
    }

    private void setXZA(boolean right){     //устанавливаем кубик за поле по x
        if (right)
            setPosition(levSqBounds.getX() + levSqBounds.getWidth() + otst, getY());
        else
            setPosition(levSqBounds.getX() - getW() - otst, getY());

    }

    private void setYZA(boolean top){     //устанавливаем кубик за поле по y
        if (top)
            setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
        else
            setPosition(getX(), levSqBounds.getY() - getW() - otst);
    }








        ///

}
