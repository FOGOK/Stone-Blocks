package com.java4game.cuadro.core.guiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.usie.UI;

/**
 * Created by FOGOK on 16.06.2017 16:20.
 */

public class TextLabel extends BaseWidget implements Drawable{

    static Color defaultColor = Color.valueOf("323232");

    float alpha = 1f;
    GlyphLayout glyphLayout;
    Color color = defaultColor;
    String text;
    float sizeText;
    boolean dirty = true;

    public TextLabel(float x, float y, float sizeText) {
        this(x, y, sizeText, "");
    }

    public TextLabel(float x, float y, float sizeText, String text) {
        super(new Rectangle(x, y, 0f, 0f ), Align.LeftBottom);  //задаем нули на ширину и высоту, т.к. они сами там расчитываются
        glyphLayout = new GlyphLayout();
        setSizeText(sizeText);
        setText(text);
    }

    public void setSizeText(float sizeText){
        this.sizeText = sizeText;
        dirty = true;
    }

    public float getSizeText() {
        return sizeText;
    }

    public void setText(String text) {
        this.text = text;
        dirty = true;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        dirty = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (dirty)
            refreshTextParams();

        UI.setAlpha(alpha, false);
        UI.getContentFont().draw(batch, glyphLayout, bounds.x, bounds.y + bounds.height);
    }

    void refreshTextParams(){
        UI.setCff(false, sizeText);
        glyphLayout.setText(UI.getContentFont(), text, color, 0f, com.badlogic.gdx.utils.Align.center, true);
        setSize(glyphLayout.width, glyphLayout.height);
        dirty = false;
    }

}
