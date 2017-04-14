package com.darkfig.plasmalivewallpaper;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.darkfig.plasmalivewallpaper.TestScreen;

public class MyWallpaperGame extends Game {
	protected SettingsListener settings;
 	protected SpriteBatch spriteBatch;
	protected ModelBatch modelBatch;
	protected ShaderProgram plasmaShader;
	protected int u_projTrans;
	protected int u_time;
	protected int u_strength;
	protected int u_brightness;
	protected int u_centerx;
	protected int u_centery;
	protected int u_scale;
	protected int u_red;
	protected int u_green;
	protected int u_blue;

	public MyWallpaperGame(SettingsListener settings) {
		this.settings = settings;
	}

	@Override
	public void create() {

		spriteBatch = new SpriteBatch();
		modelBatch = new ModelBatch();
		plasmaShader = new ShaderProgram(Gdx.files.getFileHandle("shaders/plasma.vert", Files.FileType.Internal),
				Gdx.files.getFileHandle("shaders/plasma.frag",Files.FileType.Internal));
		u_projTrans = plasmaShader.getUniformLocation("u_projModelView");
		u_time = plasmaShader.getUniformLocation("u_time");
		u_strength = plasmaShader.getUniformLocation("u_strength");
		u_brightness = plasmaShader.getUniformLocation("u_brightness");
		u_centerx = plasmaShader.getUniformLocation("u_centerx");
		u_centery = plasmaShader.getUniformLocation("u_centery");
		u_scale = plasmaShader.getUniformLocation("u_scale");
		u_red = plasmaShader.getUniformLocation("u_red");
		u_green = plasmaShader.getUniformLocation("u_green");
		u_blue = plasmaShader.getUniformLocation("u_blue");
		Screen screen = new TestScreen(this);
		setScreen(screen);
	}
}
