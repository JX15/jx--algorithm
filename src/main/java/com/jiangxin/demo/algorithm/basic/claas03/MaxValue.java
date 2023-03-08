package com.jiangxin.demo.algorithm.basic.claas03;

import com.jiangxin.demo.algorithm.basic.class01.InsertionSort;
import com.jiangxin.demo.algorithm.common.ConstantNum;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Objects;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月08日 19:41:31
 * @description: 求数组arr[L..R]中的最大值，怎么用递归方法实现
 */
public class MaxValue {
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        for (int i = 0; i < ConstantNum.ONE_MILLION; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(100, 100);
            if (randomArray.length == 0) {
                continue;
            }
            int max = getMax(randomArray);
            int[] sortedArray = InsertionSort.doSort(randomArray);
            if (!Objects.equals(max, sortedArray[sortedArray.length - 1])) {
                System.out.println("失败！");
            }
        }
        System.out.println("成功！");
    }
}
