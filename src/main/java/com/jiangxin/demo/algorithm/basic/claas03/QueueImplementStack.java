package com.jiangxin.demo.algorithm.basic.claas03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: xin.jiang.cn
 * @date: 2023年03月08日 19:30:44
 * @description: 用队列实现栈
 */
public class QueueImplementStack {

    public static class MyQueueStack<T>{
        Queue<T> queue;
        Queue<T> help;
        MyQueueStack(){
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public boolean add(T value){
            return queue.offer(value);
        }

        public T poll(){
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            T result = queue.poll();
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return result;
        }
    }
}
