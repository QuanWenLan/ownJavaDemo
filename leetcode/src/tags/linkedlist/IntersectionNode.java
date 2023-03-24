package tags.linkedlist;

import common.ListNode;

/**
 * @author Vin lan
 * @className GetIntersectionNode
 * @description 160.链表相交
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 * @createTime 2023-03-20  14:00
 **/
public class IntersectionNode {
    public static void main(String[] args) {

    }

    /**
     * 题目数据 保证 整个链式结构中不存在环
     * curA
     * 3->9->8->7->6->5->4->2
     * curB
     * 11->5->4->4
     * 我们求出两个链表的长度，并求出两个链表长度的差值，然后让curA移动到，和curB 末尾对齐的位置，也就是
     * curA(在6这个位置)
     * 3->9->8->7->6->5->4->2
     * 此时我们就可以比较curA和curB是否相同，如果不相同，同时向后移动curA和curB，如果遇到curA == curB，则找到交点。
     * 否则循环退出返回空指针
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) { // 求链表A的长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) { // 求链表B的长度
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        // 让curA为最长链表的头，lenA为其长度
        if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            //2. swap (curA, curB);
            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // 求长度差
        int gap = lenA - lenB;
        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 遍历curA 和 curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 设置A链表公共节点之前长度为 x，B 链表为y。公共长度为 z，A 指针走 x+z+y；B指针走 y+z+x ，相等
        if (headA == null || headB == null) return null;
        ListNode l1 = headA, l2 = headB;

        // 注意我这里一开始是写错了，l2.next == null 去判断了，实际上不需要，只需要判断 指针是不是null 就可以了，不用 .next，.next 已经赋值给指针了
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l2;
    }
}
