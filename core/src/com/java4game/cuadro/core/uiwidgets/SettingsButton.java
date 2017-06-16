package com.java4game.cuadro.core.uiwidgets;


public class SettingsButton extends BaseButton {

    private float startY;

    public SettingsButton(ButtonActions.All action, float x, float y, float size, int back) {
        super(action, x, y + size * 0.4f, size, back, back);
        setPositionToCenter();
        setStartY();
    }



    public void setStartY() {
        startY = bounds.getY();
    }

    public float getStartY() {
        return startY;
    }
}
