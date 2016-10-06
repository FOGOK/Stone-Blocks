package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.utils.DebugDrawer;
import com.java4game.cuadro.utils.FloatAnimator;

import java.math.BigDecimal;

/**
 * Created by FOGOK on 16.09.2016 21:33.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class SquareObject  extends GameObject{


    /**
     * Тип объекта, короче это типо все объекты на поле
     * (его можно уничтожить, задать анимацию и действия при уничтожении, а так же
     * позиция объекта задаётся относительно координат поля в
     * целых числах (тобишь округляются, (3 по x) и (7 по y) будет позиция в клетках)
     *
    * */

    float kindX, kindY;
    int sqX, sqY;
    Cube.Dir kindDir;

    boolean isCollect = false;
    boolean isDestroyed = false;
    boolean isEndedAnim = false;

    boolean isEndedCorrectPos = false;
    boolean isWired = false;
    boolean isCollected = false;
    int colorType;


    int kindedHash; //если 0, значит ни к чему не прикреплён, если >0, значит прикреплён к кубику и позиция равна этому числу в клетках

    int whoI = 0;
    int hash;
    FloatAnimator floatAnimator;


    //ссылки
    Rectangle sqBounds;
    ///
    public SquareObject(Sprite sprite, int x, int y, Rectangle sqBounds, int hash, int colorType) {
        super(sprite);
        this.hash = hash;
        kindedHash = 0;
        this.sqBounds = sqBounds;
        this.colorType = colorType;
        sqX = x;
        sqY = y;
        //устанавливаем позицию на поле в клетках и размер объекта
        setPosition(sqBounds.x + (x * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                sqBounds.y + (y * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
        setSize(LevelGen.sizeObjects);
        ///
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isCollect)
            collectAnimation();
    }


    protected void collectAnimation() {
        ///здесь реализовываем анимацию сбора предметов
        isDestroyed = true;
    }

    public void setKindedHash(int i){
        kindedHash = i;
    }

    public int getKindedHash(){
        return kindedHash;
    }

    public int getColorType(){
        return colorType;
    }

    public boolean isCollected(){
        return isCollected;
    }

    public void setCollected(boolean b){
        isCollected = b;
    }

    public void setWired(boolean b){
        isWired = b;
    }

    public boolean isWired(){
        return isWired;
    }

    public boolean isNormalPos(){
        int x = getSQX(), y = getSQY();
        return (x > -1 && x < LevelGen.SQSIZE + 1 && y > -1 && y < LevelGen.SQSIZE + 1);
    }

    public void normalizePos(){
        setPosition(sqBounds.x + (getSQX() * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                sqBounds.y + (getSQY() * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public boolean isEndedAnim(){
        return isEndedAnim;
    }

    public boolean isEndedCorrectPos(){
        return isEndedCorrectPos;
    }

    public void setEndedCorrectPos(boolean b){
        isEndedCorrectPos = b;
    }


    public void collect(){
        isCollect = true;
    }

    public void setWhoI(int whoI){
        this.whoI = whoI;
    }

    //получить координаты кубика в клетках
    BigDecimal bigDecimal;
    float posXiiP, posYiiP;





    //берём позициию объекта в клетках относительно всего объекта (параметр направления - направление кубика)
    public int getSSQX(){
        return getSQX(Cube.getDir());
    }

    public int getSSQY(){
        return getSQY(Cube.getDir());
    }
    ///

    //берём позициию объекта в клетках относительно всего объекта (параметр направления - направление движения кубика)
    public int getSQX(Cube.Dir dir){
        float dotMatch;    ///точка, относительно которой нужно отчситывать позицию, ставим её на грань объекта, относительно данного направления
        switch (dir){
            case LEFT:  dotMatch = 0f; break;
            case RIGHT: dotMatch = getW(); break;
            default:    dotMatch = getW() / 2f; break;
        }

        return getSQX(dotMatch);
    }
    public int getSQY(Cube.Dir dir) {
        float dotMatch;    ///точка, относительно которой нужно отчситывать позицию, ставим её на грань объекта, относительно данного направления
        switch (dir){
            case UP:  dotMatch = getW(); break;
            case DOWN: dotMatch = 0; break;
            default:    dotMatch = getW() / 2f; break;
        }
        DebugDrawer.drawRect(getX() + dotMatch, getY() + dotMatch);
        return getSQY(dotMatch);
    }
    ///

    //берём позицию объекта в клетках относительно центра
    public int getSQX(){
        return getSQX(getW() / 2);
    }

    public int getSQY(){
        return getSQY(getW() / 2);
    }
    ///

    //берём позицию объекта в клетках относительно точки, на этом объекте
    public int getSQX(float dotMatch){
        posXiiP = (getX() + dotMatch - sqBounds.getX()) / (sqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    public int getSQY(float dotMatch) {
        posYiiP = (getY() + dotMatch - sqBounds.getY()) / (sqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    ///


}
