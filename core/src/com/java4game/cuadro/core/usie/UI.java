package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.utils.GameUtils;
import com.java4game.cuadro.utils.Pos;

/**
 * Created by FOGOK on 12.10.2016 16:46.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */

public class UI {

    public class UIText{
        private String text;
        private Pos position;

        public UIText(boolean isTitle){
            this.isTitle = isTitle;
        }

        private boolean isTitle;

        public GlyphLayout getSize(){
            glyphLayout.setText((isTitle) ? titleFont : contentFont, text);
            return glyphLayout;
        }

        public String getText(){
            return text;
        }

        public Pos getPosition(){
            return position;
        }

        public void setText(String text){
            this.text = text;
        }

        public void setPosition(Pos position){
            this.position = position;
        }


    }

    private static BitmapFont titleFont;
    private static BitmapFont contentFont;
    private static GlyphLayout glyphLayout;

    public static void initializate(){
        final float cffMain = 0.02f;
        final float cffContentDown = 0.7f;

        final String pathToFont = "font/font.fnt";
        titleFont = new BitmapFont(Gdx.files.internal(pathToFont));
        titleFont.setUseIntegerPositions(false);
        titleFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        titleFont.getData().setScale(cffMain);

        contentFont = new BitmapFont(Gdx.files.internal(pathToFont));
        contentFont.setUseIntegerPositions(false);
        contentFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        contentFont.getData().setScale(cffMain * cffContentDown);


        glyphLayout = new GlyphLayout();
    }


    public static void drawText(SpriteBatch batch, boolean isTitle, String text, float x, float y){
        BitmapFont textBF = isTitle ? titleFont : contentFont;
        glyphLayout.setText(textBF, text);
        textBF.draw(batch, glyphLayout, x - glyphLayout.width / 2f, y + glyphLayout.height / 2f);
    }


    public static BitmapFont getTitleFont(){
        return titleFont;
    }
    public static BitmapFont getContentFont(){
        return contentFont;
    }

    public static void dispose(){
        titleFont.dispose();
        titleFont = null;
        contentFont.dispose();
        contentFont = null;
    }

}
