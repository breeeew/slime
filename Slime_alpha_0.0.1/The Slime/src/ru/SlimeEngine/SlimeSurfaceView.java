package ru.SlimeEngine;

import java.util.Timer;


import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SlimeSurfaceView extends android.view.SurfaceView implements SurfaceHolder.Callback{
	public SlimeSurfaceView(Context context) {
        super(context);
        this.getHolder().addCallback(this);
    }

    private Scene scene;
    private DrawerTask drawer;
    public Canvas canv;
    
    Timer t=new Timer();
    
	public SlimeSurfaceView(Context context, Scene s) {
	    super(context);
	    scene = s;
	    drawer = new DrawerTask(this.getHolder(), this.scene);
	    this.getHolder().addCallback(this);
	}

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		scene.setWH(width, height);
        Settings.GenerateSettings(width, height);
	}

    public void surfaceCreated(SurfaceHolder holder) {
		 	t.scheduleAtFixedRate(drawer, 0, Settings.frameInterval);
	        canv = this.getHolder().lockCanvas();
	        scene.setWH(canv.getWidth(), canv.getHeight());
	        this.getHolder().unlockCanvasAndPost(canv);
	}

    public void surfaceDestroyed(SurfaceHolder holder) {
		t.cancel();
	}

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}
