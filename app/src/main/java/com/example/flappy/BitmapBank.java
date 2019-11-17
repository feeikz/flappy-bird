package com.example.flappy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap bird;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res,R.drawable.background);
        background = scaleImage(background);
        bird = BitmapFactory.decodeResource(res, R.drawable.bird);

    }

    public Bitmap getBird() {
        return bird;
    }

    public int getBirdWidth(){
        return bird.getWidth();
    }

    public int getBirdHeight(){
        return bird.getHeight();
    }

    public Bitmap getBackground(){
        return background;
    }


    public  int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        //we will multiply widthHeightRatio with screenHeight to get scaled with of the bitmap
        //Then call createScaleBitmap() to create a new bitmap, scaled from an existing bitmap, when possible
        int backgroundScaleWidth = (int) widthHeightRatio  * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWidth, AppConstants.SCREEN_HEIGHT,false);


    }

}
