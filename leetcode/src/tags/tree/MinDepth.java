package tags.tree;

import commom.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vin lan
 * @className MinDepth
 * @description
 * @createTime 2022-07-14  17:34
 **/
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int depth = 1;
        while (!que.isEmpty()) {

            int len = que.size();
            while (len > 0) {
                TreeNode node = que.poll();
                // 这里需要注意的是，左右节点都为空才算是叶子节点了
                if (node.left == null && node.right == null) {
                    return depth;
                }
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

    /**
     * 使用递归
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // System.out.println(" root: " + root.val);
        int leftDepth = minDepth2(root.left);
        // System.out.print("  leftDepth: " + leftDepth);
        int rightDepth = minDepth2(root.right);
        // System.out.println("  rightDepth: " + rightDepth);
        // 如果一个左子树为空，右子树不为空，这是并不是最低点，而是要 求得右子树的最低深度加上根节点
        if(root.left == null && root.right != null) {
            return 1 + rightDepth;
        }
        // 如果一个右子树为空，左子树不为空，这是并不是最低点，而是要 求得左子树的最低深度加上根节点
        if(root.right == null && root.left != null) {
            return 1 + leftDepth;
        }
        int result = 1 + Math.min(leftDepth, rightDepth);
        return result;
    }
}
