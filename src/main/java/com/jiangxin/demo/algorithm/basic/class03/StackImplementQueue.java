package com.jiangxin.demo.algorithm.basic.class03;

import com.jiangxin.demo.algorithm.common.ConstantNum;
import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月08日 19:17:58
 * @description: 用栈实现队列
 */
public class StackImplementQueue {

    public static class MyStackQueue<T>{
        private Stack<T> stackPush;
        private Stack<T> stackPop;
        MyStackQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        private void pushToPop(){
            if (stackPop.empty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public boolean add(T value){
            stackPush.push(value);
            pushToPop();
            return true;
        }

        public T poll(){
            if (stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty!");
            }
           pushToPop();
           return stackPop.pop();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < ConstantNum.ONE_MILLION; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(30, 100);
            if (randomArray.length == 0){
                continue;
            }
            MyStackQueue<Integer> myStackQueue = new MyStackQueue<>();
            Queue<Integer> queue = new ArrayBlockingQueue<>(randomArray.length);
            for (int j : randomArray) {
                myStackQueue.add(j);
                queue.add(j);
            }
            for (int k = 0; k < randomArray.length; k++) {
                if (!Objects.equals(myStackQueue.poll(), queue.poll())){
                    System.out.println("失败！");
                }
            }
        }
        System.out.println("成功");
    }
}
