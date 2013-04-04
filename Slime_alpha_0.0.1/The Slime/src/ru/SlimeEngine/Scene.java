package ru.SlimeEngine;

import java.util.LinkedList;

public class Scene {
    Layer[] layers;
    private int curLay;
    public static final int ORIENTATION_VERT=0;
    public static final int ORIENTATION_HOR=1;
    
    private int orient;
    private int layCount;
    public int TopLay;
    public int BotLay = 0;
    public int width, height;
    
    public Scene(int width, int height, int layerCount) {
        this.width = width;
        this.height = height;
        if (this.width>this.height){orient= Scene.ORIENTATION_HOR;}
        else
        {orient = Scene.ORIENTATION_VERT;}
        layers = new Layer[layerCount];
        curLay = 0;
        TopLay = layerCount - 1;
        for (int i = BotLay; i < layerCount; i++) {
            layers[i] = new Layer(i);
        }
        layCount = layerCount;
    }

    public void setWH(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void setCurLay(int i) {
        if (i <= TopLay)
            curLay = i;
        else
            curLay = TopLay;
    }

    public void addItem(GameObject item) {
        layers[curLay].add(item);
    }
    public void addItem(LinkedList<AnimSprite> items){
    	for (int i = 0; i < items.size(); i++)
    	layers[curLay].add(items.get(i));
    }

    public int getCurlayNum() {
        return this.curLay;
    }

    public Layer getCurLay() {
        return layers[this.curLay];
    }

    public void clear() {
        this.layers[this.curLay].clear();
    }

    public int getLayCount() {
        return layCount;
    }

    public void delete(int i) {
        this.layers[this.curLay].delete(i);
    }

    public void resize(float newx, float newy)
    {
        for (Layer a: layers)
        {
            a.resize(newx, newy);
        }
    }

    public Layer getLayerByNum(int num)
    {
        if (num<this.layCount)
        {
            return layers[num];
        }
        return null;
    }

    public int getOrient() {
        return orient;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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

    public void update() {
        for (Layer l : layers) {
            l.update();
        }
    }
}
