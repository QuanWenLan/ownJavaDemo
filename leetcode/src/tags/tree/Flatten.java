package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lan
 * @createTime 2024-02-04  15:35
 * 114. 二叉树展开为链表
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-100-liked
 **/
public class Flatten {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8, new TreeNode(6, new TreeNode(5), new TreeNode(7)),
                new TreeNode(10, new TreeNode(9), new TreeNode(11)));
        new Flatten().flatten(root);
    }

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
        System.out.println(root);
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }
}
