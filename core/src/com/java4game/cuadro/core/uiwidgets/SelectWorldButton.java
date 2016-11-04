package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 04.11.2016 11:12.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class SelectWorldButton extends BaseButton {

    private Sprite lockedWorld;
    private int backT = 0, frontT = 0;
    private String text;
    public SelectWorldButton(TextureGen textureGen, float x, float y, float h, int world) {
        super(textureGen, ButtonActions.All.WORLD1ACT, x, y, h,  Atalas.worldBtn1, Atalas.worldBtn1);

        switch (world) {
            case 0:
                text = Localization.getText(Localization.LettersKey.SAND);
                backT = Atalas.worldBtn1;
                frontT = Atalas.worldBtn1;
                setAction(ButtonActions.All.WORLD1ACT);
                break;
            case 1:
                text = Localization.getText(Localization.LettersKey.ICE_SITY);
                backT = Atalas.worldBtn2;
                frontT = Atalas.worldBtn2;
                setAction(ButtonActions.All.WORLD2ACT);
                break;
            case 2:
                text = Localization.getText(Localization.LettersKey.SUMMER);
                backT = Atalas.worldBtn3;
                frontT = Atalas.worldBtn3;
                setAction(ButtonActions.All.WORLD3ACT);
                break;
            case 3:
                text = Localization.getText(Localization.LettersKey.RAINBOW);
                backT = Atalas.worldBtn4;
                frontT = Atalas.worldBtn4;
                setAction(ButtonActions.All.WORLD4ACT);
                break;
            case 4:
                text = Localization.getText(Localization.LettersKey.DARK_SIDE);
                backT = Atalas.worldBtn5;
                frontT = Atalas.worldBtn5;
                setAction(ButtonActions.All.WORLD5ACT);
                break;
        }

        lockedWorld = textureGen.getSprite(backT);
        setFirstTexture(textureGen.getSprite(backT));
        setSecondTexture(textureGen.getSprite(frontT));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setLockedWorld(enabled);
    }

    private void setLockedWorld(boolean enabled){
        setFirstTexture(enabled ? normalTex : lockedWorld);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

    }

    public void dispose() {

    }
}
