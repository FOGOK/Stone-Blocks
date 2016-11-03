package com.java4game.cuadro.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.java4game.cuadro.utils.Atalas;
import com.java4game.cuadro.utils.D;
import com.java4game.cuadro.utils.FloatAnimator;
import com.java4game.cuadro.utils.PosF;

/**
 * Created by FOGOK on 10.10.2016 16:37.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class ComboVombo {

    final static float WIDTH_COMBOTEXT = 6;
    final static float HEIGHT_COMBOTEXT = WIDTH_COMBOTEXT * 0.286f;

    final static float SIZE_COMBONUM = HEIGHT_COMBOTEXT;

    public static int comboC;

    final int MAX_COMBO_OBJECTS = 9;
    OneComboObject[] combosObjects = new OneComboObject[MAX_COMBO_OBJECTS];
    class OneComboObject{
        private PosF position;      ///позиция комбо
        private int whoCombo;   ///какое число отображать
        private FloatAnimator floatAnimator;    //для анимации
        boolean isExist;    ///активно ли
        boolean triggerAlpha;

        private Color color;

        ///
        private float alpha;
        private final float ALPHA_SPEED_CHANGE = 4f;
        ///

        ///
        private float currentIt;
        private final float SECOND_POST = 1.6f; ///количество секунд, которое держится надпись после анимации
        ///

        public OneComboObject(PosF position, int whoCombo, Color color){
            set(position, whoCombo, color);
        }


        public void set(PosF position, int whoCombo, Color color){
            this.position = position;
            this.whoCombo = whoCombo;
            this.color = color;
            floatAnimator = new FloatAnimator(0f, 1f, 0.4f, Interpolation.swingOut);
            isExist = whoCombo >= 0;    //если комбо меньше 0, значит мы первоначально инициализируем переменную, иначе делаем сразу её активной

            triggerAlpha = false;
            currentIt = 0f;
            alpha = 0f;
        }



        protected void calculateCff(){
            if (!Handler.ISPAUSE) {
                floatAnimator.update(Gdx.graphics.getDeltaTime());

                if (!floatAnimator.isNeedToUpdate())
                    currentIt += Gdx.graphics.getDeltaTime();

                if (alpha < 1f && currentIt < SECOND_POST && !triggerAlpha)
                    alpha += Gdx.graphics.getDeltaTime() * ALPHA_SPEED_CHANGE;




                if (currentIt > SECOND_POST){
                    alpha -= Gdx.graphics.getDeltaTime() * (ALPHA_SPEED_CHANGE * 1f);
                    if (!floatAnimator.isNeedToUpdate() && alpha > 0f) {
                        floatAnimator.resetTime();
                        floatAnimator.setFrom(1f);
                        floatAnimator.setTo(0f);
                        floatAnimator.setAnimationTime(0.3f);
                        floatAnimator.setInterpolation(Interpolation.fade);
                    }
                }

                if (alpha > 1f){
                    alpha = 1f;
                    triggerAlpha = true;
                }

                if (alpha < 0f)
                    alpha = 0f;
            }

            if (!floatAnimator.isNeedToUpdate() && currentIt > SECOND_POST && alpha == 0f){
                isExist = false;
            }


        }

        protected void setColor(Color color){
            this.color = color;
        }

        protected void downComboC(){
            alpha -= 0.05f;
        }

        protected float getAlpha(){
            return alpha;
        }

        protected float getCurrentUpdate(){
            return floatAnimator.current;
        }

        protected float getX(){
            return position.x;
        }

        protected float getY(){
            return position.y;
        }

        protected int getCombo(){
            return whoCombo;
        }

        protected Color getColor(){
            return color;
        }

        protected int getNextCombo(){
            return whoCombo + 1;
        }
    }



    Sprite comboText;
    Sprite[] numbCombos = new Sprite[10];
    public ComboVombo(TextureGen textureGen) {
        comboText = textureGen.getSprite(Atalas.comboText);
        for (int i = 0; i < numbCombos.length; i++){
            numbCombos[i] = new Sprite(textureGen.getSprite(31 + i));
            numbCombos[i].setSize(SIZE_COMBONUM, SIZE_COMBONUM);
        }
        comboText.setSize(WIDTH_COMBOTEXT, HEIGHT_COMBOTEXT);

        for (int i = 0; i < MAX_COMBO_OBJECTS; i++) {
            combosObjects[i] = new OneComboObject(new PosF(0f, 0f), -1, Color.BLACK);
        }


        comboC = 1;


    }

    public void needClear(){
        boolean need = true;
        for (int i = 0; i < MAX_COMBO_OBJECTS; i++) {
            if (combosObjects[i].isExist){
                need = false;
                break;
            }
        }

        if (need && comboC > 1){
            comboC = 1;
            nextTwo = false;
        }

    }

    public float getW(){
        return WIDTH_COMBOTEXT;
    }

    public float getH(){
        return HEIGHT_COMBOTEXT + SIZE_COMBONUM + 0.4f;
    }

    boolean nextTwo = false;
    public void PUSHCOMBOOOOO(float posX, float posY, Color color){
        if (nextTwo){
            PUSHCOMBOOOOO(posX, posY, comboC, color);
        }else{
            nextTwo = true;
        }
    }

    public void PUSHCOMBOOOOO(float posX, float posY, int comboX, Color color){
        if (comboX < 1 || comboX > MAX_COMBO_OBJECTS) throw new IllegalArgumentException("1 <= comboX <= " + MAX_COMBO_OBJECTS);


        for (int i = 0; i < MAX_COMBO_OBJECTS; i++) {
            if (combosObjects[i].isExist)
                combosObjects[i].setColor(Color.GRAY);

        }

        combosObjects[comboC - 1].set(new PosF(posX, posY), comboX, color);
        comboC++;




    }

    public void draw(SpriteBatch batch){
        for (int i = 0; i < MAX_COMBO_OBJECTS; i++) {
            if (combosObjects[i].isExist)
                drawOneCombo(i, batch);
        }

        needClear();
    }

    private void drawOneCombo(int i, SpriteBatch batch){
        OneComboObject oneComboObject = combosObjects[i];

        comboText.setAlpha(oneComboObject.getAlpha());
        comboText.setOriginCenter();
        comboText.setPosition(oneComboObject.getX() - comboText.getWidth() / 2f, oneComboObject.getY() - 0.2f);
        comboText.setScale(oneComboObject.getCurrentUpdate());
        int whDegr = (oneComboObject.getCombo() % 2 == 0) ? 340 : 370;
        comboText.setRotation((oneComboObject.getAlpha() * whDegr));
        comboText.draw(batch);

        Sprite numbCombo = numbCombos[oneComboObject.getNextCombo()];

        numbCombo.setAlpha(oneComboObject.getAlpha());
        numbCombo.setOriginCenter();
        numbCombo.setPosition(oneComboObject.getX() - numbCombo.getWidth() / 2f, oneComboObject.getY() + 0.2f - SIZE_COMBONUM);
        numbCombo.setScale(oneComboObject.getCurrentUpdate());
        numbCombo.setRotation((oneComboObject.getAlpha() * 360));
        numbCombo.setColor(oneComboObject.getColor().toFloatBits());
        numbCombo.draw(batch);

        oneComboObject.calculateCff();
    }


    public void dispose() {

    }
}
