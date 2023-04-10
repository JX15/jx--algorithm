package com.jiangxin.demo.algorithm.basic.class06;

import com.jiangxin.demo.algorithm.basic.class04.MergedSort;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月10日 21:34:17
 * @description: 堆排序
 */
public class HeapSort {

    public static void sort(int[] arr){
        /**
         * 1.构建大根堆
         * 2.将堆顶元素和堆尾元素互换，堆大小-1
         * 3.堆顶元素向下调整，重新调整为大根堆
         * 4.重复2，3，知道堆大小为0，排序完成
         */
        //从上往下构建O(N * logN)
        /*for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/
        //从下往上构建O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
        int heapSize = arr.length;
        while (heapSize > 0){
            MyArrayUtil.swap(arr, 0, --heapSize);
            heapify(arr, heapSize, 0);
        }
    }


    private static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index - 1) / 2]){
            MyArrayUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr,int heapSize,int index){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index){
                break;
            }
            MyArrayUtil.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int maxLength= 100;
        int maxValue = 100;
        int maxTimes = 1000000;
        for (int i = 0; i < maxTimes; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(maxLength, maxValue);
            int[] copyArray = MyArrayUtil.copyArray(randomArray);
            sort(randomArray);
            MergedSort.doSort(copyArray);
            boolean error = false;
            for (int j = 0; j < randomArray.length; j++) {
                if (randomArray[j] != copyArray[j]){
                    System.out.println("出错啦！");
                    System.out.println("堆排序");
                    MyArrayUtil.printArray(randomArray);
                    System.out.println("归并排序");
                    MyArrayUtil.printArray(copyArray);
                    error = true;
                    break;
                }
            }
            if (error){
                break;
            }
        }
        System.out.println("成功！");
    }

}
