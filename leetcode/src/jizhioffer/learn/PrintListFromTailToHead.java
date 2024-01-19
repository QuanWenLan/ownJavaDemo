package jizhioffer.learn;

import common.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Lan
 * @createTime 2024-01-18  09:43
 * JZ6 从尾到头打印链表
 **/
public class PrintListFromTailToHead {

    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        ListNode cur = listNode.next;
        result.add(listNode.val);
        while(cur != null) {
            result.add(cur.val);
            cur = cur.next;
        }
        ArrayList<Integer> result2 = new ArrayList<>();
        for(int i = result.size() - 1; i >= 0; i--) {
            result2.add(result.get(i));
        }
        return result2;
    }

    /**
     * 使用栈
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        //正序输出链表到栈中
        while(listNode != null){
            s.push(listNode.val);
            listNode = listNode.next;
        }
        //输出栈中元素到数组中
        while(!s.isEmpty())
            res.add(s.pop());
        return res;
    }
}
