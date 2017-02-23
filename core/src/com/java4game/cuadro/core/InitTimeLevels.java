package com.java4game.cuadro.core;

import com.java4game.cuadro.core.InitLevels.Level;
import com.java4game.cuadro.core.InitLevels.Object;
import static com.java4game.cuadro.core.InitLevels.*;

/**
 * Created by FOGOK on 20.01.2017 14:46.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
class InitTimeLevels {

    static void init(Level[] timeLevels){
        timeLevels[1] = new Level(new Object[]{
                new Object(2, 4, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        timeLevels[2] = new Level(new Object[]{
                new Object(2, 6, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_BLUE);
        timeLevels[3] = new Level(new Object[]{
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),

                new Object(6, 3, COLOR_YELLOW, BLOCK),
                new Object(5, 6, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_YELLOW);
        timeLevels[3].setGoldSeconds(20);
        timeLevels[4] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),

                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(6, 6, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_GREEN);
        timeLevels[5] = new Level(new Object[]{
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        timeLevels[5].setMinSteps(3);
        timeLevels[6] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(6, 7, COLOR_BLUE, BLOCK),
                new Object(3, 5, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        timeLevels[7] = new Level(new Object[]{
                new Object(4, 7, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),

                new Object(4, 4, COLOR_BLUE, BLOCK),
                new Object(5, 3, COLOR_BLUE, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        timeLevels[8] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_YELLOW);
        timeLevels[9] = new Level(new Object[]{
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(3, 2, COLOR_GREEN, BLOCK),
                new Object(6, 6, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_GREEN);
        timeLevels[10] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(4, 3, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_GREEN, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        timeLevels[11] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_WHITE, BLOCK),
                new Object(2, 6, COLOR_WHITE, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        timeLevels[11].setMinSteps(4);
        timeLevels[12] = new Level(new Object[]{
                new Object(1, 2, COLOR_RED, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_BLUE, BLOCK),
                new Object(1, 8, COLOR_YELLOW, BLOCK),
                new Object(8, 7, COLOR_RED, BLOCK)
        }, BACK_COLOR_GREEN);
        timeLevels[13] = new Level(new Object[]{
                new Object(7, 4, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_RED, BLOCK),
                new Object(2, 6, COLOR_GREEN, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_YELLOW);
        timeLevels[13].setMinSteps(5);
        timeLevels[14] = new Level(new Object[]{
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),

                new Object(3, 4, COLOR_YELLOW, BLOCK),
                new Object(6, 5, COLOR_WHITE, BLOCK),
                new Object(7, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        timeLevels[15] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(4, 8, COLOR_RED, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),

                new Object(4, 1, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(6, 2, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_RED);
        timeLevels[15].setMinSteps(5);
        timeLevels[16] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 3, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(4, 3, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        timeLevels[17] = new Level(new Object[]{
                new Object(3, 5, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),

                new Object(4, 5, COLOR_GREEN, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_YELLOW);
        timeLevels[17].setMinSteps(5);
        timeLevels[18] = new Level(new Object[]{
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 2, COLOR_BLUE, BLOCK),
                new Object(2, 2, COLOR_RED, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        timeLevels[19] = new Level(new Object[]{
                new Object(2, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK),
                new Object(8, 2, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_GREEN);
        timeLevels[20] = new Level(new Object[]{
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),

                new Object(1, 3, COLOR_WHITE, BLOCK),
                new Object(2, 3, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_BLUE);
        timeLevels[21] = new Level(new Object[]{
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(3, 7, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_RED, BLOCK),
                new Object(7, 5, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        timeLevels[21].setMinSteps(6);
    }

}
