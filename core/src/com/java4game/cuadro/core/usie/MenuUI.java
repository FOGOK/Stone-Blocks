package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.List;
import com.java4game.cuadro.core.uiwidgets.SelectWorldButton;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.core.uiwidgets.TextButton;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Localization;

import java.util.Random;

/**
 * Created by FOGOK on 13.10.2016 0:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class MenuUI {

    final static int GAME_NAME = 0;
    final static int START_BUTTON = 1;

    public static boolean RESETANIMATION;
    public static boolean SETSTAGEPROP;

    //proporties

    private int OPENED_WORLDS;
    public static int SELECTEDWORLD;
    private int[][] STARS = new int[5][100];

    private int[][] STARSINSTAGES = new int[5][2];

    private int[] OPENEDSTAGESINWORLD;
    private final int[] COUNTSTAGESINWORLD = new int[] {30, 30, 30, 30, 30};
    ///

    private SelectWorldButton[] selectWorldButtons = new SelectWorldButton[5];
    private TextButton startButton;
    private Sprite backMain, backGrdnt, upBarMenu, downBarMenu, gameNameTex;

    public static int MENUSTATE;
    public static final int GAMEMAIN = 0, SELECTWORLD = 1, SELECTSTAGE = 2;

    private List stageList;
    private TextBlock stageText, worldText;

    private StageButton[] stageButtons;

    FloatAnimator[] objectAnimations;
    float[] posYs;

    public MenuUI(TextureGen textureGen) {
        setBackground();

        MENUSTATE = GAMEMAIN;

        RESETANIMATION = false;
        SETSTAGEPROP = false;
        final int objCount = 2;     ///2 объекта, название игры и кнопка
        objectAnimations = new FloatAnimator[objCount];
        posYs = new float[objCount];
        for (int i = 0; i < objCount; i++) {
            objectAnimations[i] = new FloatAnimator(0f, 1f, 1f + 0.3f * i, Interpolation.bounceOut);
        }

        addTestedValues();

        setUpBarMenu(textureGen);
        setDownBarMenu(textureGen);

        setGameNameTex(textureGen);
        setStartButton(textureGen);

        initSelectWorldButtons(textureGen);

        initStageAndWorldText();

        setStageList(textureGen);
    }

    private void addTestedValues(){
        SELECTEDWORLD = 4;
        OPENED_WORLDS = 2;  ///количество открытых миров
        OPENEDSTAGESINWORLD = new int[] {6, 12, 17, 18, 28};    ///количество уровней, открытых на каждых мирах

        int addTo;
        for (int i = 0; i < 5; i++) {
            addTo = 0;
            for (int i2 = 0; i2 < COUNTSTAGESINWORLD[i]; i2++) {
                STARS[i][i2] = 1 + rnd.nextInt(3);      //количество звёзд на каждом уровне в каждом мире
                addTo += STARS[i][i2];
            }
            STARSINSTAGES[i][0] = addTo;
            STARSINSTAGES[i][1] = COUNTSTAGESINWORLD[i] * 3;
        }
    }

    private void initSelectWorldButtons(final TextureGen textureGen){
        final float otstf = 0.1f;
        final float sizeB = Gm.HEIGHT * 0.101f;
        for (int i = 0; i < selectWorldButtons.length; i++) {
            selectWorldButtons[i] = new SelectWorldButton(textureGen, Gm.WIDTH / 2f, Gm.HEIGHT * 0.712f - (sizeB + otstf) * i, sizeB, i);
            selectWorldButtons[i].setPositionToCenter();
        }
    }

    private void initStageAndWorldText(){
        stageText = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT - upBarMenu.getHeight() * 1.1f, true, Localization.getText(Localization.LettersKey.SELECTSTAGETEXT));
        stageText.setPositionToCenter();

        worldText = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT - upBarMenu.getHeight() * 1.1f, true, Localization.getText(Localization.LettersKey.SELECTWORLD));
        worldText.setPositionToCenter();
    }
    private Random rnd = new Random();
    private void setStageList(TextureGen textureGen){
        final float widthList = stageText.getBounds().width;
        final float heightList = stageText.getBounds().y - downBarMenu.getHeight() * 0.7f;
        final int columns = 3, rows = 10;
        stageList = new List(textureGen, (Gm.WIDTH - widthList) / 2f, downBarMenu.getHeight() * 0.7f, widthList, heightList, columns, rows);
        stageButtons = new StageButton[columns * rows];

        for (int i = 0; i < columns * rows; i++) {
            stageButtons[i] = new StageButton(textureGen, ButtonActions.All.RESTART_PAUSE_ACTION, 2.43f, i + 1);
        }

        setStageListPoperties();
    }

    private void setStageListPoperties(){
        final int columns = 3, rows = 10;
        final int countOpened = OPENEDSTAGESINWORLD[SELECTEDWORLD];
        for (int i = 0; i < columns * rows; i++) {
            stageButtons[i].setLockedStage(i >= countOpened);
            if (i < countOpened)
                stageButtons[i].setStarCount(STARS[SELECTEDWORLD][i]);
        }
        int i = 0, selectedRow = 0;
        boolean isSelectedRow = false;
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

    private void setGameNameTex(TextureGen textureGen){
        gameNameTex = new Sprite(textureGen.getSprite(Atalas.gameNameT));
        final float cffWidth = 0.68f;
        final float hDivW = 0.5741f;
        final float otstTop = 7.2f;
        gameNameTex.setSize(Gm.WIDTH * cffWidth, Gm.WIDTH * cffWidth * hDivW);
        gameNameTex.setPosition((Gm.WIDTH - Gm.WIDTH * cffWidth) / 2f, Gm.HEIGHT - otstTop);
        posYs[0] = otstTop;

    }
    private void setUpBarMenu(TextureGen textureGen){
        upBarMenu = new Sprite(textureGen.getSprite(Atalas.upBarMenu));
        final float hDivW = 0.2861f;
        upBarMenu.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
        upBarMenu.setPosition(0f, Gm.HEIGHT - upBarMenu.getHeight());
    }
    private void setDownBarMenu(TextureGen textureGen){
        downBarMenu = textureGen.getSprite(Atalas.downBarMenu);
        final float hDivW = 0.4046f;
        downBarMenu.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
    }

    private void setStartButton(TextureGen textureGen){
        final float otstTop = 14f;
//        startButton = new Button(textureGen, Atalas.startB, Atalas.startBAct, Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, );;
        startButton = new TextButton(textureGen, ButtonActions.All.NEXT_MENU_OPTION, Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, 2.43f, Atalas.startB, Atalas.startBAct,
                Localization.getText(Localization.LettersKey.STARTGAMETEXT));
        startButton.setPositionToCenter();

        posYs[1] = otstTop;
    }
    private void setBackground(){
        backMain = new Sprite(new Texture(Gdx.files.internal("bg_menu.png")));
        backMain.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final float wDivH = 0.5625f;
        backMain.setSize(Gm.HEIGHT * wDivH, Gm.HEIGHT);
        backMain.setPosition((Gm.WIDTH - backMain.getWidth()) / 2f, 0f);

        backGrdnt = new Sprite(new Texture(Gdx.files.internal("bg_gradient.png")));
        backGrdnt.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backGrdnt.setSize(Gm.HEIGHT * wDivH, Gm.HEIGHT);
        backGrdnt.setPosition((Gm.WIDTH - backMain.getWidth()) / 2f, 0f);
    }
    public void draw(SpriteBatch batch){

        switch (MENUSTATE){
            case GAMEMAIN:
                calcAnim();

                backMain.draw(batch);

                gameNameTex.draw(batch);    // 1 объект
                startButton.draw(batch);        // 2 объект

                break;
            case SELECTWORLD:
                backGrdnt.draw(batch);
                worldText.draw(batch);

                for (int i = 0; i < selectWorldButtons.length; i++) {
                    selectWorldButtons[i].draw(batch);
                }

                if (SETSTAGEPROP)
                    setStageListPoperties();
                break;
            case SELECTSTAGE:
                backGrdnt.draw(batch);
                stageList.draw(batch);
                stageText.draw(batch);
                break;
        }

        upBarMenu.draw(batch);
        downBarMenu.draw(batch);
    }

    private void calcAnim(){
        boolean resetAnim = false;
        if (RESETANIMATION){
            resetAnim = true;
            RESETANIMATION = false;
        }
        for (int i = 0; i < objectAnimations.length; i++) {
            FloatAnimator objAnim = objectAnimations[i];

            if (resetAnim)
                objAnim.resetTime();

            objAnim.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            float whoY = posYs[i] * objAnim.current;

            switch (i){
                case GAME_NAME:
                    gameNameTex.setY(Gm.HEIGHT - whoY);
                    break;
                case START_BUTTON:
                    startButton.setPosition(startButton.getX(), Gm.HEIGHT - whoY);
                    break;
            }
        }


    }

    public void dispose() {
        backMain.getTexture().dispose();
    }
}
