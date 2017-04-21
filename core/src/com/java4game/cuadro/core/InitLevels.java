package com.java4game.cuadro.core;

/**
 * Created by FOGOK on 04.01.2017 11:53.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class InitLevels {

    public static final int STEPS_LEVELS = 102, TIME_LEVELS = 22;

    public static final int COLOR_BLUE = 0, COLOR_GREEN = 1, COLOR_RED = 2, COLOR_WHITE = 3, COLOR_YELLOW = 4;
    public static final int BACK_COLOR_BLUE = 0, BACK_COLOR_GREEN = 2, BACK_COLOR_GRAY = 3, BACK_COLOR_RED = 4, BACK_COLOR_YELLOW = 5;
    public static final int BLOCK = 0, HOLE = 1, ARKADE_BOOSTER = 2, ARKADE_SLOWER = 3;

    public static class Object {
        private int x, y, type;
        private int blockType;

        public Object(int x, int y, int type, int blockType) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.blockType = blockType;
        }

        public void setCube(int blockType) {
            this.blockType = blockType;
        }

        public int getBlockType() {
            return blockType;
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
        private float allSeconds;
        public Level(Object objects[], int backgroundColor){
            this.objects = objects;
            this.backgroundColor = backgroundColor;
            minSteps = 0;
            allSeconds = 0f;
        }

        public Level(int sizeObjects, int backgroundColor){
            objects = new Object[sizeObjects];
            this.backgroundColor = backgroundColor;
            minSteps = 0;
            allSeconds = 0f;
        }

        public void setObject(Object object, int index){
            objects[index] = object;
        }

        public int getMinSteps() {
            return minSteps;
        }

        public float getAllSeconds() {
            return allSeconds;
        }

        public void setGoldSeconds(float allSeconds) {
            this.allSeconds = allSeconds;
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

    private static Level stepsLevels[], timeLevels[];

///whoH =
    public static Level getStepsLevels(int level){
        if (stepsLevels == null){
            stepsLevels = new Level[STEPS_LEVELS];
            InitStepsLevels.init(stepsLevels);
        }
        return stepsLevels[level];
    }

    public static Level getTimeLevels(int level) {
        if (timeLevels == null){
            timeLevels = new Level[TIME_LEVELS];
            InitTimeLevels.init(timeLevels);
        }
        return timeLevels[level];
    }


    public static void dispose(){
        stepsLevels = null;
        timeLevels = null;
    }


    ///blankLevel
//    stepsLevels[] = new Level(new Object[]{
//                new Object(0, 0, COLOR_, HOLE),
//                new Object(0, 0, COLOR_, HOLE),
//                new Object(0, 0, COLOR_, HOLE),
//
//                new Object(0, 0, COLOR_, BLOCK),
//                new Object(0, 0, COLOR_, BLOCK),
//                new Object(0, 0, COLOR_, BLOCK)
//    }, BACK_COLOR_);
    //
}
