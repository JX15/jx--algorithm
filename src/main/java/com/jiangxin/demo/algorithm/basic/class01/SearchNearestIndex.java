package com.jiangxin.demo.algorithm.basic.class01;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月28日 15:59:02
 * @Description: 在一个有序数组中，找大于等于某个数最左侧的位置
 * 20221028:8分钟，看了一次题解。接续加油
 */
public class SearchNearestIndex {


    public static int nearestIndex(int[] sortedArr,int num){
        if (sortedArr == null || sortedArr.length == 0){
            return -1;
        }

        int L = 0;
        int R = sortedArr.length - 1;

        int result = -1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= num){
                result = mid;
                R = mid - 1;
            }else if (sortedArr[mid] < num){
                L = mid + 1;
            }
        }

        return result;

    }

}
