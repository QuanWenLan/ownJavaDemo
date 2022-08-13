package tags.linkedlist;

import commom.ListNode;

/**
 * @program: javaDemo->DeleteMiddle
 * @description: 2095 删除链表的中间节点
 * https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/
 * @author: lanwenquan
 * @date: 2022-07-20 21:52
 */
public class DeleteMiddle {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        int size = nodeSize(head);
        if (size <= 5) {
            if (size == 2 || size == 3) {
                head.next = head.next.next;
                return head;
            }
            if (size == 4 || size == 5) {
                ListNode curNode = head.next;
                curNode.next = curNode.next.next;
                return head;
            }
        }
        // 这里要注意 快指针要从后一个开始
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 遍历完刚好slow是在删除节点的前一个节点
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 也可以快慢节点一起出发，只不过要定义一个节点 prev 时刻指向 slow 的前一个节点
     * @param head
     * @return
     */
    public ListNode deleteMiddle2(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    public int nodeSize(ListNode node) {
        int size = 0;
        ListNode dummyNode = new ListNode(0);

        while (dummyNode.next != null) {
            size++;
            dummyNode = dummyNode.next;
        }

        return size;
    }
}
