package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.Cube;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
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
        float levSize = Gm.WIDTH - 1.5f;

        levelSquare = new LevelSquare(textureGen, (Gm.WIDTH - levSize) / 2f, (Gm.HEIGHT - levSize) / 2f, levSize);
        //устанавливаем размер игрового поля ширина - 2 размера кубика - небольшой отступ   /\
        //устанавливаем игровое поле в центр экрана /\

        cube = new Cube(textureGen.getSprite(Atalas.squareT1), levelSquare);  ///инициализируем кубик и устанавливаем размер кубика
        ///
    }

    public void draw(SpriteBatch batch){
        levelSquare.fRender(batch);
        cube.draw(batch);
    }

    private Sprite generateLevSquare(final TextureGen textureGen){


        return new Sprite();
    }

    public void dispose() {

    }
}
