package ru.slime.objects;

import ru.SlimeEngine.mSettings;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class mAnimSprite extends mSimpleSprite {
	/**
     * Ўирина кадра
     */
    protected int fWidth;
    /**
     * ¬ысота кадра
     */
    protected int fHeight;
    protected boolean animated;
    /**
     *  оличество кадров
     */
    protected int frameCount;
    /**
     * ћассив где хран€тс€ сами изображени€
     */
    protected Bitmap[] bmpList;
    /**
     * ћассив где хранитс€ количество
     * показов дл€ каждого кадра
     */
    protected int [] frameLen;
    /**
     * Ќомер текущего кадра
     */
    protected int curFrame;
    /**
     * счетчик показов кадра
     */
    protected int curFrameCounter;

    /**
     * 
     * @param s - им€ картинки в папке assets
     * @param am - asset
     * @param FrameCount - количество кадров в спрайте
     */
    public mAnimSprite(String s, AssetManager am, int FrameCount) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.MakeFrames();
        
    } 
    /**
     * 
     * @param s - им€ картинки в папке assets
     * @param am - asset
     * @param FrameCount - количество кадров в спрайте
     * @param angle - угол, на который будет повернут спрайт
     */
    public mAnimSprite(String s, AssetManager am, int FrameCount, float angle) {
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
     * @param s - им€ картинки в папке assets
     * @param am - asset
     * @param FrameCount - количество кадров в спрайте
     * @param matrix - матрица трансформации спрайта
     */
    public mAnimSprite(String s, AssetManager am, int FrameCount, Matrix matrix) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.matrix = matrix;
        //this.matrix.postTranslate(getX(), getY());
        this.MakeFrames();
    } 
    
  /**
     * ѕресоздает фреймы в спрайте
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
     * @see mSimpleSprite#draw(android.graphics.Canvas, android.graphics.Paint)
     * –исует на экране анимированный спрайт
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
     * @see mSimpleSprite#isSelected(float, float)
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
     * @return ¬озвращает ширину каждого кадра.
     * необходимо использовать дл€ определени€ ширины
     * отображаемого на экране анимированного спрайта
     */
    @Override
    public int getWidth() {
        return fWidth;
    }
 
    /**
     * @return ¬озвращает высоту каждого кадра.
     * необходимо использовать дл€ определени€ ширины
     * отображаемого на экране анимированного спрайта
     */
    @Override
    public int getHeight() {
        return fHeight;
    }
 
    /**
     * @return количество кадров в анимации спрайта
     */
    public int getFrameCount() {
        return frameCount;
    }
 
    /**
     * @return номер текущего кадра
     */
    public int getCurFrame() {
        return curFrame;
    }
 
    /**
     * ”станавливает текущий кадр
     *
     * @param curFrame - новый текущий кадр
     */
    public void setCurFrame(int curFrame) {
        this.curFrame = curFrame;
    }
 
    /**
     * ”станавливаетс€ длительность дл€ отображени€ каждого кадра в
     * анимированном спрайте
     * @param frameLen - массив целых чисел где дл€ каждого i-го
     * кадра указано сколько раз он будет показан прежде чем
     * произойдет переход на другой кадр;
     */
    public void setFrameLen(int[] frameLen) {
        if (this.frameLen.length>=this.frameCount)
        this.frameLen = frameLen;
    }
 
    /**
     * @return ¬озвращает истину, если
     * анимаци€ включена и ложь - если выключена
     */
    public boolean isAnimated() {
        return animated;
    }
 
    /**
     * ¬ключает и выключает анимацию
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
        if (mSettings.AutoScale)
        {
            this.resize(mSettings.ScaleFactorX, mSettings.ScaleFactorY);
        }
        this.refreshAll();
    }
}
