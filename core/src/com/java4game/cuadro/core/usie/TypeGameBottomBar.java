package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.BaseObject;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;

/**
 * Created by FOGOK on 14.01.2017 22:35.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class TypeGameBottomBar extends BaseObject {

    public static int SELECTED_BTN;
    private int lastSelectedBtn;
    private TypeGameButton tgButtons[];

    private MenuUI menuUI;

    public TypeGameBottomBar(MenuUI menuUI, float h) {
        super(0, 0, Gm.WIDTH, h);
        this.menuUI = menuUI;
        final int btnsCount = 5;
        SELECTED_BTN = 0;
        tgButtons = new TypeGameButton[btnsCount];
        final float cellW = Gm.WIDTH / btnsCount;
        for (int i = 0; i < tgButtons.length; i++) {
            tgButtons[i] = new TypeGameButton(ButtonActions.All.CHANGE_GAME_MODE,
                    cellW * i + cellW / 2f, h / 2f, h / 2f, 41 + i, i);
            tgButtons[i].setPositionToCenter();
        }
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < tgButtons.length; i++) {
            tgButtons[i].setSelected(i == SELECTED_BTN);
            tgButtons[i].draw(batch);
        }

        if (lastSelectedBtn != SELECTED_BTN){
            lastSelectedBtn = SELECTED_BTN;
            menuUI.refreshTopBar(SELECTED_BTN);
        }
    }
}
