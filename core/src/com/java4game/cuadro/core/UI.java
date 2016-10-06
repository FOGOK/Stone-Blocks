package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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


    boolean restarting = false;

    static Stage stage;

    int WIDTH, HEIGHT;
    public UI(final TextureGen textureGen) {
        stage = new Stage();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(stage);

        //изображения для кнопок
        normImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT1));
        touchedImage = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT2));
        ///

        //шрифт для текста
        final Color textColor = Color.BLUE;
        final String pathToFont = "assets/font/krk.fnt";

        textStyle = new LabelStyle(new BitmapFont(Gdx.files.internal(pathToFont)), textColor);
        ///


        //addButtonRestart();
        addScoreTextBlock();
    }

    public static void setAllValues(int score){
        scoreTextBlock.setText(scoreText = score + "");
    }


    static Label scoreTextBlock;
    static String scoreText;
    private void addScoreTextBlock(){
        scoreText = Localization.RUS.SCORE + "\n";
        scoreTextBlock = new Label(scoreText + "0", textStyle);
        scoreTextBlock.setAlignment(Align.center);

        final int posXTextBlock = 20, posYTextBlock = 20;
        scoreTextBlock.setPosition(posXTextBlock, posYTextBlock);

        stage.addActor(scoreTextBlock);
    }


    private void addButtonRestart(){
        final Button restartB = new Button(normImage, touchedImage, normImage);
        restartB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Handler.ISRESTART = true;
            }
        });

        final int sizeWButton = 50, sizeHButton = 50;
        final int posXButton = 50, posYButton = HEIGHT - 50;

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
    }
}
