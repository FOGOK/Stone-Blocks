package com.java4game.cuadro.objects;

import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 27.02.2017 16:54.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Revers extends FieldObject{

    public Revers(Rectangle fieldBounds, int x, int y) {
        super(Assets.getNewSprite(66), fieldBounds, REVERS);
        setSQPos(x, y);
    }

}
