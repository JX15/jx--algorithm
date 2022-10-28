package com.jiangxin.demo.algorithm.basic.class01;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月12日 09:47:27
 * @Description: 插入排序，目标：5分钟之内写出最优解
 * 20221012:大约13分钟，通过debug的方式改写正确
 * 20221028:大约10分钟，继续加油
 */
public class InsertionSort {

    public static int[] doSort(int[] arr){
        if (arr == null || arr.length < 2){
            return arr;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1 ; j--) {
                if (arr[j] < arr[j - 1]){
                    MyArrayUtil.swap(arr, j, j - 1);
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        MyArrayUtil.checkAlgorithm(BubbleSort::doSort, InsertionSort::doSort,1000000,10,100);
    }
}
