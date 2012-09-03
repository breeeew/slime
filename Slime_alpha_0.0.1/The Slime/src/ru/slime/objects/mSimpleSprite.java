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
 * @author Байрамлы Абдулла
 * @version 0.1 Класс для работы со спрайтами унаследован от {@link Point}
 */
public class mSimpleSprite extends mPoint {
	/**
     * Bitmap спрайта
     */
	Bitmap bmp;
	
	 /**
     * положение оси вращения спрайта (пока не реализовано)
     */
    float rpx = 0, rpy = 0;
    /**
     * Ширина (width) и высота (height) спрайта
     */
    int width = 0, height = 0;
    /**
     * Является ли центр спрайта его осью вращения пока не реализовано
     */
    boolean centralAxis = true;
    /**
     * Матрица для отрисовки спрайта на канве (Для вращения. Пока не реализовано)
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
     * Конструктор
     *
     * @param x
     *            положение спрайта по оси x
     * @param y
     *            положение спрайта по оси y
     * @param bmp
     *            битмап спрайта
     */
    public mSimpleSprite(float x, float y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * Конструктор загружает спрайт из файла
     *
     * @param x
     *            положение спрайта по оси x
     * @param y
     *            положение спрайта по оси y
     * @param bmp
     *            битмап спрайта
     */
    public mSimpleSprite(int x, int y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * Конструктор загружает спрайт из файла
     *
     * @param s
     *            путь к файлу из которого будет загружен спрайт
     */
    public mSimpleSprite(String s) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeFile(s);
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
        AutoSize();
    }
    
    /**
     * конструктор агружает спрайт из ассетов
     *
     * @param s
     *            путь к файлу в папке assets откуда должен быть загружен спрайт
     * @param am
     *            - экземпляр {@link AssetManager}
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
     * Констрктор загружает спрайт из ресурсов
     *
     * @param Res
     *            собственно сами ресурсы
     * @param ID
     *            - указатель на сам спрайт
     */
    public mSimpleSprite(Resources Res, int ID) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeResource(Res, ID);
        this.setTypeOfGameObject(TYPE_SIMPLESPRITE);
    }
    
    /**
     * Изменяет размер битмапа в спрайте. например для поддержки разных
     * разрешений экрана с одними и теми же ресурсами
     *
     * @param newx
     *            новая ширина в пикселах
     * @param newy
     *            новая высота в пикселах
     */
    public void resize(int newx, int newy) {
        Bitmap tmp = Bitmap.createScaledBitmap(bmp, newx, newy, true);
        bmp = tmp;
        this.refreshAll();
    }
    /**
     * Изменяет размер битмапа в спрайте. например для поддержки разных
     * разрешений экрана с одними и теми же ресурсами
     * @param newx - множитель по ширине
     * @param newy - множитель по высоте
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
     * метод обновляет ширину, и высоту изображения вызывается после загрузки
     * спрайта и после изменения его размера. Также обновляет матрицу отрисовки
     */
    public void refreshAll() {
        if (bmp != null) {
            this.width = bmp.getWidth();
            this.height = bmp.getHeight();
            matrix.setTranslate(this.getX(), this.getY());
        }
    }

    /**
     * @return Возвращает матрицу
     */
    public Matrix getMatrix() {
        return matrix;
    }
    /**
     * @param matrix
     *            устанавливает матрицу
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        refreshAll();
    }
    /**
     * @return Возвращает ширину изображения
     * загруженного в спрайт
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return Возвращает высоту изображения
     * загруженного в спрайт
     */
    public int getHeight() {
        return height;
    }
    /*
     * 
     *
     * @see Point#setX(float) перегруженный метод установки
     * нового положения по x c обновлением матрицы
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        refreshAll();
    }
 
    
    /*
     * 
     *
     * @see Point#setY(float) перегруженный метод установки
     * нового положения по y c обновлением матрицы
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        refreshAll();
    }
    /**
     * Устанавливает новое положение спрайта
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
     * @return возвращает истину если спрайт выбран и ложь если нет
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
