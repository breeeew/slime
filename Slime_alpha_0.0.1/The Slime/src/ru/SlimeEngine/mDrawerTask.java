package ru.SlimeEngine;

import java.util.TimerTask;
import ru.slime.objects.GameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class mDrawerTask extends TimerTask {
		SurfaceHolder holder;
		Paint mainPaint;
		mScene scene;
		mLayer layer;
		Canvas canvas;

		mDrawerTask(SurfaceHolder h, mScene s) {
			mainPaint = new Paint();
			this.holder = h;
	        this.scene = s;
	    }
	
	    @Override
		public void run() {
		// TODO Auto-generated method stub
			try {
					canvas = null;
					canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
				
					for (int l = 0; l < scene.getLayCount(); l++) {
						layer = scene.getLayerByNum(l);
						if (layer != null) {
							mainPaint = layer.p;
							for (GameObject tmp : layer.data) {
								tmp.draw(canvas, mainPaint);
							}
						}
					}
					scene.update();
					mSettings.newFrame();
	        } 
	        catch (Exception e) {
	 
	        } 
	        finally {
	            		if (canvas != null) {
	            			holder.unlockCanvasAndPost(canvas);
	            		}
	        }
	    }
}
