package com.jiangxin.demo.algorithm.basic.class02;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author: xin.jiang.cn
 * @date: 2022年12月28日 16:01:28
 * @description: 一个数组中有一种数出现K次，其他数都出现了M次， M > 1, K < M 找到，出现了K次的数， 要求，额外空间复杂度O(1)，时间复杂度O(N),如果不存在则返回-1
 */
public class KTimesMTimesNum {


    public static void main(String[] args) {

        int K = 3;
        int M = 10;
        int MTimesNumCount = 10;
        int maxValue = 100;
        int checkTimes = 100000;
        System.out.println("校验开始");
        for (int i = 0; i < checkTimes; i++) {

            int[] randomArr = getRandomArr(K, M, MTimesNumCount, maxValue);
            int resultNum = printOnlyKTimeNum(randomArr, K, M);
            int resultForCompare = printOnlyKTimeNumForCompare(randomArr, K, M);
            if (resultNum != resultForCompare) {
                System.out.println("出错了");
                System.out.println(resultNum);
                System.out.println(resultForCompare);
            }
        }
        System.out.println("校验结束");
    }

    private static int[] getRandomArr(int K, int M, int MTimesNumCount, int maxValue) {

        int[] arr = new int[K + M * MTimesNumCount];
        int arrIndex = 0;

        int KTimesNum = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);

        for (int i = 0; i < K; i++) {
            arr[arrIndex++] = KTimesNum;
        }

        for (int i = 0; i < MTimesNumCount; i++) {

            int MTimesNum;
            do {
                MTimesNum = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (MTimesNum == KTimesNum);

            for (int j = 0; j < M; j++) {
                arr[arrIndex++] = MTimesNum;
            }

        }

        return arr;

    }

    public static int printOnlyKTimeNumForCompare(int[] arr, int K, int M) {
        Map<Integer, Integer> resultMap = Maps.newHashMap();
        for (int num : arr) {
            if (resultMap.containsKey(num)) {
                resultMap.put(num, resultMap.get(num) + 1);
            } else {
                resultMap.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            Integer value = entry.getValue();
            if (value == K) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public static int printOnlyKTimeNum(int[] arr, int K, int M) {

        //将数组里所有数的二进制每一位上的数相加
        //因为只有一种数出现了k次，其他数都出现了m次，所以肯定有位置上的数不能被m整除
        //找出这些位置，将这些位置设置为1，那么就找到了出现了k次的数

        int[] resultArr = new int[32];

        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                resultArr[i] += (num >> i & 1);
            }
        }

        int resultNum = 0;
        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i] % M == 0) {
                //doNothing!
            } else if (resultArr[i] % M == K) {
                resultNum |= (1 << i);
            } else {
                return -1;
            }
        }
        //deal with special num --- 0
        if (resultNum == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != K) {
                return -1;
            }
        }

        return resultNum;
    }


}
