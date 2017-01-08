package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by FOGOK on 03.01.2017 16:00.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Block extends FieldObject{

    private Rectangle mainBlockBounds;
    private boolean stacked;
    private int stackedPosition;
    private boolean isHoled;

    private int type;


    private MainBlock mainBlock;
    private FieldObject fieldObjects[]; //ccылка на все объекты

    public Block(int type, Sprite block, float customSize, int x, int y, Rectangle fieldBounds, MainBlock mainBlock, FieldObject[] fieldObjects) {
        super(block, fieldBounds, true);
        this.block.setSize(customSize, customSize);

        this.fieldObjects = fieldObjects;

        this.mainBlock = mainBlock;
        mainBlockBounds = mainBlock.getBlockBounds();

        this.type = type;
        isHoled = stacked = false;

        setSQPos(x, y);
    }

    public void setStacked(int stackedPosition){
        this.stackedPosition = stackedPosition;
        stacked = true;
    }

    public void clearStacked(){
        stacked = false;
        final int sqX = getSQX(true);
        final int sqY = getSQY(true);
        setSQX(sqX);
        setSQY(sqY);
        setIsHoledVar(sqX, sqY);
    }

    private void setIsHoledVar(int sqX, int sqY){
        isHoled = false;
        for (int i = 0; i < fieldObjects.length; i++) {
            if (!fieldObjects[i].isCube() && fieldObjects[i].getSQX(true) == sqX && fieldObjects[i].getSQY(true) == sqY){
                if (((Hole)fieldObjects[i]).getType() == type){
                    isHoled = true;
                    break;
                }
            }
        }
    }

    public boolean isHoled() {
        return isHoled;
    }

    public boolean isStacked(){
        return stacked;
    }

    @Override
    public void draw(SpriteBatch batch){
        handleMove();
        super.draw(batch);
    }

    private void handleMove(){
        if (stacked){
            switch (mainBlock.getDirection()){
                case MainBlock.TOP:
                    setBoundsPos(mainBlockBounds.getX(), mainBlockBounds.getY() + cellSize * stackedPosition);
                    break;
                case MainBlock.BOTTOM:
                    setBoundsPos(mainBlockBounds.getX(), mainBlockBounds.getY() - cellSize * stackedPosition);
                    break;
                case MainBlock.LEFT:
                    setBoundsPos(mainBlockBounds.getX() - cellSize * stackedPosition, mainBlockBounds.getY());
                    break;
                case MainBlock.RIGHT:
                    setBoundsPos(mainBlockBounds.getX() + cellSize * stackedPosition, mainBlockBounds.getY());
                    break;
            }
        }
    }
}
