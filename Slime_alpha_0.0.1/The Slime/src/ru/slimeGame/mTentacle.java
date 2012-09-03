package ru.slimeGame;

import java.util.LinkedList;

import ru.SlimeEngine.mScene;
import ru.slime.objects.mAnimSprite;
import ru.slime.objects.mPoint;

import android.content.res.AssetManager;




public class mTentacle extends Thread{
	LinkedList<mAnimSprite> bmSprite;
	mPoint a,b;
	float x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
	AssetManager am;
	mScene scen;
	String color;

	public mTentacle(mPoint aa, mPoint bb, int COLOR_OF_BASE, AssetManager am, mScene sc){
		bmSprite = new LinkedList<mAnimSprite>();
		this.am = am;
		this.b = bb;
		this.a = aa;
		this.scen = sc;
		color = getStringOfSprite(COLOR_OF_BASE);
		scen.setCurLay(0);
		start();
	}
	
	private String getStringOfSprite(int c) {
		// TODO Auto-generated method stub
		switch(c){
		case Base.BASE_COLOR_A:
		return "ad.png";
		case Base.BASE_COLOR_B:
		return "bd.png";
		}
		return null;
	}

	public LinkedList<mAnimSprite> getListOfTentacles(){
		return bmSprite;
	}
	
	public void cancelTentacle(){
		for(int i = 0; i < getListOfTentacles().size(); i++){
			scen.deleteSprite(getListOfTentacles().getLast().getX()+1, getListOfTentacles().getLast().getY()+1);
			scen.update();
		}
	}

	private int getCurrentLen(mPoint a, mPoint b){
		int hypot = (int) Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
		return hypot;
	}
	
	private int getSpriteHypot(mAnimSprite g){
		return(int) Math.hypot(g.getWidth(), g.getHeight());
	}
	
	private int sign (int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //���������� 0, ���� �������� (x) ����� ����; -1, ���� x < 0 � 1, ���� x > 0.
}
/**
 * �������� ����������
  * xstart, ystart - ������;
  * xend, yend - �����; 
  * "g.drawLine (x, y, x, y);" ���������� � �������� "setPixel (x, y);"
  * ����� ������ ���-������ ����� g.fillRect (x, y, 1, 1);
 */
public void drawBresenhamLine (float xstart, float ystart, float xend, float yend, final LinkedList<mAnimSprite> g)
{
		
        dx = xend - xstart;//�������� �� ��� ���
        dy = yend - ystart;//�������� �� ��� �����
        incx = sign((int) dx);
        /*
         * ����������, � ����� ������� ����� ����� ����������. ���� dx < 0, �.�. ������� ���
         * ������ ������ �� ����, �� incx ����� ����� -1.
         * ��� ����� �������������� � ����� �����������.
         */
        incy = sign((int) dy);
        /*
         * ����������. ���� ������ ������� ����� ����� -
         * ��� ����� ������������� ����� ��� y (����� - �������������).
         */
 
        if (dx < 0) dx = -dx;//����� �� ����� ����������: "if (dx < dy)"
        if (dy < 0) dy = -dy;//������� ���������� ������� dx = |dx|; dy = |dy|
        //��� ��� ������� ����� �������� � ���: dx = Math.abs(dx); dy = Math.abs(dy);
 
        if (dx > dy)
        //���������� ������ �������:
        {
         /*
          * ���� dx > dy, �� ������ ������� "�������" ����� ��� ���, �.�. �� ������ �������, ��� �������.
          * ������ � ����� ����� ����� ���� �� ��� (������� el = dx;), ������ "�����������" ������ �� ����
          * ���� � ������������ � ���, ����� ������� � ������ ������ ��� ��� (pdx = incx;), ��� ����
          * �� y ����� ����� �����������.
          */
                pdx = incx;     pdy = 0;
                es = dy;        el = dx;
        }
        else//������, ����� ������ ������ "�������", ��� �������, �.�. �������� �� ��� y
        {
                pdx = 0;        pdy = incy;
                es = dx;        el = dy;//����� � ����� ����� ��������� �� y
        }
 
        x = xstart;
        y = ystart;
        err = el/2;
        //g.getFirst().setXY(x, y); //������ ������ �����
        //g.getFirst().update();	   //��� ����������� ����� �������� ���� ��������, ������� ������ ������ ��� �����
        
       

				// TODO Auto-generated method stub
				for (int t = 0; t < el; t++)//��� �� ���� ������, ������� �� ������ � �� ���������
		        {
		                err -= es;
		                if (err < 0)
		                {
		                        err += el;
		                        x += incx;//�������� ������ (�������� ����� ��� ����, ���� ���� �������� �� �����)
		                        y += incy;//��� �������� �����-������, ���� ���� �������� �� y
		                }
		                else
		                {
		                        x += pdx;//���������� ������ ������ ������, �.�. �������� ����� ��� ������, ����
		                        y += pdy;//���� ��� �� ����; �������� ����� ��� ����, ���� �� y
		                }
		                

		                if (getCurrentLen(a, new mPoint(x,y)) >= 15){
		                	
		                	a = new mPoint(x, y);
		                	g.add(new mAnimSprite(color, am, 2));
		                	int[] fl = {7,7};
		                	g.getLast().setFrameLen(fl);
		                	g.getLast().setAnimated(true);
		                	g.getLast().setXY(x, y);
		                	scen.addItem(g.getLast());
		                	g.getLast().update();
		                }
		               
		                try {
							sleep(5l);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        }
				 g.removeFirst();
	             scen.update();
			}

public void run() {
	// TODO Auto-generated method stub
	drawBresenhamLine (a.getX(), a.getY(), b.getX()+10, b.getY()+10, bmSprite);
}
        
}


