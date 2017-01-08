package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.java4game.cuadro.Gm;

/**
 * Created by FOGOK on 29.12.2016 6:02.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class CustomFormButton extends BaseButton {

    private Polygon cleanForm, form, dot;

    public CustomFormButton(ButtonsForms whoForm, ButtonActions.All action, float x, float y, float h, int back, int front) {
        super(action, x, y, h, back, front);
        form = new Polygon();
        cleanForm = new Polygon();

        final float sizeH = Gm.HEIGHT / Gdx.graphics.getHeight();
        dot = new Polygon(new float[] {0f, 0f, sizeH, 0f, sizeH, sizeH, 0f, sizeH});
        switch (whoForm){
            case infoBForm:
                cleanForm.setVertices(infoBFormVerts);
                break;
            case otherBForm:
                cleanForm.setVertices(otherBFormVerts);
                break;
            case playBForm:
                cleanForm.setVertices(playBFormVerts);
                break;
            case quesBForm:
                cleanForm.setVertices(quesBFormVerts);
                break;
        }
        correctFormSize();
        form.setPosition(x, y);
    }

    private void correctFormSize(){
        form.setVertices(cleanForm.getVertices().clone());
        for (int i = 0; i < form.getVertices().length; i++) {
            if (i % 2 == 0){     //x
                form.getVertices()[i] = cleanForm.getVertices()[i] * bounds.width;
            }else{      //y
                form.getVertices()[i] = cleanForm.getVertices()[i] * bounds.height;
            }
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        form.setPosition(x, y);
    }

    @Override
    public void setBounds(float x, float y, float w, float h) {
        super.setBounds(x, y, w, h);
        if (form != null){
            correctFormSize();
            form.setPosition(x, y);
        }
    }

    @Override
    public void setPositionToCenter() {
        super.setPositionToCenter();
        form.setPosition(bounds.getX(), bounds.getY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
//        batch.end();
//        shapeRenderer.setProjectionMatrix(Gm.getCamera().combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.polygon(form.getTransformedVertices());
//        shapeRenderer.end();
//        batch.begin();
    }

    @Override
    protected void calcM() {
        if (isEnabled){
            if (Gdx.input.isTouched()){ //если на экран нажимает палец
                dot.setPosition(
                        (Gm.WIDTH / Gdx.graphics.getWidth()) * Gdx.input.getX(),
                        (Gm.HEIGHT / Gdx.graphics.getHeight()) * (Gdx.graphics.getHeight() - Gdx.input.getY()));
                isTouched = Intersector.overlapConvexPolygons(form, dot);  ///определяем,  касается ли палец кнопки или нет

            }else {              //при отпускании кнопки
                if (isTouched)      ///если при отпускании кнопки палец находился на кнопке, то выполняем действие
                    ButtonActions.activateAction(action);

                isTouched = false;  //делаем так, чтобы действие не выполнилось ещё раз
            }
        }else
            isTouched = false;
    }

    public enum ButtonsForms{
        playBForm, quesBForm, infoBForm, otherBForm
    }

    private static final float playBFormVerts[] = new float[]{
            //x, y
            0.123f, 0.088f,
            0.708f, 0.036f,
            0.897f, 0.119f,
            0.969f, 0.851f,
            0.923f, 0.902f,
            0.215f, 0.969f,
            0.036f, 0.753f
    };

    private static final float quesBFormVerts[] = new float[]{
            //x, y
            0.076f, 0.364f,
            0.600f, 0.064f,
            0.943f, 0.427f,
            0.714f, 0.936f,
            0.114f, 0.891f,
    };

    private static final float infoBFormVerts[] = new float[]{
            //x, y
            0.099f, 0.409f,
            0.440f, 0.080f,
            0.934f, 0.364f,
            0.857f, 0.841f,
            0.527f, 0.932f,
            0.132f, 0.750f
    };

    private static final float otherBFormVerts[] = new float[]{
            //x, y
            0.233f, 0.146f,
            0.325f, 0.104f,
            0.822f, 0.195f,
            0.865f, 0.238f,
            0.969f, 0.841f,
            0.915f, 0.915f,
            0.166f, 0.957f,
            0.043f, 0.817f
    };

}
