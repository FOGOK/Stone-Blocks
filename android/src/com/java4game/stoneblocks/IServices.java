package com.java4game.stoneblocks;


public interface IServices {
    void signIn();

    void showAchievements();
    void showLeaderboard();

    void addScore(final String scoreName, final Integer value);
    int getScore(final String scoreName);

    void unlockAchievement(final String achievementID);
    boolean isAchievementUnlocked(final String achievementID);
}
