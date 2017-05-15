package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.List;
import com.java4game.cuadro.objects.TrainingLine;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 23.04.2017 20:49.
 */

public class TrainingScreen {

    private Sprite topBar, botBar;
    private GameOverButton backB, openTraining;
    private List trainingList;

    public TrainingScreen() {
        botBar = Assets.getNewSprite(38);
        float heightCff = 0.3041f;
        botBar.setSize(Gm.WIDTH, Gm.WIDTH * heightCff);

        topBar = Assets.getNewSprite(Localization.getCurrentLang().equals(Localization.Lang.RUS) ?
                24 : 98);
        topBar.setSize(Gm.WIDTH, Gm.WIDTH * heightCff);
        topBar.setPosition(0f, Gm.HEIGHT - topBar.getHeight());

        float sizeB = 1.5f;
        float otst = sizeB * 1.3f;
        backB = new GameOverButton(ButtonActions.All.BACK_TO_MAIN_SCREEN,
                otst,
                topBar.getHeight() / 2f, sizeB, 88, Localization.getText(Localization.LettersKey.BACK));

        backB.setPositionToCenter();

        openTraining = new GameOverButton(ButtonActions.All.OPEN_LEARNING_INTERACTIVE,
                Gm.WIDTH - otst,
                topBar.getHeight() / 2f, sizeB, 12, Localization.getText(Localization.LettersKey.TRAINING));

        openTraining.setPositionToCenter();
        initList();
    }


    private void initList(){
        final float widthList = Gm.WIDTH * 0.76f;
        final float heightList = topBar.getY();
        final int rows = 15;

        trainingList = new List((Gm.WIDTH - widthList) / 2f, 0, widthList, heightList, 1, rows);
        trainingList.setPaddingBottom(0.5f + botBar.getHeight());
        trainingList.setButtons(false);
        TrainingLine[] trainingLines = new TrainingLine[rows];
        for (int i = 0; i < trainingLines.length; i++) {
            trainingLines[i] = new TrainingLine(1.2f, i);
            trainingList.set(trainingLines[i], 0, i);
        }
        trainingList.calculatePadding(0, 0);
    }

    public void draw(SpriteBatch batch){
        trainingList.draw(batch);
        botBar.draw(batch);
        topBar.draw(batch);
        backB.draw(batch);
        openTraining.draw(batch);
    }
}
