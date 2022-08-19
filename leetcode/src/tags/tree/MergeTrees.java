package tags.tree;

import common.TreeNode;

/**
 * @program: javaDemo->mergeTrees
 * @description: 617. 合并二叉树
 * @author: lanwenquan
 * https://leetcode.cn/problems/merge-two-binary-trees/
 * @date: 2022-07-20 23:26
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 2 确定终止条件
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;

        // 3 确定单层逻辑
        // 左
        root1.left = mergeTrees(root1.left, root2.left);
        // 右
        root1.right = mergeTrees(root1.right, root2.right);
        // 最后返回 root1
        return root1;
    }
}
