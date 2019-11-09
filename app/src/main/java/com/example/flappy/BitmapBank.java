package com.example.flappy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res,R.drawable.background);

    }

    public Bitmap getBackground(){
        return background;
    }

    public int getHeight(){
        return background.getHeight();
    }

    public int getWidth(){
        return background.getWidth();
    }
}
