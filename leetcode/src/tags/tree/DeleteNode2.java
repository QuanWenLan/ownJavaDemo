package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className DeleteNode2
 * @description 删除二叉树节点
 * @createTime 2022-08-05  10:04
 **/
public class DeleteNode2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        DeleteNode2 obj = new DeleteNode2();
        System.out.println(obj.deleteNode(root, 20));

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        // 针对于特殊情况，刚好等于 root.val
        if (root == null) {
            return null;
        }
        root = deleteTraversal(root, key);
        return root;
    }

    /**
     * 1 需要返回值，返回删除节点后的根节点；可以使用前、中、后任意一种遍历方式
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
            // 这里第二次操作目标值：最终删除的作用
            if (root.right == null) {
                return root.left;
            }
            // 如果节点的左右节点都不为空，删除节点，将左节点放到右节点的最左面的孩子节点的左子树上
            // 需要找到右节点最左面的孩子（左子树）
            TreeNode curNode = root.right;
            while (curNode.left != null) {
                curNode = curNode.left;
            }
            // 这里第一次操作目标值：交换目标值其右子树最左面节点。
            int curNodeVal = curNode.val;
            curNode.val = root.val;
            root.val = curNodeVal;
        }
        // 左子树
        root.left = deleteTraversal(root.left, key);
        // 右子树
        root.right = deleteTraversal(root.right, key);
        // 需要返回根节点
        return root;
    }
}
