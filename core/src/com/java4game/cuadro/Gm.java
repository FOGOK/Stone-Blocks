package com.java4game.cuadro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.java4game.cuadro.core.Handler;
import com.java4game.cuadro.core.InitLevels;
import com.java4game.cuadro.core.MusicCore;
import com.java4game.cuadro.core.usie.MenuUI;
import com.java4game.cuadro.core.usie.UI;
import com.java4game.cuadro.utils.Assets;
import com.java4game.cuadro.utils.DebugDrawer;
import com.java4game.cuadro.utils.DebugValueChanger;
import com.java4game.cuadro.utils.GameUtils;
import com.java4game.cuadro.utils.Prefers;

public class Gm extends ApplicationAdapter {

    /**THIS VARS IN DEBUG**/
    private boolean isNoSleep = true, textShow = false, showDebugValChanger = false;
    private SpriteBatch debugBatch;
    private BitmapFont bf;
    public static String DEBUG_VALUE1 = "", DEBUG_VALUE2 = "";
    private DebugValueChanger debugValueChanger;
    private DebugDrawer debugDrawer;
    /***/

    private Texture startTexture;

    private boolean isGameInit, isFirstIter;

	private SpriteBatch batch;
    static OrthographicCamera camera;
    private Handler handler;
	
	@Override
	public void create () {
        //initNatives
        batch = new SpriteBatch();
        initCamera();
        startTexture = new Texture(Gdx.files.internal("start.png"));
        startTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        isFirstIter = isGameInit = false;
        ///
	}

    private void initGame(){
        Prefers.initPrefs();
        setStagesOpened();
        ///

        //initDebug
        bf = new BitmapFont();
        bf.setColor(Color.BLACK);
        debugBatch = new SpriteBatch();
        debugValueChanger = new DebugValueChanger(3, debugBatch);
        debugDrawer = new DebugDrawer();
        ///

        //initAll Game
        Assets.init();
        MusicCore.init();
        GameUtils.initializate();
        UI.initializate();

        handler = new Handler();
    }

    private void setStagesOpened(){
        if (Prefers.getInt(Prefers.KeyOpenedStagesSteps) == 0){
            Prefers.putInt(Prefers.KeyOpenedStagesSteps, 1);  //103
            Prefers.putInt(Prefers.KeyOpenedStagesTimed, 1);   //22

            char[] chars = new char[MenuUI.COUNTSTAGESINWORLD[0]];
            for (int i = 0; i < MenuUI.COUNTSTAGESINWORLD[0]; i++) {
                chars[i] = '3';
            }
            Prefers.putString(Prefers.KeyStarsSteps, new String(chars));

            chars = new char[MenuUI.COUNTSTAGESINWORLD[1]];
            for (int i = 0; i < MenuUI.COUNTSTAGESINWORLD[1]; i++) {
                chars[i] = '3';
            }
            Prefers.putString(Prefers.KeyStarsTimed, new String(chars));
        }
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
        if (isGameInit)
            Gdx.gl.glClearColor(0, 0, 0, 1);
        else
            Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        ///

        if (isGameInit){
            MusicCore.handleVolumeAndTransition();
            handler.draw(batch);
        }else{
            float sizeW = Gm.WIDTH * 0.8f;
            float sizeH = sizeW * 1.095f;
            batch.draw(startTexture, (Gm.WIDTH - sizeW) / 2f, (Gm.HEIGHT - sizeH) / 2f, sizeW, sizeH);
            if (!isFirstIter)
                isFirstIter = true;
            else{
                initGame();
                isGameInit = true;
            }


        }


        //natives
        batch.end();
        mdT = Math.min(Gdx.graphics.getDeltaTime() / 0.016f, 2f);
        ///

        //DEBUG
        if (textShow)
            debugMethod1();


        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            isNoSleep = !isNoSleep;

        if (Gdx.input.isKeyJustPressed(Input.Keys.T))
            textShow = !textShow;

        if (isNoSleep)
            mdT = Math.min(Gdx.graphics.getDeltaTime() / 0.016f, 2f);
        else
            sleep(1);
        ///
    }

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
        String mmm = (!isNoSleep) ? "IS SLEEEEEEEPING" : "";
        bf.draw(debugBatch, "LOLOG    " + mmm + "\n-------------\n" +
                        "KEYSETS: SLEEP - 'S',\nDebugValueChanger - 'C',\nEnable text - 'T' " + "\n" +
                        "FPS:" + Gdx.graphics.getFramesPerSecond() + "\n" +
                        DEBUG_VALUE1 + "\n" +
                        DEBUG_VALUE2,

                20, Gdx.graphics.getHeight() - 20);

        debugBatch.end();

        DebugDrawer.drawRect(batch, true);

        if (Gdx.input.isKeyJustPressed(Input.Keys.C))
            showDebugValChanger = !showDebugValChanger;

        if (showDebugValChanger)
            debugValueChanger.draw();
    }

    public static OrthographicCamera getCamera(){
        return camera;
    }

	
	@Override
	public void dispose () {
        UI.dispose();
        GameUtils.dispose();
        batch.dispose();
        debugBatch.dispose();
        handler.dispose();
        debugValueChanger.dispose();
        debugDrawer.dispose();
        MusicCore.dispose();
        Assets.dispose();
        startTexture.dispose();
        InitLevels.dispose();


        super.dispose();
	}
}
