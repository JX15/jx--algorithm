package com.jiangxin.demo.algorithm.basic.class07;

import java.util.*;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月11日 21:06:06
 * @description: 加强堆
 * 任意位置元素改变(更新/删除)，堆调整复杂度为O(logN)
 *
 */
public class EnhancedHeap<T> {

    private static class Inner<E>{
        private E value;
        private Inner(E value){
            this.value = value;
        }
    }

    /**
     * T一定要是非基础类型，有基础类型需要包一层
     */
    private ArrayList<T> heap;
    /**
     * 反向索引，记录元素的索引
     */
    private Map<T,Integer> indexMap;
    /**
     * 比较器
     */
    private Comparator<? super T> comparator;
    private int heapSize;

    public EnhancedHeap(Comparator<? super T> comparator){
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public int size(){
        return heapSize;
    }

    public boolean contains(T obj){
        return heap.contains(obj);
    }

    public boolean isEmpty(){
        return heapSize <= 0;
    }

    public T peek(){
        return heap.get(0);
    }

    public void push(T value){
        heap.add(value);
        indexMap.put(value, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 弹出堆顶元素
     * 需要先调用{@link EnhancedHeap#isEmpty()}确认堆是否为空
     */
    public T pop(){
        T ans = heap.get(0);
        //将弹出的数和堆末尾的数做交换
        swap(0, heapSize - 1);
        //删除弹出的元素,这样可以做到删除是O(1)
        heap.remove(--heapSize);
        //删除反向索引
        indexMap.remove(ans);
        //堆重新调整
        heapify(0);
        return ans;
    }

    /**
     * 移除元素
     * 需要先调用{@link EnhancedHeap#contains(Object)}确认元素是否存在
     */
    public void remove(T obj){
        T replace = heap.get(heapSize - 1);
        Integer index = indexMap.get(obj);
        heap.remove(--heapSize);
        heap.remove(obj);
        if (obj != replace){
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    /**
     * 任意位置元素变化，进行调整
     * 需要先调用{@link EnhancedHeap#contains(Object)}确认元素是否存在
     */
    public void resign(T resign) {
        heapInsert(indexMap.get(resign));
        heapify(indexMap.get(resign));
    }

    public List<T> getAllElements(){
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add(t);
        }
        return ans;
    }

    /**
     * 从index位置向上调整堆
     */
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index),heap.get((index - 1) / 2)) < 0){
            swap(index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从index位置向下调整堆
     */
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize){
            int smallest = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            smallest = comparator.compare(heap.get(smallest), heap.get(index)) < 0 ? smallest : index;
            if (smallest == index){
                return;
            }
            swap(index, smallest);
            index = smallest;
            left = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        T ti = heap.get(i);
        T tj = heap.get(j);
        heap.set(i, tj);
        heap.set(j, ti);
        indexMap.put(ti, j);
        indexMap.put(tj, i);
    }

}
