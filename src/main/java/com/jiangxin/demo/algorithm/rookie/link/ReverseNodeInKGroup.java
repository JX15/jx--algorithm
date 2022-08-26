package com.jiangxin.demo.algorithm.rookie.link;

/**
 * @Author: xin.jiang.cn
 * @DATE: 2022年08月26日 09:31:52
 * @Description: 给定一个单链表的头节点head，和一个正数k 实现k个节点的小组内部逆序，如果最后一组不够k个就不调整
 * 例子:
 * 调整前：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8，k = 3
 * 调整后：3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8
 */
public class ReverseNodeInKGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupNode(head, k);
        if (end == null){
            return head;
        }
        head = end;
        reverseGroupNode(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getKGroupNode(start, k);
            if (end == null){
                break;
            }
            reverseGroupNode(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    public static ListNode getKGroupNode(ListNode start,int k){
        while (--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }


    public static void reverseGroupNode(ListNode start,ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end){
            //记录当前节点的下一个节点
            next = cur.next;
            //将当前节点指向上一个节点
            cur.next = pre;
            //上一个节点变更为当前节点
            pre = cur;
            //当前节点变更为下一个节点
            cur = next;
        }
        //变更开始节点位置
        start.next = end;
    }

}
