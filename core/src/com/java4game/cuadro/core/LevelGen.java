package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.PauseButton;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.FlyingStage;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.objects.Timer;
import com.java4game.cuadro.utils.Assets;

import static com.java4game.cuadro.core.usie.TypeGameBottomBar.SELECTED_BTN;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_STEPS;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_TIMED;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class LevelGen {

    /**
     * Класс, который отвечает за всё, что связанно с игрой
     * тут инициализируется игровое поле, кубик, препятствия и т.д.
     *
     * */

    public static final int SQSIZE = 7;
    public static float backHDivH;
    private Rectangle fieldBounds;
    private Sprite background, field;



    public static boolean REFRESH_REFRESH;

    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;



    //ui
    private FlyingStage flyingStage;
    private StarBlock starBlock;
    private Timer timerBlock;
    private PauseButton pauseButton;
    ///

    private MenuUI menuUI;

    public LevelGen(MenuUI menuUI) {
        //инициализируем фон
        this.menuUI = menuUI;
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                background = Assets.getNewSprite(InitLevels.getStepsLevels(StageButton.LEVEL - 1).getBackgroundColor());
                break;
            case TYPE_TIMED:
                background = Assets.getNewSprite(InitLevels.getTimeLevels(StageButton.LEVEL - 1).getBackgroundColor());
                break;
        }

        final float hDivW = 1.7777f;
        background.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
        background.setPosition(0f, (Gm.HEIGHT - background.getHeight()) / 2f);
        backHDivH = background.getHeight() / Gm.HEIGHT;
        ///
        final float fieldSize = background.getWidth() * 0.9f;
        field = Assets.getNewSprite(1);
        field.setSize(fieldSize, fieldSize);
        field.setPosition((Gm.WIDTH - fieldSize) / 2f, (Gm.HEIGHT - fieldSize) / 2f);

        fieldBounds = field.getBoundingRectangle();

        ///инициализируем кубик и устанавливаем размер кубика
        mainBlock = new MainBlock(this, Assets.getNewSprite(12), fieldBounds);
        blockGenerator = new BlockGenerator(this, mainBlock, fieldBounds, StageButton.LEVEL - 1);
        mainBlock.setBlockGenerator(blockGenerator);
        //

        //ui
        //инициаилизируем летящий текст
        Color flyStageColor = Color.valueOf("2c2c36");
//        switch (BlockAndHolesPositions.getStepsLevels(StageButton.LEVEL - 1).getBackgroundColor()){
//            case BlockAndHolesPositions.BACK_COLOR_BLUE:
//                flyStageColor = Color.ROYAL.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_GRAY:
//                flyStageColor = Color.DARK_GRAY.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_GREEN:
//                flyStageColor = Color.FOREST.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_RED:
//                flyStageColor = Color.FIREBRICK.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_YELLOW:
//                flyStageColor = Color.GOLDENROD.cpy();
//                break;
//        }
        flyingStage = new FlyingStage();
        flyingStage.setNew(StageButton.LEVEL - 1, flyStageColor);
        if (!REFRESH_REFRESH)
            flyingStage.refreshRefresh();
        //
        InitLevels.Level currLevel;
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                currLevel = InitLevels.getStepsLevels(StageButton.LEVEL - 1);
                starBlock = new StarBlock(this, currLevel.getMinSteps() == 0 ? blockGenerator.getCountMinSteps() : currLevel.getMinSteps());
                blockGenerator.setStarBlock(starBlock);
                break;
            case TYPE_TIMED:
                 currLevel = InitLevels.getTimeLevels(StageButton.LEVEL - 1);
                timerBlock = new Timer(this, currLevel.getAllSeconds() == 0f ? blockGenerator.getCountMinSteps() * 5.20f : currLevel.getAllSeconds());
                blockGenerator.setTimerBlock(timerBlock);
                break;
        }

        final float sizePauseBtn = 2.3f;
        pauseButton = new PauseButton(ButtonActions.All.PAUSE_ACT, 0.1f, Gm.HEIGHT - sizePauseBtn * 0.4f, sizePauseBtn);
        pauseButton.setPositionToCenter();
        pauseButton.completeX();

        pauseBInterp = Interpolation.exp5Out;

        MusicCore.playSound(8);


    }
    private Interpolation pauseBInterp;
    public void draw(SpriteBatch batch){



        flyingStage.handle();
        flyingStage.drawGlass(batch);

        if (!flyingStage.isFlying()){
            field.setAlpha(flyingStage.getProgressEnd());
            field.draw(batch);

            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    if (flyingStage.getProgressEnd() != 1f)
                        starBlock.handle(flyingStage.getProgressEnd());
                    else
                        starBlock.handle();
                    break;
                case TYPE_TIMED:
                    if (flyingStage.getProgressEnd() != 1f)
                        timerBlock.handle(flyingStage.getProgressEnd());
                    else
                        timerBlock.handle();
                    if (!Handler.ISPAUSE)
                        timerBlock.handleLogic();
                    break;
            }


            pauseButton.setOffsetX(pauseBInterp.apply(-pauseButton.getWidth(), 0f, flyingStage.getProgressEnd()));
            pauseButton.setAlpha(pauseBInterp.apply(0f, 1f, flyingStage.getProgressEnd()));
            pauseButton.setEnabled(!Handler.ISPAUSE);
            pauseButton.draw(batch);
        }


        batch.end();

        int srcFunc = batch.getBlendSrcFunc();
        int dstFunc = batch.getBlendDstFunc();

        batch.enableBlending();
        batch.begin();

        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

        background.draw(batch);

        batch.end();
        batch.begin();
        batch.setBlendFunction(srcFunc, dstFunc);


        if (!flyingStage.isFlying()){
            blockGenerator.setAlpha(flyingStage.getProgressEnd());
            blockGenerator.drawHoles(batch);
            mainBlock.setAlpha(flyingStage.getProgressEnd());
            mainBlock.draw(batch);
            blockGenerator.draw(batch);

            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    starBlock.drawGlass(batch);
                    starBlock.drawMetalAndText(batch);
                    break;
                case TYPE_TIMED:
                    timerBlock.draw(batch);
                    break;
            }

            pauseButton.drawIcon(batch);
        }
        flyingStage.drawText(batch);

    }

    public void refreshStars(){
        menuUI.refreshStarsData();
    }

    public void minusStep(){
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                starBlock.minusStep();
                break;
            case TYPE_TIMED:
                break;
        }
    }

    public void inspectIsChangeStar(){
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                starBlock.inspectIsChangeStar();
                break;
            case TYPE_TIMED:
                break;
        }
    }

    public void lose(){
        ButtonActions.activateAction(ButtonActions.All.TOMAINMENU_PAUSE_ACTION);
    }
}
