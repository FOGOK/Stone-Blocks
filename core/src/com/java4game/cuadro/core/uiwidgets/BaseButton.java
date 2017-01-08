package com.java4game.cuadro.core.uiwidgets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.utils.Assets;

abstract class BaseButton extends BaseObject{

    /**
     * Класс чистой кнопки с двумя текстурами - нажатой и ненажатой, а так же при нажатии выполняется действияе
     * */

    boolean isTouched;
    ButtonActions.All action;

    //must
    Sprite normalTex;       ///ненажатая кнопка
    Sprite touchedTex;      //нажатая кнопка
    ///


    //proporties
    boolean isEnabled;
    ///

    public BaseButton(final ButtonActions.All action, float x, float y, float h, int back, int front){
        super(x, y, h, h);
        this.action = action;
        Sprite firstTexture = Assets.getNewSprite(back);
        final float wCff = firstTexture.getWidth() /firstTexture.getHeight();
        final float w = h * wCff;
        bounds.setWidth(w);

        setFirstTexture(firstTexture);
        setSecondTexture(Assets.getNewSprite(front));
        isEnabled = true;
    }

    public BaseButton(final TextureGen textureGen, final ButtonActions.All action, float x, float y, float h, int back, int front){
        super(x, y, h, h);
        this.action = action;
        Sprite firstTexture = new Sprite(textureGen.getSprite(back));
        final float wCff = firstTexture.getWidth() /firstTexture.getHeight();
        final float w = h * wCff;
        bounds.setWidth(w);

        setFirstTexture(firstTexture);
        setSecondTexture(new Sprite(textureGen.getSprite(front)));
        isEnabled = true;
    }

    public void setAction(ButtonActions.All action) {
        this.action = action;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    protected void setFirstTexture(Sprite sprite){
        normalTex = sprite;
        normalTex.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    protected void setSecondTexture(Sprite sprite){
        touchedTex = sprite;
        touchedTex.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    protected void activateAction(){
        ButtonActions.activateAction(action);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        normalTex.setPosition(x, y);
        touchedTex.setPosition(x, y);
    }

    public void setAlpha(float alpha){
        normalTex.setAlpha(alpha);
        touchedTex.setAlpha(alpha);
    }

    public void setScale(float scaleXY){
        normalTex.setScale(scaleXY);
        touchedTex.setScale(scaleXY);
    }

    public void setOrigin(float x, float y){
        normalTex.setOrigin(x, y);
        touchedTex.setOrigin(x, y);
    }

    public void setRotation(float rotation){
        normalTex.setRotation(rotation);
        touchedTex.setRotation(rotation);
    }

    public float getX(){
        return bounds.getX();
    }

    public float getY(){
        return bounds.getY();
    }

    public float getWidth(){
        return bounds.getWidth();
    }

    public float getHeight(){
        return bounds.getHeight();
    }


    public void draw(SpriteBatch batch){
        calcM();
        drawButtonTexture(batch);
    }

    protected void drawButtonTexture(SpriteBatch batch){      //отрисовываем тесктуру кнопки, в зависимости от того, нажата ли она или нет
        Sprite drawingTexture = isTouched ? touchedTex : normalTex;
        drawingTexture.draw(batch);
    }

//    private void drawButtonText(SpriteBatch batch){
//        UI.getTitleFont().setColor(Color.DARK_GRAY);
//        UI.drawText(batch, true, text, bounds.x + bounds.width / 2f, bounds.y + bounds.height / 2f);
//    }

    protected void calcM(){
        if (isEnabled){
            if (Gdx.input.isTouched()){ //если на экран нажимает палец
                isTouched = bounds.contains(
                        (Gm.WIDTH / Gdx.graphics.getWidth()) * Gdx.input.getX(),
                        (Gm.HEIGHT / Gdx.graphics.getHeight()) * (Gdx.graphics.getHeight() - Gdx.input.getY())
                );  ///определяем,  касается ли палец кнопки или нет

            }else {              //при отпускании кнопки
                if (isTouched)      ///если при отпускании кнопки палец находился на кнопке, то выполняем действие
                   activateAction();

                isTouched = false;  //делаем так, чтобы действие не выполнилось ещё раз
            }
        }else
            isTouched = false;
    }


    public void dispose(){

    }
}
