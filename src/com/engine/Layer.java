package com.engine;

import java.util.LinkedList;

import com.engine.primitives.drawables.BaseObjectTypes;
import com.engine.primitives.drawables.GameObject;
import com.engine.primitives.drawables.SimpleSprite;
import android.graphics.Paint;

public class Layer {
	LinkedList<GameObject> data = new LinkedList<GameObject>();
    int level;
    
    public Paint paint;
    
    private boolean processing=false;
    
    public Layer(int lev) {
        processing = true;
        level = lev;
        paint = new Paint();
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
            if (a!=null&&a.getTypeofGameObject()== BaseObjectTypes.TYPE_SIMPLE_SPRITE)
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
}
