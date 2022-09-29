package tags.linkedlist;

import common.ListNode;

import java.nio.charset.Charset;

/**
 * @author Vin lan
 * @className AddToNumbers
 * @description 两数相加
 * https://leetcode.cn/problems/add-two-numbers/
 * @createTime 2022-09-28  11:14
 **/
public class AddToNumbers {

    public static void main(String[] args) {
        //          2->4->3(实际是342反过来)
        //          5->6->4(实际是465反过来)
        // result:  7->0->8(实际是807反过来)
        String csn = Charset.defaultCharset().name();
        if (Charset.isSupported(csn)) {
            System.out.println(Charset.forName(csn));
        }
        System.out.println(csn);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 直接遍历的时候进行相加
        ListNode curL1 = l1, curL2 = l2;
        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;
        // 记录进位
        int more = 0;
        while(curL1 != null || curL2 != null || more > 0) {
            int val = more;
            if (curL1 != null) {
                val += curL1.val;
                curL1 = curL1.next;
            }
            if (curL2 != null) {
                val += curL2.val;
                curL2 = curL2.next;
            }
            // 处理进位
            more = val / 10;
            // 处理剩余的
            val = val % 10;
            ListNode newNode = new ListNode(val);
            p.next = newNode;
            p = p.next;
        }
        return dummyNode.next;
    }
}
