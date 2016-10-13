package com.java4game.cuadro.core.uiwidgets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.usie.UI;
import com.java4game.cuadro.utils.Localization;
import com.sun.jndi.toolkit.ctx.StringHeadTail;

public class Button {

    static final float WIDHT = 5.5f;
    static final float HEIGHT = WIDHT * 0.26f;

    boolean isTouched;

    String text = "";

    int action;

    //must
    Sprite normalTex;       ///ненажатая кнопка
    Sprite touchedTex;      //нажатая кнопка

    Rectangle bounds;
    ///

    //optional
    ///


    //links
    //TextureGen textureGen;
    ///

    public Button(TextureGen textureGen, String normalTex, String touchedTex, float posX, float posY, int action){

        this.action = action;

        setFirstTexture(new Sprite(textureGen.getSprite(normalTex)), posX, posY);
        setSecondTexture(new Sprite(textureGen.getSprite(touchedTex)), posX, posY);

        bounds = new Rectangle(posX - WIDHT / 2f, posY - HEIGHT / 2f, WIDHT, HEIGHT);
    }

    private void setFirstTexture(Sprite sprite, float posX, float posY){
        normalTex = sprite;
        normalTex.setSize(WIDHT, HEIGHT);
        normalTex.setPosition(posX - WIDHT / 2f, posY - HEIGHT / 2f);
    }
    private void setSecondTexture(Sprite sprite, float posX, float posY){
        touchedTex = sprite;
        touchedTex.setSize(WIDHT, HEIGHT);
        touchedTex.setPosition(posX - WIDHT / 2f, posY - HEIGHT / 2f);
    }

    public void setPosition(float posX, float posY){
        normalTex.setPosition(posX, posY);
        touchedTex.setPosition(posX, posY);
        bounds.setPosition(posX, posY);
    }

    public float getX(){
        return bounds.getX();
    }

    public float getY(){
        return bounds.getY();
    }

    public void setText(String text){
        this.text = text;
    }


    public void draw(SpriteBatch batch){
        calcM();
        drawButtonTexture(batch);
        drawButtonText(batch);
    }

    private void drawButtonTexture(SpriteBatch batch){
        Sprite drawingTexture = isTouched ? touchedTex : normalTex;
        drawingTexture.draw(batch);
    }

    private void drawButtonText(SpriteBatch batch){
        UI.getTitleFont().setColor(Color.DARK_GRAY);
        UI.drawText(batch, true, text, bounds.x + bounds.width / 2f, bounds.y + bounds.height / 2f);
    }




    private void calcM(){
        if (Gdx.input.isTouched()){
            if (bounds.contains(
                    (Gm.WIDTH / Gdx.graphics.getWidth()) * Gdx.input.getX(),
                    (Gm.HEIGHT / Gdx.graphics.getHeight()) * (Gdx.graphics.getHeight() - Gdx.input.getY())
            )){
                isTouched = true;
            }
        }else{
            if (isTouched)
                ButtonActions.activateAction(action);

            isTouched = false;
        }
    }


    public void dispose(){

    }



}
