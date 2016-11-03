package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.usie.UI;

/**
 * Created by FOGOK on 03.11.2016 12:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TextButton extends BaseButton {

    private float textW, textH;
    private String text;
    private Color textColor = Color.valueOf("2c2c36");

    public TextButton(TextureGen textureGen, ButtonActions.All action, float x, float y, float h, int back, int front, String text) {
        super(textureGen, action, x, y, h, back, front);
        textW = UI.getSize(false, true, text);
        textH = UI.getSize(false, false, text);
        this.text = text;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        UI.setColor(textColor, false);
        UI.drawText(batch, false, text, bounds.x + (bounds.width - textW) / 2f, bounds.y + (bounds.height + textH) / 2f);
    }

    public void dispose() {

    }
}
