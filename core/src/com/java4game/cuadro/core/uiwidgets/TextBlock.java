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

    private static Color defaultColor = Color.valueOf("323232");
    private Color textColor = defaultColor;
    private boolean isTitle;
    private float customCff;
    private float alpha;
    private float offsetX, offsetY;
    private float scale = 1f;
    private String text;
    public TextBlock(float x, float y, final boolean isTitle, String text) {
        super(x, y, 0, 0);
        this.isTitle = isTitle;
        customCff = -1f;
        alpha = 1f;
        offsetX = 0f;
        offsetY = 0f;
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public void setText(String text) {
        this.text = text;
        refreshBounds();
    }

    public void setAlpha(float alpha){
        this.alpha = alpha;
    }

    public void setCustomCff(float customCff) {
        this.customCff = customCff;
        refreshBounds();
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setDefaultCff(){
        this.customCff = -1f;
        refreshBounds();
    }

    private void refreshBounds(){
        if (customCff != -1f) UI.setCff(isTitle, customCff); else UI.setDefaultCff();
        bounds.setWidth(UI.getSize(isTitle, true, text));
        bounds.setHeight(UI.getSize(isTitle, false, text));
        if (customCff != -1f) UI.setDefaultCff();
    }


    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setDefaultColor() {
        this.textColor = defaultColor;
    }



    @Override
    public void draw(final SpriteBatch batch) {
        if (customCff != -1f || scale != -1f) UI.setCff(isTitle, customCff * scale);


        UI.setColor(textColor, isTitle);
        if (alpha != 1f){
            UI.setAlpha(alpha, isTitle);
        }

        UI.drawText(batch, isTitle, text, bounds.x + offsetX - (bounds.width * scale - bounds.width) / 2f, bounds.y + offsetY +  bounds.height + (bounds.height * scale - bounds.height) / 2f);

        if (alpha != 1f){
            UI.setColor(textColor, isTitle);
        }
        if (customCff != -1f || scale != -1f) UI.setDefaultCff();
    }
}
