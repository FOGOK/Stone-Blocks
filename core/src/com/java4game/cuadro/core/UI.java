package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 17.09.2016 3:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class UI {


    //ссылки
//    Handler handler;
    ///

    TextureRegionDrawable normImage;
    TextureRegionDrawable touchedImage;
    LabelStyle textStyle;

    final int SIZE_CHAR_FONT = 32;


    private static Stage stage;

    int WIDTH, HEIGHT;
    BitmapFont textFont;
    public UI(final TextureGen textureGen, Camera camera) {
        stage = new Stage();


        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
//        Gdx.input.setInputProcessor(stage);

        //изображения для кнопок
        normImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT1));
        touchedImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT2));
        ///

        //шрифт для текста
        final Color textColor = Color.BLUE;
        final String pathToFont = "font/font.fnt";

        textFont = new BitmapFont(Gdx.files.internal(pathToFont));
        textFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textStyle = new LabelStyle(textFont, textColor);

        ///


        //addButtonRestart();
        addScoreTextBlock();
        setViewPort();
    }

    public static void setAllValues(int score){
        scoreTextBlock.setText(scoreText + score);
    }


    private static Label scoreTextBlock;
    private static String scoreText;
    private void addScoreTextBlock(){
        scoreText = Localization.RUS.SCORE + "\n";
        scoreTextBlock = new Label(scoreText + "0", textStyle);
        scoreTextBlock.setAlignment(Align.center);





        float heightCff = HEIGHT / 640f;
        //////          \/start value to scale 0.33f, then optimal effect
        float fontCff = 64f / SIZE_CHAR_FONT;
        scoreTextBlock.setFontScale(fontCff * 0.33f * heightCff);

        scoreTextBlock.setPosition((WIDTH - scoreTextBlock.getWidth()) * 0.515f, HEIGHT - (HEIGHT * LevelGen.backHDivH * 0.105f) - scoreTextBlock.getHeight() / 2f);


        stage.addActor(scoreTextBlock);
    }






    private void setViewPort(){
        stage.setViewport(new ScalingViewport(Scaling.stretch, WIDTH, HEIGHT,
                new OrthographicCamera(WIDTH, HEIGHT)));
        stage.getViewport().getCamera().position.set(new Vector3(WIDTH / 2, HEIGHT / 2, 0));
    }


    private void addButtonRestart(){    //notEnded
        final Button restartB = new Button(normImage, touchedImage, normImage);
        restartB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Handler.ISRESTART = true;
            }
        });

        final int sizeWButton = 50,
                  sizeHButton = 50;

        final int posXButton = 50,
                  posYButton = HEIGHT - 50;


        restartB.setSize(sizeWButton, sizeHButton);
        restartB.setPosition(posXButton, posYButton);
        stage.addActor(restartB);
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
    }
}
