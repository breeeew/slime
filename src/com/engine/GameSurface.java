package com.engine;

import java.util.Timer;


import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameSurface extends android.view.SurfaceView implements SurfaceHolder.Callback{
    private DrawerTask drawer;
    private Timer t = new Timer();

    public Scene mainScene = new Scene(0, 0, 1);
    public Canvas canv;

    public GameSurface(Context context) {
        super(context);
        this.getHolder().addCallback(this);
        drawer = new DrawerTask(this.getHolder(), this.mainScene);
    }

	public GameSurface(Context context, Scene s) {
	    super(context);
	    mainScene = s;
	    drawer = new DrawerTask(this.getHolder(), this.mainScene);
	    this.getHolder().addCallback(this);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		mainScene.setWH(width, height);
        EngineSettings.GenerateSettings(width, height);
	}
	public void surfaceCreated(SurfaceHolder holder) {
		 	t.scheduleAtFixedRate(drawer, 0, EngineSettings.frameInterval);
	        canv = this.getHolder().lockCanvas();
	        mainScene.setWH(canv.getWidth(), canv.getHeight());
	        this.getHolder().unlockCanvasAndPost(canv);
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		t.cancel();
	}
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
        drawer = new DrawerTask(this.getHolder(), this.mainScene);
    }
    public Scene getMainScene() {
        return mainScene;
    }
}
