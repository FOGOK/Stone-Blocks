package com.java4game.cuadro;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.Handler;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;     //прячем кнопки домой и т.д. на некоторых устройствах
		initialize(new Gm(), config);
	}

    @Override
    public void onBackPressed(){
        if (Handler.state != Handler.State.Menu)
            Handler.isBackPressed = true;
        else
            super.onBackPressed();
    }
}
