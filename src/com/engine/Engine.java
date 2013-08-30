package com.engine;

import java.util.Timer;

import android.R;
import android.app.Activity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class Engine {
	    public static Timer timer = new Timer();
	    private static FrameCounterTimerTask frameCounter = new FrameCounterTimerTask();

        public static GameSurface gameSurface;
	 
        public static boolean autoScale = false;
        public static boolean fullScreen = false;
        public static int defaultXRes = 480;
        public static int defaultYRes = 800;
        public static int currentXRes;
        public static int currentYRes;
        public static float scaleFactorX = 1;
        public static float scaleFactorY = 1;
        public static int targetFrameRate = 25;
        public static int frameInterval = 1000 / targetFrameRate;
        public static int currentFrameCounter = 0;
        public static int realFrameRate = 0;
        public static int SET_FULL_SCREEN = 0;
        public static boolean isResume = false;

        private static void _calculateScaleFactor(){
            Engine.scaleFactorX = Engine.currentXRes / (float) Engine.defaultXRes;
            Engine.scaleFactorY = Engine.currentYRes / (float) Engine.defaultYRes;
            if (Engine.scaleFactorX != 1 || Engine.scaleFactorY != 1)
            {
                Engine.autoScale = true;
            }
        }

	    public static void generateSettings(Display display){
	        Engine.currentXRes = display.getWidth();
	        Engine.currentYRes = display.getHeight();
            _calculateScaleFactor();
	    }

        public static void generateSettings(Activity activity, int option, int frameRate){
            if(option == SET_FULL_SCREEN){
                fullScreen = true;
                Window window = activity.getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            Display display = activity.getWindowManager().getDefaultDisplay();
            Engine.currentXRes = display.getWidth();
            Engine.currentYRes = display.getHeight();
            setFrameRate(frameRate);
            gameSurface = new GameSurface(activity.getApplicationContext());
            _calculateScaleFactor();
        }
	 
	    public static void generateSettings(int w, int h){
	        Engine.currentXRes = w;
	        Engine.currentYRes = h;
            _calculateScaleFactor();
	    }
	 
	    public static void setFrameRate(int rate){
	        Engine.targetFrameRate = rate;
	        frameInterval = 1000 / targetFrameRate;
	    }

        public static void startEngine(){
            timer.scheduleAtFixedRate(frameCounter, 0, 1000);
        }
	 
	    public static void setDefaultRes(int x, int y){
	        Engine.defaultXRes = x;
	        Engine.defaultYRes = y;
	    }
	 
	    public static int getFrameRate(){
	        return Engine.realFrameRate;
	    }
	 
	    public static void newFrame(){
	        Engine.currentFrameCounter++;
	    }
}
