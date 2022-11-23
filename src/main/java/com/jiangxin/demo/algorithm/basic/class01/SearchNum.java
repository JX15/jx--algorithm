package com.jiangxin.demo.algorithm.basic.class01;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月28日 15:44:59
 * @Description: 在一个有序数组中找某个数是否存在
 * 20221028:7分20秒，看了2次题解。继续加油。
 * 20221123:7分左右，位运算优先级低于（+ - * /）。继续。
 */
public class SearchNum {


    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }

        int L = 0;
        int R = sortedArr.length - 1;
        while (L <= R){
            int mid = L + ((R - L)>>1);
            if (sortedArr[mid] == num){
                return true;
            }else if (sortedArr[mid] < num){
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(100, 100);
        int[] sortedArr = BubbleSort.doSort(randomArray);
        MyArrayUtil.printArray(sortedArr);
        boolean flag = exist(sortedArr, 10);
        System.out.println("flag = " + flag);
    }
}
