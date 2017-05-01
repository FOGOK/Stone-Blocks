package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.RobotHead;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.ArkadeBlock;
import com.java4game.cuadro.objects.Block;
import com.java4game.cuadro.objects.Bomb;
import com.java4game.cuadro.objects.BoosterSlower;
import com.java4game.cuadro.objects.FieldLearnLine;
import com.java4game.cuadro.objects.FieldObject;
import com.java4game.cuadro.objects.Hole;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.MoverBonus;
import com.java4game.cuadro.objects.Revers;
import com.java4game.cuadro.objects.Rotate90;
import com.java4game.cuadro.objects.Teleport;
import com.java4game.cuadro.objects.TimerAddBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Pos;
import com.java4game.cuadro.utils.Prefers;

import java.util.Random;

import static com.java4game.cuadro.core.uiwidgets.StageButton.LEVEL;
import static com.java4game.cuadro.objects.FieldObject.BLOCK;
import static com.java4game.cuadro.objects.FieldObject.BOMB;
import static com.java4game.cuadro.objects.FieldObject.BOOSTER;
import static com.java4game.cuadro.objects.FieldObject.HOLE;
import static com.java4game.cuadro.objects.FieldObject.MOVER_BONUS;
import static com.java4game.cuadro.objects.FieldObject.NULLTYPE;
import static com.java4game.cuadro.objects.FieldObject.REVERS;
import static com.java4game.cuadro.objects.FieldObject.ROTATE90;
import static com.java4game.cuadro.objects.FieldObject.ROTATEM90;
import static com.java4game.cuadro.objects.FieldObject.SLOWER;
import static com.java4game.cuadro.objects.FieldObject.TELEPORT;
import static com.java4game.cuadro.objects.FieldObject.TIMER_ADD;
import static com.java4game.cuadro.objects.MainBlock.BOTTOM;
import static com.java4game.cuadro.objects.MainBlock.LEFT;
import static com.java4game.cuadro.objects.MainBlock.RIGHT;
import static com.java4game.cuadro.objects.MainBlock.TOP;

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

    private RobotHead robotHead;
    private FieldLearnLine firstFieldLearnLine, secondFieldLearnLine;

    private float cornTrainX, cornTrainY;

    private int targetScore;
    private int oldRecord = 0;
    private int counterBlocksDrivedOut = 0;

    private boolean isEndLevel;

    public static boolean ISARKADE, ISRANDOM;

    private boolean isCleanedCollision; //это значит, что мы ждём, пока не будет ни одной коллизии с блоком

    private float cellSize;

    private int[] refFiskIndexes, refBlockIndexes, refHoleIndexes, refDeletedHoles;
    private int refFiskCount, refBlockAndHoleCount;

    private Rectangle fieldBounds;
    private ArkadeBlock arkadeBlock;
    private LevelGen levelGen;
    private Random rnd = new Random();

    private Pos[] arkadeAndRandomPositions;
    private Pos arkadeVal = new Pos(0, 0);
    private boolean arkadeRefresh;
    private int arkadeCubesAndHolesSize, arkadeCompletePositions;

    private boolean isNewRecord, isScoreMoreTarget, needOpenNextMode;


    public BlockGenerator(LevelGen levelGen, MainBlock mainBlock, Rectangle fieldBounds, InitLevels.Level level, int MODE) {
        this.mainBlock = mainBlock;
        this.fieldBounds = fieldBounds;
        this.levelGen = levelGen;

        ISARKADE = MODE == 1;
        ISRANDOM = MODE == 2;

        mainBlockBounds = mainBlock.getBlockBounds();
        cellSize = mainBlock.getCellSize();

        dotq = new Rectangle(0f, 0f, Gm.HEIGHT / Gdx.graphics.getHeight(), Gm.HEIGHT / Gdx.graphics.getHeight());

        stackedTrain = new Rectangle(mainBlockBounds);
        cornTrainX = cornTrainY = stackedCount = 0;

        isCleanedCollision = true;

        isEndLevel = false;

        if (ISARKADE){
            int arkObjectsSize = 7;
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
            refFiskIndexes = new int[fieldObjects.length];
            refBlockIndexes = new int[fieldObjects.length];
            refHoleIndexes = new int[fieldObjects.length];
            refDeletedHoles = new int[fieldObjects.length];

            //randomize arkadeAndRandomPositions
            arkadeAndRandomPositions = new Pos[fieldObjects.length];
            for (int i = 0; i < fieldObjects.length; i++) {
//                switch (i){
//                    case 4:
//                        arkadeVal.set(2, 1);
//                        break;
//                    case 5:
//                        arkadeVal.set(2, 2);
//                        break;
//                    case 6:
//                        arkadeVal.set(2, 3);
//                        break;
//                    case 7:
//                        arkadeVal.set(2, 4);
//                        break;
//                    case 8:
//                        arkadeVal.set(2, 5);
//                        break;
//                    case 9:
//                        arkadeVal.set(2, 6);
//                        break;
//                    default:
                        setValRandomPos();
//                        break;
//                }
                arkadeAndRandomPositions[i] = new Pos(arkadeVal);
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
            types[index++] = TELEPORT;
            types[index++] = TIMER_ADD;
            types[index++] = MOVER_BONUS;

            ///

            //initObjects
            for (int i = 0; i < fieldObjects.length; i++) {
                int color;
                switch (types[i]){
                    case BLOCK:
                        color = ((Hole)fieldObjects[i - arkadeCubesAndHolesSize / 2]).getType();
                        fieldObjects[i] = new Block(color, Assets.getNewSprite(7 + color),
                                cellSize * 1.3f, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y,
                                fieldBounds, mainBlock, fieldObjects);
                        break;
                    case HOLE:
//                        color = rnd.nextInt(5);
                        color = 2;
                        fieldObjects[i] = new Hole(Assets.getNewSprite(13 + color), fieldBounds,
                                arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y, color);
                        break;
                    case BOOSTER:
                        fieldObjects[i] = new BoosterSlower(fieldBounds, true, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        break;
                    case SLOWER:
                        fieldObjects[i] = new BoosterSlower(fieldBounds, false, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        break;
                    case REVERS:
                        fieldObjects[i] = new Revers(fieldBounds, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        break;
                    case ROTATE90:
                        fieldObjects[i] = new Rotate90(fieldBounds, false, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y, mainBlock);
                        break;
                    case ROTATEM90:
                        fieldObjects[i] = new Rotate90(fieldBounds, true, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y, mainBlock);
                        break;
                    case BOMB:
                        fieldObjects[i] = new Bomb(fieldBounds, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        break;
                    case TELEPORT:
                        fieldObjects[i] = new Teleport(fieldBounds, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        break;
                    case TIMER_ADD:
                        fieldObjects[i] = new TimerAddBlock(fieldBounds, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        ((TimerAddBlock)fieldObjects[i]).setShowing(false);
                        break;
                    case MOVER_BONUS:
                        fieldObjects[i] = new MoverBonus(fieldBounds, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
                        ((MoverBonus)fieldObjects[i]).setShowing(false);
                        break;
                }
            }
            ///
        }else{
            if (ISRANDOM){
                int objectsCount = 0;
                switch (TypeGameButton.RNDLEVEL){
                    case 0:
                        objectsCount = 6;
                        break;
                    case 1:
                        objectsCount = 10;
                        break;
                    case 2:
                        objectsCount = 14;
                        break;
                }

                fieldObjects = new FieldObject[objectsCount];

                arkadeAndRandomPositions = new Pos[fieldObjects.length];
                for (int i = 0; i < fieldObjects.length; i++) {
                    setValRandomPos();
                    arkadeAndRandomPositions[i] = new Pos(arkadeVal);
                }

                int color = 0;
                for (int i = 0; i < fieldObjects.length; i++) {
                    if (i < fieldObjects.length / 2){
                        color = rnd.nextInt(5);
                        fieldObjects[i] = new Hole(Assets.getNewSprite(13 + color), fieldBounds,
                                arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y, color);
                    }else{
                        color = ((Hole)fieldObjects[i - fieldObjects.length / 2]).getType();
                        fieldObjects[i] = new Block(color, Assets.getNewSprite(7 + color),
                                cellSize * 1.3f, arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y,
                                fieldBounds, mainBlock, fieldObjects);
                    }
                }

            }else{
                fieldObjects = new FieldObject[level.getObjects().length];
                for (int i = 0; i < fieldObjects.length; i++) {
                    switch (level.getObjects()[i].getBlockType()){
                        case BLOCK:
                            fieldObjects[i] = new Block(level.getObjects()[i].getType(), Assets.getNewSprite(7 + level.getObjects()[i].getType()),
                                    cellSize * 1.3f, level. getObjects()[i].getX(), level.getObjects()[i].getY(),
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
    }

    public void setRobotHead(RobotHead robotHead) {
        this.robotHead = robotHead;
    }

    private DialogSystem dialogSystem;
    public void learningStart() {
        dialogSystem = new DialogSystem(robotHead, this);
        dialogSystem.setFieldObjects(fieldObjects);
        mainBlock.setDialogSystem(dialogSystem);
        firstFieldLearnLine = new FieldLearnLine(fieldBounds);
        secondFieldLearnLine = new FieldLearnLine(fieldBounds);
        dialogSystem.setFirstFieldLearnLine(firstFieldLearnLine);
        dialogSystem.setSecondFieldLearnLine(secondFieldLearnLine);
        dialogSystem.setMainBlock(mainBlock);

        dialogSystem.startLearning();
    }

    public void refreshArkObjects(){
        if (ISARKADE && !DialogSystem.ISLEARNING){
            if (refBlockAndHoleCount != 0){

                updateArkadePositions();

                for (int i = 0; i < refBlockAndHoleCount; i++) {
                    refreshOnePosition(refBlockIndexes[i]);
                    refreshOnePosition(refHoleIndexes[i]);
                }


                refreshFishk();

                refBlockAndHoleCount = 0;
            }else if (refFiskCount != 0){

                updateArkadePositions();

                for (int i = 0; i < refFiskCount; i++) {
                    refreshOnePosition(refFiskIndexes[i]);
                }

                refFiskCount = 0;
            }
            if (counterBlocksDrivedOut > 0){
                mainBlock.normalizeSpeed();
                if (counterBlocksDrivedOut > 1 && rnd.nextBoolean()){
                    for (int j = 0; j < fieldObjects.length; j++) {
                        if (fieldObjects[j].getTypeBlock() == MOVER_BONUS)
                            ((MoverBonus)fieldObjects[j]).setShowing(true);
                    }
                }

            }
            counterBlocksDrivedOut = 0;
        }
    }

    private void refreshFishk(){
        if (ISARKADE){
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
                arkadeAndRandomPositions[i].set(fieldObjects[i].getSQX(true), fieldObjects[i].getSQY(true));
        }
    }

    public void arkadeRefresh() {
        arkadeRefresh = true;
    }

    private int timeredColorThisMethod = 0;
    private void refreshOnePosition(int i){
        arkadeRefresh = true;

        if (fieldObjects[i].getTypeBlock() == SLOWER || fieldObjects[i].getTypeBlock() == BOOSTER)
            ((BoosterSlower)fieldObjects[i]).nextBS();

        if (fieldObjects[i].getTypeBlock() == ROTATE90 || fieldObjects[i].getTypeBlock() == ROTATEM90)
            ((Rotate90)fieldObjects[i]).nextBS();


        if (fieldObjects[i].getTypeBlock() == NULLTYPE){
            fieldObjects[i].setTypeBlock(BLOCK);
            ((Block)fieldObjects[i]).setHoleNone();
            timeredColorThisMethod = rnd.nextInt(5);
            ((Block)fieldObjects[i]).setColor(timeredColorThisMethod);
        }

        if (fieldObjects[i].getTypeBlock() == HOLE){
            ((Hole)fieldObjects[i]).setColor(timeredColorThisMethod);
        }

        setValRandomPos();
        arkadeAndRandomPositions[i] = new Pos(arkadeVal);
        fieldObjects[i].setSQPos(arkadeAndRandomPositions[i].x, arkadeAndRandomPositions[i].y);
        fieldObjects[i].setAlpha(0f);
    }

    public void setArkadeBlock(ArkadeBlock arkadeBlock) {
        this.arkadeBlock = arkadeBlock;
    }

    private void setValRandomPos(){
        arkadeVal.set(1 + rnd.nextInt(8), 1 + rnd.nextInt(8));
        while (!isValNorm(arkadeAndRandomPositions, arkadeVal)){
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
        if (firstFieldLearnLine != null){
            firstFieldLearnLine.draw(batch);
            secondFieldLearnLine.draw(batch);
        }

        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() != InitLevels.BLOCK && fieldObjects[i].getTypeBlock() != FieldObject.HIDED)
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

        if (DialogSystem.ISLEARNING)
            dialogSystem.handle();

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

        if (isEndLevel && !DialogSystem.ISLEARNING){
            isEndLevel = false;
            if (ISARKADE){
                arkadeCompletePositions = 0;
            }else{
                levelGen.win(LEVEL);
            }
        }
    }



    public void setAlpha(float alpha){
        for (int i = 0; i < fieldObjects.length; i++) {
            fieldObjects[i].setAlpha(alpha);
        }
    }

    public void reversInspect(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (((Block)fieldObjects[i]).isStacked() && ((Block)fieldObjects[i]).isHoled() && (!isOtherBlockTargetTypeAvailable(((Block)fieldObjects[i]).getType(), i) || !isNextTypeEqCurrentType()))
                    mainBlock.blockHasComedHole(ISARKADE);
            }
        }
    }

    public void inspectArkObjectsEffects(){
        if (ISARKADE){
            int mult = 0;
//            if (mainBlock.isBoost())
//                mult = 1;
//            else if (mainBlock.isRotated())
//                mult = 2;
//            else if (mainBlock.isRotated() && mainBlock.isBoost())
//                mult = 3;

            for (int i = 0; i < fieldObjects.length; i++) {
                if (mainBlock.getSQX(true) == fieldObjects[i].getSQX(true) && mainBlock.getSQY(true) == fieldObjects[i].getSQY(true)){
                    switch (fieldObjects[i].getTypeBlock()){
                        case BOOSTER:
                            mainBlock.setBoost();

                            if (!DialogSystem.ISLEARNING){
                                robotHead.setText("BOOOST!", 1.3f).showInTimered(1f);

                                updateArkadePositions();
                                refreshOnePosition(i);
                            }
                            break;
                        case SLOWER:
                            mainBlock.setSlow();

                            if (!DialogSystem.ISLEARNING){
                                robotHead.setText("LOL, SLOW!", 0.8f).showInTimered(1f);

                                arkadeBlock.updateScore(5, 8, mult, fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());

                                updateArkadePositions();
                                refreshOnePosition(i);
                            }
                            break;
                        case REVERS:
                            mainBlock.specialRevers();

                            if (!DialogSystem.ISLEARNING){
                                refFiskIndexes[refFiskCount] = i;
                                refFiskCount++;
                                for (int j = 0; j < fieldObjects.length; j++) {
                                    if (fieldObjects[j].getTypeBlock() == ROTATE90 || fieldObjects[j].getTypeBlock() == ROTATEM90)
                                        ((Rotate90)fieldObjects[j]).setDirection(false);
                                }

                                arkadeBlock.updateScore(1, 7, mult, fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());
                            }
                            break;
                        case ROTATE90:
                        case ROTATEM90:

                            int target = 0;
                            switch (((Rotate90)fieldObjects[i]).getDirection()){
                                case TOP:
                                    target = BOTTOM;
                                    break;
                                case BOTTOM:
                                    target = TOP;
                                    break;
                                case LEFT:
                                    target = RIGHT;
                                    break;
                                case RIGHT:
                                    target = LEFT;
                                    break;
                            }

                            if (mainBlock.getDirection() == target){
                                mainBlock.nextDirectionN(fieldObjects[i].getTypeBlock() == ROTATEM90);

                                if (!DialogSystem.ISLEARNING){
                                    arkadeBlock.updateScore(3, 5, mult, fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());
                                    inspectUpdatedScore();

                                    refFiskIndexes[refFiskCount] = i;
                                    refFiskCount++;
                                }
                            } else
                                mainBlock.blockHasComedHole(false);

                            break;
                        case BOMB:
                            levelGen.arkadeLose();
                            break;
                        case TELEPORT:
                            mainBlock.randomTeleport();

                            if (!DialogSystem.ISLEARNING){
                                updateArkadePositions();
                                refreshOnePosition(i);
                            }
                            break;
                        case TIMER_ADD:
                            if (((TimerAddBlock)fieldObjects[i]).isShowing()){
                                levelGen.addSecondsToTimerArcade(10f);
                                arkadeBlock.updateScore(1, 7, mult, fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());

                                robotHead.setText("+10 SEC", 0.8f).showInTimered(1f);

                                ((TimerAddBlock)fieldObjects[i]).setShowing(false);
                            }
                            break;
                        case MOVER_BONUS:
                            if (((MoverBonus)fieldObjects[i]).isShowing()){
                                levelGen.addSecondsToTimerArcade(10f);
                                arkadeBlock.updateScore(100, 9, mult, fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());

                                robotHead.setText("SUPER BONUS!!!", 0.8f).showInTimered(1f);

                                ((MoverBonus)fieldObjects[i]).setShowing(false);
                            }
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
            if (fieldObjects[i].getTypeBlock() == HOLE && fieldObjects[i].getSQX(true) == currentX + nextX
                                          && fieldObjects[i].getSQY(true) == currentY + nextY){
                if (currentType == ((Hole)fieldObjects[i]).getType())
                    return true;
            }
        }
        return false;
    }

    public void clearStacked(float x, float y){
        int timeredStackedCount = stackedCount;
        stackedCount = 0;
        isCleanedCollision = false;
        stackedTrain.set(mainBlockBounds);
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK)
                ((Block) fieldObjects[i]).clearStacked(x, y);
        }

        inspectHolesAndBlocks();

        if (ISARKADE) {
            for (int i = 0; i < fieldObjects.length; i++) {
                if (fieldObjects[i].getTypeBlock() == BLOCK) {
                    if (((Block) fieldObjects[i]).isHoled()) {
                        fieldObjects[i].setTypeBlock(NULLTYPE);
                        arkadeCompletePositions++;

                        if (!DialogSystem.ISLEARNING) {

                            int scoreUp = 0;
                            switch (((Block) fieldObjects[i]).getType()) {
                                case 0: //blue
                                    scoreUp = 25;
                                    levelGen.addSecondsToTimerArcade(8f);
                                    break;
                                case 1: //green
                                    scoreUp = 20;
                                    levelGen.addSecondsToTimerArcade(6f);
                                    break;
                                case 2: //red
                                    scoreUp = 30;
                                    levelGen.addSecondsToTimerArcade(10f);
                                    break;
                                case 3: //white
                                    scoreUp = 10;
                                    levelGen.addSecondsToTimerArcade(2f);
                                    break;
                                case 4: //yellow
                                    scoreUp = 15;
                                    levelGen.addSecondsToTimerArcade(4f);
                                    break;
                            }

                            counterBlocksDrivedOut++;
                            boolean isManyBlocks = counterBlocksDrivedOut > 1 || timeredStackedCount > 1;
                            int mult = 0, realMult = 0;
                            if (mainBlock.isRotated() && mainBlock.isBoost()) {

                                robotHead.setText("OHHH, GOD. 4X!!! TOTAL SCORE:", scoreUp, !isManyBlocks, 0.5f).showInTimered(1f);

                                scoreUp *= 4;
                                realMult = 4;
                                if (((Block) fieldObjects[i]).getType() == 2)
                                    levelGen.flyTextPls(2);
                                mult = 3;
                            } else if (mainBlock.isBoost()) {
                                scoreUp *= 2;
                                realMult = 2;
                                mult = 1;
                                robotHead.setText("2X!!! TOTAL SCORE:", scoreUp, !isManyBlocks, 0.7f).showInTimered(1f);
                            } else if (mainBlock.isRotated()) {
                                scoreUp *= 3;
                                realMult = 3;
                                mult = 2;
                                robotHead.setText("3X!!! TOTAL SCORE:", scoreUp, !isManyBlocks, 0.7f).showInTimered(1f);
                            }

                            if (isManyBlocks) {
                                scoreUp *= 2;
                                int whX = realMult != 0 ? realMult * counterBlocksDrivedOut * 2 : counterBlocksDrivedOut * 2;
                                robotHead.showInTimered(1f).setText(whX + "X!!! TOTAL SCORE:", scoreUp, true, 0.7f);
                            }

                            int lastScore = arkadeBlock.getScore();
                            arkadeBlock.updateScore(scoreUp, ((Block) fieldObjects[i]).getType(), mult,
                                    fieldObjects[i].getCenterFloatX(), fieldObjects[i].getCenterFloatY());

                            if (arkadeBlock.getScore() / 100 > lastScore / 100) {

                                if (arkadeBlock.getScore() - lastScore < 100) {
                                    if (arkadeBlock.getScore() >= 10000)
                                        robotHead.setText("Oh, My GOD!!! Already 10000 points.", 0.3f).showInTimered(2f);
                                    else
                                        robotHead.setText("Nice! Already " + arkadeBlock.getScore() + " points.", 0.6f).showInTimered(2f);
                                }

                                for (int j = 0; j < fieldObjects.length; j++) {
                                    if (fieldObjects[j].getTypeBlock() == TIMER_ADD)
                                        ((TimerAddBlock) fieldObjects[j]).setShowing(true);
                                }
                            }

                            inspectUpdatedScore();
                            if (isNewRecord) {
                                switch (TypeGameButton.TOUCHED_ARK) {
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
                        }
                        if (arkadeCompletePositions > 0) {
                            isEndLevel = true;

                            refBlockIndexes[refBlockAndHoleCount] = i;
                            if (refBlockAndHoleCount == 0) {
                                refHoleIndexes[refBlockAndHoleCount] = ((Block) fieldObjects[i]).getHoleIndex();
                                refDeletedHoles[refBlockAndHoleCount] = refHoleIndexes[refBlockAndHoleCount];
                            } else
                                deleteAndAddNewHoleToRandomizeAdd(refBlockAndHoleCount, ((Block) fieldObjects[i]).getType());

                            refBlockAndHoleCount++;
                        }
                    }

                }
            }
        }

    }

    private void deleteAndAddNewHoleToRandomizeAdd(int index, int colType){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == HOLE){
                boolean isAdd = true;
                for (int j = 0; j < index; j++) {    ///проверяем, если такие блоки в массиве удалённых объектов
                    if (refDeletedHoles[j] == i)
                        isAdd = false;
                }
                if (isAdd && colType == ((Hole)fieldObjects[i]).getType()){
                    refHoleIndexes[index] = i;
                    refDeletedHoles[index] = refHoleIndexes[index];
                }
            }
        }
    }



    private void inspectUpdatedScore(){
        if (arkadeBlock.getScore() > oldRecord){
            if (!isNewRecord){
                isNewRecord = true;
                if (oldRecord != 0)
                    levelGen.flyTextPls(1);
            }
        }

        if (needOpenNextMode){
            if (arkadeBlock.getScore() >= targetScore && targetScore != 0){
                if (!isScoreMoreTarget){
                    isScoreMoreTarget = true;
                    levelGen.flyTextPls(0);
                    Prefers.putInt(Prefers.KeyOpenedArkadeModes, Prefers.getInt(Prefers.KeyOpenedArkadeModes) + 1);
                }
            }
        }
    }

    private void inspectHolesAndBlocks(){
        int score = 0;
        if (!ISARKADE)
            isEndLevel = true;

        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].getTypeBlock() == InitLevels.BLOCK){
                if (!((Block)fieldObjects[i]).isHoled()){
                    isEndLevel = false;
                    if (ISRANDOM)
                        score++;
                    else
                        break;
                }
            }
        }

        if (ISRANDOM && !DialogSystem.ISLEARNING){
            arkadeBlock.setScore(score);
        }
    }

    public int getStackedCount() {
        return stackedCount;
    }

    public int getFieldObjectsSize(){
        return fieldObjects.length;
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

    public boolean isNewRecord() {
        return isNewRecord;
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
