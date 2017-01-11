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

    public void setAlpha(float alpha){
        block.setAlpha(alpha);
    }

    //устанавливаем позицию по x | y в клетках
    void setSQX(int iX){        //устанавливаем x на определённую клетку внутри поля
        setX(fieldBounds.getX() + iX * cellSize + (cellSize - block.getWidth()) / 2f);
    }
    void setSQY(int iY){    //устанавливаем y  на определённую клетку внутри поля
        setY(fieldBounds.getY() + iY * cellSize + (cellSize - block.getWidth()) / 2f);
    }
    float getPosSQX(){        //какую позицию будет иметь кубик, если находится в таком положении по x
        return fieldBounds.getX() + getSQX(true) * cellSize + (cellSize - block.getWidth()) / 2f;
    }
    float getPosSQY(){    //какую позицию будет иметь кубик, если находится в таком положении по y
        return fieldBounds.getY() + getSQY(true) * cellSize + (cellSize - block.getWidth()) / 2f;
    }
    ///

    //получить координаты кубика в клетках
    private BigDecimal bigDecimal;
    int getSQX(int direction, float offset){
        float val = block.getX() + block.getWidth() / 2;
        switch (direction){
            case MainBlock.RIGHT:
                val -= block.getWidth() / 2 + offset;
                break;
            case MainBlock.LEFT:
                val += block.getWidth() / 2 - offset;
                break;
        }
        return getSQX(false, val);
    }

    int getSQY(int direction, float offset){
        float val = block.getY() + block.getWidth() / 2;
        switch (direction){
            case MainBlock.BOTTOM:
                val += block.getWidth() / 2 - offset;
                break;
            case MainBlock.TOP:
                val -= block.getWidth() / 2 + offset;
                break;
        }
        return getSQY(false, val);
    }


    public int getSQX(boolean center){
        return getSQX(center, block.getX());
    }
    public int getSQY(boolean center) {
        return getSQY(center, block.getY());
    }
    int getSQX(boolean center, float value){
        final float centerP = center ? block.getWidth() / 2 : 0f;
        final float posXiiP = (value + centerP - fieldBounds.getX()) / (fieldBounds.getWidth() / (LevelGen.SQSIZE + 3));
        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    int getSQY(boolean center, float value){
        final float centerP = center ? block.getWidth() / 2 : 0f;
        final float posYiiP = (value + centerP - fieldBounds.getY()) / (fieldBounds.getWidth() / (LevelGen.SQSIZE + 3));
        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    int getSQX(float value){
        return getSQX(false, value);
    }
    int getSQY(float value){
        return getSQY(false, value);
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
