package com.java4game.cuadro.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.guiwidgets.AspectRatioHelper;
import com.java4game.cuadro.core.guiwidgets.BaseWidget;
import com.java4game.cuadro.core.guiwidgets.LetterEmersionTextLabel;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.GMUtils;
import com.java4game.cuadro.utils.Timer;

/**
 * Created by FOGOK on 16.06.2017 15:24.
 */

public class StartLogoAnim {

    private static final StartLogoAnim ourInstance = new StartLogoAnim();

    public static StartLogoAnim getInstance() {
        return ourInstance;
    }

    private LetterEmersionTextLabel textLabel;
    private Sprite logoFogok, logoJ4G;
    private boolean isStarted;

    private float animSpeed = 0.002f;

    private int animLogoIters, animLogoMax = 2;

    private Timer timer;

    private StartLogoAnim() {
        logoFogok = Assets.getNewSprite(121);
        AspectRatioHelper.setSpriteSize(logoFogok, 3f, true);
        logoFogok.setCenter(Gm.WIDTH / 2f, Gm.HEIGHT / 2f);
        logoFogok.setOriginCenter();

        logoJ4G = Assets.getNewSprite(122);
        AspectRatioHelper.setSpriteSize(logoJ4G, 5f, true);
        logoJ4G.setCenter(Gm.WIDTH / 2f, Gm.HEIGHT / 2f);
        logoJ4G.setOriginCenter();

        textLabel = new LetterEmersionTextLabel(Gm.WIDTH / 2f, logoFogok.getY() - 2f, 0.8f,
                "A FOGOK COMPANY", 0.05f);

        timer = new Timer(2.3f);

//        isStarted = true;
    }


    public void draw(SpriteBatch batch) {

        float alpha = 1f;
        if (timer.getProgress() > 0.8f)
            alpha = GMUtils.normalizeOneZero((1f - timer.getProgress()) / 0.2f);
        else if (timer.getProgress() < 0.5f)
            alpha = GMUtils.normalizeOneZero(timer.getProgress() / 0.5f);

        switch (animLogoIters) {
            case 0:
                logoFogok.scale(animSpeed * Gm.mdT);
                logoFogok.setAlpha(alpha);
                logoFogok.draw(batch);
                break;
            case 1:
                logoJ4G.scale(animSpeed * Gm.mdT);
                logoJ4G.setAlpha(alpha);
                logoJ4G.draw(batch);
                break;
        }

        textLabel.setSizeText(textLabel.getSizeText() + animSpeed * Gm.mdT * 0.7f);
        textLabel.setAlpha(alpha);
        textLabel.draw(batch);

        if (timer.next()) {
            if (animLogoIters < animLogoMax) {
                switch (animLogoIters) {
                    case 0:
                        textLabel.resetAnimation();
                        textLabel.setText("AND JAVA4GAME COMPANY");
                        textLabel.setSizeText(0.5f);
                        textLabel.setPosition(BaseWidget.Align.LeftBottom, Gm.WIDTH / 2f, logoFogok.getY() - 2f);
                        break;
                    case 1:
                        textLabel.resetAnimation();
                        textLabel.setSizeText(0.8f);
                        textLabel.setText("PRESENTS");
                        textLabel.setPosition(BaseWidget.Align.LeftBottom, Gm.WIDTH / 2f, Gm.HEIGHT / 2f);
                        break;
                }
                animLogoIters++;
                timer.reset();
            }else
                isStarted = true;
        }
    }

    public boolean isStarted() {
        return isStarted;
    }
}
