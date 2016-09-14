package com.java4game.cuadro.core;

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
    LevelGen levelGen;
    TextureGen textureGen;
    State state;

    enum State{
        Game, Pause, Menu
    }

    public Handler() {
        atls = new Atalas();
        textureGen = new TextureGen(atls);
        levelGen = new LevelGen(textureGen);
        state = State.Game;
    }

    public void draw(SpriteBatch batch){
        switch (state){ ////обработка экрана
            case Game:
            case Pause:

                levelGen.draw(batch);

                if (state.equals(State.Pause)){
                    //pause
                }

                break;
            case Menu:
                //menu
                break;
        }
    }

    public void dispose() {
        atls.dispose();
        levelGen.dispose();
    }
}
