package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class mPoint extends GameObject{
	/**
	* Координаты по осям
	*/
	private float x, y; // Позиция точки
	/**
	* Ускорения по осям
	*/
	private float axX = 1, axY = 1; // ускорение
	/**
	* Скорости по осям
	*/
	private float dx = 0, dy = 0; // скорость
	// метод для обновления положения точки на плоскости
	// изходя из ее скорости и ускорения
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x = this.dx + this.x;
		this.y = this.dy + this.y;
		this.dx = this.dx * this.axX;
		this.dy = this.dy * this.axY;
	}
	
	// Метод для определения соответствуют ли координаты точки
    // (x,y)координатам нашей точки
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
     * Конструктор принимает два целочисленных значения
     *
     * @param x
     *            - координата по x
     * @param y
     *            - координата по y
     */
    public mPoint(int x, int y) { 
		// конструктор
		this.x = x; //
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* Конструктор принимает два значения с плавающей запятой
	*
	* @param x
	*            - координата по x
	* @param y
	*            - координата по y
	*/
	public mPoint(float x, float y) {// Еще коструктор
		this.x = x;
		this.y = y;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* Конструктор принимает четыре значения с плавающей запятой
	*
	* @param x
	*            - координата по x
	* @param y
	*            - координата по y
	* @param dx
	* 			  - скорость по x   
	* @param dy
	* 			  - скорость по y          
	*/
	public mPoint(float x, float y, float axX, float axY) { 
		// конструктор
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* Конструктор принимает четыре целочисленных значения
	*
	* @param x
	*            - координата по x
	* @param y
	*            - координата по y
	* @param dx
	* 			  - скорость по x   
	* @param dy
	* 			  - скорость по y          
	*/
	public mPoint(int x, int y, int dx, int dy) { 
		// конструктор
		this.x = x; //
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* Конструктор принимает четыре целочисленных значения
	*
	* @param x
	*            - координата по x
	* @param y
	*            - координата по y
	* @param dx
	* 			  - скорость по x   
	* @param dy
	* 			  - скорость по y
	* @param axX - ускорение по x (если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется)
	* @param axY - ускорение по y (если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется)          
	*/
	public mPoint(int x, int y, int dx, int dy, int axX, int axY) { 
		// конструктор
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	/**
	* Конструктор принимает четыре значения с плавающей запятой
	*
	* @param x
	*            - координата по x
	* @param y
	*            - координата по y
	* @param dx
	* 			  - скорость по x   
	* @param dy
	* 			  - скорость по y
	* @param axX - ускорение по x (если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется)
	* @param axY - ускорение по y (если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется)          
	*/
	public mPoint(float x, float y, float dx, float dy, float axX, float axY) { 
		// конструктор
		this.x = x; //
		this.y = y;
		this.axX = axX;
		this.axY = axY;
		this.dx = dx;
		this.dy = dy;
		this.setTypeOfGameObject(GameObject.TYPE_POINT);
	}
	
	// Дальше все методы для получения и установки значений.
	/**
	* @return возвращает координату по оси X
	*/
	public float getX() {
		return x;
	}

	/**
	* Устанавливает координату по оси х
	*
	* @param x
	*            - новая координата
	*/
	public void setX(float x) {
		this.x = x;
	}
 
	/**
	* @return возвращает координату по оси Y
	*/
	public float getY() {
		return y;
	}
 
	/**
	* Устанавливает координату по оси Y
	*
	* @param y
	*            - новая координата
	*/
	public void setY(float y) {
		this.y = y;
	}
 
	/**
	* @return возвращает ускорение по оси X если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется
	*/
	public float getAxX() {
		return axX;
	}
 
	/**
	* устанавливает ускорение по оси X
	*
	* @param axX
	*            - новое ускорение. если больше 1 - то объект ускоряется если
	*            меньше 1 - то замедляется
	*/
	public void setAxX(float axX) {
		this.axX = axX;
	}
 
	/**
	* @return возвращает ускорение по оси Y если больше 1 - то объект
	*         ускоряется если меньше 1 - то замедляется
	*/
	public float getAxY() {
		return axY;
	}
 
	/**
	* устанавливает ускорение по оси Y
	*
	* @param axY
	*            - новое ускорение. если больше 1 - то объект ускоряется если
	*            меньше 1 - то замедляется
	*/
	public void setAxY(float axY) {
		this.axY = axY;
	}

	/**
	* @return возвращает скорость по оси X
	*/
	public float getDx() {
		return dx;
	}
	
	/**
	* Устанавливает скорость по оси X
	*
	* @param dx
	*            - новая скорость
	*/
	public void setDx(float dx) {
		this.dx = dx;
	}

	/**
	* @return возвращает скорость по оси Y
	*/
	public float getDy() {
		return dy;
	}
 
	/**
	* Устанавливает скорость по оси y
	*
	* @param dy
	*            - новая скорость
	*/
	public void setDy(float dy) {
		this.dy = dy;
	}
}
