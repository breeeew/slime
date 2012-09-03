package ru.SlimeEngine;

import java.util.Timer;


import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	public mSurfaceView(Context context) {
        super(context);
        this.getHolder().addCallback(this);
    }
	 /**
     * сцена
     */
	mScene scene;
	
	 /**
     * отрисовщик
     */
    mDrawerTask drawer;
    
    /**
     * канва
     */
    public Canvas canv;
    
    Timer t=new Timer();
    
    /**
     * конструктор
     *
     * @param context
     *            - контекст
     * @param s
     *            - сцена ассоциированная с поверхностью
     */
	public mSurfaceView(Context context, mScene s) {
	    super(context);
	    scene = s;
	    drawer = new mDrawerTask(this.getHolder(), this.scene);
	    this.getHolder().addCallback(this);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		scene.setWH(width, height);
        mSettings.GenerateSettings(width, height);
	}
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		 	t.scheduleAtFixedRate(drawer, 0, mSettings.frameInterval);
	        canv = this.getHolder().lockCanvas();
	        scene.setWH(canv.getWidth(), canv.getHeight());
	        this.getHolder().unlockCanvasAndPost(canv);
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		t.cancel();
	}
	/**
     * устанавливает для поверхности новую сцену
     *
     * @param scene
     *            новая сцена
     */
    public void setScene(mScene scene) {
        this.scene = scene;
    }
    /**
     * Возвращает текущую сцену
     *
     * @return возвращает текущую сцену
     */
    public mScene getScene() {
        return scene;
    }
}
