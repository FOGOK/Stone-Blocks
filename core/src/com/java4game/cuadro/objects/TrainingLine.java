package com.java4game.cuadro.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.uiwidgets.BaseObject;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 02.05.2017 13:40.
 */

public class TrainingLine extends BaseObject {

    private final static float margin = 0.6f;

    private Sprite arkadeBlock;
    private TextBlock leftText, rightText;

    private boolean isNoIcon;
    private int type;

    public TrainingLine(float h, int type) {
        super(0f, 0f, Gm.WIDTH * 0.76f, h);
        this.type = type;
        isNoIcon = type == 0 || type == 14;

        leftText = new TextBlock(0f, 0f, false, "");

        if (isNoIcon){
            switch (type){
                case 0:
                    leftText.setCustomCff(h * 0.8f);
                    leftText.setText(Localization.getText(Localization.LettersKey.ARCMODE));
                    break;
                case 14:
                    leftText.setCustomCff(h * 0.5f * Localization.getCff(Localization.CffsKey.CTBCFT));
                    leftText.setText(Localization.getText(Localization.LettersKey.CTBCFT));
                    break;
            }
        }else{
            leftText.setCustomCff(h / 3f);
            rightText = new TextBlock(0f, 0f, false, "");
            rightText.setCustomCff(h / 3f);
            switch (type){
                case 1:
                    leftText.setText(Localization.getText(Localization.LettersKey.RED) + " " + Localization.getText(Localization.LettersKey.BLOCK));
                    rightText.setText("30 " + Localization.getText(Localization.LettersKey.PTS) + " + 2 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(9);
                    break;
                case 2:
                    leftText.setText(Localization.getText(Localization.LettersKey.BLUE) + " " + Localization.getText(Localization.LettersKey.BLOCK));
                    rightText.setText("25 " + Localization.getText(Localization.LettersKey.PTS) + " + 4 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(7);
                    break;
                case 3:
                    leftText.setText(Localization.getText(Localization.LettersKey.GREEN) + " " + Localization.getText(Localization.LettersKey.BLOCK));
                    rightText.setText("20 " + Localization.getText(Localization.LettersKey.PTS) + " + 6 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(8);
                    break;
                case 4:
                    leftText.setText(Localization.getText(Localization.LettersKey.YELLOW) + " " + Localization.getText(Localization.LettersKey.BLOCK));
                    rightText.setText("15 " + Localization.getText(Localization.LettersKey.PTS) + " + 8 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(11);
                    break;
                case 5:
                    leftText.setText(Localization.getText(Localization.LettersKey.WHITE) + " " + Localization.getText(Localization.LettersKey.BLOCK));
                    rightText.setText("10 " + Localization.getText(Localization.LettersKey.PTS) + " + 10 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(10);
                    break;
                case 6:
                    leftText.setText(Localization.getText(Localization.LettersKey.REVERS));
                    rightText.setText("1 " + Localization.getText(Localization.LettersKey.PTS));
                    arkadeBlock = Assets.getNewSprite(66);
                    break;
                case 7:
                    leftText.setText(Localization.getText(Localization.LettersKey.TELEPORT));
                    rightText.setText("2 " + Localization.getText(Localization.LettersKey.PTS));
                    arkadeBlock = Assets.getNewSprite(65);
                    break;
                case 8:
                    leftText.setText(Localization.getText(Localization.LettersKey.ROTATOR));
                    rightText.setText("3Х " + Localization.getText(Localization.LettersKey.PTS));
                    arkadeBlock = Assets.getNewSprite(68);
                    break;
                case 9:
                    leftText.setText(Localization.getText(Localization.LettersKey.SLOWER));
                    rightText.setText("5 " + Localization.getText(Localization.LettersKey.PTS));
                    arkadeBlock = Assets.getNewSprite(59);
                    break;
                case 10:
                    leftText.setText(Localization.getText(Localization.LettersKey.TURBO));
                    rightText.setText("2X " + Localization.getText(Localization.LettersKey.PTS));
                    arkadeBlock = Assets.getNewSprite(64);
                    break;
                case 11:
                    leftText.setText(Localization.getText(Localization.LettersKey.TIMEUP));
                    rightText.setText("+20 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(100);
                    break;
                case 12:
                    leftText.setText(Localization.getText(Localization.LettersKey.MOVER));
                    rightText.setText("100 " + Localization.getText(Localization.LettersKey.PTS) + " + 20 " + Localization.getText(Localization.LettersKey.SEC));
                    arkadeBlock = Assets.getNewSprite(101);
                    break;
                case 13:
                    leftText.setText(Localization.getText(Localization.LettersKey.BOMB));
                    rightText.setText(Localization.getText(Localization.LettersKey.GAMEOVER));
                    arkadeBlock = Assets.getNewSprite(60);
                    break;
            }
            arkadeBlock.setSize(h, h);
        }

    }

    @Override
    public void draw(SpriteBatch batch) {
        refreshPosition();
        leftText.draw(batch);
        if (!isNoIcon) {
            arkadeBlock.draw(batch);
            rightText.draw(batch);
        }
    }

    private void refreshPosition(){
        if (!isNoIcon){
            arkadeBlock.setCenterX(Gm.WIDTH / 2f);
            arkadeBlock.setY(getBounds().getY());

            leftText.setPosition(arkadeBlock.getX() - margin - leftText.getBounds().getWidth() / 2f, arkadeBlock.getY() + arkadeBlock.getHeight() / 2f);
            leftText.setPositionToCenter();

            rightText.setPosition(arkadeBlock.getX() + arkadeBlock.getWidth() + margin + rightText.getBounds().getWidth() / 2f, arkadeBlock.getY() + arkadeBlock.getHeight() / 2f);
            rightText.setPositionToCenter();
        }else{
            if (type == 0)
                leftText.setPosition(Gm.WIDTH / 2f, getBounds().getY() + getBounds().getHeight() / 2f);
            else
                leftText.setPosition(Gm.WIDTH / 2f, getBounds().getY() + leftText.getBounds().getHeight() / 2f);
            leftText.setPositionToCenter();
        }
    }

}
