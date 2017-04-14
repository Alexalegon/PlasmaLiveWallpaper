package com.darkfig.plasmalivewallpaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.darkfig.plasmalivewallpaper.android.R;

/**
 * Created by Miguel Martrinez on 1/16/2017.
 */

public class WallpaperApp extends AppCompatActivity {
    private Button pickerButton;
    private Button settingsButton;
    private Button webButton;
    private Toolbar toolBar;
    private AdListener pickerAdListener;
    private AdListener settingsAdListener;
    private AdListener webAdListener;
    private InterstitialAd interstitialAd;
    private static final String homeUrl = "https://play.google.com/store/apps/developer?id=DarkFig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_layout);
        toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolBar);
        initAdListeners();
        initButtons();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad));
        requestNewInterstitial();
    }

    private void initAdListeners(){
        pickerAdListener = new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                goToWallpaperPreview();
                requestNewInterstitial();
            }
        };
        settingsAdListener = new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                goToWallpaperSettings();
                requestNewInterstitial();
            }
        };
        webAdListener = new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                goToWeb();
                requestNewInterstitial();
            }
        };
    }


    private void initButtons(){
        pickerButton = (Button) findViewById(R.id.picker);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.setAdListener(pickerAdListener);
                if(interstitialAd.isLoaded())
                    interstitialAd.show();
                else{
                    goToWallpaperPreview();
                    requestNewInterstitial();
                }
            }
        });
        settingsButton = (Button) findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.setAdListener(settingsAdListener);
                if(interstitialAd.isLoaded())
                    interstitialAd.show();
                else{
                    goToWallpaperSettings();
                    requestNewInterstitial();
                }
            }
        });
        webButton = (Button) findViewById(R.id.web);
        webButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                interstitialAd.setAdListener(webAdListener);
                if(interstitialAd.isLoaded())
                    interstitialAd.show();
                else{
                    goToWeb();
                    requestNewInterstitial();
                }
            }
        });
    }

    private void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }

    private void goToWallpaperPreview(){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    new ComponentName(WallpaperApp.this, LiveWallpaper.class));
        }
        else
            intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);

        startActivity(intent);
    }

    private void goToWallpaperSettings(){
        Intent intent = new Intent(WallpaperApp.this,WallpaperSettings.class);
        startActivity(intent);
    }

    private void goToWeb(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(homeUrl));
        startActivity(intent);
    }
}
