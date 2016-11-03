package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.core.LevelGen;
import com.java4game.cuadro.core.TextureGen;

import java.util.Random;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class LevelSquare{            //игровое поле
    Sprite[][] sprites;
    public static boolean[][] isTrue;

    Rectangle bounds;
    float size;

    Random rnd = new Random();
    int sqCount, iters;
    public static float sizOneSq, otst;
    int verCff;
    public LevelSquare(TextureGen textureGen, float pX, float pY, float size, float sttCff) {
        this.size = size;

        verCff = (LevelGen.SQSIZE + 1) * (LevelGen.SQSIZE + 1);

        otst = 0.02f;

        sqCount = LevelGen.SQSIZE + 3;
        sizOneSq = size / sqCount - otst * 2;

        float mmmq = sizOneSq + otst * 2;
        bounds = new Rectangle(pX + mmmq, pY + mmmq, size - mmmq * 2f - otst, size - mmmq * 2f - otst);

        sprites = new Sprite[sqCount][sqCount];
        isTrue = new boolean[sqCount][sqCount];

        iters = 0;


        for (int i = 0; i <  sqCount; i++) {
            for (int i2 = 0; i2 < sqCount; i2++) {
                boolean b = i == 0 || i == sqCount - 1 || i2 == 0 || i2 == sqCount - 1;//
//                if (((rnd.nextInt(verCff) + 1) < (int) (verCff * (1f - sttCff))) || b){
                    int rn1 = b ? 3 : 6;
                    isTrue[i][i2] = true;
                    sprites[i][i2] = textureGen.getSprite(rn1);
//                    sprites[i][i2] = textureGen.getSprite("col1");
                    sprites[i][i2].setSize(sizOneSq, sizOneSq);
                    sprites[i][i2].setPosition(pX + (sizOneSq + otst * 2) * i, pY + (sizOneSq + otst * 2) * i2);//
//                }
            }
        }
//        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) (Gdx.graphics.getWidth() * (size / Gm.WIDTH)), (int) (Gdx.graphics.getWidth() * (size / Gm.WIDTH)), true);
    }


    public Rectangle getBounds(){
        return bounds;
    }

    public void draw(SpriteBatch batch){
        for (int i = 0; i <  sqCount; i++) {
            for (int i2 = 0; i2 < sqCount; i2++) {
                if (isTrue[i][i2])//
                    sprites[i][i2].draw(batch);
//                DebugDrawer.drawRect(batch, sprites[i][i2].getBoundingRectangle());
            }
        }

////        DebugDrawer.drawRect(batch, bounds);
    }
}
