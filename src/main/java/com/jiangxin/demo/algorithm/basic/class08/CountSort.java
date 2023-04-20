package com.jiangxin.demo.algorithm.basic.class08;

import com.jiangxin.demo.algorithm.basic.class04.MergedSort;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月20日 17:03:25
 * @description: 计数排序
 * 1.知道排序数的大小范围（比如[0-10]）
 * 2.new一个排序数量范围大小的数组A，数组索引就代表这个数
 * 3.遍历排序数组，计算他在A数组中的索引，数组A中索引位置+1
 * 4.遍历A数组，当前索引就是这个数，索引位置的值就是这个数的数量，直接生成排序后的数组
 */
public class CountSort {

    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //先求最小值，处理存在负数的情况
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        //如果有负数，那么需要将数组进行处理，没个位置的数都减去这个负数，变正，在最后设置结果的时候再重新加回去
        //原因是：数组索引最小是0，不能表示负数
        if (min < 0){
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i] - min;
            }
        }
        //求最大值，目的是为了获取辅助数组的长度
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        //比如最大是10，那么索引要表示10的话，长度必须的10+1
        int[] help = new int[max + 1];
        int helpLen = help.length;
        for (int i = 0; i < arr.length; i++) {
            //计算数在help数组中的索引
            int index = arr[i] % helpLen;
            help[index]++;
        }

        int resIndex = 0;
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i]; j++) {
                //min只有为负数时才加
                arr[resIndex++] = i + Math.min(min, 0);
            }
        }
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxTime = 100000;
        for (int i = 0; i < maxTime; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(maxLen, maxValue);
            int[] copyArray = MyArrayUtil.copyArray(randomArray);
            countSort(randomArray);
            MergedSort.doSort(copyArray);
            for (int j = 0; j < randomArray.length; j++) {
                if (randomArray[j] != copyArray[j]){
                    System.out.println("出错");
                    return;
                }
            }
        }
        System.out.println("成功");
    }
}
