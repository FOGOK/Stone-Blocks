package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.ArkadeBlock;
import com.java4game.cuadro.objects.Block;
import com.java4game.cuadro.objects.Bomb;
import com.java4game.cuadro.objects.BoosterSlower;
import com.java4game.cuadro.objects.FieldObject;
import com.java4game.cuadro.objects.Hole;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.Revers;
import com.java4game.cuadro.objects.Rotate90;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Pos;
import com.java4game.cuadro.utils.Prefers;

import java.util.Random;

import static com.java4game.cuadro.core.uiwidgets.StageButton.LEVEL;
import static com.java4game.cuadro.objects.FieldObject.*;

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

    private int targetScore;
    private int oldRecord = 0;

    private boolean isEndLevel;

    public static boolean ISARKADE;

    private boolean isCleanedCollision; //это значит, что мы ждём, пока не будет ни одной коллизии с блоком

    private float cellSize;

    private Rectangle fieldBounds;
    private ArkadeBlock arkadeBlock;
    private LevelGen levelGen;
    private Random rnd = new Random();

    private Pos[] arkadePositions;
    private Pos arkadeVal = new Pos(0, 0);
    private boolean arkadeRefresh;
    private int arkadeCubesAndHolesSize, arkadeCompletePositions;

    private boolean isNewRecord, isScoreMoreTarget, needOpenNextMode;


    public BlockGenerator(LevelGen levelGen, MainBlock mainBlock, Rectangle fieldBounds, InitLevels.Level level, boolean ISARKADE) {
        this.mainBlock = mainBlock;
        this.fieldBounds = fieldBounds;
        this.levelGen = levelGen;
        this.ISARKADE = ISARKADE;

        mainBlockBounds = mainBlock.getBlockBounds();
        cellSize = mainBlock.getCellSize();

        dotq = new Rectangle(0f, 0f, Gm.HEIGHT / Gdx.graphics.getHeight(), Gm.HEIGHT / Gdx.graphics.getHeight());

        stackedTrain = new Rectangle(mainBlockBounds);
        cornTrainX = cornTrainY = stackedCount = 0;

        isCleanedCollision = true;

        isEndLevel = false;


        if (ISARKADE){
            int arkObjectsSize = 4;
            switch (TypeGameButton.TOUCHED_ARK){
                case 0:
                    arkadeCubesAndHolesSize = 2;//2
                    oldRecord = Prefers.getInt(Prefers.KeyRecordBronze);
                    targetScore = MenuUI.ARKADE_TARGET_SILVER;
                    needOpenNextMode = Prefers.getInt(Prefers.KeyOpenedArkadeModes) == 0;
                    break;
                case 1:
                    arkadeCubesAndHolesSize = 6;
                    oldRecord = Prefers.getInt(Prefers.KeyRecordSilver);
                    targetScore = MenuUI.ARKADE_TARGET_GOLD;
                    needOpenNextMode = Prefers.getInt(Prefers.KeyOpenedArkadeModes) == 1;
                    break;
                case 2:
                    arkadeCubesAndHolesSize = 10;
                    oldRecord = Prefers.getInt(Prefers.KeyRecordGold);
                    targetScore = 0;
                    needOpenNextMode = false;
                    break;
            }
            fieldObjects = new FieldObject[arkObjectsSize + arkadeCubesAndHolesSize];
            //randomize arkadePositions
            arkadePositions = new Pos[fieldObjects.length];
            for (int i = 0; i < fieldObjects.length; i++) {
                setValRandomPos();
                arkadePositions[i] = new Pos(arkadeVal);
            }
            ///
            
            ///initTypes
            int types[] = new int[fieldObjects.length];
            for (int i = 0; i < arkadeCubesAndHolesSize; i++) {
                if (i < arkadeCubesAndHolesSize / 2)
                    types[i] = HOLE;
                else
                    types[i] = BLOCK;
            }
            int index = arkadeCubesAndHolesSize;
            types[index++] = rnd.nextBoolean() ? BOOSTER : SLOWER;
            types[index++] = REVERS;
            types[index++] = rnd.nextBoolean() ? ROTATE90 : ROTATEM90;
            types[index++] = BOMB;

            ///

            //initObjects
            int color = 0;
            for (int i = 0; i < fieldObjects.length; i++) {
                if (i == arkadeCubesAndHolesSize / 2)
                    color = 0;

                switch (types[i]){
                    case BLOCK:
                        fieldObjects[i] = new Block(color, Assets.getNewSprite(7 + color),
                                cellSize * 1.3f, arkadePositions[i].x, arkadePositions[i].y,
                                fieldBounds, mainBlock, fieldObjects);
                        color++;
                        break;
                    case HOLE:
                        fieldObjects[i] = new Hole(Assets.getNewSprite(13 + color), fieldBounds,
                                arkadePositions[i].x, arkadePositions[i].y, color);
                        color++;
                        break;
                    case BOOSTER:
                        fieldObjects[i] = new BoosterSlower(fieldBounds, true, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                    case SLOWER:
                        fieldObjects[i] = new BoosterSlower(fieldBounds, false, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                    case REVERS:
                        fieldObjects[i] = new Revers(fieldBounds, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                    case ROTATE90:
                        fieldObjects[i] = new Rotate90(fieldBounds, false, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                    case ROTATEM90:
                        fieldObjects[i] = new Rotate90(fieldBounds, true, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                    case BOMB:
                        fieldObjects[i] = new Bomb(fieldBounds, arkadePositions[i].x, arkadePositions[i].y);
                        break;
                }
            }
            ///
        }else{
            fieldObjects = new FieldObject[level.getObjects().length];
            for (int i = 0; i < fieldObjects.length; i++) {
                switch (level.getObjects()[i].getBlockType()){
                    case BLOCK:
                        fieldObjects[i] = new Block(level.getObjects()[i].getType(), Assets.getNewSprite(7 + level.getObjects()[i].getType()),
                                cellSize * 1.3f, level.getObjects()[i].getX(), level.getObjects()[i].getY(),
                                fieldBounds, mainBlock, fieldObjects);
                        break;
                    case HOLE:
                        fieldObjects[i] = new Hole(Assets.getNewSprite(13 + level.getObjects()[i].getType()), fieldBounds,
                                level.getObjects()[i].getX(), level.getObjects()[i].getY(), level.getObjects()[i].getType());
                        break;
                }
            }
            calculateCountMinSteps();
        }
    }

    private void refreshAllPositions(){
        for (int i = 0; i < fieldObjects.length; i++) {
            refreshOnePosition(i);
        }
    }

    public void refreshArkObjects(){
        if (ISARKADE){
            updateArkadePositions();
            for (int i = 0; i < fieldObjects.length; i++) {
                if (fieldObjects[i].getTypeBlock() != BLOCK && fieldObjects[i].getTypeBlock() != NULLTYPE && fieldObjects[i].getTypeBlock() != HOLE) {
                    refreshOnePosition(i);
                }
            }
        }
    }

    private void updateArkadePositions(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == BLOCK)
                arkadePositions[i].set(fieldObjects[i].getSQX(true), fieldObjects[i].getSQY(true));
        }
    }

    private void refreshOnePosition(int i){
        arkadeRefresh = true;

        if (fieldObjects[i].getTypeBlock() == SLOWER || fieldObjects[i].getTypeBlock() == BOOSTER)
            ((BoosterSlower)fieldObjects[i]).nextBS();

        if (fieldObjects[i].getTypeBlock() == ROTATE90 || fieldObjects[i].getTypeBlock() == ROTATEM90)
            ((Rotate90)fieldObjects[i]).nextBS();

        if (fieldObjects[i].getTypeBlock() == NULLTYPE){
            fieldObjects[i].setTypeBlock(BLOCK);
            ((Block)fieldObjects[i]).setHoleNone();
        }

        setValRandomPos();
        arkadePositions[i] = new Pos(arkadeVal);
        fieldObjects[i].setSQPos(arkadePositions[i].x, arkadePositions[i].y);
        fieldObjects[i].setAlpha(0f);
    }

    public void setArkadeBlock(ArkadeBlock arkadeBlock) {
        this.arkadeBlock = arkadeBlock;
    }

    private void setValRandomPos(){
        arkadeVal.set(1 + rnd.nextInt(8), 1 + rnd.nextInt(8));
        while (!isValNorm(arkadePositions, arkadeVal)){
            arkadeVal.set(1 + rnd.nextInt(8), 1 + rnd.nextInt(8));
        }
    }

    private boolean isValNorm(Pos[] mas, Pos testedVal){
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] != null){
                if (mas[i].equals(testedVal))
                    return false;
            }
        }
        return true;
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

        if (arkadeRefresh){
            arkadeRefresh = false;
            for (int i = 0; i < fieldObjects.length; i++) {
                if (fieldObjects[i].getTypeBlock() != NULLTYPE){
                    if (fieldObjects[i].updateAlpha(0.02f * Gm.mdT, false))
                        arkadeRefresh = true;
                }
            }
        }

        Gm.DEBUG_VALUE2 = arkadeCompletePositions + "";

        if (isCornered(cellSize) && isStackAvailable())
            mainBlock.revers(false);

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
            if (ISARKADE){
                arkadeCompletePositions = 0;
                refreshAllPositions();
            }else{
                levelGen.win(LEVEL - 1);
            }
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
                    mainBlock.blockHasComedHole(ISARKADE);
//                    mainBlock.blockHasComedHole(ISARKADE && arkadeCompletePositions == arkadeCubesAndHolesSize / 2 - 1);
                }
            }
        }
    }

    public void inspectArkObjectsEffects(){
        if (ISARKADE){
            for (int i = 0; i < fieldObjects.length; i++) {
                if (mainBlock.getSQX(true) == fieldObjects[i].getSQX(true) && mainBlock.getSQY(true) == fieldObjects[i].getSQY(true)){
                    switch (fieldObjects[i].getTypeBlock()){
                        case BOOSTER:
                            mainBlock.setBoost(4f);
                            break;
                        case SLOWER:
                            mainBlock.setSlow(4f);
                            break;
                        case REVERS:
                            mainBlock.blockHasComedHole(false);
                            break;
                        case ROTATE90:
                            mainBlock.nextDirectionQ(false);
                            break;
                        case ROTATEM90:
                            mainBlock.nextDirectionQ(true);
                            break;
                        case BOMB:
                            levelGen.arkadeLose(arkadeBlock.getScore(), isNewRecord);
                            break;
                    }
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

        if (ISARKADE){
            for (int i = 0; i < fieldObjects.length; i++) {
                if (fieldObjects[i].getTypeBlock() == BLOCK){
                    if (((Block)fieldObjects[i]).isHoled()){
                        fieldObjects[i].setTypeBlock(NULLTYPE);
                        arkadeCompletePositions++;
                        arkadeBlock.updateScore(3);
                        inspectUpdatedScore();
                        if (isNewRecord){
                            switch (TypeGameButton.TOUCHED_ARK){
                                case 0:
                                    Prefers.putInt(Prefers.KeyRecordBronze, arkadeBlock.getScore());
                                    break;
                                case 1:
                                    Prefers.putInt(Prefers.KeyRecordSilver, arkadeBlock.getScore());
                                    break;
                                case 2:
                                    Prefers.putInt(Prefers.KeyRecordGold, arkadeBlock.getScore());
                                    break;
                            }
                        }
                        if (arkadeCompletePositions == arkadeCubesAndHolesSize / 2)
                            isEndLevel = true;
                    }
                }
            }
        }
    }

    private void inspectUpdatedScore(){
        if (arkadeBlock.getScore() > oldRecord){
            if (!isNewRecord){
                isNewRecord = true;
                if (oldRecord != 0)
                    levelGen.startNewRecord(false);
            }
        }

        if (needOpenNextMode){
            if (arkadeBlock.getScore() >= targetScore && targetScore != 0){
                if (!isScoreMoreTarget){
                    isScoreMoreTarget = true;
                    levelGen.startNewRecord(true);
                    Prefers.putInt(Prefers.KeyOpenedArkadeModes, Prefers.getInt(Prefers.KeyOpenedArkadeModes) + 1);
                }
            }
        }
    }

    private void inspectHolesAndBlocks(){
        if (!ISARKADE)
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
