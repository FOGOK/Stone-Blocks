package com.java4game.cuadro.core;

/**
 * Created by FOGOK on 04.01.2017 11:53.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class InitLevels {

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
        private float allSeconds;
        public Level(Object objects[], int backgroundColor){
            this.objects = objects;
            this.backgroundColor = backgroundColor;
            minSteps = 0;
            allSeconds = 0f;
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

    static {
        stepsLevels = new Level[102];
        InitStepsLevels.init(stepsLevels);

        timeLevels = new Level[22];
        InitTimeLevels.init(timeLevels);
    }
///whoH =
    public static Level getStepsLevels(int level){
        return stepsLevels[level];
    }

    public static Level getTimeLevels(int level) {
        return timeLevels[level];
    }

    ///blankLevel
//    stepsLevels[] = new Level(new Object[]{
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
