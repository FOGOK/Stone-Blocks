package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game and FOGOK on 11.09.2016 22:21.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TextureGen {

    /**Класс, который хранит в себе все текстуры
     *
     * */
    private Atalas atls;

    public TextureGen(Atalas atls) {
        this.atls = atls;
    }

    public Sprite getSprite(int i) {
        return new Sprite(atls.getTG(i));
    }

    public TextureRegion getTextureRG(int i){
        return atls.getTG(i);
    }
}
