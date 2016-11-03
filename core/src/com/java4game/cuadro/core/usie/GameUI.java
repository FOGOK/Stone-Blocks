package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.GameUtils;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 17.09.2016 3:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 *
 * StageREALISZTION
 */
public class GameUI {


    //ссылки
//    Handler handler;
    ///


    TextureRegionDrawable normImage, touchedImage;

    LabelStyle textStyle;

    public static final int SIZE_CHAR_FONT = 96;

    Image gemImg, starImg;


    private static Stage stage;

    public static int WIDTH, HEIGHT;
    BitmapFont textFont;
    public GameUI(final TextureGen textureGen) {
        stage = new Stage();


        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
//        Gdx.input.setInputProcessor(stage);

        //изображения
        normImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT1));
        touchedImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT2));
        ///

        gemImg = new Image(new TextureRegionDrawable(textureGen.getTextureRG(Atalas.gemSq)));
        starImg = new Image(new TextureRegionDrawable(textureGen.getTextureRG(Atalas.starSq)));

        //шрифт для текста
        final Color textColor = Color.BLUE;
        final String pathToFont = "font/font.fnt";

        textFont = new BitmapFont(Gdx.files.internal(pathToFont));
        textFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        textStyle = new LabelStyle(textFont, textColor);

        finalFontScale = GameUtils.FINAL_FONT_SCALE;


        //addButtonRestart();
        /////add UIs
        addAllTextBlocks();
        setViewPort();
    }


    private void addAllTextBlocks(){
        addScoreTextBlock();
        addStarTextBlock();
        addGemTextBlock();
        addTimerTextBlock();
        addStageTextBlock();
        addWorldTextBlock();
    }

    float finalFontScale;
    public static void setScore(int score){
//        scoreTextBlock.addAction(Actions.fadeIn(3));
        scoreTextBlock.setText(scoreText + score);
//        scoreTextBlock.getActions().get(0).restart();
//        scoreTextBlock.getActions().get(1).restart();
    }

    public static void setStarCount(int current, int max){
        starTextBlock.setText(current + "/" + max);
    }

    public static void setTime(int h, int m){
        timerTextBlock.setText(timerS + h + ":" + m);
    }



    final float sizeScoreCff = 1f;
    private static Label scoreTextBlock;
    private static String scoreText;
    private void addScoreTextBlock(){
        scoreText = Localization.getText(Localization.LettersKey.SCORE) + "\n";
        scoreTextBlock = new Label(scoreText + "0", textStyle);

        float centerScoreBlockY = scoreTextBlock.getHeight() / 2f;
        float centerScoreBlockX = scoreTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.507f - centerScoreBlockX;
        addTextBlock(scoreTextBlock, sizeScoreCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.105f) - centerScoreBlockY);
    }


    final float sizeStarTBCff = 0.8f;
    private static Label starTextBlock;

    private void addStarTextBlock(){
        starTextBlock = new Label("5/99", textStyle);

        float centerScoreBlockY = starTextBlock.getHeight() / 2f;
        float centerScoreBlockX = starTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.168f - centerScoreBlockX;
        addTextBlock(starTextBlock, sizeStarTBCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.182f) - centerScoreBlockY);

        ///добавляем изображение
        addImageBox(starImg, GameUtils.getHFromBoard(0.033f), GameUtils.getPosXFromUpBoard(0.02f),
                GameUtils.getPosYFromUpBoard(0.197f));
    }

    final float sizeGemTBCff = 0.8f;
    private static Label gemTextBlock;
    private void addGemTextBlock(){
        gemTextBlock = new Label("99", textStyle);

        float centerScoreBlockY = gemTextBlock.getHeight() / 2f;
        float centerScoreBlockX = gemTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.881f - centerScoreBlockX;
        addTextBlock(gemTextBlock, sizeGemTBCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.182f) - centerScoreBlockY);

        ///добавляем изображение
        addImageBox(gemImg, GameUtils.getHFromBoard(0.033f), GameUtils.getPosXFromUpBoard(0.923f),
                GameUtils.getPosYFromUpBoard(0.197f));
    }


    final float sizeTimerTBCff = 1f;
    private static Label timerTextBlock;
    private static String timerS;
    private void addTimerTextBlock(){
        timerS = Localization.getText(Localization.LettersKey.TIME) + " ";
        timerTextBlock = new Label(timerS + "59:59", textStyle);

        float centerScoreBlockY = timerTextBlock.getHeight() / 2f;
        float centerScoreBlockX = timerTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.51f - centerScoreBlockX;
        addTextBlock(timerTextBlock, sizeTimerTBCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.033f) - centerScoreBlockY);
    }

    final float stageTBCff = 0.73f;
    private static Label stageTextBlock;
    private static String stageS;
    private void addStageTextBlock(){
        stageS = Localization.getText(Localization.LettersKey.STAGE) + "\n";
        stageTextBlock = new Label(stageS + "1", textStyle);

        float centerScoreBlockY = stageTextBlock.getHeight() / 2f;
        float centerScoreBlockX = stageTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.907f - centerScoreBlockX;
        addTextBlock(stageTextBlock, stageTBCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.041f) - centerScoreBlockY);
    }

    final float worldTBCff = 0.73f;
    private static Label worldTextBlock;
    private static String worldS;
    private void addWorldTextBlock(){
        worldS = Localization.getText(Localization.LettersKey.WORLD) + "\n";
        worldTextBlock = new Label(worldS + "1", textStyle);

        float centerScoreBlockY = worldTextBlock.getHeight() / 2f;
        float centerScoreBlockX = worldTextBlock.getWidth() / 2f;
        float offSetCenterBlockX = WIDTH * 0.104f - centerScoreBlockX;
        addTextBlock(worldTextBlock, worldTBCff, offSetCenterBlockX, GameUtils.getPosYFromUpBoard(0.041f) - centerScoreBlockY);
    }








    private void addTextBlock(Label label, float sizeCff, float posX, float posY){
        label.setAlignment(Align.center);
        label.setFontScale(finalFontScale * sizeCff);
        label.setPosition(posX, posY);
//        label.addAction(Actions.fadeIn(100f, Interpolation.bounce));
        stage.addActor(label);
    }

    private void addImageBox(Image image, int sizePx, float posX, float posY){
        image.setAlign(Align.center);
        image.setSize(sizePx, sizePx);
        image.setPosition(posX, posY);
        stage.addActor(image);
    }








    private void setViewPort(){
        stage.setViewport(new ScalingViewport(Scaling.stretch, WIDTH, HEIGHT,
                new OrthographicCamera(WIDTH, HEIGHT)));
        stage.getViewport().getCamera().position.set(new Vector3(WIDTH / 2, HEIGHT / 2, 0));
    }





    public void draw(SpriteBatch batch){
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.begin();



    }

    public void dispose() {
        stage.dispose();
        textFont.dispose();
        scoreTextBlock = null;
        gemTextBlock = null;
    }
}

//button
//    private void addButtonRestart(){    //notEnded
//        final Button restartB = new Button(normImage, touchedImage, normImage);
//        restartB.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Handler.ISRESTART = true;
//            }
//        });
//
//        final int sizeWButton = 50,
//                sizeHButton = 50;
//
//        final int posXButton = 50,
//                posYButton = HEIGHT - 50;
//
//
//        restartB.setSize(sizeWButton, sizeHButton);
//        restartB.setPosition(posXButton, posYButton);
//        stage.addActor(restartB);
//    }
////

