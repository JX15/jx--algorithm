package com.jiangxin.demo.algorithm.basic.class04;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月03日 19:59:22
 * @description: 求数组最小和
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int pos1 = L;
        int pos2 = M + 1;
        int res = 0;
        while (pos1 <= M && pos2 <= R){
            /*
            1.计算数组中所有数左边比它小的数的和，可以转换为计算一个数右边有多少数比它大，算出这个数一共要加几次
            2.比如[1,3,4,2,5],正常情况下计算（见题目描述），转换后计算，1右边有4个数比1大，所以结果加上4个1；3右边有2个数比3大，所以结果加上2个3，
             */

            //归并时，当归并左数组元素时，计算右数组里比它大的数有几个
            res += arr[pos1] < arr[pos2] ? (R - pos2 + 1) * arr[pos1] : 0;
            help[helpIndex++] = arr[pos1] < arr[pos2] ? arr[pos1++] : arr[pos2++];
        }
        while (pos1 <= M){
            help[helpIndex++] = arr[pos1++];
        }
        while (pos2 <= R){
            help[helpIndex++] = arr[pos2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }
}
