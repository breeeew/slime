package com.engine.primitives.drawables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class AnimatedButton extends View{
    private Bitmap bitmap = null;
    private int nopresed = 0;
    private int presed = 0;
    private final int ADOWN = MotionEvent.ACTION_DOWN;
    private final int AMOVE = MotionEvent.ACTION_MOVE;
    private final int AUP = MotionEvent.ACTION_UP;

    public AnimatedButton(Context context, int pic1, int pic2){
        super(context);
        presed = pic1;
        nopresed = pic2;
        bitmap = BitmapFactory.decodeResource(getResources(), nopresed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int Action=event.getAction();
        switch(Action){
            case ADOWN:
                bitmap = BitmapFactory.decodeResource(getResources(), presed);
                invalidate();
                break;
            case AUP:
                bitmap = BitmapFactory.decodeResource(getResources(), nopresed);
                invalidate();
                break;
            case AMOVE:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null) canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
