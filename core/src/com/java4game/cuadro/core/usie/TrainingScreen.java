package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 23.04.2017 20:49.
 */

public class TrainingScreen {

    private Sprite topBar, botBar;
    private GameOverButton backB, openTraining;

    public TrainingScreen() {
        botBar = Assets.getNewSprite(38);
        float heightCff = 0.3041f;
        botBar.setSize(Gm.WIDTH, Gm.WIDTH * heightCff);

        topBar = Assets.getNewSprite(98);
        topBar.setSize(Gm.WIDTH, Gm.WIDTH * heightCff);
        topBar.setPosition(0f, Gm.HEIGHT - topBar.getHeight());

        float sizeB = 1.5f;
        float otst = sizeB * 1.3f;
        backB = new GameOverButton(ButtonActions.All.BACK_TO_MAIN_SCREEN,
                otst,
                topBar.getHeight() / 2f, sizeB, 88, "BACK");

        backB.setPositionToCenter();

        openTraining = new GameOverButton(ButtonActions.All.BACK_TO_MAIN_SCREEN,
                Gm.WIDTH - otst,
                topBar.getHeight() / 2f, sizeB, 12, "TRAINING");

        openTraining.setPositionToCenter();

    }

    public void draw(SpriteBatch batch){
        botBar.draw(batch);
        topBar.draw(batch);
        backB.draw(batch);
        openTraining.draw(batch);
    }
}
