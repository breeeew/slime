package ru.SlimeEngine;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends GameObject {
	/**
	 * ����� �����
	 */
	Point center;
	/**
	 * ������ �����
	 */
	float radius;
	/**
	 * ����� ����
	 */
	float arcStop;

	/**
	 * ������ ����
	 */
	float arcStart;

	/**
	 * ����������� {@link Circle}
	 * 
	 * @param p
	 *            - ����� ����������
	 * @param radius
	 *            - ������ ����������
	 */
	Circle(Point p, float radius) {
		this.center = p;
		this.radius = radius;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * ����������� {@link Circle}
	 * 
	 * @param p
	 *            - ����� ����������
	 * @param radius
	 *            - ������ ����������
	 * @param arcLen
	 *            - ������ ����
	 * @param arcStart
	 *            - ����� ����
	 */
	Circle(Point p, int radius, int arcLen, int arcStart) {
		this.center = p;
		this.radius = radius;
		this.arcStop = arcLen;
		this.arcStart = arcStart;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * �����������
	 * 
	 * @param x
	 *            - ���������� �� x ������ ����������
	 * @param y
	 *            - ���������� �� y ������ ����������
	 * @param radius
	 *            - ������
	 */
	Circle(int x, int y, int radius) {
		this.center = new Point(x, y);
		this.radius = radius;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * �����������
	 * 
	 * @param x
	 *            - ���������� �� x ������ ����������
	 * @param y
	 *            - ���������� �� y ������ ����������
	 * @param radius
	 *            - ������
	 */
	Circle(float x, float y, float radius) {
		this.center = new Point(x, y);
		this.radius = radius;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	@Override
	public
	void update() {
		center.update();
	}

	@Override
	public
	boolean isSelected(float x, float y) {
		if ((x - center.getX()) * (x - center.getX()) + (y - center.getY()) * (y - center.getY()) < radius
				* radius)
			return true;
		else
			return false;
	}

	@Override
	public
	void draw(Canvas c, Paint p) {
		c.drawCircle(center.getX(), center.getY(), radius, p);		
	}
}
