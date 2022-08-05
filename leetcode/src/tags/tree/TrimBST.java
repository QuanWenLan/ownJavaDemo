package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className TrimBST
 * @description 669. 修剪二叉搜索树
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 * @createTime 2022-08-05  14:44
 **/
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        // 如果root（当前节点）的元素小于low的数值，那么应该递归右子树，并返回右子树符合条件的头结点
        if (root.val < low) {
            // 寻找符合区间[low, high]的节点
            return trimBST(root.right, low, high);
        }
        // 如果root(当前节点)的元素大于high的，那么应该递归左子树，并返回左子树符合条件的头结点
        if (root.val > high) {
            // 寻找符合区间[low, high]的节点
            return trimBST(root.left, low, high);
        }
        // root在[low,high]范围内
        root.left = trimBST(root.left, low, high); // root->left接入符合条件的左孩子
        root.right = trimBST(root.right, low, high); // root->right接入符合条件的右孩子
        return root;
    }
}
