package com.jiangxin.demo.algorithm.basic.class08;

import com.jiangxin.demo.algorithm.basic.class04.MergedSort;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月20日 17:03:39
 * @description: 基数排序(数字必须时正数，且最大值不能过大)
 * 1.先找到数组中的最大值
 * 2.最大值循环除以10，直到为0，记录除的次数
 * 3.准备10个队列，存放数字是0-9的数
 * 4.从左到右遍历，个位数是0的进0队列，依此类推
 * 5.依此从0-9队列按先进先出顺序取出数，得到新的数组
 * 6.重复步骤4、5，重复次数为步骤3求出的数
 */
public class RadixSort {

    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //计算出最大值，用于后续计算循环次数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        //最大值不断除0，得到循环次数
        int digit = 0;
        while (max != 0){
            digit++;
            max = max / 10;
        }
        //优化版本，不需要10个队列
        for (int i = 0; i < digit; i++) {
            //使用一个计数数组，数组长度为10，可以记录0-9数字出现的次数
            //索引代表数字本身，索引位置的值代表出现次数
            int[] count = new int[10];
            //获取某个位置上的数字，并计算在计数数组中的位置
            //计数位置的值+1
            for (int num : arr) {
                int index = getPosNum(num, i);
                count[index]++;
            }
            //就算前缀和数组
            //此时，计数数组中每个位置的数，表示原数组中有多少个数 <= 当前索引代表的数
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            int[] help = new int[arr.length];
            //从右往左遍历
            //先获取这个数在计数数组中的位置，并取得计数数组中的值
            //模拟从队列中按顺序倒出数,因为之前计数是从左往右遍历的，所以这里从右往左遍历时，计数数组中的值 - 1 的位置就是这个数从队列里出来还原到原数组中的位置
            for (int j = arr.length - 1; j >=0; j--) {
                int index = getPosNum(arr[j],i);
                help[count[index] - 1] = arr[j];
                count[index]--;
            }

            System.arraycopy(help, 0, arr, 0, help.length);
        }
    }

    /**
     * 获取一个数某个位上的数字
     * @param num 数字
     * @param d 代表要求的位（个位为0，十位为1，依此类推）
     */
    private static int getPosNum(int num,int d){
        return (int) ((num / Math.pow(10, d)) % 10);
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 10;
        int maxTime = 10000000;
        for (int i = 0; i < maxTime; i++) {
            int[] randomArray = MyArrayUtil.getRandomArrayBiggerOrEqualZero(maxLen, maxValue);
            int[] copyArray = MyArrayUtil.copyArray(randomArray);
            radixSort(randomArray);
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
