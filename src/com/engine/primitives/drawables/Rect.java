package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rect extends GameObject {
    Point TopLeft, BottRight, TopRight, BottLeft;
    Line[] Lines = new Line[4];

    public Rect(Point p1, Point p2) {
        this.TopLeft = p1;
        this.BottRight = p2;
        refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_RECT);
    }

    public Rect(int x1, int y1, int x2, int y2) {
        this.TopLeft = new Point(x1, y1);
        this.BottRight = new Point(x2, y2);
        refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_RECT);
}

    public Rect(float x1, float y1, float x2, float y2) {
        this.TopLeft = new Point(x1, y1);
        this.BottRight = new Point(x2, y2);
        refreshLines();
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_RECT);
    }

    public void refreshLines() {
        this.TopRight = new Point(this.BottRight.getCurrentX(), this.TopLeft.getCurrentY());
        this.BottLeft = new Point(this.TopLeft.getCurrentX(), this.BottRight.getCurrentY());
        this.Lines[0] = new Line(this.TopLeft, this.TopRight);
        this.Lines[1] = new Line(this.TopRight, this.BottRight);
        this.Lines[2] = new Line(this.BottRight, this.BottLeft);
        this.Lines[3] = new Line(this.BottLeft, this.TopLeft);
    }

    public Line[] getLines() {
        return Lines;
    }

    @Override
    public void update() {
        TopLeft.update();
        BottRight.update();
        TopRight.update();
        BottLeft.update();
        this.refreshLines();
    }

    @Override
    public boolean isSelected(float x, float y) {
        if(x > TopLeft.getCurrentX()){
            if(x < TopRight.getCurrentX()){
                if(y > TopLeft.getCurrentY()){
                    if(y < BottRight.getCurrentY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        for (Line l : Lines){
            l.draw(c, p);
        }
    }
}
