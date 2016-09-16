package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.utils.FloatAnimator;

/**
 * Created by FOGOK on 16.09.2016 21:33.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class SquareObject  extends GameObject{


    /**
     * Тип объекта, короче это типо все объекты на поле (его можно уничтожить)
    * */


    boolean isCollect = false;
    boolean isDestroyed = false;
    boolean isEndedAnim = false;
    FloatAnimator floatAnimator;
    public SquareObject(Sprite sprite, int x, int y, Rectangle sqBounds) {
        super(sprite);
        //устанавливаем позицию на поле в клетках и размер объекта (он равен размеру клетки)
        setPosition(sqBounds.x + (x * (LevelSquare.sizOneSq + LevelSquare.otst * 2)), sqBounds.y + (y * (LevelSquare.sizOneSq + LevelSquare.otst * 2)));
        setSize(LevelSquare.sizOneSq);
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


}
