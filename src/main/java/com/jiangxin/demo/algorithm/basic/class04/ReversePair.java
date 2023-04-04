package com.jiangxin.demo.algorithm.basic.class04;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月03日 20:25:38
 * @description: 求逆序对数量
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 可以利用归并排序, 在merge的时候,统计逆序对个数。
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为逆序对, 返回数组中所有的逆序对
 */
public class ReversePair {

    public static int reversePair(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr,int L,int R){
        if (L == R){
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr,L,mid) + process(arr, mid + 1, R) + merge(arr,L,mid,R);
    }

    private static int merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int helpIndex = help.length - 1;
        int pos1 = M;
        int pos2 = R;
        int res = 0;
        while (pos1 >=0 && pos2 > M){
            //从右往左归并，相等时先归并右边，否则不知道右边有几个数比左边当前数小
            res += arr[pos1] > arr[pos2] ? (pos2 - M) : 0;
            help[helpIndex--] = arr[pos1] > arr[pos2] ? arr[pos1--] : arr[pos2--];
        }
        while (pos1 >= 0){
            help[helpIndex--] = arr[pos1--];
        }
        while (pos2 > M){
            help[helpIndex--] = arr[pos2--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
}
