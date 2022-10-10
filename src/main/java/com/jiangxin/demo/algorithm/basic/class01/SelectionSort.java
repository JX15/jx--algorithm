package com.jiangxin.demo.algorithm.basic.class01;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月10日 12:51:04
 * @Description: 选择排序，目标：5分钟之内写出正确且最优解
 * 20221010：25分钟，第一次没写对，第二次写对了但不是最优解，第三次看了标准答案才磕磕绊绊改完
 * 20221010: 2分13秒，没有做基础判断
 */
public class SelectionSort {


    public static int[] doSort(int[] arr) {
        //todo-jx 第二天继续练一把，达标就下一题

        return arr;
    }

    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(10, 100);
        MyArrayUtil.printArray(randomArray);
        int[] sortedArray = doSort(randomArray);
        MyArrayUtil.printArray(sortedArray);

    }
}
