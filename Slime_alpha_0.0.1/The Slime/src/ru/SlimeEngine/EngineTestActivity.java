package ru.SlimeEngine;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class EngineTestActivity extends Activity implements OnTouchListener{
	SlimeSurfaceView sf;
    Scene mainScene;
    int w, h;
    Point tmp1, tmp2;
    Paint p;
    Tentacle spr;
    float angle = 0;
    Base b;
	Line line;
    AIGameManager game;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

	public boolean onTouch(View v, MotionEvent event) {

		
		game.onTouch(event);

		return true;
	}
	
	@Override
    public void onStart() {
        super.onStart();
        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);      
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        Settings.GenerateSettings(this.getWindowManager().getDefaultDisplay());
        Settings.setTargetFrameRate(25);
        mainScene = new Scene(0, 0, 2);
        mainScene.setCurLay(0);
        sf = new SlimeSurfaceView(this, mainScene);
        sf.setOnTouchListener(this);
        this.setContentView(sf);
       // b = new Base(10, 20, this.getAssets(), mainScene);
//		game = SinglePlayActivity.;
        game = new AIGameManager();
		game.createGame(getAssets(), mainScene);
//        int w = this.getWindowManager().getDefaultDisplay().getWidth();
//        int h = this.getWindowManager().getDefaultDisplay().getHeight();
	}
    @Override
    public void onPause() {
        // ��� �� ��� ���������� ����� �������� ������ �� �����
        // �������� ����� ������ ������ ����
        super.onPause(); 
 
        // ������ ��������� ����������.
        // ������ ��� ������ � �������.
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
    
    private Point ScreenToCell(float x, float y) {
        float xx = x;
        float yy = y;
 
        int tmp;
        Point p;
        tmp = Math.min(w, h);
        if (mainScene.getOrient() == Scene.ORIENTATION_HOR) {
            xx = xx - tmp;
        } else {
            yy = yy - tmp;
        }
        p = new Point(xx, yy);
        return p;
 
    }
}