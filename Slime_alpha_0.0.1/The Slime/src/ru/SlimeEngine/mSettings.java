package ru.SlimeEngine;

import java.util.Timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.widget.Toast;

public class mSettings {
	 private static Timer t;
	    private static mFrameCounter tmp = new mFrameCounter();
	 
	    public static boolean AutoScale=false;
	    public static boolean FullScreen = false;
	    public static int DefaultXRes = 480;
	    public static int DefaultYRes = 800;
	    public static int CurrentXRes;
	    public static int CurrentYRes;
	    public static float ScaleFactorX =1;
	    public static float ScaleFactorY =1;
	    public static int targetFrameRate = 25;
	    public static int frameInterval = 1000/targetFrameRate;
	    public static int frameCounter=0;
	    public static int realFrameRate=0;
	 
	    @SuppressLint("NewApi")
		public static void GenerateSettings(Display D)
	    {
	    	Point size = new Point();
	        //
	    	if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
	            D.getSize(size);

	            mSettings.CurrentXRes = size.x;
	            mSettings.CurrentYRes = size.y;
	          }
	    	else{
	             
	            mSettings.CurrentXRes = D.getWidth(); 
	            mSettings.CurrentYRes = D.getHeight();
	          	}
	            mSettings.ScaleFactorX = mSettings.CurrentXRes/(float)mSettings.DefaultXRes;
	        mSettings.ScaleFactorY = mSettings.CurrentYRes/(float)mSettings.DefaultYRes;
	        
	        if (mSettings.ScaleFactorX!=1||mSettings.ScaleFactorY!=1)
	        {
	            mSettings.AutoScale=true;
	        }
	    }
	 
	    public static void GenerateSettings(int w, int h)
	    {
	        mSettings.CurrentXRes =w;
	        mSettings.CurrentYRes = h;
	        mSettings.ScaleFactorX = mSettings.CurrentXRes/(float)mSettings.DefaultXRes;
	        mSettings.ScaleFactorY = mSettings.CurrentYRes/(float)mSettings.DefaultYRes;
	        if (mSettings.ScaleFactorX!=1||mSettings.ScaleFactorY!=1)
	        {
	            mSettings.AutoScale=true;
	        }
	    }
	 
	    public static void setTargetFrameRate(int fr)
	    {
	        mSettings.targetFrameRate=fr;
	        frameInterval = 1000/targetFrameRate;
	        t = new Timer();
	        t.scheduleAtFixedRate(tmp, 0, 1000);
	    }
	 
	    public static void setDefaultRes(int x, int y)
	    {
	        mSettings.DefaultXRes = x;
	        mSettings.DefaultYRes = y;
	    }
	 
	    public static int getFrameRate()
	    {
	        return mSettings.realFrameRate;
	    }
	 
	    public static void newFrame()
	    {
	        mSettings.frameCounter++;
	    }
}
