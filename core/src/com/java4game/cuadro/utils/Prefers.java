package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by FOGOK on 25.10.2016 7:58.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Prefers {

    private static Preferences prefs;

    /////// DO NOT CHANGE FINAL STRINGS
    public static final String KeyOpenedStagesSteps = "KeyOpenedStages";
    public static final String KeyOpenedStagesTimed = "KeyOpenedStagesTimed";
    public static final String KeyStarsSteps = "KeyStars";
    public static final String KeyStarsTimed = "KeyStarsTimed";
    public static final String KeyRecordBronze = "KeyRecordBronze";
    public static final String KeyRecordSilver = "KeyRecordSilver";
    public static final String KeyRecordGold = "KeyRecordGold";
    public static final String KeyOpenedArkadeModes = "KeyOpenedArkadeModes";
    public static final String KeyRandomMode1 = "KeyRandomMode1";
    public static final String KeyRandomMode2 = "KeyRandomMode2";
    public static final String KeyRandomMode3 = "KeyRandomMode3";
    public static final String KeyFirstOpenLearn = "KeyFirstOpenLearn";
    public static final String CountBlacks = "CountBlacks";
    public static final String RusEng = "RusEng";
    public static final String MusicEnb = "MusicEnb";
    public static final String SoundEnb = "SoundEnb";
    public static final String GPGSStarted = "GPGSStarted";
    /////// DO NOT CHANGE FINAL STRINGS

    public static void initPrefs(){
        prefs = Gdx.app.getPreferences("com.java4game.blockmover");
    }


    public static void putInt(String key, int whoEdit){
        prefs.putInteger(key, whoEdit);
        prefs.flush();
    }


    public static void putFloat(String key, float whoEdit){
        prefs.putFloat(key, whoEdit);
        prefs.flush();
    }

    public static float getFloat(String key){
        return prefs.getFloat(key, 1f);
    }

    public static int getInt(String key){
        return prefs.getInteger(key, 0);
    }

    public static void putBool(String key, boolean whoEdit){
        prefs.putBoolean(key, whoEdit);
        prefs.flush();
    }

    public static void putString(String key, String val){
        prefs.putString(key, val);
        prefs.flush();
    }

    public static String getString(String key){
        return prefs.getString(key, " ");
    }

    public static boolean getBool(String key, boolean defVal){
        return prefs.getBoolean(key, defVal);
    }
}
