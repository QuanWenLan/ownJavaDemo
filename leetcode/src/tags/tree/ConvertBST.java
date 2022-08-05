package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className ConvertBST
 * @description 538. 把二叉搜索树转换为累加树
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 * @createTime 2022-08-05  15:10
 **/
public class ConvertBST {
    /**
     * 从题目可以看出，遍历的顺序是：右-》中-》左，实际上中序遍历是：左-》中-》右
     * 我们需要定义一个 pre 节点来记录当前节点的上一个节点，以此实现累加
     */

    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }

    /**
     * 当前节点的上一个节点的值
     */
    int pre = 0;

    /**
     * 1 递归函数，入参和返回值
     */
    public void traversal(TreeNode node) {
        // 2 终止条件
        if (node == null) {
            return;
        }
        // 右
        traversal(node.right);
        node.val += pre;
        // 记录上一个节点
        pre = node.val;
        // 左
        traversal(node.left);
    }
}
