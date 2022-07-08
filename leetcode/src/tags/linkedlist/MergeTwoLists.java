package tags.linkedlist;

import commom.ListNode;

/**
 * @author Vin lan
 * @className MergeTwoLists21
 * @description 21 合并两个有序的链表
 * https://leetcode.cn/problems/merge-two-sorted-lists
 * @createTime 2022-02-14  14:54
 **/
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(0);
        /*ListNode l1node1 = new ListNode(3);
        ListNode l1node2 = new ListNode(4);
        list1.next = l1node1;
        l1node1.next = l1node2;*/

        ListNode list2 = new ListNode(1);
        ListNode l2node1 = new ListNode(3);
        ListNode l2node2 = new ListNode(4);
        list2.next = l2node1;
        l2node1.next = l2node2;
        ListNode listNode = mergeTwoLists(list1, list2);
        System.out.println(listNode);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            // 定义一个指针，遍历第一个链表
            ListNode dummyNode1 = new ListNode(0);
            dummyNode1.next = list1;
            // 遍历第二个链表
            ListNode dummyNode2 = new ListNode(0);
            dummyNode2.next = list2;

            ListNode l1Node = dummyNode1.next;
            ListNode l2Node = dummyNode2.next;
            while (l1Node != null) {
                // 和第二个链表的节点比较

                // 需要找到合适的位置
                if (l1Node.val >= l2Node.val) {
                    if (l2Node.next == null) {
                        l2Node.next = l1Node;
                        break;
                    } else if (l1Node.val <= l2Node.next.val) {
                        ListNode temp1 = l1Node.next;
                        ListNode temp2 = l2Node.next;
                        l2Node.next = l1Node;
                        l2Node.next.next = temp2;
                        // 往右移动
                        l2Node = l2Node.next;
                        l1Node = temp1;
                    } else { // 大于后面的数的时候
                        l2Node = l2Node.next;
                    }
                } else {
                    ListNode head = new ListNode(l1Node.val);
                    head.next = l2Node;
                    dummyNode2.next = l2Node = head;
                    l1Node = l1Node.next;
                }
            }
            return dummyNode2.next;
        }
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
