package com.jiangxin.demo.algorithm.utils;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @Author: xin.jiang01@weimob.com
 * @DATE: 2022年08月25日 17:07:34
 * @Description: 数组工具
 */
public class MyArrayUtil {
    private MyArrayUtil(){

    }


    public static int[] getRandomArray(int maxLength,int maxValue){
        int length = 0;
        while (length == 0){
            length = (int)(Math.random() * maxLength);
        }
        int[] resultArr =  new int[length];
        for (int i = 0; i < length; i++) {
            int value = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
            resultArr[i] = value;
        }
        return resultArr;
    }

    public static void swap(int[] arr, int index1, int index2){
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

    public static int[] copyArray(int[] arr){
        if (arr == null){
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    /**
     * 整数 - 对数器
     * @param algorithmOne 算法1
     * @param algorithmTwo 算法2
     * @param times 比对次数
     * @param maxArrayLength 数组长度上限
     * @param maxArrayValue 数组值上限
     */
    public static void checkAlgorithm(Function<int[],int[]> algorithmOne,Function<int[],int[]> algorithmTwo,int times,int maxArrayLength,int maxArrayValue){
        System.out.println("开始校验");
        for (int i = 0; i < times; i++) {
            int[] randomArray = getRandomArray(maxArrayLength, maxArrayValue);
            int[] arrOne = copyArray(randomArray);
            int[] arrTwo = copyArray(randomArray);
            int[] resultOne = algorithmOne.apply(arrOne);
            int[] resultTwo = algorithmTwo.apply(arrTwo);
            for (int j = 0; j < resultOne.length; j++) {
                if (resultOne[j] != resultTwo[j]){
                    printArray(randomArray);
                    return;
                }
            }
        }
        System.out.println("校验结束");
    }


}
