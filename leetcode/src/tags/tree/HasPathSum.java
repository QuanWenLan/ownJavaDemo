package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className HasPathSum
 * @description 112. 路径总和
 * https://leetcode.cn/problems/path-sum/
 * @createTime 2022-07-18  15:22
 **/
public class HasPathSum {
    /**
     *                5
     *           4        8
     *        11        13  4
     *      7   2
     *
     */
    public static void main(String[] args) {
        HasPathSum obj = new HasPathSum();

        TreeNode root = new TreeNode(5);
        TreeNode left = root.left = new TreeNode(4);
        TreeNode left2 = left.left = new TreeNode(11);
        left2.left = new TreeNode(7);
        left2.right = new TreeNode(2);

        TreeNode right = root.right = new TreeNode(8);
        right.left = new TreeNode(13);
        right.right = new TreeNode(4);
        boolean b = obj.hasPathSum(root, 22);
        System.out.println(b);
    }

    /**
     * 这一类的题目要分开看，左子树和右子树分别处理，可以参考 {@link BinaryTreePaths} 所有路径
     * 这里用累加不好计算，用递减，减到最后会和最后一个叶子节点相等，则返回true.
     * <p>
     * 2 确定终止条件
     * 不要去累加然后判断是否等于目标和，那么代码比较麻烦，可以用递减，让计数器count初始为目标和，然后每次减去遍历路径节点上的数值。
     * <p>
     * 3 确定单层递归的逻辑
     * 因为终止条件是判断叶子节点，所以递归的过程中就不要让空节点进入递归了。
     * 递归函数是有返回值的，如果递归函数返回true，说明找到了合适的路径，应该立刻返回。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 先遍历根节点,减去根节点的值
        if (root == null) {
            return false;
        }
        // 递归结束条件，根节点到叶子节点路径，叶子节点：是指没有子节点的节点
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // 遍历左子树节点
        boolean hasPathInLeft = hasPathSum(root.left, targetSum - root.val);
        // 遍历右子树节点
        boolean hasPathInRight = hasPathSum(root.right, targetSum - root.val);
        return hasPathInLeft || hasPathInRight;
    }
}
