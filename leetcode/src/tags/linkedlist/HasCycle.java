package tags.linkedlist;

import common.ListNode;

/**
 * @program: javaDemo->HasCycle
 * @description: leetcode 141 环形链表
 * @author: lanwenquan
 * @date: 2023-01-31 22:33
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println("存在环？" + hasCycle(node1));
        System.out.println("环的长度是：" + cycleLength(node1));
        System.out.println("环的入环节点是：" + cyclePoint(node1).val);
    }

    /**
     * @param head 头节点
     * @return 是否有环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return false;
        }
        // p1 指针一次走一步
        ListNode p1 = head;
        // p2 指针一次走两步，必然会相遇
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param head 头节点
     * @return 环的长度
     */
    public static int cycleLength(ListNode head) {
        if (head == null) {
            return -1;
        }
        if (head.next == null) {
            return -1;
        }
        int count = 0;
        int returnFlag = 0;
        // p1 指针一次走一步
        ListNode p1 = head;
        // p2 指针一次走两步，必然会相遇
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                returnFlag++;
            }
            if (returnFlag == 1) {
                count++;
            }
            if (returnFlag == 2) {
                break;
            }
        }
        return count;
    }

    /**
     * @param head 头节点
     * @return 返回入环点
     * 从链表头结点到入环点的距离，等于从首次相遇点绕环n-1圈再回到入环点的距离.
     * 这样一来， 只要把其中一个指针放回到头节点位置， 另一个指针保持在首次相遇点， 两个指针都是每次向前走1步。
     * 那么，它们最终相遇的节点， 就是入环节点
     */
    public static ListNode cyclePoint(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        ListNode firstMeetPoint = null;
        // p1 指针一次走一步
        ListNode p1 = head;
        // p2 指针一次走两步，必然会相遇
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                firstMeetPoint = p1;
                break;
            }
        }
        ListNode meetNode = null;
        ListNode p3 = head;
        while (firstMeetPoint != null && firstMeetPoint.next != null) {
            p3 = p3.next;
            firstMeetPoint = firstMeetPoint.next;
            if (p3 == firstMeetPoint) {
                meetNode = p3;
                break;
            }
        }
        return meetNode;
    }
}
