package tags.tree;

import common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className LevelOrder2
 * @description 429. N 叉树的层序遍历
 * https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
 * @createTime 2022-07-14  11:29
 **/
public class MultipleNodeLevelOrder {
    public static void main(String[] args) {

//        System.out.println(levelOrder(root));
    }

    /**
     * 和 二叉树 的区别在于一个节点可能有多个子节点
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<Node> noteQueue = new LinkedList<>();
        // 先将 root 入队
        noteQueue.offer(root);
        // 出队
        while (!noteQueue.isEmpty()) {
            // 入队之后，先将 queue 中的 node 取出，同时 这个节点的 listnodes  入队
            List<Integer> temp = new ArrayList<>();
            int size = noteQueue.size();
            for (int i = 0; i < size; i++) {
                Node node = noteQueue.poll();
                if (node != null) {
                    temp.add(node.val);
                    List<Node> children = node.children;
                    if (!children.isEmpty()) {
                        for (int j = 0; j < children.size(); j++) {
                            if (children.get(j) != null) {
                                noteQueue.offer(children.get(j));
                            }
                        }
                    }
                }
            }
            list.add(temp);
        }
        return list;
    }
}
