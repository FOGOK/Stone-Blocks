package com.java4game.cuadro.utils;

/**
 * Created by FOGOK on 17.07.2016 0:54.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class PosF {

    public float x, y;

    public PosF(final float x, final float y) {
        set(x, y);
    }

    public PosF(final PosF pos){
        set(pos.x, pos.y);
    }

    public void set(final float x, final float y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(final float x, final float y){
        return this.x == x && this.y == y;
    }
}
