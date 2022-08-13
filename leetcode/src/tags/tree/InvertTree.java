package tags.tree;

import commom.TreeNode;

/**
 * @program: javaDemo->InvertTree
 * @description: 翻转二叉树
 * https://leetcode.cn/problems/invert-binary-tree/
 * @author: lanwenquan
 * @date: 2022-07-14 22:31
 */
public class InvertTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        InvertTree obj = new InvertTree();
        System.out.println(obj.invertTree(root));
    }

    /** 将整棵树的节点翻转
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        /**** 前序遍历位置 ****/
        // root 节点需要交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        /**** 中序遍历位置不行 ****/
        // root 节点需要交换它的左右子节点
        // TreeNode tmp = root.left;
        // root.left = root.right;
        // root.right = tmp;
        invertTree(root.right);

        return root;
    }
}
