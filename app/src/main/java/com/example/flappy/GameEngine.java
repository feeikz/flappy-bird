package com.example.flappy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    Random random;
    BackgroundImage backgroundImage;
    Bird bird;
    Column[] northC, southC;
    Column up1, up2, up3, up4, down1, down2, down3, down4;
    int distance = 400;
    Column Up[];
    Column Down[];
    int distanceBetweenTubes, minTubeOffset, maxTubeOffset;
    int gap = 400;
    int numberOfTubes = 4;
    int[] tubeX =  new int[numberOfTubes];
    int[] topTubeY = new int[numberOfTubes];
    Rect bird_rect, north_rect, south_rect, view_rect;
    Rect[] north_rects =  new Rect[numberOfTubes];
    Rect[] south_rects = new Rect[numberOfTubes];



    static int gameState; //0-not started , 1 - playing , 2 - game over

    public GameEngine(){
        random = new Random();
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        bird_rect = new Rect(AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitmapBank().getBirdWidth(), AppConstants.SCREEN_HEIGHT/2-AppConstants.getBitmapBank().getBirdHeight(), AppConstants.getBitmapBank().getBirdWidth(), AppConstants.getBitmapBank().getBirdHeight());
        north_rect = new Rect();
        south_rect = new Rect();
        view_rect = new Rect(0,0,AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT);


        distanceBetweenTubes = AppConstants.SCREEN_WIDTH *3/4;
        minTubeOffset  = gap/2;
        maxTubeOffset = AppConstants.SCREEN_HEIGHT - minTubeOffset - gap;
        for(int i = 0; i<numberOfTubes; i++){
            tubeX[i] = AppConstants.SCREEN_WIDTH + i*distanceBetweenTubes;
            topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);//wary between min and max
            north_rects[i] = new Rect(tubeX[i], topTubeY[i] - AppConstants.getBitmapBank().getNorthPipeHeight(),AppConstants.getBitmapBank().getNorthPipeWidth(), AppConstants.getBitmapBank().getNorthPipeHeight());
            south_rects[i] = new Rect(tubeX[i], topTubeY[i]+gap, AppConstants.getBitmapBank().getSouthPipeWidth(), AppConstants.getBitmapBank().getSouthPipeHeight());
        }
        gameState = 0;
    }

    public void gameController(Canvas canvas){
                updateAndDrawBackgroundImage(canvas);
                updateAndDrawBird(canvas);
                updateAndDrawWall(canvas);
                checkCollision();
    }




    public void updateAndDrawBackgroundImage(Canvas canvas){
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if(backgroundImage.getX() < - AppConstants.getBitmapBank().getBackgroundWidth()){
            backgroundImage.setX(0);
        }

        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(),null);
        if(backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)){
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(),backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(),backgroundImage.getY(),null);
        }
    }

    public void updateAndDrawBird(Canvas canvas){
        if (gameState == 1) {
            if(bird.getBirdY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() < 0 ){
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setBirdY(bird.getBirdY() + bird.getVelocity());
            }
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(),bird.getBirdX(), bird.getBirdY(), null);
        bird_rect.set(bird.getBirdX(),bird.getBirdY(),AppConstants.getBitmapBank().getBirdWidth(), AppConstants.getBitmapBank().getBirdHeight());
    }

    public void newupdate (Canvas canvas){
        if(gameState == 1){
            for(int i = 0; i<numberOfTubes; i++){
                tubeX[i] -= 5;
                if(tubeX[i] < -AppConstants.getBitmapBank().getNorthPipeWidth()){
                    tubeX[i]+= numberOfTubes * distanceBetweenTubes;
                    topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);;//wary between min and max
                }
                canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), tubeX[i], topTubeY[i] - AppConstants.getBitmapBank().getNorthPipeHeight(),null);
               north_rects[i].set(tubeX[i], topTubeY[i] -  AppConstants.getBitmapBank().getNorthPipeHeight(),AppConstants.getBitmapBank().getNorthPipeWidth(), AppConstants.getBitmapBank().getNorthPipeHeight());
               //Log.d("msg", "north_rect["+String.valueOf(i)+"] = X " + String.valueOf(tubeX[i]) + " , Y = " + String.valueOf(topTubeY[i] -  AppConstants.getBitmapBank().getNorthPipeHeight()) + ", width = " + String.valueOf(AppConstants.getBitmapBank().getNorthPipeWidth()) + ", bottom = "  + String.valueOf(topTubeY[i] -  AppConstants.getBitmapBank().getNorthPipeHeight() + AppConstants.getBitmapBank().getNorthPipeHeight() )  );
               canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(), tubeX[i], topTubeY[i]+gap, null);
              south_rects[i].set(tubeX[i], topTubeY[i]+gap, AppConstants.getBitmapBank().getSouthPipeWidth(), AppConstants.getBitmapBank().getSouthPipeHeight());
            }
        }
    }

    public void updateAndDrawWall(Canvas canvas){
        //updateAndDrawNP(canvas);
       // updateAndDrawSP(canvas);
       // updateColumn(canvas);
        //updateArrayUp(canvas);
        newupdate(canvas);
    }

    public void checkCollision(){
        for(int i=0;i<numberOfTubes;i++){
            if(Rect.intersects(south_rects[i],bird_rect) || Rect.intersects(north_rects[i],bird_rect)){
                Log.d("m", "GameOver");
            }
            if(bird.getBirdX() + AppConstants.getBitmapBank().getBirdWidth() > tubeX[i]){
                Log.d("m", "OK");
            }

        }

    }

    public void updateArrayUp(Canvas canvas){
        if(gameState==1){
            for (Column column : Up ){
                column.setPosX(column.getPosX() - 5);
                canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(),column.getPosX(),column.getPosY() ,null);
            }
        }
    }


    public void updateAndDrawNP(Canvas canvas){
        if(gameState==1) {

            up1.setPosX(up1.getPosX() - 5);
            up2.setPosX(up2.getPosX() - 5);
            if (up1.getPosX() + AppConstants.getBitmapBank().getNorthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH) {
                up1.setPosX(AppConstants.SCREEN_WIDTH);
            }
            if (up2.getPosX() + AppConstants.getBitmapBank().getNorthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH) {
                up2.setPosX(AppConstants.SCREEN_WIDTH);
            }
            if (up3.getPosX() + AppConstants.getBitmapBank().getNorthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH) {
                up3.setPosX(AppConstants.SCREEN_WIDTH);

            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), up1.getPosX(), up1.getPosY(), null);
            canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), up2.getPosX(), up2.getPosY(), null);
            canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), up3.getPosX(), up3.getPosY(), null);

        }}

    public void updateAndDrawSP(Canvas canvas) {
        if (gameState == 1) {
            down1.setPosX(down1.getPosX() -5 );
            down2.setPosX(down2.getPosX() -5 );
            if(down1.getPosX() + AppConstants.getBitmapBank().getSouthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH){
                down1.setPosX(AppConstants.SCREEN_WIDTH);
            }
            if(down2.getPosX() + AppConstants.getBitmapBank().getSouthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH){
                down2.setPosX(AppConstants.SCREEN_WIDTH);
            }
            if(down3.getPosX() + AppConstants.getBitmapBank().getSouthPipeWidth() < AppConstants.SCREEN_WIDTH - AppConstants.SCREEN_WIDTH){
                down3.setPosX(AppConstants.SCREEN_WIDTH);
            }
            canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(),down1.getPosX(),down1.getPosY() ,null);
            canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(),down2.getPosX(),down2.getPosY() ,null);
            canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(),down3.getPosX(),down3.getPosY() ,null);
        }
    }




}
