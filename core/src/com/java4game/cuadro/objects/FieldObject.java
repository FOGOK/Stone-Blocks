package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
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

    public static final int BLOCK = 0, HOLE = 1, BOOSTER = 2, SLOWER = 3, REVERS = 4, ROTATE90 = 5, ROTATEM90 = 6, BOMB = 7, NULLTYPE = 8, TELEPORT = 9, TIMER_ADD = 10,
            MOVER_BONUS = 11, HIDED = 12;

    Sprite block;
    Rectangle blockBounds;
    Rectangle fieldBounds;
    float cellSize;  //размер ячейки без отступов // (т.е. тупое деление размера поля на количество клеток)
    private Interpolation blockAnim = Interpolation.elasticOut;
    private boolean isReversAlpha;
    int typeBlock;

    FieldObject(Sprite block, Rectangle fieldBounds, int typeBlock) {
        this.block = block;
        this.typeBlock = typeBlock;
        this.fieldBounds = fieldBounds;
        cellSize = fieldBounds.getWidth() / (LevelGen.SQSIZE + 3);
        blockBounds = new Rectangle();
        blockBounds.setSize(cellSize);
        block.setSize(cellSize, cellSize);
        if (typeBlock == BLOCK)
            block.setOrigin(block.getWidth() * 0.589f, block.getHeight() * 0.679f);
        else
            block.setOriginCenter();
    }

    public void draw(SpriteBatch batch){
        block.draw(batch);
    }

    public void setSQPos(int iX, int iY){
        setSQX(iX);
        setSQY(iY);
    }

    public void setAlpha(float alpha){
        block.setAlpha(alpha);
        if (!isReversAlpha)
            block.setScale(blockAnim.apply(alpha, 1f, alpha / 1f));
        else
            block.setScale(alpha / 1f);
    }

    public void setReversAlpha(boolean reversAlpha) {
        isReversAlpha = reversAlpha;
    }

    public boolean updateAlpha(float f, boolean revers){
        if (revers){
            isReversAlpha = true;
            if (block.getColor().a - f >= 0f){
                setAlpha(block.getColor().a - f);
                return true;
            }else
                return false;
        }else{
            isReversAlpha = false;
            if (block.getColor().a + f <= 1f){
                setAlpha(block.getColor().a + f);
                return true;
            }else
                return false;
        }
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
                val += block.getWidth() / 2 + offset;
                break;
            case MainBlock.LEFT:
                val -= block.getWidth() / 2 - offset;
                break;
        }
        return getSQX(false, val);
    }

    int getSQY(int direction, float offset){
        float val = block.getY() + block.getWidth() / 2;
        switch (direction){
            case MainBlock.BOTTOM:
                val -= block.getWidth() / 2 - offset;
                break;
            case MainBlock.TOP:
                val += block.getWidth() / 2 + offset;
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
        return (int) posXiiP;
//        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
//        return bigDecimal.intValue();
    }
    int getSQY(boolean center, float value){
        final float centerP = center ? block.getWidth() / 2 : 0f;
        final float posYiiP = (value + centerP - fieldBounds.getY()) / (fieldBounds.getWidth() / (LevelGen.SQSIZE + 3));
        return (int) posYiiP;
//        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
//        return bigDecimal.intValue();
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

    public float getCenterFloatX(){
        return block.getX() + block.getWidth() / 2f;
    }

    public float getCenterFloatY(){
        return block.getY() + block.getHeight() / 2f;
    }

    public int getTypeBlock() {
        return typeBlock;
    }

    public void setTypeBlock(int typeBlock) {
        this.typeBlock = typeBlock;
    }
}
