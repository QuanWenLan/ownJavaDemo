package tags.linkedlist;

import common.ListNode;

/**
 * @author Lan
 * @createTime 2024-01-19  10:32
 * 剑指offer jz76 删除链表中重复的节点
 **/
public class DeleteDuplication {

    /**
     * 写出来了，开心！！！
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = pHead;
        // 当前节点
        ListNode cur = pHead;
        // 前一个节点
        ListNode prev = dummyNode;
        // 下一个不相同的节点
        ListNode nextDif = pHead.next;
        while (nextDif != null) {
            // 遍历当前节点的时候，通过使用nextDif去判断下个不相同节点的位置在哪里
            boolean haveSame = false;
            while (nextDif != null && nextDif.val == cur.val) {
                nextDif = nextDif.next;
                haveSame = true;
            }

            if (!haveSame) {
                cur = cur.next;
                prev = prev.next;
                nextDif = nextDif.next;
            } else {
                if (nextDif == null) {
                    prev.next = null;
                    cur.next = null;
                } else {
                    cur.next = nextDif;
                    cur = cur.next;
                    prev.next = nextDif;
                    nextDif = nextDif.next;
                }
            }
        }
        return dummyNode.next;
    }

    /**
     * 官方题解
     * step 1：给链表前加上表头，方便可能的话删除第一个节点。
     * ListNode res = new ListNode(0);
     * //在链表前加一个表头
     * res.next = pHead;
     * step 2：遍历链表，每次比较相邻两个节点，如果遇到了两个相邻节点相同，则新开内循环将这一段所有的相同都遍历过去。
     * step 3：在step 2中这一连串相同的节点前的节点直接连上后续第一个不相同值的节点。
     * step 4：返回时去掉添加的表头。
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead) {
        //空链表
        if (pHead == null)
            return null;
        ListNode res = new ListNode(0);
        //在链表前加一个表头
        res.next = pHead;
        ListNode cur = res;
        while (cur.next != null && cur.next.next != null) {
            //遇到相邻两个节点值相同
            if (cur.next.val == cur.next.next.val) {
                int temp = cur.next.val;
                //将所有相同的都跳过
                while (cur.next != null && cur.next.val == temp)
                    cur.next = cur.next.next;
            } else
                cur = cur.next;
        }
        //返回时去掉表头
        return res.next;
    }

    /**
     * 83 删除排序链表中的重复元素
     * {1,2,3,3,4,4,5}
     * 返回 {1,2,3,4,5}
     * 我这里是保留了，一开始题目看错了
     * 20ms击败0.33%使用 Java 的用户
     * 43.63MB击败5.01%使用 Java 的用户
     * 有点垃圾啊
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 当前节点
        ListNode cur = head;
        // 下一个不相同的节点
        ListNode nextDif = head.next;
        while (nextDif != null) {
            // 遍历当前节点的时候，通过使用nextDif去判断下个不相同节点的位置在哪里
            boolean haveSame = false;
            while (nextDif != null && nextDif.val == cur.val) {
                System.out.println("chong fu:" + nextDif.val);
                nextDif = nextDif.next;
                haveSame = true;
            }

            if (!haveSame) {
                cur = cur.next;
                nextDif = nextDif.next;
            } else {
                if (nextDif == null) {
                    cur.next = null;
                } else {
                    cur.next = nextDif;
                    cur = cur.next;
                    nextDif = nextDif.next;
                }
            }
        }
        return dummyNode.next;
    }

    /**
     * 83 删除排序链表中的重复元素
     * 官方题解
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates4(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
