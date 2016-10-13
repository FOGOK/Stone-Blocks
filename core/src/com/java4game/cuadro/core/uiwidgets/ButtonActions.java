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

    public static final int START_MENU_ACTION = 0;

    public static final int CONTINUE_PAUSE_ACTION = 1;
    public static final int RESTART_PAUSE_ACTION = 2;
    public static final int OPTION_PAUSE_ACTION = 3;
    public static final int TOMAINMENU_PAUSE_ACTION = 4;


    public static void activateAction(int i){
        switch (i){
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

            case OPTION_PAUSE_ACTION:
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
