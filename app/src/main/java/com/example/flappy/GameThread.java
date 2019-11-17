package com.example.flappy;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33; //delay in ms bewteen screen refreshes


    public GameThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run() {
        startTime = SystemClock.uptimeMillis();
        Canvas canvas = surfaceHolder.lockCanvas(null);
        if(canvas!=null){
            synchronized (surfaceHolder){
                Log.i("msg","Som tu");
                AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                AppConstants.getGameEngine().updateAndDrawBird(canvas);
                //unlocking the canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        //loop
        loopTime = SystemClock.uptimeMillis()- startTime;
        //pausing here to make sure we update the right amount per second

        if(loopTime < DELAY){
            try{
                Thread.sleep(DELAY - loopTime);
            }catch(InterruptedException e){
                Log.e("Intterruped", "Interrupted while sleeping");
            };
        }
    }

    //return whether the thread is running

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean running) {
        isRunning = running;
    }
}
