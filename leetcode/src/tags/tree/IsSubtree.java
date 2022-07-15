package tags.tree;

import commom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className IsSubtree
 * @description 572. 另一棵树的子树
 * https://leetcode.cn/problems/subtree-of-another-tree/
 * @createTime 2022-07-15  14:50
 **/
public class IsSubtree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        /*TreeNode right = root.right = new TreeNode(5);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);*/

        TreeNode subRoot = new TreeNode(1);
        /*TreeNode left2 = new TreeNode(15);
//        left2.left = new TreeNode(10);
        subRoot.left = left2;
        subRoot.right = new TreeNode(7);*/
        IsSubtree obj = new IsSubtree();
//        System.out.println(obj.isSubtree2(root, subRoot));
        System.out.println("---------------");
        System.out.println(obj.isSubtree(root, subRoot));
    }

    /**
     * 利用深度优先的前序遍历将root、subRoot 排序成一个序列，然后比较是否含有
     * 最终通过了，卧槽
     * 执行用时：35 ms, 在所有 Java 提交中击败了5.35%的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (subRoot == null) {
            return false;
        }
        List<String> list1 = new ArrayList<>();
        traversal(root, list1);
        List<String> list2 = new ArrayList<>();
        traversal(subRoot, list2);
        System.out.println(list1);
        System.out.println(list2);
        // 进行对比
        boolean res = true;
        for (int i = 0, j = 0; i < list1.size(); i++) {
            int count = 0;
            for (; j < list2.size(); ) {
                if (!list1.get(i).equals(list2.get(j))) {
                    res = false;
                    j = 0;
                    if (count > 0) { // 有相同的时候，需要回退自增的 i 的次数
                        i -= count;
                    }
                    break;
                } else if (list1.get(i).equals(list2.get(j))) {
                    if (j == list2.size() - 1 && res) {
                        return true;
                    }
                    i++;
                    j++;
                    count++;
                    res = true;
                }
            }
        }
        return false;
    }

    public void traversal(TreeNode node, List<String> res) {
        // 递归终止条件
        if (node == null) {
            res.add("null ");
            return;
        }
        res.add(node.val + " "); // 中
        traversal(node.left, res); // 左
        traversal(node.right, res); // 右
    }

    /**
     * 使用递归
     *
     * @param s root
     * @param t subRoot
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // t 为 null 一定都是 true
        if (t == null) {
            return true;
        }
        // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
        if (s == null) {
            return false;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    /**
     * 判断两棵树是否相同
     */
    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
