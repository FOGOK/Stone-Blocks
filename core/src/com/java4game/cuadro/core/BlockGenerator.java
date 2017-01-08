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
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Prefers;
import com.java4game.cuadro.utils.Timer;

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
    private Rectangle mainBlockBounds, stackedTrain, dotq;
    private int stackedCount;
    private MainBlock mainBlock;

    private float cornTrainX, cornTrainY;

    private boolean isEndLevel;

    private Timer endGameTimer;

    private boolean isCleanedCollision; //это значит, что мы ждём, пока не будет ни одной коллизии с блоком

    private float cellSize;

    private Rectangle fieldBounds;

    public BlockGenerator(MainBlock mainBlock, Rectangle fieldBounds, int LEVEL) {
        this.mainBlock = mainBlock;
        this.fieldBounds = fieldBounds;

        this.LEVEL = LEVEL;

        mainBlockBounds = mainBlock.getBlockBounds();
        cellSize = mainBlock.getCellSize();

        dotq = new Rectangle(0f, 0f, Gm.HEIGHT / Gdx.graphics.getHeight(), Gm.HEIGHT / Gdx.graphics.getHeight());

        stackedTrain = new Rectangle(mainBlockBounds);
        cornTrainX = cornTrainY = stackedCount = 0;

        isCleanedCollision = true;

        isEndLevel = false;

        BlockAndHolesPositions.Level level = BlockAndHolesPositions.getLevel(LEVEL);

        endGameTimer = new Timer(1);

        fieldObjects = new FieldObject[level.getObjects().length];
        for (int i = 0; i < fieldObjects.length; i++) {
            if (level.getObjects()[i].isCube())
                fieldObjects[i] = new Block(level.getObjects()[i].getType(), Assets.getNewSprite(7 + level.getObjects()[i].getType()),
                        cellSize * 1.3f, level.getObjects()[i].getX(), level.getObjects()[i].getY(),
                        fieldBounds, mainBlock, fieldObjects);
            else
                fieldObjects[i] = new Hole(Assets.getNewSprite(13 + level.getObjects()[i].getType()), fieldBounds,
                        level.getObjects()[i].getX(), level.getObjects()[i].getY(), level.getObjects()[i].getType());
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
            fieldObjects[i].draw(batch);
        }

        if (isEndLevel || endGameTimer.isStarted()){
            if (endGameTimer.next()){
                Handler.state = Handler.State.Menu;
                MenuUI.MENUSTATE = MenuUI.SELECTSTAGE;
                MenuUI.SETSTAGEPROP = true;
                MusicCore.play(MusicCore.MENU);
                if (MenuUI.OPENEDSTAGESINWORLD[0] == LEVEL + 1){     //открываем следующий уровень
                    if (MenuUI.OPENEDSTAGESINWORLD[0] <= MenuUI.COUNTSTAGESINWORLD[0]){
                        MenuUI.OPENEDSTAGESINWORLD[0]++;
                        Prefers.putInt(Prefers.KeyOpenedStages, MenuUI.OPENEDSTAGESINWORLD[0]);
                    }
                }
            }
        }

//        dotq.setPosition(cornTrainX, cornTrainY);
//
//        DebugDrawer.drawRect(batch, dotq);
//        DebugDrawer.drawRect(batch, stackedTrain);
    }

    public void clearStacked(){
        stackedCount = 0;
        isCleanedCollision = false;
        stackedTrain.set(mainBlockBounds);
        for (int i = 0; i < fieldObjects.length; i++) {
            if (fieldObjects[i].isCube())
                ((Block)fieldObjects[i]).clearStacked();
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

    public void dispose() {

    }
}
