package com.java4game.cuadro;


public interface IServices {
    void signIn();

    void showAchievements();
    void showLeaderboard();

    void addScore(int where, final int value);
    int getScore(int where);

    void unlockAchievement(final String achievementID);
    boolean isAchievementUnlocked(final String achievementID);
}
