package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public class mPolyLine extends GameObject {

	private mPoint[] Points;
    private int size = 0;
 
    private mLine[] Lines;
 
    /**
     * Конструктор {@link mPolyLine}
     *
     * @param size
     *            количество точек в полилинии
     * @param data
     *            - массив типа {@link mPoint} содержащий информацию о точках
     */
    public mPolyLine(int size, mPoint[] data) {
        Points = data;
        this.size = size;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * Конструктор {@link mPolyLine}
     *
     * @param data
     *            - массив типа {@link mPoint} содержащий информацию о точках.
     *            количество вершин полилинии определяется из длины массива
     *            точек.
     */
    public mPolyLine(mPoint[] data) {
        Points = data;
        this.size = data.length;
        this.refreshLines();
        this.setTypeOfGameObject(GameObject.TYPE_POLYLINE);
    }
 
    /**
     * Конструктор {@link mPolyLine}
     *
     * @param dataXY
     *            - массив пар координат точек первая точка в паре координата по
     *            x вторая по y
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
     * @return возвращает массив точек - вершин полилинии
     */
    public mPoint[] getPoints() {
        return Points;
    }
 
    /**
     * @return возвращает количество вершин в полилинии
     */
    public int getSize() {
        return size;
    }
 
    /**
     * Обновляет линии в полилинии если изменились точки
     */
    public void refreshLines() {
        Lines = new mLine[this.size - 1];
        for (int i = 0; i < this.size - 1; i++) {
            Lines[i] = new mLine(Points[i], Points[i + 1]);
        }
    }
 
    /**
     * @return Возвращает массив отрезков из которых состоит полилиния
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
