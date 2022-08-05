package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className deleteNode
 * @description 450. 删除二叉搜索树中的节点
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 * @createTime 2022-08-05  09:55
 **/
public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 针对于特殊情况，刚好等于 root.val
        if (root == null) {
            return null;
        }
        root = deleteTraversal(root, key);
        return root;
    }

    /**
     1 需要返回值，返回删除节点后的根节点；可以使用前、中、后任意一种遍历方式
     */
    public TreeNode deleteTraversal(TreeNode root, int key) {
        // 2 确定终止逻辑
        // (1) 没有找到的情况
        if (root == null) {
            return null;
        }
        // 中
        // (2) 找到节点情况
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                // (2.1) 如果节点的左、右节点都为空，直接删除，返回null作为根节点
                return null;
            } else if (root.left == null) {
                // (2.2) 如果节点的左节点为空，右节点不为空，删除节点，右节点作为根节点返回
                return root.right;
            } else if (root.right == null) {
                // (2.3) 如果节点的左节点不为空，右节点为空，删除节点，左节点作为根节点返回
                return root.left;
            } else {
                // (2.3) 如果节点的左右节点都不为空，删除节点，将左节点放到右节点的最左面的孩子节点的左子树上
                // 需要找到右节点最左面的孩子（左子树）
                TreeNode curNode = root.right;
                TreeNode leftNode = root.left;
                while(curNode.left != null) {
                    curNode = curNode.left;
                }
                curNode.left = leftNode;
                root.left = null;
                return root.right;
            }
        }
        // 左子树
        if (root.val > key) {
            root.left = deleteTraversal(root.left, key);
        }
        // 右子树
        if (root.val < key) {
            root.right = deleteTraversal(root.right, key);
        }
        // 需要返回根节点
        return root;
    }
}
