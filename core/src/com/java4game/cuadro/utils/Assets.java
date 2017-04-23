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

    private static TextureAtlas textureAtlasObjects, textureAtlasBacks;

    public static void init(){
        textureAtlasObjects = new TextureAtlas("textures/720/720.atlas");
        textureAtlasBacks = new TextureAtlas("textures/720/backs.atlas");
    }

    public static Sprite getNewSprite(int who){
        if (who < 6 || who == 29)
            return new Sprite(textureAtlasBacks.findRegion(String.valueOf(who)));
        else
            return new Sprite(textureAtlasObjects.findRegion(String.valueOf(who)));
    }

    public static TextureAtlas getTextureAtlas() {
        return textureAtlasObjects;
    }

    public static void dispose(){
        textureAtlasObjects.dispose();
        textureAtlasBacks.dispose();
    }

}
