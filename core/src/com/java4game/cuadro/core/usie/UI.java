package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by FOGOK on 12.10.2016 16:46.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */

public class UI {

    private static final float CFFMAIN = 0.013f, CFFMAINDOTCONTENT = 0.5f;

    private static BitmapFont titleFont;
    private static BitmapFont contentFont;
    private static GlyphLayout glyphLayout;

    public static void initializate(){
        final float cffMain = CFFMAIN;
        final float cffContentDown = CFFMAINDOTCONTENT;

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

    public static float getSize(boolean isTitle, boolean wh, String text){
        BitmapFont textBF = isTitle ? titleFont : contentFont;
        glyphLayout.setText(textBF, text);
        return wh ? glyphLayout.width : glyphLayout.height;
    }

    public static void setCff(boolean isTitle, float cff){
        BitmapFont textBF = isTitle ? titleFont : contentFont;
        textBF.getData().setScale(cff);
    }

    public static void setDefaultCff(){
        titleFont.getData().setScale(CFFMAIN);
        contentFont.getData().setScale(CFFMAIN * CFFMAINDOTCONTENT);
    }



    public static void setColor(final Color color, final boolean isTitle){
        BitmapFont textBF = isTitle ? titleFont : contentFont;
        textBF.setColor(color);
    }

    public static void drawText(final SpriteBatch batch, final boolean isTitle, final String text, final float x, final float y){
        BitmapFont textBF = isTitle ? titleFont : contentFont;
        textBF.draw(batch, text, x, y);
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
