package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Align;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.RobotHead;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;

public class InformationScreen {

    private GameOverButton backB, openSite;
    private RobotHead robotHead;
    private Sprite cover;
    private FloatAnimator floatAnimator;

    private float sizeText = 0.47f;
    private static final String text1 = "BLOCK MOVER";
    private static final String text2 = "VERSION 1.0.2\n\n" +
                                        "IDEA, ART: ANDREY SVETASHOV\n" +
                                        "PROGRAMMING: OLEG KARLOVSKIY\n" +
                                        "MORE GAMES HERE:";
    private GlyphLayout gameName = new GlyphLayout(), contentText = new GlyphLayout();


    public InformationScreen() {
        float sizeB = 1.5f;
        backB = new GameOverButton(ButtonActions.All.BACK_TO_MAIN_SCREEN,
                Gm.WIDTH / 2f,
                3f, sizeB, 88, "BACK");

        backB.setPositionToCenter();

        cover = Assets.getNewSprite(91);
        float sizeCoverW = Gm.WIDTH * 0.95f;
        cover.setSize(sizeCoverW, sizeCoverW * 0.675f);
        cover.setPosition((Gm.WIDTH - cover.getWidth()) / 2f, (Gm.HEIGHT - cover.getHeight()) / 2f);

        initTextBlocks();

        openSite = new GameOverButton(ButtonActions.All.OPEN_SITE,
                Gm.WIDTH / 2f,
                cover.getY() + (cover.getHeight() - (otst * 1.5f + gameName.height + contentText.height)) / 2f, sizeB, 92, "");

        openSite.setPositionToCenter();

        robotHead = new RobotHead(cover.getY() + cover.getHeight());
        robotHead.setText("Hello!!! My name is mover", 0.7f);

        floatAnimator = new FloatAnimator(1f, 1.2f, 0.3f, Interpolation.pow3);
    }

    public void reset(){
        robotHead.show();
    }

    private void initTextBlocks(){
        UI.setCff(false, sizeText * 2f);
        gameName.setText(UI.getContentFont(), text1, Color.valueOf("323232"), 0, Align.center, false);
        UI.setCff(false, sizeText);
        contentText.setText(UI.getContentFont(), text2, Color.valueOf("323232"), 0, Align.center, false);
    }

    public void draw(SpriteBatch batch){
        backB.draw(batch);
        robotHead.draw(batch);
        cover.draw(batch);
        drawText(batch);

        floatAnimator.updateLoop(Gdx.graphics.getDeltaTime());
        openSite.setScale(floatAnimator.current);
        openSite.draw(batch);
    }

    float otst = 0.6f;
    private void drawText(SpriteBatch batch){
        UI.setCff(false, sizeText * 2f);
        UI.getContentFont().draw(batch, gameName, Gm.WIDTH / 2f, cover.getY() + cover.getHeight() - otst);
        UI.setCff(false, sizeText);
        UI.getContentFont().draw(batch, contentText, Gm.WIDTH / 2f, cover.getY() + cover.getHeight() - gameName.height - otst * 1.5f);
    }

}
