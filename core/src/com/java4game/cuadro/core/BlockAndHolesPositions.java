package com.java4game.cuadro.core;

/**
 * Created by FOGOK on 04.01.2017 11:53.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class BlockAndHolesPositions {

    public static final int COLOR_BLUE = 0, COLOR_GREEN = 1, COLOR_RED = 2, COLOR_WHITE = 3, COLOR_YELLOW = 4;
    public static final int BACK_COLOR_BLUE = 0, BACK_COLOR_GREEN = 2, BACK_COLOR_GRAY = 3, BACK_COLOR_RED = 4, BACK_COLOR_YELLOW = 5;
    public static final boolean CUBE = true, HOLE = false;

    public static class Object {
        private int x, y, type;
        private boolean isCube;

        public Object(int x, int y, int type, boolean isCube) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.isCube = isCube;
        }

        public void setCube(boolean cube) {
            isCube = cube;
        }

        public boolean isCube() {
            return isCube;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getType() {
            return type;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
    public static class Level{
        private Object objects[];
        private int backgroundColor;
        private int minSteps;
        public Level(Object objects[], int backgroundColor){
            this.objects = objects;
            this.backgroundColor = backgroundColor;
            minSteps = 0;
        }

        public int getMinSteps() {
            return minSteps;
        }

        public void setMinSteps(int minSteps) {
            this.minSteps = minSteps;
        }

        public int getBackgroundColor() {
            return backgroundColor;
        }

        public Object[] getObjects() {
            return objects;
        }
    }

    private static Level levels[];

    static {
        levels = new Level[102];


        levels[1] = new Level(new Object[]{
                new Object(2, 4, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[2] = new Level(new Object[]{
                new Object(2, 6, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_BLUE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[3] = new Level(new Object[]{
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),

                new Object(6, 3, COLOR_YELLOW, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[4] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),

                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(6, 6, COLOR_GREEN, CUBE)
        }, BACK_COLOR_GREEN);
        levels[5] = new Level(new Object[]{
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[5].setMinSteps(3);
        levels[6] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(6, 7, COLOR_BLUE, CUBE),
                new Object(3, 5, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[7] = new Level(new Object[]{
                new Object(4, 7, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),

                new Object(4, 4, COLOR_BLUE, CUBE),
                new Object(5, 3, COLOR_BLUE, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[8] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[9] = new Level(new Object[]{
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(3, 2, COLOR_GREEN, CUBE),
                new Object(6, 6, COLOR_BLUE, CUBE)
        }, BACK_COLOR_GREEN);
        levels[10] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(4, 3, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_GREEN, CUBE),
                new Object(2, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[11] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_WHITE, CUBE),
                new Object(2, 6, COLOR_WHITE, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[11].setMinSteps(4);
        levels[12] = new Level(new Object[]{
                new Object(1, 2, COLOR_RED, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_BLUE, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
                new Object(8, 7, COLOR_RED, CUBE)
        }, BACK_COLOR_GREEN);
        levels[13] = new Level(new Object[]{
                new Object(7, 4, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_RED, CUBE),
                new Object(2, 6, COLOR_GREEN, CUBE),
                new Object(2, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[13].setMinSteps(5);
        levels[14] = new Level(new Object[]{
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),

                new Object(3, 4, COLOR_YELLOW, CUBE),
                new Object(6, 5, COLOR_WHITE, CUBE),
                new Object(7, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[15] = new Level(new Object[]{
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(4, 8, COLOR_RED, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),

                new Object(4, 1, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_RED, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE)
        }, BACK_COLOR_RED);
        levels[15].setMinSteps(5);
        levels[16] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(5, 3, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(4, 3, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[17] = new Level(new Object[]{
                new Object(3, 5, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),

                new Object(4, 5, COLOR_GREEN, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[17].setMinSteps(5);
        levels[18] = new Level(new Object[]{
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 2, COLOR_BLUE, CUBE),
                new Object(2, 2, COLOR_RED, CUBE),
                new Object(5, 5, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[19] = new Level(new Object[]{
                new Object(2, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE),
                new Object(8, 2, COLOR_BLUE, CUBE)
        }, BACK_COLOR_GREEN);
        levels[20] = new Level(new Object[]{
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),

                new Object(1, 3, COLOR_WHITE, CUBE),
                new Object(2, 3, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_BLUE);
        levels[21] = new Level(new Object[]{
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(3, 7, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_RED, CUBE),
                new Object(7, 5, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[21].setMinSteps(6);
        levels[22] = new Level(new Object[]{
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),

                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_YELLOW, CUBE),
                new Object(3, 2, COLOR_GREEN, CUBE)
        }, BACK_COLOR_GRAY);
        levels[22].setMinSteps(6);
        levels[23] = new Level(new Object[]{
                new Object(1, 1, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(3, 5, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_RED, CUBE),
                new Object(4, 6, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[23].setMinSteps(7);
        levels[24] = new Level(new Object[]{
                new Object(3, 6, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(4, 2, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),

                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(3, 5, COLOR_BLUE, CUBE),
                new Object(5, 1, COLOR_YELLOW, CUBE),
                new Object(7, 3, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[25] = new Level(new Object[]{
                new Object(2, 5, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(7, 4, COLOR_RED, HOLE),

                new Object(4, 6, COLOR_RED, CUBE),
                new Object(5, 5, COLOR_RED, CUBE),
                new Object(4, 4, COLOR_GREEN, CUBE),
                new Object(6, 5, COLOR_GREEN, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[26] = new Level(new Object[]{
                new Object(1, 5, COLOR_WHITE, HOLE),
                new Object(1, 3, COLOR_GREEN, HOLE),
                new Object(8, 5, COLOR_BLUE, HOLE),
                new Object(8, 3, COLOR_WHITE, HOLE),

                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(7, 5, COLOR_WHITE, CUBE),
                new Object(7, 3, COLOR_GREEN, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[26].setMinSteps(8);
        levels[27] = new Level(new Object[]{
                new Object(1, 6, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_YELLOW, HOLE),
                new Object(8, 6, COLOR_RED, HOLE),

                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(4, 6, COLOR_RED, CUBE),
                new Object(5, 6, COLOR_RED, CUBE),
                new Object(6, 6, COLOR_RED, CUBE)
        }, BACK_COLOR_GREEN);
        levels[27].setMinSteps(8);
        levels[28] = new Level(new Object[]{
                new Object(3, 8, COLOR_RED, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(8, 3, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(3, 3, COLOR_GREEN, CUBE),
                new Object(7, 6, COLOR_WHITE, CUBE),
                new Object(7, 1, COLOR_RED, CUBE)
        }, BACK_COLOR_GREEN);
        levels[28].setMinSteps(5);
        levels[29] = new Level(new Object[]{
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),

                new Object(2, 2, COLOR_WHITE, CUBE),
                new Object(5, 3, COLOR_GREEN, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE)
        }, BACK_COLOR_RED);
        levels[30] = new Level(new Object[]{
                new Object(1, 5, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 6, COLOR_RED, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, CUBE),
                new Object(5, 2, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_RED, CUBE),
                new Object(2, 7, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[31] = new Level(new Object[]{
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(8, 4, COLOR_GREEN, HOLE),

                new Object(1, 4, COLOR_GREEN, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(7, 8, COLOR_WHITE, CUBE),
                new Object(6, 1, COLOR_RED, CUBE)
        }, BACK_COLOR_GRAY);
        levels[31].setMinSteps(9);
        levels[32] = new Level(new Object[]{
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_BLUE, HOLE),
                new Object(6, 4, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),

                new Object(2, 3, COLOR_BLUE, CUBE),
                new Object(2, 4, COLOR_GREEN, CUBE),
                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_RED, CUBE),
                new Object(6, 7, COLOR_BLUE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[33] = new Level(new Object[]{
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),

                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(3, 7, COLOR_BLUE, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(6, 7, COLOR_BLUE, CUBE),
                new Object(8, 1, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[34] = new Level(new Object[]{
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(2, 1, COLOR_RED, CUBE),
                new Object(4, 4, COLOR_YELLOW, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(5, 6, COLOR_RED, CUBE),
                new Object(6, 3, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GREEN);
        levels[35] = new Level(new Object[]{
                new Object(1, 8, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(1, 1, COLOR_YELLOW, CUBE),
                new Object(2, 1, COLOR_RED, CUBE),
                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(6, 3, COLOR_WHITE, CUBE)
        }, BACK_COLOR_RED);
        levels[36] = new Level(new Object[]{
                new Object(3, 3, COLOR_YELLOW, HOLE),
                new Object(4, 6, COLOR_YELLOW, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),

                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(2, 4, COLOR_YELLOW, CUBE),
                new Object(3, 6, COLOR_YELLOW, CUBE),
                new Object(5, 5, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_GRAY);
        levels[37] = new Level(new Object[]{
                new Object(4, 7, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(6, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_WHITE, CUBE),
                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(3, 2, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_RED, CUBE),
                new Object(5, 2, COLOR_RED, CUBE)
        }, BACK_COLOR_BLUE);
        levels[37].setMinSteps(9);
        levels[38] = new Level(new Object[]{
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(8, 7, COLOR_GREEN, HOLE),

                new Object(1, 7, COLOR_YELLOW, CUBE),
                new Object(1, 6, COLOR_YELLOW, CUBE),
                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(7, 3, COLOR_RED, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[38].setMinSteps(6);
        levels[39] = new Level(new Object[]{
                new Object(3, 4, COLOR_WHITE, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_WHITE, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),

                new Object(2, 4, COLOR_YELLOW, CUBE),
                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_WHITE, CUBE),
                new Object(7, 8, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GREEN);
        levels[40] = new Level(new Object[]{
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(2, 3, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(5, 7, COLOR_RED, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_RED, CUBE),
                new Object(5, 2, COLOR_RED, CUBE),
                new Object(5, 8, COLOR_WHITE, CUBE),
                new Object(6, 8, COLOR_RED, CUBE),
                new Object(7, 2, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[41] = new Level(new Object[]{
                new Object(4, 7, COLOR_GREEN, HOLE),
                new Object(5, 7, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),

                new Object(2, 2, COLOR_GREEN, CUBE),
                new Object(7, 2, COLOR_GREEN, CUBE),
                new Object(4, 4, COLOR_GREEN, CUBE),
                new Object(5, 4, COLOR_GREEN, CUBE),
                new Object(4, 6, COLOR_GREEN, CUBE),
                new Object(5, 6, COLOR_GREEN, CUBE),
        }, BACK_COLOR_GREEN);
        levels[42] = new Level(new Object[]{
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(8, 1, COLOR_GREEN, HOLE),

                new Object(1, 8, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(3, 3, COLOR_BLUE, CUBE),
                new Object(3, 6, COLOR_RED, CUBE),
                new Object(5, 3, COLOR_YELLOW, CUBE),
                new Object(6, 3, COLOR_RED, CUBE)
        }, BACK_COLOR_GREEN);
        levels[43] = new Level(new Object[]{
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),

                new Object(5, 6, COLOR_WHITE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE),
                new Object(4, 5, COLOR_YELLOW, CUBE),
                new Object(6, 5, COLOR_YELLOW, CUBE),
                new Object(5, 3, COLOR_YELLOW, CUBE),
                new Object(5, 1, COLOR_WHITE, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[44] = new Level(new Object[]{
                new Object(4, 3, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(1, 1, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(3, 3, COLOR_BLUE, CUBE),
                new Object(5, 3, COLOR_GREEN, CUBE),
                new Object(6, 3, COLOR_RED, CUBE),
                new Object(8, 1, COLOR_GREEN, CUBE)
        }, BACK_COLOR_YELLOW);

        levels[45] = new Level(new Object[]{
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(4, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_WHITE, CUBE),
                new Object(3, 2, COLOR_WHITE, CUBE),
                new Object(4, 2, COLOR_BLUE, CUBE),
                new Object(5, 2, COLOR_WHITE, CUBE),
                new Object(2, 7, COLOR_RED, CUBE),
                new Object(4, 7, COLOR_BLUE, CUBE),
        }, BACK_COLOR_BLUE);

        levels[46] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(8, 4, COLOR_GREEN, HOLE),
                new Object(1, 5, COLOR_GREEN, HOLE),
                new Object(2, 7, COLOR_YELLOW, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),

                new Object(4, 1, COLOR_RED, CUBE),
                new Object(5, 1, COLOR_GREEN, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(4, 5, COLOR_YELLOW, CUBE),
                new Object(3, 6, COLOR_YELLOW, CUBE),
                new Object(4, 8, COLOR_GREEN, CUBE),
        }, BACK_COLOR_BLUE);


        levels[47] = new Level(new Object[]{
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(1, 5, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_WHITE, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),

                new Object(5, 3, COLOR_WHITE, CUBE),
                new Object(2, 5, COLOR_RED, CUBE),
                new Object(3, 5, COLOR_RED, CUBE),
                new Object(5, 5, COLOR_WHITE, CUBE),
                new Object(6, 5, COLOR_YELLOW, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[48] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 3, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(6, 6, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),

                new Object(6, 2, COLOR_GREEN, CUBE),
                new Object(7, 2, COLOR_RED, CUBE),
                new Object(7, 5, COLOR_GREEN, CUBE),
                new Object(2, 6, COLOR_GREEN, CUBE),
                new Object(3, 6, COLOR_RED, CUBE),
                new Object(2, 7, COLOR_RED, CUBE),
        }, BACK_COLOR_GRAY);

        levels[49] = new Level(new Object[]{
                new Object(2, 1, COLOR_GREEN, HOLE),
                new Object(5, 1, COLOR_GREEN, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(3, 4, COLOR_WHITE, CUBE),
                new Object(7, 4, COLOR_WHITE, CUBE),
                new Object(8, 7, COLOR_GREEN, CUBE),
                new Object(2, 8, COLOR_WHITE, CUBE),
                new Object(3, 8, COLOR_GREEN, CUBE),
                new Object(4, 8, COLOR_GREEN, CUBE),
        }, BACK_COLOR_RED);

        levels[50] = new Level(new Object[]{
                new Object(2, 3, COLOR_BLUE, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(7, 5, COLOR_BLUE, HOLE),
                new Object(8, 5, COLOR_BLUE, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(1, 2, COLOR_BLUE, CUBE),
                new Object(3, 4, COLOR_BLUE, CUBE),
                new Object(1, 5, COLOR_BLUE, CUBE),
                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(5, 5, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_BLUE, CUBE),
        }, BACK_COLOR_RED);

        levels[51] = new Level(new Object[]{
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_WHITE, HOLE),
                new Object(7, 6, COLOR_WHITE, HOLE),
                new Object(8, 6, COLOR_WHITE, HOLE),
                new Object(5, 7, COLOR_WHITE, HOLE),

                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(4, 5, COLOR_WHITE, CUBE),
                new Object(1, 6, COLOR_WHITE, CUBE),
                new Object(2, 6, COLOR_WHITE, CUBE),
                new Object(3, 6, COLOR_WHITE, CUBE),
                new Object(4, 6, COLOR_WHITE, CUBE),
                new Object(4, 7, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GRAY);
        levels[51].setMinSteps(4);

        levels[52] = new Level(new Object[]{
                new Object(5, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_BLUE, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(2, 1, COLOR_WHITE, CUBE),
                new Object(7, 2, COLOR_YELLOW, CUBE),
                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(4, 7, COLOR_RED, CUBE),
                new Object(5, 7, COLOR_BLUE, CUBE),
                new Object(6, 7, COLOR_BLUE, CUBE),
        }, BACK_COLOR_BLUE);
        levels[52].setMinSteps(8);

        levels[53] = new Level(new Object[]{
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(6, 5, COLOR_BLUE, HOLE),
                new Object(2, 6, COLOR_YELLOW, HOLE),
                new Object(6, 6, COLOR_GREEN, HOLE),
                new Object(8, 6, COLOR_BLUE, HOLE),
                new Object(1, 7, COLOR_WHITE, HOLE),

                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(7, 2, COLOR_YELLOW, CUBE),
                new Object(3, 3, COLOR_YELLOW, CUBE),
                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(3, 5, COLOR_WHITE, CUBE),
                new Object(4, 7, COLOR_RED, CUBE),
                new Object(3, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[54] = new Level(new Object[]{
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(7, 4, COLOR_BLUE, HOLE),
                new Object(6, 5, COLOR_RED, HOLE),
                new Object(3, 8, COLOR_RED, HOLE),

                new Object(3, 1, COLOR_RED, CUBE),
                new Object(3, 3, COLOR_WHITE, CUBE),
                new Object(2, 4, COLOR_BLUE, CUBE),
                new Object(4, 4, COLOR_RED, CUBE),
                new Object(8, 4, COLOR_BLUE, CUBE),
                new Object(5, 5, COLOR_YELLOW, CUBE),
                new Object(6, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_GREEN);

        levels[55] = new Level(new Object[]{
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(5, 1, COLOR_WHITE, HOLE),
                new Object(6, 3, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_GREEN, HOLE),
                new Object(5, 5, COLOR_GREEN, HOLE),
                new Object(6, 8, COLOR_WHITE, HOLE),
                new Object(8, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_GREEN, CUBE),
                new Object(4, 1, COLOR_WHITE, CUBE),
                new Object(7, 1, COLOR_WHITE, CUBE),
                new Object(8, 1, COLOR_GREEN, CUBE),
                new Object(3, 6, COLOR_GREEN, CUBE),
                new Object(1, 8, COLOR_WHITE, CUBE),
                new Object(3, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_RED);

        levels[56] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(7, 2, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(1, 1, COLOR_RED, CUBE),
                new Object(8, 1, COLOR_RED, CUBE),
                new Object(3, 5, COLOR_YELLOW, CUBE),
                new Object(4, 5, COLOR_BLUE, CUBE),
                new Object(6, 5, COLOR_YELLOW, CUBE),
                new Object(1, 8, COLOR_RED, CUBE),
                new Object(8, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_GRAY);

        levels[57] = new Level(new Object[]{
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(6, 2, COLOR_YELLOW, HOLE),
                new Object(3, 3, COLOR_BLUE, HOLE),
                new Object(1, 4, COLOR_BLUE, HOLE),
                new Object(4, 6, COLOR_BLUE, HOLE),
                new Object(6, 7, COLOR_YELLOW, HOLE),
                new Object(6, 8, COLOR_YELLOW, HOLE),

                new Object(5, 2, COLOR_BLUE, CUBE),
                new Object(2, 3, COLOR_YELLOW, CUBE),
                new Object(2, 4, COLOR_YELLOW, CUBE),
                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(1, 7, COLOR_BLUE, CUBE),
                new Object(5, 8, COLOR_BLUE, CUBE),
                new Object(7, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_BLUE);

        levels[58] = new Level(new Object[]{
                new Object(7, 1, COLOR_RED, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(2, 5, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_GREEN, HOLE),
                new Object(6, 5, COLOR_GREEN, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(6, 7, COLOR_RED, HOLE),

                new Object(4, 2, COLOR_WHITE, CUBE),
                new Object(5, 2, COLOR_RED, CUBE),
                new Object(2, 3, COLOR_GREEN, CUBE),
                new Object(3, 4, COLOR_RED, CUBE),
                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(4, 6, COLOR_RED, CUBE),
                new Object(3, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[59] = new Level(new Object[]{
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(5, 1, COLOR_WHITE, HOLE),
                new Object(2, 6, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_WHITE, HOLE),
                new Object(1, 8, COLOR_WHITE, HOLE),
                new Object(3, 8, COLOR_RED, HOLE),
                new Object(5, 8, COLOR_GREEN, HOLE),

                new Object(4, 2, COLOR_WHITE, CUBE),
                new Object(6, 2, COLOR_RED, CUBE),
                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(8, 4, COLOR_RED, CUBE),
                new Object(3, 5, COLOR_WHITE, CUBE),
                new Object(7, 6, COLOR_GREEN, CUBE),
                new Object(8, 7, COLOR_RED, CUBE),
        }, BACK_COLOR_GREEN);

        levels[60] = new Level(new Object[]{
                new Object(5, 2, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(2, 4, COLOR_YELLOW, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(8, 4, COLOR_YELLOW, HOLE),
                new Object(4, 7, COLOR_YELLOW, HOLE),

                new Object(4, 2, COLOR_YELLOW, CUBE),
                new Object(1, 4, COLOR_YELLOW, CUBE),
                new Object(3, 4, COLOR_YELLOW, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(7, 4, COLOR_YELLOW, CUBE),
                new Object(2, 7, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_RED);


        levels[61] = new Level(new Object[]{
                new Object(2, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(2, 3, COLOR_GREEN, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(2, 6, COLOR_GREEN, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(2, 2, COLOR_WHITE, CUBE),
                new Object(7, 2, COLOR_RED, CUBE),
                new Object(2, 4, COLOR_GREEN, CUBE),
                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(2, 5, COLOR_GREEN, CUBE),
                new Object(7, 5, COLOR_GREEN, CUBE),
                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_BLUE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[62] = new Level(new Object[]{
                new Object(1, 1, COLOR_BLUE, HOLE),
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_BLUE, HOLE),
                new Object(8, 3, COLOR_BLUE, HOLE),
                new Object(7, 4, COLOR_WHITE, HOLE),
                new Object(8, 4, COLOR_WHITE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_BLUE, HOLE),

                new Object(2, 3, COLOR_BLUE, CUBE),
                new Object(6, 3, COLOR_BLUE, CUBE),
                new Object(1, 4, COLOR_WHITE, CUBE),
                new Object(2, 4, COLOR_WHITE, CUBE),
                new Object(1, 5, COLOR_BLUE, CUBE),
                new Object(8, 5, COLOR_BLUE, CUBE),
                new Object(1, 6, COLOR_BLUE, CUBE),
                new Object(8, 6, COLOR_BLUE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[63] = new Level(new Object[]{
                new Object(6, 1, COLOR_YELLOW, HOLE),
                new Object(7, 2, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_YELLOW, HOLE),
                new Object(7, 4, COLOR_YELLOW, HOLE),
                new Object(6, 5, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(7, 8, COLOR_YELLOW, HOLE),

                new Object(3, 1, COLOR_YELLOW, CUBE),
                new Object(2, 2, COLOR_YELLOW, CUBE),
                new Object(3, 3, COLOR_BLUE, CUBE),
                new Object(2, 4, COLOR_RED, CUBE),
                new Object(4, 4, COLOR_YELLOW, CUBE),
                new Object(3, 5, COLOR_GREEN, CUBE),
                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(3, 7, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[64] = new Level(new Object[]{
                new Object(1, 1, COLOR_YELLOW, HOLE),
                new Object(2, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(8, 1, COLOR_BLUE, HOLE),
                new Object(1, 8, COLOR_BLUE, HOLE),
                new Object(2, 8, COLOR_BLUE, HOLE),
                new Object(7, 8, COLOR_YELLOW, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(7, 4, COLOR_BLUE, CUBE),
                new Object(5, 5, COLOR_BLUE, CUBE),
                new Object(7, 5, COLOR_YELLOW, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE),
                new Object(7, 6, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_BLUE, CUBE),
                new Object(7, 7, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_GREEN);

        levels[65] = new Level(new Object[]{
                new Object(3, 3, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_RED, HOLE),
                new Object(5, 6, COLOR_RED, HOLE),
                new Object(7, 6, COLOR_RED, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),
                new Object(7, 8, COLOR_RED, HOLE),

                new Object(5, 3, COLOR_RED, CUBE),
                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_RED, CUBE),
                new Object(7, 5, COLOR_YELLOW, CUBE),
                new Object(6, 6, COLOR_RED, CUBE),
                new Object(5, 7, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
                new Object(6, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_RED);

        levels[66] = new Level(new Object[]{
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(2, 2, COLOR_YELLOW, HOLE),
                new Object(1, 4, COLOR_GREEN, HOLE),
                new Object(2, 4, COLOR_RED, HOLE),
                new Object(8, 6, COLOR_GREEN, HOLE),
                new Object(1, 8, COLOR_RED, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),
                new Object(8, 8, COLOR_GREEN, HOLE),

                new Object(5, 2, COLOR_BLUE, CUBE),
                new Object(6, 2, COLOR_YELLOW, CUBE),
                new Object(7, 2, COLOR_RED, CUBE),
                new Object(5, 4, COLOR_GREEN, CUBE),
                new Object(6, 4, COLOR_RED, CUBE),
                new Object(7, 4, COLOR_YELLOW, CUBE),
                new Object(3, 6, COLOR_GREEN, CUBE),
                new Object(3, 8, COLOR_GREEN, CUBE),
        }, BACK_COLOR_GREEN);

        levels[67] = new Level(new Object[]{
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(4, 2, COLOR_WHITE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),
                new Object(6, 4, COLOR_WHITE, HOLE),
                new Object(2, 5, COLOR_WHITE, HOLE),
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(3, 6, COLOR_GREEN, HOLE),
                new Object(4, 6, COLOR_WHITE, HOLE),

                new Object(6, 2, COLOR_WHITE, CUBE),
                new Object(3, 3, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_WHITE, CUBE),
                new Object(3, 4, COLOR_WHITE, CUBE),
                new Object(7, 5, COLOR_BLUE, CUBE),
                new Object(8, 6, COLOR_WHITE, CUBE),
                new Object(2, 7, COLOR_WHITE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[68] = new Level(new Object[]{
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(5, 4, COLOR_RED, HOLE),
                new Object(4, 5, COLOR_RED, HOLE),
                new Object(5, 5, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_WHITE, CUBE),
                new Object(8, 1, COLOR_WHITE, CUBE),
                new Object(3, 3, COLOR_RED, CUBE),
                new Object(6, 3, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_RED, CUBE),
                new Object(6, 6, COLOR_RED, CUBE),
                new Object(1, 8, COLOR_WHITE, CUBE),
                new Object(8, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_BLUE);

        levels[69] = new Level(new Object[]{
                new Object(4, 1, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(6, 3, COLOR_BLUE, HOLE),
                new Object(4, 4, COLOR_YELLOW, HOLE),
                new Object(5, 5, COLOR_YELLOW, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(7, 7, COLOR_GREEN, HOLE),
                new Object(5, 8, COLOR_YELLOW, HOLE),

                new Object(6, 1, COLOR_GREEN, CUBE),
                new Object(8, 2, COLOR_BLUE, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(7, 4, COLOR_YELLOW, CUBE),
                new Object(2, 5, COLOR_YELLOW, CUBE),
                new Object(4, 5, COLOR_YELLOW, CUBE),
                new Object(1, 7, COLOR_BLUE, CUBE),
                new Object(3, 8, COLOR_GREEN, CUBE),
        }, BACK_COLOR_GRAY);

        levels[70] = new Level(new Object[]{
                new Object(1, 1, COLOR_WHITE, HOLE),
                new Object(2, 1, COLOR_WHITE, HOLE),
                new Object(3, 1, COLOR_WHITE, HOLE),
                new Object(1, 2, COLOR_WHITE, HOLE),
                new Object(2, 2, COLOR_WHITE, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(1, 3, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_RED, HOLE),

                new Object(8, 1, COLOR_WHITE, CUBE),
                new Object(7, 2, COLOR_WHITE, CUBE),
                new Object(3, 3, COLOR_RED, CUBE),
                new Object(6, 3, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(4, 5, COLOR_WHITE, CUBE),
                new Object(3, 6, COLOR_WHITE, CUBE),
                new Object(2, 7, COLOR_WHITE, CUBE),
                new Object(1, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[71] = new Level(new Object[]{
                new Object(1, 1, COLOR_GREEN, HOLE),
                new Object(3, 1, COLOR_BLUE, HOLE),
                new Object(6, 1, COLOR_RED, HOLE),
                new Object(3, 2, COLOR_BLUE, HOLE),
                new Object(6, 2, COLOR_WHITE, HOLE),
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(1, 8, COLOR_GREEN, HOLE),
                new Object(3, 8, COLOR_BLUE, HOLE),
                new Object(8, 8, COLOR_YELLOW, HOLE),

                new Object(8, 1, COLOR_YELLOW, CUBE),
                new Object(6, 3, COLOR_BLUE, CUBE),
                new Object(1, 4, COLOR_GREEN, CUBE),
                new Object(6, 4, COLOR_BLUE, CUBE),
                new Object(1, 5, COLOR_GREEN, CUBE),
                new Object(6, 5, COLOR_BLUE, CUBE),
                new Object(6, 6, COLOR_BLUE, CUBE),
                new Object(6, 7, COLOR_RED, CUBE),
                new Object(6, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_BLUE);

        levels[72] = new Level(new Object[]{
                new Object(3, 2, COLOR_RED, HOLE),
                new Object(7, 3, COLOR_GREEN, HOLE),
                new Object(3, 4, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(6, 5, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_RED, HOLE),
                new Object(3, 6, COLOR_BLUE, HOLE),
                new Object(3, 7, COLOR_RED, HOLE),
                new Object(4, 7, COLOR_GREEN, HOLE),

                new Object(6, 2, COLOR_RED, CUBE),
                new Object(4, 3, COLOR_BLUE, CUBE),
                new Object(5, 3, COLOR_YELLOW, CUBE),
                new Object(2, 4, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_GREEN, CUBE),
                new Object(5, 6, COLOR_WHITE, CUBE),
                new Object(7, 6, COLOR_GREEN, CUBE),
                new Object(6, 7, COLOR_RED, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[73] = new Level(new Object[]{
                new Object(8, 1, COLOR_WHITE, HOLE),
                new Object(1, 2, COLOR_GREEN, HOLE),
                new Object(6, 2, COLOR_GREEN, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(8, 2, COLOR_BLUE, HOLE),
                new Object(1, 7, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(8, 7, COLOR_BLUE, HOLE),

                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(3, 2, COLOR_GREEN, CUBE),
                new Object(4, 2, COLOR_BLUE, CUBE),
                new Object(5, 2, COLOR_GREEN, CUBE),
                new Object(2, 7, COLOR_BLUE, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(4, 7, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_GREEN, CUBE),
                new Object(1, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GREEN);

        levels[74] = new Level(new Object[]{
                new Object(4, 1, COLOR_RED, HOLE),
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(5, 2, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_YELLOW, HOLE),
                new Object(6, 3, COLOR_RED, HOLE),
                new Object(3, 4, COLOR_YELLOW, HOLE),
                new Object(6, 4, COLOR_YELLOW, HOLE),
                new Object(7, 5, COLOR_YELLOW, HOLE),
                new Object(8, 7, COLOR_RED, HOLE),

                new Object(3, 3, COLOR_RED, CUBE),
                new Object(1, 4, COLOR_RED, CUBE),
                new Object(4, 4, COLOR_RED, CUBE),
                new Object(4, 5, COLOR_YELLOW, CUBE),
                new Object(5, 5, COLOR_RED, CUBE),
                new Object(2, 6, COLOR_YELLOW, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE),
                new Object(6, 7, COLOR_YELLOW, CUBE),
                new Object(7, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_RED);

        levels[75] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(3, 2, COLOR_WHITE, HOLE),
                new Object(7, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(2, 7, COLOR_BLUE, HOLE),
                new Object(6, 7, COLOR_WHITE, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_WHITE, CUBE),
                new Object(6, 3, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(6, 4, COLOR_WHITE, CUBE),
                new Object(3, 5, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_BLUE, CUBE),
                new Object(5, 5, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_BLUE, CUBE),
                new Object(4, 6, COLOR_BLUE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[76] = new Level(new Object[]{
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_RED, HOLE),
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(1, 7, COLOR_YELLOW, HOLE),
                new Object(2, 7, COLOR_GREEN, HOLE),
                new Object(6, 7, COLOR_GREEN, HOLE),
                new Object(1, 8, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),
                new Object(3, 8, COLOR_YELLOW, HOLE),

                new Object(3, 2, COLOR_GREEN, CUBE),
                new Object(7, 2, COLOR_GREEN, CUBE),
                new Object(2, 4, COLOR_YELLOW, CUBE),
                new Object(4, 4, COLOR_YELLOW, CUBE),
                new Object(7, 4, COLOR_YELLOW, CUBE),
                new Object(3, 5, COLOR_YELLOW, CUBE),
                new Object(6, 5, COLOR_YELLOW, CUBE),
                new Object(5, 7, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
        }, BACK_COLOR_BLUE);

        levels[77] = new Level(new Object[]{
                new Object(6, 3, COLOR_WHITE, HOLE),
                new Object(5, 4, COLOR_WHITE, HOLE),
                new Object(4, 5, COLOR_WHITE, HOLE),
                new Object(3, 6, COLOR_WHITE, HOLE),
                new Object(1, 7, COLOR_WHITE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),
                new Object(1, 8, COLOR_GREEN, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),

                new Object(1, 1, COLOR_WHITE, CUBE),
                new Object(2, 1, COLOR_WHITE, CUBE),
                new Object(8, 1, COLOR_RED, CUBE),
                new Object(1, 2, COLOR_WHITE, CUBE),
                new Object(2, 2, COLOR_WHITE, CUBE),
                new Object(7, 2, COLOR_GREEN, CUBE),
                new Object(8, 7, COLOR_WHITE, CUBE),
                new Object(7, 8, COLOR_WHITE, CUBE),
                new Object(8, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[78] = new Level(new Object[]{
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

                new Object(8, 8, COLOR_WHITE, CUBE),
                new Object(7, 6, COLOR_BLUE, CUBE),
                new Object(6, 5, COLOR_GREEN, CUBE),
                new Object(5, 4, COLOR_RED, CUBE),
                new Object(4, 3, COLOR_YELLOW, CUBE),
                new Object(3, 2, COLOR_WHITE, CUBE),
                new Object(2, 1, COLOR_BLUE, CUBE),
                new Object(1, 5, COLOR_GREEN, CUBE),
                new Object(1, 6, COLOR_RED, CUBE),
                new Object(1, 7, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_GRAY);
        levels[78].setMinSteps(10);

        levels[79] = new Level(new Object[]{
                new Object(2, 1, COLOR_RED, HOLE),
                new Object(4, 1, COLOR_WHITE, HOLE),
                new Object(2, 3, COLOR_BLUE, HOLE),
                new Object(5, 4, COLOR_GREEN, HOLE),
                new Object(4, 5, COLOR_BLUE, HOLE),
                new Object(5, 5, COLOR_WHITE, HOLE),
                new Object(6, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),
                new Object(7, 7, COLOR_WHITE, HOLE),

                new Object(7, 1, COLOR_WHITE, CUBE),
                new Object(5, 2, COLOR_WHITE, CUBE),
                new Object(4, 3, COLOR_BLUE, CUBE),
                new Object(7, 3, COLOR_RED, CUBE),
                new Object(3, 4, COLOR_BLUE, CUBE),
                new Object(2, 5, COLOR_BLUE, CUBE),
                new Object(7, 5, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_GREEN, CUBE),
                new Object(4, 7, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GREEN);

        levels[80] = new Level(new Object[]{
                new Object(5, 1, COLOR_RED, HOLE),
                new Object(5, 3, COLOR_RED, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(6, 4, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_BLUE, HOLE),
                new Object(7, 5, COLOR_RED, HOLE),
                new Object(4, 7, COLOR_RED, HOLE),
                new Object(6, 7, COLOR_RED, HOLE),
                new Object(5, 8, COLOR_RED, HOLE),

                new Object(5, 2, COLOR_BLUE, CUBE),
                new Object(4, 3, COLOR_RED, CUBE),
                new Object(6, 3, COLOR_RED, CUBE),
                new Object(3, 4, COLOR_RED, CUBE),
                new Object(7, 4, COLOR_RED, CUBE),
                new Object(2, 5, COLOR_RED, CUBE),
                new Object(8, 5, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_RED, CUBE),
                new Object(7, 6, COLOR_RED, CUBE),
        }, BACK_COLOR_RED);

        levels[81] = new Level(new Object[]{
                new Object(4, 1, COLOR_YELLOW, HOLE),
                new Object(7, 1, COLOR_BLUE, HOLE),
                new Object(2, 2, COLOR_RED, HOLE),
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(6, 2, COLOR_RED, HOLE),
                new Object(3, 3, COLOR_WHITE, HOLE),
                new Object(4, 3, COLOR_YELLOW, HOLE),
                new Object(6, 7, COLOR_BLUE, HOLE),
                new Object(2, 8, COLOR_WHITE, HOLE),

                new Object(3, 2, COLOR_BLUE, CUBE),
                new Object(7, 3, COLOR_WHITE, CUBE),
                new Object(4, 5, COLOR_BLUE, CUBE),
                new Object(3, 6, COLOR_WHITE, CUBE),
                new Object(4, 6, COLOR_YELLOW, CUBE),
                new Object(3, 7, COLOR_RED, CUBE),
                new Object(4, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
                new Object(4, 8, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[82] = new Level(new Object[]{
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

                new Object(3, 1, COLOR_RED, CUBE),
                new Object(7, 1, COLOR_RED, CUBE),
                new Object(2, 2, COLOR_WHITE, CUBE),
                new Object(6, 2, COLOR_WHITE, CUBE),
                new Object(1, 4, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_WHITE, CUBE),
                new Object(2, 6, COLOR_WHITE, CUBE),
                new Object(6, 6, COLOR_WHITE, CUBE),
                new Object(1, 8, COLOR_WHITE, CUBE),
                new Object(5, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[83] = new Level(new Object[]{
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

                new Object(2, 2, COLOR_GREEN, CUBE),
                new Object(6, 2, COLOR_GREEN, CUBE),
                new Object(7, 2, COLOR_GREEN, CUBE),
                new Object(2, 3, COLOR_GREEN, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(5, 4, COLOR_RED, CUBE),
                new Object(7, 6, COLOR_GREEN, CUBE),
                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(7, 7, COLOR_GREEN, CUBE),
        }, BACK_COLOR_BLUE);

        levels[84] = new Level(new Object[]{
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

                new Object(5, 1, COLOR_YELLOW, CUBE),
                new Object(6, 1, COLOR_GREEN, CUBE),
                new Object(3, 2, COLOR_YELLOW, CUBE),
                new Object(1, 3, COLOR_YELLOW, CUBE),
                new Object(3, 4, COLOR_WHITE, CUBE),
                new Object(2, 5, COLOR_YELLOW, CUBE),
                new Object(1, 6, COLOR_WHITE, CUBE),
                new Object(2, 8, COLOR_RED, CUBE),
                new Object(5, 8, COLOR_GREEN, CUBE),
                new Object(6, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[85] = new Level(new Object[]{
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

                new Object(6, 1, COLOR_GREEN, CUBE),
                new Object(3, 2, COLOR_YELLOW, CUBE),
                new Object(5, 2, COLOR_RED, CUBE),
                new Object(8, 2, COLOR_YELLOW, CUBE),
                new Object(6, 4, COLOR_YELLOW, CUBE),
                new Object(2, 5, COLOR_WHITE, CUBE),
                new Object(5, 5, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_GREEN, CUBE),
                new Object(7, 7, COLOR_YELLOW, CUBE),
                new Object(6, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_GREEN);

        levels[86] = new Level(new Object[]{
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

                new Object(1, 1, COLOR_GREEN, CUBE),
                new Object(7, 1, COLOR_RED, CUBE),
                new Object(8, 1, COLOR_RED, CUBE),
                new Object(7, 2, COLOR_RED, CUBE),
                new Object(8, 2, COLOR_RED, CUBE),
                new Object(5, 5, COLOR_YELLOW, CUBE),
                new Object(1, 7, COLOR_BLUE, CUBE),
                new Object(2, 7, COLOR_BLUE, CUBE),
                new Object(1, 8, COLOR_BLUE, CUBE),
                new Object(2, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_RED);

        levels[87] = new Level(new Object[]{
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

                new Object(3, 4, COLOR_BLUE, CUBE),
                new Object(4, 4, COLOR_RED, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(6, 4, COLOR_GREEN, CUBE),
                new Object(7, 4, COLOR_WHITE, CUBE),
                new Object(2, 5, COLOR_WHITE, CUBE),
                new Object(3, 7, COLOR_BLUE, CUBE),
                new Object(4, 7, COLOR_RED, CUBE),
                new Object(5, 7, COLOR_YELLOW, CUBE),
                new Object(6, 7, COLOR_GREEN, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[88] = new Level(new Object[]{
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

                new Object(2, 1, COLOR_BLUE, CUBE),
                new Object(6, 1, COLOR_GREEN, CUBE),
                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(6, 2, COLOR_GREEN, CUBE),
                new Object(2, 3, COLOR_YELLOW, CUBE),
                new Object(7, 3, COLOR_YELLOW, CUBE),
                new Object(4, 5, COLOR_YELLOW, CUBE),
                new Object(5, 5, COLOR_YELLOW, CUBE),
                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_YELLOW, CUBE),
                new Object(1, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_GREEN);

        levels[89] = new Level(new Object[]{
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

                new Object(1, 2, COLOR_WHITE, CUBE),
                new Object(2, 2, COLOR_RED, CUBE),
                new Object(3, 2, COLOR_WHITE, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE),
                new Object(8, 2, COLOR_BLUE, CUBE),
                new Object(1, 7, COLOR_RED, CUBE),
                new Object(2, 7, COLOR_BLUE, CUBE),
                new Object(3, 7, COLOR_RED, CUBE),
                new Object(6, 7, COLOR_GREEN, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE),
                new Object(8, 7, COLOR_GREEN, CUBE),
        }, BACK_COLOR_BLUE);

        levels[90] = new Level(new Object[]{
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

                new Object(6, 1, COLOR_RED, CUBE),
                new Object(8, 1, COLOR_RED, CUBE),
                new Object(7, 2, COLOR_YELLOW, CUBE),
                new Object(8, 3, COLOR_BLUE, CUBE),
                new Object(5, 4, COLOR_YELLOW, CUBE),
                new Object(8, 5, COLOR_BLUE, CUBE),
                new Object(3, 6, COLOR_YELLOW, CUBE),
                new Object(8, 7, COLOR_BLUE, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
                new Object(6, 8, COLOR_WHITE, CUBE),
                new Object(8, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[91] = new Level(new Object[]{
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

                new Object(3, 1, COLOR_GREEN, CUBE),
                new Object(4, 1, COLOR_GREEN, CUBE),
                new Object(3, 5, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_BLUE, CUBE),
                new Object(5, 6, COLOR_RED, CUBE),
                new Object(6, 6, COLOR_RED, CUBE),
                new Object(7, 6, COLOR_RED, CUBE),
                new Object(8, 6, COLOR_RED, CUBE),
                new Object(2, 7, COLOR_WHITE, CUBE),
                new Object(3, 7, COLOR_WHITE, CUBE),
                new Object(5, 8, COLOR_YELLOW, CUBE),
                new Object(6, 8, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_RED);

        levels[92] = new Level(new Object[]{
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

                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(7, 2, COLOR_BLUE, CUBE),
                new Object(2, 3, COLOR_WHITE, CUBE),
                new Object(7, 3, COLOR_WHITE, CUBE),
                new Object(4, 4, COLOR_WHITE, CUBE),
                new Object(4, 5, COLOR_BLUE, CUBE),
                new Object(6, 5, COLOR_BLUE, CUBE),
                new Object(5, 6, COLOR_BLUE, CUBE),
                new Object(6, 6, COLOR_WHITE, CUBE),
                new Object(3, 7, COLOR_WHITE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE),
                new Object(3, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_GRAY);

        levels[93] = new Level(new Object[]{
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

                new Object(7, 3, COLOR_RED, CUBE),
                new Object(2, 4, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_WHITE, CUBE),
                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(8, 4, COLOR_BLUE, CUBE),
                new Object(1, 5, COLOR_GREEN, CUBE),
                new Object(7, 5, COLOR_RED, CUBE),
                new Object(2, 6, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_WHITE, CUBE),
                new Object(7, 6, COLOR_GREEN, CUBE),
                new Object(8, 6, COLOR_BLUE, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
        }, BACK_COLOR_BLUE);

        levels[94] = new Level(new Object[]{
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

                new Object(1, 1, COLOR_RED, CUBE),
                new Object(2, 1, COLOR_BLUE, CUBE),
                new Object(3, 1, COLOR_WHITE, CUBE),
                new Object(7, 1, COLOR_BLUE, CUBE),
                new Object(1, 3, COLOR_YELLOW, CUBE),
                new Object(1, 4, COLOR_WHITE, CUBE),
                new Object(8, 4, COLOR_GREEN, CUBE),
                new Object(1, 5, COLOR_GREEN, CUBE),
                new Object(1, 6, COLOR_YELLOW, CUBE),
                new Object(5, 8, COLOR_GREEN, CUBE),
                new Object(6, 8, COLOR_WHITE, CUBE),
                new Object(7, 8, COLOR_RED, CUBE),
                new Object(8, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[95] = new Level(new Object[]{
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

                new Object(1, 2, COLOR_GREEN, CUBE),
                new Object(1, 3, COLOR_GREEN, CUBE),
                new Object(3, 3, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_RED, CUBE),
                new Object(1, 4, COLOR_GREEN, CUBE),
                new Object(1, 5, COLOR_GREEN, CUBE),
                new Object(3, 5, COLOR_GREEN, CUBE),
                new Object(5, 5, COLOR_YELLOW, CUBE),
                new Object(1, 6, COLOR_GREEN, CUBE),
                new Object(1, 7, COLOR_GREEN, CUBE),
                new Object(3, 7, COLOR_GREEN, CUBE),
                new Object(5, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_GREEN);

        levels[96] = new Level(new Object[]{
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

                new Object(1, 2, COLOR_RED, CUBE),
                new Object(2, 2, COLOR_RED, CUBE),
                new Object(4, 3, COLOR_GREEN, CUBE),
                new Object(5, 3, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_GREEN, CUBE),
                new Object(7, 4, COLOR_BLUE, CUBE),
                new Object(4, 5, COLOR_RED, CUBE),
                new Object(2, 6, COLOR_WHITE, CUBE),
                new Object(7, 6, COLOR_BLUE, CUBE),
                new Object(4, 7, COLOR_BLUE, CUBE),
                new Object(5, 7, COLOR_GREEN, CUBE),
                new Object(7, 7, COLOR_WHITE, CUBE),
                new Object(8, 7, COLOR_BLUE, CUBE),
                new Object(2, 8, COLOR_BLUE, CUBE),
                new Object(6, 8, COLOR_WHITE, CUBE),
        }, BACK_COLOR_RED);

        levels[97] = new Level(new Object[]{
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

                new Object(1, 1, COLOR_RED, CUBE),
                new Object(7, 1, COLOR_WHITE, CUBE),
                new Object(8, 1, COLOR_GREEN, CUBE),
                new Object(1, 2, COLOR_WHITE, CUBE),
                new Object(4, 2, COLOR_WHITE, CUBE),
                new Object(5, 2, COLOR_WHITE, CUBE),
                new Object(2, 4, COLOR_WHITE, CUBE),
                new Object(7, 4, COLOR_WHITE, CUBE),
                new Object(2, 5, COLOR_WHITE, CUBE),
                new Object(7, 5, COLOR_WHITE, CUBE),
                new Object(4, 7, COLOR_WHITE, CUBE),
                new Object(5, 7, COLOR_WHITE, CUBE),
                new Object(8, 7, COLOR_WHITE, CUBE),
                new Object(1, 8, COLOR_BLUE, CUBE),
                new Object(2, 8, COLOR_WHITE, CUBE),
                new Object(8, 8, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_GRAY);

        levels[98] = new Level(new Object[]{
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

                new Object(1, 1, COLOR_GREEN, CUBE),
                new Object(2, 1, COLOR_GREEN, CUBE),
                new Object(4, 2, COLOR_YELLOW, CUBE),
                new Object(4, 4, COLOR_GREEN, CUBE),
                new Object(5, 4, COLOR_GREEN, CUBE),
                new Object(6, 4, COLOR_GREEN, CUBE),
                new Object(5, 5, COLOR_BLUE, CUBE),
                new Object(4, 6, COLOR_WHITE, CUBE),
                new Object(6, 6, COLOR_BLUE, CUBE),
                new Object(7, 6, COLOR_BLUE, CUBE),
                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(3, 7, COLOR_YELLOW, CUBE),
                new Object(5, 7, COLOR_RED, CUBE),
                new Object(6, 7, COLOR_RED, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
                new Object(3, 8, COLOR_BLUE, CUBE),
                new Object(4, 8, COLOR_BLUE, CUBE),
        }, BACK_COLOR_BLUE);

        levels[99] = new Level(new Object[]{
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

                new Object(2, 1, COLOR_BLUE, CUBE),
                new Object(5, 1, COLOR_GREEN, CUBE),
                new Object(8, 1, COLOR_BLUE, CUBE),
                new Object(4, 2, COLOR_YELLOW, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE),
                new Object(2, 3, COLOR_GREEN, CUBE),
                new Object(4, 3, COLOR_BLUE, CUBE),
                new Object(5, 4, COLOR_BLUE, CUBE),
                new Object(6, 4, COLOR_GREEN, CUBE),
                new Object(8, 4, COLOR_GREEN, CUBE),
                new Object(1, 5, COLOR_YELLOW, CUBE),
                new Object(7, 6, COLOR_BLUE, CUBE),
                new Object(3, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_YELLOW, CUBE),
                new Object(8, 7, COLOR_YELLOW, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
                new Object(6, 8, COLOR_YELLOW, CUBE),
                new Object(8, 8, COLOR_GREEN, CUBE),
        }, BACK_COLOR_YELLOW);

        levels[100] = new Level(new Object[]{
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

                new Object(5, 1, COLOR_RED, CUBE),
                new Object(6, 1, COLOR_RED, CUBE),
                new Object(7, 1, COLOR_RED, CUBE),
                new Object(5, 2, COLOR_YELLOW, CUBE),
                new Object(8, 2, COLOR_BLUE, CUBE),
                new Object(4, 3, COLOR_YELLOW, CUBE),
                new Object(8, 3, COLOR_BLUE, CUBE),
                new Object(3, 4, COLOR_GREEN, CUBE),
                new Object(7, 4, COLOR_BLUE, CUBE),
                new Object(2, 5, COLOR_GREEN, CUBE),
                new Object(4, 5, COLOR_WHITE, CUBE),
                new Object(6, 5, COLOR_BLUE, CUBE),
                new Object(1, 6, COLOR_GREEN, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE),
                new Object(1, 7, COLOR_GREEN, CUBE),
                new Object(4, 7, COLOR_YELLOW, CUBE),
                new Object(2, 8, COLOR_RED, CUBE),
                new Object(3, 8, COLOR_RED, CUBE),
                new Object(4, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_GREEN);

        levels[101] = new Level(new Object[]{
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

                new Object(1, 1, COLOR_WHITE, CUBE),
                new Object(1, 2, COLOR_WHITE, CUBE),
                new Object(3, 2, COLOR_BLUE, CUBE),
                new Object(5, 2, COLOR_WHITE, CUBE),
                new Object(1, 4, COLOR_YELLOW, CUBE),
                new Object(5, 4, COLOR_RED, CUBE),
                new Object(7, 4, COLOR_BLUE, CUBE),
                new Object(1, 6, COLOR_RED, CUBE),
                new Object(3, 6, COLOR_BLUE, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE),
                new Object(1, 7, COLOR_YELLOW, CUBE),
                new Object(7, 7, COLOR_RED, CUBE),
                new Object(8, 7, COLOR_RED, CUBE),
                new Object(1, 8, COLOR_YELLOW, CUBE),
                new Object(3, 8, COLOR_WHITE, CUBE),
                new Object(7, 8, COLOR_RED, CUBE),
                new Object(8, 8, COLOR_RED, CUBE),
        }, BACK_COLOR_RED);
        levels[101].setMinSteps(33);


    }
///whoH =
    public static Level getLevel(int level){
        return levels[level];
    }



    ///blankLevel
//    levels[] = new Level(new Object[]{
//                new Object(0, 0, COLOR_, HOLE),
//                new Object(0, 0, COLOR_, HOLE),
//                new Object(0, 0, COLOR_, HOLE),
//
//                new Object(0, 0, COLOR_, CUBE),
//                new Object(0, 0, COLOR_, CUBE),
//                new Object(0, 0, COLOR_, CUBE)
//    }, BACK_COLOR_);
    //
}
