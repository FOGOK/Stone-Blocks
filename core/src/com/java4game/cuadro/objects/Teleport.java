package com.java4game.cuadro.objects;

import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 14.03.2017 1:43.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Teleport extends FieldObject {

    public Teleport(Rectangle fieldBounds, int x, int y) {
        super(Assets.getNewSprite(65), fieldBounds, TELEPORT);
        setSQPos(x, y);
    }

}
