package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.usie.GameUI;

/**
 * Created by FOGOK on 09.10.2016 22:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameUtils {


    public static Texture generateBlackPic(){
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1f);
        pixmap.fillRectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


    /**
     * ///val % => позиция Y относительно фона начиная с верхней границы
     * */
    public static int getPosYFromUpBoard(float val){
        return (int) (GameUI.HEIGHT - (GameUI.HEIGHT * LevelGen.backHDivH * val));
    }

    /**
     * ///val % => количество пикселей относительно высоты фона
     * */
    public static int getPosXFromUpBoard(float val){
        return (int) (GameUI.WIDTH * val);
    }

    /**
     * ///val % => количество пикселей относительно высоты фона
     * */
    public static int getHFromBoard(float val){
        return (int) (GameUI.HEIGHT * LevelGen.backHDivH * val);
    }

    public static float FINAL_FONT_SCALE;

    public static void initializate(){
        ///какой базовый размер шрифта
        float heightCff = Gdx.graphics.getHeight() / 640f;
        float fontCff = 64f / GameUI.SIZE_CHAR_FONT;
        //////                      \/start value to scale 0.33f, then optimal effect
        FINAL_FONT_SCALE = fontCff * 0.33f * heightCff;
        ///
    }


}
