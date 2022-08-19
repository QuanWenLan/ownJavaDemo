package tags.linkedlist;

import common.ListNode;

/**
 * @program: javaDemo->DeleteNode
 * @description: 面试题18. 删除链表的节点
 * @author: lanwenquan
 *
 * @date: 2022-07-19 22:38
 */
public class DeleteNode {
    /**
     * 我的写法
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        // 遍历 head
        if (head.val == val) {
            // 删除头节点
            return head.next;
        }

        ListNode tempNode = head;
        while(tempNode != null) {
            if (tempNode.next != null && tempNode.next.val == val) {
                tempNode.next = tempNode.next.next;
                break;
            }
            tempNode = tempNode.next;
        }
        return head;
    }

    /**
     * 双指针写法
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val) {
        if(head.val == val) {
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) {
            pre.next = cur.next;
        }
        return head;
    }
}
