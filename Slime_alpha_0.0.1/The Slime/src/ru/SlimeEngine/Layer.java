package ru.SlimeEngine;

import java.util.LinkedList;
import android.graphics.Paint;

public class Layer {
    private int level;
    public Paint p;

    LinkedList<GameObject> data = new LinkedList<GameObject>();

    //TODO: ������ � ���������� �������� ������������������
    private boolean processing=false;
    
    public Layer(int lev) {
        setLevel(lev);
        p = new Paint();
        processing = true;
        processing = false;
    }
    public void add(GameObject item) {
        processing = true;
        data.add(item);
        processing = false;
    }
    public int getSize() {
        return data.size();
    }
    public GameObject get(int i) {
        return data.get(i);
    }
    public void delete(int i) {
        processing = true;
        data.remove(i);
        processing = false;
    }
    public void clear() {
        processing = true;
        data.clear();
        processing = false;
    }
    public void update() {
        processing = true;
        for (GameObject a : data) {
            if (a != null)
                a.update();
        }
        processing = false;
    }

    public void resize(float newx, float newy)
    {
        processing = true;
        for (GameObject a: data){
            if (a!=null&&a.getTypeOfGameObject()==GameObject.TYPE_SIMPLESPRITE)
            {
                ((SimpleSprite)a).resize(newx, newy);
            }
        }
        processing = false;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
