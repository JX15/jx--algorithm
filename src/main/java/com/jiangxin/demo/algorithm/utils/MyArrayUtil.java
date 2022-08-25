package com.jiangxin.demo.algorithm.utils;

import java.util.Arrays;

/**
 * @Author: xin.jiang01@weimob.com
 * @DATE: 2022年08月25日 17:07:34
 * @Description: 数组工具
 */
public class MyArrayUtil {
    private MyArrayUtil(){

    }


    public static int[] getRandomArray(int maxLength,int maxValue){
        int length = (int)(Math.random() * maxLength);
        int[] resultArr =  new int[length];
        for (int i = 0; i < length; i++) {
            int value = (int)(Math.random() * maxValue);
            resultArr[i] = value;
        }
        return resultArr;
    }

    public static void changeArrayElementIndex(int[] arr,int index1,int index2){
        if (arr == null || arr.length == 0 || index1 >= arr.length || index2 >= arr.length || index1 == index2){
            return;
        }
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void printArray(int[] arr){
        String res = Arrays.toString(arr);
        System.out.println(res);
    }

}
