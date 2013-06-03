package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
	private int typeOfGameObject;

    public int getTypeofGameObject(){
		return typeOfGameObject;
	}

    public void setTypeOfGameObject(int type){
		this.typeOfGameObject = type;
	}
	
	public abstract void update();
	public abstract boolean isSelected(float x, float y);
	public abstract void draw(Canvas c, Paint p);
}