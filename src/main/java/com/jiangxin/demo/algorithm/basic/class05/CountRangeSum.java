package com.jiangxin.demo.algorithm.basic.class05;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月03日 21:11:59
 * @description: 达标子数组数量
 * 给定一个数组arr，两个整数lower和upper， 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 */
public class CountRangeSum {
    /*
    前置知识：前缀和数组

    思路：
        1.改变思考方向，求以数组中元素结尾的子数组有多少个满足条件
        2.假设 0-i 整体累加和是 x
        3.求满足条件，且以i位置结尾的子数组，有多少个在[lower,upper]上，
        4.等同于求，i之前的所有前缀和中有多少个前缀和在[x-upper,x-lower]上

     为什么可以用merge sort？
        1.这道题转换思路之后，就是求对于右边数组的每个元素，左边数组有多少个元素满足给定条件.
        2.对于merge sort，
            (1)它既可以做队友右边数组的每个元素，给定一个条件，求左边数组中有多少个满足条件的元素，
            (2)也可以求队友左边数组的每个元素，给定一个条件，右边数组中有多少个满足条件的元素
     */

    public static int countRangeSum(int[] arr,int lower,int upper){
        if (arr == null || arr.length == 0){
            return 0;
        }
        //求每个位置的前缀和数组（包括当前位置）
        long[] sumArr = new long[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        return process(sumArr,0,sumArr.length - 1,lower,upper);
    }

    private static int process(long[] sumArr,int L,int R,int lower,int upper){
        if (L == R){
            if (sumArr[L] >= lower && sumArr[L] <= upper){
                return 1;
            }else {
                return 0;
            }
        }
        int mid = L + ((R - L) >> 1);
        return process(sumArr, L, mid, lower, upper) + process(sumArr, mid + 1,R, lower, upper) + merge(sumArr,L,mid,R,lower,upper);
    }

    private static int merge(long[] sumArr, int L, int M, int R, int lower, int upper) {

        int res = 0;
        int windowL = L;
        int windowR = L;
        //前缀和数组中，两个元素的差（右边 - 左边）都是一个子数组
        //求满足条件的子数组，就是求前缀和数组中，左边数组有多少元素落在[右边数组元素 - upper，右边数组元素 - lower]
        //左右数组都是有序的，所以左边界和右边界一定是递增的，所以窗口不用回退
        for (int i = M + 1; i <= R; i++) {
            long min = sumArr[i] - upper;
            long max = sumArr[i] - lower;
            //左窗口停在满足条件最小处
            while (windowL <= M && sumArr[windowL] < min){
                windowL++;
            }
            //右边窗口停在满足条件最大处 + 1
            while (windowR <= M && sumArr[windowR] <= max){
                windowR++;
            }
            res += windowR - windowL;
        }

        long[] help = new long[R - L + 1];
        int helpIndex = 0;
        int pos1 = L;
        int pos2 = M + 1;
        while (pos1 <= M && pos2 <= R){
            help[helpIndex++] = sumArr[pos1] <= sumArr[pos2] ? sumArr[pos1++] :  sumArr[pos2++];
        }
        while (pos1 <= M){
            help[helpIndex++] = sumArr[pos1++];
        }
        while (pos2 <= R){
            help[helpIndex++] = sumArr[pos2++];
        }
        for (int i = 0; i < help.length; i++) {
            sumArr[L + i] = help[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int count = countRangeSum(new int[]{-2147483647, 0, -2147483647, 2147483647}, -385, 5999);
        System.out.println("count = " + count);
    }

}
