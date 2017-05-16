package com.java4game.stoneblocks.basegameutils;

import com.java4game.cuadro.IServices;

/**
 * Created by FOGOK on 21.04.2017 14:55.
 */

public class AchievementsManager {
    public static IServices achievements;
    public static void setPlayService(IServices _achievements) {
        achievements = _achievements;
    }
}
