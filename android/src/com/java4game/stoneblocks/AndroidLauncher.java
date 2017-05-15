package com.java4game.stoneblocks;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.stoneblocks.basegameutils.AchievementsManager;
import com.java4game.stoneblocks.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements IServices, GameHelper.GameHelperListener{

    private GameHelper gameHelper;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        initGPGS();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;     //прячем кнопки домой и т.д. на некоторых устройствах
		initialize(new Gm(), config);
	}

	private void initGPGS(){
        gameHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
        AchievementsManager.setPlayService(this);
        gameHelper.setup(this);
    }

    @Override
    public void onBackPressed(){
        if (Handler.state != Handler.State.Menu)
            Handler.isBackPressed = true;
        else if (MenuUI.MENUSTATE == MenuUI.SELECTSTAGE || MenuUI.MENUSTATE == MenuUI.INFORMATION)
            MenuUI.MENUSTATE = MenuUI.GAMEMAIN;
        else
            super.onBackPressed();
    }

    @Override
    public void signIn() {

    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void showAchievements() {

    }

    @Override
    public void showLeaderboard() {

    }

    @Override
    public void addScore(String scoreName, Integer value) {

    }

    @Override
    public int getScore(String scoreName) {
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
