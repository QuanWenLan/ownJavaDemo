package tags.tree;

import commom.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vin lan
 * @className SsSymmetricTree
 * @description 101. 对称二叉树
 * https://leetcode.cn/problems/symmetric-tree/
 * @createTime 2022-07-15  11:51
 **/
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = root.left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        TreeNode right = root.right = new TreeNode(2);
        right.left = new TreeNode(4);
        right.right = new TreeNode(3);

        SymmetricTree obj = new SymmetricTree();
        System.out.println(obj.isSymmetric(root));
    }
    /**
     * 要点：本题遍历只能是“后序遍历”，因为我们要通过递归函数的返回值来判断两个子树的内侧节点和外侧节点是否相等。
     *
     * 正是因为要遍历两棵树而且要比较内侧和外侧节点，所以准确的来说是一个树的遍历顺序是左右中，一个树的遍历顺序是右左中
     *                  1
     *                /   \
     *              2      2
     *             / \    / \
     *            3   4  4   3
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compareNode(root.left, root.right);
    }

    /**
     * 递归函数，1 确定入参和返回值
     * @return
     */
    public boolean compareNode(TreeNode left, TreeNode right) {
        // 2 确定终止条件
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }

        // 3 单层循环逻辑
        // 当为根节点的时候，也是有外侧和里侧之分
        // 比较外侧
        boolean outside = compareNode(left.left, right.right);
        boolean inside = compareNode(left.right, right.left);
        return outside && inside;
    }

    /**
     * 迭代法
     * 使用普通队列
     */
    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(root.left);
        deque.offer(root.right);
        while (!deque.isEmpty()) {
            TreeNode leftNode = deque.poll();
            TreeNode rightNode = deque.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
            // 以上三个判断条件合并
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            // 这里顺序与使用Deque不同
            deque.offer(leftNode.left);
            deque.offer(rightNode.right);
            deque.offer(leftNode.right);
            deque.offer(rightNode.left);
        }
        return true;
    }

    /**
     * 1 确定递归函数的参数和返回值
     * 因为我们要比较的是根节点的两个子树是否是相互翻转的，进而判断这个树是不是对称树，所以要比较的是两个树，参数自然也是左子树节点和右子树节点。
     * bool compare(TreeNode* left, TreeNode* right)
     *
     * 2 确定终止条件
     * 要比较两个节点数值相不相同，首先要把两个节点为空的情况弄清楚！否则后面比较数值的时候就会操作空指针了。
     *
     * 节点为空的情况有：（注意我们比较的其实不是左孩子和右孩子，所以如下我称之为左节点右节点）
     *
     * 左节点为空，右节点不为空，不对称，return false
     * 左不为空，右为空，不对称 return false
     * 左右都为空，对称，返回true
     * 此时已经排除掉了节点为空的情况，那么剩下的就是左右节点不为空：
     *
     * 左右都不为空，比较节点数值，不相同就return false
     * 此时左右节点不为空，且数值也不相同的情况我们也处理了。
     *
     * if (left == NULL && right != NULL) return false;
     * else if (left != NULL && right == NULL) return false;
     * else if (left == NULL && right == NULL) return true;
     * else if (left->val != right->val) return false; // 注意这里我没有使用else
     *
     * 3 确定单层递归的逻辑
     * 此时才进入单层递归的逻辑，单层递归的逻辑就是处理 左右节点都不为空，且数值相同的情况。
     * (1)比较二叉树外侧是否对称：传入的是左节点的左孩子，右节点的右孩子。
     * (2)比较内测是否对称，传入左节点的右孩子，右节点的左孩子。
     * (3)如果左右都对称就返回true ，有一侧不对称就返回false。
     * bool outside = compare(left->left, right->right);   // 左子树：左、 右子树：右
     * bool inside = compare(left->right, right->left);    // 左子树：右、 右子树：左
     * bool isSame = outside && inside;                    // 左子树：中、 右子树：中（逻辑处理）
     * return isSame;
     */
}
