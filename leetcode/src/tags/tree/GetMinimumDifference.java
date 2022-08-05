package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className GetMinimumDifference
 * @description 530. 二叉搜索树的最小绝对差
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * @createTime 2022-07-29  16:46
 **/
public class GetMinimumDifference {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        GetMinimumDifference obj = new GetMinimumDifference();
        System.out.println(obj.getMinimumDifference(root));
    }

    /**
     * 记录当前节点的上一个遍历的结点
     */
    private TreeNode pre;
    private int result = Integer.MAX_VALUE;

    /**
     * https://www.programmercarl.com/
     *
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
        // 可以思考只有一层的时候，pre 节点如何赋值：遍历到最左边的节点的时候，再进行下一次遍历触发了递归结束条件，返回，此时cur节点是最左边节点
        // 这个时候 pre 即为前一个节点（对于下一个要遍历的节点来说）
        if (pre != null) {
            result = Math.min(result, root.val - pre.val);
        }
        pre = root;
        //右
        traversal(root.right);
    }
}
