package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.PauseButton;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.FlyingGlass;
import com.java4game.cuadro.objects.FlyingStage;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.utils.Assets;

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

    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;



    //ui
    private FlyingStage flyingStage;
    private StarBlock starBlock;
    private PauseButton pauseButton;
    ///

    private MenuUI menuUI;

    public LevelGen(MenuUI menuUI) {
        //инициализируем фон
        this.menuUI = menuUI;
        BlockAndHolesPositions.Level currLevel = BlockAndHolesPositions.getLevel(StageButton.LEVEL - 1);
        background = Assets.getNewSprite(currLevel.getBackgroundColor());
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
//        switch (BlockAndHolesPositions.getLevel(StageButton.LEVEL - 1).getBackgroundColor()){
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
        //
        starBlock = new StarBlock(this, currLevel.getMinSteps() == 0 ? blockGenerator.getCountMinSteps() : currLevel.getMinSteps());
        blockGenerator.setStarBlock(starBlock);

        final float sizePauseBtn = 2.3f;
        pauseButton = new PauseButton(ButtonActions.All.PAUSE_ACT, 0.1f, Gm.HEIGHT - sizePauseBtn * 0.4f, sizePauseBtn);
        pauseButton.setPositionToCenter();
        pauseButton.completeX();


    }

    public void draw(SpriteBatch batch){



        flyingStage.handle();
        flyingStage.drawGlass(batch);

        if (!flyingStage.isFlying()){
            field.setAlpha(flyingStage.getProgressEnd());
            field.draw(batch);
            if (flyingStage.getProgressEnd() != 1f)
                starBlock.handle(flyingStage.getProgressEnd());
            else
                starBlock.handle();

            pauseButton.setOffsetX((1f - flyingStage.getProgressEnd()) * -pauseButton.getWidth());
            pauseButton.setAlpha(flyingStage.getProgressEnd());
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
            blockGenerator.draw(batch);
            mainBlock.setAlpha(flyingStage.getProgressEnd());
            mainBlock.draw(batch);
            starBlock.drawGlass(batch);
            starBlock.drawMetalAndText(batch);

            pauseButton.drawIcon(batch);
        }
        flyingStage.drawText(batch);

    }

    public void refreshStars(){
        menuUI.refreshStarsData();
    }

    public void minusStep(){
        starBlock.minusStep();
    }

    public void inspectIsChangeStar(){
        starBlock.inspectIsChangeStar();
    }

    public void lose(){
        ButtonActions.activateAction(ButtonActions.All.TOMAINMENU_PAUSE_ACTION);
    }
}
