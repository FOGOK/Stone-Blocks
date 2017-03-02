package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.InitLevels;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 04.01.2017 9:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Hole extends FieldObject{

    private int type;
    public Hole(Sprite block, Rectangle fieldBounds, int x, int y, int type) {
        super(block, fieldBounds, InitLevels.HOLE);
        setSQPos(x, y);
        this.type = type;
    }

    public void setColor(int color){
        type = color;
        int rgI = 13 + color;
        TextureRegion textureRegion = Assets.getTextureAtlas().findRegion(rgI + "");
        block.setRegion(textureRegion.getU(), textureRegion.getV(), textureRegion.getU2(), textureRegion.getV2());
    }

    public int getType() {
        return type;
    }
}
