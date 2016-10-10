package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.java4game.cuadro.utils.DebugValueChanger;
import com.java4game.cuadro.utils.GameUtils;

/**
 * Created by FOGOK on 10.10.2016 16:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ComboVombo {
//
//    int posX = Gdx.graphics.getWidth() / 2;
//    int posY = Gdx.graphics.getHeight() / 2 + 20;
//    int angle = 20;
    String text = "COMBO 1";
    BitmapFont comboText;

    SpriteBatch batch;
    OrthographicCamera camera;

    public ComboVombo() {
        final String pathToFont = "font/font.fnt";
        comboText = new BitmapFont(Gdx.files.internal(pathToFont), true);
        comboText.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        comboText.setColor(Color.BLACK);
        comboText.getData().setScale(GameUtils.FINAL_FONT_SCALE * 2f);


        camera = new OrthographicCamera();
        camera.rotate(45);
        batch = new SpriteBatch(10);


//        oldTransformMatrix = batch.getTransformMatrix().cpy();
//
//
//
//        mx4Font = new Matrix4(oldTransformMatrix);
//        mx4Font.setToRotation(new Vector3(200, 200, 0), 45);

//        mx4Font.trn(posX, posY, 0);

    }

    public void PUSHCOMBOOOOO(){

    }

    public void draw(SpriteBatch oldBatch){


//        oldBatch.end();
////        batch.setProjectionMatrix(mx4Font);
//        batch.begin();
//        comboText.draw(batch, text, 0, 0);
//        batch.end();
////        batch.setProjectionMatrix(oldTransformMatrix);
//        oldBatch.begin();


    }

    public void dispose() {
        comboText.dispose();
        batch.dispose();
    }
}
