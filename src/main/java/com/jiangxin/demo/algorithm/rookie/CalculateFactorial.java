package com.jiangxin.demo.algorithm.rookie;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年08月25日 17:28:18
 * @Description: 计算阶乘
 */
public class CalculateFactorial {

    public static void main(String[] args) {
        long res = calculate(4);
        System.out.println("res = " + res);
    }

    /* 给定一个参数N，返回：  1! + 2! + 3! + 4! + … + N!   的结果*/

    private static long calculate(int N){
        long ans = 0;
        long factorial = 1;
        for (int i = 1; i <= N; i++) {
            ans = ans + factorial * i;
            //每次循环将当前数的阶乘结果保存
            //下次循环计算下个数的阶乘可以用 factorial * 下个数 就是下个数的阶乘，比如 3! = 2! * 3
            factorial = factorial * i;
        }
        return ans;
    }

}
