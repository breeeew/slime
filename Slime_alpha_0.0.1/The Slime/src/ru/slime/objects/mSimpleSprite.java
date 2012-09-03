package ru.slime.objects;

import java.io.IOException;

import ru.SlimeEngine.mSettings;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
/**
 * @author �������� �������
 * @version 0.1 ����� ��� ������ �� ��������� ����������� �� {@link Point}
 */
public class mSimpleSprite extends mPoint {
	/**
     * Bitmap �������
     */
	Bitmap bmp;
	
	 /**
     * ��������� ��� �������� ������� (���� �� �����������)
     */
    float rpx = 0, rpy = 0;
    /**
     * ������ (width) � ������ (height) �������
     */
    int width = 0, height = 0;
    /**
     * �������� �� ����� ������� ��� ���� �������� ���� �� �����������
     */
    boolean centralAxis = true;
    /**
     * ������� ��� ��������� ������� �� ����� (��� ��������. ���� �� �����������)
     */
    Matrix matrix=new Matrix();
    
    boolean selected=false;
    
    private void AutoSize()
    {
        if (mSettings.AutoScale)
        {
            this.resize(mSettings.ScaleFactorX, mSettings.ScaleFactorY);
        }
        this.refreshAll();
    }

    /**
     * �����������
     *
     * @param x
     *            ��������� ������� �� ��� x
     * @param y
     *            ��������� ������� �� ��� y
     * @param bmp
     *            ������ �������
     */
    public mSimpleSprite(float x, float y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * ����������� ��������� ������ �� �����
     *
     * @param x
     *            ��������� ������� �� ��� x
     * @param y
     *            ��������� ������� �� ��� y
     * @param bmp
     *            ������ �������
     */
    public mSimpleSprite(int x, int y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * ����������� ��������� ������ �� �����
     *
     * @param s
     *            ���� � ����� �� �������� ����� �������� ������
     */
    public mSimpleSprite(String s) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeFile(s);
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * ����������� �������� ������ �� �������
     *
     * @param s
     *            ���� � ����� � ����� assets ������ ������ ���� �������� ������
     * @param am
     *            - ��������� {@link AssetManager}
     */
    public mSimpleSprite(String s, AssetManager am) {
 
        super(0, 0);
        try {
            this.bmp = BitmapFactory.decodeStream(am.open(s));
        } catch (IOException e) {
            bmp = null;
        }
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
        Log.d("SimpleSprite", "SRITE "+s+" LOADED");
    }
 
    /**
     * ���������� ��������� ������ �� ��������
     *
     * @param Res
     *            ���������� ���� �������
     * @param ID
     *            - ��������� �� ��� ������
     */
    public mSimpleSprite(Resources Res, int ID) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeResource(Res, ID);
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
    }
    
    /**
     * �������� ������ ������� � �������. �������� ��� ��������� ������
     * ���������� ������ � ������ � ���� �� ���������
     *
     * @param newx
     *            ����� ������ � ��������
     * @param newy
     *            ����� ������ � ��������
     */
    public void resize(int newx, int newy) {
        Bitmap tmp = Bitmap.createScaledBitmap(bmp, newx, newy, true);
        bmp = tmp;
        this.refreshAll();
    }
    /**
     * �������� ������ ������� � �������. �������� ��� ��������� ������
     * ���������� ������ � ������ � ���� �� ���������
     * @param newx - ��������� �� ������
     * @param newy - ��������� �� ������
     */
    public void resize(float newx, float newy) {
        int nx;
        int ny;
        this.refreshAll();
        nx = (int)(this.width*newx);
        ny = (int)(this.height*newy);
        Bitmap tmp = Bitmap.createScaledBitmap(bmp, nx, ny, true);
        bmp = tmp;
        this.refreshAll();
    }
    /**
     * ����� ��������� ������, � ������ ����������� ���������� ����� ��������
     * ������� � ����� ��������� ��� �������. ����� ��������� ������� ���������
     */
    public void refreshAll() {
        if (bmp != null) {
            this.width = bmp.getWidth();
            this.height = bmp.getHeight();
            matrix.setTranslate(this.getX(), this.getY());
        }
    }

    /**
     * @return ���������� �������
     */
    public Matrix getMatrix() {
        return matrix;
    }
    /**
     * @param matrix
     *            ������������� �������
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        refreshAll();
    }
    /**
     * @return ���������� ������ �����������
     * ������������ � ������
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return ���������� ������ �����������
     * ������������ � ������
     */
    public int getHeight() {
        return height;
    }
    /*
     * 
     *
     * @see Point#setX(float) ������������� ����� ���������
     * ������ ��������� �� x c ����������� �������
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        refreshAll();
    }
 
    
    /*
     * 
     *
     * @see Point#setY(float) ������������� ����� ���������
     * ������ ��������� �� y c ����������� �������
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        refreshAll();
    }
    /**
     * ������������� ����� ��������� �������
     *
     * @param x
     * @param y
     */
    public void setXY(float x, float y) {
        super.setY(y);
        super.setX(x);
        refreshAll();
    }
    /* (non-Javadoc)
     * @see Point#isSelected(float, float)
     */
    @Override
    public boolean isSelected(float x, float y) {
        selected = false;
        if (x > this.getX() && x < (this.getX() + this.width) && y > this.getY()
                && y < (this.getY() + this.height)) {
            selected = true;
        }
        return selected;
    }

    /**
     * @return ���������� ������ ���� ������ ������ � ���� ���� ���
     */
    public boolean isSelected() {
        return selected;
    }
    
    /* (non-Javadoc)
     * @see mPoint#draw(android.graphics.Canvas, android.graphics.Paint)
     */
    @Override
    public void draw(Canvas c, Paint p)
    {	   	
        c.drawBitmap(bmp, matrix, p);
    }
}
