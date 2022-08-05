package easy;

import common.TreeNode;

/**
 * @author Vin lan
 * @className InverseBinaryTree
 * @description
 * @createTime 2021-08-02  14:31
 **/
public class InverseBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = root.left = new TreeNode(2);
        left.left = new TreeNode(1);
        left.right = new TreeNode(3);
        TreeNode right = root.right = new TreeNode(7);
        right.left = new TreeNode(6);
        right.right = new TreeNode(9);

        InverseBinaryTree inverseBinaryTree = new InverseBinaryTree();
        inverseBinaryTree.invertTree(root);
    }

    TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        /**** 前序遍历位置 ****/
        // root 节点需要交换它的左右子节点
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        /**** 中序遍历位置不行，因为中序遍历会导致左子树在左边的时候翻转一次后，再在上一个父节点的位置被翻转成右子树，再翻转了这个右子树，又变回去了，结果之前的右子树原封不动变成了左子树 ****/
        // root 节点需要交换它的左右子节点
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
        invertTree(root.right);

        return root;
    }
}
