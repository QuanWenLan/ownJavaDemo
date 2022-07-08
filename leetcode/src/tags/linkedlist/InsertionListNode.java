package tags.linkedlist;


import commom.ListNode;

/**
 * @program: javaDemo->InsertionListNode
 * @description: 链表插入排序
 * @author: lanwenquan
 * @date: 2022-02-20 16:55
 */
public class InsertionListNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0, null)))));
        ListNode result = insertionListNote(head);
        System.out.println(result);
    }
    public static ListNode insertionListNote(ListNode head) {
        // 定义一个 dummyHead 作为一个头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 待插入的节点为下一个节点
        ListNode curr = head.next;
        // 最后一个有序的节点为 lastSortedNode，初始为 head
        ListNode lastSortedNode = head;

        while(curr != null) {
            if (curr.val >= lastSortedNode.val) {
                // 插入到后面即可
                lastSortedNode = lastSortedNode.next;
            } else {
                // 小于的情况则是需要找到一个合适的位置进行插入。待插入节点的 前一个节点
                ListNode prev = dummyHead;
                // 需要找到待插入节点小于的那个节点
                while(curr.val >= prev.next.val) {
                    prev = prev.next;
                }
                // 插入
                lastSortedNode.next = curr.next; // 保持后面的节点的顺序不变
                curr.next = prev.next;
                prev.next = curr;
            }

            // 一轮插入结束后，curr 为 最后排序的节点的next
            curr = lastSortedNode.next;
        }
        return dummyHead.next;
    }
}
