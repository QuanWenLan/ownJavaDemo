package tags.linkedlist;

import commom.ListNode;

/**
 * @program: javaDemo->DeleteNode2
 * @description: 237. 删除链表中的节点
 * @author: lanwenquan
 * @date: 2022-07-19 22:41
 */
public class DeleteNode2 {
    public void deleteNode(ListNode node) {
        // 替换掉要删除的自己
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
