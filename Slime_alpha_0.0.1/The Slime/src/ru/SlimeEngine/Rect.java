package ru.SlimeEngine;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rect extends GameObject {

	/**
	 * ����� - ������� ��������������
	 */
	Point TopLeft, BottRight, TopRight, BottLeft;

	/**
	 * ����� ��������������
	 */
	Line[] Lines = new Line[4];
	

	/**
	 * ����������� {@link Rect}
	 * 
	 * @param p1
	 *            - �������� ����� �����
	 * @param p2
	 *            - ������ ������ �����
	 */
	public Rect(Point p1, Point p2) {
		this.TopLeft = p1;
		this.BottRight = p2;
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 * ����������� {@link Rect}
	 * 
	 * @param x1
	 *            - �������� ����� ����� �
	 * @param y1
	 *            - �������� ����� ����� y
	 * @param x2
	 *            - ������ ������ ����� x
	 * @param y2
	 *            - ������ ������ ����� y
	 */
	public Rect(int x1, int y1, int x2, int y2) {
		this.TopLeft = new Point(x1, y1);
		this.BottRight = new Point(x1, y1);
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 * ����������� {@link Rect}
	 * 
	 * @param x1
	 *            - �������� ����� ����� �
	 * @param y1
	 *            - �������� ����� ����� y
	 * @param x2
	 *            - ������ ������ ����� x
	 * @param y2
	 *            - ������ ������ ����� y
	 */
	public Rect(float x1, float y1, float x2, float y2) {
		this.TopLeft = new Point(x1, y1);
		this.BottRight = new Point(x1, y1);
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 * ��������� ����� ��� ��������� ��������� �����
	 */
	public void refreshLines() {
		this.TopRight = new Point(this.BottRight.getX(), this.TopLeft.getY());
		this.BottLeft = new Point(this.TopLeft.getX(), this.BottRight.getY());
		this.Lines[0] = new Line(this.TopLeft, this.TopRight);
		this.Lines[1] = new Line(this.TopRight, this.BottRight);
		this.Lines[2] = new Line(this.BottRight, this.BottLeft);
		this.Lines[3] = new Line(this.BottLeft, this.TopLeft);
	}

	/**
	 * @return ���������� ������ ����� �� ������� ������� �������������
	 */
	public Line[] getLines() {
		return Lines;
	}

	@Override
	public
	void update() {
		// TODO Auto-generated method stub
		TopLeft.update();
		BottRight.update();
		TopRight.update();
		BottLeft.update();
		this.refreshLines();
	}

	@Override
	public
	boolean isSelected(float x, float y) {
		if (x > TopLeft.getX() && x < TopRight.getX() && y > TopLeft.getY() && y < BottRight.getY())
			return false;
		else
			return true;
	}

	@Override
	public
	void draw(Canvas c, Paint p) {
		for (Line l : Lines)
		{
			l.draw(c, p);
		}
		
	}

}
