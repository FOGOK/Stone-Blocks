package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.CustomFormButton;
import com.java4game.cuadro.core.uiwidgets.List;
import com.java4game.cuadro.core.uiwidgets.RobotHead;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.objects.Blink;
import com.java4game.cuadro.objects.BlockAnimation;
import com.java4game.cuadro.objects.FlyingGlass;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Prefers;

import static com.java4game.cuadro.core.usie.TypeGameBottomBar.*;

/**
 * Created by FOGOK on 13.10.2016 0:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class MenuUI {

    final static int START_BUTTON = 1;
    final static int Q_BUTTON = 2;
    final static int INFO_BUTTON = 3;
    final static int GAME_NAME = 0;

    final float sizeButtonMains = 3.43f;


    public static boolean TEST;


    public static boolean RESETANIMATION;
    public static boolean SETSTAGEPROP;

    //proporties
    private Sprite starIco;

    private Blink blinks[];
    private BlockAnimation blockAnimation;

    private TypeGameBottomBar typeGameBottomBar;

    public static int SELECTEDWORLD;
    private StarBlock.Star[] STARS_STEPS;
    private StarBlock.Star[] STARS_TIMED;


    public static int[] OPENEDSTAGESINWORLD;
    public static final int[] COUNTSTAGESINWORLD = new int[] {102, 22, 30, 30, 30};
    ///

//    private SelectWorldButton[] selectWorldButtons = new SelectWorldButton[5];
//    private TextButton startButton;
    private CustomFormButton startButton, infoButton, qButton;
    private Sprite backMain, /*upBarMenu, downBarMenu,*/ gameNameTex, selectStageText, bottomBar;

    private TypeGameButton bronzeB, silverB, goldB, normalB, hardB, extremeB;

    public static int MENUSTATE;
    public static final int GAMEMAIN = 0, SELECTWORLD = 1, SELECTSTAGE = 2, INFORMATION = 3, TRAINING = 4;

    private float sizeTopT;

    private InformationScreen informationScreen;
    private TrainingScreen trainingScreen;

    private FlyingGlass[] flyingGlasses;

    private List stepsList, timedList;
    private TextBlock /*stageText, worldText, */starsText;

    private RecordsScreen recordsScreen;

    private StageButton[] stepsStagesButtons, timedStagesButtons;

    private FloatAnimator[] objectAnimations;
    private float[] posYs;

    public MenuUI() {
        setBackground();

        MENUSTATE = GAMEMAIN;

        RESETANIMATION = false;
        SETSTAGEPROP = false;
        final int objCount = 4;     ///4 объекта, кнопки и название игры
        objectAnimations = new FloatAnimator[objCount];
        posYs = new float[objCount];
        for (int i = 0; i < objCount; i++) {
            objectAnimations[i] = new FloatAnimator(0f, 1f, 1f + 0.3f * i, Interpolation.bounceOut);
            objectAnimations[i].setTimer((5f + 0.5f * i) / 10f);
        }

        initInformationAndTrainingScreens();
        setMainVars();

        setSelectStageTextAndBottomBar();

        setGameNameTex();
        initAnimGlass();
        setStartButton();
        setQButton();
        setInfoButton();

        initArkadeButtons();

        initStepsList();
        initTimedList();

        initBlinks();
        initBlockAnimation();
        initTypeGameBottomBar();
        initRecordsScreen();
        initRandomTextAnnotation();
        initRandomButtons();
    }

    private void initRecordsScreen(){
        recordsScreen = new RecordsScreen(typeGameBottomBar.getBounds().height, selectStageText.getY());
    }

    private void initInformationAndTrainingScreens(){
        informationScreen = new InformationScreen();
        trainingScreen = new TrainingScreen();
    }

    private void initTypeGameBottomBar(){
        typeGameBottomBar = new TypeGameBottomBar(this, bottomBar.getHeight());
    }

    private void initAnimGlass(){
        flyingGlasses = new FlyingGlass[35];
        for (int i = 0; i < flyingGlasses.length; i++) {
            flyingGlasses[i] = new FlyingGlass(i, flyingGlasses.length, gameNameTex.getY());
        }
    }

    private void initBlockAnimation(){
        blockAnimation = new BlockAnimation();
        blockAnimation.setY(gameNameTex.getY() + gameNameTex.getHeight() * 0.041f);
    }

    private void initBlinks(){
        blinks = new Blink[9];

        final float posXStart = gameNameTex.getX() + gameNameTex.getWidth() * 0.137f, posYStart = gameNameTex.getY() + gameNameTex.getHeight() * 0.34f;
        final float widthText = gameNameTex.getWidth() * 0.727f, heightText = gameNameTex.getHeight() * 0.399f;

        blinks[0] = new Blink(posXStart + widthText * 0.091f, posYStart + heightText);
        blinks[1] = new Blink(posXStart + widthText * 0.303f, posYStart + heightText);
        blinks[2] = new Blink(posXStart + widthText * 0.516f, posYStart + heightText);
        blinks[3] = new Blink(posXStart + widthText * 0.773f, posYStart + heightText);
        blinks[4] = new Blink(posXStart + widthText * 0.918f, posYStart + heightText * 0.989f);

        blinks[5] = new Blink(posXStart + widthText * 0.109f, posYStart + heightText * 0.438f);
        blinks[6] = new Blink(posXStart + widthText * 0.4f, posYStart + heightText * 0.471f);
        blinks[7] = new Blink(posXStart + widthText * 0.602f, posYStart + heightText * 0.463f);
        blinks[8] = new Blink(posXStart + widthText * 0.899f, posYStart + heightText * 0.471f);
    }

    public static final int ARKADE_TARGET_SILVER = 100, ARKADE_TARGET_GOLD = 1000;

    private final float otstArkButtons = 3.2f;
    private void initArkadeButtons(){
        bronzeB = new TypeGameButton(ButtonActions.All.START_ARKADE_MODE, Gm.WIDTH / 2f, Gm.HEIGHT / 2f + otstArkButtons * 1.2f, otstArkButtons, 33, 0, false, true);
        silverB = new TypeGameButton(ButtonActions.All.START_ARKADE_MODE, Gm.WIDTH / 2f, Gm.HEIGHT / 2f, otstArkButtons, 34, 1, false, true);
        goldB = new TypeGameButton(ButtonActions.All.START_ARKADE_MODE, Gm.WIDTH / 2f, Gm.HEIGHT / 2f - otstArkButtons * 1.2f, otstArkButtons, 35, 2, false, true);
        refreshArkadeButtons();
    }

    private void refreshArkadeButtons(){
        bronzeB.setText(Prefers.getInt(Prefers.KeyRecordBronze) + "");
        bronzeB.setText2(ARKADE_TARGET_SILVER + "");
        bronzeB.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT / 2f + otstArkButtons * 1.2f);
        bronzeB.setPositionToCenter();

        silverB.setText(Prefers.getInt(Prefers.KeyRecordSilver) + "");
        silverB.setText2(ARKADE_TARGET_GOLD + "");
        silverB.setLocked(Prefers.getInt(Prefers.KeyOpenedArkadeModes) < 1);
        silverB.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT / 2f);
        silverB.setPositionToCenter();

        goldB.setText(Prefers.getInt(Prefers.KeyRecordGold) + "");
        goldB.setText2("infinity");
        goldB.setLocked(Prefers.getInt(Prefers.KeyOpenedArkadeModes) < 2);
        goldB.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT / 2f - otstArkButtons * 1.2f);
        goldB.setPositionToCenter();
    }

    private RobotHead robotHead;
    private void initRandomTextAnnotation(){
        robotHead = new RobotHead(typeGameBottomBar.getBounds().getY() + typeGameBottomBar.getBounds().getHeight() * 0.855f);
        robotHead.setText("complete 25/50/75 levels in steps/time mode for open this mode", 0.5f);
    }

    private final float otstRandButtons = 2.6f;
    private float startPos;
    private void initRandomButtons(){
        startPos = robotHead.getTopY() + (selectStageText.getY() - robotHead.getTopY()) / 2f;
        normalB = new TypeGameButton(ButtonActions.All.START_RANDOM_MODE, Gm.WIDTH / 2f, startPos + otstRandButtons * 1.2f, otstRandButtons, 33, 0, false, false);
        hardB = new TypeGameButton(ButtonActions.All.START_RANDOM_MODE, Gm.WIDTH / 2f, startPos, otstRandButtons, 34, 1, false, false);
        extremeB = new TypeGameButton(ButtonActions.All.START_RANDOM_MODE, Gm.WIDTH / 2f, startPos - otstRandButtons * 1.2f, otstRandButtons, 35, 2, false, false);

        refreshRandomButtons();
    }

    private void refreshRandomButtons(){
        int stagesOpenedCount = Prefers.getInt(Prefers.KeyOpenedStagesSteps);
        int timedOpenedCount = Prefers.getInt(Prefers.KeyOpenedStagesTimed);

        normalB.setText("NORMAL");
        normalB.setText2("COUNT:" + Prefers.getInt(Prefers.KeyRandomMode1));
        normalB.setLocked(stagesOpenedCount < 25 && timedOpenedCount < 25);
        normalB.setPosition(Gm.WIDTH / 2f, startPos + otstRandButtons * 1.2f);
        normalB.setPositionToCenter();

        hardB.setText("HARD");
        hardB.setText2("COUNT:" + Prefers.getInt(Prefers.KeyRandomMode2));
        hardB.setLocked(stagesOpenedCount < 50 && timedOpenedCount < 50);
        hardB.setPosition(Gm.WIDTH / 2f, startPos);
        hardB.setPositionToCenter();

        extremeB.setText("EXTREME");
        extremeB.setText2("COUNT:" + Prefers.getInt(Prefers.KeyRandomMode3));
        extremeB.setLocked(stagesOpenedCount < 75 && timedOpenedCount < 75);
        extremeB.setPosition(Gm.WIDTH / 2f, startPos - otstRandButtons * 1.2f);
        extremeB.setPositionToCenter();
    }

    private void setMainVars(){
        SELECTEDWORLD = 0;
        OPENEDSTAGESINWORLD = new int[2];    ///количество уровней, открытых на каждых мирах
        OPENEDSTAGESINWORLD[0] = Prefers.getInt(Prefers.KeyOpenedStagesSteps);
        OPENEDSTAGESINWORLD[1] = Prefers.getInt(Prefers.KeyOpenedStagesTimed);

        refreshStarsData();
    }

    public void refreshStarsData(){
        STARS_STEPS = new StarBlock.Star[COUNTSTAGESINWORLD[0]];
        char[] starsS = Prefers.getString(Prefers.KeyStarsSteps).toCharArray();
        for (int i = 0; i < COUNTSTAGESINWORLD[0]; i++) {
            if (i < OPENEDSTAGESINWORLD[0]){
                int key = Character.getNumericValue(starsS[i]);
                switch (key){
                    case 0:
                        STARS_STEPS[i] = StarBlock.Star.None;
                        break;
                    case 1:
                        STARS_STEPS[i] = StarBlock.Star.Bronze;
                        break;
                    case 2:
                        STARS_STEPS[i] = StarBlock.Star.Silver;
                        break;
                    case 3:
                        STARS_STEPS[i] = StarBlock.Star.Gold;
                    break;
                }
            }else
                STARS_STEPS[i] = StarBlock.Star.None;
        }

        STARS_TIMED = new StarBlock.Star[COUNTSTAGESINWORLD[1]];
        starsS = Prefers.getString(Prefers.KeyStarsTimed).toCharArray();
        for (int i = 0; i < COUNTSTAGESINWORLD[1]; i++) {
            if (i < OPENEDSTAGESINWORLD[1]){
                int key = Character.getNumericValue(starsS[i]);
                switch (key){
                    case 0:
                        STARS_TIMED[i] = StarBlock.Star.None;
                        break;
                    case 1:
                        STARS_TIMED[i] = StarBlock.Star.Bronze;
                        break;
                    case 2:
                        STARS_TIMED[i] = StarBlock.Star.Silver;
                        break;
                    case 3:
                        STARS_TIMED[i] = StarBlock.Star.Gold;
                        break;
                }
            }else
                STARS_TIMED[i] = StarBlock.Star.None;
        }
    }


    private void initStepsList(){
        final float widthList = Gm.WIDTH * 0.76f;
        final float heightList = selectStageText.getY();
        final int columns = 3, rows = 34;
        stepsList = new List((Gm.WIDTH - widthList) / 2f, 0, widthList, heightList, columns, rows);
        stepsList.setPaddingBottom(0.5f + bottomBar.getHeight());
        stepsStagesButtons = new StageButton[columns * rows];

        for (int i = 0; i < columns * rows; i++) {
            stepsStagesButtons[i] = new StageButton(ButtonActions.All.RESTART_PAUSE_ACTION, 2.43f, i + 1, this);
        }
        setStepsListProporties();
    }

    private void setStepsListProporties(){
        final int columns = 3, rows = 34;
        final int countOpened = OPENEDSTAGESINWORLD[0];
        for (int i = 0; i < columns * rows; i++) {
            stepsStagesButtons[i].setCompleteStage(i < countOpened - 1 || OPENEDSTAGESINWORLD[0] == i, STARS_STEPS[i]);
            stepsStagesButtons[i].setLockedStage(i >= countOpened);
        }
        int i = 0, selectedRow = 0;
        boolean isSelectedRow = false;
        boolean isAllStagesOpened = (columns * rows) <= OPENEDSTAGESINWORLD[0];
        if (isAllStagesOpened){
            selectedRow = rows - 1;
            isSelectedRow = true;
        }
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                stepsList.set(stepsStagesButtons[i], column, row);
                if (!isSelectedRow){
                    if (stepsStagesButtons[i + 1].isLockedStage()){
                        isSelectedRow = true;
                        selectedRow = row;
                    }
                }
                i++;
            }
        }
        stepsList.calculatePadding(0, 0);
        stepsList.setToCenter(selectedRow);
    }

    private void initTimedList(){
        final float widthList = Gm.WIDTH * 0.76f;
        final float heightList = selectStageText.getY();
        final int columns = 3, rows = 7;
        timedList = new List((Gm.WIDTH - widthList) / 2f, 0, widthList, heightList, columns, rows);
        timedList.setPaddingBottom(0.5f + bottomBar.getHeight());
        timedStagesButtons = new StageButton[columns * rows];

        for (int i = 0; i < columns * rows; i++) {
            timedStagesButtons[i] = new StageButton(ButtonActions.All.RESTART_PAUSE_ACTION, 2.43f, i + 1, this);
        }
        setTimedListProporties();
    }

    private void setTimedListProporties(){
        final int columns = 3, rows = 7;
        final int countOpened = OPENEDSTAGESINWORLD[1];
        for (int i = 0; i < columns * rows; i++) {
            timedStagesButtons[i].setCompleteStage(i < countOpened - 1 || OPENEDSTAGESINWORLD[1] == i, STARS_TIMED[i]);
            timedStagesButtons[i].setLockedStage(i >= countOpened);
        }
        int i = 0, selectedRow = 0;
        boolean isSelectedRow = false;
        boolean isAllStagesOpened = (columns * rows) <= OPENEDSTAGESINWORLD[1];
        if (isAllStagesOpened){
            selectedRow = rows - 1;
            isSelectedRow = true;
        }
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                timedList.set(timedStagesButtons[i], column, row);
                if (!isSelectedRow){
                    if (timedStagesButtons[i + 1].isLockedStage()){
                        isSelectedRow = true;
                        selectedRow = row;
                    }
                }
                i++;
            }
        }
        timedList.calculatePadding(0, 0);
        timedList.setToCenter(selectedRow);
    }

    private void setGameNameTex(){
        gameNameTex = Assets.getNewSprite(29);
        final float cffHeight = 0.9446f;
        gameNameTex.setSize(Gm.WIDTH, Gm.WIDTH * cffHeight);
        gameNameTex.setPosition(0f, Gm.HEIGHT - gameNameTex.getHeight());

        gameNameTex.setFlip(false, TEST);
        posYs[GAME_NAME] = gameNameTex.getHeight();
    }

    private void setSelectStageTextAndBottomBar(){
        final float cffHeight = 0.3041f;

        bottomBar = Assets.getNewSprite(38);
        bottomBar.setSize(Gm.WIDTH, Gm.WIDTH * cffHeight);
        bottomBar.setPosition(0f, 0f);

        refreshTopBar(2);
    }

    public void refreshTopBar(int i){
        selectStageText = Assets.getNewSprite(i + 51);
        final float cffHeight = 0.3041f;
        selectStageText.setSize(Gm.WIDTH, Gm.WIDTH * cffHeight);
        selectStageText.setPosition(0f, Gm.HEIGHT - selectStageText.getHeight());
    }

    private void setInfoButton(){
        final float size = sizeButtonMains * 0.46f;
        infoButton = new CustomFormButton(CustomFormButton.ButtonsForms.infoBForm, ButtonActions.All.INFO_ACT,
                startButton.getX() + startButton.getWidth() * 0.672f, startButton.getY() - startButton.getHeight() * 0.34f, size,
                20, 21);
        posYs[INFO_BUTTON] = infoButton.getY() + size;
    }

    private void setQButton(){
        final float size = sizeButtonMains / 2f;
        qButton = new CustomFormButton(CustomFormButton.ButtonsForms.quesBForm, ButtonActions.All.QUESTION_ACT,
                startButton.getX() - size * 0.585f, startButton.getY() + startButton.getHeight() * 0.778f, size,
                18, 19);
        posYs[Q_BUTTON] = Gm.HEIGHT - qButton.getY();
    }

    private void setStartButton(){
        final float otstTop = 14.6f;

        startButton = new CustomFormButton(CustomFormButton.ButtonsForms.playBForm, ButtonActions.All.NEXT_MENU_OPTION,
                Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, sizeButtonMains,

                22, 23);
        startButton.setPositionToCenter();

        posYs[START_BUTTON] = Gm.HEIGHT - startButton.getY();
    }
    private void setBackground(){
        backMain = Assets.getNewSprite(3);
        final float wDivH = 0.5625f;
        backMain.setSize(Gm.WIDTH, Gm.WIDTH * (1f / wDivH));
        backMain.setPosition(0f, (Gm.HEIGHT - backMain.getHeight()) / 2f);
    }

    private int lastMenuState, lastSelectedBtn;
    public void draw(SpriteBatch batch){
        int dstFunc;
        int srcFunc;

        switch (MENUSTATE){
            case GAMEMAIN:
                calcAnim();

                if (!objectAnimations[GAME_NAME].isNeedToUpdate()){
                    for (int i = 0; i < flyingGlasses.length; i++) {
                        flyingGlasses[i].draw(batch);
                    }
                }

                batch.end();

                srcFunc = batch.getBlendSrcFunc();
                dstFunc = batch.getBlendDstFunc();

                batch.enableBlending();
                batch.begin();

                batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

                backMain.draw(batch);

                batch.end();
                batch.begin();
                batch.setBlendFunction(srcFunc, dstFunc);

                startButton.draw(batch);        // 2 объект
                qButton.draw(batch);        // 3 объект
                infoButton.draw(batch);

                gameNameTex.draw(batch);    // 1 объект


                if (!objectAnimations[GAME_NAME].isNeedToUpdate()){  //если анимация прошла, тогда отрисовываем анимацию езжущего блока
                    blockAnimation.draw(batch);

                    for (int i = 0; i < blinks.length; i++) {
                        blinks[i].draw(batch);
                    }
                }

                break;
//            case SELECTWORLD:
//                backMain.draw(batch);
//                starIco.draw(batch);
//                starsText.draw(batch);
//
//                for (int i = 0; i < selectWorldButtons.length; i++)
//                    selectWorldButtons[i].draw(batch);
//
//                break;
            case SELECTSTAGE:
                if (SETSTAGEPROP){
                    SETSTAGEPROP = false;
                    setStepsListProporties();
                    setTimedListProporties();
                    refreshArkadeButtons();
                    refreshRandomButtons();
                }

                for (int i = 0; i < flyingGlasses.length; i++) {
                    flyingGlasses[i].draw(batch);
                }

                batch.end();

                srcFunc = batch.getBlendSrcFunc();
                dstFunc = batch.getBlendDstFunc();

                batch.enableBlending();
                batch.begin();

                batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

                backMain.draw(batch);

                batch.end();
                batch.begin();
                batch.setBlendFunction(srcFunc, dstFunc);

                switch (SELECTED_BTN){
                    case TYPE_STEPS:
                        stepsList.draw(batch);
                        break;
                    case TYPE_TIMED:
                        timedList.draw(batch);
                        break;
                    case TYPE_ARKADE:
                        bronzeB.draw(batch);
                        silverB.draw(batch);
                        goldB.draw(batch);
                        break;
                    case TYPE_RANDOM:
                        normalB.draw(batch);
                        hardB.draw(batch);
                        extremeB.draw(batch);
                        robotHead.draw(batch);
                        break;
                    case TYPE_RECORDS:
                        recordsScreen.draw(batch);
                        break;
                }

                selectStageText.draw(batch);
                bottomBar.draw(batch);

                typeGameBottomBar.draw(batch);
                break;
            case INFORMATION:

                for (int i = 0; i < flyingGlasses.length; i++) {
                    flyingGlasses[i].draw(batch);
                }

                batch.end();

                srcFunc = batch.getBlendSrcFunc();
                dstFunc = batch.getBlendDstFunc();

                batch.enableBlending();
                batch.begin();

                batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

                backMain.draw(batch);

                batch.end();
                batch.begin();
                batch.setBlendFunction(srcFunc, dstFunc);

                informationScreen.draw(batch);

                break;
            case TRAINING:

                for (int i = 0; i < flyingGlasses.length; i++) {
                    flyingGlasses[i].draw(batch);
                }

                batch.end();

                srcFunc = batch.getBlendSrcFunc();
                dstFunc = batch.getBlendDstFunc();

                batch.enableBlending();
                batch.begin();

                batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

                backMain.draw(batch);

                batch.end();
                batch.begin();
                batch.setBlendFunction(srcFunc, dstFunc);

                trainingScreen.draw(batch);
                break;
        }

        if (lastMenuState != MENUSTATE || lastSelectedBtn != SELECTED_BTN){
            robotHead.show();
            informationScreen.reset();
        }
        lastMenuState = MENUSTATE;
        lastSelectedBtn = SELECTED_BTN;


//        upBarMenu.draw(batch);
//        downBarMenu.draw(batch);
    }

    private void calcAnim(){
        boolean resetAnim = false;
        if (RESETANIMATION){
            initAnimGlass();
            resetAnim = true;
            RESETANIMATION = false;
//            infoButton.setPosition(infoButton.getX(), posYs[2] - infoButton.getHeight());
//            qButton.setPosition(qButton.getX(), Gm.HEIGHT - posYs[1]);
//            startButton.setPosition(startButton.getX(), Gm.HEIGHT - posYs[0]);
        }
        for (int i = 0; i < objectAnimations.length; i++) {
            FloatAnimator objAnim = objectAnimations[i];

            if (resetAnim)
                objAnim.resetTime();

            objAnim.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            final float whoY = posYs[i] * objAnim.current;

            switch (i){
                case INFO_BUTTON:
                    infoButton.setPosition(infoButton.getX(), whoY - infoButton.getHeight());
                    break;
                case Q_BUTTON:
                    qButton.setPosition(qButton.getX(), Gm.HEIGHT - whoY);
                    break;
                case START_BUTTON:
                    startButton.setPosition(startButton.getX(), Gm.HEIGHT - whoY);
                    break;
                case GAME_NAME:
                    gameNameTex.setPosition(gameNameTex.getX(), Gm.HEIGHT - whoY);
                    break;
            }
        }
    }

    public void dispose() {

    }
}
