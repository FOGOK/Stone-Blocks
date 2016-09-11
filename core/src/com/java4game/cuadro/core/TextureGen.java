package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.java4game.cuadro.utils.Atalas;

import java.util.HashMap;

/**
 * Created by java4game on 11.09.2016 22:21.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TextureGen {

    /**Класс, который хранит в себе все текстуры
     *
     * */

    HashMap<String, Sprite> objects;

    //cсылки
    Atalas atls;
    ///

    public TextureGen(Atalas atls) {
        objects = new HashMap<String, Sprite>(Atalas.textsCount);

        //инициализация ссылок
        this.atls = atls;
        ///

        for (int i = 0; i < Atalas.textsCount; i++)
            objects.put(Atalas.names[i], new Sprite(atls.getTG(i)));

    }

    public Sprite getSprite(String s){
        return new Sprite(objects.get(s));
    }

    public void dispose() {

    }
}
