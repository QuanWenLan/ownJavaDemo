package commom;

import java.util.List;

/**
 * @author Vin lan
 * @className Node
 * @description N 叉树的节点
 * @createTime 2022-07-14  12:11
 **/
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
