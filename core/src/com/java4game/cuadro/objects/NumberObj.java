package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;

/**
 * Created by FOGOK on 16.09.2016 15:52.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class NumberObj extends GameObject {


    int number;
    boolean isDestroyed;
    public NumberObj(Sprite sprite, int x, int y, Rectangle sqBounds, int number) {
        super(sprite);
        this.number = number;
        setPosition(sqBounds.x + (x * (LevelSquare.sizOneSq + LevelSquare.otst * 2)), sqBounds.y + (y * (LevelSquare.sizOneSq + LevelSquare.otst * 2)));
        setSize(LevelSquare.sizOneSq);
    }

    boolean isCollect = false;
    public void collect(){
       isCollect = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isCollect){
            collectAnimation();
        }
    }


    private void collectAnimation(){
        LevelGen.SCORE += number;
        isDestroyed = true;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }
}
