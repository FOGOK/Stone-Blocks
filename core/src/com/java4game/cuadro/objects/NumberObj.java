package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.utils.DebugValueChanger;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.GMUtils;

/**
 * Created by FOGOK on 16.09.2016 15:52.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class NumberObj extends SquareObject {

    //переменная с очками

    public static float pXii, pYii;    ///куда будут лететь очки
    float firstX, firstY;
    float distToPXY;
    int number, degrToPXY;
    public NumberObj(Sprite sprite, int x, int y, Rectangle sqBounds, int number) {
        super(sprite, x, y, sqBounds);
        this.number = number;
        firstX = getX();
        firstY = getY();
        distToPXY = GMUtils.getDist(firstX, firstY, pXii, pYii);
        degrToPXY = (int) GMUtils.getDeg(firstX, firstY, pXii, pYii);
        //инициализируем аниматор
        floatAnimator = new FloatAnimator(0f, 1f, 0.72f + 0.2f * (number / 10f), Interpolation.swingIn);
        ///
    }


    @Override
    protected void collectAnimation() {
        super.collectAnimation();

        setPosition(firstX + GMUtils.getNextX(distToPXY * floatAnimator.current, degrToPXY),
                firstY + GMUtils.getNextY(distToPXY * floatAnimator.current, degrToPXY));

        if (!floatAnimator.isNeedToUpdate()){
            LevelGen.SCORE += number;
            isEndedAnim = true;
        }
        else
            floatAnimator.update(Gdx.graphics.getDeltaTime());
    }
}
