package com.engine.primitives.drawables;

import java.io.IOException;

import com.engine.EngineSettings;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import com.engine.primitives.interfaces.Movable;

public class SimpleSprite extends Point implements Movable{
    float rpx = 0;
    float rpy = 0;
    int width = 0;
    int height = 0;
    boolean centralAxis = true;
    boolean selected = false;
    boolean isInMove = false;

    Bitmap bmp;
    Matrix matrix = new Matrix();

    private void AutoSize()
    {
        if (EngineSettings.autoScale)
        {
            this.resize(EngineSettings.scaleFactorX, EngineSettings.scaleFactorY);
        }
        this.refreshAll();
    }

    public SimpleSprite(float x, float y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_SIMPLE_SPRITE);
        AutoSize();
    }

    public SimpleSprite(int x, int y, Bitmap bmp) {
        super(x, y);
        this.bmp = bmp;
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_SIMPLE_SPRITE);
        AutoSize();
    }
    
    public SimpleSprite(String s) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeFile(s);
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_SIMPLE_SPRITE);
        AutoSize();
    }
    
    public SimpleSprite(String s, AssetManager am) {
        super(0, 0);
        try {
            this.bmp = BitmapFactory.decodeStream(am.open(s));
        } catch (IOException e) {
            bmp = null;
        }
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_SIMPLE_SPRITE);
        AutoSize();
        Log.d("SimpleSprite", "SRITE "+s+" LOADED");
    }
 
    public SimpleSprite(Resources Res, int ID) {
        super(0, 0);
        this.bmp = BitmapFactory.decodeResource(Res, ID);
        this.setTypeOfGameObject(BaseObjectTypes.TYPE_SIMPLE_SPRITE);
    }
    
    public void resize(int newx, int newy) {
        Bitmap tmp = Bitmap.createScaledBitmap(bmp, newx, newy, true);
        bmp = tmp;
        this.refreshAll();
    }

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

    public void refreshAll() {
        if (bmp != null) {
            this.width = bmp.getWidth();
            this.height = bmp.getHeight();
            matrix.setTranslate(this.getCurrentX(), this.getCurrentY());
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        refreshAll();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void setCurrentX(float x) {
        super.setCurrentX(x);
        refreshAll();
    }
 
    @Override
    public void setCurrentY(float y) {
        super.setCurrentY(y);
        refreshAll();
    }

    public void setXY(float x, float y) {
        super.setCurrentY(y);
        super.setCurrentX(x);
        refreshAll();
    }

    @Override
    public boolean isSelected(float x, float y) {
        selected = false;
        if (x > this.getCurrentX() && x < (this.getCurrentX() + this.width) && y > this.getCurrentY()
                && y < (this.getCurrentY() + this.height)) {
            selected = true;
        }
        return selected;
    }

    public boolean isSelected() {
        return selected;
    }
    
    @Override
    public void draw(Canvas c, Paint p){
        c.drawBitmap(bmp, matrix, p);
    }

    /*
        TODO: реализовать движение. Возможно в отдельном потоке со слипами
        нужно узнать - как надо, как правильно
    */
    @Override
    public void moveToXY(float x, float y) {

    }

    @Override
    public void moveToPoint(Point p) {
    }

    @Override
    public void LerpMoveToXY(float x, float y) {
    }

    @Override
    public void LerpMoveToPoint(Point p) {
    }
}
