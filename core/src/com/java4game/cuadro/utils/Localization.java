package com.java4game.cuadro.utils;

/**
 * Created by FOGOK on 06.10.2016 14:38.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Localization {

    public enum Lang {
        RUS, ENG
    }
    private static Lang lang = Lang.ENG;

    public enum LettersKey{
        SCORE, TIME, STAGE, WORLD, STARTGAMETEXT, CONTINUEPAUSETEXT, RESTARTPAUSETEXT, SETTINGSPAUSETEXT, EXITMENUPAUSETEXT, SELECTSTAGETEXT

    }

    //RUS
    private static String[] rusLetters = new String[]{
            "ОЧКИ", "ВРЕМЯ", "УРОВЕНЬ", "МИР", "НАЧАТЬ ИГРУ", "ПРОДОЛЖИТЬ", "НАЧАТЬ ЗАНОВО", "НАСТРОЙКИ", "ГЛАВНОЕ МЕНЮ", "ВЫБЕРЕТЕ УРОВЕНЬ"
    };
    ///

    //ENG
    private static String[] engLetters = new String[]{
            "SCORE", "TIME", "STAGE", "WORLD", "START", "CONTINUE", "RESTART", "SETTINGS", "MENU", "SELECT STAGE"
    };
    ///

    public static Lang getCurrentLang(){
        return lang;
    }


    public static String getText(LettersKey key){
        switch (lang){
            case RUS:
                return rusLetters[key.ordinal()];
            case ENG:
                return engLetters[key.ordinal()];

            default: return null;
        }
    }




}
