package tags.tree;

import easy.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className SortedArrayConvertToBST
 * @description 108 将有序数组转换成二叉搜索树（二叉排序树）
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree
 * 一种特殊的二叉树：二叉查找树（binary search tree）。这种树的主要作用就是进行查找操作。二叉查找树在二叉树的基础上又增加了一下几个条件。
 * <p>
 * - 如果左子树不为空，则左子树上所有节点的值均小于根节点的值
 * <p>
 * - 如果右子树不为空，则右子树上所有节点的值均大于根节点的值
 * <p>
 * - 左、右子树也都是二叉查找树
 * @createTime 2021-08-02  14:48
 **/
public class SortedArrayConvertToBST {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};  // 这是一个有序的数组，其结果是二叉树的中序遍历结果，转换成的二叉树如下，也可以是
        /**
         *        0                                   0
         *      /  \                                /   \
         *    -3    9                            -10     5
         *   /     /                               \      \
         * -10    5                                -3      9
         */
        SortedArrayConvertToBST obj = new SortedArrayConvertToBST();
        TreeNode treeNode = obj.sortedArrayToBST(nums);
        List<Integer> list = obj.inorderTraversal(treeNode);
        System.out.println(list.toString());
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 构造左节点
        root.left = helper(nums, left, mid - 1);
        // 构造右节点
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    ArrayList<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return result;
    }
}
