package com.java4game.cuadro.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.java4game.cuadro.Gm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Quadro";
		config.width = 480; config.height = 800;
		//config.width = 480; config.height = 854;
		//config.width = 540; config.height = 960;
		//config.width = 600; config.height = 1024;
		//config.width = 720; config.height = 1280;
		//config.width = 800; config.height = 1280;
		new LwjglApplication(new Gm(), config);
	}
}
