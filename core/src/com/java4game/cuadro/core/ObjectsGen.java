package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.objects.ColoredCube;
import com.java4game.cuadro.objects.GameObject;
import com.java4game.cuadro.objects.LevelSquare;
import com.java4game.cuadro.objects.NumberObj;
import com.java4game.cuadro.objects.SquareObject;
import com.java4game.cuadro.utils.Atalas;

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
        Number, Colored
    }

    ObjType[] types;
    SquareObject[] allObjects;

    ColorCubeMover colorCubeMover;


    public static int edge_lenght;
    public static int iters = 0;
    public static int verCff;
    int malPlus = 0; ///если мы не можем выставить объект в заданной точке, тогда прибавляем 1 к этой темке, и при возможности ставим

    public ObjectsGen(int edge_lenght, float fulledCff, Rectangle levSqBounds, TextureGen textureGen) {
        this.edge_lenght = edge_lenght;

        //generate
        int cQ = (edge_lenght + 1) * (edge_lenght + 1);
        allObjects = new SquareObject[cQ];
        types = new ObjType[cQ];
        verCff = edge_lenght * edge_lenght;
        malPlus = 0;
        iters = 0;


        for (int xQ = 0; xQ < edge_lenght; xQ++) {
            for (int yQ = 0; yQ < edge_lenght; yQ++) {
                if (((rnd.nextInt(verCff) + 1) < (int) (verCff * fulledCff)/* || malPlus != 0*/) && LevelSquare.isTrue[xQ + 1][yQ + 1]){

//                    if (isNormalPos(xQ + 1, yQ + 1)){
                        types[iters] = (rnd.nextBoolean()) ? ObjType.Number : ObjType.Colored;

                        if (malPlus != 0) malPlus--;

                        switch (types[iters]){
                            case Number:
                                //  если выпал тип цифры
                                int rnnn = rnd.nextInt(9) + 1;
                                allObjects[iters] = new NumberObj(textureGen.getSprite("num" + rnnn), xQ, yQ, levSqBounds, rnnn, iters);

                                break;
                            case Colored:
                                //если выпал тип цвета
                                int rnnn2 = rnd.nextInt(4) + 1;
                                allObjects[iters] = new ColoredCube(textureGen.getSprite(Atalas.squareT3), xQ, yQ, levSqBounds, false, allObjects, iters);

                                break;
                        }
                        iters++;
//                    }

                }
            }
        }

        colorCubeMover = new ColorCubeMover(allObjects);
        ///
    }

//    private boolean isNormalPos(int x, int y){
//
//        int b = 2;
//        for (int i = 1; i < LevelGen.SQSIZE + 2; i++) {
//            if (!LevelSquare.isTrue[x][i]){
//                b--;
//                break;
//            }
//
//        }
//
//        for (int i = 1; i < LevelGen.SQSIZE + 2; i++) {
//            if (!LevelSquare.isTrue[i][y]){
//                b--;
//                break;
//            }
//        }
//
//        if (b == 0) malPlus++;
//
//        return (b > 0);
//    }

    public void draw(SpriteBatch batch){
        for (int i = 0; i < iters; i++) {
            if (!allObjects[i].isEndedAnim())
                allObjects[i].draw(batch);
        }

        colorCubeMover.match(batch);
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
