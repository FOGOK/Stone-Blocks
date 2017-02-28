package com.java4game.cuadro.objects;

import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 27.02.2017 16:54.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Rotate90 extends FieldObject {

    public Rotate90(Rectangle fieldBounds, boolean isMRotate, int x, int y) {
        super(Assets.getNewSprite(65), fieldBounds, !isMRotate ? ROTATE90 : ROTATEM90);
        block.setFlip(isMRotate, false);
        setSQPos(x, y);
    }

    public void nextBS(){
        block.flip(true, false);
        if (typeBlock == ROTATE90)
            typeBlock = ROTATEM90;
        else
            typeBlock = ROTATE90;
    }

}
