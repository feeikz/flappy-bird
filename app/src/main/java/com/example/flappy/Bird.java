package com.example.flappy;

public class Bird {

    private int birdX, birdY, velocity;


    public Bird(){
            birdX = AppConstants.SCREEN_WIDTH/2;
            birdY = AppConstants.SCREEN_HEIGHT/2;
            velocity = 0;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getBirdX() {
        return birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdX(int birdX){
        this.birdX = birdX;
    }

    public void setBirdY(int birdY){
        this.birdY = birdY;
    }
}
