package com.example.flappy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Random;



public class GameEngine {

    SharedPreferences sharedPreferences;
    BackgroundImage backgroundImage;
    Bird bird;
    ArrayList<Tube> tubes;
    Random random;
    int score;
    int scoringTube; //keeps track of scoring tubes
    Paint scorePaint;
    boolean tmp;


    int distanceBetweenTubes, minTubeOffset, maxTubeOffset;
    int gap = 400;
    int numberOfTubes = 4;
    int[] tubeX = new int[numberOfTubes];
    int[] topTubeY = new int[numberOfTubes];
    Rect bird_rect, north_rect, south_rect, view_rect;
    Rect[] north_rects = new Rect[numberOfTubes];
    Rect[] south_rects = new Rect[numberOfTubes];

    static int gameState; //0-not started , 1 - playing , 2 - game over



    public GameEngine() {
        //Context context = AppConstants.gameActivityContext;
        //sharedPreferences = context.getSharedPreferences("save",0);
        //tmp = sharedPreferences.getBoolean("value", true);
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();


        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanecBetweenTubes;
            int topTubeOffsetY = AppConstants.minTubeOffsetY +
                    random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);

    }

    public void updateAndDrawTubes(Canvas canvas) {


        if (gameState == 1) {
            if ((tubes.get(scoringTube).getTubeX() < bird.getBirdX() + AppConstants.getBitmapBank().getBirdWidth()) && (tubes.get(scoringTube).getTopTubeOffsetY() > bird.getBirdY() || tubes.get(scoringTube).getBottomTubeY() < (bird.getBirdY() + AppConstants.getBitmapBank().getBirdHeight()))) {
               /* if(tmp){
                    AppConstants.getSounBank().playHit();
                }*/
                AppConstants.getSounBank().playHit();
                gameState = 2;
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
               // ((Activity)context).finish();
            } else if (tubes.get(scoringTube).getTubeX() < bird.getBirdX() - AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
                /*if(tmp){
                    AppConstants.getSounBank().playPoint();
                }*/
                AppConstants.getSounBank().playPoint();
            }
            for (int i = 0; i < AppConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() + AppConstants.numberOfTubes * AppConstants.distanecBetweenTubes);
                    int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                    tubes.get(i).setTubeColor();
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                if (tubes.get(i).getTubeColor() == 0) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                } else {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);

                }
            }
            canvas.drawText("Pt: " + score, 0, 110, scorePaint);
        }
    }


    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawBird(Canvas canvas) {
        if (gameState == 1) {
            if (bird.getBirdY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setBirdY(bird.getBirdY() + bird.getVelocity());
            }
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(), bird.getBirdX(), bird.getBirdY(), null);
        //bird_rect.set( bird.getBirdX(), bird.getBirdY(),bird.getBirdX()+AppConstants.getBitmapBank().getBirdWidth(), bird.getBirdY() + AppConstants.getBitmapBank().getBirdHeight());
        //  Log.d("msg", "bird left: " + String.valueOf(bird_rect.left) + " bird right: " + String.valueOf(bird_rect.right) + " bird top: " + String.valueOf(bird_rect.top) + "bird bot" + String.valueOf(bird_rect.bottom) );
        // Log.d("msg", "bird left: " + String.valueOf(bird.getBirdX()) + " bird right: " + String.valueOf(bird.getBirdX() + AppConstants.getBitmapBank().getBirdWidth()) + " bird top: " + String.valueOf(bird.getBirdY()) + " bird bot: " + String.valueOf(bird.getBirdY() + AppConstants.getBitmapBank().getBirdHeight()) );

    }

    public void newupdate(Canvas canvas) {
        if (gameState == 1) {
            for (int i = 0; i < numberOfTubes; i++) {
                tubeX[i] -= 5;
                if (tubeX[i] < -AppConstants.getBitmapBank().getNorthPipeWidth()) {
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    topTubeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
                    ;//wary between min and max
                }
                canvas.drawBitmap(AppConstants.getBitmapBank().getNorthPipe(), tubeX[i], topTubeY[i] - AppConstants.getBitmapBank().getNorthPipeHeight(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getSouthPipe(), tubeX[i], topTubeY[i] + gap, null);
                north_rects[i] = new Rect(tubeX[i], topTubeY[i] - AppConstants.getBitmapBank().getNorthPipeHeight(), tubeX[i] + AppConstants.getBitmapBank().getNorthPipeWidth(), topTubeY[i]);
                //super.setCollisionRect(north_rects[i]);
                south_rects[i] = new Rect(tubeX[i], topTubeY[i] + gap, tubeX[i] + AppConstants.getBitmapBank().getSouthPipeWidth(), topTubeY[i] + gap + AppConstants.getBitmapBank().getSouthPipeHeight());

            }
        }
    }

    public void updateAndDrawWall(Canvas canvas) {
        newupdate(canvas);
    }

    public void checkCollision() {
        for (int i = 0; i < numberOfTubes; i++) {
            if (bird_rect.intersect(south_rects[i])) Log.d("msg", "OK");
        }
    }

    public void gameController(Canvas canvas) {
        updateAndDrawBackgroundImage(canvas);
        updateAndDrawBird(canvas);
        updateAndDrawTubes(canvas);
        // updateAndDrawWall(canvas);
        // checkCollision();
    }

}
