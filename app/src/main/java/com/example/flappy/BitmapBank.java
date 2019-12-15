package com.example.flappy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.nio.channels.Pipe;

public class BitmapBank {

    Bitmap background;
    Bitmap bird;
    Bitmap[] birds;
    Bitmap northPipe, southPipe;
    Bitmap tubeTop, tubeBottom;
    Bitmap redTubeTop, redTubeBottom;
    Settings settings;

    public BitmapBank(Resources res, boolean day) {

        settings = new Settings();
        if(day ){
            background = BitmapFactory.decodeResource(res,R.drawable.background);
        }else{
            background = BitmapFactory.decodeResource(res,R.drawable.backgroundnight);
        }

        background = scaleImage(background);
        //bird = BitmapFactory.decodeResource(res, R.drawable.bird);
        birds = new Bitmap[3];
        birds[0] = BitmapFactory.decodeResource(res,R.drawable.birdup);
        birds[1] = BitmapFactory.decodeResource(res,R.drawable.birdmid);
        birds[2] = BitmapFactory.decodeResource(res,R.drawable.birddown);
        northPipe = BitmapFactory.decodeResource(res,R.drawable.pipeup1);
        southPipe = BitmapFactory.decodeResource(res, R.drawable.pipedown1);
        tubeTop = BitmapFactory.decodeResource(res, R.drawable.pipeup1);
        tubeBottom = BitmapFactory.decodeResource(res, R.drawable.pipedown1);
        redTubeBottom = BitmapFactory.decodeResource(res,R.drawable.pipedownred);
        redTubeTop = BitmapFactory.decodeResource(res,R.drawable.pipeupred);
    }

    public Bitmap setBackground(Resources res, boolean day){
        if(day){
            background = BitmapFactory.decodeResource(res,R.drawable.background);
        }
        else background = BitmapFactory.decodeResource(res,R.drawable.backgroundnight);
        return background;
    }

    public Bitmap getBirds(int frame) {
        return birds[frame];
    }
    public int getBirdsWidth(){
        return birds[0].getWidth();
    }

    public int getBirdsHeight(){
        return birds[0].getHeight();
    }

    public Bitmap getRedTubeTop(){
       return redTubeTop;
    }

    public Bitmap getRedTubeBottom() {
        return redTubeBottom;
    }

    public Bitmap getTubeTop(){
        return tubeTop;
    }

    public Bitmap getTubeBottom(){
        return tubeBottom;
    }

    public int getTubeWidth(){
        return tubeTop.getWidth();
    }

    public int getTubeHeight(){
        return  tubeTop.getHeight();
    }


    public Bitmap getBird() {
        return bird;
    }
    public Bitmap getBackground() {
        return background;
    }
    public Bitmap getNorthPipe() {
        return northPipe;
    }
    public Bitmap getSouthPipe() {return southPipe; }


    public int getNorthPipeWidth(){
        return northPipe.getWidth();
    }
    public int getNorthPipeHeight(){
        return northPipe.getHeight();
    }

    public int getSouthPipeWidth(){
        return southPipe.getWidth();
    }
    public int getSouthPipeHeight(){
        return southPipe.getHeight();
    }

  //  public int getBirdWidth(){ return bird.getWidth(); }
   // public int getBirdHeight(){ return bird.getHeight(); }

    public  int getBackgroundWidth(){ return background.getWidth(); }
    public int getBackgroundHeight(){ return background.getHeight(); }



    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        //we will multiply widthHeightRatio with screenHeight to get scaled with of the bitmap
        //Then call createScaleBitmap() to create a new bitmap, scaled from an existing bitmap, when possible
        int backgroundScaleWidth = (int) widthHeightRatio  * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWidth, AppConstants.SCREEN_HEIGHT,false);


    }

}
