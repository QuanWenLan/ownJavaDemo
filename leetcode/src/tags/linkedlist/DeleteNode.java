package tags.linkedlist;

import common.ListNode;

/**
 * @program: javaDemo->DeleteNode
 * @description: 面试题18. 删除链表的节点
 * @author: lanwenquan
 * @date: 2022-07-19 22:38
 */
public class DeleteNode {
    /**
     * 我的写法
     *
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
        while (tempNode != null) {
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
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
        return head;
    }

    /**
     * step 1：首先我们加入一个头部节点，方便于如果可能的话删除掉第一个元素。
     * step 2：准备两个指针遍历链表，一个指针指向当前要遍历的元素，另一个指针指向该元素的前序节点，便于获取它的指针。
     * step 3：遍历链表，找到目标节点，则断开连接，指向后一个。
     * step 4：返回时去掉我们加入的头节点。
     */
    public ListNode deleteNode3(ListNode head, int val) {
        //加入一个头节点
        ListNode res = new ListNode(0);
        res.next = head;
        //前序节点
        ListNode pre = res;
        //当前节点
        ListNode cur = head;
        //遍历链表
        while (cur != null) {
            //找到目标节点
            if (cur.val == val) {
                //断开连接
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        //返回去掉头节点
        return res.next;
    }
}
