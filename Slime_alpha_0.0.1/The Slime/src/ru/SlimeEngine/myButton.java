package ru.SlimeEngine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class myButton extends View{
	/**
	 * @uml.property  name="bitmap"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Bitmap bitmap = null;	
	/**
	 * @uml.property  name="nopresed"
	 */
	private int nopresed = 0;
	/**
	 * @uml.property  name="presed"
	 */
	private int presed = 0;
	/**
	 * @uml.property  name="aDOWN"
	 */
	private final int ADOWN = MotionEvent.ACTION_DOWN;
	/**
	 * @uml.property  name="aMOVE"
	 */
	private final int AMOVE = MotionEvent.ACTION_MOVE;
	/**
	 * @uml.property  name="aUP"
	 */
	private final int AUP = MotionEvent.ACTION_UP;
	public myButton(Context context,int pic1, int pic2){
		super(context);
		presed = pic1;
		nopresed = pic2;		
		bitmap = BitmapFactory.decodeResource(getResources(), nopresed);
	}
	
	

	@Override 
	      public boolean onTouchEvent(MotionEvent event) 
	     { 
	          
	          //Float X=(Float)event.getX(); 
	          //Float Y=(Float)event.getY(); 
	        int Action=event.getAction(); 
	                 
	        switch(Action) 
	         { 
	            case ADOWN: 
	            	bitmap = BitmapFactory.decodeResource(getResources(), presed);
	            	invalidate();
	            	break;
	            	
	            case AMOVE: break; 
	            
	            case AUP:
	            bitmap = BitmapFactory.decodeResource(getResources(), nopresed);
            	invalidate();
	            break; 
	            
	         }
	       
			return true;
	         } 

	
	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
		setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
		//setBackgroundColor(Color.BLUE);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (bitmap != null) canvas.drawBitmap(bitmap, 0, 0, null);
	}

}
