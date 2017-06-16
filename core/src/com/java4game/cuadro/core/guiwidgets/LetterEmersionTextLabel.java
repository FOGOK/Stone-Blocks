package com.java4game.cuadro.core.guiwidgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.utils.Timer;

/**
 * Created by FOGOK on 16.06.2017 16:56.
 */

public class LetterEmersionTextLabel extends TextLabel {


    private Timer timer;
    private char[] textChars;
    private StringBuilder charSettingsStrBld, finalStringStrBld;
    private int currentCharAnimate = 0;

    private static String openCl = "[", closeCl = "]", hashCl = "#";

    public LetterEmersionTextLabel(float x, float y, float sizeText, String text, float charsSpeed) {
        super(x, y, sizeText, text);
        charSettingsStrBld = new StringBuilder();
        finalStringStrBld = new StringBuilder();
        textChars = text.toCharArray();
        timer = new Timer(charsSpeed);
    }

    public void resetAnimation(){
        currentCharAnimate = 0;
        timer.reset();
    }

    public boolean isAnimEnd(){
        return currentCharAnimate == textChars.length && timer.isEnd();
    }

    @Override
    public void draw(SpriteBatch batch) {
        animate();
        super.draw(batch);
    }

    private void animate(){
        if (timer.next()) {
            if (currentCharAnimate < textChars.length) {
                currentCharAnimate++;
                timer.reset();
            }
        }
        dirty = true;
    }

    @Override
    void refreshTextParams() {

        finalStringStrBld.setLength(0);
        for (int i = 0; i < textChars.length; i++) {
            charSettingsStrBld.setLength(0);
            charSettingsStrBld.append(openCl);
            charSettingsStrBld.append(hashCl);
            if (i >= currentCharAnimate)
                color.a = i > currentCharAnimate ? 0f : timer.getProgress() * alpha;
            else
                color.a = alpha;
            charSettingsStrBld.append(color.toString());
            color.a = 1f;
            charSettingsStrBld.append(closeCl);
            charSettingsStrBld.append(textChars[i]);
            finalStringStrBld.append(charSettingsStrBld.toString());
        }

        setText(finalStringStrBld.toString());

        super.refreshTextParams();
    }
}
