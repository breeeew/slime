package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Класс, описывающий визуальные игровые объекты (Абстрактный)
 * @author Байрамлы Абдулла
 * @version 1.0
 * @created 28-фев-2012 11:32:03
 */
public abstract class GameObject {
	 /**
	 * Указывает на тип объекта-наследника класса GameObject. Присваивается в
	 * конструкторах классов потомков. Для каждого класса потомка свой.
	 */
	private int typeOfGameObject;
	public int getTypeofGameObject(){
		return typeOfGameObject;
	}
	public void setTypeOfGameObject(int type){
		typeOfGameObject = type;
	}
	
	/**
	 * Типы объектов
	 */
	public static final int TYPE_POINT = 1;
	public static final int TYPE_LINE = 2;
	public static final int TYPE_POLYLINE = 3;
	public static final int TYPE_RECT = 4;
	public static final int TYPE_CIRCLE = 5;
	public static final int TYPE_SIMPLESPRITE = 6;
	
	public abstract void update();
	
	/**
	 * Проверяет, попадает ли точка в какой-то из примитивов
	 *
	 * @param x
	 * - координата по оси x
	 * @param y
	 * - координата по оси y
	 * @return Возвращает истину, если попадает и ложь, если нет
	 */
	public abstract boolean isSelected(float x, float y);
	
	 /**
	 * Метод отрисовки
	 * @param c - канва для отрисовки
	 * @param p - кисть для отрисовки
	 */
	 public abstract void draw(Canvas c, Paint p);
	
//	/**
//	 * Цвет
//	 */
//	protected Color cvet;
//	/**
//	 * Высота изображения (чтобы не загромождать код)
//	 */
//	private int mHeight;
//	/**
//	 * Левый верхний угол прямоугольника, ограничивающего объект. С её помощью
//	 * однозначно определяется положение объекта на плоскости
//	 */
//	protected Point mPoint;
//	/**
//	 * Ширина изображения (чтобы не загромождать код)
//	 */
//	private int mWidth;
//
//	public GameObject(){
//
//	}
//
//	public void finalize() throws Throwable {
//
//	}
//
//	/**
//	 * Здесь будет кусок кода, которий нужно выполнить
//	 */
//	protected void doInBackground(){
//
//	}
//
//	/**
//	 * Метод рисования объекта на указанной канве
//	 * 
//	 * @param canvas
//	 */
//	public void draw(Canvas canvas){
//
//	}
//
//	/**
//	 * Цвет
//	 */
//	public Color getcvet(){
//		return cvet;
//	}
//
//	/**
//	 * Левый верхний угол прямоугольника, ограничивающего объект. С её помощью
//	 * однозначно определяется положение объекта на плоскости
//	 */
//	public Point getmPoint(){
//		return mPoint;
//	}
//
//	/**
//	 * Здесь код, который будет выполнятся перед основной работой
//	 */
//	protected void onPreExecute(){
//
//	}
//
//	/**
//	 * Вызывается при каждом вызове в doInBackground() метода publishProgress(param)
//	 */
//	protected void onProgressUpdate(){
//
//	}
//
//	/**
//	 * Цвет
//	 * 
//	 * @param newVal
//	 */
//	public void setcvet(Color newVal){
//		cvet = newVal;
//	}
//
//	/**
//	 * Левый верхний угол прямоугольника, ограничивающего объект. С её помощью
//	 * однозначно определяется положение объекта на плоскости
//	 * 
//	 * @param newVal
//	 */
//	public void setmPoint(Point newVal){
//		mPoint = newVal;
//	}

}