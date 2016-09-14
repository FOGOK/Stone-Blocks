package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;

import java.util.Random;

/**
 * Created by java4game and FOGOK on 10.09.2016 23:16.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class LevelSquare{            //игровое поле

    FrameBuffer frameBuffer;
    Texture texture;
    Sprite[][] sprites;

    Rectangle bounds;
    float size;

    Random rnd = new Random();
    int sqCount;
    public static float sizOneSq, otst;
    public LevelSquare(TextureGen textureGen, float pX, float pY, float size) {
        this.size = size;



        otst = 0.02f;

        sqCount = Cube.SQSIZE + 3;
        sizOneSq = size / sqCount - otst * 2;

        float mmmq = sizOneSq + otst * 2;
        bounds = new Rectangle(pX + mmmq, pY + mmmq, size - mmmq * 2f, size - mmmq * 2f);

        sprites = new Sprite[sqCount][sqCount];
        for (int i = 0; i <  sqCount; i++) {
            for (int i2 = 0; i2 < sqCount; i2++) {
                boolean b = i == 0 || i == sqCount - 1 || i2 == 0 || i2 == sqCount - 1;
                int rn1 = b ? 0 : 6;
                sprites[i][i2] = textureGen.getSprite(String.valueOf(rn1 + rnd.nextInt(6)));
                sprites[i][i2].setSize(sizOneSq, sizOneSq);
                sprites[i][i2].setPosition(pX + (sizOneSq + otst * 2) * i, pY + (sizOneSq + otst * 2) * i2);
            }
        }
//        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) (Gdx.graphics.getWidth() * (size / Gm.WIDTH)), (int) (Gdx.graphics.getWidth() * (size / Gm.WIDTH)), true);


    }





    public Rectangle getBounds(){
        return bounds;
    }

    public float getSize(){
        return size;
    }



    public void fRender(SpriteBatch batch){
        for (int i = 0; i <  sqCount; i++) {
            for (int i2 = 0; i2 < sqCount; i2++) {
                sprites[i][i2].draw(batch);
            }
        }
    }
}
