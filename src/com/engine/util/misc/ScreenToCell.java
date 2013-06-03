package com.engine.util.misc;

import com.engine.Scene;
import com.engine.primitives.drawables.Point;

public class ScreenToCell {
    public static Point screenToCell(Scene scene, float x, float y) {
        float xx = x;
        float yy = y;
        int tmp;
        Point p;
        tmp = Math.min(scene.width, scene.height);
        if (scene.getOrient() == Scene.ORIENTATION_HOR) {
            xx = xx - tmp;
        } else {
            yy = yy - tmp;
        }
        p = new Point(xx, yy);
        return p;
    }

}
