package tags.tree;

import commom.TreeNode;

/**
 * @author Vin lan
 * @className IsBalanced
 * @description 110 平衡二叉树
 * https://leetcode.cn/problems/balanced-binary-tree/
 * @createTime 2022-07-15  16:38
 **/
public class IsBalanced {
    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * <p>
     * 本题中，一棵高度平衡二叉树定义为：
     * <p>
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        IsBalanced obj = new IsBalanced();
        System.out.println(obj.isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        int i = countHeight(root);
        return i != -1;
    }


    /**
     * 统计子树的高度
     */
    public int countHeight(TreeNode node) {
        // 终止条件
        if (node == null) {
            return 0;
        }
        // 单层逻辑 ==
        int leftHeight = countHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = countHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        // 差值大于 1 不是平衡二叉树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            // 以当前节点为根节点的树的最大高度
            return 1 + Math.max(leftHeight, rightHeight);
        }
        // 单层逻辑 ==
    }
}
