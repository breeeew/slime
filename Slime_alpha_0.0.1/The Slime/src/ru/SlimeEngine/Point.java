package ru.SlimeEngine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Point extends GameObject{
	private float x, y;
	private float axX = 1, axY = 1;
	private float dx = 0, dy = 0;
	@Override
	public void update() {
		this.x = this.dx + this.x;
		this.y = this.dy + this.y;
		this.dx = this.dx * this.axX;
		this.dy = this.dy * this.axY;
	}
	
	@Override
	public boolean isSelected(float x, float y) {
		if (this.x == x && this.y == y)
			return true;
		else
			return false;
	}
	
	@Override
	public void draw(Canvas c, Paint p) {
		p.setColor(Color.RED);
		p.setStrokeWidth(5);
		c.drawPoint(x, y, p);
	}

    public Point(int x, int y) {
		this.x = x; //
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public Point(float x, float y, float axX, float axY) {
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public Point(int x, int y, int dx, int dy) {
		// �����������
		this.x = x; //
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public Point(int x, int y, int dx, int dy, int axX, int axY) {
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public Point(float x, float y, float dx, float dy, float axX, float axY) {
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
 
	public float getY() {
		return y;
	}
 
	public void setY(float y) {
		this.y = y;
	}
 
	public float getAxX() {
		return axX;
	}
 
	public void setAxX(float axX) {
		this.axX = axX;
	}
 
	public float getAxY() {
		return axY;
	}
 
	public void setAxY(float axY) {
		this.axY = axY;
	}

	public float getDx() {
		return dx;
	}
	
	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}
 
	public void setDy(float dy) {
		this.dy = dy;
	}
}
