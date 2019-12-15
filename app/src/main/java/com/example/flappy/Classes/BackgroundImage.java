package com.example.flappy.Classes;

public class BackgroundImage {

    private int backgroundImageX, getBackgroundImageY,getBackgroundImageVelocity;

    public BackgroundImage(){
        backgroundImageX = 0;
        getBackgroundImageY = 0;
        getBackgroundImageVelocity = 3;
    }

    public int getX(){ return backgroundImageX; }

    public int getY(){
        return getBackgroundImageY;
    }

    public void setX(int backgroundImageX){
        this.backgroundImageX=backgroundImageX;
    }

    public void setY(int backgroundImageY){
        this.getBackgroundImageY = backgroundImageY;
    }

    public int getVelocity(){
        return getBackgroundImageVelocity;
    }


}
