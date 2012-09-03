package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class mRect extends GameObject {

	/**
	 * точка - вершина пр€моугольника
	 */
	mPoint TopLeft, BottRight, TopRight, BottLeft;

	/**
	 * ребра пр€моугольника
	 */
	mLine[] Lines = new mLine[4];
	

	/**
	 *  онструктор {@link mRect}
	 * 
	 * @param p1
	 *            - верзхн€€ лева€ точка
	 * @param p2
	 *            - нижн€€ права€ точка
	 */
	public mRect(mPoint p1, mPoint p2) {
		this.TopLeft = p1;
		this.BottRight = p2;
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 *  онструктор {@link mRect}
	 * 
	 * @param x1
	 *            - верзхн€€ лева€ точка х
	 * @param y1
	 *            - верзхн€€ лева€ точка y
	 * @param x2
	 *            - нижн€€ права€ точка x
	 * @param y2
	 *            - нижн€€ права€ точка y
	 */
	public mRect(int x1, int y1, int x2, int y2) {
		this.TopLeft = new mPoint(x1, y1);
		this.BottRight = new mPoint(x1, y1);
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 *  онструктор {@link mRect}
	 * 
	 * @param x1
	 *            - верзхн€€ лева€ точка х
	 * @param y1
	 *            - верзхн€€ лева€ точка y
	 * @param x2
	 *            - нижн€€ права€ точка x
	 * @param y2
	 *            - нижн€€ права€ точка y
	 */
	public mRect(float x1, float y1, float x2, float y2) {
		this.TopLeft = new mPoint(x1, y1);
		this.BottRight = new mPoint(x1, y1);
		refreshLines();
		this.setTypeOfGameObject(TYPE_RECT);
	}

	/**
	 * ќбновл€ет линии при изменении координат точек
	 */
	public void refreshLines() {
		this.TopRight = new mPoint(this.BottRight.getX(), this.TopLeft.getY());
		this.BottLeft = new mPoint(this.TopLeft.getX(), this.BottRight.getY());
		this.Lines[0] = new mLine(this.TopLeft, this.TopRight);
		this.Lines[1] = new mLine(this.TopRight, this.BottRight);
		this.Lines[2] = new mLine(this.BottRight, this.BottLeft);
		this.Lines[3] = new mLine(this.BottLeft, this.TopLeft);
	}

	/**
	 * @return ¬озвращает массив линий из которых состоит пр€моугольник
	 */
	public mLine[] getLines() {
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
		for (mLine l : Lines)
		{
			l.draw(c, p);
		}
		
	}

}
