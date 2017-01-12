package com.java4game.cuadro.core.uiwidgets;

/**
 * Created by FOGOK on 12.01.2017 3:51.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Button extends BaseButton{

    private float xQ;
    public Button(ButtonActions.All action, float x, float y, float h, int back, int front) {
        super(action, x, y, h, back, front);
    }

    public void completeX(){
        xQ = getX();
    }

    public void setOffsetX(float x){
        setPosition(xQ + x, getY());
    }
}
