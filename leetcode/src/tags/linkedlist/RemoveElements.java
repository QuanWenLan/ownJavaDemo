package tags.linkedlist;

import common.ListNode;

/**
 * @author Vin lan
 * @className RemoveElements
 * @description 203.移除链表元素
 * @createTime 2023-03-20  15:33
 **/
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 构建一个dummyNode 用来做删除操作
        ListNode dummyNode = new ListNode(0, head);
        ListNode preNode = dummyNode;
        ListNode curNode = head;
        while (curNode != null) {
            int value = curNode.val;
            if (value == val) {
                preNode.next = curNode.next;
            } else {
                // 将当前节点赋值给前一个节点
                preNode = curNode;
            }
            curNode = curNode.next;
        }
        return dummyNode.next;
    }

    /**
     * 不添加虚拟节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return head;
        }
        // 已确定当前head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 不添加虚拟节点and pre Node方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode curr = head;
        while (curr != null) {
            while (curr.next != null && curr.next.val == val) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }
}
