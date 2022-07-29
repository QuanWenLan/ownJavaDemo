package tags.tree;

import commom.TreeNode;

/**
 * @author Vin lan
 * @className GetMinimumDifference
 * @description 530. 二叉搜索树的最小绝对差
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * @createTime 2022-07-29  16:46
 **/
public class GetMinimumDifference {
    private TreeNode pre;// 记录当前节点的上一个遍历的结点
    private int result = Integer.MAX_VALUE;

    /**
     * https://www.programmercarl.com/
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traversal(root);
        return result;
    }

    public void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        //左
        traversal(root.left);
        //中
        if (pre != null) {
            result = Math.min(result, root.val - pre.val);
        }
        pre = root;
        //右
        traversal(root.right);
    }
}
