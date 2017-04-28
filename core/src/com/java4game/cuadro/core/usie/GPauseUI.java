package com.java4game.cuadro.core.usie;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GameUtils;

public class GPauseUI {

    private GameOverButton resumeB, menuB, restartB;
    private Sprite blackScreen, coverPause;
    private TextBlock textBlock;

    public GPauseUI(float ySquare) {
        final float sizeStar = 3f;
        final float sizeB = sizeStar / 2f;
        final float otst = sizeStar * 0.8f;
        final Color textColor = Color.valueOf("323232");

        blackScreen = new Sprite(GameUtils.generateWhitePic());
        blackScreen.setSize(Gm.WIDTH, Gm.HEIGHT);
        blackScreen.setColor(Color.BLACK);
        blackScreen.setAlpha(0.3f);

        resumeB = new GameOverButton(ButtonActions.All.CONTINUE_PAUSE_ACTION,
                (Gm.WIDTH - sizeB) / 2f - otst + sizeB / 2f,
                ySquare / 2f, sizeB, 88, "RESUME");
        resumeB.getTextBlock().setTextColor(textColor);


        menuB = new GameOverButton(ButtonActions.All.TOMAINMENU_PAUSE_ACTION,
                (Gm.WIDTH - sizeB) / 2f + sizeB / 2f,
                ySquare / 2f, sizeB, 57, "MENU");
        menuB.getTextBlock().setTextColor(textColor);

        restartB = new GameOverButton(ButtonActions.All.RESTART_PAUSE_ACTION,
                (Gm.WIDTH - sizeB) / 2f + otst + sizeB / 2f,
                ySquare / 2f, sizeB, 56, "RESTART");
        restartB.getTextBlock().setTextColor(textColor);

        resumeB.setPositionToCenter();
        menuB.setPositionToCenter();
        restartB.setPositionToCenter();

        textBlock = new TextBlock(Gm.WIDTH / 2f, Gm.HEIGHT / 2f, false, "PAUSE");
        textBlock.setCustomCff(0.8f);
        textBlock.setPositionToCenter();
        textBlock.setTextColor(textColor);

        coverPause = Assets.getNewSprite(30);
        float coverPauseWidth = textBlock.getBounds().width * 1.3f;
        coverPause.setSize(coverPauseWidth, coverPauseWidth * 0.656f);
        coverPause.setPosition((Gm.WIDTH - coverPause.getWidth()) / 2f, (Gm.HEIGHT - coverPause.getHeight()) / 2f);
    }

    public void setRestartAction(ButtonActions.All buttonAction){
        restartB.setAction(buttonAction);
    }

    public void draw(SpriteBatch batch){
        blackScreen.draw(batch);
        coverPause.draw(batch);
        coverPause.draw(batch);//не баг, так надо 2 раза, чтобы
        textBlock.draw(batch);
        resumeB.draw(batch);
        menuB.draw(batch);
        restartB.draw(batch);
    }

    public void dispose(){

    }

}
