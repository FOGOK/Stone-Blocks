package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.UI;

/**
 * Created by FOGOK on 09.10.2016 22:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class GameUtils {

    /**
     * ///val % => позиция Y относительно фона начиная с верхней границы
     * */
    public static int getPosYFromUpBoard(float val){
        return (int) (UI.HEIGHT - (UI.HEIGHT * LevelGen.backHDivH * val));
    }

    /**
     * ///val % => количество пикселей относительно высоты фона
     * */
    public static int getPosXFromUpBoard(float val){
        return (int) (UI.WIDTH * val);
    }

    /**
     * ///val % => количество пикселей относительно высоты фона
     * */
    public static int getHFromBoard(float val){
        return (int) (UI.HEIGHT * LevelGen.backHDivH * val);
    }

    public static float FINAL_FONT_SCALE;

    static {
        ///какой базовый размер шрифта
        float heightCff = Gdx.graphics.getHeight() / 640f;
        float fontCff = 64f / UI.SIZE_CHAR_FONT;
        //////                      \/start value to scale 0.33f, then optimal effect
        FINAL_FONT_SCALE = fontCff * 0.33f * heightCff;
        ///
    }


}
