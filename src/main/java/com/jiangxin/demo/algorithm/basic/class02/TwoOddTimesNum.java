package com.jiangxin.demo.algorithm.basic.class02;

/**
 * @author: xin.jiang.cn
 * @date: 2022年12月28日 15:48:11
 * @description: 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class TwoOddTimesNum {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,-1,-1,2,2,2,33,33,4,4,66,66,77,77,77,77};
        printTwoOddTimesNum(arr);
    }



    public static void printTwoOddTimesNum(int[] arr){
        //假设出现奇数次的数为a，b，那么 eor = a^b
        //eor二进制最右边的1，a，b在这一位上必不相同
        //数组中的数就可以分为2组，一组该位置上的数和a相同，另一组和b相同
        //进行^运算之前，先让数组中的数和eor最右边的1 进行&运算，如果非0才进行^运算，最后就可以得到两个数其中一个
        //最后和eor 进行^ 运算得到另一个

        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        //取出eor最右边的1，-eor 等价于 ~eor+1
        int rightOne = eor & (-eor);
        int numOne = 0;
        for (int num : arr) {
            if ((num & rightOne) != 0){
                numOne ^= num;
            }
        }
        int numTwo = eor ^ numOne;
        System.out.printf("两个出现奇数次的数为%s，%s%n", numOne,numTwo);
    }
}
