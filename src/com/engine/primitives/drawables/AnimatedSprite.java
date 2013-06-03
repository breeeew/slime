package com.engine.primitives.drawables;

import com.engine.EngineSettings;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class AnimatedSprite extends SimpleSprite {
    protected int fWidth;
    protected int fHeight;
    protected boolean animated;
    protected int frameCount;
    protected Bitmap[] bmpList;
    protected int [] frameLen;
    protected int curFrame;
    protected int curFrameCounter;

    public AnimatedSprite(String s, AssetManager am, int FrameCount) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.MakeFrames();
    }

    public AnimatedSprite(String s, AssetManager am, int FrameCount, float angle) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        matrix = new Matrix();
        matrix.postRotate(angle, 0, fHeight/2);
        matrix.postTranslate(getCurrentX(), getCurrentY());
        this.MakeFrames();
    }

    public AnimatedSprite(String s, AssetManager am, int FrameCount, Matrix matrix) {
        super(s, am);
        this.frameCount=FrameCount;
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.matrix = matrix;
        this.MakeFrames();
    }

    public void MakeFrames(){
        curFrame=0;
        bmpList = new Bitmap[this.frameCount];
        frameLen = new int[this.frameCount];
        for (int i = 0; i< this.frameCount; i++){
            bmpList[i]=Bitmap.createBitmap(bmp, i*this.fWidth, 0, this.fWidth, this.fHeight, matrix, true);
            frameLen[i]=1;
        }
    }

    @Override
    public void draw(Canvas c, Paint p){
        if (curFrameCounter >=frameLen[curFrame]){
            curFrameCounter = 1;
            if (curFrame==frameCount-1){
                curFrame=0;
            }
            else{
                curFrame++;
            }
        }
        c.drawBitmap(bmpList[this.curFrame], this.getCurrentX(), this.getCurrentY(), p);
        if (this.animated){
            curFrameCounter++;
        }
    }

    @Override
    public boolean isSelected(float x, float y) {
        selected = false;
        if (x > this.getCurrentX()){
            if (x < (this.getCurrentX() + this.fWidth)){
                if (y > this.getCurrentY()){
                    if (y < (this.getCurrentY() + this.fHeight)) {
                        selected = true;
                    }
                }
            }
        }
        return selected;
    }

    @Override
    public int getWidth() {
        return fWidth;
    }

    @Override
    public int getHeight() {
        return fHeight;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public int getCurFrame() {
        return curFrame;
    }

    public void setCurFrame(int curFrame) {
        this.curFrame = curFrame;
    }

    public void setFrameDelay(int[] frameLen) {
        if (this.frameLen.length >= this.frameCount){
            this.frameLen = frameLen;
        }
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    @Override
    public void resize(float newx, float newy) {
        super.resize(newx, newy);
    }

    public void setNewBitmap(Bitmap bmp, int frameCount) {
        this.bmp = bmp;
        AutoSize();
        this.frameCount = frameCount;
        int[] fl = {7,7};
        setFrameDelay(fl);
        this.fHeight=this.height;
        this.fWidth=this.width/this.frameCount;
        this.MakeFrames();
    }

    private void AutoSize(){
        if (EngineSettings.autoScale){
            this.resize(EngineSettings.scaleFactorX, EngineSettings.scaleFactorY);
        }
        this.refreshAll();
    }
}
