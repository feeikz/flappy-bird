package com.example.flappy.Classes;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.flappy.R;

public class SounBank {

    Context context;
    MediaPlayer jump, point, hit, wing;

    public SounBank(Context context){
        this.context = context;
        jump = MediaPlayer.create(context, R.raw.swoosh);
        point = MediaPlayer.create(context,R.raw.point);
        hit = MediaPlayer.create(context,R.raw.hit);
    }

    public void playJump(){
        if(jump != null){
         jump.start();
        }
    }

    public void playPoint(){
        if(point != null){
            point.start();
        }
    }

    public void playHit(){
        if(hit != null){
            hit.start();
        }
    }

}
