package com.darkfig.plasmalivewallpaper;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;


public class LiveWallpaper extends AndroidLiveWallpaperService {
    private MyWallpaperGame applicationListener;
	@Override
	public void onCreateApplication() {
		super.onCreateApplication();
		SettingsManager.loadPreferences(getApplicationContext());
        applicationListener = new MyWallpaperGame(SettingsManager.settings);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.getTouchEventsForLiveWallpaper = true;
		cfg.useAccelerometer = true;
        initialize(applicationListener, cfg);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
