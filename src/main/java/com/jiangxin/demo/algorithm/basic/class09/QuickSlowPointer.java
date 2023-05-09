package com.jiangxin.demo.algorithm.basic.class09;

import javafx.util.Pair;
import lombok.Data;

/**
 * @author: xin.jiang.cn
 * @date: 2023年05月08日 16:58:15
 * @description:
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 *
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 *
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 *
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class QuickSlowPointer {

    /**
     * 小结：
     *  1.快慢指针起始位置都在头节点，慢指针每次移动一步，慢指针每次移动两步,移动总步数以快指针为准
     *  2.从头节点开始移动的话，快指针移动的次数都是 (链表长度 - 1) / 2
     *  3.如果都从头节点开始移动(对应第1题)
     *      3.1.在奇数长度链表中，慢指针停在中点位置，快指针停在最后一个节点
     *      3.2.在偶数长度链表中，慢指针停在上中点位置，快指针停在最后一个节点的前一个节点
     *  4.如果快指针起始位置是头节点的下一个节点，对于奇数长度链表，移动次数会少一次，偶数长度链表则没有变化
     *      4.1.如果此时慢指针从头节点开始移动（对应第4题）
     *          4.1.1.对于奇数长度链表，慢指针会停在中点的前一个节点
     *          4.1.2.偶数长度链表则仍然停在上中点
     *      4.2.如果此时慢指针从头节点的下一个节点开始移动（对应第2题）
     *          4.2.1.对于奇数长度，慢指针会停在中点
     *          4.2.2.偶数长度链表则会停在下中点
     *  5.如果快指针起始位置是头节点的下下一个节点，对于奇数、偶数长度链表，移动次数都会少一次
     *      5.1.如果此时慢指针从头节点开始移动（对应第3题）
     *          5.1.1.奇数长度链表，慢指针会停在中点的前一个节点
     *          5.1.2.偶数长度链表，慢指针会停在上中点的前一个节点
     */

    @Data
    public static class Node{
        private int value;
        private Node next;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * @param head 头节点
     * @return 中点/上中点
     */
    public Node getMiddleNode01(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * @param head 头节点
     * @return 中点/下中点
     */
    public Node getMiddleNode02(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param head 头节点
     * @return 中点前一个/上中点前一个
     */
    public Node getMiddleNode03(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * @param head 头节点
     * @return 中点前一个/下中点前一个
     */
    public Node getMiddleNode04(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        QuickSlowPointer alg = new QuickSlowPointer();
        int maxListSize = 10;
        int maxValue = 100;
        int maxTimes = 1000000;
        for (int i = 0; i < maxTimes; i++) {
            Pair<Node,Integer> headAndSize = getRandomList(maxListSize, maxValue);
            Node head = headAndSize.getKey();
            Integer size = headAndSize.getValue();
            int mid = size % 2 == 0 ? size / 2 : size / 2 + 1;
            int count = 2;
            Node ans = head;
            while (ans.next != null && count < mid){
                ans = ans.next;
                count++;
            }
            if (size > 2){
                ans = size % 2 == 0 ? ans.next : ans;
            }
            Node ans2 = alg.getMiddleNode04(head);
            if (ans != ans2 && ans.value != ans2.value){
                System.out.println("出错啦！！");
                break;
            }
        }
    }



    public static Pair<Node,Integer> getRandomList(int maxListSize,int maxValue){
        Node head = new Node();
        head.value = (int)(Math.random() * maxValue);
        int size = (int)(maxListSize * Math.random()) + 1;
        Node cur = head;
        for (int i = 1; i < size; i++) {
            cur.next = new Node();
            cur.next.value = (int)(Math.random() * maxValue);
            cur = cur.next;
        }
        return new Pair<Node, Integer>(head,size);
    }
}
