package com.jiangxin.demo.algorithm.basic.class01;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月11日 09:45:15
 * @Description: 冒泡排序，目标：5分钟之内写出最优解
 * 20221011: 10分钟左右，看了最优解
 * 20221011: 5分钟左右
 * 20221012: 3分钟左右，下一题
 */
public class BubbleSort {

    public static int[] doSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]){
                    MyArrayUtil.swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        MyArrayUtil.checkAlgorithm(SelectionSort::doSort,BubbleSort::doSort,1000000,10,100);
    }
}
