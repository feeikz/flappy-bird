package com.example.flappy.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Switch;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

   GameThread gameThread;
   boolean tmp;
   SharedPreferences sharedPreferences;

    public GameView(Context context){
        super(context);
        sharedPreferences = context.getSharedPreferences("save2",0);
        tmp = sharedPreferences.getBoolean("value2", true);

        initView();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(holder);
            gameThread.start();
        }else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry){
                try{
                    gameThread.join();//to stop the thread
                    retry = false;
                }catch(InterruptedException e){
                    //
                }
            }
        }
    }

    void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            if(AppConstants.getGameEngine().gameState ==0){
                AppConstants.getGameEngine().gameState =1;
            }
            else{
                if(tmp){ AppConstants.getSounBank().playJump();}


            }
            AppConstants.getGameEngine().gameState = 1;
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }
        return true;
    }
}
