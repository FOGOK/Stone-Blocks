package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by java4game and FOGOK on 14.09.2016 13:39.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class DebugValueChanger{

    Skin skin;
    Stage stage;
    static TextField[] textFields;
    Button[] buttons;
    static boolean[] inOne;

//    public static Array<T> m = new Array<T>();


    static Float[] values;
    //default type: Float /\

    Window window;

    public DebugValueChanger(final int mx, SpriteBatch batch) {
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));


        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);

        textFields = new TextField[mx];
        buttons = new Button[mx];
        values = new Float[mx];
        inOne = new boolean[mx];

        for (int i2 = 0; i2 < mx; i2++) {
            textFields[i2] = new TextField("0", skin);
            textFields[i2].setMessageText("Type value ");
            buttons[i2] = new TextButton("A " + i2, skin);
            buttons[i2].setUserObject(i2);
            inOne[i2] = false;
        }

        window = new Window("DebugValueChanger", skin);
        window.setPosition(0, 0);
        window.defaults().spaceBottom(10);
        window.row().fill().expandX();

        for (int i2 = 0; i2 < mx; i2++) {
            window.add(textFields[i2]).minWidth(100);
            window.add(buttons[i2]).minSize(20);
            window.row();
        }



        window.pack();
        stage.addActor(window);
        for (int i2 = 0; i2 < mx; i2++) {
            textFields[i2].setTextFieldListener(new TextField.TextFieldListener() {
                public void keyTyped (TextField textField, char key) {
                    if (key == '\n') textField.getOnscreenKeyboard().show(false);
                }
            });

            buttons[i2].addListener(new ChangeListener(){
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    try{
                        values[Integer.valueOf(actor.getUserObject().toString())] = Float.parseFloat(textFields[Integer.valueOf(actor.getUserObject().toString())].getText());
                    }catch (Exception e){}
                }
            });
        }


    }

    public static float getFloatObj(int i){
        return values[i];
    }


    public static void setFloatObj(int i, float f){
        values[i] = f;
        textFields[i].setText(f + "");
    }

    public static void setFloatObjInOne(int i, float f){
        if (!inOne[i]){
            inOne[i] = true;
            setFloatObj(i, f);
        }
    }

    public void show(){
        window.setVisible(true);
    }

    public void hide(){
        window.setVisible(false);
    }

    public void draw(){
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
