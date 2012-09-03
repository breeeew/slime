package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class mLine extends GameObject{
	 private mPoint p1, p2; // концы отрезка
	 private Path ptCurve;
	 private int color = Color.RED;
	 // конструкторы
	 /**
	 * Конструктор отрезка принимает в качестве параметров координаты двух точек
	 *
	 * @param x1
	 * - координата первой точки по x
	 * @param y1
	 * - координата первой точки по y
	 * @param x2
	 * - координата второй точки по x
	 * @param y2
	 * - координата второй точки по y
	 */
	 public mLine(int x1, int y1, int x2, int y2) {
	 ptCurve = new Path();
	 p1 = new mPoint(x1, y1);
	 p2 = new mPoint(x2, y2);
	 //ptCurve.moveTo(x, y)
	 this.setTypeOfGameObject(GameObject.TYPE_LINE);
	 }
	 
	 /**
	 * Конструктор отрезка принимает в качестве параметров координаты двух точек
	 *
	 * @param x1
	 * - координата первой точки по x
	 * @param y1
	 * - координата первой точки по y
	 * @param x2
	 * - координата второй точки по x
	 * @param y2
	 * - координата второй точки по y
	 */
	 public mLine(float x1, float y1, float x2, float y2) {
	 p1 = new mPoint(x1, y1);
	 p2 = new mPoint(x2, y2);
	 this.setTypeOfGameObject(GameObject.TYPE_LINE);
	 }
	 
	 /**
	 * Конструктор отрезка принимает в качестве параметров две точки типа mPoint
	 *
	 * @param p1
	 * @param p2
	 */
	 public mLine(mPoint p1, mPoint p2) {
	 this.p1 = p1;
	 this.p2 = p2;
	 this.setTypeOfGameObject(GameObject.TYPE_LINE);
	 }
	 /**
		 * Конструктор отрезка принимает в качестве параметров две точки типа mPoint и цвет color
		 *
		 * @param p1 - начало отрезка
		 * @param p2 - конец отрезка
		 * @param color - цвет
		 */
		 public mLine(mPoint p1, mPoint p2, int color) {
		 this.p1 = p1;
		 this.p2 = p2;
		 this.color = color;
		 this.setTypeOfGameObject(GameObject.TYPE_LINE);
		 }
	 
	 /**
	 * @return Возвращает первую точку отрезка
	 */
	 public mPoint getP1() {
	 return p1;
	 }
	 
	 /**
	 * устанавливает перую точку отрезка (его начало)
	 *
	 * @param p1
	 * - новое начало отрезка
	 */
	 public void setP1(mPoint p1) {
	 this.p1 = p1;
	 }
	 
	 /**
	 * @return Возвращает вторую точку отрезка
	 */
	 public mPoint getP2() {
	 return p2;
	 }
	 
	 /**
	 * устанавливает вторую точку отрезка (его конец)
	 *
	 * @param p2
	 * - новый конец отрезка
	 */
	 public void setP2(mPoint p2) {
	 this.p2 = p2;
	 }
	 
	 // метод обновляет положения обоих концов отрезка
	 @Override
	public
	 void update() {
	 p1.update();
	 p2.update();
	 }
	 
	 // метод заглушка
	 @Override
	public
	 boolean isSelected(float x, float y) {
	 return false;
	 }
	 
	 @Override
	public
	 void draw(Canvas c, Paint p) {
		 Paint a = new Paint(Paint.ANTI_ALIAS_FLAG);
		 a.setColor(Color.rgb(0, 148, 255));
		 a.setAlpha(128);
		 a.setStrokeWidth(3);
		 
		 a.setStyle(Paint.Style.STROKE);
		 c.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), a);
	 }

}
