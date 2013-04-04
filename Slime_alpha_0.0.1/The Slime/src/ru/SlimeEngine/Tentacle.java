package ru.SlimeEngine;

import java.util.LinkedList;

import android.content.res.AssetManager;




public class Tentacle extends Thread{
	LinkedList<AnimSprite> bmSprite;
	Point a,b;
	float x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
	AssetManager am;
	Scene scen;
	String color;

	public Tentacle(Point aa, Point bb, int COLOR_OF_BASE, AssetManager am, Scene sc){
		bmSprite = new LinkedList<AnimSprite>();
		this.am = am;
		this.b = bb;
		this.a = aa;
		this.scen = sc;
		color = getStringOfSprite(COLOR_OF_BASE);
		scen.setCurLay(0);
		start();
	}
	
	private String getStringOfSprite(int c) {
		switch(c){
		case Base.BASE_COLOR_A:
		return "ad.png";
		case Base.BASE_COLOR_B:
		return "bd.png";
		}
		return null;
	}

	public LinkedList<AnimSprite> getListOfTentacles(){
		return bmSprite;
	}
	
	public void cancelTentacle(){
		for(int i = 0; i < getListOfTentacles().size(); i++){
			scen.deleteSprite(getListOfTentacles().getLast().getX()+1, getListOfTentacles().getLast().getY()+1);
			scen.update();
		}
	}

	private int getCurrentLen(Point a, Point b){
		int hypot = (int) Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
		return hypot;
	}
	
	private int getSpriteHypot(AnimSprite g){
		return(int) Math.hypot(g.getWidth(), g.getHeight());
	}
	
	private int sign (int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    public void drawBresenhamLine (float xstart, float ystart, float xend, float yend, final LinkedList<AnimSprite> g){
        dx = xend - xstart;
        dy = yend - ystart;
        incx = sign((int) dx);
        incy = sign((int) dy);

        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;
        if (dx > dy){
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        }
        else{
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }
        x = xstart;
        y = ystart;
        err = el/2;
		for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0){
                err += el;
                x += incx;
                y += incy;
            }
            else{
                x += pdx;
                y += pdy;
            }
            if (getCurrentLen(a, new Point(x,y)) >= 15){
                a = new Point(x, y);
                g.add(new AnimSprite(color, am, 2));
                int[] fl = {7,7};
                g.getLast().setFrameLen(fl);
                g.getLast().setAnimated(true);
                g.getLast().setXY(x, y);
                scen.addItem(g.getLast());
                g.getLast().update();
            }
            try {
                sleep(5l);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        g.removeFirst();
        scen.update();
    }

    public void run() {
        drawBresenhamLine (a.getX(), a.getY(), b.getX()+10, b.getY()+10, bmSprite);
    }
}


