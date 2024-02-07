package tags.tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vin lan
 * @className MaxDepth
 * @description 二叉树的最大深度 104  二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
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
        System.out.println(new MaxDepth().maxDepth3(root));
    }

    /**
     * don't forget to initialize answer before call maximum_depth
     */
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

    /**
     * 中间迭代一层就是一个深度
     *
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int depth = 0;
        while (!que.isEmpty()) {
            int len = que.size();
            while (len > 0) {
                TreeNode node = que.poll();
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
                len--;
            }
            depth++;
        }
        return depth;
    }

    int result;
    void getdepth(TreeNode node, int depth) {
        result = Math.max(depth, result); // 中

        if (node.left == null && node.right == null) return ;

        if (node.left != null) { // 左
            depth++;    // 深度+1
            getdepth(node.left, depth);
            depth--;    // 回溯，深度-1
        }
        if (node.right != null) { // 右
            depth++;    // 深度+1
            getdepth(node.right, depth);
            depth--;    // 回溯，深度-1
        }
    }
    int maxDepth4(TreeNode root) {
        result = 0;
        if (root == null) return result;
        getdepth(root, 1);
        return result;
    }
}
