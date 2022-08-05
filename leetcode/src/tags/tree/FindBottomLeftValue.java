package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className FindBottomLeftValue
 * @description 513. 找树左下角的值
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @createTime 2022-07-18  14:57
 **/
public class FindBottomLeftValue {
    /**
     * 最底层 最左边
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        // 只有根节点的时候
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // 使用层序遍历，获取最后一层的第一个
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> noteQueue = new LinkedList<>();
        // 先将 root 入队
        noteQueue.offer(root);
        // 出队
        while (!noteQueue.isEmpty()) {
            // 入队之后，先将 queue 中的 node 取出，同时 left  和 right 入队
            List<Integer> temp = new ArrayList<>();
            int size = noteQueue.size() - 1;
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                if (node != null) {
                    temp.add(node.val);
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                }
                size--;
            }
            list.add(temp);
        }
        List<Integer> lastLevel = list.get(list.size() - 1);
        int res = lastLevel.get(0);
        return res;
    }

}
