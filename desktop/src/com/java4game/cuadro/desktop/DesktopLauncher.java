package com.java4game.cuadro.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.ViewportsTest1;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

//        config.width = 160;
//        config.height = 320;
//
//        config.samples = 8;
        config.width = 320;
        config.height = 640;
        config.resizable = false;
        config.y = 768 / 2 - 20 - config.height / 2;

		new LwjglApplication(new Gm(), config);
	}
}
