package com.jiangxin.demo.algorithm.basic.class04;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月03日 19:06:34
 * @description: 归并排序
 */
public class MergedSort {

    public static void doSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        //递归终止条件
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int pos1 = L;
        int pos2 = M + 1;
        int helpIndex = 0;
        //合并左右数组
        while (pos1 <= M && pos2 <= R){
            help[helpIndex] = arr[pos1] < arr[pos2] ? arr[pos1++] : arr[pos2++];
        }
        //pos1越界或者pos2越界
        while (pos1 <= M){
            help[helpIndex++] = arr[pos1++];
        }
        while (pos2 <= R){
            help[helpIndex++] = arr[pos2++];
        }
        //将排序结果写回原数组
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
