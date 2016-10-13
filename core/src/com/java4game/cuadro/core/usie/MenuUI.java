package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.uiwidgets.Button;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 13.10.2016 0:55.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class MenuUI {

    final static int GAME_NAME = 0;
    final static int START_BUTTON = 1;

    public static boolean RESETANIMATION;


    private Button startButton;
    private Sprite background, upBarMenu, gameNameTex;

    FloatAnimator[] objectAnimations;
    float[] posYs;

    public MenuUI(TextureGen textureGen) {
        setBackground();

        RESETANIMATION = false;
        final int objCount = 2;     ///2 объекта, название игры и кнопка
        objectAnimations = new FloatAnimator[objCount];
        posYs = new float[objCount];
        for (int i = 0; i < objCount; i++) {
            objectAnimations[i] = new FloatAnimator(0f, 1f, 1f + 0.3f * i, Interpolation.bounceOut);
        }

        setUpBarMenu(textureGen);

        setGameNameTex(textureGen);
        setStartButton(textureGen);
    }

    private void setGameNameTex(TextureGen textureGen){
        gameNameTex = new Sprite(textureGen.getSprite(Atalas.gameNameT));
        final float cffWidth = 0.68f;
        final float hDivW = 0.5741f;
        final float otstTop = 7.2f;
        gameNameTex.setSize(Gm.WIDTH * cffWidth, Gm.WIDTH * cffWidth * hDivW);
        gameNameTex.setPosition((Gm.WIDTH - Gm.WIDTH * cffWidth) / 2f, Gm.HEIGHT - otstTop);
        posYs[0] = otstTop;

    }
    private void setUpBarMenu(TextureGen textureGen){
        upBarMenu = new Sprite(textureGen.getSprite(Atalas.upBarMenu));
        final float hDivW = 0.2481f;
        upBarMenu.setSize(Gm.WIDTH, Gm.WIDTH * hDivW);
        upBarMenu.setPosition(0f, Gm.HEIGHT - upBarMenu.getHeight());
    }
    private void setStartButton(TextureGen textureGen){
        final float otstTop = 11f;
        startButton = new Button(textureGen, Atalas.startB, Atalas.startBAct, Gm.WIDTH / 2f, Gm.HEIGHT - otstTop, ButtonActions.START_MENU_ACTION);;
        startButton.setText(Localization.ENG.STARTGAMETEXT);
        posYs[1] = otstTop;
    }
    private void setBackground(){
        background = new Sprite(new Texture(Gdx.files.internal("bgMenu.png")));
        background.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final float wDivH = 0.5625f;
        background.setSize(Gm.HEIGHT * wDivH, Gm.HEIGHT);
        background.setPosition((Gm.WIDTH - background.getWidth()) / 2f, 0f);
    }
    public void draw(SpriteBatch batch){

        calcAnim();

        background.draw(batch);

        gameNameTex.draw(batch);    // 1 объект
        startButton.draw(batch);        // 2 объект


        upBarMenu.draw(batch);
    }

    private void calcAnim(){
        boolean resetAnim = false;
        if (RESETANIMATION){
            resetAnim = true;
            RESETANIMATION = false;
        }
        for (int i = 0; i < objectAnimations.length; i++) {
            FloatAnimator objAnim = objectAnimations[i];

            if (resetAnim)
                objAnim.resetTime();

            objAnim.update(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            float whoY = posYs[i] * objAnim.current;

            switch (i){
                case GAME_NAME:
                    gameNameTex.setY(Gm.HEIGHT - whoY);
                    break;
                case START_BUTTON:
                    startButton.setPosition(startButton.getX(), Gm.HEIGHT - whoY);
                    break;
            }
        }


    }

    public void dispose() {
        background.getTexture().dispose();
    }
}
