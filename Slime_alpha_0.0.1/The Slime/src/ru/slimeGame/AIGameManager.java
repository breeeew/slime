package ru.slimeGame;

import java.util.ArrayList;
import java.util.Timer;

import ru.SlimeEngine.mScene;
import ru.slime.objects.mPoint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.MotionEvent;

public class AIGameManager extends GameManager{
	Timer t;
	mPoint tmp1 = null, tmp2 = null;
	int a,b;
	@Override
	public void createGame(AssetManager am, mScene scene) {
		// TODO Auto-generated method stub
		this.gameBases = new ArrayList<Base>();
		gameBases.add(new Base(100, 20, am, scene, 20,Base.BASE_COLOR_B));
		gameBases.add(new Base(160, 200, am, scene, 70, Base.BASE_COLOR_A));
		gameBases.add(new Base(10, 200, am, scene, 40, Base.BASE_COLOR_B));
		gameBases.add(new Base(20, 10, am, scene , 10, Base.BASE_COLOR_A));
		t = new Timer();
		//t.schedule(task, when, period);
	}

	@Override
	public boolean checkEndOfGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	public void onTouch(MotionEvent event) {
		// TODO Auto-generated method stub
			
			if(event.getAction() == MotionEvent.ACTION_DOWN){
			for(int i = 0; i < gameBases.size(); i++ ){
				if (gameBases.get(i).spr.isSelected(event.getX(), 
												event.getY()))
				{
					tmp1 = new mPoint(event.getX(), event.getY());
					a = i;
				}
			}
			}
			
			if(event.getAction() == MotionEvent.ACTION_UP)
			for(int i = 0; i < gameBases.size(); i++ ){
				if (gameBases.get(i).spr.isSelected(event.getX(), 
												event.getY()))
				{
					tmp2 = new mPoint(event.getX(), event.getY());
					b = i;
					if (a != b)
						gameBases.get(a).tentacleTo(gameBases.get(b));
				}
				
		}
			
	}
		
	
}
