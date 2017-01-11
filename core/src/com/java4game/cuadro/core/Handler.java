package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.core.usie.PauseUI;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:15.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Handler {

    /**Класс, который отвечает за обработку всей игры
     *
     * */

    public static boolean isBackPressed;

    Atalas atls;

    //game
    LevelGen levelGen;

    public static boolean ISPAUSE;
    public static boolean ISRESTART;
    ///

    //ui
//    GameUI gameUi;
    PauseUI pauseUI;
    MenuUI menuUI;
    ///


    TextureGen textureGen;
    public static State state;

    public enum State{
        Game, Pause, Menu
    }

    public Handler(){
        ISPAUSE = ISRESTART = false;

        isBackPressed = false;
        atls = new Atalas();
        textureGen = new TextureGen(atls);
//        gameUi = new GameUI(textureGen);
        pauseUI = new PauseUI(textureGen);
        menuUI = new MenuUI(textureGen);
        state = State.Menu;

//        state = State.Game;
//        StageButton.LEVEL = 8;
//        levelGen = new LevelGen();
    }

    public void draw(SpriteBatch batch){
        if (state == State.Game || state == State.Pause){
            levelGen.draw(batch);
//            gameUi.draw(batch);
            if (state == State.Pause)
               pauseUI.draw(batch);

        }else if (state == State.Menu){
            menuUI.draw(batch);
        }


        if (ISRESTART) restart();



        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || isBackPressed){
            isBackPressed = false;

            if (Handler.state == Handler.State.Pause)
                Handler.state = State.Game;
            else
                Handler.state = State.Pause;

            ISPAUSE = !ISPAUSE;
        }

    }

    public void restart(){
        levelGen = new LevelGen();
        ISRESTART = false;
    }

    public void dispose() {
        atls.dispose();
        menuUI.dispose();
//        gameUi.dispose();
        pauseUI.dispose();
    }
}
