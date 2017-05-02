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
    private static Lang lang = Lang.RUS;

    public enum LettersKey{
        BACK, TRAINING, HELLOINF, INF1, INF2, ARCMODE, CTBCFT, 
        RED, BLUE, YELLOW, WHITE, GREEN, BLOCK, REVERS, TELEPORT,
        ROTATOR, SLOWER, TURBO, TIMEUP, MOVER, BOMB, GAMEOVER,
        PTS, SEC, MULTIXTEXT, OHMYGODTEXT, NICEALREADYTEXT, SUPERBONUS, TR1, TR2, TR3, GOOD, TR5, TR6, TR7, TR8,
        TR9, TR10, TR11, TR12, TR13, TR14, TR15
    }

    //RUS
    private static String[] rusLetters = new String[]{
        "назад", "тренировка", "привет!!!\n меня зовут мувер", "BLOCK MOVER", "версия 1.0.2\n\n" +
            "идея, арт: андрей светашов\n" +
            "программирование: олег карловский\n\n" +
            "больше игр здесь:", "аркадный режим", "нажмите на черный куб для тренировки",
            "красный", "синий", "желтый", "белый", "зеленый", "блок", "реверс", "телепорт",
            "ротатор", "замедлитель", "ускоритель", "прибавка времени", "мувер", "бомба",
            "завершение игры", "очк.", "сек.", "x!!! всего очк:", "о боже!!!", "отлично, уже",
            "супер бонус!!!", "привет! добро пожаловать на тренировку", "ждите, пока кубик не достигнет зеленого квадрата",
            "нажмите на экран!!!", "хорошо!", "теперь повторите сами", "нажмите на экран, когда кубик будет на зеленом квадрате",
            "шаг первый: остановите блок перед лункой",
            "шаг второй: загоните блок в лунку", "это реверс. возьмите его", "это телепорт. он телепортирует на случайную позицию",
            "это ускоритель. при загоне блока под ускорителем вы получите 2х очков", "это замедлитель. за него вы получите 5 очков",
            "это ротатор. при загоне блока после поворота вы получите 3х очков",
            "загоните два кубика в одну лунку, чтобы сделать умножение очков", "тренировка завершена!"

    };
    ///

    //ENG
    private static String[] engLetters = new String[]{
        "back", "training", "hello!!!\n my name is mover", "BLOCK MOVER", "VERSION 1.0.2\n\n" +
            "IDEA, ART: ANDREY SVETASHOV\n" +
            "PROGRAMMING: OLEG KARLOVSKIY\n\n" +
            "MORE GAMES HERE:", "ARCADE MODE", "CLICK TO BLACK CUBE FOR TRAINING",
            "RED", "BLUE", "YELLOW", "WHITE", "GREEN", "BLOCK", "REVERS", "TELEPORT",
            "ROTATOR", "SLOWER", "TURBO", "TIME UP", "MOVER", "BOMB", "GAME OVER",
            "PTS", "SEC", "X!!! TOTAL SCORE:",
            "Oh, My GOD!!!", "Nice! Already ", "SUPER BONUS!!!", "HI! WELCOME TO TRAINING",
            "WAIT UNTIL THE CUBE COME GREEN SQUARE", "TAAAP TO SCREEEEN!", "GOOD!", "NOW DO IT YOURSELF",
            "TAP TO SCREEN IN GREEN POSITION FOR MOVE BLOCK", "STEP ONE: STOP THE BLOCK BEFORE WHITE HOLE",
            "STEP TWO: STOP THE BLOCK IN WHITE HOLE", "THIS IS REVERSE. TRY IT!", "THIS IS TELEPORT AT RANDOM POSITION",
            "THIS IS TURBO.\n X2 POINTS GET IT", "THIS IS SLOWER.\n 5 POINTS GET IT", "THIS IS ROTATOR.\n X2 POINTS GET IT",
            "2 IN 1 HOLE.\n X2 POINTS DO IT", "TRAINING COMPLETED!"

    };
    ///

    public enum CffsKey{
        CTBCFT, TURBO, SLOWER, TR1, TR11, TR12, TR13
    }

    //RUS сffs
    private static float[] rusCffs = new float[]{
            0.7f, 0.5f, 0.5f, 0.7f, 0.6f, 0.8f, 0.7f
    };
    ///

    public static Lang getCurrentLang(){
        return lang;
    }

    public static float getCff(CffsKey cffsKey){
        switch (lang){
            case RUS:
                return rusCffs[cffsKey.ordinal()];
            case ENG:
                return 1f;

            default: return 0f;
        }
    }

    public static String getText(LettersKey key){
        switch (lang){
            case RUS:
                return rusLetters[key.ordinal()];
            case ENG:
                return engLetters[key.ordinal()];

            default: return "";
        }
    }




}
