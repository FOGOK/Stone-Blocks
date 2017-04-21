package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.usie.UI;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Timer;

import java.util.Random;


public class RobotHead {

    private Sprite head, dialogCover, leftEave, rightEave;
    private float sizeH;
    private String text;
    private float sizeText;
    private Rectangle textAreaBounds;
    private GlyphLayout glyphLayout = new GlyphLayout();

    private Timer timer;

    public RobotHead(float y) {
        sizeH = Gm.WIDTH * 0.32f;
        if (sizeH > 3.6f) sizeH = 3.6f;

        head = Assets.getNewSprite(90);
        head.setSize(sizeH * 1.04f, sizeH);

        dialogCover = Assets.getNewSprite(89);
        dialogCover.setSize(sizeH * 0.829f * 2.67f, sizeH * 0.829f);

        textAreaBounds = new Rectangle();
        textAreaBounds.setSize(dialogCover.getWidth() * 0.866f, dialogCover.getHeight() * 0.853f);

        leftEave = Assets.getNewSprite(97);
        leftEave.setSize(head.getHeight() * 0.473f, head.getHeight() * 0.473f);

        rightEave = new Sprite();
        rightEave.set(leftEave);

        timer = new Timer(1.3f);

        setPositionY(y);
    }

    public float getTopY(){
        return head.getY() + head.getHeight();
    }

    public void setPositionY(float y){
        float dialogCoverX = head.getWidth() * 0.864f;
        float sizeWidth = dialogCoverX + dialogCover.getWidth();

        head.setPosition((Gm.WIDTH - sizeWidth) / 2f, y);
        dialogCover.setPosition(head.getX() + dialogCoverX, y);

        textAreaBounds.setPosition(dialogCover.getX() + dialogCover.getWidth() * 0.11f, dialogCover.getY() + dialogCover.getHeight() * 0.159f);
        float otst = 0.1f;
        textAreaBounds.setPosition(textAreaBounds.getX() + otst, textAreaBounds.getY() + otst);
        textAreaBounds.setSize(textAreaBounds.getWidth() - otst * 2f, textAreaBounds.getHeight() - otst * 2f);

        leftEave.setPosition(head.getX() + head.getWidth() * 0.049f, head.getY() + head.getHeight() * 0.251f);
        rightEave.setPosition(head.getX() + head.getWidth() * 0.514f, head.getY() + head.getHeight() * 0.215f);
    }

    public void setText(String text, float sizeText){
        this.text = text;
        this.sizeText = sizeText;
        UI.setCff(false, sizeText);
        glyphLayout.setText(UI.getContentFont(), text, Color.valueOf("323232"), textAreaBounds.getWidth(), Align.center, true);
    }

    public void draw(SpriteBatch batch){
        head.draw(batch);
        dialogCover.draw(batch);
        UI.setCff(false, sizeText);
        UI.getContentFont().draw(batch, glyphLayout, textAreaBounds.getX(), textAreaBounds.getY() + (textAreaBounds.getHeight() + glyphLayout.height) / 2f);

        if (timer.next()) {
            if (isEyesClosed){
                timer.reset(rnd.nextInt(20) / 10f);
                isEyesClosed = false;
            }else{
                timer.reset(0.2f);
                isEyesClosed = true;
            }
        }

        if (isEyesClosed){
            leftEave.draw(batch);
            rightEave.draw(batch);
        }
    }

    private boolean isEyesClosed;
    private Random rnd = new Random();

}
