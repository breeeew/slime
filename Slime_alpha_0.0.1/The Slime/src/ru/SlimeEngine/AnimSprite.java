package ru.SlimeEngine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class AnimSprite extends SimpleSprite {
	/**
     * ������ �����
     */
    protected int fWidth;
    /**
     * ������ �����
     */
    protected int fHeight;
    protected boolean animated;
    /**
     * ���������� ������
     */
    protected int frameCount;
    /**
     * ������ ��� �������� ���� �����������
     */
    protected Bitmap[] bmpList;
    /**
     * ������ ��� �������� ����������
     * ������� ��� ������� �����
     */
    protected int [] frameLen;
    /**
     * ����� �������� �����
     */
    protected int curFrame;
    /**
     * ������� ������� �����
     */
    protected int curFrameCounter;

    /**
     * 
     * @param s - ��� �������� � ����� assets
     * @param am - asset
     * @param FrameCount - ���������� ������ � �������
     */
    public AnimSprite(String s, AssetManager am, int FrameCount) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.MakeFrames();
        
    } 
    /**
     * 
     * @param s - ��� �������� � ����� assets
     * @param am - asset
     * @param FrameCount - ���������� ������ � �������
     * @param angle - ����, �� ������� ����� �������� ������
     */
    public AnimSprite(String s, AssetManager am, int FrameCount, float angle) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        matrix = new Matrix();
        matrix.postRotate(angle, 0, fHeight/2);
        matrix.postTranslate(getX(), getY());
        this.MakeFrames();
        
    } 
    /**
     * 
     * @param s - ��� �������� � ����� assets
     * @param am - asset
     * @param FrameCount - ���������� ������ � �������
     * @param matrix - ������� ������������� �������
     */
    public AnimSprite(String s, AssetManager am, int FrameCount, Matrix matrix) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.matrix = matrix;
        //this.matrix.postTranslate(getX(), getY());
        this.MakeFrames();
    } 
    
  /**
     * ���������� ������ � �������
     */
    public void MakeFrames()
    {
        curFrame=0;
        bmpList = new Bitmap[this.frameCount];
        frameLen = new int[this.frameCount];
        for (int i = 0; i< this.frameCount; i++)
        {
            bmpList[i]=Bitmap.createBitmap(bmp, i*this.fWidth, 0, this.fWidth, this.fHeight, matrix, true);
            frameLen[i]=1;
        }
    }
    
    /**
     * @see SimpleSprite#draw(android.graphics.Canvas, android.graphics.Paint)
     * ������ �� ������ ������������� ������
     */
    @Override
    public void draw(Canvas c, Paint p)
    {
    	if (curFrameCounter >=frameLen[curFrame])
        {
            curFrameCounter = 1;
            if (curFrame==frameCount-1)
            {
                curFrame=0;
            }
            else
            {
                curFrame++;
            }     
    	}
        c.drawBitmap(bmpList[this.curFrame], this.getX(), this.getY(), p);
        if (this.animated){
            curFrameCounter++;
        }
    	
    }
 
//    public void draw(Canvas c, Paint p, int dx, int dy)
//    {
//        if (curFrameCounter >=frameLen[curFrame])
//        {
//            curFrameCounter = 1;
//            if (curFrame==frameCount-1)
//            {
//                curFrame=0;
//            }
//            else
//            {
//                curFrame++;
//            }
//        }
//        c.drawBitmap(bmpList[this.curFrame], this.getX()+dx, this.getY()+dy, p);
//        
//        if (this.animated)
//        {
//            curFrameCounter++;
//        }
//        
//    }
 
    /**
     * @see SimpleSprite#isSelected(float, float)
     */
    @Override
    public boolean isSelected(float x, float y) {
        selected = false;
        if (x > this.getX() && x < (this.getX() + this.fWidth) && y > this.getY()
                && y < (this.getY() + this.fHeight)) {
            selected = true;
        }
        return selected;
    }
 
    /**
     * @return ���������� ������ ������� �����.
     * ���������� ������������ ��� ����������� ������
     * ������������� �� ������ �������������� �������
     */
    @Override
    public int getWidth() {
        return fWidth;
    }
 
    /**
     * @return ���������� ������ ������� �����.
     * ���������� ������������ ��� ����������� ������
     * ������������� �� ������ �������������� �������
     */
    @Override
    public int getHeight() {
        return fHeight;
    }
 
    /**
     * @return ���������� ������ � �������� �������
     */
    public int getFrameCount() {
        return frameCount;
    }
 
    /**
     * @return ����� �������� �����
     */
    public int getCurFrame() {
        return curFrame;
    }
 
    /**
     * ������������� ������� ����
     *
     * @param curFrame - ����� ������� ����
     */
    public void setCurFrame(int curFrame) {
        this.curFrame = curFrame;
    }
 
    /**
     * ��������������� ������������ ��� ����������� ������� ����� �
     * ������������� �������
     * @param frameLen - ������ ����� ����� ��� ��� ������� i-��
     * ����� ������� ������� ��� �� ����� ������� ������ ���
     * ���������� ������� �� ������ ����;
     */
    public void setFrameLen(int[] frameLen) {
        if (this.frameLen.length>=this.frameCount)
        this.frameLen = frameLen;
    }
 
    /**
     * @return ���������� ������, ����
     * �������� �������� � ���� - ���� ���������
     */
    public boolean isAnimated() {
        return animated;
    }
 
    /**
     * �������� � ��������� ��������
     * @param animated
     */
    public void setAnimated(boolean animated) {
    	this.animated = animated;
    }
    
    @Override
    public void resize(float newx, float newy) {
    	// TODO Auto-generated method stub
    	super.resize(newx, newy);
    }
	public void setNewBitmap(Bitmap bmp, int frameCount) {
		// TODO Auto-generated method stub
		this.bmp = bmp;
		AutoSize();
		this.frameCount = frameCount;
		int[] fl = {7,7};
		setFrameLen(fl);
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
		this.MakeFrames();
	}
    private void AutoSize()
    {
        if (Settings.AutoScale)
        {
            this.resize(Settings.ScaleFactorX, Settings.ScaleFactorY);
        }
        this.refreshAll();
    }
}
