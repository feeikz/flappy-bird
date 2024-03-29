package com.example.flappy.Classes;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.flappy.Classes.GameEngine;

public class AppConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopAndBottomTubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY;
    static int maxTubeOffsetY;
    static int distanecBetweenTubes;
    static SounBank sounBank;
    public static Context gameActivityContext;


    public static void initialization(Context context){
        setScreenSize(context);

        bitmapBank = new BitmapBank(context.getResources(), true);
        setGameConstants();
        gameEngine = new GameEngine(context);
        sounBank = new SounBank(context);
    }
    public static void initialization2(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources(),false);
        setGameConstants();
        gameEngine = new GameEngine(context);
        sounBank = new SounBank(context);
    }

    public static SounBank getSounBank(){
        return sounBank;
    }

    public static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = - 40;
        gapBetweenTopAndBottomTubes = 500;
        AppConstants.numberOfTubes = 2;
        AppConstants.tubeVelocity = 7;
        AppConstants.minTubeOffsetY = (int)(AppConstants.gapBetweenTopAndBottomTubes / 2.0);
        AppConstants.maxTubeOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTubeOffsetY - AppConstants.gapBetweenTopAndBottomTubes;
        AppConstants.distanecBetweenTubes = AppConstants.SCREEN_WIDTH * 3/4;
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}

