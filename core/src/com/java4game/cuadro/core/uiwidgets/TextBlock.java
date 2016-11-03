package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.usie.UI;

/**
 * Created by FOGOK on 03.11.2016 14:39.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TextBlock extends BaseObject {

    private Color textColor = Color.valueOf("2c2c36");
    private boolean isTitle;
    private String text;
    public TextBlock(float x, float y, final boolean isTitle, String text) {
        super(x, y, 0, 0);
        this.isTitle = isTitle;
        this.text = text;
        bounds.setWidth(UI.getSize(isTitle, true, text));
        bounds.setHeight(UI.getSize(isTitle, false, text));
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    @Override
    public void draw(final SpriteBatch batch) {
        UI.setColor(textColor, isTitle);
        UI.drawText(batch, isTitle, text, bounds.x, bounds.y + bounds.height);
    }
}
