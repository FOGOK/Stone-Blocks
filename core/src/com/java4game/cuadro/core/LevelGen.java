package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    Sprite background;

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

        //инициализируем фон
        background = new Sprite(new Texture(Gdx.files.internal("bg.png")));
        background.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background.setSize(Gm.WIDTH, Gm.HEIGHT);
        ///
    }

    public void draw(SpriteBatch batch){
        background.draw(batch);
        levelSquare.draw(batch);
        cube.draw(batch);
    }

    public void dispose() {
        background.getTexture().dispose();
    }
}
