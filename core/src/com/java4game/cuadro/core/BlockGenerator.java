package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.Block;
import com.java4game.cuadro.objects.FieldObject;
import com.java4game.cuadro.objects.Hole;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.objects.TimerBlock;
import com.java4game.cuadro.utils.Assets;

import static com.java4game.cuadro.core.uiwidgets.StageButton.LEVEL;

/**
 * Created by FOGOK on 03.01.2017 15:59.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class BlockGenerator {

//    private Block blocks[];
    private FieldObject fieldObjects[];
    private int countMinSteps;
    private Rectangle mainBlockBounds, stackedTrain, dotq;
    private int stackedCount;
    private MainBlock mainBlock;

    private float cornTrainX, cornTrainY;

    private boolean isEndLevel;

    private boolean isCleanedCollision; //это значит, что мы ждём, пока не будет ни одной коллизии с блоком

    private float cellSize;

    private Rectangle fieldBounds;
    private StarBlock starBlock;
    private TimerBlock timerBlockBlock;
    private LevelGen levelGen;

    public BlockGenerator(LevelGen levelGen, MainBlock mainBlock, Rectangle fieldBounds, InitLevels.Level level) {
        this.mainBlock = mainBlock;
        this.fieldBounds = fieldBounds;
        this.levelGen = levelGen;

        mainBlockBounds = mainBlock.getBlockBounds();
        cellSize = mainBlock.getCellSize();

        dotq = new Rectangle(0f, 0f, Gm.HEIGHT / Gdx.graphics.getHeight(), Gm.HEIGHT / Gdx.graphics.getHeight());

        stackedTrain = new Rectangle(mainBlockBounds);
        cornTrainX = cornTrainY = stackedCount = 0;

        isCleanedCollision = true;

        isEndLevel = false;

        fieldObjects = new FieldObject[level.getObjects().length];
        for (int i = 0; i < fieldObjects.length; i++) {
            switch (level.getObjects()[i].getBlockType()){
                case InitLevels.BLOCK:
                    fieldObjects[i] = new Block(level.getObjects()[i].getType(), Assets.getNewSprite(7 + level.getObjects()[i].getType()),
                            cellSize * 1.3f, level.getObjects()[i].getX(), level.getObjects()[i].getY(),
                            fieldBounds, mainBlock, fieldObjects);
                    ((Block)fieldObjects[i]).setBlockGenerator(this);
                    break;
                case InitLevels.HOLE:
                    fieldObjects[i] = new Hole(Assets.getNewSprite(13 + level.getObjects()[i].getType()), fieldBounds,
                            level.getObjects()[i].getX(), level.getObjects()[i].getY(), level.getObjects()[i].getType());
                    break;
            }
        }
        calculateCountMinSteps();
    }

    public void setStarBlock(StarBlock starBlock) {
        this.starBlock = starBlock;
    }

    public void setTimerBlockBlock(TimerBlock timerBlockBlock) {
        this.timerBlockBlock = timerBlockBlock;
    }

    private void calculateCountMinSteps(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                countMinSteps += isOneLineInHoles(fieldObjects[i].getSQX(true), fieldObjects[i].getSQY(true), ((Block)fieldObjects[i]).getType()) ? 1 : 2;
            }
        }
    }

    private boolean isOneLineInHoles(int x, int y, int type){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() != InitLevels.BLOCK){
                if (((Hole)fieldObjects[i]).getType() == type){
                    if (x == fieldObjects[i].getSQX(true) || y == fieldObjects[i].getSQY(true))
                        return true;
                }
            }
        }
        return false;
    }

    public void drawHoles(SpriteBatch batch){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() != InitLevels.BLOCK)
                fieldObjects[i].draw(batch);
        }
    }

    public void draw(SpriteBatch batch){
        handleStackedTrain();

        if (isCornered(cellSize) && isStackAvailable())
            mainBlock.revers();

        if (isCleanedCollision)
            handleLogic();
        else
            waitToNoCollision();

        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK)
                fieldObjects[i].draw(batch);
        }

        if (isEndLevel){
            isEndLevel = false;
            levelGen.win(LEVEL - 1);
        }

//        dotq.setPosition(cornTrainX, cornTrainY);
//
//        DebugDrawer.drawRect(batch, dotq);
//        DebugDrawer.drawRect(batch, stackedTrain);
    }



    public void setAlpha(float alpha){
        for (int i = 0; i < fieldObjects.length; i++) {
            fieldObjects[i].setAlpha(alpha);
        }
    }

    public void reversInspect(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (((Block)fieldObjects[i]).isStacked() && ((Block)fieldObjects[i]).isHoled() && (!isOtherBlockTargetTypeAvailable(((Block)fieldObjects[i]).getType(), i) || !isNextTypeEqCurrentType())){
                    mainBlock.blockHasComedHole();
                }
            }
        }
    }

    private boolean isOtherBlockTargetTypeAvailable(int targetType, int targetCube){   ///есть ли в стаке ещё такие блоки, как этот и в лунках ли они
        for (int i = 0; i < fieldObjects.length; i++) {
            if (i != targetCube){
                if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                    if (((Block)fieldObjects[i]).isStacked() && !((Block)fieldObjects[i]).isHoled() && ((Block)fieldObjects[i]).getType() == targetType){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isNextTypeEqCurrentType(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (((Block)fieldObjects[i]).isStacked() && ((Block)fieldObjects[i]).getStackedPosition() == stackedCount)
                    return nextTypeEqualsCurrentType(i, true);
            }
        }
        return false;
    }


    private boolean nextTypeEqualsCurrentType(int currentBlock, boolean isOffset){
        int nextX = 0, nextY = 0;
        if (isOffset){
            switch (mainBlock.getDirection()){
                case MainBlock.TOP:
                    nextY++;
                    break;
                case MainBlock.BOTTOM:
                    nextY--;
                    break;
                case MainBlock.RIGHT:
                    nextX++;
                    break;
                case MainBlock.LEFT:
                    nextX--;
                    break;
            }
        }
        int currentX = fieldObjects[currentBlock].getSQX(true);
        int currentY = fieldObjects[currentBlock].getSQY(true);
        int currentType = ((Block)fieldObjects[currentBlock]).getType();
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() != InitLevels.BLOCK && fieldObjects[i].getSQX(true) == currentX + nextX
                                          && fieldObjects[i].getSQY(true) == currentY + nextY){
                if (currentType == ((Hole)fieldObjects[i]).getType())
                    return true;
            }
        }
        return false;
    }

    public void clearStacked(float x, float y){
        stackedCount = 0;
        isCleanedCollision = false;
        stackedTrain.set(mainBlockBounds);
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK)
                ((Block)fieldObjects[i]).clearStacked(x, y);
        }
        inspectHolesAndBlocks();
    }

    private void inspectHolesAndBlocks(){
        isEndLevel = true;
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (!((Block)fieldObjects[i]).isHoled()){
                    isEndLevel = false;
                    break;
                }
            }
        }
    }

    public int getStackedCount() {
        return stackedCount;
    }

    public boolean isStackAvailable(){
        return stackedCount > 0;
    }

    private void waitToNoCollision(){
        boolean result = false;
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (stackedTrain.overlaps(fieldObjects[i].getBlockBounds())){
                    result = true;
                    break;
                }
            }
        }
        if (!result){
            isCleanedCollision = true;
        }
    }

    private boolean isCornered(float offset){   //если при следующей итерации мы выходим за край, тогда возвращаем тру, иначе фолс
        switch (mainBlock.getDirection()){
            case MainBlock.BOTTOM: return cornTrainY < fieldBounds.getY() + offset;
            case MainBlock.TOP: return cornTrainY > fieldBounds.getY() + fieldBounds.getWidth() - offset;
            case MainBlock.LEFT: return cornTrainX < fieldBounds.getX() + offset;
            case MainBlock.RIGHT: return cornTrainX > fieldBounds.getX() + fieldBounds.getWidth() - offset;
            default: return false;
        }
    }

    private void handleLogic(){  //логика столкновений главного кубика и всех остальных
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (!((Block)fieldObjects[i]).isStacked()){
                    if (fieldObjects[i].getBlockBounds().contains(cornTrainX, cornTrainY)){
                        stackedCount++;
                        MusicCore.playSound(6);
                        ((Block)fieldObjects[i]).setStacked(stackedCount);
                        switch (mainBlock.getDirection()){
                            case MainBlock.TOP:
                                stackedTrain.setHeight(stackedTrain.getHeight() + cellSize);
                                break;
                            case MainBlock.BOTTOM:
                                stackedTrain.setHeight(stackedTrain.getHeight() + cellSize);
                                stackedTrain.setY(stackedTrain.getY() - cellSize);
                                break;
                            case MainBlock.LEFT:
                                stackedTrain.setWidth(stackedTrain.getWidth() + cellSize);
                                stackedTrain.setX(stackedTrain.getX() - cellSize);
                                break;
                            case MainBlock.RIGHT:
                                stackedTrain.setWidth(stackedTrain.getWidth() + cellSize);
                                break;
                        }
                        handleStackedTrain();
                        handleLogic();
                        break;
                    }
                }
            }
        }
    }

    private void setNormalARBounds(boolean topbot){
        if (topbot ? stackedTrain.getWidth() > stackedTrain.getHeight() : stackedTrain.getHeight() > stackedTrain.getWidth()){
            final float w = stackedTrain.getWidth();
            stackedTrain.setWidth(stackedTrain.getHeight());
            stackedTrain.setHeight(w);
        }
    }

    private void handleStackedTrain(){
        switch (mainBlock.getDirection()){
            case MainBlock.TOP:
                setNormalARBounds(true);
                stackedTrain.setPosition(mainBlockBounds.getX(), mainBlockBounds.getY());
                cornTrainX = stackedTrain.getX() + cellSize / 2f;
                cornTrainY = stackedTrain.getY() + stackedTrain.getHeight() + mainBlock.getSpeed() * Gm.mdT;
                break;
            case MainBlock.RIGHT:
                setNormalARBounds(false);
                stackedTrain.setPosition(mainBlockBounds.getX(), mainBlockBounds.getY());
                cornTrainX = stackedTrain.getX() + stackedTrain.getWidth() + mainBlock.getSpeed() * Gm.mdT;
                cornTrainY = stackedTrain.getY() + cellSize / 2f;
                break;
            case MainBlock.BOTTOM:
                setNormalARBounds(true);
                stackedTrain.setPosition(mainBlockBounds.getX(), mainBlockBounds.getY() - stackedTrain.getHeight() + cellSize);
                cornTrainX = stackedTrain.getX() + cellSize / 2f;
                cornTrainY = stackedTrain.getY() - mainBlock.getSpeed() * Gm.mdT;
                break;
            case MainBlock.LEFT:
                setNormalARBounds(false);
                stackedTrain.setPosition(mainBlockBounds.getX() - stackedTrain.getWidth() + cellSize, mainBlockBounds.getY());
                cornTrainX = stackedTrain.getX() - mainBlock.getSpeed() * Gm.mdT;
                cornTrainY = stackedTrain.getY() + cellSize / 2f;
                break;
        }
    }

    public int getCountMinSteps() {
        return countMinSteps;
    }
}
