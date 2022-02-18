package commom;

/**
 * @author Vin lan
 * @className ListNode
 * @description
 * @createTime 2022-02-10  14:00
 **/
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
