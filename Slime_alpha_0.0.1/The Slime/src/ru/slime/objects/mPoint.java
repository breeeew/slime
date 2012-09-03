package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class mPoint extends GameObject{
	/**
	* ���������� �� ����
	*/
	private float x, y; // ������� �����
	/**
	* ��������� �� ����
	*/
	private float axX = 1, axY = 1; // ���������
	/**
	* �������� �� ����
	*/
	private float dx = 0, dy = 0; // ��������
	// ����� ��� ���������� ��������� ����� �� ���������
	// ������ �� �� �������� � ���������
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x = this.dx + this.x;
		this.y = this.dy + this.y;
		this.dx = this.dx * this.axX;
		this.dy = this.dy * this.axY;
	}
	
	// ����� ��� ����������� ������������� �� ���������� �����
    // (x,y)����������� ����� �����
	@Override
	public boolean isSelected(float x, float y) {
		// TODO Auto-generated method stub
		if (this.x == x && this.y == y)
			return true;
		else
			return false;
	}
	
	@Override
	public void draw(Canvas c, Paint p) {
		// TODO Auto-generated method stub
		p.setColor(Color.RED);
		p.setStrokeWidth(5);
		c.drawPoint(x, y, p);
	}
	/**
     * ����������� ��������� ��� ������������� ��������
     *
     * @param x
     *            - ���������� �� x
     * @param y
     *            - ���������� �� y
     */
    public mPoint(int x, int y) { 
		// �����������
		this.x = x; //
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* ����������� ��������� ��� �������� � ��������� �������
	*
	* @param x
	*            - ���������� �� x
	* @param y
	*            - ���������� �� y
	*/
	public mPoint(float x, float y) {// ��� ����������
		this.x = x;
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* ����������� ��������� ������ �������� � ��������� �������
	*
	* @param x
	*            - ���������� �� x
	* @param y
	*            - ���������� �� y
	* @param dx
	* 			  - �������� �� x   
	* @param dy
	* 			  - �������� �� y          
	*/
	public mPoint(float x, float y, float axX, float axY) { 
		// �����������
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* ����������� ��������� ������ ������������� ��������
	*
	* @param x
	*            - ���������� �� x
	* @param y
	*            - ���������� �� y
	* @param dx
	* 			  - �������� �� x   
	* @param dy
	* 			  - �������� �� y          
	*/
	public mPoint(int x, int y, int dx, int dy) { 
		// �����������
		this.x = x; //
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* ����������� ��������� ������ ������������� ��������
	*
	* @param x
	*            - ���������� �� x
	* @param y
	*            - ���������� �� y
	* @param dx
	* 			  - �������� �� x   
	* @param dy
	* 			  - �������� �� y
	* @param axX - ��������� �� x (���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������)
	* @param axY - ��������� �� y (���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������)          
	*/
	public mPoint(int x, int y, int dx, int dy, int axX, int axY) { 
		// �����������
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* ����������� ��������� ������ �������� � ��������� �������
	*
	* @param x
	*            - ���������� �� x
	* @param y
	*            - ���������� �� y
	* @param dx
	* 			  - �������� �� x   
	* @param dy
	* 			  - �������� �� y
	* @param axX - ��������� �� x (���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������)
	* @param axY - ��������� �� y (���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������)          
	*/
	public mPoint(float x, float y, float dx, float dy, float axX, float axY) { 
		// �����������
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	// ������ ��� ������ ��� ��������� � ��������� ��������.
	/**
	* @return ���������� ���������� �� ��� X
	*/
	public float getX() {
		return x;
	}

	/**
	* ������������� ���������� �� ��� �
	*
	* @param x
	*            - ����� ����������
	*/
	public void setX(float x) {
		this.x = x;
	}
 
	/**
	* @return ���������� ���������� �� ��� Y
	*/
	public float getY() {
		return y;
	}
 
	/**
	* ������������� ���������� �� ��� Y
	*
	* @param y
	*            - ����� ����������
	*/
	public void setY(float y) {
		this.y = y;
	}
 
	/**
	* @return ���������� ��������� �� ��� X ���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������
	*/
	public float getAxX() {
		return axX;
	}
 
	/**
	* ������������� ��������� �� ��� X
	*
	* @param axX
	*            - ����� ���������. ���� ������ 1 - �� ������ ���������� ����
	*            ������ 1 - �� �����������
	*/
	public void setAxX(float axX) {
		this.axX = axX;
	}
 
	/**
	* @return ���������� ��������� �� ��� Y ���� ������ 1 - �� ������
	*         ���������� ���� ������ 1 - �� �����������
	*/
	public float getAxY() {
		return axY;
	}
 
	/**
	* ������������� ��������� �� ��� Y
	*
	* @param axY
	*            - ����� ���������. ���� ������ 1 - �� ������ ���������� ����
	*            ������ 1 - �� �����������
	*/
	public void setAxY(float axY) {
		this.axY = axY;
	}

	/**
	* @return ���������� �������� �� ��� X
	*/
	public float getDx() {
		return dx;
	}
	
	/**
	* ������������� �������� �� ��� X
	*
	* @param dx
	*            - ����� ��������
	*/
	public void setDx(float dx) {
		this.dx = dx;
	}

	/**
	* @return ���������� �������� �� ��� Y
	*/
	public float getDy() {
		return dy;
	}
 
	/**
	* ������������� �������� �� ��� y
	*
	* @param dy
	*            - ����� ��������
	*/
	public void setDy(float dy) {
		this.dy = dy;
	}
}
