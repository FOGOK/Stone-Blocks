package com.java4game.cuadro.utils;

/**
 * Created by FOGOK on 17.07.2016 0:54.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class Pos {

    public int x, y;

    public Pos(final int x, final int y) {
        set(x, y);
    }

    public Pos(final Pos pos){
        set(pos.x, pos.y);
    }

    public void set(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(final int x, final int y){
        return this.x == x && this.y == y;
    }
}
