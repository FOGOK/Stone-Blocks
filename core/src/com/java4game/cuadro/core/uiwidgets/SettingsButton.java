package com.java4game.cuadro.core.uiwidgets;


public class SettingsButton extends BaseButton {

    private final static float btnSize = 1.2f;
    private float startY;

    public SettingsButton(ButtonActions.All action, float x, float y, int back) {
        super(action, x, y + btnSize * 0.4f, btnSize, back, back);
        setPositionToCenter();
        startY = bounds.getY();
    }

    public float getStartY() {
        return startY;
    }
}
