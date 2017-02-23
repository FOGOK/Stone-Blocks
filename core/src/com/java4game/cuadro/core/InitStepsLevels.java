package com.java4game.cuadro.core;

import com.java4game.cuadro.core.InitLevels.Level;
import com.java4game.cuadro.core.InitLevels.Object;
import static com.java4game.cuadro.core.InitLevels.*;

/**
 * Created by FOGOK on 20.01.2017 14:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
class InitStepsLevels {

    static void init(Level[] stepsLevels){
        stepsLevels[1] = new Level(new Object[]{
                new Object(2, 4, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[2] = new Level(new Object[]{
                new Object(2, 6, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[3] = new Level(new Object[]{
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),

                new Object(6, 3, COLOR_YELLOW, BLOCK),
                new Object(5, 6, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[4] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),

                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(6, 6, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[5] = new Level(new Object[]{
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[5].setMinSteps(3);
        stepsLevels[6] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(6, 7, COLOR_BLUE, BLOCK),
                new Object(3, 5, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[7] = new Level(new Object[]{
                new Object(4, 7, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),

                new Object(4, 4, COLOR_BLUE, BLOCK),
                new Object(5, 3, COLOR_BLUE, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[8] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[9] = new Level(new Object[]{
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(3, 2, COLOR_GREEN, BLOCK),
                new Object(6, 6, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[10] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(4, 3, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_GREEN, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[11] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_WHITE, BLOCK),
                new Object(2, 6, COLOR_WHITE, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[11].setMinSteps(4);
        stepsLevels[12] = new Level(new Object[]{
                new Object(1, 2, COLOR_RED, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_BLUE, BLOCK),
                new Object(1, 8, COLOR_YELLOW, BLOCK),
                new Object(8, 7, COLOR_RED, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[13] = new Level(new Object[]{
                new Object(7, 4, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_RED, BLOCK),
                new Object(2, 6, COLOR_GREEN, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[13].setMinSteps(5);
        stepsLevels[14] = new Level(new Object[]{
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),

                new Object(3, 4, COLOR_YELLOW, BLOCK),
                new Object(6, 5, COLOR_WHITE, BLOCK),
                new Object(7, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[15] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(4, 8, COLOR_RED, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),

                new Object(4, 1, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(6, 2, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[15].setMinSteps(5);
        stepsLevels[16] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 3, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(4, 3, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[17] = new Level(new Object[]{
                new Object(3, 5, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),

                new Object(4, 5, COLOR_GREEN, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[17].setMinSteps(5);
        stepsLevels[18] = new Level(new Object[]{
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 2, COLOR_BLUE, BLOCK),
                new Object(2, 2, COLOR_RED, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[19] = new Level(new Object[]{
                new Object(2, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK),
                new Object(8, 2, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[20] = new Level(new Object[]{
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),

                new Object(1, 3, COLOR_WHITE, BLOCK),
                new Object(2, 3, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[21] = new Level(new Object[]{
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(3, 7, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_RED, BLOCK),
                new Object(7, 5, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[21].setMinSteps(6);
        stepsLevels[22] = new Level(new Object[]{
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),

                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_YELLOW, BLOCK),
                new Object(3, 2, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[22].setMinSteps(6);
        stepsLevels[23] = new Level(new Object[]{
                new Object(1, 1, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(3, 5, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_RED, BLOCK),
                new Object(4, 6, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[23].setMinSteps(7);
        stepsLevels[24] = new Level(new Object[]{
                new Object(3, 6, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(4, 2, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(3, 5, COLOR_BLUE, BLOCK),
                new Object(5, 1, COLOR_YELLOW, BLOCK),
                new Object(7, 3, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[25] = new Level(new Object[]{
                new Object(2, 5, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 4, COLOR_RED, HOLE),

                new Object(4, 6, COLOR_RED, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK),
                new Object(4, 4, COLOR_GREEN, BLOCK),
                new Object(6, 5, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[26] = new Level(new Object[]{
                new Object(1, 5, COLOR_WHITE, HOLE),
                new Object(1, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_BLUE, HOLE),
                new Object(8, 3, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(7, 5, COLOR_WHITE, BLOCK),
                new Object(7, 3, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[26].setMinSteps(8);
        stepsLevels[27] = new Level(new Object[]{
                new Object(1, 6, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_YELLOW, HOLE),
                new Object(8, 6, COLOR_RED, HOLE),

                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(4, 6, COLOR_RED, BLOCK),
                new Object(5, 6, COLOR_RED, BLOCK),
                new Object(6, 6, COLOR_RED, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[27].setMinSteps(8);
        stepsLevels[28] = new Level(new Object[]{
                new Object(3, 8, COLOR_RED, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(8, 3, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(3, 3, COLOR_GREEN, BLOCK),
                new Object(7, 6, COLOR_WHITE, BLOCK),
                new Object(7, 1, COLOR_RED, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[28].setMinSteps(5);
        stepsLevels[29] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),

                new Object(2, 2, COLOR_WHITE, BLOCK),
                new Object(5, 3, COLOR_GREEN, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[30] = new Level(new Object[]{
                new Object(1, 5, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 6, COLOR_RED, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(5, 2, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_RED, BLOCK),
                new Object(2, 7, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[31] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(8, 4, COLOR_GREEN, HOLE),

                new Object(1, 4, COLOR_GREEN, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(7, 8, COLOR_WHITE, BLOCK),
                new Object(6, 1, COLOR_RED, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[31].setMinSteps(9);
        stepsLevels[32] = new Level(new Object[]{
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_BLUE, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 3, COLOR_BLUE, BLOCK),
                new Object(2, 4, COLOR_GREEN, BLOCK),
                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(6, 7, COLOR_BLUE, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[33] = new Level(new Object[]{
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),

                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(3, 7, COLOR_BLUE, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(6, 7, COLOR_BLUE, BLOCK),
                new Object(8, 1, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[33].setMinSteps(8);
        stepsLevels[34] = new Level(new Object[]{
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(2, 1, COLOR_RED, BLOCK),
                new Object(4, 4, COLOR_YELLOW, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(5, 6, COLOR_RED, BLOCK),
                new Object(6, 3, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[35] = new Level(new Object[]{
                new Object(1, 8, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_YELLOW, BLOCK),
                new Object(2, 1, COLOR_RED, BLOCK),
                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(6, 3, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[35].setMinSteps(7);
        stepsLevels[36] = new Level(new Object[]{
                new Object(3, 3, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_YELLOW, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(2, 4, COLOR_YELLOW, BLOCK),
                new Object(3, 6, COLOR_YELLOW, BLOCK),
                new Object(5, 5, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK)
        }, BACK_COLOR_GRAY);
        stepsLevels[37] = new Level(new Object[]{
                new Object(4, 7, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(6, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_WHITE, BLOCK),
                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(3, 2, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_RED, BLOCK),
                new Object(5, 2, COLOR_RED, BLOCK)
        }, BACK_COLOR_BLUE);
        stepsLevels[37].setMinSteps(8);
        stepsLevels[38] = new Level(new Object[]{
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),

                new Object(1, 7, COLOR_YELLOW, BLOCK),
                new Object(1, 6, COLOR_YELLOW, BLOCK),
                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(7, 3, COLOR_RED, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[38].setMinSteps(4);
        stepsLevels[39] = new Level(new Object[]{
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),

                new Object(2, 4, COLOR_YELLOW, BLOCK),
                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_WHITE, BLOCK),
                new Object(7, 8, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[40] = new Level(new Object[]{
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(2, 3, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(5, 7, COLOR_RED, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_RED, BLOCK),
                new Object(5, 2, COLOR_RED, BLOCK),
                new Object(5, 8, COLOR_WHITE, BLOCK),
                new Object(6, 8, COLOR_RED, BLOCK),
                new Object(7, 2, COLOR_RED, BLOCK)
        }, BACK_COLOR_RED);
        stepsLevels[41] = new Level(new Object[]{
                new Object(4, 7, COLOR_GREEN, HOLE),
                new Object(5, 7, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),

                new Object(2, 2, COLOR_GREEN, BLOCK),
                new Object(7, 2, COLOR_GREEN, BLOCK),
                new Object(4, 4, COLOR_GREEN, BLOCK),
                new Object(5, 4, COLOR_GREEN, BLOCK),
                new Object(4, 6, COLOR_GREEN, BLOCK),
                new Object(5, 6, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_GREEN);
        stepsLevels[41].setMinSteps(10);
        stepsLevels[42] = new Level(new Object[]{
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_GREEN, HOLE),

                new Object(1, 8, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(3, 3, COLOR_BLUE, BLOCK),
                new Object(3, 6, COLOR_RED, BLOCK),
                new Object(5, 3, COLOR_YELLOW, BLOCK),
                new Object(6, 3, COLOR_RED, BLOCK)
        }, BACK_COLOR_GREEN);
        stepsLevels[32].setMinSteps(8);
        stepsLevels[43] = new Level(new Object[]{
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),

                new Object(5, 6, COLOR_WHITE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK),
                new Object(4, 5, COLOR_YELLOW, BLOCK),
                new Object(6, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 3, COLOR_YELLOW, BLOCK),
                new Object(5, 1, COLOR_WHITE, BLOCK)
        }, BACK_COLOR_YELLOW);
        stepsLevels[43].setMinSteps(9);
        stepsLevels[44] = new Level(new Object[]{
                new Object(4, 3, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(1, 1, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(3, 3, COLOR_BLUE, BLOCK),
                new Object(5, 3, COLOR_GREEN, BLOCK),
                new Object(6, 3, COLOR_RED, BLOCK),
                new Object(8, 1, COLOR_GREEN, BLOCK)
        }, BACK_COLOR_YELLOW);

        stepsLevels[45] = new Level(new Object[]{
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_WHITE, BLOCK),
                new Object(3, 2, COLOR_WHITE, BLOCK),
                new Object(4, 2, COLOR_BLUE, BLOCK),
                new Object(5, 2, COLOR_WHITE, BLOCK),
                new Object(2, 7, COLOR_RED, BLOCK),
                new Object(4, 7, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[46] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(8, 4, COLOR_GREEN, HOLE),
                new Object(1, 5, COLOR_GREEN, HOLE),
                new Object(2, 7, COLOR_YELLOW, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),

                new Object(4, 1, COLOR_RED, BLOCK),
                new Object(5, 1, COLOR_GREEN, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(4, 5, COLOR_YELLOW, BLOCK),
                new Object(3, 6, COLOR_YELLOW, BLOCK),
                new Object(4, 8, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_BLUE);


        stepsLevels[47] = new Level(new Object[]{
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(1, 5, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_WHITE, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),

                new Object(5, 3, COLOR_WHITE, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK),
                new Object(3, 5, COLOR_RED, BLOCK),
                new Object(5, 5, COLOR_WHITE, BLOCK),
                new Object(6, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[48] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),

                new Object(6, 2, COLOR_GREEN, BLOCK),
                new Object(7, 2, COLOR_RED, BLOCK),
                new Object(7, 5, COLOR_GREEN, BLOCK),
                new Object(2, 6, COLOR_GREEN, BLOCK),
                new Object(3, 6, COLOR_RED, BLOCK),
                new Object(2, 7, COLOR_RED, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[49] = new Level(new Object[]{
                new Object(2, 1, COLOR_GREEN, HOLE),
                new Object(5, 1, COLOR_GREEN, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(3, 4, COLOR_WHITE, BLOCK),
                new Object(7, 4, COLOR_WHITE, BLOCK),
                new Object(8, 7, COLOR_GREEN, BLOCK),
                new Object(2, 8, COLOR_WHITE, BLOCK),
                new Object(3, 8, COLOR_GREEN, BLOCK),
                new Object(4, 8, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[50] = new Level(new Object[]{
                new Object(2, 3, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(7, 5, COLOR_BLUE, HOLE),
                new Object(8, 5, COLOR_BLUE, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(1, 2, COLOR_BLUE, BLOCK),
                new Object(3, 4, COLOR_BLUE, BLOCK),
                new Object(1, 5, COLOR_BLUE, BLOCK),
                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[51] = new Level(new Object[]{
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(7, 6, COLOR_WHITE, HOLE),
                new Object(8, 6, COLOR_WHITE, HOLE),
                new Object(5, 7, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(4, 5, COLOR_WHITE, BLOCK),
                new Object(1, 6, COLOR_WHITE, BLOCK),
                new Object(2, 6, COLOR_WHITE, BLOCK),
                new Object(3, 6, COLOR_WHITE, BLOCK),
                new Object(4, 6, COLOR_WHITE, BLOCK),
                new Object(4, 7, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GRAY);
        stepsLevels[51].setMinSteps(4);

        stepsLevels[52] = new Level(new Object[]{
                new Object(5, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_BLUE, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(2, 1, COLOR_WHITE, BLOCK),
                new Object(7, 2, COLOR_YELLOW, BLOCK),
                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(4, 7, COLOR_RED, BLOCK),
                new Object(5, 7, COLOR_BLUE, BLOCK),
                new Object(6, 7, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_BLUE);
        stepsLevels[52].setMinSteps(8);

        stepsLevels[53] = new Level(new Object[]{
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 5, COLOR_BLUE, HOLE),
                new Object(2, 6, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),
                new Object(8, 6, COLOR_BLUE, HOLE),
                new Object(1, 7, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(7, 2, COLOR_YELLOW, BLOCK),
                new Object(3, 3, COLOR_YELLOW, BLOCK),
                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(3, 5, COLOR_WHITE, BLOCK),
                new Object(4, 7, COLOR_RED, BLOCK),
                new Object(3, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[54] = new Level(new Object[]{
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(7, 4, COLOR_BLUE, HOLE),
                new Object(6, 5, COLOR_RED, HOLE),
                new Object(3, 8, COLOR_RED, HOLE),

                new Object(3, 1, COLOR_RED, BLOCK),
                new Object(3, 3, COLOR_WHITE, BLOCK),
                new Object(2, 4, COLOR_BLUE, BLOCK),
                new Object(4, 4, COLOR_RED, BLOCK),
                new Object(8, 4, COLOR_BLUE, BLOCK),
                new Object(5, 5, COLOR_YELLOW, BLOCK),
                new Object(6, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[55] = new Level(new Object[]{
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(5, 1, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(6, 8, COLOR_WHITE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_GREEN, BLOCK),
                new Object(4, 1, COLOR_WHITE, BLOCK),
                new Object(7, 1, COLOR_WHITE, BLOCK),
                new Object(8, 1, COLOR_GREEN, BLOCK),
                new Object(3, 6, COLOR_GREEN, BLOCK),
                new Object(1, 8, COLOR_WHITE, BLOCK),
                new Object(3, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[56] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 1, COLOR_RED, BLOCK),
                new Object(8, 1, COLOR_RED, BLOCK),
                new Object(3, 5, COLOR_YELLOW, BLOCK),
                new Object(4, 5, COLOR_BLUE, BLOCK),
                new Object(6, 5, COLOR_YELLOW, BLOCK),
                new Object(1, 8, COLOR_RED, BLOCK),
                new Object(8, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[57] = new Level(new Object[]{
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),
                new Object(3, 3, COLOR_BLUE, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(6, 7, COLOR_YELLOW, HOLE),
                new Object(6, 8, COLOR_YELLOW, HOLE),

                new Object(5, 2, COLOR_BLUE, BLOCK),
                new Object(2, 3, COLOR_YELLOW, BLOCK),
                new Object(2, 4, COLOR_YELLOW, BLOCK),
                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(1, 7, COLOR_BLUE, BLOCK),
                new Object(5, 8, COLOR_BLUE, BLOCK),
                new Object(7, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[58] = new Level(new Object[]{
                new Object(7, 1, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_GREEN, HOLE),
                new Object(6, 5, COLOR_GREEN, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_RED, HOLE),

                new Object(4, 2, COLOR_WHITE, BLOCK),
                new Object(5, 2, COLOR_RED, BLOCK),
                new Object(2, 3, COLOR_GREEN, BLOCK),
                new Object(3, 4, COLOR_RED, BLOCK),
                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(4, 6, COLOR_RED, BLOCK),
                new Object(3, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[59] = new Level(new Object[]{
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(5, 1, COLOR_WHITE, HOLE),
                new Object(2, 6, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(1, 8, COLOR_WHITE, HOLE),
                new Object(3, 8, COLOR_RED, HOLE),
                new Object(5, 8, COLOR_GREEN, HOLE),

                new Object(4, 2, COLOR_WHITE, BLOCK),
                new Object(6, 2, COLOR_RED, BLOCK),
                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(8, 4, COLOR_RED, BLOCK),
                new Object(3, 5, COLOR_WHITE, BLOCK),
                new Object(7, 6, COLOR_GREEN, BLOCK),
                new Object(8, 7, COLOR_RED, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[60] = new Level(new Object[]{
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(8, 4, COLOR_YELLOW, HOLE),
                new Object(4, 7, COLOR_YELLOW, HOLE),

                new Object(4, 2, COLOR_YELLOW, BLOCK),
                new Object(1, 4, COLOR_YELLOW, BLOCK),
                new Object(3, 4, COLOR_YELLOW, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(7, 4, COLOR_YELLOW, BLOCK),
                new Object(2, 7, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_RED);


        stepsLevels[61] = new Level(new Object[]{
                new Object(2, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(2, 3, COLOR_GREEN, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(2, 6, COLOR_GREEN, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(2, 2, COLOR_WHITE, BLOCK),
                new Object(7, 2, COLOR_RED, BLOCK),
                new Object(2, 4, COLOR_GREEN, BLOCK),
                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(2, 5, COLOR_GREEN, BLOCK),
                new Object(7, 5, COLOR_GREEN, BLOCK),
                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[62] = new Level(new Object[]{
                new Object(1, 1, COLOR_BLUE, HOLE),
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(8, 3, COLOR_BLUE, HOLE),
                new Object(7, 4, COLOR_WHITE, HOLE),
                new Object(8, 4, COLOR_WHITE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(2, 3, COLOR_BLUE, BLOCK),
                new Object(6, 3, COLOR_BLUE, BLOCK),
                new Object(1, 4, COLOR_WHITE, BLOCK),
                new Object(2, 4, COLOR_WHITE, BLOCK),
                new Object(1, 5, COLOR_BLUE, BLOCK),
                new Object(8, 5, COLOR_BLUE, BLOCK),
                new Object(1, 6, COLOR_BLUE, BLOCK),
                new Object(8, 6, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[63] = new Level(new Object[]{
                new Object(6, 1, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(7, 4, COLOR_YELLOW, HOLE),
                new Object(6, 5, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(7, 8, COLOR_YELLOW, HOLE),

                new Object(3, 1, COLOR_YELLOW, BLOCK),
                new Object(2, 2, COLOR_YELLOW, BLOCK),
                new Object(3, 3, COLOR_BLUE, BLOCK),
                new Object(2, 4, COLOR_RED, BLOCK),
                new Object(4, 4, COLOR_YELLOW, BLOCK),
                new Object(3, 5, COLOR_GREEN, BLOCK),
                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(3, 7, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[64] = new Level(new Object[]{
                new Object(1, 1, COLOR_YELLOW, HOLE),
                new Object(2, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_BLUE, HOLE),
                new Object(1, 8, COLOR_BLUE, HOLE),
                new Object(2, 8, COLOR_BLUE, HOLE),
                new Object(7, 8, COLOR_YELLOW, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(7, 4, COLOR_BLUE, BLOCK),
                new Object(5, 5, COLOR_BLUE, BLOCK),
                new Object(7, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 6, COLOR_YELLOW, BLOCK),
                new Object(7, 6, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_BLUE, BLOCK),
                new Object(7, 7, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[65] = new Level(new Object[]{
                new Object(3, 3, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_RED, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_RED, BLOCK),
                new Object(7, 5, COLOR_YELLOW, BLOCK),
                new Object(6, 6, COLOR_RED, BLOCK),
                new Object(5, 7, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
                new Object(6, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[66] = new Level(new Object[]{
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(2, 2, COLOR_YELLOW, HOLE),
                new Object(1, 4, COLOR_GREEN, HOLE),
                new Object(2, 4, COLOR_RED, HOLE),
                new Object(8, 6, COLOR_GREEN, HOLE),
                new Object(1, 8, COLOR_RED, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(5, 2, COLOR_BLUE, BLOCK),
                new Object(6, 2, COLOR_YELLOW, BLOCK),
                new Object(7, 2, COLOR_RED, BLOCK),
                new Object(5, 4, COLOR_GREEN, BLOCK),
                new Object(6, 4, COLOR_RED, BLOCK),
                new Object(7, 4, COLOR_YELLOW, BLOCK),
                new Object(3, 6, COLOR_GREEN, BLOCK),
                new Object(3, 8, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[67] = new Level(new Object[]{
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(4, 2, COLOR_WHITE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(2, 5, COLOR_WHITE, HOLE),
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(3, 6, COLOR_GREEN, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),

                new Object(6, 2, COLOR_WHITE, BLOCK),
                new Object(3, 3, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_WHITE, BLOCK),
                new Object(3, 4, COLOR_WHITE, BLOCK),
                new Object(7, 5, COLOR_BLUE, BLOCK),
                new Object(8, 6, COLOR_WHITE, BLOCK),
                new Object(2, 7, COLOR_WHITE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[68] = new Level(new Object[]{
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(5, 4, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_WHITE, BLOCK),
                new Object(8, 1, COLOR_WHITE, BLOCK),
                new Object(3, 3, COLOR_RED, BLOCK),
                new Object(6, 3, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_RED, BLOCK),
                new Object(6, 6, COLOR_RED, BLOCK),
                new Object(1, 8, COLOR_WHITE, BLOCK),
                new Object(8, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[69] = new Level(new Object[]{
                new Object(4, 1, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_BLUE, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(5, 8, COLOR_YELLOW, HOLE),

                new Object(6, 1, COLOR_GREEN, BLOCK),
                new Object(8, 2, COLOR_BLUE, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(7, 4, COLOR_YELLOW, BLOCK),
                new Object(2, 5, COLOR_YELLOW, BLOCK),
                new Object(4, 5, COLOR_YELLOW, BLOCK),
                new Object(1, 7, COLOR_BLUE, BLOCK),
                new Object(3, 8, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[70] = new Level(new Object[]{
                new Object(1, 1, COLOR_WHITE, HOLE),
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(3, 1, COLOR_WHITE, HOLE),
                new Object(1, 2, COLOR_WHITE, HOLE),
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(1, 3, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(8, 1, COLOR_WHITE, BLOCK),
                new Object(7, 2, COLOR_WHITE, BLOCK),
                new Object(3, 3, COLOR_RED, BLOCK),
                new Object(6, 3, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(4, 5, COLOR_WHITE, BLOCK),
                new Object(3, 6, COLOR_WHITE, BLOCK),
                new Object(2, 7, COLOR_WHITE, BLOCK),
                new Object(1, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[71] = new Level(new Object[]{
                new Object(1, 1, COLOR_GREEN, HOLE),
                new Object(3, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(1, 8, COLOR_GREEN, HOLE),
                new Object(3, 8, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(8, 1, COLOR_YELLOW, BLOCK),
                new Object(6, 3, COLOR_BLUE, BLOCK),
                new Object(1, 4, COLOR_GREEN, BLOCK),
                new Object(6, 4, COLOR_BLUE, BLOCK),
                new Object(1, 5, COLOR_GREEN, BLOCK),
                new Object(6, 5, COLOR_BLUE, BLOCK),
                new Object(6, 6, COLOR_BLUE, BLOCK),
                new Object(6, 7, COLOR_RED, BLOCK),
                new Object(6, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[72] = new Level(new Object[]{
                new Object(3, 2, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(6, 5, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(4, 7, COLOR_GREEN, HOLE),

                new Object(6, 2, COLOR_RED, BLOCK),
                new Object(4, 3, COLOR_BLUE, BLOCK),
                new Object(5, 3, COLOR_YELLOW, BLOCK),
                new Object(2, 4, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_GREEN, BLOCK),
                new Object(5, 6, COLOR_WHITE, BLOCK),
                new Object(7, 6, COLOR_GREEN, BLOCK),
                new Object(6, 7, COLOR_RED, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[73] = new Level(new Object[]{
                new Object(8, 1, COLOR_WHITE, HOLE),
                new Object(1, 2, COLOR_GREEN, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(8, 2, COLOR_BLUE, HOLE),
                new Object(1, 7, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),

                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(3, 2, COLOR_GREEN, BLOCK),
                new Object(4, 2, COLOR_BLUE, BLOCK),
                new Object(5, 2, COLOR_GREEN, BLOCK),
                new Object(2, 7, COLOR_BLUE, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(4, 7, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_GREEN, BLOCK),
                new Object(1, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[74] = new Level(new Object[]{
                new Object(4, 1, COLOR_RED, HOLE),
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(3, 4, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_YELLOW, HOLE),
                new Object(8, 7, COLOR_RED, HOLE),

                new Object(3, 3, COLOR_RED, BLOCK),
                new Object(1, 4, COLOR_RED, BLOCK),
                new Object(4, 4, COLOR_RED, BLOCK),
                new Object(4, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK),
                new Object(2, 6, COLOR_YELLOW, BLOCK),
                new Object(5, 6, COLOR_YELLOW, BLOCK),
                new Object(6, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[75] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(2, 7, COLOR_BLUE, HOLE),
                new Object(6, 7, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_WHITE, BLOCK),
                new Object(6, 3, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(6, 4, COLOR_WHITE, BLOCK),
                new Object(3, 5, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_BLUE, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_BLUE, BLOCK),
                new Object(4, 6, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[76] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(1, 7, COLOR_YELLOW, HOLE),
                new Object(2, 7, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(1, 8, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),
                new Object(3, 8, COLOR_YELLOW, HOLE),

                new Object(3, 2, COLOR_GREEN, BLOCK),
                new Object(7, 2, COLOR_GREEN, BLOCK),
                new Object(2, 4, COLOR_YELLOW, BLOCK),
                new Object(4, 4, COLOR_YELLOW, BLOCK),
                new Object(7, 4, COLOR_YELLOW, BLOCK),
                new Object(3, 5, COLOR_YELLOW, BLOCK),
                new Object(6, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 7, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[77] = new Level(new Object[]{
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(1, 7, COLOR_WHITE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),
                new Object(1, 8, COLOR_GREEN, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_WHITE, BLOCK),
                new Object(2, 1, COLOR_WHITE, BLOCK),
                new Object(8, 1, COLOR_RED, BLOCK),
                new Object(1, 2, COLOR_WHITE, BLOCK),
                new Object(2, 2, COLOR_WHITE, BLOCK),
                new Object(7, 2, COLOR_GREEN, BLOCK),
                new Object(8, 7, COLOR_WHITE, BLOCK),
                new Object(7, 8, COLOR_WHITE, BLOCK),
                new Object(8, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[78] = new Level(new Object[]{
                new Object(1, 1, COLOR_WHITE, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_GREEN, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(1, 2, COLOR_GREEN, HOLE),
                new Object(1, 3, COLOR_RED, HOLE),
                new Object(1, 4, COLOR_YELLOW, HOLE),

                new Object(8, 8, COLOR_WHITE, BLOCK),
                new Object(7, 6, COLOR_BLUE, BLOCK),
                new Object(6, 5, COLOR_GREEN, BLOCK),
                new Object(5, 4, COLOR_RED, BLOCK),
                new Object(4, 3, COLOR_YELLOW, BLOCK),
                new Object(3, 2, COLOR_WHITE, BLOCK),
                new Object(2, 1, COLOR_BLUE, BLOCK),
                new Object(1, 5, COLOR_GREEN, BLOCK),
                new Object(1, 6, COLOR_RED, BLOCK),
                new Object(1, 7, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_GRAY);
        stepsLevels[78].setMinSteps(10);

        stepsLevels[79] = new Level(new Object[]{
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(4, 1, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(7, 1, COLOR_WHITE, BLOCK),
                new Object(5, 2, COLOR_WHITE, BLOCK),
                new Object(4, 3, COLOR_BLUE, BLOCK),
                new Object(7, 3, COLOR_RED, BLOCK),
                new Object(3, 4, COLOR_BLUE, BLOCK),
                new Object(2, 5, COLOR_BLUE, BLOCK),
                new Object(7, 5, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_GREEN, BLOCK),
                new Object(4, 7, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[80] = new Level(new Object[]{
                new Object(5, 1, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(7, 5, COLOR_RED, HOLE),
                new Object(4, 7, COLOR_RED, HOLE),
                new Object(6, 7, COLOR_RED, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),

                new Object(5, 2, COLOR_BLUE, BLOCK),
                new Object(4, 3, COLOR_RED, BLOCK),
                new Object(6, 3, COLOR_RED, BLOCK),
                new Object(3, 4, COLOR_RED, BLOCK),
                new Object(7, 4, COLOR_RED, BLOCK),
                new Object(2, 5, COLOR_RED, BLOCK),
                new Object(8, 5, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_RED, BLOCK),
                new Object(7, 6, COLOR_RED, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[81] = new Level(new Object[]{
                new Object(4, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_YELLOW, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),

                new Object(3, 2, COLOR_BLUE, BLOCK),
                new Object(7, 3, COLOR_WHITE, BLOCK),
                new Object(4, 5, COLOR_BLUE, BLOCK),
                new Object(3, 6, COLOR_WHITE, BLOCK),
                new Object(4, 6, COLOR_YELLOW, BLOCK),
                new Object(3, 7, COLOR_RED, BLOCK),
                new Object(4, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
                new Object(4, 8, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[82] = new Level(new Object[]{
                new Object(1, 1, COLOR_RED, HOLE),
                new Object(5, 1, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_WHITE, HOLE),
                new Object(8, 2, COLOR_WHITE, HOLE),
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(7, 4, COLOR_WHITE, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(8, 6, COLOR_WHITE, HOLE),
                new Object(3, 8, COLOR_WHITE, HOLE),
                new Object(7, 8, COLOR_WHITE, HOLE),

                new Object(3, 1, COLOR_RED, BLOCK),
                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(2, 2, COLOR_WHITE, BLOCK),
                new Object(6, 2, COLOR_WHITE, BLOCK),
                new Object(1, 4, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_WHITE, BLOCK),
                new Object(2, 6, COLOR_WHITE, BLOCK),
                new Object(6, 6, COLOR_WHITE, BLOCK),
                new Object(1, 8, COLOR_WHITE, BLOCK),
                new Object(5, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[83] = new Level(new Object[]{
                new Object(4, 3, COLOR_GREEN, HOLE),
                new Object(5, 3, COLOR_GREEN, HOLE),
                new Object(3, 4, COLOR_GREEN, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(3, 5, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(6, 5, COLOR_GREEN, HOLE),
                new Object(4, 6, COLOR_GREEN, HOLE),
                new Object(5, 6, COLOR_GREEN, HOLE),

                new Object(2, 2, COLOR_GREEN, BLOCK),
                new Object(6, 2, COLOR_GREEN, BLOCK),
                new Object(7, 2, COLOR_GREEN, BLOCK),
                new Object(2, 3, COLOR_GREEN, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(5, 4, COLOR_RED, BLOCK),
                new Object(7, 6, COLOR_GREEN, BLOCK),
                new Object(2, 7, COLOR_GREEN, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(7, 7, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[84] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_YELLOW, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(8, 6, COLOR_YELLOW, HOLE),
                new Object(6, 7, COLOR_YELLOW, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),
                new Object(8, 7, COLOR_YELLOW, HOLE),
                new Object(7, 8, COLOR_WHITE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(5, 1, COLOR_YELLOW, BLOCK),
                new Object(6, 1, COLOR_GREEN, BLOCK),
                new Object(3, 2, COLOR_YELLOW, BLOCK),
                new Object(1, 3, COLOR_YELLOW, BLOCK),
                new Object(3, 4, COLOR_WHITE, BLOCK),
                new Object(2, 5, COLOR_YELLOW, BLOCK),
                new Object(1, 6, COLOR_WHITE, BLOCK),
                new Object(2, 8, COLOR_RED, BLOCK),
                new Object(5, 8, COLOR_GREEN, BLOCK),
                new Object(6, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[85] = new Level(new Object[]{
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(1, 2, COLOR_YELLOW, HOLE),
                new Object(2, 3, COLOR_WHITE, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(8, 4, COLOR_YELLOW, HOLE),
                new Object(8, 6, COLOR_YELLOW, HOLE),
                new Object(1, 7, COLOR_RED, HOLE),
                new Object(2, 8, COLOR_RED, HOLE),
                new Object(4, 8, COLOR_GREEN, HOLE),

                new Object(6, 1, COLOR_GREEN, BLOCK),
                new Object(3, 2, COLOR_YELLOW, BLOCK),
                new Object(5, 2, COLOR_RED, BLOCK),
                new Object(8, 2, COLOR_YELLOW, BLOCK),
                new Object(6, 4, COLOR_YELLOW, BLOCK),
                new Object(2, 5, COLOR_WHITE, BLOCK),
                new Object(5, 5, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_GREEN, BLOCK),
                new Object(7, 7, COLOR_YELLOW, BLOCK),
                new Object(6, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[86] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(3, 2, COLOR_RED, HOLE),
                new Object(2, 3, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(7, 6, COLOR_BLUE, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(1, 1, COLOR_GREEN, BLOCK),
                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(8, 1, COLOR_RED, BLOCK),
                new Object(7, 2, COLOR_RED, BLOCK),
                new Object(8, 2, COLOR_RED, BLOCK),
                new Object(5, 5, COLOR_YELLOW, BLOCK),
                new Object(1, 7, COLOR_BLUE, BLOCK),
                new Object(2, 7, COLOR_BLUE, BLOCK),
                new Object(1, 8, COLOR_BLUE, BLOCK),
                new Object(2, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[87] = new Level(new Object[]{
                new Object(3, 3, COLOR_YELLOW, HOLE),
                new Object(4, 3, COLOR_BLUE, HOLE),
                new Object(5, 3, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(7, 3, COLOR_RED, HOLE),
                new Object(7, 5, COLOR_WHITE, HOLE),
                new Object(3, 6, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(7, 6, COLOR_RED, HOLE),

                new Object(3, 4, COLOR_BLUE, BLOCK),
                new Object(4, 4, COLOR_RED, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(6, 4, COLOR_GREEN, BLOCK),
                new Object(7, 4, COLOR_WHITE, BLOCK),
                new Object(2, 5, COLOR_WHITE, BLOCK),
                new Object(3, 7, COLOR_BLUE, BLOCK),
                new Object(4, 7, COLOR_RED, BLOCK),
                new Object(5, 7, COLOR_YELLOW, BLOCK),
                new Object(6, 7, COLOR_GREEN, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[88] = new Level(new Object[]{
                new Object(3, 1, COLOR_GREEN, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(1, 2, COLOR_YELLOW, HOLE),
                new Object(3, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(8, 2, COLOR_YELLOW, HOLE),
                new Object(3, 4, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(3, 6, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_YELLOW, HOLE),
                new Object(8, 8, COLOR_RED, HOLE),

                new Object(2, 1, COLOR_BLUE, BLOCK),
                new Object(6, 1, COLOR_GREEN, BLOCK),
                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(6, 2, COLOR_GREEN, BLOCK),
                new Object(2, 3, COLOR_YELLOW, BLOCK),
                new Object(7, 3, COLOR_YELLOW, BLOCK),
                new Object(4, 5, COLOR_YELLOW, BLOCK),
                new Object(5, 5, COLOR_YELLOW, BLOCK),
                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_YELLOW, BLOCK),
                new Object(1, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[89] = new Level(new Object[]{
                new Object(1, 1, COLOR_RED, HOLE),
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_WHITE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(2, 6, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(2, 8, COLOR_RED, HOLE),
                new Object(7, 8, COLOR_GREEN, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(1, 2, COLOR_WHITE, BLOCK),
                new Object(2, 2, COLOR_RED, BLOCK),
                new Object(3, 2, COLOR_WHITE, BLOCK),
                new Object(6, 2, COLOR_BLUE, BLOCK),
                new Object(8, 2, COLOR_BLUE, BLOCK),
                new Object(1, 7, COLOR_RED, BLOCK),
                new Object(2, 7, COLOR_BLUE, BLOCK),
                new Object(3, 7, COLOR_RED, BLOCK),
                new Object(6, 7, COLOR_GREEN, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK),
                new Object(8, 7, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[90] = new Level(new Object[]{
                new Object(1, 1, COLOR_YELLOW, HOLE),
                new Object(2, 1, COLOR_YELLOW, HOLE),
                new Object(3, 1, COLOR_YELLOW, HOLE),
                new Object(4, 1, COLOR_YELLOW, HOLE),
                new Object(5, 1, COLOR_WHITE, HOLE),
                new Object(7, 1, COLOR_WHITE, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(1, 5, COLOR_BLUE, HOLE),
                new Object(1, 6, COLOR_BLUE, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(6, 1, COLOR_RED, BLOCK),
                new Object(8, 1, COLOR_RED, BLOCK),
                new Object(7, 2, COLOR_YELLOW, BLOCK),
                new Object(8, 3, COLOR_BLUE, BLOCK),
                new Object(5, 4, COLOR_YELLOW, BLOCK),
                new Object(8, 5, COLOR_BLUE, BLOCK),
                new Object(3, 6, COLOR_YELLOW, BLOCK),
                new Object(8, 7, COLOR_BLUE, BLOCK),
                new Object(1, 8, COLOR_YELLOW, BLOCK),
                new Object(6, 8, COLOR_WHITE, BLOCK),
                new Object(8, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[91] = new Level(new Object[]{
                new Object(7, 1, COLOR_WHITE, HOLE),
                new Object(8, 1, COLOR_WHITE, HOLE),
                new Object(2, 2, COLOR_YELLOW, HOLE),
                new Object(3, 2, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(1, 4, COLOR_RED, HOLE),
                new Object(2, 4, COLOR_RED, HOLE),
                new Object(3, 4, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),

                new Object(3, 1, COLOR_GREEN, BLOCK),
                new Object(4, 1, COLOR_GREEN, BLOCK),
                new Object(3, 5, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_BLUE, BLOCK),
                new Object(5, 6, COLOR_RED, BLOCK),
                new Object(6, 6, COLOR_RED, BLOCK),
                new Object(7, 6, COLOR_RED, BLOCK),
                new Object(8, 6, COLOR_RED, BLOCK),
                new Object(2, 7, COLOR_WHITE, BLOCK),
                new Object(3, 7, COLOR_WHITE, BLOCK),
                new Object(5, 8, COLOR_YELLOW, BLOCK),
                new Object(6, 8, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[92] = new Level(new Object[]{
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(7, 1, COLOR_WHITE, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_BLUE, HOLE),
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(8, 4, COLOR_BLUE, HOLE),
                new Object(2, 5, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(6, 8, COLOR_BLUE, HOLE),

                new Object(2, 2, COLOR_BLUE, BLOCK),
                new Object(7, 2, COLOR_BLUE, BLOCK),
                new Object(2, 3, COLOR_WHITE, BLOCK),
                new Object(7, 3, COLOR_WHITE, BLOCK),
                new Object(4, 4, COLOR_WHITE, BLOCK),
                new Object(4, 5, COLOR_BLUE, BLOCK),
                new Object(6, 5, COLOR_BLUE, BLOCK),
                new Object(5, 6, COLOR_BLUE, BLOCK),
                new Object(6, 6, COLOR_WHITE, BLOCK),
                new Object(3, 7, COLOR_WHITE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK),
                new Object(3, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[93] = new Level(new Object[]{
                new Object(8, 3, COLOR_GREEN, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(4, 4, COLOR_WHITE, HOLE),
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_RED, HOLE),
                new Object(6, 5, COLOR_RED, HOLE),
                new Object(8, 5, COLOR_GREEN, HOLE),
                new Object(1, 6, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),

                new Object(7, 3, COLOR_RED, BLOCK),
                new Object(2, 4, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_WHITE, BLOCK),
                new Object(7, 4, COLOR_GREEN, BLOCK),
                new Object(8, 4, COLOR_BLUE, BLOCK),
                new Object(1, 5, COLOR_GREEN, BLOCK),
                new Object(7, 5, COLOR_RED, BLOCK),
                new Object(2, 6, COLOR_RED, BLOCK),
                new Object(3, 6, COLOR_WHITE, BLOCK),
                new Object(7, 6, COLOR_GREEN, BLOCK),
                new Object(8, 6, COLOR_BLUE, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[94] = new Level(new Object[]{
                new Object(4, 1, COLOR_WHITE, HOLE),
                new Object(5, 1, COLOR_GREEN, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_RED, HOLE),
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(8, 2, COLOR_YELLOW, HOLE),
                new Object(8, 3, COLOR_WHITE, HOLE),
                new Object(8, 5, COLOR_GREEN, HOLE),
                new Object(8, 6, COLOR_YELLOW, HOLE),
                new Object(1, 7, COLOR_BLUE, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),
                new Object(1, 8, COLOR_BLUE, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_RED, BLOCK),
                new Object(2, 1, COLOR_BLUE, BLOCK),
                new Object(3, 1, COLOR_WHITE, BLOCK),
                new Object(7, 1, COLOR_BLUE, BLOCK),
                new Object(1, 3, COLOR_YELLOW, BLOCK),
                new Object(1, 4, COLOR_WHITE, BLOCK),
                new Object(8, 4, COLOR_GREEN, BLOCK),
                new Object(1, 5, COLOR_GREEN, BLOCK),
                new Object(1, 6, COLOR_YELLOW, BLOCK),
                new Object(5, 8, COLOR_GREEN, BLOCK),
                new Object(6, 8, COLOR_WHITE, BLOCK),
                new Object(7, 8, COLOR_RED, BLOCK),
                new Object(8, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[95] = new Level(new Object[]{
                new Object(1, 1, COLOR_YELLOW, HOLE),
                new Object(6, 1, COLOR_GREEN, HOLE),
                new Object(7, 1, COLOR_GREEN, HOLE),
                new Object(8, 1, COLOR_GREEN, HOLE),
                new Object(4, 2, COLOR_GREEN, HOLE),
                new Object(6, 2, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_GREEN, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_YELLOW, HOLE),
                new Object(8, 6, COLOR_RED, HOLE),
                new Object(6, 8, COLOR_GREEN, HOLE),
                new Object(7, 8, COLOR_GREEN, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(1, 2, COLOR_GREEN, BLOCK),
                new Object(1, 3, COLOR_GREEN, BLOCK),
                new Object(3, 3, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(1, 4, COLOR_GREEN, BLOCK),
                new Object(1, 5, COLOR_GREEN, BLOCK),
                new Object(3, 5, COLOR_GREEN, BLOCK),
                new Object(5, 5, COLOR_YELLOW, BLOCK),
                new Object(1, 6, COLOR_GREEN, BLOCK),
                new Object(1, 7, COLOR_GREEN, BLOCK),
                new Object(3, 7, COLOR_GREEN, BLOCK),
                new Object(5, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
                new Object(1, 8, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[96] = new Level(new Object[]{
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_GREEN, HOLE),
                new Object(8, 2, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_BLUE, HOLE),
                new Object(6, 3, COLOR_BLUE, HOLE),
                new Object(2, 4, COLOR_GREEN, HOLE),
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_BLUE, HOLE),
                new Object(6, 5, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_WHITE, HOLE),

                new Object(1, 2, COLOR_RED, BLOCK),
                new Object(2, 2, COLOR_RED, BLOCK),
                new Object(4, 3, COLOR_GREEN, BLOCK),
                new Object(5, 3, COLOR_RED, BLOCK),
                new Object(6, 4, COLOR_GREEN, BLOCK),
                new Object(7, 4, COLOR_BLUE, BLOCK),
                new Object(4, 5, COLOR_RED, BLOCK),
                new Object(2, 6, COLOR_WHITE, BLOCK),
                new Object(7, 6, COLOR_BLUE, BLOCK),
                new Object(4, 7, COLOR_BLUE, BLOCK),
                new Object(5, 7, COLOR_GREEN, BLOCK),
                new Object(7, 7, COLOR_WHITE, BLOCK),
                new Object(8, 7, COLOR_BLUE, BLOCK),
                new Object(2, 8, COLOR_BLUE, BLOCK),
                new Object(6, 8, COLOR_WHITE, BLOCK),
        }, BACK_COLOR_RED);

        stepsLevels[97] = new Level(new Object[]{
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(8, 2, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(5, 3, COLOR_WHITE, HOLE),
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(5, 4, COLOR_GREEN, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(3, 5, COLOR_WHITE, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(6, 5, COLOR_WHITE, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(1, 7, COLOR_WHITE, HOLE),
                new Object(7, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_RED, BLOCK),
                new Object(7, 1, COLOR_WHITE, BLOCK),
                new Object(8, 1, COLOR_GREEN, BLOCK),
                new Object(1, 2, COLOR_WHITE, BLOCK),
                new Object(4, 2, COLOR_WHITE, BLOCK),
                new Object(5, 2, COLOR_WHITE, BLOCK),
                new Object(2, 4, COLOR_WHITE, BLOCK),
                new Object(7, 4, COLOR_WHITE, BLOCK),
                new Object(2, 5, COLOR_WHITE, BLOCK),
                new Object(7, 5, COLOR_WHITE, BLOCK),
                new Object(4, 7, COLOR_WHITE, BLOCK),
                new Object(5, 7, COLOR_WHITE, BLOCK),
                new Object(8, 7, COLOR_WHITE, BLOCK),
                new Object(1, 8, COLOR_BLUE, BLOCK),
                new Object(2, 8, COLOR_WHITE, BLOCK),
                new Object(8, 8, COLOR_YELLOW, BLOCK),
        }, BACK_COLOR_GRAY);

        stepsLevels[98] = new Level(new Object[]{
                new Object(6, 1, COLOR_GREEN, HOLE),
                new Object(7, 1, COLOR_GREEN, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),
                new Object(2, 3, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(7, 3, COLOR_YELLOW, HOLE),
                new Object(8, 3, COLOR_YELLOW, HOLE),
                new Object(2, 4, COLOR_BLUE, HOLE),
                new Object(3, 4, COLOR_RED, HOLE),
                new Object(2, 5, COLOR_BLUE, HOLE),
                new Object(3, 5, COLOR_RED, HOLE),
                new Object(7, 5, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_BLUE, HOLE),
                new Object(8, 6, COLOR_BLUE, HOLE),

                new Object(1, 1, COLOR_GREEN, BLOCK),
                new Object(2, 1, COLOR_GREEN, BLOCK),
                new Object(4, 2, COLOR_YELLOW, BLOCK),
                new Object(4, 4, COLOR_GREEN, BLOCK),
                new Object(5, 4, COLOR_GREEN, BLOCK),
                new Object(6, 4, COLOR_GREEN, BLOCK),
                new Object(5, 5, COLOR_BLUE, BLOCK),
                new Object(4, 6, COLOR_WHITE, BLOCK),
                new Object(6, 6, COLOR_BLUE, BLOCK),
                new Object(7, 6, COLOR_BLUE, BLOCK),
                new Object(2, 7, COLOR_YELLOW, BLOCK),
                new Object(3, 7, COLOR_YELLOW, BLOCK),
                new Object(5, 7, COLOR_RED, BLOCK),
                new Object(6, 7, COLOR_RED, BLOCK),
                new Object(7, 7, COLOR_RED, BLOCK),
                new Object(3, 8, COLOR_BLUE, BLOCK),
                new Object(4, 8, COLOR_BLUE, BLOCK),
        }, BACK_COLOR_BLUE);

        stepsLevels[99] = new Level(new Object[]{
                new Object(6, 1, COLOR_GREEN, HOLE),
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),
                new Object(7, 3, COLOR_YELLOW, HOLE),
                new Object(8, 3, COLOR_YELLOW, HOLE),
                new Object(1, 4, COLOR_YELLOW, HOLE),
                new Object(3, 4, COLOR_GREEN, HOLE),
                new Object(7, 4, COLOR_GREEN, HOLE),
                new Object(2, 5, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(6, 5, COLOR_BLUE, HOLE),
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 6, COLOR_YELLOW, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(8, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_YELLOW, HOLE),
                new Object(5, 7, COLOR_YELLOW, HOLE),
                new Object(5, 8, COLOR_BLUE, HOLE),

                new Object(2, 1, COLOR_BLUE, BLOCK),
                new Object(5, 1, COLOR_GREEN, BLOCK),
                new Object(8, 1, COLOR_BLUE, BLOCK),
                new Object(4, 2, COLOR_YELLOW, BLOCK),
                new Object(6, 2, COLOR_BLUE, BLOCK),
                new Object(2, 3, COLOR_GREEN, BLOCK),
                new Object(4, 3, COLOR_BLUE, BLOCK),
                new Object(5, 4, COLOR_BLUE, BLOCK),
                new Object(6, 4, COLOR_GREEN, BLOCK),
                new Object(8, 4, COLOR_GREEN, BLOCK),
                new Object(1, 5, COLOR_YELLOW, BLOCK),
                new Object(7, 6, COLOR_BLUE, BLOCK),
                new Object(3, 7, COLOR_YELLOW, BLOCK),
                new Object(7, 7, COLOR_YELLOW, BLOCK),
                new Object(8, 7, COLOR_YELLOW, BLOCK),
                new Object(1, 8, COLOR_YELLOW, BLOCK),
                new Object(6, 8, COLOR_YELLOW, BLOCK),
                new Object(8, 8, COLOR_GREEN, BLOCK),
        }, BACK_COLOR_YELLOW);

        stepsLevels[100] = new Level(new Object[]{
                new Object(1, 1, COLOR_GREEN, HOLE),
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(3, 1, COLOR_RED, HOLE),
                new Object(1, 2, COLOR_GREEN, HOLE),
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(1, 3, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_YELLOW, HOLE),
                new Object(2, 4, COLOR_GREEN, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_BLUE, HOLE),
                new Object(3, 5, COLOR_GREEN, HOLE),
                new Object(7, 5, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_YELLOW, HOLE),
                new Object(8, 6, COLOR_BLUE, HOLE),
                new Object(5, 7, COLOR_YELLOW, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),
                new Object(6, 8, COLOR_RED, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),
                new Object(8, 8, COLOR_RED, HOLE),

                new Object(5, 1, COLOR_RED, BLOCK),
                new Object(6, 1, COLOR_RED, BLOCK),
                new Object(7, 1, COLOR_RED, BLOCK),
                new Object(5, 2, COLOR_YELLOW, BLOCK),
                new Object(8, 2, COLOR_BLUE, BLOCK),
                new Object(4, 3, COLOR_YELLOW, BLOCK),
                new Object(8, 3, COLOR_BLUE, BLOCK),
                new Object(3, 4, COLOR_GREEN, BLOCK),
                new Object(7, 4, COLOR_BLUE, BLOCK),
                new Object(2, 5, COLOR_GREEN, BLOCK),
                new Object(4, 5, COLOR_WHITE, BLOCK),
                new Object(6, 5, COLOR_BLUE, BLOCK),
                new Object(1, 6, COLOR_GREEN, BLOCK),
                new Object(5, 6, COLOR_YELLOW, BLOCK),
                new Object(1, 7, COLOR_GREEN, BLOCK),
                new Object(4, 7, COLOR_YELLOW, BLOCK),
                new Object(2, 8, COLOR_RED, BLOCK),
                new Object(3, 8, COLOR_RED, BLOCK),
                new Object(4, 8, COLOR_RED, BLOCK),
        }, BACK_COLOR_GREEN);

        stepsLevels[101] = new Level(new Object[]{
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(4, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_WHITE, HOLE),
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(8, 3, COLOR_BLUE, HOLE),
                new Object(8, 4, COLOR_RED, HOLE),
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(6, 5, COLOR_YELLOW, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_YELLOW, HOLE),
                new Object(4, 7, COLOR_WHITE, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),
                new Object(4, 8, COLOR_RED, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),

                new Object(1, 1, COLOR_WHITE, BLOCK),    //1
                new Object(1, 2, COLOR_WHITE, BLOCK),    //2
                new Object(3, 2, COLOR_BLUE, BLOCK), //3
                new Object(5, 2, COLOR_WHITE, BLOCK),    //4
                new Object(1, 4, COLOR_YELLOW, BLOCK),   //5
                new Object(5, 4, COLOR_RED, BLOCK),  //6
                new Object(7, 4, COLOR_BLUE, BLOCK), //7
                new Object(1, 6, COLOR_RED, BLOCK),  //8
                new Object(3, 6, COLOR_BLUE, BLOCK), //9
                new Object(5, 6, COLOR_YELLOW, BLOCK),   //10
                new Object(1, 7, COLOR_YELLOW, BLOCK),   //11
                new Object(7, 7, COLOR_RED, BLOCK),  //12
                new Object(8, 7, COLOR_RED, BLOCK),  //13
                new Object(1, 8, COLOR_YELLOW, BLOCK),   //14
                new Object(3, 8, COLOR_WHITE, BLOCK),    //15
                new Object(7, 8, COLOR_RED, BLOCK),  //16
                new Object(8, 8, COLOR_RED, BLOCK),  //17
        }, BACK_COLOR_RED);
        stepsLevels[101].setMinSteps(33);
    }
}
