package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.BlockGenerator;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.InitLevels;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.utils.Timer;

import java.util.Random;

import static com.java4game.cuadro.core.usie.TypeGameBottomBar.*;

/**
 * Created by FOGOK on 02.01.2017 17:31.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class MainBlock extends FieldObject{


    public final static int LEFT = 0, RIGHT = 1, TOP = 2, BOTTOM = 3;
    private final static int BOTTOM_LEFT = 0, TOP_LEFT = 1, TOP_RIGHT = 2, BOTTOM_RIGHT = 3;
    private int direction;  //направление движения кубика
    private boolean isDirectionChanged;  //поворачивали ли мы направление кубика
    private boolean lockChangeInTouch, startChangeDir;

    private float speed, speedCff;
    private Timer timerSpeedChange;
    private boolean isSlow;


    private static final float SPEED_START = 0.055f;
    private float currentRotation, speedRotation = 120f, rotationMax = 180;

    private boolean isRotationStart;
    private boolean isBlockMovedOneSquare;

    private boolean isReversTrued;
    private boolean isNextDirectionTrued, isNextDirectionTrued2;

    private BlockGenerator blockGenerator;
    private LevelGen levelGen;

    private Random rnd = new Random();
    private boolean isRevers;


    //corner = угол поля

    public MainBlock(LevelGen levelGen, Sprite block, Rectangle fieldBounds) {
        super(block, fieldBounds, InitLevels.BLOCK);
        this.levelGen = levelGen;

        this.block.setSize(cellSize * 1.3f, cellSize * 1.3f);
        this.block.setOriginCenter();
        
        timerSpeedChange = new Timer(0f);

        float lowLevelCffSpeed = (StageButton.LEVEL / 25f + 1f) * 1.2f;
        lowLevelCffSpeed = lowLevelCffSpeed > 2f ? 2f : lowLevelCffSpeed;
        speed = StageButton.LEVEL <= 25 ? lowLevelCffSpeed * SPEED_START : SPEED_START * 2f;
        if (MenuUI.TEST || SELECTED_BTN == TYPE_TIMED)
            speed = SPEED_START * 2f;
        isNextDirectionTrued = true;
        isReversTrued = startChangeDir = lockChangeInTouch = isRotationStart = isRevers = false;
        isDirectionChanged = true;
        switch (SELECTED_BTN) {
            case TYPE_STEPS:
            case TYPE_ARKADE:
                setPositionToCorner(rnd.nextBoolean(), rnd.nextInt(4));
                break;
            case TYPE_TIMED:
                setPositionToCorner(false, 0);
                break;
        }
    }

    public void setBlockGenerator(BlockGenerator blockGenerator) {
        this.blockGenerator = blockGenerator;
    }

    private void setPositionToCorner(boolean isRevers, int position){
        this.isRevers = isRevers;
        switch (position){
            case BOTTOM_LEFT:     //bot left
                setSQX(0);
                setSQY(0);
                direction = !isRevers ? TOP : RIGHT;
                break;
            case TOP_LEFT:     //top left
                setSQX(0);
                setSQY(LevelGen.SQSIZE + 2);
                direction = !isRevers ? RIGHT : BOTTOM;
                break;
            case TOP_RIGHT:     //top right
                setSQX(LevelGen.SQSIZE + 2);
                setSQY(LevelGen.SQSIZE + 2);
                direction = !isRevers ? BOTTOM : LEFT;
                break;
            case BOTTOM_RIGHT:     //bot right
                setSQX(LevelGen.SQSIZE + 2);
                setSQY(0);
                direction = !isRevers ? LEFT : TOP;
                break;
        }
        lastSQX = getSQX(true);
        lastSQY = getSQY(true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        handleSpeedCff();
        if (!Handler.ISPAUSE && !LevelGen.ISGAMEOVER)
            blockMove();
//        setDebugText();
    }
    
    private void handleSpeedCff(){
        if (!timerSpeedChange.next()){
            speedCff = isSlow ? 0.5f : 1.5f;
        }else{
            speedCff = 1f;
            block.setColor(Color.WHITE);
        }
    }

    public void setSlow(float time){
        isSlow = true;
        timerSpeedChange.reset(time);
    }

    public void setBoost(float time){
        isSlow = false;
        timerSpeedChange.reset(time);
    }

    private void setDebugText(){
        String dirS = "";
        switch (direction){
            case LEFT:
                dirS = " LEFT";
                break;
            case RIGHT:
                dirS = " RIGHT";
                break;
            case TOP:
                dirS = " TOP";
                break;
            case BOTTOM:
                dirS = " BOTTOM";
                break;

        }
        Gm.DEBUG_VALUE1 = "DIRECTION =" + dirS + " X = " + NCsQX + " Y = " + NCsQY + "\nISREVERS = " + isRevers + "isCornered = " + positionIsCorner();
    }


    private void blockMove(){   ///ездием по кругу
//        float speed * speedCff = !lockChangeInTouch ? this.speed * speedCff : this.speed * speedCff * 0.8f;
        switch (direction){
            case BOTTOM:
                setY(block.getY() - speed * speedCff * Gm.mdT);
                break;
            case TOP:
                setY(block.getY() + speed * speedCff * Gm.mdT);
                break;
            case LEFT:
                setX(block.getX() - speed * speedCff * Gm.mdT);
                break;
            case RIGHT:
                setX(block.getX() + speed * speedCff * Gm.mdT);
                break;
        }

        sQX = getSQX(true);
        sQY = getSQY(true);
        NCsQX = getSQX(direction, 0);
        NCsQY = getSQY(direction, 0);

        if (Gdx.input.justTouched() && (Gm.HEIGHT / Gdx.graphics.getHeight()) * Gdx.input.getY() > 3f){
            if (!lockChangeInTouch)
                startChangeDir = true;
            if (blockGenerator.isStackAvailable())
                revers(false);

        }

        if (startChangeDir && !isDirectionChanged && !positionIsCorner()){
            startChangeDir = false;
            lockChangeInTouch = true;
            nextDirection();
            startRotation();
            isNextDirectionTrued = true;
            isNextDirectionTrued2 = true;
            MusicCore.playSound(11, 0.3f);
            levelGen.minusStep();  //отнимаем один ход
        }


        if (isCornered(cellSize / 2f) || isRotationStart)
            rotationLogic();

        if (isCornered(0) && !isDirectionChanged){
            nextDirection();
            if (lockChangeInTouch){
                levelGen.inspectIsChangeStar();
                blockGenerator.refreshArkObjects();
            }
            isReversTrued = lockChangeInTouch = false;
        }
        else if (isDirectionChanged)
            isDirectionChanged = !(sQX != lastSQX || sQY != lastSQY);

        if (NCsQX != NClastSQX || NCsQY != NClastSQY){
            if (isBlockMovedOneSquare)
                blockGenerator.reversInspect();
        }

        if (sQX != lastSQX || sQY != lastSQY)
            blockGenerator.inspectArkObjectsEffects();


        if (blockGenerator.isStackAvailable()){
            if (!isBlockMovedOneSquare)
                isBlockMovedOneSquare = (sQX != lastSQX || sQY != lastSQY);
        }else
            isBlockMovedOneSquare = false;

        lastSQX = sQX;
        lastSQY = sQY;
        NClastSQX = NCsQX;
        NClastSQY = NCsQY;
    }

    public void blockHasComedHole(boolean isArkade){
        revers(isArkade);
    }
    public void revers(boolean isArkade){
//        isTrueRevers = true;

        if (!isArkade){
            if (!isReversTrued){
                isReversTrued = true;
                blockGenerator.clearStacked(getPosSQX() + block.getWidth() / 2f, getPosSQY() + block.getHeight() / 2f);
                nextDirection();
                nextDirection();    //поворачиваемся на 180 градусов
            }
        }else{
            blockGenerator.clearStacked(getPosSQX() + block.getWidth() / 2f, getPosSQY() + block.getHeight() / 2f);
        }
    }

    public void specialRevers(){
        if (!isReversTrued){
            isReversTrued = true;
            blockGenerator.clearStacked(getPosSQX() + block.getWidth() / 2f, getPosSQY() + block.getHeight() / 2f);
            nextDirectionQ(true);
            nextDirection();    //поворачиваемся на 180 градусов
        }
    }

    private void startRotation(){
        currentRotation = 0f;
        isRotationStart = true;
    }

    private void rotationLogic(){
        if (!isRotationStart){
            startRotation();
        }
        float rotateAngle = speedRotation * speed * speedCff * Gm.mdT;
        if (!isRevers) rotateAngle *= -1f;
        block.rotate(rotateAngle);
        currentRotation += Math.abs(rotateAngle);
        if (currentRotation > rotationMax){
            isRotationStart = false;
            block.setRotation(0f);
        }
    }

    private int lastSQX, lastSQY, NClastSQX, NClastSQY;
    private int sQX, sQY, NCsQX, NCsQY;

    private boolean positionIsCorner(){     //находимся ли мы на краю
        return getSQX(true) == 0 && getSQY(true) == 0 ||
                getSQX(true) == 0 && getSQY(true) == LevelGen.SQSIZE + 2 ||
                getSQX(true) == LevelGen.SQSIZE + 2 && getSQY(true) == 0 ||
                getSQX(true) == LevelGen.SQSIZE + 2 && getSQY(true) == LevelGen.SQSIZE + 2;
    }

    private boolean isCornered(float offset){   //если при следующей итерации мы выходим за край, тогда возвращаем тру, иначе фолс
        switch (direction){
            case BOTTOM: return blockBounds.getY() - speed * speedCff * Gm.mdT < fieldBounds.getY() + offset;
            case TOP: return blockBounds.getY() + blockBounds.getWidth() + speed * speedCff * Gm.mdT > fieldBounds.getY() + fieldBounds.getWidth() - offset;
            case LEFT: return blockBounds.getX() - speed * speedCff * Gm.mdT < fieldBounds.getX() + offset;
            case RIGHT: return blockBounds.getX() + blockBounds.getWidth() + speed * speedCff * Gm.mdT > fieldBounds.getX() + fieldBounds.getWidth() - offset;
            default: return false;
        }
    }

    public void nextDirectionQ(boolean ssRevers){
        if (isNextDirectionTrued){
            blockHasComedHole(true);
            nextDirection(ssRevers, false);
            isNextDirectionTrued = false;
        }
    }

    public void nextDirectionN(boolean isRevDir){
        if (isNextDirectionTrued2){
            blockHasComedHole(true);
            nextDirection(false, isRevDir);
            isNextDirectionTrued2 = false;
        }
    }

    private void nextDirection(){
        nextDirection(false, false);
    }

    private void nextDirection(boolean ssRevers, boolean isRevDir){
        if (ssRevers)
            isRevers = !isRevers;

        switch (direction){
            case TOP:
                direction = !isRevDir ? !isRevers ? RIGHT : LEFT : isRevers ? RIGHT : LEFT;
//                setSQY(LevelGen.SQSIZE + 2);
//                setPositionToCorner(isRevers, !isRevers ? TOP_LEFT : TOP_RIGHT);
                break;
            case BOTTOM:
                direction = !isRevDir ? !isRevers ? LEFT : RIGHT : isRevers ? LEFT : RIGHT;
//                setSQY(0);
//                setPositionToCorner(isRevers, !isRevers ? BOTTOM_RIGHT : BOTTOM_LEFT);
                break;
            case RIGHT:
                direction = !isRevDir ? !isRevers ? BOTTOM : TOP : isRevers ? BOTTOM : TOP;
//                setSQX(LevelGen.SQSIZE + 2);
//                setPositionToCorner(isRevers, !isRevers ? TOP_RIGHT : BOTTOM_RIGHT);
                break;
            case LEFT:
                direction = !isRevDir ? !isRevers ? TOP : BOTTOM : isRevers ? TOP : BOTTOM;
//                setSQX(0);
//                setPositionToCorner(isRevers, !isRevers ? BOTTOM_LEFT : TOP_LEFT);
                break;
        }
        isDirectionChanged = true;
        setSQX(getSQX(true));
        setSQY(getSQY(true));
    }

    public int getDirection() {
        return direction;
    }

    public boolean isRevers() {
        return isRevers;
    }

    public float getSpeed() {
        return speed * speedCff;
    }

    public float getCellSize() {
        return cellSize;
    }

    ///                     trash
//    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
//            block.setX(block.getX() - speed * speedCff * Gm.mdT);
//
//    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//            block.setX(block.getX() + speed * speedCff * Gm.mdT);
//
//    if (Gdx.input.isKeyPressed(Input.Keys.UP))
//            block.setY(block.getY() + speed * speedCff * Gm.mdT);
//
//    if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
//            block.setY(block.getY() - speed * speedCff * Gm.mdT);
//
//    Gm.DEBUG_VALUE1 = "X: " + getSQX() + " Y: " + getSQY();
}
