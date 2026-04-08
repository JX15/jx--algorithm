package com.jiangxin.demo.algorithm.basic.class03;

/**
 * @author: xin.jiang.cn
 * @date: 2023年02月05日 16:25:43
 * @description: 反转双向链表
 */
public class ReverseDoubleLinkedList {
    public static class DoubleNode{
        int val;
        DoubleNode last;
        DoubleNode next;
        DoubleNode (){};
        DoubleNode(int val){
            this.val = val;
        }
        DoubleNode(int val, DoubleNode next,DoubleNode last){
            this.val = val;
            this.last = last;
            this.next = next;
        }
    }

    public DoubleNode reverseDoubleLinkedList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode cur = null;
        while (head != null){
            cur = head.next;
            head.next = pre;
            head.last = cur;
            pre = head;
            head = cur;
        }
        return pre;
    }
}
