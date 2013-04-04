package ru.SlimeEngine;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
    public static final int TYPE_POINT = 1;
    public static final int TYPE_LINE = 2;
    public static final int TYPE_POLYLINE = 3;
    public static final int TYPE_RECT = 4;
    public static final int TYPE_CIRCLE = 5;
    public static final int TYPE_SIMPLESPRITE = 6;

	private int typeOfGameObject;

    public int getTypeOfGameObject(){
		return typeOfGameObject;
	}
    public void setTypeOfGameObject(int type){
		typeOfGameObject = type;
	}

	public abstract void update();
    public abstract void draw(Canvas canvas, Paint paint);
    public abstract boolean isSelected(float x, float y);

}
