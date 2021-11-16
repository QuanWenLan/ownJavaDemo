package easy;

/**
 * @author Vin lan
 * @className MaxDepth  https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @description 二叉树的最大深度 104  二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * @createTime 2021-07-06  14:01
 **/
public class MaxDepth {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        System.out.println(new MaxDepth().maxDepth(root));
    }

    // don't forget to initialize answer before call maximum_depth
    private int answer;
    public int maxDepth(TreeNode root) {
        answer = 0;
        int depth = 1;
        maximumDepth(root, depth);
        return answer;
    }

    private void maximumDepth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        maximumDepth(root.left, depth + 1);
        maximumDepth(root.right, depth + 1);
    }

    // 方法2
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }
}
