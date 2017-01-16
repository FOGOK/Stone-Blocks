package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.BlockGenerator;
import com.java4game.cuadro.utils.Assets;

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

    private Sprite blockCompleted;

    private int type;


    private MainBlock mainBlock;
    private FieldObject fieldObjects[]; //ccылка на все объекты
    private BlockGenerator blockGenerator;

    public Block(int type, Sprite block, float customSize, int x, int y, Rectangle fieldBounds, MainBlock mainBlock, FieldObject[] fieldObjects) {
        super(block, fieldBounds, true);
        this.block.setSize(customSize, customSize);

        this.fieldObjects = fieldObjects;

        this.mainBlock = mainBlock;
        mainBlockBounds = mainBlock.getBlockBounds();

        blockCompleted = Assets.getNewSprite(31);
        blockCompleted.setSize(cellSize / 3f, cellSize / 3f);

        this.type = type;
        isHoled = stacked = false;

        setSQPos(x, y);
    }

    public void setStacked(int stackedPosition){
        this.stackedPosition = stackedPosition;
        stacked = true;
    }

    public void setBlockGenerator(BlockGenerator blockGenerator) {
        this.blockGenerator = blockGenerator;
    }

    public void clearStacked(float ssqX, float ssqY){
        if (stacked){
            stacked = false;

            float sqX = ssqX;
            float sqY = ssqY;

            int nextsqX = getSQX(true), nextsqY = getSQY(true);

            switch (mainBlock.getDirection()){
                case MainBlock.TOP:
                    sqY += cellSize * stackedPosition;
                    nextsqY += 1;
                    break;
                case MainBlock.BOTTOM:
                    sqY -= cellSize * stackedPosition;
                    nextsqY -= 1;
                    break;
                case MainBlock.LEFT:
                    sqX -= cellSize * stackedPosition;
                    nextsqX -= 1;
                    break;
                case MainBlock.RIGHT:
                    sqX += cellSize * stackedPosition;
                    nextsqX += 1;
                    break;
            }

            setSQX(getSQX(sqX));
            setSQY(getSQY(sqY));
            setIsHoledVar(getSQX(true), getSQY(true), nextsqX, nextsqY);
        }
    }

    private void setIsHoledVar(int sqX, int sqY, int nextsqX, int nextsqY){
        isHoled = false;
        for (int i = 0; i < fieldObjects.length; i++) {
            if (!fieldObjects[i].isCube() && fieldObjects[i].getSQX(true) == sqX && fieldObjects[i].getSQY(true) == sqY){
                if (((Hole)fieldObjects[i]).getType() == type && (isNextNoHoles(nextsqX, nextsqY) || blockGenerator.getStackedCount() == 1)){
                    isHoled = true;
                    mainBlock.blockHasComeHole();
                    break;
                }
            }
        }
        setIsHoledVarCleaned(sQX, sQY);
    }

    private void setIsHoledVarCleaned(int sqX, int sqY){
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

    private boolean isNextNoHoles(int nextsqX, int nextsqY){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getSQX(true) == nextsqX && fieldObjects[i].getSQY(true) == nextsqY){
                if (!fieldObjects[i].isCube()){
                    if (((Hole)fieldObjects[i]).getType() == type) {
                        return false;
                    }
                }
//                else
//                    result = stackedPosition == blockGenerator.getStackedCount();
            }
        }

        return true;
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
        if (isHoled)
            blockCompleted.draw(batch);
    }

    private int sQX, sQY, lastSQX, lastSQY;
    private float pX, pY, lastPX, lastPY;
    private boolean oldStacked;

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
        sQX = getSQX(true);
        sQY = getSQY(true);
        int nextsqX = sQX, nextsqY = sQY;

        switch (mainBlock.getDirection()){
            case MainBlock.TOP:
                nextsqY += 1;
                break;
            case MainBlock.BOTTOM:
                nextsqY -= 1;
                break;
            case MainBlock.LEFT:
                nextsqX -= 1;
                break;
            case MainBlock.RIGHT:
                nextsqX += 1;
                break;
        }


        if (sQX != lastSQX || sQY != lastSQY || oldStacked != stacked)
            setIsHoledVar(sQX, sQY, nextsqX, nextsqY);
        oldStacked = stacked;
        lastSQX = sQX;
        lastSQY = sQY;

//        pX = mainBlockBounds.getX();
//        pY = mainBlockBounds.getY();
//        if (pX != lastPX || pY != lastPY)
//            setIsHoledVarCleaned(sQX, sQY);
//        lastPX = pX;
//        lastPY = pY;


        blockCompleted.setPosition(block.getX() + cellSize * 0.152f, block.getY() + cellSize * 0.2f);
    }

    public int getType() {
        return type;
    }
}
