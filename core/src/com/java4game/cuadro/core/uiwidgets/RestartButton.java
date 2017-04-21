package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 12.01.2017 3:51.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class RestartButton extends BaseButton{

    private Sprite icon;
    private float xQ;
    public RestartButton(ButtonActions.All action, float x, float y, float h) {
        super(action, x, y, h, 30, 30);
        Sprite first = Assets.getNewSprite(30);
        Sprite second = Assets.getNewSprite(36);

        first.flip(true, false);
        second.flip(true, false);
        setFirstTexture(first);
        setSecondTexture(second);

        icon = Assets.getNewSprite(37);
        final float size = h * 0.42f;
        icon.setFlip(false, false);
        icon.setSize(size, size);
    }

    public void drawIcon(SpriteBatch batch){
        icon.setCenter(normalTex.getX() + normalTex.getWidth() * 0.688f, normalTex.getY() + normalTex.getHeight() * 0.486f);
        icon.draw(batch);
    }

    public void completeX(){
        xQ = getX();
    }

    public void setOffsetX(float x){
        setPosition(xQ + x, getY());
    }
}
