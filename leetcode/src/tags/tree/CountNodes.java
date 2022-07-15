package tags.tree;

import commom.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className CountNodes
 * @description 222 完全二叉树的节点个数
 * @createTime 2022-07-15  16:21
 **/
public class CountNodes {


    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int counts = 0;
        LinkedList<TreeNode> noteQueue = new LinkedList<>();
        // 先将 root 入队
        noteQueue.offer(root);
        // 出队
        while (!noteQueue.isEmpty()) {
            // 入队之后，先将 queue 中的 node 取出，同时 left  和 right 入队
            int size = noteQueue.size() - 1;
            while (size >= 0) {
                TreeNode node = noteQueue.poll();
                counts++;
                if (node != null) {
                    if (node.left != null) {
                        noteQueue.offer(node.left);
                    }
                    if (node.right != null) {
                        noteQueue.offer(node.right);
                    }
                }
                size--;
            }
        }
        return counts;
    }
}
