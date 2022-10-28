package com.jiangxin.demo.algorithm.basic.class01;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月28日 15:44:59
 * @Description: 在一个有序数组中找某个数是否存在
 * 20221028:7分20秒，看了2次题解。继续加油。
 */
public class SearchNum {


    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }

        int left = 0;
        int right = sortedArr.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return sortedArr[left] == num;
    }

    public static void main(String[] args) {
        int[] randomArray = MyArrayUtil.getRandomArray(100, 100);
        int[] sortedArr = BubbleSort.doSort(randomArray);
        MyArrayUtil.printArray(sortedArr);
        boolean flag = exist(sortedArr, 10);
        System.out.println("flag = " + flag);
    }
}
