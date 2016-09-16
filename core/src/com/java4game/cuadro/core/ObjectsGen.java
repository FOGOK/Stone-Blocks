package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.objects.GameObject;
import com.java4game.cuadro.objects.NumberObj;
import com.java4game.cuadro.objects.SquareObject;

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
    SquareObject[] allObjects;



    int edge_lenght;
    int iters = 0;

    public ObjectsGen(int edge_lenght, float fulledCff, Rectangle sqBounds, TextureGen textureGen) {
        this.edge_lenght = edge_lenght;

        //generate
        int cQ = (edge_lenght + 1) * (edge_lenght + 1);
        allObjects = new SquareObject[cQ];
        types = new ObjType[cQ];


        for (int xQ = 0; xQ < edge_lenght; xQ++) {
            for (int yQ = 0; yQ < edge_lenght; yQ++) {
                if (((rnd.nextInt(10) + 1) < (int) (10 * fulledCff))){
                    types[iters] = ObjType.Number;
                    switch (types[iters]){
                        case Number:
                            //  если выпал тип цифры
                            int rnnn = rnd.nextInt(9) + 1;
                            allObjects[iters] = new NumberObj(textureGen.getSprite("num" + rnnn), xQ, yQ, sqBounds, rnnn);

                            break;
                    }
                    iters++;
                }
            }
        }
        ///

    }

    public void draw(SpriteBatch batch){
        for (int i = 0; i < iters; i++) {
            if (!allObjects[i].isEndedAnim())
                allObjects[i].draw(batch);

        }
    }

    public int getObjCount(){
        return iters;
    }

    public ObjType getObjType(int i){
        return types[i];
    }

    public SquareObject getObjects(int i){
        return allObjects[i];
    }

    public void collectObject(int i){
        allObjects[i].collect();
    }


    public void dispose() {

    }
}
