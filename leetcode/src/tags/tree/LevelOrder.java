package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className LevelOrder
 * @description 二叉树的广序遍历 102
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @createTime 2021-07-06  11:49
 **/
public class LevelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = root.right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        System.out.println(levelOrder(root));
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return result;
        }
        // 根节点先入队
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            // 循环加入一个list中
            // for 循环这里不能使用  for(int i = 0; i < queue.size(); i++) 这个，因为后面入队了之后，queue的size变化了，导致结果不对
            for (int i = 0; i < size; i++) {
                // 根节点出队
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                // 它的左子树和右子树节点入队
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println(list.toString());
            result.add(list);
        }
        return result;
    }


}
