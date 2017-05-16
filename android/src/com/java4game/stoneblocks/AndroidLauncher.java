package com.java4game.stoneblocks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.IServices;
import com.java4game.cuadro.R;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.utils.Localization;
import com.java4game.stoneblocks.basegameutils.AchievementsManager;
import com.java4game.stoneblocks.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements IServices, GameHelper.GameHelperListener{

    private GameHelper gameHelper;
    final AndroidLauncher context = this;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        initGPGS();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;     //прячем кнопки домой и т.д. на некоторых устройствах
        config.useWakelock = true;
		initialize(new Gm(this), config);
	}

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    private void initGPGS(){
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(true);
        gameHelper.setConnectOnStart(true);
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
        try {
            runOnUiThread(new Runnable(){
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (final Exception ex) {
        }
    }

    @Override
    public void onSignInFailed() {
        gameHelper.showFailureDialog();
    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void showAchievements() {
        if (gameHelper.isSignedIn()) {
            startActivityForResult(
                    Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
        }else{
            toast(Localization.getText(Localization.LettersKey.TRY_INTERNET));
            signIn();
        }
    }

    @Override
    public void showLeaderboard() {
        if (gameHelper.isSignedIn()) {
            startActivityForResult(
                    Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                            getResources().getString(R.string.event_gold_arkade_score)), 101);
        }else{
            toast(Localization.getText(Localization.LettersKey.TRY_INTERNET));
            signIn();
        }
    }

    public void toast(final String txt){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void addScore(int where, int value) {
        if (gameHelper.isSignedIn()) {
            String s;
            switch (where){
                case 0:
                    s = getResources().getString(R.string.event_gold_arkade_score);
                    break;
                default:
                    s = getResources().getString(R.string.event_giant_brain_random_games);
                    break;
            }
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), s, value);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        gameHelper.signOut();
        super.onDestroy();
    }
}
