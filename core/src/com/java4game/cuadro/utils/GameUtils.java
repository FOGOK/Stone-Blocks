package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.java4game.cuadro.core.usie.UI;

/**
 * Created by FOGOK on 09.10.2016 22:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameUtils {


    private static Texture texture;

    public static Texture generateWhitePic(){
        if (texture == null){
            Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
            pixmap.setColor(1f, 1f, 1f, 1f);
            pixmap.fillRectangle(0, 0, 50, 50);
            texture = new Texture(pixmap);
            pixmap.dispose();
        }
        return texture;
    }


//    /**
//     * ///val % => позиция Y относительно фона начиная с верхней границы
//     * */
//    public static int getPosYFromUpBoard(float val){
//        return (int) (GameUI.HEIGHT - (GameUI.HEIGHT * LevelGen.backHDivH * val));
//    }
//
//    /**
//     * ///val % => количество пикселей относительно высоты фона
//     * */
//    public static int getPosXFromUpBoard(float val){
//        return (int) (GameUI.WIDTH * val);
//    }
//
//    /**
//     * ///val % => количество пикселей относительно высоты фона
//     * */
//    public static int getHFromBoard(float val){
//        return (int) (GameUI.HEIGHT * LevelGen.backHDivH * val);
//    }

    public static float FINAL_FONT_SCALE;

    public static void initializate(){
        ///какой базовый размер шрифта
        float heightCff = Gdx.graphics.getHeight() / 640f;
        float fontCff = 64f / UI.SIZE_CHAR_FONT;
        //////                      \/start value to scale 0.33f, then optimal effect
        FINAL_FONT_SCALE = fontCff * 0.33f * heightCff;
        ///
    }

    public static void dispose(){
        if (texture != null){
            texture.dispose();
            texture = null;
        }
    }


}
