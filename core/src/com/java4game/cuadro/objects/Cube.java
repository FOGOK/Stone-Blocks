package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
    float speed = 0.1f;
    float otst; //отступ от поля

    Rectangle levSqBounds;


    public Cube(Sprite sprite, LevelSquare levelSquare, float otst) {
        super(sprite);
        this.otst = otst;
        levSqBounds = levelSquare.getBounds();
        dir = Dir.RIGHT;    //изначально двигаемся вправо
    }



    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        mathPosition();
    }

    private void mathPosition(){
        //двигаем кубик
        switch (dir){
            case RIGHT:
                setPosition(getX() + speed, getY());
                if (getX() > levSqBounds.getX() + levSqBounds.getWidth() + otst){   //значит, что достигли право-верхнего угла
                    setPosition(levSqBounds.getX() + levSqBounds.getWidth() + otst, getY());
                    dir = Dir.DOWN;
                }
                break;
            case DOWN:
                setPosition(getX(), getY() - speed);
                if (getY() < levSqBounds.getY() - getH() - otst){                //значит, что достигли право-нижнего угла
                    setPosition(getX(), levSqBounds.getY() - getH() - otst);
                    dir = Dir.LEFT;
                }
                break;
            case LEFT:
                setPosition(getX() - speed, getY());
                if (getX() < levSqBounds.getX() - getW() - otst){                //значит, что достигли лево-нижнего угла
                    setPosition(levSqBounds.getX() - getW() - otst, getY());
                    dir = Dir.UP;
                }
                break;
            case UP:
                setPosition(getX(), getY() + speed);
                if (getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst){      //значит, что достигли лево-верхнего угла
                    setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
                    dir = Dir.RIGHT;
                }
                break;
        }






        ///
    }
}
