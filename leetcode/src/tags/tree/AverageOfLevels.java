package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className LevelOrder2
 * @description 637. 二叉树的层平均值
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 * @createTime 2022-07-14  11:29
 **/
public class AverageOfLevels {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        System.out.println(averageOfLevels(root));
    }

    /**
     * 和层序遍历二叉树一样，只不过把每层的结果求了一个平均值
     *
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
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
            int len = noteQueue.size();
            double allSum = 0.0d;
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                if (node != null) {
                    allSum += node.val;
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                    size--;
                }
            }
            // 完成一层之后再添加到 list 中
            list.add(allSum / len);
        }
        return list;
    }
}
