package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.java4game.cuadro.utils.Timer;

/**
 * Created by FOGOK on 08.01.2017 5:40.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class MusicCore {

    private static float MUSIC_VOLUME = 0.0f;   //0.3
    private static float SOUND_VOLUME = 1.0f;

    private static Timer stopTimer, startTimer;
    private static Music game, menu;
    private static Sound sounds[];
    private static int currentPlay;
    public static final int GAME = 0, MENU = 1;

    public static void init(){
        game = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
        game.setLooping(true);
        menu = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
        menu.setLooping(true);

        //initSounds
        sounds = new Sound[15];
        String endLett;
        for (int i = 0; i < sounds.length; i++) {
            endLett = i > 13 ? ".wav" : ".mp3";
            sounds[i] = Gdx.audio.newSound(Gdx.files.internal("sounds/" + i + endLett));
        }
        ///

        stopTimer = new Timer(0);
        startTimer = new Timer(1.5f);
        currentPlay = MENU;
    }

    public static void playSound(int i){
        playSound(i, 1f);
    }

    public static void playSound(int i, float volumeModulation){
        sounds[i].play(SOUND_VOLUME * volumeModulation);
    }

    public static void play(int _currentPlay){
        currentPlay = _currentPlay;
        stopTimer.reset(1.5f);
        startTimer.reset(1.5f);
    }

    public static void handleVolumeAndTransition(){
        if (currentPlay == GAME){
            if (stopTimer.next()){
                menu.stop();
                game.play();
                startTimer.next();
                game.setVolume(startTimer.getProgress() * MUSIC_VOLUME);
            }else
                menu.setVolume((1f - stopTimer.getProgress()) * MUSIC_VOLUME);
        } else if (currentPlay == MENU){
            if (stopTimer.next()){
                game.stop();
                menu.play();
                startTimer.next();
                menu.setVolume(startTimer.getProgress() * MUSIC_VOLUME);
            }else
                game.setVolume((1f - stopTimer.getProgress()) * MUSIC_VOLUME);
        }
    }

    public static void dispose(){
        game.dispose();
        game = null;

        menu.dispose();
        menu = null;

        for (int i = 0; i < sounds.length; i++) {
            sounds[i].dispose();
            sounds[i] = null;
        }
        sounds = null;
    }
}
