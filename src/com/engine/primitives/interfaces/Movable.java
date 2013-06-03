package com.engine.primitives.interfaces;

import com.engine.primitives.drawables.Point;

public interface Movable {
    public void moveToXY(float x, float y);
    public void moveToPoint(Point p);
    public void LerpMoveToXY(float x, float y);
    public void LerpMoveToPoint(Point p);
}
