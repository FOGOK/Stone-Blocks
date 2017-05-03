package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.GameOverButton;
import com.java4game.cuadro.core.uiwidgets.ManyStatsButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Localization;

public class RecordsScreen {

    private GameOverButton leaderBoards, achievements;
    private ManyStatsButton connectOnStart;
    private TextBlock connectOnStartText;

    public RecordsScreen(float startY, float height) {
        float sizeB = 2f;
        float sizeSecondB = sizeB * 0.4f;
        leaderBoards = new GameOverButton(ButtonActions.All.OPEN_LIDERBOARD,
                Gm.WIDTH / 2f, startY + height * 0.6f, sizeB, 96, Localization.getText(Localization.LettersKey.LEADERBOARDS));
        leaderBoards.setPositionToCenter();

        achievements = new GameOverButton(ButtonActions.All.OPEN_ACHIEVEMENTS,
                Gm.WIDTH / 2f, startY + height * 0.3f, sizeB, 93, Localization.getText(Localization.LettersKey.ACHIEVEMENTS));
        achievements.setPositionToCenter();



        float otst = 0.15f;

        connectOnStartText = new TextBlock(Gm.WIDTH / 2f, startY + otst * 2f, false, Localization.getText(Localization.LettersKey.CONNECTONSTART));
        connectOnStartText.setCustomCff(sizeSecondB * 0.6f);

        connectOnStart = new ManyStatsButton(ButtonActions.All.CHECKBOXCLICK_AUTORIZETOSTART,
                Gm.WIDTH / 2f, startY + otst * 2f, sizeSecondB, 94);
        connectOnStart.initStats(94, 95);
        connectOnStart.setCurrentStat(0);

        float sizeWTwoObjects = connectOnStartText.getBounds().getWidth() + otst + connectOnStart.getWidth();

        connectOnStart.setPositionToCenter(sizeWTwoObjects, connectOnStart.getHeight(), false);

        connectOnStartText.setPositionToCenter(sizeWTwoObjects,
                connectOnStartText.getBounds().getHeight(), true);

    }

    public void draw(SpriteBatch batch){
        leaderBoards.draw(batch);
        achievements.draw(batch);
        connectOnStart.draw(batch);
        connectOnStartText.draw(batch);
    }
}
