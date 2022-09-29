package tags.linkedlist;

import common.ListNode;

import java.util.ArrayList;

/**
 * @program: javaDemo->ReverseList206
 * @description: 206 反转链表
 * https://leetcode.cn/problems/reverse-linked-list
 * @author: lanwenquan
 * @date: 2022-04-17 10:17
 */
public class ReverseList206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode next = head.next = new ListNode(3);
        next.next = new ListNode(4);
        ReverseList206 obj = new ReverseList206();
        ListNode reverseNode = obj.reverse(head);
        System.out.println(reverseNode);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ArrayList<ListNode> nodes = new ArrayList<ListNode>();

        nodes.add(new ListNode(head.val));
        while (head.next != null) {
            nodes.add(new ListNode(head.next.val));
            head = head.next;
        }

        // 添加到一个node里面
        ListNode zeroNode = new ListNode(0);
        int size = nodes.size();
        ListNode tempHead = nodes.get(size - 1);
        zeroNode.next = tempHead;
        for (size = size - 2; size >= 0; size--) {
            ListNode newNode = nodes.get(size);
            tempHead.next = newNode;
            tempHead = newNode;
        }
        return zeroNode.next;
    }

    public ListNode reverse(ListNode node) {
        // 前一个节点默认为null
        ListNode preNode = null;
        ListNode curNode = node;

        while(curNode != null) {
            // 记录节点的下一个节点
            ListNode nextNode = curNode.next ;
            // 改变next指向
            curNode.next = preNode;
            // 前一个节点变成当前节点
            preNode = curNode;
            // 下一个节点变成下一个节点
            curNode = nextNode;
        }
        return preNode;
    }
}
