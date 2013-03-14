package ru.slimeGame;

import ru.SlimeEngine.myButton;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class TheSlimeActivity extends Activity implements OnTouchListener{
	
	/**
	 * @uml.property  name="mENU_SELECT_SINGLE"
	 */
	final int MENU_SELECT_SINGLE = 1;
	/**
	 * @uml.property  name="mENU_SELECT_MULTY"
	 */
	final int MENU_SELECT_MULTY = 2;
	/**
	 * @uml.property  name="mENU_SELECT_EXIT"
	 */
	final int MENU_SELECT_EXIT = 3;
	/**
	 * @uml.property  name="bUTTON_SINGLE"
	 */
	final int BUTTON_SINGLE = 100;
	/**
	 * @uml.property  name="bUTTON_MULTY"
	 */
	final int BUTTON_MULTY = 101;
	/**
	 * @uml.property  name="matchParent"
	 */
	final int matchParent = LayoutParams.MATCH_PARENT;
	/**
	 * @uml.property  name="wrapContent"
	 */
	final int wrapContent = LayoutParams.WRAP_CONTENT;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //создаем корневой элемент экрана
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        //параметры корневого элемента
        LayoutParams mainParams = new LayoutParams(matchParent,matchParent);
        
        //относительная разметка
        RelativeLayout rLay = new RelativeLayout(this);
        RelativeLayout.LayoutParams rParam = new RelativeLayout.LayoutParams(matchParent,matchParent);
        
 
        
        myButton sBtn = new myButton(this, R.drawable.button_pressed,R.drawable.button_nopres);
        //sBtn.setText("Одиночная игра");
        sBtn.setId(BUTTON_SINGLE);
        RelativeLayout.LayoutParams sBtnParam = new RelativeLayout.LayoutParams(wrapContent,wrapContent);
        sBtnParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //sBtnParam.addRule(RelativeLayout.ABOVE,BUTTON_MULTY);
        //sBtnParam.setMargins(0, R.dimen.pic_marg_up, R.dimen.pic_marg_left, R.dimen.pic_marg_left);
        
        myButton mBtn = new myButton(this,R.drawable.button_pressed,R.drawable.button_nopres_mp);
        //mBtn.setText("Сетевая игра");
        mBtn.setId(BUTTON_MULTY);
        RelativeLayout.LayoutParams mBtnParam = new RelativeLayout.LayoutParams(wrapContent,wrapContent);
        mBtnParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mBtnParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        
        sBtn.setOnTouchListener(this);
        mBtn.setOnTouchListener(this);
        
        
        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);      
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        
        
        rLay.addView(sBtn,sBtnParam);
        rLay.addView(mBtn,mBtnParam);
        mainLayout.addView(rLay, rParam);
        setContentView(mainLayout, mainParams);
        
}
    
    
    // создание меню
   
    public boolean onCreateOptionsMenu(Menu menu) {
      // добавляем пункты меню
      menu.add(0, MENU_SELECT_SINGLE, 0, "Одиночная игра");
      menu.add(0, MENU_SELECT_MULTY, 1, "Сетевая игра");
      menu.add(0, MENU_SELECT_EXIT, 2, "Выйти");
      
      return super.onCreateOptionsMenu(menu);
    }
    
       
    public boolean onPrepareOptionsMenu(Menu menu) {
       // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
       // menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
      }
    
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
        switch (item.getItemId()) {
		case MENU_SELECT_SINGLE:
			//действие при выборе одиночной игры
			intent = new Intent(this, SinglePlayActivity.class);
			startActivity(intent);
			break;
		
		case MENU_SELECT_MULTY:
			//действие при выборе сетевой игры
			intent = new Intent(this, ru.bluetooth.BluetoothUIManager.class);
			startActivity(intent);
			break;
			
		case MENU_SELECT_EXIT:
			//выход
			finish();
			break;
			
		default:
			break;
		}
    	
    return super.onOptionsItemSelected(item);
      }



	public boolean onTouch(View v, MotionEvent event) {
		Intent intent;
		switch (v.getId()){
		case BUTTON_SINGLE: if (event.getAction() == MotionEvent.ACTION_UP){
			intent = new Intent(this, SinglePlayActivity.class);
			startActivity(intent);}
			break;
		case BUTTON_MULTY: if (event.getAction() == MotionEvent.ACTION_UP){
			intent = new Intent(this, ru.bluetooth.BluetoothUIManager.class);
			startActivity(intent);}
			break;
		}
		return false;
	}




    
}
