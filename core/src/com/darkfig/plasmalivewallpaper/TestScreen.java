package com.darkfig.plasmalivewallpaper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Miguel Martrinez on 2/3/2017.
 */

public class TestScreen implements Screen,GestureDetector.GestureListener{
    private MyWallpaperGame game;
    private Camera camera;
    private Sprite sprite;
    private Mesh mesh;
    long startTime;

    public TestScreen(Game game) {
        this.game = (MyWallpaperGame) game;
        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        sprite = new Sprite();
        mesh = MeshFactory.createQuadMesh(20,20);
        mesh.scale(1f,1f,1f);
        startTime = System.currentTimeMillis();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0f,0f,1f,1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.plasmaShader.begin();
        game.plasmaShader.setUniformMatrix(game.u_projTrans, game.spriteBatch.getProjectionMatrix());
        game.plasmaShader.setUniformf(game.u_strength,game.settings.getX()/100);
        game.plasmaShader.setUniformf(game.u_brightness,game.settings.getY()/200);
        game.plasmaShader.setUniformf(game.u_scale,game.settings.getDist());
        game.plasmaShader.setUniformf(game.u_centerx,game.settings.getCenterx()/10);
        game.plasmaShader.setUniformf(game.u_centery,game.settings.getCentery()/10);
        game.plasmaShader.setUniformf(game.u_red,game.settings.getRed()/100f);
        game.plasmaShader.setUniformf(game.u_green,game.settings.getGreen()/100f);
        game.plasmaShader.setUniformf(game.u_blue,game.settings.getBlue()/100f);
        float time = (float)((System.currentTimeMillis()-startTime)/1000.0);
        if(time >= 12f * MathUtils.PI)
            startTime = System.currentTimeMillis();
        game.plasmaShader.setUniformf(game.u_time,(time));
        //System.out.println("time    " + time);
        mesh.render(game.plasmaShader, GL20.GL_TRIANGLE_STRIP);
        game.plasmaShader.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    mesh.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
