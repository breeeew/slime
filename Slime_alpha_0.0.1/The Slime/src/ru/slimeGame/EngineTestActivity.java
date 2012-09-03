package ru.slimeGame;

import ru.SlimeEngine.mScene;
import ru.SlimeEngine.mSettings;
import ru.SlimeEngine.mSurfaceView;
import ru.slime.objects.mLine;
import ru.slime.objects.mPoint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class EngineTestActivity extends Activity implements OnTouchListener{
	mSurfaceView sf;
    mScene mainScene;
    int w, h;
    mPoint tmp1, tmp2;
    Paint p;
    mTentacle spr;
    float angle = 0;
    Base b;
	mLine line;
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
        mSettings.GenerateSettings(this.getWindowManager().getDefaultDisplay());
        mSettings.setTargetFrameRate(25);
        mainScene = new mScene(0, 0, 2);
        mainScene.setCurLay(0);
        sf = new mSurfaceView(this, mainScene);
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
        // Это то что происходит когда активити уходит на паузу
        // например когда нажали кнопку хоум
        super.onPause(); 
 
        // просто завершаем приложение.
        // нечего ему висеть в воздухе.
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
    
    private mPoint ScreenToCell(float x, float y) {
        float xx = x;
        float yy = y;
 
        int tmp;
        mPoint p;
        tmp = Math.min(w, h);
        if (mainScene.getOrient() == mScene.ORIENTATION_HOR) {
            xx = xx - tmp;
        } else {
            yy = yy - tmp;
        }
        p = new mPoint(xx, yy);
        return p;
 
    }
}