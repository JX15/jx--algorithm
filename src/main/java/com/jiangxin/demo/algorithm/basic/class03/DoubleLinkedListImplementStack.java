package com.jiangxin.demo.algorithm.basic.class03;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Objects;
import java.util.Stack;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月07日 22:38:16
 * @description: 双向链表实现栈
 */
public class DoubleLinkedListImplementStack {

    public static class DoubleNode<T> {
        DoubleNode<T> last;
        DoubleNode<T> next;
        T value;

        DoubleNode() {
        }

        DoubleNode(T value) {
            this.value = value;
        }
        DoubleNode(T value,DoubleNode<T> last,DoubleNode<T> next){
            this.value = value;
            this.last = last;
            this.next = next;
        }
    }

    public static class MyStack<T>{

        private DoubleNode<T> head;
        private DoubleNode<T> tail;

        MyStack(){

        }


        public boolean push(T value){
            DoubleNode<T> newNode = new DoubleNode<>(value);
            if (head == null){
                head = newNode;
            }
            if (tail != null) {
                tail.next = newNode;
                newNode.last = tail;
            }
            tail = newNode;

            return true;
        }

        public T pop(){
            if (tail == head){
                DoubleNode<T> result = tail;
                head = null;
                tail = null;
                return result.value;
            }
            else if (tail != null){
                DoubleNode<T> result = tail;
                DoubleNode<T> pre = tail.last;
                pre.next = null;
                tail.last = null;
                tail = pre;
                return result.value;
            }
            return null;

        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        MyStack<Integer> myStack = new MyStack<>();

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

    }
}
