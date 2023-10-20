package tags.linkedlist;

import common.ListNode;

/**
 * @author Lan
 * @createTime 2023-10-18  16:41
 * 面试题 02.02. 返回倒数第 k 个节点
 * https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/
 **/
public class KthToLast {
    public int kthToLast(ListNode head, int k) {
        if (head == null || k <= 0) {
            return 0;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 将快指针先移动k步
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return 0; // 处理k大于链表长度的情况
            }
            fast = fast.next;
        }
        // 同时移动快慢指针，直到快指针到达链表末尾
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
    }
}
