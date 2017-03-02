package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.utils.Assets;

import java.util.Random;

import static com.java4game.cuadro.objects.MainBlock.*;

/**
 * Created by FOGOK on 27.02.2017 16:54.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Rotate90 extends FieldObject {

    private final int top = 67, bot = 68, left = 69, right = 70;
    private int direction;

    private MainBlock mainBlock;
    public Rotate90(Rectangle fieldBounds, boolean isMRotate, int x, int y, MainBlock mainBlock) {
        super(Assets.getNewSprite(67), fieldBounds, !isMRotate ? ROTATE90 : ROTATEM90);
        this.mainBlock = mainBlock;
        block.setFlip(false, false);
        setSQPos(x, y);
        setDirection(true);
    }
    private int currReg = 0;
    private void setNewReg(int reg){
        TextureRegion textureRegion = Assets.getTextureAtlas().findRegion(reg + "");
        block.setRegion(textureRegion.getU(), textureRegion.getV(), textureRegion.getU2(), textureRegion.getV2());
        currReg = reg;
    }


    public void setDirection(boolean isRandom){
        direction = isRandom ? new Random().nextInt(4) : direction;
        int reversA;
        if (typeBlock == ROTATEM90)
            reversA = mainBlock.isRevers() ? 0 : 4;
        else
            reversA = mainBlock.isRevers() ? 4 : 0;


        switch (direction){
            case LEFT:
                setNewReg(left + reversA);
                break;
            case RIGHT:
                setNewReg(right + reversA);
                break;
            case TOP:
                setNewReg(top + reversA);
                break;
            case BOTTOM:
                setNewReg(bot + reversA);
                break;
        }


    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void nextBS(){
        if (typeBlock == ROTATE90)
            typeBlock = ROTATEM90;
        else
            typeBlock = ROTATE90;
        setDirection(true);
    }

}
