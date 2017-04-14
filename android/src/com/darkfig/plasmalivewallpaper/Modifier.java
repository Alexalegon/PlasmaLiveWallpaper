package com.darkfig.plasmalivewallpaper;

/**
 * Created by Miguel Martrinez on 3/27/2017.
 */

public enum Modifier {
    STRENGTHMODIFIER("Color Strength Modifier",100,100),BRIGHTNESSMODIFIER("Brightness Modifier",100,0),SCALEMODIFIER("Scale Modifier",50,10),
    CENTERXMODIFIER("CenterX Modifier",30,10), CENTERYMODIFIER("CenterY Modifier,",30,10),REDCOMPONENT("Red Color Modifier",100,10),
    GREENCOMPONENT("Green Color Modifier",100,71), BLUECOMPONENT("Blue Color Modifier",100,71);

    private String title;
    private int value;
    private int maxValue;
    Modifier(String title, int maxValue,int defaultValue){
        this.title = title;
        this.maxValue = maxValue;
        value = defaultValue;
    }

    public void setValue(int value){
        this.value = value;
    }

    public String getTitle(){
        return title;
    }

    public int getMaxValue(){
        return maxValue;
    }

    public int getValue(){
        return value;
    }
}
