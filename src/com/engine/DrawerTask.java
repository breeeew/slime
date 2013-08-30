package com.engine;

import java.util.TimerTask;
import com.engine.primitives.drawables.GameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawerTask extends TimerTask {
		SurfaceHolder holder;
		Paint paint;
		Scene scene;
		Layer layer;
		Canvas canvas;

		DrawerTask(SurfaceHolder surfaceHolder, Scene scene) {
			paint = new Paint();
			this.holder = surfaceHolder;
	        this.scene = scene;
	    }
	
	    @Override
		public void run() {
			try {
					canvas = null;
					canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
				
					for (int current_layout_number = 0; current_layout_number < scene.getLayCount(); current_layout_number++) {
						layer = scene.getLayerByNum(current_layout_number);
						if (layer != null) {
							paint = layer.paint;
							for (GameObject tmp : layer.data) {
								tmp.draw(canvas, paint);
							}
						}
					}
					scene.update();
					Engine.newFrame();
	        } 
	        catch (Exception e) {
	            // TODO: вывод в лог и пользователю информации об ошибке
            }
	        finally {
	            		if (canvas != null) {
	            			holder.unlockCanvasAndPost(canvas);
	            		}
	        }
	    }
}
