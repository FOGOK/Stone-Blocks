package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:15.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Handler {

    /**Класс, который отвечает за обработку всей игры по сути
     *
     * */

    Atalas atls;

    //game
    LevelGen levelGen;

    public static boolean ISPAUSE;
    public static boolean ISRESTART;
    ///

    //ui
    UI ui;
    ///


    TextureGen textureGen;
    State state;

    enum State{
        Game, Pause, Menu
    }

    public Handler(Camera camera, SpriteBatch batch){
        ISPAUSE = ISRESTART = false;

        atls = new Atalas();
        textureGen = new TextureGen(atls);
        levelGen = new LevelGen(textureGen, batch);
        ui = new UI(textureGen, camera);
        state = State.Game;
    }

    public void draw(SpriteBatch batch){
        if (state == State.Game || state == State.Pause)
              levelGen.draw(batch);

        ui.draw(batch);

        if (ISRESTART) restart(batch);
    }

    public void restart(SpriteBatch batch){
        levelGen.dispose();
        levelGen = new LevelGen(textureGen, batch);
        ISRESTART = false;
    }

    public void dispose() {
        atls.dispose();
        levelGen.dispose();
        ui.dispose();
    }
}
