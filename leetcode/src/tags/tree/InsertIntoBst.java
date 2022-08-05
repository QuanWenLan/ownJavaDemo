package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className InsertIntoBst
 * @description 701. 二叉搜索树中的插入操作
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 * @createTime 2022-08-03  15:15
 **/
public class InsertIntoBst {
    /**
    * 递归有返回值
    */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回。
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            // 递归创建右子树
            root.right = insertIntoBST(root.right, val);
        } else if (root.val > val) {
            // 递归创建左子树
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 需要记录遍历节点的父节点（parent）
     */
    private TreeNode parent;

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        parent = new TreeNode(0);
        if (root == null) {
            root = new TreeNode(val);
        }
        traversal(root, val);
        return root;
    }


    /**
     * 1 确定入参和返回值。插入到跟根节点的左子树或者右子树，直接返回的是 root 节点，所以递归不需要返回值
     */
    public void traversal(TreeNode root, int val) {
        // 2 确定终止条件
        if (root == null) {
            // 插入节点
            if (parent.val > val) {
                parent.left = new TreeNode(val);
            } else if (parent.val < val) {
                parent.right = new TreeNode(val);
            }
            return;
        }
        // 记录遍历节点的父节点
        parent = root;
        // 左
        if (root.val > val) {
            traversal(root.left, val);
        }
        // 右
        if (root.val < val) {
            traversal(root.right, val);
        }
    }
}
