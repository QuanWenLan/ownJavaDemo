package jizhioffer.learn;

import commom.ListNode;

// https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=23267&ru=/practice/6ab1d9a29e88450685099d45c9e31e46&qru=/ta/coding-interviews/question-ranking
public class Snippet {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }
}
