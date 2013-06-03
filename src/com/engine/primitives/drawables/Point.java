package com.engine.primitives.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Point extends GameObject {
	private float currentX;
    private float currentY;
	private float accelerationX = 1; // 1 = no acceleration
    private float accelerationY = 1;
	private float displaceX = 0; // 0 = no move
    private float displaceY = 0;

    @Override
	public void update() {
		this.currentX = this.displaceX + this.currentX;
		this.currentY = this.displaceY + this.currentY;
		this.displaceX = this.displaceX * this.accelerationX;
		this.displaceY = this.displaceY * this.accelerationY;
	}
	
	@Override
	public boolean isSelected(float x, float y) {
		if (this.currentX == x && this.currentY == y)
			return true;
		else
			return false;
	}
	
	@Override
	public void draw(Canvas c, Paint p) {
		p.setColor(Color.RED);
		p.setStrokeWidth(5);
		c.drawPoint(currentX, currentY, p);
	}

    public Point(int currentX, int currentY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public Point(float currentX, float currentY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public Point(float currentX, float currentY, float accelerationX, float accelerationY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.accelerationX = accelerationX;
		this.accelerationY = accelerationY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public Point(int currentX, int currentY, int displaceX, int displaceY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.displaceX = displaceX;
		this.displaceY = displaceY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public Point(int currentX, int currentY, int displaceX, int displaceY, int accelerationX, int accelerationY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.accelerationX = accelerationX;
		this.accelerationY = accelerationY;
		this.displaceX = displaceX;
		this.displaceY = displaceY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public Point(float currentX, float currentY, float displaceX, float displaceY, float accelerationX, float accelerationY) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.accelerationX = accelerationX;
		this.accelerationY = accelerationY;
		this.displaceX = displaceX;
		this.displaceY = displaceY;
		this.setTypeOfGameObject(BaseObjectTypes.TYPE_POINT);
	}
	
	public float getCurrentX() {
		return currentX;
	}

	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}
 
	public float getCurrentY() {
		return currentY;
	}
 
	public void setCurrentY(float currentY) {
		this.currentY = currentY;
	}
 
	public float getAccelerationX() {
		return accelerationX;
	}
 
	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}
 
	public float getAccelerationY() {
		return accelerationY;
	}
 
	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public float getDisplaceX() {
		return displaceX;
	}
	
	public void setDisplaceX(float displaceX) {
		this.displaceX = displaceX;
	}

	public float getDisplaceY() {
		return displaceY;
	}
 
	public void setDisplaceY(float displaceY) {
		this.displaceY = displaceY;
	}
}
