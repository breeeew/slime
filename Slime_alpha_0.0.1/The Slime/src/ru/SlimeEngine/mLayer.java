package ru.SlimeEngine;

import java.util.LinkedList;
import ru.slime.objects.GameObject;
import ru.slime.objects.mSimpleSprite;
import android.graphics.Paint;
/**
 * 
 *          ���� �� ������� ����� ��������� ������� {@link mBasic}
 *          ������������ � ������ ����� {@link mScene}
 */ 
public class mLayer {
	LinkedList<GameObject> data = new LinkedList<GameObject>();
	 /**
     * ����� ���� � ������ ����� �� �����
     */
    int level;
    
    /**
     * �����, ������� ������ �������������� ����
     */
    public Paint p;
    
    //TODO: ������ � ���������� �������� ������������������
    private boolean processing=false;
    
    /**
     * ����������� ��������� � �������� ��������� ����� ����
     *
     * @param lev
     *            ����� ���� �� �����
     */
    public mLayer(int lev) {
        processing = true;
        level = lev;
        p = new Paint();
        processing = false;
    }
    /**
     * ���������� �� ���� ��������
     *
     * @param item
     *            - ����������� ������
     */
    public void add(GameObject item) {
        processing = true;
        data.add(item);
        processing = false;
    }
    /**
     * @return ���������� ���������� �������� �� ����
     */
    public int getSize() {
        return data.size();
    }
    /**
     * ���������� ������ <b>GameObject</b> c ������� <b>i</b>
     *
     * @param i
     *            ����� �������
     * @return ������ �� ���� � ������� <b>i</b>
     */
    public GameObject get(int i) {
        return data.get(i);
    }
    /**
     * ������� ������ � ������� i �� ����
     *
     * @param i
     */
    public void delete(int i) {
        processing = true;
        data.remove(i);
        processing = false;
    }
    /**
     * ������� ������� �����
     */
    public void clear() {
        processing = true;
        data.clear();
        processing = false;
    }
    /**
     * ��������� ��� ������� �� ����
     */
    public void update() {
        processing = true;
        for (GameObject a : data) {
            if (a != null)
                a.update();
        }
        processing = false;
    }
    /**
     * ��������� ���� �������� �� �����
     * @param newx - ��������� ������
     * @param newy - ��������� ������
     */
    public void resize(float newx, float newy)
    {
        processing = true;
        for (GameObject a: data){
            if (a!=null&&a.getTypeofGameObject()==GameObject.TYPE_SIMPLESPRITE)
            {
                ((mSimpleSprite)a).resize(newx, newy);
            }
        }
        processing = false;
    }
    /**
     * �������� ������ ������ �� ����, � ������� �������� ����� � �������������
     * (f,g)
     *
     * @param f
     *            ���������� �� x
     * @param g
     *            ���������� �� y
     * @return ������ �� ����
     */
    public GameObject select(float f, float g) {
        GameObject tmp = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) != null && data.get(i).isSelected(f, g)) {
                tmp = data.get(i);
                break;
            }
        }
        return tmp;
    }
 
    public boolean isProcessing() {
        return processing;
    }
}
