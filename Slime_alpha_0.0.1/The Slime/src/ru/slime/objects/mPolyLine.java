package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class mPolyLine extends GameObject {

	private mPoint[] Points;
    private int size = 0;
 
    private mLine[] Lines;
 
    /**
     * ����������� {@link mPolyLine}
     *
     * @param size
     *            ���������� ����� � ���������
     * @param data
     *            - ������ ���� {@link mPoint} ���������� ���������� � ������
     */
    public mPolyLine(int size, mPoint[] data) {
        Points = data;
        this.size = size;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * ����������� {@link mPolyLine}
     *
     * @param data
     *            - ������ ���� {@link mPoint} ���������� ���������� � ������.
     *            ���������� ������ ��������� ������������ �� ����� �������
     *            �����.
     */
    public mPolyLine(mPoint[] data) {
        Points = data;
        this.size = data.length;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * ����������� {@link mPolyLine}
     *
     * @param dataXY
     *            - ������ ��� ��������� ����� ������ ����� � ���� ���������� ��
     *            x ������ �� y
     */
    public mPolyLine(int[] dataXY) {
        this.size = dataXY.length / 2;
        Points = new mPoint[this.size];
        for (int i = 0; i < this.size; i++) {
            Points[i] = new mPoint(dataXY[i * 2], dataXY[i * 2 + 1]);
        }
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * @return ���������� ������ ����� - ������ ���������
     */
    public mPoint[] getPoints() {
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
        Lines = new mLine[this.size - 1];
        for (int i = 0; i < this.size - 1; i++) {
            Lines[i] = new mLine(Points[i], Points[i + 1]);
        }
    }
 
    /**
     * @return ���������� ������ �������� �� ������� ������� ���������
     */
    public mLine[] getLines() {
        return Lines;
    }
 
    @Override
	public
    void update() {
        for (mPoint a : Points) {
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
        for (mLine l :Lines)
        {
            l.draw(c, p);
        }
 
    }
}
