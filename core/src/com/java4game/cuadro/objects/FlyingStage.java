package com.java4game.cuadro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.DialogSystem;
import com.java4game.cuadro.core.uiwidgets.TextBlock;
import com.java4game.cuadro.core.uiwidgets.TypeGameButton;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.Localization;

/**
 * Created by FOGOK on 08.01.2017 2:23.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class FlyingStage {

    private Sprite glassCover;
    private TextBlock stageT, numberT;
    private FloatAnimator flyingAnimatorFrom, flyingAnimatorTo, flyingAnimatorToTo;

    private float size;
    private boolean isStage, show;

    public FlyingStage(boolean isStage) {
        this.isStage = isStage;
        size = 2.3f;

        show = false;

        flyingAnimatorFrom = new FloatAnimator(Gm.HEIGHT + size, Gm.HEIGHT / 2f, 1f, Interpolation.exp10Out);
        flyingAnimatorTo = new FloatAnimator(Gm.HEIGHT / 2f, -size, 1f, Interpolation.exp10In);
        flyingAnimatorTo.setTimer(0.5f);

        flyingAnimatorToTo = new FloatAnimator(Gm.WIDTH + size, Gm.WIDTH - size * 0.57f, 0.8f, Interpolation.exp10Out);

        glassCover = Assets.getNewSprite(30);
        glassCover.setSize(size * 1.524f, size);
        stageT = new TextBlock(0, 0, true, "");
        stageT.setCustomCff(size * 0.3f);

        numberT = new TextBlock(0, 0, true, "");
        numberT.setCustomCff(size * 0.3f);
    }

    public void setNew(int level, Color color, int mode){
        flyingAnimatorFrom.resetTime();
        flyingAnimatorTo.resetTime();
        flyingAnimatorToTo.resetTime();

        String text = "";

        if (DialogSystem.ISLEARNING){
            text = DialogSystem.LEARNING_PART == 0 ? Localization.getText(Localization.LettersKey.BASE) : Localization.getText(Localization.LettersKey.ARCADE);
            stageT.setText(text);
            stageT.setTextColor(color);

            numberT.setText(Localization.getText(Localization.LettersKey.MODE));
            numberT.setTextColor(color);
        }else{
            if (mode > 0){

                if (mode == 1){
                    switch (TypeGameButton.TOUCHED_ARK){
                        case 0:
                            text = Localization.getText(Localization.LettersKey.BRONZE);
                            break;
                        case 1:
                            text = Localization.getText(Localization.LettersKey.SILVER);
                            break;
                        case 2:
                            text = Localization.getText(Localization.LettersKey.GOLD);
                            break;
                    }
                }else{
                    switch (TypeGameButton.RNDLEVEL){
                        case 0:
                            text = "5X";
                            break;
                        case 1:
                            text = "10X";
                            break;
                        case 2:
                            text = "15X";
                            break;
                    }
                }
                stageT.setText(text);
                stageT.setTextColor(color);

                numberT.setText(mode == 1 ? Localization.getText(Localization.LettersKey.ARCADE) : Localization.getText(Localization.LettersKey.RANDOM));
                numberT.setTextColor(color);

                stageT.setCustomCff(size * 0.25f * Localization.getCff(Localization.CffsKey.BRONZESILVERGOLD));
                numberT.setCustomCff(size * 0.25f * Localization.getCff(Localization.CffsKey.BRONZESILVERGOLD));
            }else{
                stageT.setText(Localization.getText(Localization.LettersKey.STAGE));
                stageT.setTextColor(color);

                numberT.setText(level + "");
                numberT.setTextColor(color);
            }
        }


        setPositionX(Gm.WIDTH / 2f);
    }

    public void startT(String text1, String text2){
        show = true;
        flyingAnimatorFrom.resetTime();
        flyingAnimatorTo.resetTime();
        flyingAnimatorToTo.resetTime();

        stageT.setText(text1);
        numberT.setText(text2);

        setPositionX(Gm.WIDTH / 2f);
    }

    private void setPositionY(float y){
        glassCover.setPosition(glassCover.getX(), y - glassCover.getHeight() / 2f);
        refreshPosText();
    }

    private void setPositionX(float x){
        glassCover.setPosition(x - glassCover.getWidth() / 2f, glassCover.getY());
        refreshPosText();
    }

    private void refreshPosText(){
        stageT.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f + stageT.getBounds().getHeight() / 2f);
        stageT.setPositionToCenter();

        numberT.setPosition(glassCover.getX() + glassCover.getWidth() / 2f, glassCover.getY() + glassCover.getHeight() / 2f - stageT.getBounds().getHeight() * 0.8f);
        numberT.setPositionToCenter();
    }

    public void refreshRefresh(){
        flyingAnimatorTo.setNeedToUpdate(false);
    }

    public void handle(){
        if (show || isStage){
            if (flyingAnimatorTo.isNeedToUpdate()){
                if (flyingAnimatorFrom.isNeedToUpdate()){
                    flyingAnimatorFrom.update(Gdx.graphics.getDeltaTime());
                    setPositionY(flyingAnimatorFrom.current);
                }else{
                    flyingAnimatorTo.update(Gdx.graphics.getDeltaTime());
                    setPositionY(flyingAnimatorTo.current);
                }
            }else{
                if (isStage){
                    flyingAnimatorToTo.update(Gdx.graphics.getDeltaTime());
                    setPositionY(Gm.HEIGHT - size * 0.4f);
                    setPositionX(flyingAnimatorToTo.current);
                }else{
                    show = false;
                }
            }
        }
    }


    public boolean isFlying(){
        return flyingAnimatorTo.isNeedToUpdate();
    }

    public float getProgressEnd(){
        return flyingAnimatorToTo.getProgress();
    }

    public void drawGlass(SpriteBatch batch){
        if (show || isStage)
            glassCover.draw(batch);
    }

    public void drawText(SpriteBatch batch){
        if (show || isStage){
            stageT.draw(batch);
            numberT.draw(batch);
        }
    }

    private Interpolation exp10In = Interpolation.exp10Out, exp10Out = Interpolation.exp10In;
    public float getAlphaInNewRecordArkade(){
        float returnQ = 0f;
        if (show){
            if (flyingAnimatorFrom.isNeedToUpdate())
                returnQ = exp10In.apply(1f, 0f, flyingAnimatorFrom.getProgress() / 1f);
            else
                returnQ = exp10Out.apply(0f, 1f, flyingAnimatorTo.getProgress() / 1f);
        }else
            returnQ = 1f;
        return returnQ;
    }

    public boolean isShow() {
        return show;
    }

    public void dispose() {

    }
}
