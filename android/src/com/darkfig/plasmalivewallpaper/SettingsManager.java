package com.darkfig.plasmalivewallpaper;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Miguel Martrinez on 4/5/2017.
 */

public class SettingsManager {
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor preferenceEditor;
    public static SettingsListener settings = new SettingsListener();
    public static void loadPreferences(Context context){
        preferences = context.getSharedPreferences("myPreferences",context.MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                matchPreferencesToSettings(s);
            }
        });
        preferenceEditor = preferences.edit();
        for(Modifier modifier: Modifier.values()){
            matchPreferencesToSettings(modifier.getTitle());
        }
        loadPreferencesToModifiers();
    }

    public static void matchPreferencesToSettings(String s){
        if(s.equalsIgnoreCase(Modifier.STRENGTHMODIFIER.getTitle())) {
            settings.setX(preferences.getFloat(s, Modifier.STRENGTHMODIFIER.getValue()));

        }
        else if(s.equalsIgnoreCase(Modifier.BRIGHTNESSMODIFIER.getTitle()))
            settings.setY(preferences.getFloat(s,Modifier.BRIGHTNESSMODIFIER.getValue()));
        else if(s.equalsIgnoreCase(Modifier.CENTERXMODIFIER.getTitle()))
            settings.setCenterX(preferences.getFloat(s,Modifier.CENTERXMODIFIER.getValue()));
        else if(s.equalsIgnoreCase(Modifier.CENTERYMODIFIER.getTitle()))
            settings.setCenterY(preferences.getFloat(s,Modifier.CENTERYMODIFIER.getValue()));
        else if(s.equalsIgnoreCase(Modifier.SCALEMODIFIER.getTitle()))
            settings.setDist(preferences.getFloat(s,Modifier.SCALEMODIFIER.getValue()));
        else if(s.equalsIgnoreCase(Modifier.REDCOMPONENT.getTitle()))
            settings.setRed(preferences.getFloat(s,Modifier.REDCOMPONENT.getValue()));
        else if(s.equalsIgnoreCase(Modifier.GREENCOMPONENT.getTitle()))
            settings.setGreen(preferences.getFloat(s,Modifier.GREENCOMPONENT.getValue()));
        else if(s.equalsIgnoreCase(Modifier.BLUECOMPONENT.getTitle()))
            settings.setBlue(preferences.getFloat(s,Modifier.BLUECOMPONENT.getValue()));

    }

    public static void loadPreferencesToModifiers(){
        for(Modifier modifier: Modifier.values()){
            modifier.setValue((int)preferences.getFloat(modifier.getTitle(),modifier.getValue()));
        }
    }

}
