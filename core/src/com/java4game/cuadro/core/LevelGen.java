package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.Cube;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class LevelGen {

    /**
     * Класс, который отвечает за всё, что связанно с игрой
     * тут инициализируется игровое поле, кубик, препятствия и т.д.
     *
     * */

    Cube cube;
    LevelSquare levelSquare;

    //ссылки
    TextureGen textureGen;
    ///

    public LevelGen(TextureGen textureGen) {
        //инициализация ссылок
        this.textureGen = textureGen;
        ///

        //инициализация кубика и игрового поля
        levelSquare = new LevelSquare(textureGen.getSprite(Atalas.sfieldT));
        float otst = 0.1f;  //отступ от краёв кубика
        float cubeSize = 1.15f; /// размер кубика
        levelSquare.setSize(Gm.WIDTH - cubeSize * 2 - otst * 4f);        //устанавливаем размер игрового поля ширина - 2 размера кубика - небольшой отступ
        levelSquare.setPosition((Gm.WIDTH - levelSquare.getW()) / 2f, (Gm.HEIGHT - levelSquare.getW()) / 2f);       //устанавливаем игровое поле в центр экрана

        cube = new Cube(textureGen.getSprite(Atalas.squareT), levelSquare, otst, cubeSize);  ///инициализируем кубик и устанавливаем размер кубика
        ///
    }

    public void draw(SpriteBatch batch){
        levelSquare.draw(batch);
        cube.draw(batch);
    }

    public void dispose() {

    }
}
