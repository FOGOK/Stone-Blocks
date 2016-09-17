package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.utils.Atalas;

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

    boolean restarting = false;
    Stage stage;
    int WIDTH, HEIGHT;
    public UI(final TextureGen textureGen) {
        stage = new Stage();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(stage);


        TextureRegionDrawable tD = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT1));
        TextureRegionDrawable tM = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT3));
        TextureRegionDrawable tC = new TextureRegionDrawable(textureGen.getTextureRG(Atalas.squareT2));

        final Button button = new Button(tD, tC, tD);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Handler.ISPAUSE = !Handler.ISPAUSE;
            }
        });
        button.setSize(50, 50);
        button.setY(HEIGHT - 50);

        final Button button2 = new Button(tM, tC, tM);
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Handler.ISRESTART = true;
            }
        });
        button2.setSize(50, 50);
        button2.setPosition(50, HEIGHT - 50);






        stage.addActor(button);
        stage.addActor(button2);
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
