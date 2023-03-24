package tags.linkedlist;

import common.ListNode;

/**
 * @author Vin lan
 * @className DetectCycle2
 * @description 142 环形链表2
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 * @createTime 2023-03-20  14:15
 **/
public class DetectCycle2 {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && slow != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            // 有环
            if (fast == slow) {
                ListNode index1 = fast;
                ListNode index2 = head;
                // 快慢指针相遇，此时从head 和 相遇点，同时查找直至相遇
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index2;
            }
        }
        return null;
    }
}
