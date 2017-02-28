package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 25.02.2017 18:26.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class BoosterSlower extends FieldObject {

    private Sprite booster, slower;
    private boolean isBuster;
    public BoosterSlower(Rectangle fieldBounds, boolean isBuster, int x, int y) {
        super(Assets.getNewSprite(!isBuster ? 59 : 64), fieldBounds, isBuster ? BOOSTER : SLOWER);
        this.isBuster = isBuster;
        if (!isBuster){
            slower = block;
            booster = Assets.getNewSprite(64);
            booster.setSize(cellSize, cellSize);
            booster.setOriginCenter();
        }else{
            booster = block;
            slower = Assets.getNewSprite(59);
            slower.setSize(cellSize, cellSize);
            slower.setOriginCenter();
        }
        setSQPos(x, y);
    }

    public void nextBS(){
        isBuster = !isBuster;
        block = !isBuster ? slower : booster;
        setTypeBlock(isBuster ? BOOSTER : SLOWER);
    }

}
