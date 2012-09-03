package ru.SlimeEngine;

import java.util.LinkedList;
import ru.slime.objects.GameObject;
import ru.slime.objects.mSimpleSprite;
import android.graphics.Paint;
/**
 * 
 *          Слой на который можно добавлять объекты {@link mBasic}
 *          Используется в классе сцены {@link mScene}
 */ 
public class mLayer {
	LinkedList<GameObject> data = new LinkedList<GameObject>();
	 /**
     * номер слоя в стопке слоев на сцене
     */
    int level;
    
    /**
     * Кисть, которой должен отрисовываться слой
     */
    public Paint p;
    
    //TODO: решить в дальнейшем проблему потокобезопасности
    private boolean processing=false;
    
    /**
     * Конструктор принимает в качестве параметра номер слоя
     *
     * @param lev
     *            номер слоя на сцене
     */
    public mLayer(int lev) {
        processing = true;
        level = lev;
        p = new Paint();
        processing = false;
    }
    /**
     * добавление на слой объектов
     *
     * @param item
     *            - графический объект
     */
    public void add(GameObject item) {
        processing = true;
        data.add(item);
        processing = false;
    }
    /**
     * @return возвращает количество объектов на слое
     */
    public int getSize() {
        return data.size();
    }
    /**
     * Возвращает объект <b>GameObject</b> c номером <b>i</b>
     *
     * @param i
     *            номер объекта
     * @return объект со слоя с номером <b>i</b>
     */
    public GameObject get(int i) {
        return data.get(i);
    }
    /**
     * Удаляет объект с номером i со слоя
     *
     * @param i
     */
    public void delete(int i) {
        processing = true;
        data.remove(i);
        processing = false;
    }
    /**
     * Очищает текущий солой
     */
    public void clear() {
        processing = true;
        data.clear();
        processing = false;
    }
    /**
     * обновляет все объекты на слое
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
     * Изменение всех спрайтов на сцене
     * @param newx - множитель ширины
     * @param newy - множитель высоты
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
     * выбирает первый объект на слое, в который попадает точка с кооординатами
     * (f,g)
     *
     * @param f
     *            координата по x
     * @param g
     *            координата по y
     * @return объект со слоя
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
