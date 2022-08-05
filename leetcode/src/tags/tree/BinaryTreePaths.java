package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className BinaryTreePaths
 * @description 257. 二叉树的所有路径
 * https://leetcode.cn/problems/binary-tree-paths/
 * @createTime 2022-07-15  17:18
 **/
public class BinaryTreePaths {
    public static void main(String[] args) {
        BinaryTreePaths obj = new BinaryTreePaths();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        System.out.println(obj.binaryTreePaths(root));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<Integer> path = new LinkedList<>();
        traversal(root, path, list);
        return list;
    }

    public void traversal(TreeNode node, LinkedList<Integer> path, List<String> list) {
        // 终止条件
        if (node.left == null && node.right == null) {
            // 叶子节点时，需要将叶子节点也加入到 path 中去
            path.offer(node.val);
            // 此时，一条路径结束，需要加入到结果 list 中去
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            list.add(sb.toString());
            return;
        }

        // 单层循环，此时，这个是根节点
        path.offer(node.val);
        // **** 回溯和递归是一一对应的，有一个递归，就要有一个回溯 **** //
        if (node.left != null) {
            traversal(node.left, path, list);
            // 需要将叶子节点从 path 中删除，也就是 回溯 到根节点
            path.removeLast();
        }

        if (node.right != null) {
            traversal(node.right, path, list);
            // 需要将叶子节点从 path 中删除，也就是 回溯 到根节点
            path.removeLast();
        }
    }
}
