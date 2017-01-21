package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.RestartButton;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.usie.GameOverUI;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.FlyingStage;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.objects.TimerBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Prefers;

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
    public static boolean ISGAMEOVER;

    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;



    //ui
    private FlyingStage flyingStage;
    private StarBlock starBlock;
    private TimerBlock timerBlock;
    private RestartButton restartButton;
    private GameOverUI gameOverUI;
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
        ISGAMEOVER = false;
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
        float starSize = 0f;
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                currLevel = InitLevels.getStepsLevels(StageButton.LEVEL - 1);
                starBlock = new StarBlock(this, currLevel.getMinSteps() == 0 ? blockGenerator.getCountMinSteps() : currLevel.getMinSteps());
                blockGenerator.setStarBlock(starBlock);
                starSize = starBlock.getStarSize();
                break;
            case TYPE_TIMED:
                 currLevel = InitLevels.getTimeLevels(StageButton.LEVEL - 1);
                timerBlock = new TimerBlock(this, currLevel.getAllSeconds() == 0f ? blockGenerator.getCountMinSteps() * 6f : currLevel.getAllSeconds());
                blockGenerator.setTimerBlockBlock(timerBlock);
                starSize = timerBlock.getStarSize();
                break;
        }

        gameOverUI = new GameOverUI(starSize, fieldBounds.getY());

        final float sizePauseBtn = 2.3f;
        restartButton = new RestartButton(ButtonActions.All.RESTART_PAUSE_ACTION, 0.1f, Gm.HEIGHT - sizePauseBtn * 0.4f, sizePauseBtn);
        restartButton.setPositionToCenter();
        restartButton.completeX();

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
                        starBlock.handle(flyingStage.getProgressEnd(), false);
                    else{
                        if (!ISGAMEOVER)
                            starBlock.handle();
                        else
                            starBlock.handle(gameOverUI.getTime(), true);
                    }


                    break;
                case TYPE_TIMED:
                    if (flyingStage.getProgressEnd() != 1f)
                        timerBlock.handle(flyingStage.getProgressEnd(), false);
                    else{
                        if (!ISGAMEOVER)
                            timerBlock.handle();
                        else
                            timerBlock.handle(gameOverUI.getTime(), true);
                    }
                    if (!Handler.ISPAUSE && !ISGAMEOVER)
                        timerBlock.handleLogic();

                    break;
            }


            restartButton.setOffsetX(pauseBInterp.apply(-restartButton.getWidth(), 0f, flyingStage.getProgressEnd()));
            restartButton.setAlpha(pauseBInterp.apply(0f, 1f, flyingStage.getProgressEnd()));
            restartButton.setEnabled(!Handler.ISPAUSE && !ISGAMEOVER);
            restartButton.draw(batch);
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
                    if (ISGAMEOVER){
                        starBlock.draw(batch); //текст
                        gameOverUI.drawBack(batch);     //чёрная подложка
                        starBlock.drawStar(batch);     //звезда
                        gameOverUI.draw(batch); //текст окончания игры и кнопки
                        starBlock.drawBlinks(batch);     //блики
                    }else {
                        starBlock.drawStar(batch);     //звезда
                        starBlock.draw(batch); //текст
                        starBlock.drawBlinks(batch);     //блики
                    }
                    break;
                case TYPE_TIMED:

                    if (ISGAMEOVER){
                        timerBlock.draw(batch); //текст
                        gameOverUI.drawBack(batch);     //чёрная подложка
                        timerBlock.drawStar(batch);     //звезда
                        gameOverUI.draw(batch); //текст окончания игры и кнопки
                        timerBlock.drawStarBlinks(batch);     //блики
                    }else {
                        timerBlock.drawStar(batch);     //звезда
                        timerBlock.draw(batch); //текст
                        timerBlock.drawStarBlinks(batch);     //блики
                    }

                    break;
            }

            restartButton.drawIcon(batch);
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

    public void win(int LEVEL){
//        Handler.state = Handler.State.Menu;
//        MenuUI.MENUSTATE = MenuUI.SELECTSTAGE;
//        MenuUI.SETSTAGEPROP = true;
//        MusicCore.play(MusicCore.MENU);

        int curStar = 1;
        char[] chars;
        //setStar
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                chars = Prefers.getString(Prefers.KeyStarsSteps).toCharArray();
                curStar = starBlock.getCurrentStar().ordinal();
                if (curStar > Character.getNumericValue(chars[LEVEL]))
                    chars[LEVEL] = Integer.toString(curStar).charAt(0);
                Prefers.putString(Prefers.KeyStarsSteps, new String(chars));
                refreshStars();
                break;
            case TYPE_TIMED:
                chars = Prefers.getString(Prefers.KeyStarsTimed).toCharArray();
                curStar = timerBlock.getCurrentStar().ordinal();
                if (curStar > Character.getNumericValue(chars[LEVEL - 1]))
                    chars[LEVEL - 1] = Integer.toString(curStar).charAt(0);
                Prefers.putString(Prefers.KeyStarsTimed, new String(chars));
                refreshStars();
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

        ISGAMEOVER = true;
        gameOverUI.setText(true);
    }

    public void lose(){
        ISGAMEOVER = true;
        gameOverUI.setText(false);
    }

    public void dispose(){
        gameOverUI.dispose();
    }
}
