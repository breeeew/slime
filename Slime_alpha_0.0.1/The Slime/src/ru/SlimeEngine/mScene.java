package ru.SlimeEngine;

import java.util.LinkedList;

import ru.slime.objects.GameObject;
import ru.slime.objects.mAnimSprite;


/**
 * @author Байрамлы А. Г.
 * @version Сцена Набор слоев на каждый из которых можно добавлять
 *          графические примитивы (GameObject). При этом имеет активный слой с которым сейчас
 *          ведется работа и пассивные на которые можно переключиться
 */
public class mScene {
	/**
     * Массив слоев на сцене
     */
    mLayer[] layers;
 
    /**
     * номер текущего слоя
     */
    private int curLay;
    public static final int ORIENTATION_VERT=0;
    public static final int ORIENTATION_HOR=1;
    
    private int orient;
    /**
     * Количество слоев на сцене
     */
    private int layCount;
    /**
     * Верхний слой на сцене
     */
    public int TopLay;
    /**
     * нижний слой на сцене
     */
    public int BotLay = 0;
    /**
     * высота и ширина сцены
     */
    public int width, height;
    
    /**
     * Конструктор Создание новой сцены
     *
     * @param width
     *            - ширина
     * @param height
     *            - высота
     * @param layerCount
     *            - количество слоев
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
     * Установить новую ширину и высоту сцены
     *
     * @param w
     *            - новая ширина
     * @param h
     *            - новая высота
     */
    public void setWH(int w, int h) {
        this.width = w;
        this.height = h;
    }
    /**
     * Установить текущий слой
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
     * Добавить объект на текущий слой сцены
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
     * @return Возвращает номер текущего слоя
     */
    public int getCurlayNum() {
        return this.curLay;
    }
    /**
     * @return возвращает текущий слой
     */
    public mLayer getCurLay() {
        return layers[this.curLay];
    }
    /**
     * Очищает текущий слой
     */
    public void clear() {
        this.layers[this.curLay].clear();
    }
    /**
     * @return возвращает количество слоев на сцене
     */
    public int getLayCount() {
        return layCount;
    }
    /**
     * удаляет с текущего слоя объект с номером i
     *
     * @param i
     */
    public void delete(int i) {
        this.layers[this.curLay].delete(i);
    }
    /**
     * изменение размера всех спрайтов на сцене
     * @param newx - множитель по ширине
     * @param newy - множитель по высоте
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
     * @return Возвращает ориентацию экрана
     * 1 - если горизонтальная
     * 0 - если вертикальная
     * ориетация расчитывается исходя из того
     * что больше высота или ширина сцены
     */
    public int getOrient() {
        return orient;
    }
    /**
     * @return возвращает ширину сцены в пиксеах
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return Возвращает высоту сцены в пикселах
     */
    public int getHeight() {
        return height;
    }
    /**
     * Выбирает объект c текущего слоя сцены который содержит точку с
     * координатами f и g
     *
     * @see mLayer
     * @param f
     *            - координата по
     * @param g
     *            - координата по
     * @return возвращает объект или null если подходящего объекта на слое не
     *         нашлось
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
     * Обновляет все соержимое сцены
     */
    public void update() {
        for (mLayer l : layers) {
            l.update();
        }
    }
}
