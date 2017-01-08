package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.StageButton;
import com.java4game.cuadro.objects.FlyingStage;
import com.java4game.cuadro.objects.MainBlock;
import com.java4game.cuadro.utils.Assets;

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

    public static final int SQSIZE = 7;
    public static float backHDivH;
    private Rectangle fieldBounds;
    private Sprite background, field;

    private MainBlock mainBlock;
    private BlockGenerator blockGenerator;
    private FlyingStage flyingStage;

    public LevelGen() {
        //инициализируем фон
        background = Assets.getNewSprite(BlockAndHolesPositions.getLevel(StageButton.LEVEL - 1).getColor());
        final float hDivW = 1.7777f;
        background.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
        background.setPosition(0f, (Gm.HEIGHT - background.getHeight()) / 2f);
        backHDivH = background.getHeight() / Gm.HEIGHT;
        ///
        final float fieldSize = background.getWidth() * 0.9f;
        field = Assets.getNewSprite(1);
        field.setSize(fieldSize, fieldSize);
        field.setPosition((Gm.WIDTH - fieldSize) / 2f, (Gm.HEIGHT - fieldSize) / 2f);

        fieldBounds = field.getBoundingRectangle();

        ///инициализируем кубик и устанавливаем размер кубика
        mainBlock = new MainBlock(Assets.getNewSprite(12), fieldBounds);
        blockGenerator = new BlockGenerator(mainBlock, fieldBounds, StageButton.LEVEL - 1);
        mainBlock.setBlockGenerator(blockGenerator);
        //
        //инициаилизируем летящий текст
        Color flyStageColor = Color.BLACK;
//        switch (BlockAndHolesPositions.getLevel(StageButton.LEVEL - 1).getColor()){
//            case BlockAndHolesPositions.BACK_COLOR_BLUE:
//                flyStageColor = Color.ROYAL.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_GRAY:
//                flyStageColor = Color.DARK_GRAY.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_GREEN:
//                flyStageColor = Color.FOREST.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_RED:
//                flyStageColor = Color.FIREBRICK.cpy();
//                break;
//            case BlockAndHolesPositions.BACK_COLOR_YELLOW:
//                flyStageColor = Color.GOLDENROD.cpy();
//                break;
//        }
        flyingStage = new FlyingStage();
        flyingStage.setNew(StageButton.LEVEL, flyStageColor);
        //
    }

    public void draw(SpriteBatch batch){
        field.draw(batch);


        batch.end();

        int srcFunc = batch.getBlendSrcFunc();
        int dstFunc = batch.getBlendDstFunc();

        batch.enableBlending();
        batch.begin();

        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

        background.draw(batch);

        batch.end();
        batch.begin();
        batch.setBlendFunction(srcFunc, dstFunc);

        blockGenerator.draw(batch);
        mainBlock.draw(batch);

        flyingStage.handle();
        flyingStage.drawGlass(batch);
        flyingStage.drawText(batch);
    }
}
