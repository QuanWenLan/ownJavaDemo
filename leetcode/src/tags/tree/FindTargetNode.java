package tags.tree;

import common.TreeNode;

/**
 * @author Lan
 * @createTime 2024-02-06  09:54
 * LCR 174. 寻找二叉搜索树中的目标节点
 * 某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 **/
public class FindTargetNode {
    public static void main(String[] args) {
        FindTargetNode obj = new FindTargetNode();
        TreeNode root = new TreeNode(7, new TreeNode(3, new TreeNode(1), new TreeNode(5)),
                new TreeNode(9, new TreeNode(8), new TreeNode(10)));
        System.out.println(obj.findTargetNode(root, 2));
    }

    int res = 0;
    int rank = 0;
    public int findTargetNode(TreeNode root, int cnt) {
        traversal(root, cnt);
        return res;
    }

    public void traversal(TreeNode node, int cnt) {
        // 需要将遍历改成 右 中 左
        if (node == null) {
            return;
        }
        // 右
        if (node.right != null) {
            traversal(node.right, cnt);
        }
        // 中
        rank++;
        if (rank == cnt) {
            res = node.val;
            return;
        }
        // 左
        if (node.left != null) {
            traversal(node.left, cnt);
        }
    }
}
