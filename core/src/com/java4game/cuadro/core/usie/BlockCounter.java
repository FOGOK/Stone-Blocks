package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.SettingsButton;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;

public class BlockCounter {

    private SettingsButton settingsButton;
    public static boolean OPENCLOSEACTION;

    private FloatAnimator floatAnimator;
    private boolean isOpen;
    private Sprite settingsCover, blockIcon;
    private TextBlock textBlock;

    private float yPauseBtn;


    public BlockCounter() {

        settingsButton = new SettingsButton(ButtonActions.All.BLOCKCOUNTER_ACTION,
                0f, 0f, 1f, 118);
        settingsButton.setPosition(Gm.WIDTH / 2f, Gm.HEIGHT - settingsButton.getHeight() / 2f);
        settingsButton.setPositionToCenter();
        settingsButton.setStartY();

        settingsButton.setRotation(180f);

        floatAnimator = new FloatAnimator(1f, 0f, 0.4f);
        floatAnimator.setNeedToUpdate(false);

        float sizeB = 1.5f;
        float otst = 0.3f;
        float widthCover = sizeB * 3f + otst * 4f;
        settingsCover = new Sprite(Assets.getNewSprite(115));
        settingsCover.setSize(widthCover, widthCover * 0.357f);
        settingsCover.setX((Gm.WIDTH - settingsCover.getWidth()) / 2f);

        float sizeIcon = 0.8f;

        textBlock = new TextBlock(0f, 0f, false, "");
        textBlock.setCustomCff(sizeIcon * 0.8f);

        blockIcon = Assets.getNewSprite(12);
        blockIcon.setSize(sizeIcon, sizeIcon);



        OPENCLOSEACTION = false;

    }

    public void draw(SpriteBatch batch, boolean isGame){

        if (isGame){
            drawBlockCount(batch, true);
        }else{
            if (OPENCLOSEACTION){
                OPENCLOSEACTION = false;
                floatAnimator.resetTime().revers();
                isOpen = !isOpen;
            }

            if (floatAnimator.isNeedToUpdate()){
                floatAnimator.update(Gdx.graphics.getDeltaTime());
                settingsButton.setPosition(settingsButton.getX(), settingsButton.getStartY() - floatAnimator.current * settingsCover.getHeight());
                settingsButton.setRotation(floatAnimator.current * 180f + 180f);
                settingsCover.setY(settingsButton.getY() + settingsButton.getHeight());
            }

            if (isOpen || floatAnimator.isNeedToUpdate()){
                settingsCover.draw(batch);
                drawBlockCount(batch, false);
            }

            settingsButton.setEnabled(!floatAnimator.isNeedToUpdate());
            settingsButton.draw(batch);
        }

    }

    public void setCountBlocks(int count){
        textBlock.setText(": " + count);
        lastIsGame = false;
        refreshTextBlock(false);
    }


    private boolean lastIsGame;
    private void drawBlockCount(SpriteBatch batch, boolean isGame){
//        if (isGame != lastIsGame){
//            lastIsGame = isGame;
//            refreshTextBlock(isGame);
//        }
//        blockIcon.draw(batch);
//        textBlock.draw(batch);
    }

    private final float otst = 0.4f;
    private void refreshTextBlock(boolean isGame){
        if (isGame) {
            blockIcon.setPosition(0f, yPauseBtn - otst * 0.7f - blockIcon.getHeight());
        } else {
            blockIcon.setPosition((Gm.WIDTH - (blockIcon.getWidth() + textBlock.getBounds().getWidth())) / 2f,
                    Gm.HEIGHT - otst - blockIcon.getHeight());
        }
        textBlock.setPosition(blockIcon.getX() + blockIcon.getWidth(),
                blockIcon.getY() + (blockIcon.getHeight() - textBlock.getBounds().getHeight()) / 2f);
    }

    public void setyPauseBtn(float yPauseBtn) {
        this.yPauseBtn = yPauseBtn;
    }
}
