package ru.SlimeEngine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * ����� ����������� ���������� ������� ������� - ����
 * @author �������� �������
 * @version 1.0
 * @created 28-���-2012 11:32:05
 */
public class Base extends Point {


	public AnimSprite spr;
	float curX, curY;
	int CURRENT_BASE_STRENGTH;
	Scene scene;
	
	private int power;
	
	public final static int BASE_SMALL = 1;
	public final static int BASE_MEDIUM = 2;
	public final static int BASE_LARGE = 3;
	int[] fl;
	public final static int BASE_SPRITE_FRAMES = 5;
	
	public final static int BASE_COLOR_A = 1;
	public final static int BASE_COLOR_B = 2;
	public final static int BASE_COLOR_C = 3;
	private String prefix; 
	int CURRENT_BASE_COLOR;
	public Bitmap bmp;
	private AssetManager am;
	LinkedList<Tentacle> tentacles;
	
	public Base(float x, float y, AssetManager am, Scene ss) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.curX = x;
		this.curY = y;
		this.am = am;
		this.scene = ss;
		this.scene.setCurLay(1);
		setBaseColor(BASE_COLOR_A);
		spr = new AnimSprite(prefix + "a.png", am, 2);
		int[] fl = {5,5};
		spr.setFrameLen(fl);
		spr.setXY(curX, curY);
		spr.setAnimated(true);

		setPower(1);
		tentacles = new LinkedList<Tentacle>();

		ss.addItem(spr);
		checkBase();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
			    powerIncrement(1);
			}
		}, 1000l,1000l);
	}
	
	
	public Base(float x, float y, AssetManager am, Scene ss, int power) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.curX = x;
		this.curY = y;
		this.am = am;
		this.scene = ss;
		ss.setCurLay(1);
		setBaseColor(BASE_COLOR_A);
		spr = new AnimSprite(prefix + "a.png", am, 2);
		int[] fl = {5,5};
		spr.setFrameLen(fl);
		spr.setXY(curX, curY);
		spr.setAnimated(true);
		
		setPower(power);
		tentacles = new LinkedList<Tentacle>();
		
		ss.addItem(spr);
		checkBase();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
			    powerIncrement(1);
			}
		}, 1000l,1000l);
	}
	
	
	public Base(float x, float y, AssetManager am, Scene ss, int power, int BASE_COLOR) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.curX = x;
		this.curY = y;
		this.am = am;
		this.scene = ss;
		ss.setCurLay(1);
		setBaseColor(BASE_COLOR);
		spr = new AnimSprite(prefix + "a.png", am, 2);
		int[] fl = {5,5};
		spr.setFrameLen(fl);
		spr.setXY(curX, curY);
		spr.setAnimated(true);
		
		setPower(power);
		tentacles = new LinkedList<Tentacle>();
		
		ss.addItem(spr);
		checkBase();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
			    powerIncrement(1);
			}
		}, 1000l,1000l);
	}

	
public void setBaseColor(int BASE_COLOR){
	CURRENT_BASE_COLOR = BASE_COLOR;
	switch (CURRENT_BASE_COLOR) {
	case BASE_COLOR_A:
			prefix = "a";
		break;
	case BASE_COLOR_B:
			prefix = "b";
		break;
	case BASE_COLOR_C:
			prefix = "c";
	}
}

/**
 * 
 * @return ���������� ���� ���� (����, ����, �����������)
 */
public int getBaseColor(){
	return this.CURRENT_BASE_COLOR;
}
	
/**
 * ������������� ��� ����
 * @param CUR_SPRITE - ��������� ���� �� ��������: 
 *  BASE_SMALL - ��������� ����
 *  BASE_MEDIUM - ������� ����
 *  BASE_LARGE - ������� ����
 */
	private void setCurrentBaseSprite(int CUR_SPRITE){
		int[] fl = {4,4};
		CURRENT_BASE_STRENGTH = CUR_SPRITE;
		switch (CURRENT_BASE_STRENGTH){
		case BASE_SMALL:
			try {
				spr.setNewBitmap(BitmapFactory.decodeStream(am.open(prefix + "a.png")),2);
				spr.setFrameLen(fl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case BASE_MEDIUM:
			try {
				spr.setNewBitmap(BitmapFactory.decodeStream(am.open(prefix + "b.png")),2);
				spr.setFrameLen(fl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case BASE_LARGE:
			try {
				spr.setNewBitmap(BitmapFactory.decodeStream(am.open(prefix + "c.png")),2);
				spr.setFrameLen(fl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	 public synchronized void powerIncrement(int x){
		setPower(getPower() + x);
		checkBase();
	}
	 
	public synchronized void powerDecrement(int x){
		setPower(getPower() - x);
		checkBase();
	}
	
	/**
	 * ��������� �������� ���� � ����, � � ����������� �� �������� ������ ��������
	 */
	private void checkBase(){
		if ((getPower() <= 30)&&(getPower() > 1)&&(getCurrentBaseStrenght() != BASE_SMALL)){
			setCurrentBaseSprite(BASE_SMALL);
			scene.update();
		}
		if ((getPower() > 30)&&(getPower() < 69)&&(getCurrentBaseStrenght() != BASE_MEDIUM)){
			setCurrentBaseSprite(BASE_MEDIUM);
			scene.update();

		}
		if ((getPower() > 70)&&(getPower() <= 100)&&(getCurrentBaseStrenght() != BASE_LARGE)){		
			setCurrentBaseSprite(BASE_LARGE);
			scene.update();
		}
			
	}
	
	
	
	/**
	 * ����������� �������� �� ��������� ����
	 * @param base - ����, �� ������� ����� ��������� ��������
	 */
	public void tentacleTo(final Base base){
		tentacles.add(new Tentacle(new Point(curX+spr.getWidth()/2,curY+spr.getHeight()/2),
									new Point(base.getX(), base.getY()),
									CURRENT_BASE_COLOR, am, scene));
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if (base.getBaseColor() == CURRENT_BASE_COLOR){
			    base.powerIncrement(getCurrentBaseStrenght());
			    powerDecrement(1);
			    	
			    }
				else{
					base.powerDecrement(getCurrentBaseStrenght());
				    powerDecrement(1);
				    if (base.getPower() <= 0){
			    		base.setBaseColor(CURRENT_BASE_COLOR);
			    		//base.tentacles.getLast().cancelTentacle();
			    		//setPower(1);
			    		checkBase();
			    	}
				}
			}
		}, 1000l,1000l);
	}
	
	/**
	 * �������� ������� �������� ����
	 * @return ����
	 */
	public synchronized int getPower(){
		return power;
	}
	
	/**
	 * ���������� �������� ��������� ����
	 * @param power - ����
	 */
	private synchronized void setPower(int power){
		this.power = power;
		checkBase();
	}
	
	/**
	 * 
	 * @return ���������� ������ ����
	 */
	public AnimSprite getItem(){
		return spr;
	}
	
	/**
	 * 
	 * @return - ������� ���������� �������� ����
	 */
	public int getCurrentBaseStrenght(){
		return CURRENT_BASE_STRENGTH;
	}
	
}