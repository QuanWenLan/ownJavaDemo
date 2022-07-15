package tags.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vin lan
 * @className ConnectNode
 * @description 117. 填充每个节点的下一个右侧节点指针 ||
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
 * @createTime 2022-07-14  17:14
 **/
public class ConnectNode2 {
    /**
     *
     * 也是层序遍历，只不过在单层遍历的时候记录一下本层的头部节点，然后在遍历的时候让前一个节点指向本节点就可以了。这个是完美二叉树
     */
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node node = null;
            Node nodePre = null;

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    // 取出本层头一个节点
                    nodePre = queue.poll();
                    node = nodePre;
                } else {
                    node = queue.poll();
                    // 本层前一个节点 next 指向当前节点
                    nodePre.next = node;
                    nodePre = node;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 本层最后一个节点 next 指向 null
            nodePre.next = null;
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

