package ru.SlimeEngine;

import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawerTask extends TimerTask {
		SurfaceHolder holder;
		Paint mainPaint;
		Scene scene;
		Layer layer;
		Canvas canvas;

		DrawerTask(SurfaceHolder h, Scene s) {
			mainPaint = new Paint();
			this.holder = h;
	        this.scene = s;
	    }
	
	    @Override
		public void run() {
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
					Settings.newFrame();
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
