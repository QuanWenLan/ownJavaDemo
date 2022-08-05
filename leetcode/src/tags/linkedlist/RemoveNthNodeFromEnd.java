package tags.linkedlist;

import common.ListNode;

/**
 * @author Vin lan
 * @className RemoveNthNodeFromEnd
 * @description 19 删除链表的倒数第 N 个节点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list
 * @createTime 2021-11-12  11:11
 **/
public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        RemoveNthNodeFromEnd obj = new RemoveNthNodeFromEnd();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode result = obj.removeNthFromEnd(listNode, 2);

        obj.quoteTest1();
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    private void quoteTest1() {
        ListNode l = new ListNode(2);
        ListNode lh = l;
        for (int i = 0; i < 6; i++) {
            l.next = new ListNode(i);
            l = l.next;
        }
        System.out.println("l =>" + l);
        System.out.println("lh =>" + lh);
    }
}
