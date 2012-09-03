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
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
}
/**
 * Алгоритм Брезенхема
  * xstart, ystart - начало;
  * xend, yend - конец; 
  * "g.drawLine (x, y, x, y);" используем в качестве "setPixel (x, y);"
  * Можно писать что-нибудь вроде g.fillRect (x, y, 1, 1);
 */
public void drawBresenhamLine (float xstart, float ystart, float xend, float yend, final LinkedList<mAnimSprite> g)
{
		
        dx = xend - xstart;//проекция на ось икс
        dy = yend - ystart;//проекция на ось игрек
        incx = sign((int) dx);
        /*
         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
         * справа налево по иксу, то incx будет равен -1.
         * Это будет использоваться в цикле постороения.
         */
        incy = sign((int) dy);
        /*
         * Аналогично. Если рисуем отрезок снизу вверх -
         * это будет отрицательный сдвиг для y (иначе - положительный).
         */
 
        if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);
 
        if (dx > dy)
        //определяем наклон отрезка:
        {
         /*
          * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
          * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
          * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
          * по y сдвиг такой отсутствует.
          */
                pdx = incx;     pdy = 0;
                es = dy;        el = dx;
        }
        else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
                pdx = 0;        pdy = incy;
                es = dx;        el = dy;//тогда в цикле будем двигаться по y
        }
 
        x = xstart;
        y = ystart;
        err = el/2;
        //g.getFirst().setXY(x, y); //ставим первую точку
        //g.getFirst().update();	   //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла
        
       

				// TODO Auto-generated method stub
				for (int t = 0; t < el; t++)//идём по всем точкам, начиная со второй и до последней
		        {
		                err -= es;
		                if (err < 0)
		                {
		                        err += el;
		                        x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
		                        y += incy;//или сместить влево-вправо, если цикл проходит по y
		                }
		                else
		                {
		                        x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
		                        y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
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


