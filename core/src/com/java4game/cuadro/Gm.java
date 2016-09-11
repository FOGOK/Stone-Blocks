package com.java4game.cuadro;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.java4game.cuadro.core.Handler;

public class Gm extends ApplicationAdapter {



	SpriteBatch batch;
    OrthographicCamera camera;
    Handler handler;



	
	@Override
	public void create () {
        //initNatives
        batch = new SpriteBatch();
        initCamera();
        ///

        //initAll Game
        handler = new Handler();
        ///
	}

    public static float aspectR, WIDTH, HEIGHT;     ///размеры экрана статичны (высота всегда равна 20, ширина 20 * отнощение ширины на высоту
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
        ///
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        handler.dispose();
	}
}
