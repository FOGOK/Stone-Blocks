package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.java4game.cuadro.objects.StarBlock;

import java.util.Map;

/**
 * Created by FOGOK on 25.10.2016 7:58.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Prefers {

    private static Preferences prefs;

    /////// DO NOT CHANGE FINAL STRINGS
    public static final String KeyOpenedStages = "KeyOpenedStages";
    public static final String KeyStars = "KeyStars";
    /////// DO NOT CHANGE FINAL STRINGS

    public static void initPrefs(){
        prefs = Gdx.app.getPreferences("My Preferences");
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
