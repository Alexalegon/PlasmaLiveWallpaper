package com.darkfig.plasmalivewallpaper;

/**
 * Created by Miguel Martrinez on 2/20/2017.
 */

public class SettingsListener {
    private float x;
    private float xy;
    private float y;
    private float centerx;
    private float centery;
    private float dist;
    private float red;
    private float green;
    private float blue;
    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setCenterX(float centerx){
        /*our plasma shader implementation takes a center offset value we divide by
        the larger this value is, the smaller amount the animation is offset
        so that we make the settings intuitive, we take the user input value and
        we invert this value so that as center offset is higher animation speeds up*/
        this.centerx = invertValue(centerx);
    }

    public void setCenterY(float centery){
        /*our plasma shader implementation takes a center offset value we divide by
        the larger this value is, the smaller amount the animation is offset
        so that we make the settings intuitive, we take the user input value and
        we invert this value so that as center offset is higher animation speeds up*/
        this.centery = invertValue(centery);
    }

    public void setDist(float dist){
        this.dist = dist;
    }

    public void setXY(float xy){
        this.xy = xy;
    }

    public void setRed(float red){
        this.red = red;
    }

    public void setGreen(float green){
        this.green = green;
    }

    public void setBlue(float blue){
        this.blue = blue;
    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public float getCenterx() {
        return centerx;
    }

    public float getCentery() {
        return centery;
    }

    public float getDist() {
        return dist;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getXy() {
        return xy;
    }

    private float invertValue(float value){
        value -= 30f;
        value *= -1;
        return value <= 0 ? 1 : value;
    }
}
