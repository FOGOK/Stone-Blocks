package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 04.01.2017 12:30.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class FieldObject {

    Sprite block;
    Rectangle blockBounds;
    Rectangle fieldBounds;
    float cellSize;  //размер ячейки без отступов // (т.е. тупое деление размера поля на количество клеток)

    boolean isCube;

    FieldObject(Sprite block, Rectangle fieldBounds, boolean isCube) {
        this.block = block;
        this.isCube = isCube;
        this.fieldBounds = fieldBounds;
        cellSize = fieldBounds.getWidth() / (LevelGen.SQSIZE + 3);
        blockBounds = new Rectangle();
        blockBounds.setSize(cellSize);
        block.setSize(cellSize, cellSize);
    }

    public void draw(SpriteBatch batch){
        block.draw(batch);
    }

    void setSQPos(int iX, int iY){
        setSQX(iX);
        setSQY(iY);
    }

    //устанавливаем позицию по x | y в клетках
    void setSQX(int iX){        //устанавливаем x на определённую клетку внутри поля
        setX(fieldBounds.getX() + iX * cellSize + (cellSize - block.getWidth()) / 2f);
    }
    void setSQY(int iY){    //устанавливаем y  на определённую клетку внутри поля
        setY(fieldBounds.getY() + iY * cellSize + (cellSize - block.getWidth()) / 2f);
    }
    ///

    //получить координаты кубика в клетках
    private BigDecimal bigDecimal;
    int getSQX(boolean center){
        final float centerP = center ? block.getWidth() / 2 : 0f;
        final float posXiiP = (block.getX() + centerP - fieldBounds.getX()) / (fieldBounds.getWidth() / (LevelGen.SQSIZE + 3));
        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    int getSQY(boolean center) {
        final float centerP = center ? block.getWidth() / 2 : 0f;
        final float posYiiP = (block.getY() + centerP - fieldBounds.getY()) / (fieldBounds.getWidth() / (LevelGen.SQSIZE + 3));
        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    ///

    public Rectangle getBlockBounds() {
        return blockBounds;
    }

    void setPosition(float x, float y){
        setX(x); setY(y);
    }

    void setX(float x){
        block.setX(x);
        blockBounds.setX(x + (block.getWidth() - blockBounds.getWidth()) / 2f);
    }

    void setY(float y){
        block.setY(y);
        blockBounds.setY(y + (block.getWidth() - blockBounds.getWidth()) / 2f);
    }

    void setBoundsPos(float x, float y){
        setBoundsX(x);
        setBoundsY(y);
    }

    void setBoundsX(float x){
        blockBounds.setX(x);
        block.setX(x - (block.getWidth() - blockBounds.getWidth()) / 2f);
    }

    void setBoundsY(float y){
        blockBounds.setY(y);
        block.setY(y - (block.getWidth() - blockBounds.getWidth()) / 2f);
    }

    public boolean isCube() {
        return isCube;
    }
}
