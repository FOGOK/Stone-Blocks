package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;

/**
 * Created by FOGOK on 30.10.2016 7:40.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class IconButton extends BaseButton {

    //timered Class NOT WORKING!

    private Sprite icon;
    private float sizeDiff;

    public IconButton(TextureGen textureGen, ButtonActions.All action, int img, float x, float y, float h) {
        super(textureGen, action, x, y, h, 0, 0);
        icon = textureGen.getSprite(img);
        icon.setSize(h * 0.6f, h * 0.6f);
        sizeDiff = (h - icon.getWidth()) / 2f;
        icon.setPosition(x + sizeDiff, y + sizeDiff);///NOTWORK         // FIXME: 03.11.2016 fixMEe)0)00 NOT WORKING CLASS!!!

    }

    @Override
    public void setOrigin(float x, float y) {
        super.setOrigin(x, y);
        icon.setOrigin(x - sizeDiff, y - sizeDiff);
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        icon.setAlpha(alpha);
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        icon.setScale(scaleXY);
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
        icon.setRotation(rotation);
    }

    @Override
    public void setPosition(float posX, float posY) {
        super.setPosition(posX, posY);
        icon.setPosition(bounds.x + bounds.width / 2f - icon.getWidth() / 2f, bounds.y + bounds.height / 2f - icon.getHeight() / 2f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        icon.draw(batch);
    }
}
