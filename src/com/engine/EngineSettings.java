package com.engine;

import java.util.Timer;

import android.view.Display;

public class EngineSettings {
	    private static Timer timer;
	    private static FrameCounterTimerTask frameCounter = new FrameCounterTimerTask();
	 
	    public static boolean autoScale = false;
        // TODO: реализовать fullscreen в движке
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

        private static void _calculateScaleFactor(){
            EngineSettings.scaleFactorX = EngineSettings.currentXRes / (float)EngineSettings.defaultXRes;
            EngineSettings.scaleFactorY = EngineSettings.currentYRes / (float)EngineSettings.defaultYRes;
            if (EngineSettings.scaleFactorX != 1 || EngineSettings.scaleFactorY != 1)
            {
                EngineSettings.autoScale = true;
            }
        }

	    public static void generateSettings(Display display){
	        EngineSettings.currentXRes = display.getWidth();
	        EngineSettings.currentYRes = display.getHeight();
            _calculateScaleFactor();
	    }
	 
	    public static void GenerateSettings(int w, int h){
	        EngineSettings.currentXRes = w;
	        EngineSettings.currentYRes = h;
            _calculateScaleFactor();
	    }
	 
	    public static void setFrameRate(int rate){
	        EngineSettings.targetFrameRate = rate;
	        frameInterval = 1000 / targetFrameRate;
	        timer = new Timer();
	        timer.scheduleAtFixedRate(frameCounter, 0, 1000);
	    }
	 
	    public static void setDefaultRes(int x, int y){
	        EngineSettings.defaultXRes = x;
	        EngineSettings.defaultYRes = y;
	    }
	 
	    public static int getFrameRate(){
	        return EngineSettings.realFrameRate;
	    }
	 
	    public static void newFrame(){
	        EngineSettings.currentFrameCounter++;
	    }
}
