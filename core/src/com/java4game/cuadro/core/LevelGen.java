package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
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

    public static final int SQSIZE = 7;         // 8 - 1, т.к. последняя клетка равна 5 а первая 0

    private Cube cube;
    private LevelSquare levelSquare;
    private Sprite background;
    private ObjectsGen objectsGen;

    public static int SCORE = 0;

    public static float backHDivH;
    private static ComboVombo comboVombo;

    static float centerXLEVSQ, centerYLEVSQ;

    float fulledCff = 0.4f; //степень заполненности
    float sttCff = 0.125f; //степень сетчатости поля (короче количество % дырок)

    final float levSize = Gm.WIDTH - 0.5f; // размер поля

    public static float sizeObjects;
    public static float otstObjects;

    //ссылки
    private TextureGen textureGen;
    ///

    public LevelGen(TextureGen textureGen, SpriteBatch batch) {
        //инициализация ссылок
        this.textureGen = textureGen;
        ///
        //инициализируем фон
        background = new Sprite(new Texture(Gdx.files.internal("bg.png")));
        background.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final float hDivW = 1.7777f;
        background.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
        backHDivH = background.getHeight() / Gm.HEIGHT;
        background.setPosition(0f, Gm.HEIGHT - background.getHeight());
        ///

        //инициализация кубика и игрового поля


        final float levSqOtst = 0.22f;
        final float posSqInMacketCFF = 0.22f; /// на 22 проценте находится фон поля как бы
        final float levSqPos = (Gm.HEIGHT - background.getY()) * posSqInMacketCFF + background.getY();
        levelSquare = new LevelSquare(textureGen, (Gm.WIDTH - levSize) / 2f, levSqPos + levSqOtst, levSize, sttCff);
        centerXLEVSQ = Gm.WIDTH / 2f;
        centerYLEVSQ = levSqPos + levSqOtst + levSize / 2f;
        //устанавливаем размер игрового поля ширина - 2 размера кубика - небольшой отступ   /\
        //устанавливаем игровое поле в центр экрана /\

        /// инициализируем игровые объекты
        NumberObj.pXii = 5f;
        NumberObj.pYii = 16f;
        sizeObjects = LevelSquare.sizOneSq * 0.85f;
        otstObjects = LevelSquare.sizOneSq - sizeObjects;

        objectsGen = new ObjectsGen(SQSIZE + 1, fulledCff, levelSquare.getBounds(), textureGen);

        comboVombo = new ComboVombo(textureGen);
        ///

        ///инициализируем кубик и устанавливаем размер кубика
        cube = new Cube(textureGen.getSprite(Atalas.squareT2), levelSquare, objectsGen);
        //
    }

    public void draw(SpriteBatch batch){
        background.draw(batch);
        levelSquare.draw(batch);
        objectsGen.draw(batch);
        cube.draw(batch);
        comboVombo.draw(batch);
        UI.setScore(SCORE);

    }

    public static void PUSHCOMBOOOOO(){
        comboVombo.PUSHCOMBOOOOO(centerXLEVSQ, centerYLEVSQ, Color.RED);
    }

    public void dispose() {
        background.getTexture().dispose();
        objectsGen.dispose();
        comboVombo.dispose();
    }
}
