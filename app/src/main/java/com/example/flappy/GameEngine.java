package com.example.flappy;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    Random random;
    BackgroundImage backgroundImage;
    Bird bird;
    ArrayList<Column>northC;
    ArrayList<Column>southC;
    Column column1, column2;

    static int gameState; //0-not started , 1 - playing , 2 - game over

    public GameEngine(){
        random = new Random();
        backgroundImage = new BackgroundImage();
        bird = new Bird();
       /*northC = new ArrayList<Column>();
        southC = new ArrayList<Column>();
        southC.add(new Column(AppConstants.SCREEN_WIDTH*3/4, AppConstants.SCREEN_HEIGHT));*/
       column1 = new Column(AppConstants.SCREEN_WIDTH*3/4, 1300);
        column2 = new Column(AppConstants.SCREEN_WIDTH*3/4, -700);

       gameState = 0;
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
    }


    public void updateAndDrawNP(Canvas canvas){
        if(gameState == 1){
            column1.setPosX(column1.getPosX() - 5);
            Log.d("posy1","posY1: " + String.valueOf(column1.getPosY()));
            canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), column1.getPosX(), column1.getPosY(), null);
        }
    }

    public void updateAndDrawSP(Canvas canvas){
        if(gameState == 1){
            column2.setPosX(column2.getPosX() - 5);
            Log.d("posy2","posY2: " + String.valueOf(column1.getPosY()));
            canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(), column2.getPosX(), column2.getPosY(), null);
        }
    }


    public void updateAndDrawWall(Canvas canvas){
        updateAndDrawNP(canvas);
        updateAndDrawSP(canvas);
    }



}
