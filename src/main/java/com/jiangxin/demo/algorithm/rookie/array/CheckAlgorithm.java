package com.jiangxin.demo.algorithm.rookie.array;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年09月07日 23:08:26
 * @Description: 算法验证
 */
public class CheckAlgorithm {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        InsertionSort insertionSort = new InsertionSort();
        MyArrayUtil.checkAlgorithm(bubbleSort::doSort, insertionSort::doSort);
    }
}
