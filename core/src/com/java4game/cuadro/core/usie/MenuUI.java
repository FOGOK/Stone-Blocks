package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.CustomFormButton;
import com.java4game.cuadro.core.uiwidgets.List;
import com.java4game.cuadro.core.uiwidgets.SelectWorldButton;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.objects.Blink;
import com.java4game.cuadro.objects.BlockAnimation;
import com.java4game.cuadro.objects.FlyingGlass;
import com.java4game.cuadro.objects.StarBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Prefers;

import java.util.Random;

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


    public static boolean RESETANIMATION;
    public static boolean SETSTAGEPROP;

    //proporties
    private Sprite starIco;

    private Blink blinks[];
    private BlockAnimation blockAnimation;

    private TypeGameBottomBar typeGameBottomBar;

    private int OPENED_WORLDS;
    public static int SELECTEDWORLD;
    private StarBlock.Star[] STARS = new StarBlock.Star[10];

//    public static int[][] STARSINSTAGES = new int[5][2];

    public static int[] OPENEDSTAGESINWORLD;
    public static final int[] COUNTSTAGESINWORLD = new int[] {102, 30, 30, 30, 30};
    ///

    private SelectWorldButton[] selectWorldButtons = new SelectWorldButton[5];
//    private TextButton startButton;
    private CustomFormButton startButton, infoButton, qButton;
    private Sprite backMain, /*upBarMenu, downBarMenu,*/ gameNameTex, selectStageText, bottomBar;

    public static int MENUSTATE;
    public static final int GAMEMAIN = 0, SELECTWORLD = 1, SELECTSTAGE = 2;

    private float sizeTopT;

    private FlyingGlass[] flyingGlasses;

    private List stageList;
    private TextBlock /*stageText, worldText, */starsText;

    private StageButton[] stageButtons;

    private FloatAnimator[] objectAnimations;
    private float[] posYs;

    public MenuUI(TextureGen textureGen) {
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



        addTestedValues();

        setSelectStageTextAndBottomBar();

        setGameNameTex();
        initAnimGlass();
        setStartButton();
        setQButton();
        setInfoButton();

        initSelectWorld(textureGen);

        setStageList(textureGen);

        initBlinks();
        initBlockAnimation();
        initTypeGameBottomBar();

        MusicCore.playSound(8);
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

    private void addTestedValues(){
        SELECTEDWORLD = 0;
        OPENED_WORLDS = 2;  ///количество открытых миров
        OPENEDSTAGESINWORLD = new int[] {1, 12, 17, 18, 28};    ///количество уровней, открытых на каждых мирах
        OPENEDSTAGESINWORLD[0] = Prefers.getInt(Prefers.KeyOpenedStages);

        refreshStarsData();

//        int addTo;
//        for (int i = 0; i < 5; i++) {
//            addTo = 0;
//            for (int i2 = 0; i2 < OPENEDSTAGESINWORLD[i]; i2++) {
//                STARS[i][i2] = 1 + rnd.nextInt(3);      //количество звёзд на каждом уровне в каждом мире
//                addTo += STARS[i][i2];
//            }
//            STARSINSTAGES[i][0] = addTo;
//            STARSINSTAGES[i][1] = COUNTSTAGESINWORLD[i] * 3;
//        }
    }

    public void refreshStarsData(){
        STARS = new StarBlock.Star[COUNTSTAGESINWORLD[0]];
        final char[] starsS = Prefers.getString(Prefers.KeyStars).toCharArray();
        for (int i = 0; i < COUNTSTAGESINWORLD[0]; i++) {
            if (i < OPENEDSTAGESINWORLD[0]){
                int key = Character.getNumericValue(starsS[i]);
                switch (key){
                    case 0:
                        STARS[i] = StarBlock.Star.None;
                        break;
                    case 1:
                        STARS[i] = StarBlock.Star.Bronze;
                        break;
                    case 2:
                        STARS[i] = StarBlock.Star.Silver;
                        break;
                    case 3:
                        STARS[i] = StarBlock.Star.Gold;
                        break;
                }
            }else
                STARS[i] = StarBlock.Star.None;
        }
    }

    private void initSelectWorld(final TextureGen textureGen){
        starIco = textureGen.getSprite(Atalas.star);

        final float otstf = 0.1f;
        final float sizeB = Gm.HEIGHT * 0.101f;
        for (int i = 0; i < selectWorldButtons.length; i++) {
            selectWorldButtons[i] = new SelectWorldButton(textureGen, Gm.WIDTH / 2f, Gm.HEIGHT * 0.702f - (sizeB + otstf) * i, sizeB, i);
            selectWorldButtons[i].setPositionToCenter();
            selectWorldButtons[i].setEnabled(i < OPENED_WORLDS);
        }
        starIco.setBounds(selectWorldButtons[0].getX(), selectWorldButtons[0].getY() + selectWorldButtons[0].getBounds().getHeight() + otstf,
        sizeB * 0.4f, sizeB * 0.4f);


//        int starsCount = 0;
//        for (int i = 0; i < 5; i++) {
//            for (int i2 = 0; i2 < COUNTSTAGESINWORLD[i]; i2++) {
//                starsCount += STARS[i][i2];
//            }
//        }
//        starsText = new TextBlock(starIco.getX() + starIco.getWidth() + otstf, starIco.getY() + 0.15f, true, "" + starsCount);
//        starsText.setCustomCff(starIco.getHeight() * 0.8f);
    }


//    private void initStageAndWorldText(){
//
//        stageText = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT - selectStageText.getHeight() * 1.1f, true, Localization.getText(Localization.LettersKey.SELECTSTAGETEXT));
//        stageText.setCustomCff(1.2f);
//        stageText.setPositionToCenter();
//
//        worldText = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT - selectStageText.getHeight() * 1.1f, true, Localization.getText(Localization.LettersKey.SELECTWORLD));
//        worldText.setCustomCff(1.2f);
//        worldText.setPositionToCenter();
//    }
    private Random rnd = new Random();
    private void setStageList(TextureGen textureGen){
        final float widthList = Gm.WIDTH * 0.76f;
        final float heightList = selectStageText.getY();
        final int columns = 3, rows = 34;
        stageList = new List(textureGen, (Gm.WIDTH - widthList) / 2f, 0, widthList, heightList, columns, rows);
        stageList.setPaddingBottom(0.5f + bottomBar.getHeight());
        stageButtons = new StageButton[columns * rows];

        for (int i = 0; i < columns * rows; i++) {
            if (i != 0)
                stageButtons[i] = new StageButton(ButtonActions.All.RESTART_PAUSE_ACTION, 2.43f, i + 1, this);
            else
                stageButtons[i] = new StageButton(ButtonActions.All.START_LEARN, 2.43f, i + 1, this);
        }
        setStageListPoperties();
    }

    private void setStageListPoperties(){
        final int columns = 3, rows = 34;
        final int countOpened = OPENEDSTAGESINWORLD[SELECTEDWORLD];
        for (int i = 0; i < columns * rows; i++) {
            stageButtons[i].setCompleteStage(i < countOpened - 1 || OPENEDSTAGESINWORLD[SELECTEDWORLD] == i, STARS[i]);
            stageButtons[i].setLockedStage(i >= countOpened);
//            if (i < countOpened)
//                stageButtons[i].setStarCount(STARS[SELECTEDWORLD][i]);
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
                stageList.set(stageButtons[i], column, row);
                if (!isSelectedRow){
                    if (stageButtons[i + 1].isLockedStage()){
                        isSelectedRow = true;
                        selectedRow = row;
                    }
                }
                i++;
            }
        }
        stageList.calculatePadding(0, 0);
        stageList.setToCenter(selectedRow);
    }

    private void setGameNameTex(){
        gameNameTex = Assets.getNewSprite(29);
        final float cffHeight = 0.9446f;
        gameNameTex.setSize(Gm.WIDTH, Gm.WIDTH * cffHeight);
        gameNameTex.setPosition(0f, Gm.HEIGHT - gameNameTex.getHeight());
        posYs[GAME_NAME] = gameNameTex.getHeight();
    }

