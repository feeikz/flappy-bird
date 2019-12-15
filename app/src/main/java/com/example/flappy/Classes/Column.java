package com.example.flappy.Classes;
public class Column {
    private int posX, posY;

    public Column(int posX, int posY){
        this.posX=posX;
        this.posY=posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int position){
        this.posX=position;
    }
}
