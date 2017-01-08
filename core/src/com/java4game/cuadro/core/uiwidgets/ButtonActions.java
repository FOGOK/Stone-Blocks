package com.java4game.cuadro.core.uiwidgets;

import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.utils.D;

/**
 * Created by FOGOK on 13.10.2016 0:49.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ButtonActions {

    public enum All{
        START_GAME_ACTION, CONTINUE_PAUSE_ACTION, RESTART_PAUSE_ACTION, SETTINGS_PAUSE_ACTION, TOMAINMENU_PAUSE_ACTION, NEXT_MENU_OPTION,
        WORLD1ACT, WORLD2ACT, WORLD3ACT, WORLD4ACT, WORLD5ACT, QUESTION_ACT, INFO_ACT
    }

    public static void activateAction(All action){
        switch (action){
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
                MenuUI.SELECTEDWORLD = 0;
                MenuUI.SETSTAGEPROP = true;
                MenuUI.MENUSTATE = 2;
                break;
            case START_GAME_ACTION:
                Handler.state = Handler.State.Game;
                break;

            case CONTINUE_PAUSE_ACTION:
                Handler.ISPAUSE = false;
                Handler.state = Handler.State.Game;
                break;

            case RESTART_PAUSE_ACTION:
                Handler.ISRESTART = true;
                Handler.ISPAUSE = false;
                Handler.state = Handler.State.Game;
                break;

            case QUESTION_ACT:

                break;
            case INFO_ACT:

                break;

            case SETTINGS_PAUSE_ACTION:
                D.S("OPT");
                break;

            case TOMAINMENU_PAUSE_ACTION:
                Handler.ISRESTART = true;
                Handler.state = Handler.State.Menu;
                Handler.ISPAUSE = false;

                MenuUI.RESETANIMATION = true;
                MenuUI.MENUSTATE = 0;
                MusicCore.play(MusicCore.MENU);
                break;
        }
    }

}
