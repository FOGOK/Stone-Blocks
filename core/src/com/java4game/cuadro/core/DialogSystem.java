package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.RobotHead;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.Block;
import com.java4game.cuadro.objects.BoosterSlower;
import com.java4game.cuadro.objects.FieldLearnLine;
import com.java4game.cuadro.objects.FieldObject;
import com.java4game.cuadro.objects.Hole;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.Revers;
import com.java4game.cuadro.objects.Rotate90;
import com.java4game.cuadro.objects.Teleport;
import com.java4game.cuadro.utils.Assets;

import static com.java4game.cuadro.objects.FieldObject.NULLTYPE;

/**
 * Created by FOGOK on 28.04.2017 12:23.
 */

public class DialogSystem {

    public static int LEARNING_PART;
    public static boolean ISLEARNING, ISALLOWTOUCH;

    private RobotHead robotHead;
    private int screen, mainBlockRotationCount;
    private FieldObject fieldObjects[];
    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;

    private FieldLearnLine firstFieldLearnLine, secondFieldLearnLine;

    public DialogSystem(RobotHead robotHead, BlockGenerator blockGenerator) {
        this.robotHead = robotHead;
        this.blockGenerator = blockGenerator;
        ISALLOWTOUCH = false;
    }

    public void setFirstFieldLearnLine(FieldLearnLine firstFieldLearnLine) {
        this.firstFieldLearnLine = firstFieldLearnLine;
    }
    public void setSecondFieldLearnLine(FieldLearnLine secondFieldLearnLine) {
        this.secondFieldLearnLine = secondFieldLearnLine;
    }
    public void setMainBlock(MainBlock mainBlock) {
        this.mainBlock = mainBlock;
    }

    public void startLearning(){
        if (LEARNING_PART == 0)
            screen = -1;
        else
//            screen = 21;
            screen = 13;
        nextScreen();
    }

    public void setFieldObjects(FieldObject[] fieldObjects) {
        this.fieldObjects = fieldObjects;
    }

    public void handle(){
        boolean condition = false;
        switch (screen){
            case 0:
            case 13:
            case 24:
                condition = mainBlockRotationCount > 2;
                break;
            case 1:
                condition = firstFieldLearnLine.greenBlockCollide(mainBlock);
                break;
            case 2:
                condition = isJustTouch();  //ждать нажатия
                break;
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            case 15:
            case 18:
            case 22:
                condition = mainBlockRotationCount > 0; //ждать, пока кубик выедет за поле
                break;
            case 4:
                condition = firstFieldLearnLine.greenBlockCollide(mainBlock) && isJustTouch();
                break;
            case 6:
            case 11:
                condition = ((Block)fieldObjects[1]).isHoled();
                break;
            case 9:
                condition = fieldObjects[1].getSQX(true) == 2 && fieldObjects[1].getSQY(true) == 4 && isJustTouch();
                break;
            case 14:
            case 17:
            case 19:
                condition = fieldObjects[6].getSQX(true) == mainBlock.getSQX(true) &&
                        fieldObjects[6].getSQY(true) == mainBlock.getSQY(true);
                break;
            case 20:
                condition = mainBlockRotationCount > 1; //ждать, пока кубик выедет за поле
                break;
            case 21:
                condition = fieldObjects[3].getTypeBlock() == NULLTYPE;
                break;
            case 23:
                condition = fieldObjects[3].getTypeBlock() == NULLTYPE && fieldObjects[4].getTypeBlock() == NULLTYPE;
                break;
        }

        if (condition)
            nextScreen();
    }

    private boolean isJustTouch(){
        return Gdx.input.justTouched() && (Gm.HEIGHT / Gdx.graphics.getHeight()) * Gdx.input.getY() > 3f;
    }


