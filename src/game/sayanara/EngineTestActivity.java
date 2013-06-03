package game.sayanara;

import android.app.Activity;
import com.engine.EngineSettings;
import com.engine.GameSurface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import com.engine.primitives.drawables.SimpleSprite;

public class EngineTestActivity extends Activity implements OnTouchListener {
    public GameSurface gs;
    SimpleSprite s;
	public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                s.moveToXY(500, 1000);
                break;
            case MotionEvent.ACTION_UP:
                s.moveToXY(0, 20);
        }
		return true;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        EngineSettings.generateSettings(this.getWindowManager().getDefaultDisplay());
        EngineSettings.setFrameRate(50);
        gs = new GameSurface(this);
        gs.setOnTouchListener(this);
        this.setContentView(gs);
    }

	@Override
    public void onStart() {
        super.onStart();
        s = new SimpleSprite("aa.png", getAssets());
        s.setXY(10, 10);
        gs.mainScene.addItem(s);
	}
}