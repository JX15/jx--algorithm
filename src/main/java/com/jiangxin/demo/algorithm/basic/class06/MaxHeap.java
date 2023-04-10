package com.jiangxin.demo.algorithm.basic.class06;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Arrays;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月10日 21:17:56
 * @description: 大根堆
 */
public class MaxHeap {

    private int[] heap;
    private int limit;
    private int heapSize;

    public MaxHeap(int limit) {
        this.heap = new int[limit];
        this.limit = limit;
        this.heapSize = 0;
    }

    public void push(int value){
        if (heapSize == limit){
            throw new RuntimeException("heap is full!");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    public int pop(){
        int ans = heap[0];
        MyArrayUtil.swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    /**
     * 新加入的数，需要向上调整到合适的位置（移动到0位置或者比自己父亲小了）
     */
    private void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index - 1) / 2]){
            MyArrayUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从index位置
     */
    private void heapify(int[] arr,int index,int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            //先比较左右孩子，取大的
            int largest = (left + 1) < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            //拿大的孩子和父节点比，取大的
            largest = arr[largest] > arr[index] ? largest : index;
            //如果父节点最大，循环结束
            if (largest == index){
                break;
            }
            //交换父节点和最大孩子位置
            MyArrayUtil.swap(arr, largest, index);
            //位置更新，进行下一次循环
            index = largest;
            left = index * 2 + 1;
        }
    }
}
