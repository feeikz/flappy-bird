package com.example.flappy;

import android.content.Context;
import android.media.MediaPlayer;

public class SounBank {

    Context context;
    MediaPlayer jump, point, hit, win;

    public SounBank(Context context){
        this.context = context;
        jump = MediaPlayer.create(context, R.raw.swoosh);
        point = MediaPlayer.create(context,R.raw.point);
        hit = MediaPlayer.create(context,R.raw.hit);
        win = MediaPlayer.create(context,R.raw.win);
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

    public void playWin(){
        if(hit != null){
            win.start();
        }
    }

}
