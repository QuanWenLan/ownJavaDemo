package tags.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className IsValidBST
 * @description 98. 验证二叉搜索树
 * @createTime 2023-03-20  17:05
 **/
public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    // 中序遍历是一个递增的序列
    public void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            inOrderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inOrderTraversal(root.right, result);
        }
    }
}
