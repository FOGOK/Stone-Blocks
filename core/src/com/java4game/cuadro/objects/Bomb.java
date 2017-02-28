package com.java4game.cuadro.objects;

import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 27.02.2017 16:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Bomb extends FieldObject{
    public Bomb(Rectangle fieldBounds, int x, int y) {
        super(Assets.getNewSprite(60), fieldBounds, BOMB);
        setSQPos(x, y);
    }
}
