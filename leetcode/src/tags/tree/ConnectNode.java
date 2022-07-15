package tags.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className ConnectNode
 * @description 116. 填充每个节点的下一个右侧节点指针
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
 * @createTime 2022-07-14  17:14
 **/
public class ConnectNode {
    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下
     * 也是层序遍历，只不过在单层遍历的时候记录一下本层的头部节点，然后在遍历的时候让前一个节点指向本节点就可以了。这个是完美二叉树
     */
    public Node connect(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        if(root == null) {
            return null;
        }
        // 根节点先入队
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            // 头节点出队
            Node curNode = queue.poll();
            // 它的左子树和右子树节点入队
            if(curNode.left != null) {
                queue.offer(curNode.left);
            }
            if(curNode.right != null) {
                queue.offer(curNode.right);
            }

            for (int i = 1; i < size; i++) {
                // 节点出队，赋值给头节点，同时左右节点也需要入队
                Node node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                curNode.next = node;
                curNode = node;
            }
        }
        return root;
    }

    /**
     * Definition for a Node.
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}

