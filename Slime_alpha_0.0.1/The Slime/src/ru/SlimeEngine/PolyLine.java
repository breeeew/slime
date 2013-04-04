package ru.SlimeEngine;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PolyLine extends GameObject {

	private Point[] Points;
    private int size = 0;
 
    private Line[] Lines;
 
    /**
     * ����������� {@link PolyLine}
     *
     * @param size
     *            ���������� ����� � ���������
     * @param data
     *            - ������ ���� {@link Point} ���������� ���������� � ������
     */
    public PolyLine(int size, Point[] data) {
        Points = data;
        this.size = size;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * ����������� {@link PolyLine}
     *
     * @param data
     *            - ������ ���� {@link Point} ���������� ���������� � ������.
     *            ���������� ������ ��������� ������������ �� ����� �������
     *            �����.
     */
    public PolyLine(Point[] data) {
        Points = data;
        this.size = data.length;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * ����������� {@link PolyLine}
     *
     * @param dataXY
     *            - ������ ��� ��������� ����� ������ ����� � ���� ���������� ��
     *            x ������ �� y
     */
    public PolyLine(int[] dataXY) {
        this.size = dataXY.length / 2;
        Points = new Point[this.size];
        for (int i = 0; i < this.size; i++) {
            Points[i] = new Point(dataXY[i * 2], dataXY[i * 2 + 1]);
        }
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * @return ���������� ������ ����� - ������ ���������
     */
    public Point[] getPoints() {
        return Points;
    }
 
    /**
     * @return ���������� ���������� ������ � ���������
     */
    public int getSize() {
        return size;
    }
 
    /**
     * ��������� ����� � ��������� ���� ���������� �����
     */
    public void refreshLines() {
        Lines = new Line[this.size - 1];
        for (int i = 0; i < this.size - 1; i++) {
            Lines[i] = new Line(Points[i], Points[i + 1]);
        }
    }
 
    /**
     * @return ���������� ������ �������� �� ������� ������� ���������
     */
    public Line[] getLines() {
        return Lines;
    }
 
    @Override
	public
    void update() {
        for (Point a : Points) {
            a.update();
        }
        this.refreshLines();
    }
 
    @Override
	public
    boolean isSelected(float x, float y) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
	public
    void draw(Canvas c, Paint p) {
        for (Line l :Lines)
        {
            l.draw(c, p);
        }
 
    }
}
