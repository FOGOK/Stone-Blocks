package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 28.04.2017 14:20.
 */

public class FieldLearnLine {

    private Sprite partLine, greenBlock;

    private int offsetXY;   // from 0 to 9
    private boolean isVertical, isShow, isReversStartBlock, isShowBlockT;
    private Rectangle fieldBounds;
    private float cellSize;
    private int otst;

    private int x1, y1, x2, y2;

    public FieldLearnLine(Rectangle fieldBounds) {
        this.fieldBounds = fieldBounds;

        cellSize = fieldBounds.getWidth() / (LevelGen.SQSIZE + 3);

        greenBlock = Assets.getNewSprite(103);
        greenBlock.setSize(cellSize, cellSize);

        partLine = Assets.getNewSprite(104);
        partLine.setSize(cellSize, cellSize);
    }

    public void setParams(boolean isVertical, boolean isReversStartBlock, int offsetXY, boolean isShow){
        this.isVertical = isVertical;
        this.offsetXY = offsetXY;
        this.isShow = isShow;
        this.isReversStartBlock = isReversStartBlock;
        otst = 0;
        isShowBlockT = true;
        int offsetSecond = isReversStartBlock ? LevelGen.SQSIZE + 2 : 0;
        if (isVertical){
            setPositionSprite(offsetXY, offsetSecond, greenBlock);
            x1 = offsetXY;
            y1 = offsetSecond;
        }
        else{
            setPositionSprite(offsetSecond, offsetXY, greenBlock);
            x1 = offsetSecond;
            y1 = offsetXY;
        }
    }

    public void setOtst(int otst) {
        this.otst = otst;
    }

    public void setShowBlockT(boolean showBlockT) {
        isShowBlockT = showBlockT;
    }

    public void draw(SpriteBatch batch){
        if (isShow){
            if (isShowBlockT)
                greenBlock.draw(batch);

            int one = isReversStartBlock ? otst : 0;
            int second = isReversStartBlock ? 0 : otst;
            for (int i = 1 + one; i < (LevelGen.SQSIZE + 2) - second; i++) {
                if (isVertical)
                    setPositionSprite(offsetXY, i, partLine);
                else
                    setPositionSprite(i, offsetXY, partLine);
                partLine.draw(batch);
            }
        }
    }

    public Rectangle getFieldBounds() {
        return fieldBounds;
    }

    public float getCellSize() {
        return cellSize;
    }

    private void setPositionSprite(int iX, int iY, Sprite block){        //устанавливаем x на определённую клетку внутри поля
        block.setX(fieldBounds.getX() + iX * cellSize + (cellSize - block.getWidth()) / 2f);
        block.setY(fieldBounds.getY() + iY * cellSize + (cellSize - block.getWidth()) / 2f);
    }

    public boolean greenBlockCollide(MainBlock mainBlock){
        return mainBlock.getSQX(true) == x1 && mainBlock.getSQY(true) == y1;
    }


}
