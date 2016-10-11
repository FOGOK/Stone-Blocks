package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by FOGOK on 10.10.2016 16:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ComboVombo {

    final static float WIDTH_COMBOTEXT = 6;
    final static float HEIGHT_COMBOTEXT = WIDTH_COMBOTEXT * 0.286f;

    final static float SIZE_COMBONUM = HEIGHT_COMBOTEXT;



    Sprite comboText;
    Sprite[] numbCombos = new Sprite[10];
    public ComboVombo(TextureGen textureGen) {
        comboText = textureGen.getSprite(Atalas.comboText);
        for (int i = 0; i < numbCombos.length; i++){
            numbCombos[i] = new Sprite(textureGen.getSprite("comboText" + i));
            numbCombos[i].setSize(SIZE_COMBONUM, SIZE_COMBONUM);
        }

        comboText.setSize(WIDTH_COMBOTEXT, HEIGHT_COMBOTEXT);



    }

    public void PUSHCOMBOOOOO(){

    }

    public void draw(SpriteBatch batch){


    }

    public void dispose() {

    }
}
