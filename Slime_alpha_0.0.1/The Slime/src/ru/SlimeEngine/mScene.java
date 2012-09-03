package ru.SlimeEngine;

import java.util.LinkedList;

import ru.slime.objects.GameObject;
import ru.slime.objects.mAnimSprite;


/**
 * @author �������� �. �.
 * @version ����� ����� ����� �� ������ �� ������� ����� ���������
 *          ����������� ��������� (GameObject). ��� ���� ����� �������� ���� � ������� ������
 *          ������� ������ � ��������� �� ������� ����� �������������
 */
public class mScene {
	/**
     * ������ ����� �� �����
     */
    mLayer[] layers;
 
    /**
     * ����� �������� ����
     */
    private int curLay;
    public static final int ORIENTATION_VERT=0;
    public static final int ORIENTATION_HOR=1;
    
    private int orient;
    /**
     * ���������� ����� �� �����
     */
    private int layCount;
    /**
     * ������� ���� �� �����
     */
    public int TopLay;
    /**
     * ������ ���� �� �����
     */
    public int BotLay = 0;
    /**
     * ������ � ������ �����
     */
    public int width, height;
    
    /**
     * ����������� �������� ����� �����
     *
     * @param width
     *            - ������
     * @param height
     *            - ������
     * @param layerCount
     *            - ���������� �����
     */
    public mScene(int width, int height, int layerCount) {
        this.width = width;
        this.height = height;
        if (this.width>this.height){orient=mScene.ORIENTATION_HOR;}
        else
        {orient = mScene.ORIENTATION_VERT;}
        layers = new mLayer[layerCount];
        curLay = 0;
        TopLay = layerCount - 1;
        for (int i = BotLay; i < layerCount; i++) {
            layers[i] = new mLayer(i);
        }
        layCount = layerCount;
    }
    /**
     * ���������� ����� ������ � ������ �����
     *
     * @param w
     *            - ����� ������
     * @param h
     *            - ����� ������
     */
    public void setWH(int w, int h) {
        this.width = w;
        this.height = h;
    }
    /**
     * ���������� ������� ����
     *
     * @param i
     */
    public void setCurLay(int i) {
        if (i <= TopLay)
            curLay = i;
        else
            curLay = TopLay;
    }
    /**
     * �������� ������ �� ������� ���� �����
     *
     * @param item
     */
    public void addItem(GameObject item) {
        layers[curLay].add(item);
    }
    public void addItem(LinkedList<mAnimSprite> items){
    	for (int i = 0; i < items.size(); i++)
    	layers[curLay].add(items.get(i));
    }
    /**
     * @return ���������� ����� �������� ����
     */
    public int getCurlayNum() {
        return this.curLay;
    }
    /**
     * @return ���������� ������� ����
     */
    public mLayer getCurLay() {
        return layers[this.curLay];
    }
    /**
     * ������� ������� ����
     */
    public void clear() {
        this.layers[this.curLay].clear();
    }
    /**
     * @return ���������� ���������� ����� �� �����
     */
    public int getLayCount() {
        return layCount;
    }
    /**
     * ������� � �������� ���� ������ � ������� i
     *
     * @param i
     */
    public void delete(int i) {
        this.layers[this.curLay].delete(i);
    }
    /**
     * ��������� ������� ���� �������� �� �����
     * @param newx - ��������� �� ������
     * @param newy - ��������� �� ������
     */
    public void resize(float newx, float newy)
    {
        for (mLayer a: layers)
        {
            a.resize(newx, newy);
        }
    }
    public mLayer getLayerByNum(int num)
    {
        if (num<this.layCount)
        {
            return layers[num];
        }
        return null;
    }
    /**
     * @return ���������� ���������� ������
     * 1 - ���� ��������������
     * 0 - ���� ������������
     * ��������� ������������� ������ �� ����
     * ��� ������ ������ ��� ������ �����
     */
    public int getOrient() {
        return orient;
    }
    /**
     * @return ���������� ������ ����� � �������
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return ���������� ������ ����� � ��������
     */
    public int getHeight() {
        return height;
    }
    /**
     * �������� ������ c �������� ���� ����� ������� �������� ����� �
     * ������������ f � g
     *
     * @see mLayer
     * @param f
     *            - ���������� ��
     * @param g
     *            - ���������� ��
     * @return ���������� ������ ��� null ���� ����������� ������� �� ���� ��
     *         �������
     */
    public GameObject selectSprite(float f, float g) {
        GameObject sel = null;
        sel = this.layers[curLay].select(f, g);
        return sel;
    }
    
    public void deleteSprite(float f, float g){
    	GameObject sel = null;
    	sel = this.layers[curLay].select(f, g);
        for (int i = 0; i < this.layers[curLay].getSize();i++){
        	if (sel == this.layers[curLay].get(i)){
        		delete(i);
        	}
        }
    }
    /**
     * ��������� ��� ��������� �����
     */
    public void update() {
        for (mLayer l : layers) {
            l.update();
        }
    }
}
