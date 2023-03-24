package tags.tree;

import common.TreeNode;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className BuildTree2
 * @description 105 从前序与中序遍历序列构造二叉树
 * @createTime 2022-07-19  11:01
 **/
public class BuildTree2 {
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        BuildTree2 obj = new BuildTree2();
        TreeNode root = obj.buildTree(preorder, inorder);
        System.out.println(root.toString());
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int inOrderLen = inorder.length;
        int preOrderLen = preorder.length;
        // 这一个判断不能少，少了有问题
        if (preOrderLen == 0) {
            return null;
        }
        // 第一步：确定根节点
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        // 叶子节点
        if (preOrderLen == 1) {
            return root;
        }
        // 第二步：根据 前序遍历的根节点去切割 中序遍历的节点(inorder 和 inorder 都由 不同 的值组成)
        // 2.1 找中序遍历中的 index
        int cutPointIndex = 0;
        for (int i = 0; i < inOrderLen; i++) {
            if (inorder[i] == rootValue) {
                cutPointIndex = i;
                break;
            }
        }

        // 2.2 切割中序数组
        // 中序左数组
        int[] cutPointInOrderLeft = Arrays.copyOfRange(inorder, 0, cutPointIndex);
        // 中序右数组
        int[] cutPointInOrderRight = Arrays.copyOfRange(inorder, cutPointIndex + 1, inOrderLen);

        // 第三步：根据中序数组切割 前序数组

        // 前序数组的切割点怎么找？
        // 前序数组没有明确的切割元素来进行左右切割，不像中序数组有明确的切割点，切割点左右分开就可以了。此时有一个很重的点，就是中序数组大小一定是和后序数组的大小相同的（这是必然）
        // 删除用掉的第一个元素
        preorder = Arrays.copyOfRange(preorder, 1, preOrderLen);
        // 前序左数组 左闭右开
        int[] cutPointPreOrderLeft = Arrays.copyOfRange(preorder, 0, cutPointInOrderLeft.length);
        // 前序右数组 左闭右开
        int[] cutPointPreOrderRight = Arrays.copyOfRange(preorder, cutPointInOrderLeft.length, preorder.length);
        // 第六步
        root.left = buildTree(cutPointPreOrderLeft, cutPointInOrderLeft);
        root.right = buildTree(cutPointPreOrderRight, cutPointInOrderRight);
        return root;
    }
}
