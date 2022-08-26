package com.jiangxin.demo.algorithm.rookie.array;


import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年08月25日 18:02:52
 * @Description: 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(20, 20);
        MyArrayUtil.printArray(randomArray);
        int[] ans = doSelectionSort(randomArray);
        MyArrayUtil.printArray(ans);
    }



    public static int[] doSelectionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }

        for (int i = 0; i < arr.length; i++) {
            //0 - length-1
            //1 - length-1
            //2 - length-1
            //...
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]){
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            MyArrayUtil.swap(arr, i, minIndex);
        }
        return arr;
    }
}
