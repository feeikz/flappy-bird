package com.example.flappy;

import android.graphics.Canvas;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState; //0-not started , 1 - playing , 2 - game over

    public GameEngine(){
        backgroundImage = new BackgroundImage();
        bird = new Bird();
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

}
