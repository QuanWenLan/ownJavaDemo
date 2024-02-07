package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lan
 * @createTime 2024-02-06  09:27
 * 230. 二叉搜索树中第K小的元素
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/
 **/
public class KthSmallest {

    /**
     * BST 的中序遍历结果是有序的（升序），所以用一个外部变量记录中序遍历结果第 k 个元素即是第 k 小的元素。
     */
    /**
     * 解法二，遍历完之后在比较
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        if (k > result.size()) {
            return -1;
        }
        Collections.sort(result);
        for (int i = 0; i <= result.size() - 1; i++) {
            if (i + 1 == k) {
                return result.get(i);
            }
        }
        return -1;
    }

    // 中序遍历是一个递增的序列
    public void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            inOrderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inOrderTraversal(root.right, result);
        }
    }

    // 解法1：遍历的时候直接比较

    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

    void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }
}
