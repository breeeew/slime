package game.sayanara;

import android.app.Activity;
import android.widget.Toast;
import com.engine.Engine;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class EngineTestActivity extends Activity implements OnTouchListener {
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //s.moveToXY(500, 1000);
                break;
            case MotionEvent.ACTION_UP:
                //s.moveToXY(0, 20);
        }
		return true;
	}

    private void createGame(){
        Engine.generateSettings(this, Engine.SET_FULL_SCREEN, 50);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createGame();
        //setContentView(Engine.gameSurface);
        Toast.makeText(this, "on create", Toast.LENGTH_LONG).show();
    }

	@Override
    public void onRestart(){
        super.onRestart();
        Toast.makeText(this, "on restart", Toast.LENGTH_LONG).show();
	}

    @Override
    public void onPause(){
        super.onPause();
        Toast.makeText(this, "on pause", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        //setContentView(Engine.gameSurface);
        Toast.makeText(this, "on resume", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(this, "on start", Toast.LENGTH_LONG).show();
    }

}