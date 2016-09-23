package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
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

    boolean isWired = false;


    int kindedHash; //если 0, значит ни к чему не прикреплён, если >0, значит прикреплён к кубику и позиция равна этому числу в клетках

    int whoI = 0;
    int hash;
    FloatAnimator floatAnimator;


    //ссылки
    Rectangle sqBounds;
    ///
    public SquareObject(Sprite sprite, int x, int y, Rectangle sqBounds, int hash) {
        super(sprite);
        this.hash = hash;
        kindedHash = 0;
        this.sqBounds = sqBounds;
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

    public void setWired(boolean b){
        isWired = b;
    }

    public boolean isWired(){
        return isWired;
    }

    public boolean isNormalPos(){
        int x = getSQX(getX()), y = getSQY(getY());
        return (x > -1 && x < LevelGen.SQSIZE + 1 && y > -1 && y < LevelGen.SQSIZE + 1);
    }

    public void normalizePos(){
        setPosition(sqBounds.x + (getSQX(getX()) * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f,
                sqBounds.y + (getSQY(getY()) * (LevelSquare.sizOneSq + LevelSquare.otst * 2)) + (LevelSquare.sizOneSq - LevelGen.sizeObjects) / 2f);
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public boolean isEndedAnim(){
        return isEndedAnim;
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
    public int getSQX(float x){
        posXiiP = (x + getW() / 2 - sqBounds.getX()) / (sqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posXiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }
    public int getSQY(float y) {
        posYiiP = (y + getW() / 2 - sqBounds.getY()) / (sqBounds.getWidth() / (LevelGen.SQSIZE + 1));
        bigDecimal = new BigDecimal(posYiiP).setScale(0, BigDecimal.ROUND_FLOOR);
        return bigDecimal.intValue();
    }


}
