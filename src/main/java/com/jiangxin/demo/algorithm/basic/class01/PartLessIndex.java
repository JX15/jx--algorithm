package com.jiangxin.demo.algorithm.basic.class01;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年10月28日 16:14:59
 * @Description:
 * 在一个无序数组中, 值有可能正, 负, 或者零, 数组中任由两个相邻的数一定不相等.
 * 定义局部最小:
 *      1.长度为1，arr[0]就是局部最小；
 *      2.数组的开头，如果arr[0] < arr[1] ，arr[0]被定义为局部最小。
 *      3.数组的结尾，如果arr[N-1] < arr[N-2] ，arr[N-1]被定义为局部最小。
 *      4.任何一个中间位置i, 即数组下标1~N-2之间, 必须满足arr[i-1] > arr[i] < arr[i+1] ,叫找到一个局部最小。
 * 请找到任意一个局部最小并返回。
 */
public class PartLessIndex {


    public static int getLessIndex(int[] arr){
        if (arr == null){
            return -1;
        }
        if (arr.length == 1){
            return 0;
        }
        if (arr[0] < arr[1]){
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length -2]){
            return arr.length - 1;
        }

        int L = 1;
        int R = arr.length - 2;

        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;

    }

    public static void main(String[] args) {
        int lessIndex = getLessIndex(new int[]{9,7,2,3,4,5,6,1,2,8,9});
        System.out.println("lessIndex = " + lessIndex);
    }
}
