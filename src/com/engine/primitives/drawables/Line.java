package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Line extends GameObject{
    private Point begin;
    private Point end;
    private Path ptCurve;
    private int color = Color.RED;

    public Line(int x1, int y1, int x2, int y2) {
        ptCurve = new Path();
        begin = new Point(x1, y1);
        end = new Point(x2, y2);
        //ptCurve.moveTo(x, y)
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_LINE);
    }

    public Line(float x1, float y1, float x2, float y2) {
        begin = new Point(x1, y1);
        end = new Point(x2, y2);
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_LINE);
    }

    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_LINE);
    }

    public Line(Point begin, Point end, int color) {
        this.begin = begin;
        this.end = end;
        this.color = color;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_LINE);
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void update() {
        begin.update();
        end.update();
    }

    @Override
    public boolean isSelected(float x, float y) {
        return false;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        Paint a = new Paint(Paint.ANTI_ALIAS_FLAG);
        a.setColor(Color.rgb(0, 148, 255));
        a.setAlpha(128);
        a.setStrokeWidth(3);
        a.setStyle(Paint.Style.STROKE);
        c.drawLine(begin.getCurrentX(), begin.getCurrentY(), end.getCurrentX(), end.getCurrentY(), a);
    }
}
