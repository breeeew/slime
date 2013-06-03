package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends GameObject {
    Point center;
    float radius;

    float arcStop;
    float arcStart;

    Circle(Point p, float radius) {
        this.center = p;
        this.radius = radius;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_CIRCLE);
    }

    Circle(Point p, int radius, int arcLen, int arcStart) {
        this.center = p;
        this.radius = radius;
        this.arcStop = arcLen;
        this.arcStart = arcStart;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_CIRCLE);
    }

    Circle(int x, int y, int radius) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_CIRCLE);
    }

    Circle(float x, float y, float radius) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_CIRCLE);
    }

    @Override
    public void update() {
        center.update();
    }

    @Override
    public boolean isSelected(float x, float y) {
        if ((x - center.getCurrentX()) *
            (x - center.getCurrentX()) +
            (y - center.getCurrentY()) *
            (y - center.getCurrentY()) < radius * radius){

            return true;
        }
        else return false;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        c.drawCircle(center.getCurrentX(), center.getCurrentY(), radius, p);
    }
}
