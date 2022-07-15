package tags.tree;

import commom.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className LargestValuesInLevelOrder
 * @description 515. 在每个树行中找最大值
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * @createTime 2022-07-14  11:29
 **/
public class LargestValuesInLevelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        System.out.println(largestValues(root));
    }

    /**
     * TreeNode 的 val 取值是 -2^31 <= Node.val <= 2^31 - 1
     * @param root
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> noteQueue = new LinkedList<>();
        // 先将 root 入队
        noteQueue.offer(root);
        // 出队
        while (!noteQueue.isEmpty()) {
            // 入队之后，先将 queue 中的 node 取出，同时 left  和 right 入队
            List<Integer> temp = new ArrayList<>();
            int size = noteQueue.size() - 1;
            int maxValue = Integer.MIN_VALUE;
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                if (node != null) {
                    /*temp.add(node.val);*/
                    maxValue = Math.max(maxValue, node.val);
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                }
                size--;
            }
            list.add(maxValue);
        }
        return list;
    }
}
