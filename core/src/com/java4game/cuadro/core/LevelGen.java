package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.objects.Cube;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.objects.NumberObj;
import com.java4game.cuadro.utils.Atalas;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class LevelGen {

    /**
     * Класс, который отвечае*т за всё, что связанно с игрой
     * тут инициализируется игровое поле, кубик, препятствия и т.д.
     *
     * */

    Cube cube;
    LevelSquare levelSquare;
    Sprite background;
    ObjectsGen objectsGen;

    public static int SCORE = 0;

    float fulledCff = 0.8f; //степень заполненности

    final float levSize = Gm.WIDTH - 0.5f; // размер поля

    //ссылки
    TextureGen textureGen;
    ///

    public LevelGen(TextureGen textureGen) {
        //инициализация ссылок
        this.textureGen = textureGen;
        ///

        //инициализация кубика и игрового поля


        levelSquare = new LevelSquare(textureGen, (Gm.WIDTH - levSize) / 2f, 3f, levSize);
        //устанавливаем размер игрового поля ширина - 2 размера кубика - небольшой отступ   /\
        //устанавливаем игровое поле в центр экрана /\

        /// инициализируем игровые объекты
        NumberObj.pXii = 2f;
        NumberObj.pYii = 18f;

        objectsGen = new ObjectsGen(Cube.SQSIZE + 1, fulledCff, levelSquare.getBounds(), textureGen);
        ///

        ///инициализируем кубик и устанавливаем размер кубика
        cube = new Cube(textureGen.getSprite(Atalas.squareT1), levelSquare, objectsGen);
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
        objectsGen.draw(batch);
        cube.draw(batch);
        Gm.DEBUG_VALUE1 = "Score: " + SCORE;
    }

    public void dispose() {
        background.getTexture().dispose();
    }
}