//    private void setUpBarMenu(TextureGen textureGen){
//        upBarMenu = new Sprite(textureGen.getSprite(Atalas.upBarMenu));
//        final float hDivW = 0.2861f;
//        upBarMenu.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
//        upBarMenu.setPosition(0f, Gm.HEIGHT - upBarMenu.getHeight());
//    }
//    private void setDownBarMenu(TextureGen textureGen){
//        downBarMenu = textureGen.getSprite(Atalas.downBarMenu);
//        final float hDivW = 0.4046f;
//        downBarMenu.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
//    }

    private void setSelectStageTextAndBottomBar(){
        final float cffHeight = 0.3041f;

        bottomBar = Assets.getNewSprite(38);
        bottomBar.setSize(Gm.WIDTH, Gm.WIDTH * cffHeight);
        bottomBar.setPosition(0f, 0f);

        refreshTopBar(0);
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
//        startButton = new Button(textureGen, Atalas.startB, Atalas.startBAct, Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, );;
//        startButton = new TextButton(textureGen, ButtonActions.All.NEXT_MENU_OPTION, Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, 2.43f, Atalas.startB, Atalas.startBAct,
//                Localization.getText(Localization.LettersKey.STARTGAMETEXT));
//        startButton.getTextBlock().setCustomCff(1.215f);

        startButton = new CustomFormButton(CustomFormButton.ButtonsForms.playBForm, ButtonActions.All.NEXT_MENU_OPTION,
                Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, sizeButtonMains,

                22, 23);
        startButton.setPositionToCenter();

        posYs[START_BUTTON] = Gm.HEIGHT - startButton.getY();
    }
    private void setBackground(){
        backMain = Assets.getNewSprite(3);
//        backMain.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final float wDivH = 0.5625f;
        backMain.setSize(Gm.WIDTH, Gm.WIDTH * (1f / wDivH));
        backMain.setPosition(0f, (Gm.HEIGHT - backMain.getHeight()) / 2f);

//        backGrdnt = new Sprite(new Texture(Gdx.files.internal("bg_gradient.png")));
//        backGrdnt.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//        backGrdnt.setSize(Gm.HEIGHT * wDivH, Gm.HEIGHT);
//        backGrdnt.setPosition((Gm.WIDTH - backMain.getWidth()) / 2f, 0f);
    }
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
            case SELECTWORLD:
                backMain.draw(batch);
//                worldText.draw(batch);
                starIco.draw(batch);
                starsText.draw(batch);

                for (int i = 0; i < selectWorldButtons.length; i++) {
                    selectWorldButtons[i].draw(batch);
                }
                break;
            case SELECTSTAGE:
                if (SETSTAGEPROP){
                    SETSTAGEPROP = false;
                    setStageListPoperties();
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








                stageList.draw(batch);
                selectStageText.draw(batch);
                bottomBar.draw(batch);
                typeGameBottomBar.draw(batch);

                break;
        }


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
