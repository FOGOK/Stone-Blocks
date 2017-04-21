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
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.core.usie.GameOverUI;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.objects.ArkadeBlock;
import com.java4game.cuadro.objects.FlyingStage;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.objects.TimerBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Prefers;

import java.util.Random;

import static com.java4game.cuadro.core.usie.TypeGameBottomBar.SELECTED_BTN;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_ARKADE;
import static com.java4game.cuadro.core.usie.TypeGameBottomBar.TYPE_RANDOM;
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

    private boolean ISARKADE, ISRANDOM;

    public static boolean REFRESH_REFRESH;
    public static boolean ISGAMEOVER;

    private boolean isStartAnimNewRecord;

    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;


    //ui
    private FlyingStage flyingStage, arkadeNewRecord;
    private StarBlock starBlock;
    private TimerBlock timerBlock;
    private ArkadeBlock arkadeBlock;
    private RestartButton restartButton;
    private GameOverUI gameOverUI;
    ///

    private MenuUI menuUI;

    private InitLevels.Level levelTEST;
    private Random rnd = new Random();

    public LevelGen(MenuUI menuUI) {
        ISARKADE = StageButton.ARKADE_LEVEL == StageButton.LEVEL;
        ISRANDOM = StageButton.RANDOM_LEVEL == StageButton.LEVEL;

        //инициализируем фон
        this.menuUI = menuUI;
        InitLevels.Level currLevel = null;
        if (MenuUI.TEST){
            createTESTLevel();
            currLevel = levelTEST;
            background = Assets.getNewSprite(currLevel.getBackgroundColor());
        }else{
            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    currLevel = InitLevels.getStepsLevels(StageButton.LEVEL - 1);
                    background = Assets.getNewSprite(currLevel.getBackgroundColor());
                    break;
                case TYPE_TIMED:
                    currLevel = InitLevels.getTimeLevels(StageButton.LEVEL - 1);
                    background = Assets.getNewSprite(currLevel.getBackgroundColor());
                    break;
                case TYPE_ARKADE:
                case TYPE_RANDOM:
                    background = Assets.getNewSprite(rnd.nextBoolean() ? 0 : (2 + rnd.nextInt(4)));
                    break;
            }
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
        int mode = 0;
        if (ISARKADE)
            mode = 1;
        else if (ISRANDOM)
            mode = 2;

        blockGenerator = new BlockGenerator(this, mainBlock, fieldBounds, currLevel, mode);
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
        flyingStage = new FlyingStage(true);
        flyingStage.setNew(StageButton.LEVEL - 1, flyStageColor, mode);
        if (!REFRESH_REFRESH)
            flyingStage.refreshRefresh();
        //
        float starSize = 0f;
        switch (SELECTED_BTN){
            case TYPE_STEPS:
                starBlock = new StarBlock(this, currLevel.getMinSteps() == 0 ? blockGenerator.getCountMinSteps() : currLevel.getMinSteps());
                starSize = starBlock.getStarSize();
                break;
            case TYPE_TIMED:
                timerBlock = new TimerBlock(this, currLevel.getAllSeconds() == 0f ? blockGenerator.getCountMinSteps() * 6f : currLevel.getAllSeconds());
                starSize = timerBlock.getStarSize();
                break;
            case TYPE_ARKADE:
                arkadeBlock = new ArkadeBlock(TypeGameButton.TOUCHED_ARK, false);
                arkadeBlock.showAnimate();
                blockGenerator.setArkadeBlock(arkadeBlock);
                starSize = arkadeBlock.getStarSize();
                break;
            case TYPE_RANDOM:
                arkadeBlock = new ArkadeBlock(2, true);
                arkadeBlock.setScore(blockGenerator.getFieldObjectsSize() / 2);
                arkadeBlock.showAnimate();
                blockGenerator.setArkadeBlock(arkadeBlock);
                starSize = arkadeBlock.getStarSize();
                break;
        }

        gameOverUI = new GameOverUI(starSize, fieldBounds.getY(), mode);

        final float sizePauseBtn = 2.3f;
        restartButton = new RestartButton(ButtonActions.All.PAUSE_ACT, 0.1f, Gm.HEIGHT - sizePauseBtn * 0.4f, sizePauseBtn);
        restartButton.setPositionToCenter();
        restartButton.completeX();

        pauseBInterp = Interpolation.exp5Out;

        if (ISARKADE){
            arkadeNewRecord = new FlyingStage(false);
            arkadeNewRecord.refreshRefresh();
        }

        MusicCore.playSound(8);
    }
    private void createTESTLevel(){
        String[] mainParts = Handler.TEST_STRING.split(",");
        String[] allObjects = mainParts[2].split("_");

        SELECTED_BTN = Integer.parseInt(mainParts[0]);
        StageButton.LEVEL = Integer.parseInt(mainParts[1]) + 1;


        levelTEST = new InitLevels.Level(allObjects.length, Integer.parseInt(mainParts[3]));

        for (int i = 0; i < allObjects.length; i++) {
            levelTEST.setObject(new InitLevels.Object(
                    Character.getNumericValue(allObjects[i].charAt(0)),
                    Character.getNumericValue(allObjects[i].charAt(1)),
                    Character.getNumericValue(allObjects[i].charAt(2)),
                    Character.getNumericValue(allObjects[i].charAt(3))), i);
        }


        if (Integer.parseInt(mainParts[4]) != -1){
            switch (SELECTED_BTN){
                case TYPE_STEPS:
                    levelTEST.setMinSteps(Integer.parseInt(mainParts[4]));
                    break;
                case TYPE_TIMED:
                    levelTEST.setGoldSeconds(Float.parseFloat(mainParts[4]));
                    break;
            }
        }
    }


    private Interpolation pauseBInterp;

    private boolean arkadeSpecialAlphaFlag;
    public void draw(SpriteBatch batch){



        flyingStage.handle();
        flyingStage.drawGlass(batch);

        if (arkadeNewRecord != null){
            arkadeNewRecord.handle();
            arkadeNewRecord.drawGlass(batch);
        }

        float gameTransparency = ISARKADE ? arkadeNewRecord.getAlphaInNewRecordArkade() : flyingStage.getProgressEnd();

        if (!flyingStage.isFlying()){

            field.setAlpha(gameTransparency);
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
                case TYPE_ARKADE:

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
            if (flyingStage.getProgressEnd() != 1f)
                blockGenerator.setAlpha(flyingStage.getProgressEnd());
            else if (!arkadeSpecialAlphaFlag){
                arkadeSpecialAlphaFlag = true;
                blockGenerator.setAlpha(1f);
            }

            blockGenerator.drawHoles(batch);
            if (gameTransparency != 1f)
                mainBlock.setAlpha(gameTransparency);

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
                case TYPE_ARKADE:
                case TYPE_RANDOM:
                    if (ISGAMEOVER){
                        gameOverUI.drawBack(batch);     //чёрная подложка
                        arkadeBlock.drawStar(batch);     //звезда
                        gameOverUI.draw(batch); //текст окончания игры и кнопки
                        arkadeBlock.drawStarBlinks(batch);     //блики
                    }else{
                        arkadeBlock.draw(batch);

                        if (isStartAnimNewRecord){
                            blockGenerator.setAlpha(gameTransparency);
                            isStartAnimNewRecord = arkadeNewRecord.isShow();
                            Handler.ISPAUSE = arkadeNewRecord.isShow();
                        }
                    }
                    break;
            }

            restartButton.drawIcon(batch);
        }
        flyingStage.drawText(batch);
        if (arkadeNewRecord != null)
            arkadeNewRecord.drawText(batch);

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
        int scoreText = 0;
        if (!MenuUI.TEST){

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
                case TYPE_RANDOM:
                    switch (TypeGameButton.RNDLEVEL){
                        case 0:
                            scoreText = Prefers.getInt(Prefers.KeyRandomMode1) + 1;
                            Prefers.putInt(Prefers.KeyRandomMode1, scoreText);
                            break;
                        case 1:
                            scoreText = Prefers.getInt(Prefers.KeyRandomMode2) + 1;
                            Prefers.putInt(Prefers.KeyRandomMode2, scoreText);
                            break;
                        case 2:
                            scoreText = Prefers.getInt(Prefers.KeyRandomMode3) + 1;
                            Prefers.putInt(Prefers.KeyRandomMode3, scoreText);
                            break;
                    }
                    break;
            }

        }

        ISGAMEOVER = true;
        if (ISRANDOM){
            gameOverUI.setSpecialText(scoreText);
            gameOverUI.setRecord(true);
        }
        gameOverUI.setText(true);
    }

    public void lose(){
        ISGAMEOVER = true;
        gameOverUI.setText(false);
    }

    public void flyTextPls(int mode){
        isStartAnimNewRecord = true;
        if (mode < 2)
            arkadeNewRecord.startT("NEW", mode == 0 ? "MODE!" : "RECORD!");
        else if (mode == 2)
            arkadeNewRecord.startT("WOOW", "MAX!!!");

    }

    public Rectangle getFieldBounds() {
        return fieldBounds;
    }

    public void arkadeLose(int score, boolean isNewRecord){
        ISGAMEOVER = true;
        gameOverUI.setScoreText(score, false);
        gameOverUI.setRecord(isNewRecord);
    }

    public void dispose(){
        gameOverUI.dispose();
    }
}
