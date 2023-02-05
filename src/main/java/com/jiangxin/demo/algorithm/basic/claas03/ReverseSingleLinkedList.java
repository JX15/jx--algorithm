package com.jiangxin.demo.algorithm.basic.claas03;

/**
 * @author: xin.jiang.cn
 * @date: 2023年02月05日 16:14:24
 * @description: 反转单链表
 */
public class ReverseSingleLinkedList {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode (){};
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head){
        ListNode cur = null;
        ListNode pre = null;
        while (head != null){
            cur = head.next;
            head.next = pre;
            pre = head;
            head = cur;

        }
        return pre;
    }

    /**
     * https://leetcode.cn/problems/reverse-linked-list-ii/
     */
    public static ListNode reverseBetween(ListNode head,int left,int right){
        return null;
    }

}
