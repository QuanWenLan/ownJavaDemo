package tags.tree;

import commom.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className RightSideView
 * @description  199. 二叉树的右视图
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 * @createTime 2022-07-14  11:36
 **/
public class RightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left  = root.left = new TreeNode(2);
        left.right = new TreeNode(5);

        TreeNode right = root.right = new TreeNode(3);
//        right.left = new TreeNode(15);
//        right.right = new TreeNode(4);

        RightSideView obj = new RightSideView();
        System.out.println(obj.rightSideView(root));
    }
    /**
     * 和 层序遍历{@link LevelOrder}的区别在于，遍历到该层的最后一个才加入到 数组中
     * @param root 根节点
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
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
            int size = noteQueue.size() - 1;
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                if (node != null) {
                    // 最后一个元素加入到数组中
                    if (size == 0) {
                        list.add(node.val);
                    }
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                }
                size--;
            }
        }
        return list;
    }
}
