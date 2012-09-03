package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class mCircle extends GameObject {
	/**
	 * Центр круга
	 */
	mPoint center;
	/**
	 * Радиус круга
	 */
	float radius;
	/**
	 * Конец дуги
	 */
	float arcStop;

	/**
	 * Начало дуги
	 */
	float arcStart;

	/**
	 * Конструктор {@link mCircle}
	 * 
	 * @param p
	 *            - Центр окружности
	 * @param radius
	 *            - радиус окружности
	 */
	mCircle(mPoint p, float radius) {
		this.center = p;
		this.radius = radius;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * Конструктор {@link mCircle}
	 * 
	 * @param p
	 *            - Центр окружности
	 * @param radius
	 *            - радиус окружности
	 * @param arcLen
	 *            - начало дуги
	 * @param arcStart
	 *            - конец дуги
	 */
	mCircle(mPoint p, int radius, int arcLen, int arcStart) {
		this.center = p;
		this.radius = radius;
		this.arcStop = arcLen;
		this.arcStart = arcStart;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * конструктор
	 * 
	 * @param x
	 *            - координата по x центра окружности
	 * @param y
	 *            - координата по y центра окружности
	 * @param radius
	 *            - радиус
	 */
	mCircle(int x, int y, int radius) {
		this.center = new mPoint(x, y);
		this.radius = radius;
		this.setTypeOfGameObject(TYPE_CIRCLE);
	}

	/**
	 * конструктор
	 * 
	 * @param x
	 *            - координата по x центра окружности
	 * @param y
	 *            - координата по y центра окружности
	 * @param radius
	 *            - радиус
	 */
	mCircle(float x, float y, float radius) {
		this.center = new mPoint(x, y);
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
