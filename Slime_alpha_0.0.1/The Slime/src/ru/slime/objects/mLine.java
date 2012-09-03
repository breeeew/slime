package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class mLine extends GameObject{
	 private mPoint p1, p2; // ����� �������
	 private Path ptCurve;
	 private int color = Color.RED;
	 // ������������
	 /**
	 * ����������� ������� ��������� � �������� ���������� ���������� ���� �����
	 *
	 * @param x1
	 * - ���������� ������ ����� �� x
	 * @param y1
	 * - ���������� ������ ����� �� y
	 * @param x2
	 * - ���������� ������ ����� �� x
	 * @param y2
	 * - ���������� ������ ����� �� y
	 */
	 public mLine(int x1, int y1, int x2, int y2) {
	 ptCurve = new Path();
	 p1 = new mPoint(x1, y1);
	 p2 = new mPoint(x2, y2);
	 //ptCurve.moveTo(x, y)
	 this.setTypeOfGameObject(GameObject.TYPE_LINE);
	 }
	 
	 /**
	 * ����������� ������� ��������� � �������� ���������� ���������� ���� �����
	 *
	 * @param x1
	 * - ���������� ������ ����� �� x
	 * @param y1
	 * - ���������� ������ ����� �� y
	 * @param x2
	 * - ���������� ������ ����� �� x
	 * @param y2
	 * - ���������� ������ ����� �� y
	 */
	 public mLine(float x1, float y1, float x2, float y2) {
	 p1 = new mPoint(x1, y1);
	 p2 = new mPoint(x2, y2);
	 this.setTypeOfGameObject(GameObject.TYPE_LINE);
	 }
	 
	 /**
	 * ����������� ������� ��������� � �������� ���������� ��� ����� ���� mPoint
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
		 * ����������� ������� ��������� � �������� ���������� ��� ����� ���� mPoint � ���� color
		 *
		 * @param p1 - ������ �������
		 * @param p2 - ����� �������
		 * @param color - ����
		 */
		 public mLine(mPoint p1, mPoint p2, int color) {
		 this.p1 = p1;
		 this.p2 = p2;
		 this.color = color;
		 this.setTypeOfGameObject(GameObject.TYPE_LINE);
		 }
	 
	 /**
	 * @return ���������� ������ ����� �������
	 */
	 public mPoint getP1() {
	 return p1;
	 }
	 
	 /**
	 * ������������� ����� ����� ������� (��� ������)
	 *
	 * @param p1
	 * - ����� ������ �������
	 */
	 public void setP1(mPoint p1) {
	 this.p1 = p1;
	 }
	 
	 /**
	 * @return ���������� ������ ����� �������
	 */
	 public mPoint getP2() {
	 return p2;
	 }
	 
	 /**
	 * ������������� ������ ����� ������� (��� �����)
	 *
	 * @param p2
	 * - ����� ����� �������
	 */
	 public void setP2(mPoint p2) {
	 this.p2 = p2;
	 }
	 
	 // ����� ��������� ��������� ����� ������ �������
	 @Override
	public
	 void update() {
	 p1.update();
	 p2.update();
	 }
	 
	 // ����� ��������
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
