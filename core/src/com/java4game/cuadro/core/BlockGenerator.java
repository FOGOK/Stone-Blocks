package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.Block;
import com.java4game.cuadro.objects.FieldObject;
import com.java4game.cuadro.objects.Hole;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Prefers;
import com.java4game.cuadro.utils.Timer;

import static com.java4game.cuadro.core.usie.TypeGameBottomBar.SELECTED_BTN;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_STEPS;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_TIMED;

/**
 * Created by FOGOK on 03.01.2017 15:59.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class BlockGenerator {

    private int LEVEL;
//    private Block blocks[];
    private FieldObject fieldObjects[];
    private int countMinSteps;
    private Rectangle mainBlockBounds, stackedTrain, dotq;
    private int stackedCount;
    private MainBlock mainBlock;

    private float cornTrainX, cornTrainY;

    private boolean isEndLevel;

    private Timer endGameTimer;

    private boolean isCleanedCollision; //это значит, что мы ждём, пока не будет ни одной коллизии с блоком

    private float cellSize;

    private Rectangle fieldBounds;
    private StarBlock starBlock;
    private LevelGen levelGen;

    public BlockGenerator(LevelGen levelGen, MainBlock mainBlock, Rectangle fieldBounds, int LEVEL) {
        this.mainBlock = mainBlock;
        this.fieldBounds = fieldBounds;
        this.levelGen = levelGen;

        this.LEVEL = LEVEL;

        mainBlockBounds = mainBlock.getBlockBounds();
        cellSize = mainBlock.getCellSize();

        dotq = new Rectangle(0f, 0f, Gm.HEIGHT / Gdx.graphics.getHeight(), Gm.HEIGHT / Gdx.graphics.getHeight());

        stackedTrain = new Rectangle(mainBlockBounds);
        cornTrainX = cornTrainY = stackedCount = 0;

        isCleanedCollision = true;

        isEndLevel = false;

        InitLevels.Level level = InitLevels.getStepsLevels(LEVEL);

        endGameTimer = new Timer(1f);

        fieldObjects = new FieldObject[level.getObjects().length];
        for (int i = 0; i < fieldObjects.length; i++) {
            if (level.getObjects()[i].isCube()){
                fieldObjects[i] = new Block(level.getObjects()[i].getType(), Assets.getNewSprite(7 + level.getObjects()[i].getType()),
                        cellSize * 1.3f, level.getObjects()[i].getX(), level.getObjects()[i].getY(),
                        fieldBounds, mainBlock, fieldObjects);
                ((Block)fieldObjects[i]).setBlockGenerator(this);
            }
            else
                fieldObjects[i] = new Hole(Assets.getNewSprite(13 + level.getObjects()[i].getType()), fieldBounds,
                        level.getObjects()[i].getX(), level.getObjects()[i].getY(), level.getObjects()[i].getType());
        }
        calculateCountMinSteps();
    }

    public void setStarBlock(StarBlock starBlock) {
        this.starBlock = starBlock;
    }

    private void calculateCountMinSteps(){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].isCube()){
                countMinSteps += isOneLineInHoles(fieldObjects[i].getSQX(true), fieldObjects[i].getSQY(true), ((Block)fieldObjects[i]).getType()) ? 1 : 2;
            }
        }
    }

    private boolean isOneLineInHoles(int x, int y, int type){
        for (int i = 0; i < fieldObjects.length; i++) {
            if (!fieldObjects[i].isCube()){
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
            if (!fieldObjects[i].isCube())
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
            if (fieldObjects[i].isCube())
                fieldObjects[i].draw(batch);
        }

        if (isEndLevel || endGameTimer.isStarted()){
//            if (endGameTimer.next()){
            Handler.state = Handler.State.Menu;
            MenuUI.MENUSTATE = MenuUI.SELECTSTAGE;
            MenuUI.SETSTAGEPROP = true;
            MusicCore.play(MusicCore.MENU);

            int curStar = 1;

            //setStar
            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    final char[] chars = Prefers.getString(Prefers.KeyStars).toCharArray();
                    curStar = starBlock.getCurrentStar().ordinal();
                    if (curStar > Character.getNumericValue(chars[LEVEL]))
                        chars[LEVEL] = Integer.toString(curStar).charAt(0);
                    Prefers.putString(Prefers.KeyStars, new String(chars));
                    levelGen.refreshStars();
                    break;
                case TYPE_TIMED:

                    break;
            }
            //

//            chars[LEVEL] =
            ///

            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    if (MenuUI.OPENEDSTAGESINWORLD[0] == LEVEL + 1 && curStar != 0){     //открываем следующий уровень
                        if (MenuUI.OPENEDSTAGESINWORLD[0] <= MenuUI.COUNTSTAGESINWORLD[0]){
                            MenuUI.OPENEDSTAGESINWORLD[0]++;
                            Prefers.putInt(Prefers.KeyOpenedStagesSteps, MenuUI.OPENEDSTAGESINWORLD[0]);
                        }
                    }
                    break;
                case TYPE_TIMED:
                    if (MenuUI.OPENEDSTAGESINWORLD[1] == LEVEL){     //открываем следующий уровень
                        if (MenuUI.OPENEDSTAGESINWORLD[1] <= MenuUI.COUNTSTAGESINWORLD[1]){
                            MenuUI.OPENEDSTAGESINWORLD[1]++;
                            Prefers.putInt(Prefers.KeyOpenedStagesTimed, MenuUI.OPENEDSTAGESINWORLD[1]);
                        }
                    }
                    break;
            }


//            }
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
            if (fieldObjects[i].isCube()){
                if (((Block)fieldObjects[i]).isStacked() && ((Block)fieldObjects[i]).isHoled() && (!isOtherBlockTargetTypeAvailable(((Block)fieldObjects[i]).getType(), i) || !isNextTypeEqCurrentType())){
                    mainBlock.blockHasComedHole();
                }
            }
        }
    }

    private boolean isOtherBlockTargetTypeAvailable(int targetType, int targetCube){   ///есть ли в стаке ещё такие блоки, как этот и в лунках ли они
        for (int i = 0; i < fieldObjects.length; i++) {
            if (i != targetCube){
                if (fieldObjects[i].isCube()){
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
            if (fieldObjects[i].isCube()){
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
            if (!fieldObjects[i].isCube() && fieldObjects[i].getSQX(true) == currentX + nextX
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
            if (fieldObjects[i].isCube())
                ((Block)fieldObjects[i]).clearStacked(x, y);
        }
        inspectHolesAndBlocks();
    }

    private void inspectHolesAndBlocks(){
        isEndLevel = true;
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].isCube()){
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
            if (fieldObjects[i].isCube()){
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
            if (fieldObjects[i].isCube()){
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
