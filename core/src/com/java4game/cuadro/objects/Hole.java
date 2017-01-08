package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by FOGOK on 04.01.2017 9:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Hole extends FieldObject{

    private int type;
    public Hole(Sprite block, Rectangle fieldBounds, int x, int y, int type) {
        super(block, fieldBounds, false);
        setSQPos(x, y);
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
