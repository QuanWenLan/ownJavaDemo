package easy;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className PreOrderTraversal144
 * @description 二叉树的前序遍历
 * @createTime 2021-06-22  16:29
 **/
public class PreOrderTraversal144 {
    public static void main(String[] args) {

    }

    private ArrayList<Integer> result = new ArrayList<>();
    /**
     * 前序遍历
     * 根节点->左子树->右子树
     * @param root 根节点
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        if (root.left != null) {
            preorderTraversal(root.left);
        }
        if (root.right != null) {
            preorderTraversal(root.right);
        }
        return result;
    }

    /**
     * 中序遍历
     * 左子树->根节点->右子树
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return result;
    }
    /**
     * 后序遍历
     * 左子树->右子树->根节点
     * @param root 根节点
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            postorderTraversal(root.left);
        }
        if (root.right != null) {
            postorderTraversal(root.right);
        }
        result.add(root.val);
        return result;
    }
}
