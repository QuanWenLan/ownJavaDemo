package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className LevelOrder2
 * @description 107. 二叉树的层序遍历 II
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 * @createTime 2022-07-14  11:29
 **/
public class LevelOrder2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        System.out.println(levelOrderBottom(root));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
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
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                if (node != null) {
                    temp.add(node.val);
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                }
                size--;
            }
            list.add(temp);
        }
        List<List<Integer>> result2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result2.add(list.get(i));
        }
        return result2;
    }
}
