package com.java4game.cuadro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.utils.DebugValueChanger;

public class Gm extends ApplicationAdapter {

	SpriteBatch batch;
    OrthographicCamera camera;
    Handler handler;
    SpriteBatch debugBatch;
    BitmapFont bf;
    public static String DEBUG_VALUE1 = "", DEBUG_VALUE2 = "";


    DebugValueChanger debugValueChanger;

	
	@Override
	public void create () {
        //initNatives
        batch = new SpriteBatch();
        initCamera();
        ///

        //initDebug
        bf = new BitmapFont();
        bf.setColor(Color.BLACK);
        debugBatch = new SpriteBatch();
        debugValueChanger = new DebugValueChanger(1, debugBatch);
        ///

        //initAll Game
        handler = new Handler();
        ///
	}

    public static float aspectR, WIDTH, HEIGHT, mdT;     ///размеры экрана статичны (высота всегда равна 20, ширина 20 * отнощение ширины на высоту, mdT - delta / 0.016 = ~1
    private void initCamera(){
        aspectR =  (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera(20f * aspectR, 20f);
        camera.position.set(new Vector3(20f * aspectR / 2f, 10f, 0f));
        WIDTH = camera.viewportWidth;
        HEIGHT = camera.viewportHeight;
    }

	@Override
	public void render () {
        //natives
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        ///


        handler.draw(batch);


        //natives
        batch.end();
        mdT = Math.min(Gdx.graphics.getDeltaTime() / 0.016f, 2f);
        ///

        //DEBUG
        if (textShow)
            debugMethod1();


        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            isSleep = !isSleep;

        if (Gdx.input.isKeyJustPressed(Input.Keys.T))
            textShow = !textShow;

        if (isSleep)
            mdT = Math.min(Gdx.graphics.getDeltaTime() / 0.016f, 2f);
        else
            sleep(1);
        ///
    }

    boolean isSleep = true, textShow = true;

    private long diff, start = System.currentTimeMillis();
    public void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();
        }
    }

    private void debugMethod1(){
        debugBatch.begin();
        String mmm = (!isSleep) ? "IS SLEEEEEEEPING" : "";
        bf.draw(debugBatch, "LOLOG    " + mmm + "\n-------------\n" +
                        "KEYSETS: SLEEP - 'S',\nDebugValueChanger - 'C',\nEnable text - 'T' " + "\n" +
                        "FPS:" + Gdx.graphics.getFramesPerSecond() + "\n" +
                        DEBUG_VALUE1 + "\n" +
                        DEBUG_VALUE2,

                20, Gdx.graphics.getHeight() - 20);

        debugBatch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.C))
            showDebugValChanger = !showDebugValChanger;

        if (showDebugValChanger)
            debugValueChanger.draw();
    }
    boolean showDebugValChanger = false;
	
	@Override
	public void dispose () {
		batch.dispose();
        debugBatch.dispose();
        handler.dispose();
        debugValueChanger.dispose();
	}
}
