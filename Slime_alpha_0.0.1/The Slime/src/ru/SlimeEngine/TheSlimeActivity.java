package ru.SlimeEngine;

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
import ru.slimeGame.R;

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
	final int matchParent = LayoutParams.FILL_PARENT;
	/**
	 * @uml.property  name="wrapContent"
	 */
	final int wrapContent = LayoutParams.WRAP_CONTENT;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //������� �������� ������� ������
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        //��������� ��������� ��������
        LayoutParams mainParams = new LayoutParams(matchParent,matchParent);
        
        //������������� ��������
        RelativeLayout rLay = new RelativeLayout(this);
        RelativeLayout.LayoutParams rParam = new RelativeLayout.LayoutParams(matchParent,matchParent);
        
 
        
        ImageButton sBtn = new ImageButton(this, R.drawable.button_pressed,R.drawable.button_nopres);
        //sBtn.setText("��������� ����");
        sBtn.setId(BUTTON_SINGLE);
        RelativeLayout.LayoutParams sBtnParam = new RelativeLayout.LayoutParams(wrapContent,wrapContent);
        sBtnParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //sBtnParam.addRule(RelativeLayout.ABOVE,BUTTON_MULTY);
        //sBtnParam.setMargins(0, R.dimen.pic_marg_up, R.dimen.pic_marg_left, R.dimen.pic_marg_left);
        
        ImageButton mBtn = new ImageButton(this,R.drawable.button_pressed,R.drawable.button_nopres_mp);
        //mBtn.setText("������� ����");
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
    
    
    // �������� ����
   
    public boolean onCreateOptionsMenu(Menu menu) {
      // ��������� ������ ����
      menu.add(0, MENU_SELECT_SINGLE, 0, "��������� ����");
      menu.add(0, MENU_SELECT_MULTY, 1, "������� ����");
      menu.add(0, MENU_SELECT_EXIT, 2, "�����");
      
      return super.onCreateOptionsMenu(menu);
    }
    
       
    public boolean onPrepareOptionsMenu(Menu menu) {
       // ������ ���� � ID ������ = 1 �����, ���� � CheckBox ����� �����
       // menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
      }
    
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
        switch (item.getItemId()) {
		case MENU_SELECT_SINGLE:
			//�������� ��� ������ ��������� ����
			intent = new Intent(this, SinglePlayActivity.class);
			startActivity(intent);
			break;
		
		case MENU_SELECT_MULTY:
			//�������� ��� ������ ������� ����
			intent = new Intent(this, BluetoothUIManager.class);
			startActivity(intent);
			break;
			
		case MENU_SELECT_EXIT:
			//�����
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
			intent = new Intent(this, BluetoothUIManager.class);
			startActivity(intent);}
			break;
		}
		return false;
	}




    
}
