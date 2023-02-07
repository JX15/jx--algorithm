package com.jiangxin.demo.algorithm.basic.claas03;

/**
 * @author: xin.jiang.cn
 * @date: 2023年02月07日 15:00:36
 * @description: 删除给定的值
 */
public class RemoveValueInLinkedList {

    public class Node{
        int value;
        Node next;
        public Node(int value){
            this.value = value;
        }
        public Node(int value,Node next){
            this.value = value;
            this.next = next;
        }
    }


    public Node removeValueNode(Node head,int num){
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
