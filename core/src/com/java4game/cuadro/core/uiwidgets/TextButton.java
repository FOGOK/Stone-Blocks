package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;

/**
 * Created by FOGOK on 03.11.2016 12:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TextButton extends BaseButton {

    private TextBlock textBlock;

    public TextButton(TextureGen textureGen, ButtonActions.All action, float x, float y, float h, int back, int front, String text) {
        super(textureGen, action, x, y, h, back, front);
        textBlock = new TextBlock(x + bounds.width / 2f, y + bounds.height / 2f, false, text);
        textBlock.setTextColor(Color.valueOf("2c2c36"));
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        textBlock.setPosition(x + bounds.width / 2f, y + bounds.height / 2f);
        textBlock.setPositionToCenter();
    }

    public TextBlock getTextBlock() {
        return textBlock;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        textBlock.draw(batch);
    }

    public void dispose() {

    }
}
