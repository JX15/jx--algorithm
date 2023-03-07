package com.jiangxin.demo.algorithm.basic.claas03;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月07日 23:12:44
 * @description: 数组实现栈和队列
 */
public class ArrayImplementStackAndQueue {

    public static class MyStack<T>{

        T[] array;
        int index;
        int size;

        @SuppressWarnings("unchecked")
        MyStack(Class<T> componentType,int length){
            array = (T[])Array.newInstance(componentType,length);
            index = 0;
            size = 0;
        }

        public boolean push(T value){
            if  (size < array.length) {
                array[index] = value;
                index++;
                size++;
                return true;
            }
            return false;
        }

        public T pop(){
            if (size == 0){
                return null;
            }
            index--;
            T result = array[index];
            array[index] = null;
            size--;
            return result;
        }
    }

    public static class MyQueue<T>{
        T[] array;
        int index;
        int size;
        @SuppressWarnings("unchecked")
        MyQueue(Class<T> componentType,int length){
            array = (T[])Array.newInstance(componentType,length);
            index = 0;
            size = 0;
        }
        public boolean push(T value){
            if (size < array.length) {
                array[index] = value;
                index++;
                size++;
                return true;
            }
            return false;
        }

        public T pop(){
            if (size == 0){
                return null;
            }
            T result = array[0];
            array[0] = null;
            size--;
            index--;

            //位置调整
            System.arraycopy(array, 1, array, 0, array.length - 1);
            array[index] = null;


            return result;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        MyStack<Integer> myStack = new MyStack<>(Integer.class, 30);
        for (int i = 0; i < 100000; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(30, 100);
            for (int element : randomArray) {
                stack.push(element);
                myStack.push(element);
            }
            for (int j = 0; j < randomArray.length; j++) {
                if (Objects.equals(stack.pop(),myStack.pop())){
                    continue;
                }
                System.out.println("校验失败");
            }
        }
        System.out.println("成功");

        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(30);
        MyQueue<Integer> myQueue = new MyQueue<>(Integer.class, 30);
        for (int i = 0; i < 100000; i++) {
            int[] randomArray = MyArrayUtil.getRandomArray(30, 100);
            for (int element : randomArray) {
                queue.add(element);
                myQueue.push(element);
            }
            for (int j = 0; j < randomArray.length; j++) {
                if (Objects.equals(queue.poll(),myQueue.pop())){
                    continue;
                }
                System.out.println("校验失败");
            }
        }
        System.out.println("成功");
    }
}
