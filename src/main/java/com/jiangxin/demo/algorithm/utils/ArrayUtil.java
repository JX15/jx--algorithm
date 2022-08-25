package com.jiangxin.demo.algorithm.utils;

/**
 * @Author: xin.jiang01@weimob.com
 * @DATE: 2022年08月25日 17:07:34
 * @Description: 数组工具
 */
public class ArrayUtil {
    private ArrayUtil(){

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


}
