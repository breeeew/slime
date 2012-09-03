package ru.slime.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * �����, ����������� ���������� ������� ������� (�����������)
 * @author �������� �������
 * @version 1.0
 * @created 28-���-2012 11:32:03
 */
public abstract class GameObject {
	 /**
	 * ��������� �� ��� �������-���������� ������ GameObject. ������������� �
	 * ������������� ������� ��������. ��� ������� ������ ������� ����.
	 */
	private int typeOfGameObject;
	public int getTypeofGameObject(){
		return typeOfGameObject;
	}
	public void setTypeOfGameObject(int type){
		typeOfGameObject = type;
	}
	
	/**
	 * ���� ��������
	 */
	public static final int TYPE_POINT = 1;
	public static final int TYPE_LINE = 2;
	public static final int TYPE_POLYLINE = 3;
	public static final int TYPE_RECT = 4;
	public static final int TYPE_CIRCLE = 5;
	public static final int TYPE_SIMPLESPRITE = 6;
	
	public abstract void update();
	
	/**
	 * ���������, �������� �� ����� � �����-�� �� ����������
	 *
	 * @param x
	 * - ���������� �� ��� x
	 * @param y
	 * - ���������� �� ��� y
	 * @return ���������� ������, ���� �������� � ����, ���� ���
	 */
	public abstract boolean isSelected(float x, float y);
	
	 /**
	 * ����� ���������
	 * @param c - ����� ��� ���������
	 * @param p - ����� ��� ���������
	 */
	 public abstract void draw(Canvas c, Paint p);
	
//	/**
//	 * ����
//	 */
//	protected Color cvet;
//	/**
//	 * ������ ����������� (����� �� ������������ ���)
//	 */
//	private int mHeight;
//	/**
//	 * ����� ������� ���� ��������������, ��������������� ������. � � �������
//	 * ���������� ������������ ��������� ������� �� ���������
//	 */
//	protected Point mPoint;
//	/**
//	 * ������ ����������� (����� �� ������������ ���)
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
//	 * ����� ����� ����� ����, ������� ����� ���������
//	 */
//	protected void doInBackground(){
//
//	}
//
//	/**
//	 * ����� ��������� ������� �� ��������� �����
//	 * 
//	 * @param canvas
//	 */
//	public void draw(Canvas canvas){
//
//	}
//
//	/**
//	 * ����
//	 */
//	public Color getcvet(){
//		return cvet;
//	}
//
//	/**
//	 * ����� ������� ���� ��������������, ��������������� ������. � � �������
//	 * ���������� ������������ ��������� ������� �� ���������
//	 */
//	public Point getmPoint(){
//		return mPoint;
//	}
//
//	/**
//	 * ����� ���, ������� ����� ���������� ����� �������� �������
//	 */
//	protected void onPreExecute(){
//
//	}
//
//	/**
//	 * ���������� ��� ������ ������ � doInBackground() ������ publishProgress(param)
//	 */
//	protected void onProgressUpdate(){
//
//	}
//
//	/**
//	 * ����
//	 * 
//	 * @param newVal
//	 */
//	public void setcvet(Color newVal){
//		cvet = newVal;
//	}
//
//	/**
//	 * ����� ������� ���� ��������������, ��������������� ������. � � �������
//	 * ���������� ������������ ��������� ������� �� ���������
//	 * 
//	 * @param newVal
//	 */
//	public void setmPoint(Point newVal){
//		mPoint = newVal;
//	}

}