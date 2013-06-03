package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PolyLine extends GameObject {

	private Point[] Points;
    private int size = 0;
 
    private Line[] Lines;
 
    public PolyLine(int size, Point[] data) {
        Points = data;
        this.size = size;
        this.refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_POLYLINE);
    }
 
    public PolyLine(Point[] data) {
        Points = data;
        this.size = data.length;
        this.refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_POLYLINE);
    }
 
    public PolyLine(int[] dataXY) {
        this.size = dataXY.length / 2;
        Points = new Point[this.size];
        for (int i = 0; i < this.size; i++) {
            Points[i] = new Point(dataXY[i * 2], dataXY[i * 2 + 1]);
        }
        this.refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_POLYLINE);
    }
 
    public Point[] getPoints() {
        return Points;
    }
 
    public int getSize() {
        return size;
    }
 
    public void refreshLines() {
        Lines = new Line[this.size - 1];
        for (int i = 0; i < this.size - 1; i++) {
            Lines[i] = new Line(Points[i], Points[i + 1]);
        }
    }
 
    public Line[] getLines() {
        return Lines;
    }
 
    @Override
    public void update() {
        for (Point a : Points) {
            a.update();
        }
        this.refreshLines();
    }

    @Override
    public boolean isSelected(float x, float y) {
        // TODO: реализовать!
        return false;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        for (Line l :Lines){
            l.draw(c, p);
        }
    }
}
