package com.example.flappy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.channels.Pipe;

public class BitmapBank {

    Bitmap background;
    Bitmap bird;
    Bitmap northPipe, southPipe;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res,R.drawable.background);
        background = scaleImage(background);
        bird = BitmapFactory.decodeResource(res, R.drawable.bird);
        northPipe = BitmapFactory.decodeResource(res,R.drawable.pipedown);
        southPipe = BitmapFactory.decodeResource(res, R.drawable.pipeup);
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
    public Bitmap getSouthPipe() {
        return southPipe;
    }


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

    public int getBirdWidth(){ return bird.getWidth(); }
    public int getBirdHeight(){ return bird.getHeight(); }

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
