package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.ColorCubeMover;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.ObjectsGen;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */

public class Cube extends GameObject{           ///класс кубика, который будет двигаться по игровому полю

    static Dir dir;
    Dir nextDir;
    public enum Dir{   //направление движения
        RIGHT, DOWN, LEFT, UP
    }
    float speed = 0.11f;
    float otst; //отступ от поля
    static boolean povorot;
    Random rnd = new Random();

    static float X, Y, SIZE;
//    static boolean IVERSEDIR;
    static boolean ISMOVECOLORCUBES;

    float sizeSquareF; //размер клетки поля

    Rectangle levSqBounds;

    //ссылки
    ObjectsGen objectsGen;
    ////

    public Cube(Sprite sprite, LevelSquare levelSquare, ObjectsGen objectsGen) {
        super(sprite);

        this.objectsGen = objectsGen;

        setSize(LevelGen.sizeObjects);
        this.otst = LevelSquare.otst  + (LevelSquare.sizOneSq - SIZE) / 2f;
        this.sprite.setOriginCenter();
        levSqBounds = levelSquare.getBounds();
        sizeSquareF = LevelSquare.sizOneSq + LevelSquare.otst * 2;

        ISMOVECOLORCUBES = false;
        povorot = false;

        dir = (rnd.nextBoolean()) ? ((rnd.nextBoolean()) ? Dir.RIGHT : Dir.LEFT) : ((rnd.nextBoolean()) ? Dir.UP : Dir.DOWN);
        revers = rnd.nextBoolean();

        if (dir.equals(Dir.RIGHT)){
            setXZA((!revers) ? false : true);
            setYZA((!revers) ? true : false);
        }else if (dir.equals(Dir.LEFT)){
            setXZA((!revers) ? true : false);
            setYZA((!revers) ? false : true);
        }else if (dir.equals(Dir.UP)){
            setXZA((!revers) ? false : true);
            setYZA((!revers) ? false : true);
        }else{
            setXZA((!revers) ? true : false);
            setYZA((!revers) ? true : false);
        }

        sprite.setRotation(0);
    }





    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        mathPosition();
    }


    int lastpSQX, lastpSQY; ///позиция в клетках относительно левого нижнего угла поля
    static int sqX, sqY;
    boolean chngX, cngTR;
    int chgVal;
    float pX, pY;

    boolean revers = false;

    public static int getSqX(){
        return sqX;
    }

    public static int getSqY(){
        return sqY;
    }

    public static boolean inmCHD = false, lockCHD = false;
    private void mathPosition(){

        sqX = lastpSQX = getSQX();
        sqY = lastpSQY = getSQY();

        switch (dir){
            case RIGHT:     ///если движемся вправо
                chngX = true;                                       //указываем, двигаемся по x или y
                chgVal = LevelGen.SQSIZE + 1;                       //указываем на какой клетке начать поворачивать
                cngTR = true;                                       //указываем, ставить кубик при достижении края (наверх | направо(в зависимости от chngX))
                nextDir = (revers) ? Dir.UP : Dir.DOWN;             //в зависимости от переменной revers говорим, куда двигатсья при достижении края
                pX = speed * Gm.mdT;                                ///указываем скорость x
                pY = 0f;                                            //указываем скорость y
                break;
            case DOWN:      ///если движемся вниз
                chngX = false;
                chgVal = -1;
                cngTR = false;
                nextDir = (revers) ? Dir.RIGHT : Dir.LEFT;
                pX = 0f;
                pY = -speed * Gm.mdT;
                break;
            case LEFT:      ///если движемся влево
                chngX = true;
                chgVal = -1;
                cngTR = false;
                nextDir = (revers) ? Dir.DOWN : Dir.UP;
                pX = -speed * Gm.mdT;
                pY = 0f;
                break;
            case UP:
                chngX = false;
                chgVal = LevelGen.SQSIZE + 1;
                cngTR = true;
                nextDir = (revers) ? Dir.LEFT : Dir.RIGHT;
                pX = 0f;
                pY = speed * Gm.mdT;
                break;
        }

        int k = (chngX) ? getSQX() : getSQY();
        if (!Handler.ISPAUSE)
            setPosition(getX() + pX, getY() + pY);
        if (k == chgVal){
            povorot = true;
            if ((chngX) ? isXOUT(cngTR) : isYOUT(cngTR)){
                if (chngX)
                    setXZA(cngTR);
                else
                    setYZA(cngTR);

                dir = nextDir;
            }
        }



        if (povorot){
            float delt = -120f * speed * Gm.mdT;
            if (revers) delt *= -1;
            if (!Handler.ISPAUSE)
                sprite.rotate(delt);

            if (Math.abs(sprite.getRotation() + delt) > limRot){
                sprite.setRotation(0);
                povorot = false;
            }
        }

        if (lastpSQX != getSQX() || lastpSQY != getSQY()){
            boolean qb = (chngX) ? lastPosInXY == getSQY() : lastPosInXY == getSQX();
            if ((((getSQX() == -1 || getSQX() == LevelGen.SQSIZE + 1) && getSQY() > -1 && getSQY() < LevelGen.SQSIZE + 1) ||
                    ((getSQY() == -1 || getSQY() == LevelGen.SQSIZE + 1) && getSQX() > -1 && getSQX() < LevelGen.SQSIZE + 1)) && !qb){
                lockCHD = false;
                lastPosInXY = -50;
            }
            else
                lockCHD = true;
            checkCollisionInObjects();
        }


//        inLastInSquare = (getSQX() > -1 && getSQX() < SQSIZE + 1 && getSQY() > -1 && getSQY() < SQSIZE + 1);

        if (Gdx.input.justTouched() && (!lockCHD || ISMOVECOLORCUBES) && isYInDown()){
            if (ISMOVECOLORCUBES){
                ColorCubeMover.isPregInv = true;
                ISMOVECOLORCUBES = false;
            }else{
                inmCHD = true;
                lockCHD = true;
            }
        }

        if (inmCHD){         ///если должны повернуть раньше края
            dir = nextDir;
            if (chngX) setSQX(getSQX()); else setSQY(getSQY());
            lastPosInXY = (chngX) ? getSQX() : getSQY();
            pX = pY = 0f;
            inmCHD = false;
            povorot = true;
        }

    }
    float limRot = 180;     /// на сколько поворачивать кубик
    int lastPosInXY;

    private boolean isYInDown(){
        return ((Gm.HEIGHT / Gdx.graphics.getHeight()) * (Gdx.graphics.getHeight() - Gdx.input.getY()) < levSqBounds.getY() + levSqBounds.getHeight());
    }

    private void checkCollisionInObjects(){
        for (int i = 0; i < objectsGen.getObjCount(); i++) {
            if (objectsGen.getObjects(i).getBounds().overlaps(getBounds()) && !objectsGen.getObjects(i).isEndedAnim())
                objectsGen.collectObject(i);
        }
        checkOut();
    }

    private void checkOut(){
        if (getSQX() > -1 && getSQX() < LevelGen.SQSIZE + 1 && getSQY() > -1 && getSQY() < LevelGen.SQSIZE + 1){
            if (!LevelSquare.isTrue[getSQX() + 1][getSQY() + 1])
                Handler.ISRESTART = true;
        }
    }

    //получить координаты кубика в клетках
    BigDecimal bigDecimal;
    float posXiiP, posYiiP;
    public int getSQX(){
        posXiiP = (getX() + SIZE / 2 - levSqBounds.getX()) / (levSqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    public int getSQY() {
        posYiiP = (getY() + SIZE / 2 - levSqBounds.getY()) / (levSqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    ///


    //устанавливаем позицию по x | y в клетках
    private void setSQX(int iX){        //устанавливаем x на определённую клетку внутри поля
        setPosition(levSqBounds.getX() + iX * sizeSquareF + (sizeSquareF - SIZE) / 2f, getY());
    }
    private void setSQY(int iY){    //устанавливаем y  на определённую клетку внутри поля
        setPosition(getX(), levSqBounds.getY() + iY * sizeSquareF + (sizeSquareF - SIZE) / 2f);
    }
    ///


    ///устанавливаем кубик за поле по x | y
    private void setXZA(boolean right){     //устанавливаем кубик за поле по x
        if (right)
            setPosition(levSqBounds.getX() + levSqBounds.getWidth() + otst, getY());
        else
            setPosition(levSqBounds.getX() - SIZE - otst, getY());
    }
    private void setYZA(boolean top){     //устанавливаем кубик за поле по y
        if (top)
            setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
        else
            setPosition(getX(), levSqBounds.getY() - SIZE - otst);
    }
    ///


    //проверяем, находится ли кубик за полем по x | y
    private boolean isXOUT(boolean right){
        return (right) ? getX() > levSqBounds.getX() + levSqBounds.getWidth() + otst : getX() < levSqBounds.getX() - SIZE - otst;
    }
    private boolean isYOUT(boolean top){
        return (top) ? getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst : getY() < levSqBounds.getY() - SIZE - otst;
    }
    ////

    @Override
    public void setSize(float size) {
        super.setSize(size);
        SIZE = size;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        X = x;
        Y = y;
    }

    public static void setMovedControl(boolean b) {
        ISMOVECOLORCUBES = b;
    }


    public static float getSX(){
        return X;
    }

    public static float getSY(){
        return Y;
    }

    public static Dir getDir(){
        return dir;
    }

    public static void inverseDir() {        ///меняем направление на противоположное (если лево то право или если вниз то вверх)
        if (dir == Dir.LEFT)
            dir = Dir.RIGHT;
        else if (dir == Dir.RIGHT)
            dir = Dir.LEFT;
        else if (dir == Dir.UP)
            dir = Dir.DOWN;
        else if (dir == Dir.DOWN)
            dir = Dir.UP;
        povorot = true;
    }







        ///govno!!!

//    public static int mmmm = 0;
//    String m;


//    m = Integer.toBinaryString(mmmm);
//
//
//    try {
//        rigDown = (m.charAt(0) == '0');
//    }catch (Exception e){}
//
//    try {
//        rigLef = (m.charAt(1) == '0');
//    }catch (Exception e){}
//
//    try {
//        upDown = (m.charAt(2) == '0');
//    }catch (Exception e){}
//
//    try {
//        revers = (m.charAt(3) == '0');
//    }catch (Exception e){}


//    Gm.DEBUG_VALUE1 = "x" + getSQX() + "y" + getSQY() + ";" + " mmm " + mmmm;
//    Gm.DEBUG_VALUE2 = "" + revers + " " + upDown + " " +  rigLef  + " " + rigDown + "\n" + m;
    //двигаем кубик
//        switch (dir){
//            case RIGHT:
//                setPosition(getX(), getY());
//                if (pSQX == SQSIZE + 1){   //значит, что достигли право-верхнего угла
//                    setXZA(true);
//                    povorot = true;
//                    dir = Dir.DOWN;
//                }
//                break;
//            case DOWN:
//                setPosition(getX(), getY() - speed * Gm.mdT);
//                if (pSQY == -1){                //значит, что достигли право-нижнего угла
//                    setYZA(false);
//                    povorot = true;
//                    dir = Dir.LEFT;
//                }
//                break;
//            case LEFT:
//                setPosition(getX() - speed * Gm.mdT, getY());
//                if (pSQX == -1){                //значит, что достигли лево-нижнего угла
//                    setXZA(false);
//                    povorot = true;
//                    dir = Dir.LEFT;
//                }
//                break;
//            case UP:
//                setPosition(getX(), getY() + speed * Gm.mdT);
//                if (getY() > levSqBounds.getY() + levSqBounds.getHeight() + otst){      //значит, что достигли лево-верхнего угла
//                    setPosition(getX(), levSqBounds.getY() + levSqBounds.getHeight() + otst);
//                    setYZA(true);
//                    dir = Dir.RIGHT;
//                    sprite.setRotation(360);
//                }
//                break;
//        }


//    switch (dir){
//        case RIGHT:
//            sprite.setOrigin(0f, 0f);
//            sprite.rotate(-speed * Gm.mdT * 50f);
//            if (sprite.getRotation() < 270){
//                povorot = false;
//                sprite.setOriginCenter();
//                sprite.setRotation(270);
//                setXZA(true);
//                setSQY(SQSIZE);
//                dir = Dir.DOWN;
//            }
//            break;
//        case DOWN:
//            sprite.setOrigin(0f, SIZE);
//            if (sprite.getRotation() == 270f) sprite.setRotation(360); /// нормализуем поворот кубика из-за смены точки поворота
//            sprite.rotate(-speed * Gm.mdT * 50f);
//            if (sprite.getRotation() < 271f){
//                povorot = false;
//                sprite.setOriginCenter();
//                sprite.setRotation(180);
//                setYZA(false);
//                setSQX(SQSIZE);
//                dir = Dir.LEFT;
//            }
//            break;
//        case LEFT:
//            break;
//        case UP:
//            break;
//
//    }


}
