package ru.slimeGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

public class SinglePlayActivity extends Activity implements OnItemClickListener{
	
	
	/**
	 * @uml.property  name="matchParent"
	 */
	final int matchParent = LayoutParams.FILL_PARENT;
	/**
	 * @uml.property  name="wrapContent"
	 */
	final int wrapContent = LayoutParams.WRAP_CONTENT;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] levels = { "Уровень 1", "Уровень 2", "Уровень 3", "Уровень 4", "Уровень 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item, levels);
        
        Window w = this.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);      
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);        
        LayoutParams mainParams = new LayoutParams(matchParent,matchParent);
        ListView list = new ListView(getApplicationContext());
        android.view.ViewGroup.LayoutParams listParam = new ViewGroup.LayoutParams(matchParent, matchParent);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        mainLayout.addView(list, listParam);
        
        setContentView(mainLayout, mainParams);
	}



	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			Toast.makeText(getApplicationContext(), "ур 1", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, EngineTestActivity.class);
			startActivity(intent);
			break;

		case 1:
			Toast.makeText(getApplicationContext(), "ур 2", Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(getApplicationContext(), "ур 3", Toast.LENGTH_LONG).show();
			break;
		case 3:
			Toast.makeText(getApplicationContext(), "ур 4", Toast.LENGTH_LONG).show();
			break;
		case 4:
			Toast.makeText(getApplicationContext(), "ур 5", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
}
