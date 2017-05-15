package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.Gdx;
import com.java4game.cuadro.core.DialogSystem;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.core.usie.Settings;
import com.java4game.cuadro.core.usie.TypeGameBottomBar;
import com.java4game.cuadro.utils.Prefers;


/**
 * Created by FOGOK on 13.10.2016 0:49.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ButtonActions {

    public enum All{
        START_GAME_ACTION, CONTINUE_PAUSE_ACTION, RESTART_PAUSE_ACTION, SETTINGS_PAUSE_ACTION, TOMAINMENU_PAUSE_ACTION, NEXT_MENU_OPTION,
        WORLD1ACT, WORLD2ACT, WORLD3ACT, WORLD4ACT, WORLD5ACT, QUESTION_ACT, INFO_ACT, PAUSE_ACT, CHANGE_GAME_MODE, NEXT_LEVEL_ACT, START_ARKADE_MODE, START_RANDOM_MODE,
        BACK_TO_MAIN_SCREEN, OPEN_SITE, OPEN_ACHIEVEMENTS, OPEN_LIDERBOARD, OPEN_LEARNING_INTERACTIVE, CHANGE_LANGUAGE, CHANGE_MUSIC, CHANGE_SOUND, CHECKBOXCLICK_AUTORIZETOSTART
    }

    public static void activateAction(All action){
        switch (action){
            case CHECKBOXCLICK_AUTORIZETOSTART:

                break;
            case OPEN_ACHIEVEMENTS:

                break;
            case OPEN_LIDERBOARD:

                break;
            case OPEN_SITE:
                Gdx.net.openURI("http://www.java4game.net");
                break;
            case START_RANDOM_MODE:
                LevelGen.REFRESH_REFRESH = true;
                Handler.ISRESTART = true;
                Handler.ISPAUSE = false;
                LevelGen.ISGAMEOVER = false;
                Handler.state = Handler.State.Game;
                StageButton.LEVEL = StageButton.RANDOM_LEVEL;
                break;
            case START_ARKADE_MODE:
                LevelGen.REFRESH_REFRESH = true;
                Handler.ISRESTART = true;
                Handler.ISPAUSE = false;
                LevelGen.ISGAMEOVER = false;
                Handler.state = Handler.State.Game;
                StageButton.LEVEL = StageButton.ARKADE_LEVEL;
//                MusicCore.playSound(4);
                break;
            case CHANGE_GAME_MODE:
                TypeGameBottomBar.SELECTED_BTN = TypeGameButton.TOUCHED_B;
                MusicCore.playSound(4);
                break;
//            case COMPLETE_LEARN:
//                final char[] chars = Prefers.getString(Prefers.KeyStarsSteps).toCharArray();
//                chars[0] = '3';
//                Prefers.putString(Prefers.KeyStarsSteps, new String(chars));
//
////            chars[LEVEL] =
//                ///
//
//                if (MenuUI.OPENEDSTAGESINWORLD[0] == 1){     //открываем следующий уровень
//                    MenuUI.OPENEDSTAGESINWORLD[0]++;
//                    Prefers.putInt(Prefers.KeyOpenedStagesSteps, MenuUI.OPENEDSTAGESINWORLD[0]);
//                }
//                break;
            case PAUSE_ACT:
                Handler.isBackPressed = true;
                break;
            case WORLD1ACT:
                MenuUI.SELECTEDWORLD = 0;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE++;
                break;
            case WORLD2ACT:
                MenuUI.SELECTEDWORLD = 1;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE++;
                break;
            case WORLD3ACT:
                MenuUI.SELECTEDWORLD = 2;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE++;
                break;
            case WORLD4ACT:
                MenuUI.SELECTEDWORLD = 3;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE++;
                break;
            case WORLD5ACT:
                MenuUI.SELECTEDWORLD = 4;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE++;
                break;

            case NEXT_MENU_OPTION:
//                MenuUI.MENUSTATE++;
                if (Prefers.getBool(Prefers.KeyFirstOpenLearn, false)){     //если ещё не проходили обучение
                    MenuUI.SELECTEDWORLD = 0;
                    MenuUI.SETSTAGEPROP = true;
                    MenuUI.MENUSTATE = 2;
                }else{
                    MenuUI.MENUSTATE = MenuUI.TRAINING;
                }
                MusicCore.playSound(4);
                break;
            case START_GAME_ACTION:
                Handler.state = Handler.State.Game;

                break;

            case CONTINUE_PAUSE_ACTION:
                Handler.ISPAUSE = false;
                Handler.state = Handler.State.Game;
                break;

            case RESTART_PAUSE_ACTION:
                DialogSystem.ISLEARNING = false;
                Handler.ISRESTART = true;
                Handler.ISPAUSE = false;
                LevelGen.ISGAMEOVER = false;
                Handler.state = Handler.State.Game;
                LevelGen.REFRESH_REFRESH = false;
                break;
            case NEXT_LEVEL_ACT:
                Handler.ISRESTART = true;
                LevelGen.ISGAMEOVER = false;
                Handler.ISPAUSE = false;
                LevelGen.REFRESH_REFRESH = false;
                break;
            case QUESTION_ACT:
                MusicCore.playSound(7);
                MenuUI.MENUSTATE = MenuUI.TRAINING;
                break;
            case INFO_ACT:
                MusicCore.playSound(7);
                MenuUI.MENUSTATE = MenuUI.INFORMATION;
                break;
            case BACK_TO_MAIN_SCREEN:
                MenuUI.MENUSTATE = MenuUI.GAMEMAIN;
                break;

            case OPEN_LEARNING_INTERACTIVE:
                DialogSystem.ISLEARNING = true;
                switch (DialogSystem.LEARNING_PART){
                    case 0:
                        StageButton.LEVEL = StageButton.RANDOM_LEVEL;
                        TypeGameButton.RNDLEVEL = 0;
                        break;
                    case 1:
                        StageButton.LEVEL = StageButton.ARKADE_LEVEL;
                        TypeGameButton.TOUCHED_ARK = 1;
                        break;
                }

                Handler.ISRESTART = true;
                Handler.ISPAUSE = false;
                LevelGen.ISGAMEOVER = false;
                Handler.state = Handler.State.Game;
                LevelGen.REFRESH_REFRESH = true;
                break;

            case SETTINGS_PAUSE_ACTION:
                Settings.OPENCLOSEACTION = true;
                break;

            case CHANGE_LANGUAGE:
                int tmpLoc = Prefers.getInt(Prefers.RusEng);
                Prefers.putInt(Prefers.RusEng, tmpLoc == 0 ? 1 : 0);
                Handler.REFRESHLANG = true;
                break;

            case CHANGE_SOUND:
                Prefers.putBool(Prefers.SoundEnb, !Prefers.getBool(Prefers.SoundEnb, false));
                MusicCore.refreshState();
                break;

            case CHANGE_MUSIC:
                Prefers.putBool(Prefers.MusicEnb, !Prefers.getBool(Prefers.MusicEnb, false));
                MusicCore.refreshState();
                break;

            case TOMAINMENU_PAUSE_ACTION:

                Handler.state = Handler.State.Menu;
                Handler.ISPAUSE = false;
                MenuUI.RESETANIMATION = true;
                MenuUI.SETSTAGEPROP = true;

                if (DialogSystem.ISLEARNING)
                    MenuUI.MENUSTATE = MenuUI.GAMEMAIN;
                else
                    MenuUI.MENUSTATE = MenuUI.SELECTSTAGE;


                MusicCore.play(MusicCore.MENU);
                break;
        }
    }

}
