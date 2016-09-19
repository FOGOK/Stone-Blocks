package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.utils.FloatAnimator;

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
    int kindedHash; //хэш цветного, который будет вести наш кубик, если равен -1, значит им является кубик
    int whoI = 0;
    int hash;
    FloatAnimator floatAnimator;
    public SquareObject(Sprite sprite, int x, int y, Rectangle sqBounds, int hash) {
        super(sprite);
        this.hash = hash;
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


}
