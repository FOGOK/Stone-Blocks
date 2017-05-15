package com.java4game.cuadro.core.usie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.ButtonActions;
import com.java4game.cuadro.core.uiwidgets.ManyStatsButton;
import com.java4game.cuadro.core.uiwidgets.SettingsButton;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Localization;
import com.java4game.cuadro.utils.Prefers;

public class Settings {

    private SettingsButton settingsButton;
    public static boolean OPENCLOSEACTION;

    private ManyStatsButton langB, musicB, soundB;
    private FloatAnimator floatAnimator;
    private boolean isOpen;
    private Sprite settingsCover;

    public Settings() {
        settingsButton = new SettingsButton(ButtonActions.All.SETTINGS_PAUSE_ACTION,
                Gm.WIDTH / 2f, 0f, 118);
        floatAnimator = new FloatAnimator(1f, 0f, 0.4f);
        floatAnimator.setNeedToUpdate(false);

        float sizeB = 1.5f;
        float otst = 0.3f;
        float widthCover = sizeB * 3f + otst * 4f;
        settingsCover = new Sprite(Assets.getNewSprite(115));
        settingsCover.setSize(widthCover, widthCover * 0.357f);
        settingsCover.setX((Gm.WIDTH - settingsCover.getWidth()) / 2f);

        langB = new ManyStatsButton(ButtonActions.All.CHANGE_LANGUAGE, (Gm.WIDTH - sizeB) / 2f, 0f, sizeB, 105);
        langB.initStats(105, 106);
        langB.setCurrentStat(Localization.getCurrentLang().ordinal());

        musicB = new ManyStatsButton(ButtonActions.All.CHANGE_MUSIC,
                (Gm.WIDTH - sizeB) / 2f - sizeB - otst, 0f, sizeB, 119);
        musicB.initStats(119, 120);
        musicB.setCurrentStat(Prefers.getBool(Prefers.MusicEnb, false) ? 1 : 0);

        soundB = new ManyStatsButton(ButtonActions.All.CHANGE_SOUND,
                (Gm.WIDTH - sizeB) / 2f + sizeB + otst, 0f, sizeB, 116);
        soundB.initStats(116, 117);
        soundB.setCurrentStat(Prefers.getBool(Prefers.SoundEnb, false) ? 1 : 0);

        OPENCLOSEACTION = false;
    }

    public void draw(SpriteBatch batch){
        if (OPENCLOSEACTION){
            OPENCLOSEACTION = false;
            floatAnimator.resetTime().revers();
            isOpen = !isOpen;
        }

        if (floatAnimator.isNeedToUpdate()){
            floatAnimator.update(Gdx.graphics.getDeltaTime());
            settingsButton.setPosition(settingsButton.getX(), settingsButton.getStartY() + floatAnimator.current * settingsCover.getHeight());
            settingsButton.setRotation(floatAnimator.current * 180f);
            settingsCover.setY(settingsButton.getY() - settingsCover.getHeight());
            langB.setPosition(langB.getX(), settingsCover.getY() + (settingsCover.getHeight() - langB.getHeight()) / 2f);
            musicB.setPosition(musicB.getX(), settingsCover.getY() + (settingsCover.getHeight() - langB.getHeight()) / 2f);
            soundB.setPosition(soundB.getX(), settingsCover.getY() + (settingsCover.getHeight() - langB.getHeight()) / 2f);
        }

        if (isOpen || floatAnimator.isNeedToUpdate()){
            settingsCover.draw(batch);
            langB.draw(batch);
            musicB.draw(batch);
            soundB.draw(batch);
        }

        settingsButton.setEnabled(!floatAnimator.isNeedToUpdate());
        settingsButton.draw(batch);
    }




}
