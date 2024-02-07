package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Lan
 * @createTime 2024-02-04  14:19
 * JZ77 按之字形顺序打印二叉树：给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 * https://www.nowcoder.com/share/jump/5391693681707028972992
 * 103. 二叉树的锯齿形层序遍历
 **/
public class PrintZhi {
    public static void main(String[] args) {
        PrintZhi obj = new PrintZhi();
        TreeNode root = new TreeNode(8, new TreeNode(6, new TreeNode(5), new TreeNode(7)),
                new TreeNode(10, new TreeNode(9), new TreeNode(11)));
        System.out.println(obj.print(root));
    }


    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) {
            return result;
        }
        // 层序遍历
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(pRoot);
        int level = 1;
        while (!list.isEmpty()) {
            ArrayList<Integer> levelResult = new ArrayList<Integer>();
            int len = list.size();
            if (level % 2 == 0) {
                for (int i = len - 1; i >= 0; i--) {
                    levelResult.add(list.get(i).val);
                }
            }
            for (int i = 0; i <= len - 1; i++) {
                TreeNode node = list.pop();
                if (level % 2 != 0) {
                    levelResult.add(node.val);
                }
                if (node.left != null) {
                    list.offer(node.left);
                }
                if (node.right != null) {
                    list.offer(node.right);
                }
            }
            result.add(levelResult);
            level++;
        }
        return result;
    }
}
