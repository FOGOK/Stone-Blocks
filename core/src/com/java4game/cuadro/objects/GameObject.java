package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by java4game on 10.09.2016 23:30.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameObject {

    /**
     * То, что имеет игровой объект
     * т.е. спрайт и органичивающую область (я знаю о существовании ограничивающей области у спрайта (getBoundingRectangle)
     * но этот метод слишком долгий, поэтому есть тут rectangle, чтобы потом можно было удобно обрабатывать коллизии
     *
     * */

    Sprite sprite;
    Rectangle rectangle;

    public GameObject(Sprite sprite) {
        this.sprite = new Sprite(sprite);
        rectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setSize(float w, float h){          ///дублируем применение размера к спрайту и ограничивающей области
        rectangle.setSize(w, h);
        sprite.setSize(w, h);
    }

    public void setSize(float size){
        setSize(size, size);
    }

    public void setPosition(float x, float y){     ///дублируем применение положения к спрайту и ограничивающей области
        rectangle.setPosition(x, y);
        sprite.setPosition(x, y);
    }




    ///геттеры размеров и положения объекта
    public float getW(){
        return rectangle.width;
    }

    public float getH(){
        return rectangle.height;
    }

    public float getX(){
        return rectangle.x;
    }

    public float getY(){
        return rectangle.y;
    }
    ////




    //геттер размера объекта
    public Rectangle getBounds(){
        return rectangle;
    }
    ///
}
