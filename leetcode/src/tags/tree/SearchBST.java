package tags.tree;

import common.TreeNode;

/**
 * @program: javaDemo->SearchBST
 * @description: 700 二叉搜索树中的搜索
 * @author: lanwenquan
 * @date: 2022-07-20 22:25
 */
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        return traversal(root, val);
    }

    public TreeNode traversal(TreeNode node, int val) {

        // 2 确定终止条件
        if (node.val == val || (node.left != null && node.left.val == val) || (node.right != null && node.right.val == val)) {
            return node;
        }

        // 3 单层循环
        // 在左子树中
        if (node.val > val && node.left != null) {
            TreeNode resNode = traversal(node.left, val);
            return resNode;
        }
        // 在右子树中
        if (node.val < val && node.right != null) {
            TreeNode resNode = traversal(node.right, val);
            return resNode;
        }
        return null;
    }
}
