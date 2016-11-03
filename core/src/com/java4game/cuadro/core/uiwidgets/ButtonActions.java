package com.java4game.cuadro.core.uiwidgets;

import com.java4game.cuadro.core.Handler;
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
        START_MENU_ACTION, CONTINUE_PAUSE_ACTION, RESTART_PAUSE_ACTION, SETTINGS_PAUSE_ACTION, TOMAINMENU_PAUSE_ACTION
    }

    public static void activateAction(All action){
        switch (action){
            case START_MENU_ACTION:
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

            case SETTINGS_PAUSE_ACTION:
                D.S("OPT");
                break;

            case TOMAINMENU_PAUSE_ACTION:
                Handler.ISRESTART = true;
                Handler.state = Handler.State.Menu;
                Handler.ISPAUSE = false;

                MenuUI.RESETANIMATION = true;
                break;
        }
    }

}
