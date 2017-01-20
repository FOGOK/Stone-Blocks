package com.java4game.cuadro.core;

import com.java4game.cuadro.core.InitLevels.Level;
import com.java4game.cuadro.core.InitLevels.Object;

/**
 * Created by FOGOK on 20.01.2017 14:46.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
class InitTimeLevels {

    private static final int COLOR_BLUE = 0, COLOR_GREEN = 1, COLOR_RED = 2, COLOR_WHITE = 3, COLOR_YELLOW = 4;
    private static final int BACK_COLOR_BLUE = 0, BACK_COLOR_GREEN = 2, BACK_COLOR_GRAY = 3, BACK_COLOR_RED = 4, BACK_COLOR_YELLOW = 5;
    private static final boolean CUBE = true, HOLE = false;

    static void init(Level[] timeLevels){
        timeLevels[1] = new Level(new Object[]{
                new Object(2, 4, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        timeLevels[2] = new Level(new Object[]{
                new Object(2, 6, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_BLUE, CUBE)
        }, BACK_COLOR_BLUE);
        timeLevels[3] = new Level(new Object[]{
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),

                new Object(6, 3, COLOR_YELLOW, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        timeLevels[4] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),

                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(6, 6, COLOR_GREEN, CUBE)
        }, BACK_COLOR_GREEN);
        timeLevels[5] = new Level(new Object[]{
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        timeLevels[5].setMinSteps(3);
        timeLevels[6] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(6, 7, COLOR_BLUE, CUBE),
                new Object(3, 5, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        timeLevels[7] = new Level(new Object[]{
                new Object(4, 7, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),

                new Object(4, 4, COLOR_BLUE, CUBE),
                new Object(5, 3, COLOR_BLUE, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        timeLevels[8] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_YELLOW);
        timeLevels[9] = new Level(new Object[]{
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(3, 2, COLOR_GREEN, CUBE),
                new Object(6, 6, COLOR_BLUE, CUBE)
        }, BACK_COLOR_GREEN);
        timeLevels[10] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(4, 3, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_GREEN, CUBE),
                new Object(2, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        timeLevels[11] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_WHITE, CUBE),
                new Object(2, 6, COLOR_WHITE, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        timeLevels[11].setMinSteps(4);
        timeLevels[12] = new Level(new Object[]{
                new Object(1, 2, COLOR_RED, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_BLUE, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
                new Object(8, 7, COLOR_RED, CUBE)
        }, BACK_COLOR_GREEN);
        timeLevels[13] = new Level(new Object[]{
                new Object(7, 4, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_RED, CUBE),
                new Object(2, 6, COLOR_GREEN, CUBE),
                new Object(2, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_YELLOW);
        timeLevels[13].setMinSteps(5);
        timeLevels[14] = new Level(new Object[]{
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),

                new Object(3, 4, COLOR_YELLOW, CUBE),
                new Object(6, 5, COLOR_WHITE, CUBE),
                new Object(7, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        timeLevels[15] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(4, 8, COLOR_RED, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),

                new Object(4, 1, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_RED, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE)
        }, BACK_COLOR_RED);
        timeLevels[15].setMinSteps(5);
        timeLevels[16] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 3, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(4, 3, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        timeLevels[17] = new Level(new Object[]{
                new Object(3, 5, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),

                new Object(4, 5, COLOR_GREEN, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        timeLevels[17].setMinSteps(5);
        timeLevels[18] = new Level(new Object[]{
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 2, COLOR_BLUE, CUBE),
                new Object(2, 2, COLOR_RED, CUBE),
                new Object(5, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        timeLevels[19] = new Level(new Object[]{
                new Object(2, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE),
                new Object(8, 2, COLOR_BLUE, CUBE)
        }, BACK_COLOR_GREEN);
        timeLevels[20] = new Level(new Object[]{
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),

                new Object(1, 3, COLOR_WHITE, CUBE),
                new Object(2, 3, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_BLUE);
        timeLevels[21] = new Level(new Object[]{
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(3, 7, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_RED, CUBE),
                new Object(7, 5, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        timeLevels[21].setMinSteps(6);
    }

}
