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
        levels = new Level[10];

        levels[0] = new Level(new Object[]{
                new Object(1, 1, COLOR_WHITE, HOLE),
                new Object(1, 2, COLOR_BLUE, HOLE),
                new Object(1, 3, COLOR_GREEN, HOLE),
                new Object(2, 4, COLOR_RED, HOLE),
                new Object(2, 5, COLOR_YELLOW, HOLE),
                new Object(2, 6, COLOR_WHITE, HOLE),
                new Object(2, 7, COLOR_BLUE, HOLE),
                new Object(3, 4, COLOR_GREEN, HOLE),
                new Object(4, 4, COLOR_RED, HOLE),
                new Object(5, 4, COLOR_YELLOW, HOLE),

                new Object(6, 1, COLOR_WHITE, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE),
                new Object(6, 3, COLOR_GREEN, CUBE),
                new Object(8, 4, COLOR_RED, CUBE),
                new Object(7, 5, COLOR_YELLOW, CUBE),
                new Object(7, 6, COLOR_WHITE, CUBE),
                new Object(8, 7, COLOR_BLUE, CUBE),
                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(1, 4, COLOR_RED, CUBE),
                new Object(8, 1, COLOR_YELLOW, CUBE),
        }, BACK_COLOR_GRAY);
        levels[0].setMinSteps(10);
//        levels[0] = new Level(new Object[]{
//                new Object(2, 4, COLOR_WHITE, HOLE),
//
//                new Object(5, 4, COLOR_WHITE, CUBE)
//        }, BACK_COLOR_GRAY);
        levels[1] = new Level(new Object[]{
                new Object(2, 6, COLOR_BLUE, HOLE),

                new Object(5, 3, COLOR_BLUE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[2] = new Level(new Object[]{
                new Object(1, 6, COLOR_YELLOW, HOLE),
                new Object(2, 8, COLOR_YELLOW, HOLE),

                new Object(6, 3, COLOR_YELLOW, CUBE),
                new Object(5, 6, COLOR_YELLOW, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[3] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),

                new Object(7, 4, COLOR_GREEN, CUBE),
                new Object(6, 6, COLOR_GREEN, CUBE)
        }, BACK_COLOR_GREEN);
        levels[4] = new Level(new Object[]{
                new Object(1, 2, COLOR_RED, HOLE),
                new Object(3, 5, COLOR_RED, HOLE),
                new Object(2, 7, COLOR_RED, HOLE),

                new Object(7, 1, COLOR_RED, CUBE),
                new Object(6, 4, COLOR_RED, CUBE),
                new Object(6, 7, COLOR_RED, CUBE)
        }, BACK_COLOR_RED);
        levels[5] = new Level(new Object[]{
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(6, 7, COLOR_BLUE, CUBE),
                new Object(3, 5, COLOR_WHITE, CUBE)
        }, BACK_COLOR_GRAY);
        levels[6] = new Level(new Object[]{
                new Object(4, 7, COLOR_BLUE, HOLE),
                new Object(5, 6, COLOR_BLUE, HOLE),
                new Object(2, 7, COLOR_WHITE, HOLE),

                new Object(2, 4, COLOR_BLUE, CUBE),
                new Object(8, 3, COLOR_BLUE, CUBE),
                new Object(7, 8, COLOR_WHITE, CUBE)
        }, BACK_COLOR_BLUE);
        levels[7] = new Level(new Object[]{
                new Object(4, 2, COLOR_YELLOW, HOLE),
                new Object(2, 2, COLOR_BLUE, HOLE),
                new Object(7, 2, COLOR_WHITE, HOLE),

                new Object(2, 7, COLOR_YELLOW, CUBE),
                new Object(7, 8, COLOR_BLUE, CUBE),
                new Object(3, 4, COLOR_WHITE, CUBE)
        }, BACK_COLOR_YELLOW);
        levels[8] = new Level(new Object[]{
                new Object(2, 2, COLOR_GREEN, HOLE),
                new Object(3, 7, COLOR_GREEN, HOLE),
                new Object(7, 7, COLOR_BLUE, HOLE),
                new Object(3, 4, COLOR_BLUE, HOLE),

                new Object(2, 7, COLOR_GREEN, CUBE),
                new Object(7, 8, COLOR_GREEN, CUBE),
                new Object(6, 2, COLOR_BLUE, CUBE),
                new Object(2, 1, COLOR_BLUE, CUBE)
        }, BACK_COLOR_GREEN);
        levels[9] = new Level(new Object[]{
                new Object(1, 3, COLOR_YELLOW, HOLE),
                new Object(8, 5, COLOR_RED, HOLE),
                new Object(3, 7, COLOR_BLUE, HOLE),
                new Object(5, 3, COLOR_GREEN, HOLE),
                new Object(7, 3, COLOR_WHITE, HOLE),

                new Object(2, 8, COLOR_YELLOW, CUBE),
                new Object(2, 4, COLOR_RED, CUBE),
                new Object(2, 2, COLOR_BLUE, CUBE),
                new Object(7, 5, COLOR_GREEN, CUBE),
                new Object(4, 2, COLOR_WHITE, CUBE)
        }, BACK_COLOR_RED);
    }

    public static Level getLevel(int level){
        return levels[level];
    }

}
