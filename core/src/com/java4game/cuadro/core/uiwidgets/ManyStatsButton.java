package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.java4game.cuadro.utils.Assets;

/**
 * Created by FOGOK on 28.03.2017 22:26.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ManyStatsButton extends BaseButton {

    private Sprite stats[];

    private int currentStat, allStats;

    public ManyStatsButton(ButtonActions.All action, float x, float y, float h, int back) {
        super(action, x, y, h, back, back);
    }

    public void initStats(int start, int end){
        stats = new Sprite[end - start + 1];
        int st = 0;
        for (int i = start; i < end + 1; i++) {
            stats[st++] = Assets.getNewSprite(i);
        }
        allStats = stats.length;
    }

    public void setCurrentStat(int currentStat) {
        this.currentStat = currentStat;
        setFirstTexture(stats[currentStat]);
        setSecondTexture(stats[currentStat]);
    }

    @Override
    protected void activateAction() {
        super.activateAction();
        currentStat++;
        if (currentStat > allStats - 1)
            currentStat = 0;
        setFirstTexture(stats[currentStat]);
        setSecondTexture(stats[currentStat]);
    }
}