    public void nextScreen(){
        screen++;
        mainBlockRotationCount = 0;
        switch (screen){
            case 0:
                for (int i = 0; i < fieldObjects.length; i++)
                    fieldObjects[i].setTypeBlock(FieldObject.HIDED);
                robotHead.setText("HI! WELCOME TO TRAINING", 0.8f).show();
                break;
            case 1:
                robotHead.setText("WAIT UNTIL THE CUBE COME GREEN SQUARE", 0.6f).show();
                firstFieldLearnLine.setParams(false, true, 4, true);
                break;
            case 2:
                robotHead.setText("TAAAP TO SCREEEEN!", 0.9f).show();
                Handler.ISPAUSE = true;
                break;
            case 3:
                robotHead.setText("GOOD!", 1.2f).show();    //после условия с нажатием
                Handler.ISPAUSE = false;
                mainBlock.touchAction();
                break;
            case 8:
                nextScreen();
                break;
            case 4:
                robotHead.setText("NOW DO IT YOURSELF", 0.6f).show();
                firstFieldLearnLine.setParams(true, false, 4, true);
                ISALLOWTOUCH = true;
                break;
            case 5:
            case 7:
            case 10:
            case 12:
            case 15:
            case 18:
            case 20:
            case 22:
                robotHead.setText("GOOD!", 1.2f).show();
                break;
            case 6:
                robotHead.setText("TAP TO SCREEN IN GREEN POSITION FOR MOVE BLOCK", 0.6f).show();
                firstFieldLearnLine.setParams(false, true, 5, true);
                firstFieldLearnLine.setOtst(1);

                fieldObjects[0] = new Hole(Assets.getNewSprite(16), firstFieldLearnLine.getFieldBounds(),
                        2, 5, 3);
                fieldObjects[0].setAlpha(0f);
                fieldObjects[1] = new Block(3, Assets.getNewSprite(10),
                        firstFieldLearnLine.getCellSize() * 1.3f, 6, 5,
                        firstFieldLearnLine.getFieldBounds(), mainBlock, fieldObjects);
                fieldObjects[1].setAlpha(0f);

                blockGenerator.arkadeRefresh();
                break;
            case 9:
                robotHead.setText("STEP ONE: STOP THE BLOCK BEFORE WHITE HOLE", 0.5f).show();
                firstFieldLearnLine.setParams(false, true, 4, true);
                firstFieldLearnLine.setOtst(1);
//                fieldObjects[i].setAlpha(0f);
                fieldObjects[0] = new Hole(Assets.getNewSprite(16), firstFieldLearnLine.getFieldBounds(),
                        2, 5, 3);
                fieldObjects[1] = new Block(3, Assets.getNewSprite(10),
                        firstFieldLearnLine.getCellSize() * 1.3f, 6, 4,
                        firstFieldLearnLine.getFieldBounds(), mainBlock, fieldObjects);
                fieldObjects[0].setAlpha(0f);
                fieldObjects[1].setAlpha(0f);
                blockGenerator.arkadeRefresh();
                break;
            case 11:
                robotHead.setText("STEP TWO: STOP THE BLOCK IN WHITE HOLE", 0.5f).show();
                firstFieldLearnLine.setParams(true, false, 2, true);
                firstFieldLearnLine.setOtst(3);
                break;
            case 13:
                LEARNING_PART = 1;
                ButtonActions.activateAction(ButtonActions.All.OPEN_LEARNING_INTERACTIVE);
                break;

            //arkade
            case 14:
                for (int i = 0; i < fieldObjects.length; i++)
                    fieldObjects[i].setTypeBlock(FieldObject.HIDED);

                firstFieldLearnLine.setParams(true, false, 5, true);
                firstFieldLearnLine.setShowBlockT(false);
                secondFieldLearnLine.setParams(false, false, 4, true);
                secondFieldLearnLine.setShowBlockT(false);

                fieldObjects[6] = new Revers(firstFieldLearnLine.getFieldBounds(),
                        5, 4);
                fieldObjects[6].setAlpha(0f);

                blockGenerator.arkadeRefresh();

                ISALLOWTOUCH = true;

                robotHead.setText("THIS IS REVERSE. TRY IT!", 0.7f).show();
                break;
            case 16:
                firstFieldLearnLine.setParams(true, false, 4, true);
                firstFieldLearnLine.setShowBlockT(false);
                secondFieldLearnLine.setParams(false, false, 6, true);
                secondFieldLearnLine.setShowBlockT(false);

                fieldObjects[6] = new Teleport(firstFieldLearnLine.getFieldBounds(),
                        4, 6);
                fieldObjects[6].setAlpha(0f);

                blockGenerator.arkadeRefresh();

                robotHead.setText("THIS IS TELEPORT AT RANDOM POSITION", 0.5f).show();
                break;
            case 17:
            case 19:
                firstFieldLearnLine.setParams(true, false, screen == 17 ? 8 : 6, true);
                firstFieldLearnLine.setShowBlockT(false);
                secondFieldLearnLine.setParams(false, false, screen == 17 ? 4 : 2, true);
                secondFieldLearnLine.setShowBlockT(false);

                fieldObjects[6] = new BoosterSlower(firstFieldLearnLine.getFieldBounds(), screen == 17,
                        screen == 17 ? 8 : 6, screen == 17 ? 4 : 2);
                fieldObjects[6].setAlpha(0f);

                blockGenerator.arkadeRefresh();

                if (screen == 17)
                    robotHead.setText("THIS IS TURBO. X2 POINTS GET IT", 0.5f).show();
                else
                    robotHead.setText("THIS IS SLOWER. 5 POINTS GET IT", 0.5f).show();

                break;
            case 21:
                mainBlock.normalizeSpeed();

                firstFieldLearnLine.setParams(true, false, 2, true);
                firstFieldLearnLine.setOtst(3);
                secondFieldLearnLine.setParams(false, true, 5, true);
                secondFieldLearnLine.setShowBlockT(false);
                secondFieldLearnLine.setOtst(1);

                fieldObjects[6] = new Rotate90(firstFieldLearnLine.getFieldBounds(), true,
                        2, 5, mainBlock);
                ((Rotate90)fieldObjects[6]).setBottomDirInLearn();

                fieldObjects[0] = new Hole(Assets.getNewSprite(16), firstFieldLearnLine.getFieldBounds(),
                        7, 5, 3);
                fieldObjects[3] = new Block(3, Assets.getNewSprite(10),
                        firstFieldLearnLine.getCellSize() * 1.3f, 4, 5,
                        firstFieldLearnLine.getFieldBounds(), mainBlock, fieldObjects);
                fieldObjects[0].setAlpha(0f);
                fieldObjects[6].setAlpha(0f);
                fieldObjects[3].setAlpha(0f);

                blockGenerator.arkadeRefresh();

                robotHead.setText("THIS IS ROTATOR. X2 POINTS GET IT", 0.5f).show();

                break;
            case 23:
                for (int i = 0; i < fieldObjects.length; i++)
                    fieldObjects[i].setTypeBlock(FieldObject.HIDED);

                firstFieldLearnLine.setParams(false, true, 5, true);
                secondFieldLearnLine.setParams(false, true, 5, false);

                fieldObjects[0] = new Hole(Assets.getNewSprite(16), firstFieldLearnLine.getFieldBounds(),
                        2, 5, 3);

                fieldObjects[3] = new Block(3, Assets.getNewSprite(10),
                        firstFieldLearnLine.getCellSize() * 1.3f, 5, 5,
                        firstFieldLearnLine.getFieldBounds(), mainBlock, fieldObjects);

                fieldObjects[4] = new Block(3, Assets.getNewSprite(10),
                        firstFieldLearnLine.getCellSize() * 1.3f, 7, 5,
                        firstFieldLearnLine.getFieldBounds(), mainBlock, fieldObjects);
                fieldObjects[0].setAlpha(0f);
                fieldObjects[3].setAlpha(0f);
                fieldObjects[4].setAlpha(0f);

                blockGenerator.arkadeRefresh();

                robotHead.setText("2 IN 1 HOLE. X2 POINTS DO IT", 0.5f).show();
                break;
            case 24:
                for (int i = 0; i < fieldObjects.length; i++)
                    fieldObjects[i].setTypeBlock(FieldObject.HIDED);

                firstFieldLearnLine.setParams(false, true, 5, false);

                ISALLOWTOUCH = false;
                robotHead.setText("GOOD!", 1.2f).show();
                break;
            case 25:
                ButtonActions.activateAction(ButtonActions.All.TOMAINMENU_PAUSE_ACTION);
                break;
        }
    }

    public void teleportAction(){
        nextScreen();
    }

    public void incrMainBlockRotationCount() {
        mainBlockRotationCount++;
    }

    public int getScreen() {
        return screen;
    }


}
