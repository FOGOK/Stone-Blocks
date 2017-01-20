package com.java4game.cuadro.core.uiwidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.java4game.cuadro.Gm;
import com.java4game.cuadro.core.TextureGen;
import com.java4game.cuadro.utils.PosF;

/**
 * Created by FOGOK on 03.11.2016 13:45.
 * Если ты это читаешь, то знай, что этот код хуже
 * кожи разлагающегося бомжа лежащего на гнилой
 * лавочке возле остановки автобуса номер 985
 */
public class List extends BaseObject {

    private BaseObject[][] objects;    ///все объекты внутри листа
    private PosF[][] positions;    ///все объекты внутри листа

    private int columns, rows;

    private float currentPos;
    private float abstSizeList;

    //proporties
    private float padding = 0f;
    private float paddingTop = 0f;
    private float paddingBottom = 0.5f;

    ///

    public List(float x, float y, float w, float h, int columns, int rows) {
        super(x, y, w, h);
        this.columns = columns;
        this.rows = rows;
        objects = new BaseObject[columns][rows];
        positions = new PosF[columns][rows];
        setBoundsToChControls();
    }
    private void setBoundsToChControls(){
        posX = (int) (bounds.x * (Gdx.graphics.getWidth() / Gm.WIDTH));
        posY = (int) (bounds.y * (Gdx.graphics.getHeight() / Gm.HEIGHT));
        w = (int) (bounds.width * (Gdx.graphics.getWidth() / Gm.WIDTH));
        h = (int) (bounds.height * (Gdx.graphics.getHeight() / Gm.HEIGHT));
    }

    public void set(final BaseObject object, final int column, final int row){
        objects[column][row] = object;
    }

    public void setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void calculatePadding(int targetObjectColumn, int targetObjectRow){
        padding = (bounds.width - objects[targetObjectColumn][targetObjectRow].getBounds().width * columns) / (float) (columns - 1);
        setPositionAllObjects();
    }


    @Override
    public void draw(final SpriteBatch batch) {
        matchControl();
        drawObjects(batch);
    }

    private void drawObjects(final SpriteBatch batch){
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                float x = positions[column][row].x, y = positions[column][row].y + currentPos;
                if (y + objects[column][row].getBounds().getHeight() > bounds.y && y < bounds.y + bounds.height){
                    objects[column][row].setPosition(x, y);
                    objects[column][row].draw(batch); //TODO: сделать тут проверку на разные объекты, может быть вызвано исключение
                    if (!((StageButton)objects[column][row]).isLockedStage())
                        ((StageButton)objects[column][row]).setEnabled(!moveSp && isTouchedList);
                }
            }
        }
    }

    public void setToCenter(int row){
        final float sizeOneO = (padding + objects[0][0].getBounds().height);
        final float abstDYY = sizeOneO * row + paddingTop - bounds.height / 2f + sizeOneO / 2f;

        dYY = 0;
        if (abstDYY > 0f && abstDYY < Math.abs(abstSizeList))
            dYY = (int) (abstDYY * (Gdx.graphics.getHeight() / Gm.HEIGHT));
        else if (abstDYY >= Math.abs(abstSizeList))
            dYY = sizeSpisAll;

        currentPos = dYY * (Gm.HEIGHT / Gdx.graphics.getHeight());
        lastDYY = dYY;
    }

    private void setPositionAllObjects(){
        float x = 0f, y = -paddingTop - objects[0][0].getBounds().height, maxHeight = 0f;
        Rectangle currentRect;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                positions[column][row] = new PosF(bounds.x + x, bounds.y + bounds.height + y);
                currentRect = objects[column][row].getBounds();
                x += currentRect.width;
                if (column != columns - 1)
                    x += padding;
                maxHeight = (currentRect.getHeight() > maxHeight) ? currentRect.getHeight() : maxHeight;
            }
            y -= maxHeight;
            if (row != rows - 1)
                y -= padding;
            else{
                abstSizeList = y + bounds.height + maxHeight - paddingBottom;
                sizeSpisAll = Math.abs((int) (abstSizeList * (Gdx.graphics.getHeight() / Gm.HEIGHT)));
            }

            maxHeight = 0f;
            x = 0f;
        }
    }

    private boolean onch = false;
    private int startY;
    private float deltYYY;
    private boolean moveSp = false;
    private int dYY = 0, dYYd = 0, lastDYY = 0;
    private int posX, posY, w, h, sizeSpisAll;
    private boolean isTouchedList;
    private void matchControl() {
        isTouchedList = Gdx.input.isTouched() && Gdx.input.getX() > posX &&
                Gdx.input.getX() < posX + w &&
                Gdx.graphics.getHeight() - Gdx.input.getY() > posY + paddingBottom * (Gdx.graphics.getHeight() / Gm.HEIGHT) &&
                Gdx.graphics.getHeight() - Gdx.input.getY() < posY + h;
        if (isTouchedList){

            if (!onch) {
                onch = true;
                startY = Gdx.input.getY();
            }

            dYYd = startY - Gdx.input.getY();

            if (Math.abs(dYYd) > 6)
                moveSp = true;


            if (moveSp){
                if (lastDYY + dYYd > 0 && lastDYY + dYYd < sizeSpisAll){
                    dYY = lastDYY + dYYd;
                    deltYYY = Gdx.input.getDeltaY() * -1f;
                }
                else if (lastDYY + dYYd < 0){
                    dYY = 0;
                    deltYYY = 0f;
                }
                else if (lastDYY + dYYd > sizeSpisAll){
                    dYY = sizeSpisAll;
                    deltYYY = 0f;
                }
            }
        }else{
            moveSp = false;
            onch = false;
            dYYd = 0;
            deltYYY *= 0.8f;
            if (Math.abs(deltYYY) > 0.1f){
                dYY = dYY + (int) deltYYY;
            }

            lastDYY = dYY;

            if (dYY < 0){
                dYY *= -1;
                deltYYY = -1;
            }

            if (dYY > sizeSpisAll) {
                dYY = sizeSpisAll;
                deltYYY *= -1;
            }
        }

        currentPos = dYY * (Gm.HEIGHT / Gdx.graphics.getHeight());
    }

    public void dispose() {

    }
}
