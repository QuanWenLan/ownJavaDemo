package tags.tree;

import common.TreeNode;

/**
 * @author Vin lan
 * @className SumOfLeftLeaves
 * @description 404. 左叶子之和
 * https://leetcode.cn/problems/sum-of-left-leaves/
 * @createTime 2022-07-18  12:21
 **/
public class SumOfLeftLeaves {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        SumOfLeftLeaves obj = new SumOfLeftLeaves();
        System.out.println(obj.sumOfLeftLeaves(root));
    }

    /**
     * 2 确定入参和返回值
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {

        // 1 终止条件
        if (root == null) {
            return 0;
        }
        // 3 单层循环逻辑
        // 判断当前节点是不是左叶子是无法判断的，必须要通过节点的父节点来判断其左孩子是不是左叶子。
        int result = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result = result + root.left.val;
        }
        // 统计左子树左节点的值
        int leftV = sumOfLeftLeaves(root.left);
        // 统计右子树左节点的值
        int rightV = sumOfLeftLeaves(root.right);
        return result + leftV + rightV;
    }
}
