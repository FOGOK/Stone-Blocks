package com.java4game.cuadro.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by FOGOK on 29.12.2016 5:12.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Assets {

    private static TextureAtlas textureAtlas;

    public static void init(){
        textureAtlas = new TextureAtlas("textures/720/720.atlas");
    }

    public static Sprite getNewSprite(int who){
        return new Sprite(textureAtlas.findRegion(String.valueOf(who)));
    }

    public static TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public static void dispose(){
        textureAtlas.dispose();
    }

}
