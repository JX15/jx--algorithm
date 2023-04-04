package com.jiangxin.demo.algorithm.basic.class04;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月03日 20:44:07
 * @description: 大两倍数数量
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然小于num，求总个数
 */
public class BiggerTwiceNum {

    public static int doAlgorithm(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length - 1);
    }

    private static int process(int[] arr,int L,int R){
        if (L == R){
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1,R) + merge(arr,L,mid,R);
    }

    private static int merge(int[] arr,int L,int M,int R){
        //先求2倍数数量
        int res = 0;
        //滑动窗口
        int windowR = M + 1;
        for(int i = L;i <= M;i++){
            //因为左右两边数组都是有序的，那么arr[i+1] >= arr[i] >= arr[windowR] * 2,所以windowR不用回退
            //需要注意的是，最后一个windowR不能算进去，因为要么windowR越界，要么不满足题目条件
            while (windowR <= R && (long)arr[i] > (long)arr[windowR] * 2){
                windowR++;
            }
            //不包括windowR指向的位置
            res += windowR - M - 1;
        }

        //再归并
        int[] help = new int[R - L + 1];
        int helpIndex = help.length - 1;
        int pos1 = M;
        int pos2 = R;
        while (pos1 >= 0 && pos2 > M){
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
