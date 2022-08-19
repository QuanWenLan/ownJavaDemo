package tags.tree;


import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: javaDemo->PreOrderTraversalByStack
 * @description: 144 二叉树的前序遍历（栈的 迭代法）
 * @author: lanwenquan
 * @date: 2022-07-13 21:40
 */
public class TreeOrderTraversalByStack {
    /**
     *        5
     *      /  \
     *      4   6
     *     / \ / \
     *    1  2 7  8
     * 前序输出（中-左-右）：  5 4 1 2 6 7 8
     * 中序输出（左-中-右）：  1 4 2 5 7 6 8
     * 后序输出（左-右-中）：  1 2 4 7 8 6 5
     */
    public static void main(String[] args) {
        System.out.println("     *        5\n" +
                "     *      /  \\ \n" +
                "     *      4   6 \n" +
                "     *     / \\ / \\ \n" +
                "     *    1  2 7  8 \n");
        TreeNode root = new TreeNode(5);
        TreeNode left = root.left = new TreeNode(4);
        left.left = new TreeNode(1);
        left.right = new TreeNode(2);

        TreeNode right = root.right = new TreeNode(6);
        right.left = new TreeNode(7);
        right.right = new TreeNode(8);
        preOrderTraversal(root);
        inOrderTraversal(root);
        postOrderTraversal(root);
    }

    /**
     * 前序遍历是 中-》左-》右 输出的，那么入栈则需要 中-》右-》左
     * @param root
     */
    public static List<Integer> preOrderTraversal(TreeNode root) {
        // 用来存储二叉树节点的值
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 前序遍历
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        // 循环遍历栈，根节点出栈之后，右节点入栈
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.peek();
            result.add(node.val);
            // 出栈
            nodeStack.pop();
            // 空节点不需要入栈
            if (node.right != null) {
                nodeStack.push(node.right);    // 右
            }
            if (node.left != null) {
                nodeStack.push(node.left);    // 左
            }
        }
        System.out.println("前序遍历 = " + result.toString());
        return result;
    }


    /**
     * 中遍历是 左 -》中-》右 输出的，入栈和出栈的规则和前序遍历的有点不一样，中序先访问的二叉树的节点，然后一层一层向下访问，直到树的最左的底部
     * 再开始处理节点
     * @param root
     */
    public static List<Integer> inOrderTraversal(TreeNode root) {
        // 用来存储二叉树节点的值
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 前序遍历
        Stack<TreeNode> nodeStack = new Stack<>();

        // 当前节点
        TreeNode curNode = root;
        // 循环遍历栈，根节点出栈之后，右节点入栈
        while (!nodeStack.isEmpty() || curNode != null) {
            // 1 指针访问节点，访问到最底层
            if (curNode != null) {
                nodeStack.push(curNode);
                curNode = curNode.left;  // 左
            } else {
                // 如果说 curNode.left 为空了，说明到了最后一个节点，节点出栈
                curNode = nodeStack.pop();  // 中
                result.add(curNode.val);
                // 需要将右节点入栈, 如果curNode本身为 null 则会继续走到 else 内
                curNode = curNode.right; // 右
            }
        }
        System.out.println("中序遍历 = " + result.toString());
        return result;
    }

    /**
     * 后序遍历是 左-》右-》中 输出的，前序遍历的中-》左-》右，调整代码顺序 中-》右-》左，然后反转 result 数组
     * 中-》左-》右 入栈，中-》右-》左 出栈
     * @param root
     */
    public static List<Integer> postOrderTraversal(TreeNode root) {
        // 用来存储二叉树节点的值
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 前序遍历
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        // 循环遍历栈，根节点出栈之后，右节点入栈
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.peek();
            result.add(node.val); // 中
            // 出栈
            nodeStack.pop();
            // 空节点不需要入栈
            if (node.left != null) {
                nodeStack.push(node.left);    // 左
            }
            if (node.right != null) {
                nodeStack.push(node.right);    // 右
            }
        }
        ArrayList<Integer> result2 = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            result2.add(result.get(i));
        }
        System.out.println("后序遍历 = " + result2.toString());
        return result2;
    }
}
