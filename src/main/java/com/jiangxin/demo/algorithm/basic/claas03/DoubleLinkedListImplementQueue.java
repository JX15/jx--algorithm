package com.jiangxin.demo.algorithm.basic.claas03;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月07日 22:38:16
 * @description: 双向链表实现栈
 */
public class DoubleLinkedListImplementQueue {

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

    public static class MyQueue<T>{

        private DoubleNode<T> head;
        private DoubleNode<T> tail;

        MyQueue(){

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
                DoubleNode<T> result = head;
                head = null;
                tail = null;
                return result.value;
            }
            else if (head != null){
                DoubleNode<T> result = head;
                DoubleNode<T> next = head.next;
                next.last = null;
                head.next = null;
                head = next;
                return result.value;
            }
            return null;

        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(30);
        MyQueue<Integer> myQueue = new MyQueue<>();
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
