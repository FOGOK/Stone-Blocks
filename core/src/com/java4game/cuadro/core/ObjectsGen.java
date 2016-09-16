package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.objects.GameObject;
import com.java4game.cuadro.objects.NumberObj;
import com.java4game.cuadro.utils.Pos;

import java.util.Random;

/**
 * Created by FOGOK on 16.09.2016 16:33.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ObjectsGen {

    Random rnd = new Random();

    enum ObjType{
        Number
    }

    ObjType[] types = new ObjType[ObjType.values().length];
    NumberObj[] numberObjs;



    int edge_lenght;
    int iters = 0;

    public ObjectsGen(int edge_lenght, float fulledCff, Rectangle sqBounds, TextureGen textureGen) {
        this.edge_lenght = edge_lenght;

        //generate
        int cQ = (edge_lenght + 1) * (edge_lenght + 1);
        numberObjs = new NumberObj[cQ];
        types = new ObjType[cQ];


        for (int xQ = 0; xQ < edge_lenght; xQ++) {
            for (int yQ = 0; yQ < edge_lenght; yQ++) {
                if (((rnd.nextInt(10) + 1) < (int) (10 * fulledCff))){
                    types[iters] = ObjType.Number;
                    int rnnn = rnd.nextInt(9) + 1;
                    numberObjs[iters] = new NumberObj(textureGen.getSprite("num" + rnnn), xQ, yQ, sqBounds, rnnn);
                    iters++;
                }
            }
        }
        ///

    }

    public void draw(SpriteBatch batch){
        for (int i = 0; i < iters; i++) {
            switch (types[i]){
                case Number:
                    if (!numberObjs[i].isDestroyed())
                        numberObjs[i].draw(batch);
                    break;
            }
        }
    }

    public int getObjCount(){
        return iters;
    }

    public ObjType getObjType(int i){
        return types[i];
    }

    public GameObject getObjects(int i){
        switch (types[i]){
            case Number:    return numberObjs[i];
        }
        return null;
    }

    public void collectObject(int i){
        switch (types[i]){
            case Number:
                numberObjs[i].collect();
                break;
        }
    }


    public void dispose() {

    }
}
