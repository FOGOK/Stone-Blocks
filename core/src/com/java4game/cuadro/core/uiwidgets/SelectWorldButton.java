package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 04.11.2016 11:12.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class SelectWorldButton extends BaseButton {

    private Sprite lockedWorld, starIco;
    private int backT = 0, frontT = 0;
    private TextBlock text, starsText;
    public SelectWorldButton(TextureGen textureGen, float x, float y, float h, int world) {
        super(textureGen, ButtonActions.All.WORLD1ACT, x, y, h,  Atalas.worldBtn1, Atalas.worldBtn1);
        starIco = textureGen.getSprite(Atalas.star);

        final float otstf = 0.1f;
        final float sizeText = bounds.getHeight() * 0.251f;
        starIco.setBounds(x + bounds.width * 0.529f, y, h * 0.4f, h * 0.4f);

        int countStars = 0, allStars = 0;

        text = new TextBlock(x + bounds.width * 0.927f, y + bounds.height * 0.61f, true, "Q");
        switch (world) {
            case 0:
                text.setText(Localization.getText(Localization.LettersKey.SAND));
                backT = Atalas.worldBtn1;
                frontT = Atalas.worldBtn2;
                setAction(ButtonActions.All.WORLD1ACT);
//                countStars = MenuUI.STARSINSTAGES[0][0];
//                allStars = MenuUI.STARSINSTAGES[0][1];
                break;
            case 1:
                text.setText(Localization.getText(Localization.LettersKey.ICE_SITY));
                backT = Atalas.worldBtn2;
                frontT = Atalas.worldBtn1;
                setAction(ButtonActions.All.WORLD2ACT);
//                countStars = MenuUI.STARSINSTAGES[1][0];
//                allStars = MenuUI.STARSINSTAGES[1][1];
                break;
            case 2:
                text.setText(Localization.getText(Localization.LettersKey.SUMMER));
                backT = Atalas.worldBtn3;
                frontT = Atalas.worldBtn2;
                setAction(ButtonActions.All.WORLD3ACT);
//                countStars = MenuUI.STARSINSTAGES[2][0];
//                allStars = MenuUI.STARSINSTAGES[2][1];
                break;
            case 3:
                text.setText(Localization.getText(Localization.LettersKey.RAINBOW));
                backT = Atalas.worldBtn4;
                frontT = Atalas.worldBtn2;
                setAction(ButtonActions.All.WORLD4ACT);
//                countStars = MenuUI.STARSINSTAGES[3][0];
//                allStars = MenuUI.STARSINSTAGES[3][1];
                break;
            case 4:
                text.setText(Localization.getText(Localization.LettersKey.DARK_SIDE));
                text.setTextColor(Color.valueOf("858585"));
                backT = Atalas.worldBtn5;
                frontT = Atalas.worldBtn2;
                setAction(ButtonActions.All.WORLD5ACT);
//                countStars = MenuUI.STARSINSTAGES[4][0];
//                allStars = MenuUI.STARSINSTAGES[4][1];
                break;
        }

        lockedWorld = textureGen.getSprite(Atalas.worldBtnLock);
        setFirstTexture(textureGen.getSprite(backT));
        setSecondTexture(textureGen.getSprite(frontT));

        starsText = new TextBlock(starIco.getX() + starIco.getWidth() + otstf, y + 0.2f, true, countStars + "/" + allStars);
        starsText.setCustomCff(sizeText);


        text.setCustomCff(sizeText);
        text.setPosition(text.getBounds().x - text.getBounds().width, text.getBounds().y);
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        final float otstf = 0.1f;
        text.setPosition((bounds.x + bounds.width * 0.927f) - text.getBounds().width, bounds.y + bounds.height * 0.61f);
        starIco.setPosition(bounds.x + bounds.width * 0.529f, bounds.y);
        starsText.setPosition(starIco.getX() + starIco.getWidth() + otstf, bounds.y + 0.2f);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setLockedWorld(enabled);
    }

    private void setLockedWorld(boolean enabled){
        setFirstTexture(enabled ? normalTex : lockedWorld);
        if (!enabled){
            text.setTextColor(Color.valueOf("808080"));
            starsText.setTextColor(Color.valueOf("808080"));
            starsText.setText("0");
            starIco.setAlpha(0.4f);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        text.draw(batch);
        starIco.draw(batch);
        starsText.draw(batch);
    }

    public void dispose() {

    }
}
