package com.java4game.cuadro.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.IServices;
import com.java4game.cuadro.core.Handler;

import static com.java4game.cuadro.core.usie.MenuUI.TEST;

public class DesktopLauncher implements IServices {

    private static DesktopLauncher desktopLauncher;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

//        config.width = 160;
//        config.height = 320;

        if (desktopLauncher == null)
            desktopLauncher = new DesktopLauncher();

        boolean Oleg = true;

        Handler.TEST_STRING = "1,3,1641_2841_6340_5640,3,12";

        TEST = false;
        if (arg != null){
            if (arg.length > 0){
                if (arg[0] != null){
                    TEST = true;
                    Handler.TEST_STRING = arg[0];
                }
            }
        }




        config.samples = 10;
        config.resizable = false;

        if (Oleg){
//            config.width = 450;
//            config.height = 600;
            config.width = 360;
            config.height = 640;
//            config.y = 768 / 2 - 20 - config.height / 2;
        }else{
            config.width = 480;
            config.height = 854;
        }

		new LwjglApplication(new Gm(desktopLauncher), config);
	}

    @Override
    public void signIn() {

    }

    @Override
    public void showAchievements() {

    }

    @Override
    public void showLeaderboard() {

    }

    @Override
    public void addScore(int where, int value) {

    }

    @Override
    public int getScore(int where) {
        return 0;
    }

    @Override
    public void unlockAchievement(String achievementID) {

    }

    @Override
    public boolean isAchievementUnlocked(String achievementID) {
        return false;
    }
}
