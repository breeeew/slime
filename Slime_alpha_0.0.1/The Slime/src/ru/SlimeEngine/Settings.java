package ru.SlimeEngine;

import java.util.Timer;

import android.view.Display;

public class Settings {
	 private static Timer t;
	    private static FrameCounter tmp = new FrameCounter();
	 
	    public static boolean AutoScale = false;
	    public static boolean FullScreen = false;
	    public static int DefaultXRes = 480;
	    public static int DefaultYRes = 800;
	    public static int CurrentXRes;
	    public static int CurrentYRes;
	    public static float ScaleFactorX = 1;
	    public static float ScaleFactorY = 1;
	    public static int targetFrameRate = 25;
	    public static int frameInterval = 1000/targetFrameRate;
	    public static int frameCounter = 0;
	    public static int realFrameRate = 0;
	 
	    public static void GenerateSettings(Display D)
	    {
	        Settings.CurrentXRes = D.getWidth();
	        Settings.CurrentYRes = D.getHeight();
	        Settings.ScaleFactorX = Settings.CurrentXRes/(float) Settings.DefaultXRes;
	        Settings.ScaleFactorY = Settings.CurrentYRes/(float) Settings.DefaultYRes;
	        if (Settings.ScaleFactorX!=1|| Settings.ScaleFactorY!=1)
	        {
	            Settings.AutoScale=true;
	        }
	    }
	 
	    public static void GenerateSettings(int w, int h)
	    {
	        Settings.CurrentXRes =w;
	        Settings.CurrentYRes = h;
	        Settings.ScaleFactorX = Settings.CurrentXRes/(float) Settings.DefaultXRes;
	        Settings.ScaleFactorY = Settings.CurrentYRes/(float) Settings.DefaultYRes;
	        if (Settings.ScaleFactorX!=1|| Settings.ScaleFactorY!=1)
	        {
	            Settings.AutoScale=true;
	        }
	    }
	 
	    public static void setTargetFrameRate(int fr)
	    {
	        Settings.targetFrameRate=fr;
	        frameInterval = 1000/targetFrameRate;
	        t = new Timer();
	        t.scheduleAtFixedRate(tmp, 0, 1000);
	    }
	 
	    public static void setDefaultRes(int x, int y)
	    {
	        Settings.DefaultXRes = x;
	        Settings.DefaultYRes = y;
	    }
	 
	    public static int getFrameRate()
	    {
	        return Settings.realFrameRate;
	    }
	 
	    public static void newFrame()
	    {
	        Settings.frameCounter++;
	    }
}
