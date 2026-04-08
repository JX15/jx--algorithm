package com.jiangxin.demo.algorithm.basic.class03;

import com.jiangxin.demo.algorithm.basic.class01.InsertionSort;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Objects;
import java.util.Stack;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月08日 18:49:45
 * @description:
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2）设计的栈类型可以使用现成的栈结构。
 */
public class GetMinStack {

    public static class MinStack{

        Stack<Integer> normalStack;
        Stack<Integer> minStack;

        MinStack(){
            normalStack = new Stack<>();
            minStack = new Stack<>();
        }

        public boolean push(int value){
            normalStack.push(value);
            if (minStack.empty()){
                minStack.push(value);
            }else if (value <= minStack.peek()){
                minStack.push(value);
            }
            return true;
        }

        public Integer pop(){
            Integer result = normalStack.pop();
            if (Objects.equals(result,minStack.peek())){
                minStack.pop();
            }
            return result;
        }

        public Integer getMin(){
            return minStack.peek();
        }

    }


    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            MinStack minStack = new MinStack();
            int[] randomArray = MyArrayUtil.getRandomArray(30, 100);
            if (randomArray.length == 0){
                continue;
            }
            for (int j : randomArray) {
                minStack.push(j);
            }
            int[] sortedArray = InsertionSort.doSort(randomArray);
            if (!Objects.equals(sortedArray[0], minStack.getMin())){
                System.out.println("失败！");
            }
        }
        System.out.println("成功！");


    }
}
