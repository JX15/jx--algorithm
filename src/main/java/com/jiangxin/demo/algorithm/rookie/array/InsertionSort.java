package com.jiangxin.demo.algorithm.rookie.array;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年08月26日 16:14:18
 * @Description: 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(20, 100);
        MyArrayUtil.printArray(randomArray);
        int[] res = doSort(randomArray);
        MyArrayUtil.printArray(res);
    }

    public static int[] doSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            //0 ~ i
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j -1]){
                    MyArrayUtil.swap(arr, j,j-1);
                }
            }
        }
        return arr;
    }
}
