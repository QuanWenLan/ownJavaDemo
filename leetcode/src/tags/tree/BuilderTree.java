package tags.tree;

import common.TreeNode;

import java.util.Arrays;

/**
 * @program: javaDemo->BuilderTree
 * @description: 106. 从中序与后序遍历序列构造二叉树
 * @author: lanwenquan
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @date: 2022-07-18 21:52
 */
public class BuilderTree {

    public static void main(String[] args) {
        BuilderTree obj = new BuilderTree();
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode treeNode = obj.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    /**
     * 就是以 后序数组的最后一个元素为切割点，先切中序数组，根据中序数组，反过来在切后序数组。一层一层切下去，每次后序数组最后一个元素就是节点元素。
     * 后序数组是：左-右-中，中序数组是：左-中-右
     *          3
     *      9       20
     *            15   7
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inOrderLen = inorder.length;
        int postOrderLen = postorder.length;
        // 这一个判断不能少，少了有问题
        if (postOrderLen == 0) {
            return null;
        }
        // 第一步：确定根节点
        int rootValue = postorder[postOrderLen - 1];
        TreeNode root = new TreeNode(rootValue);
        // 叶子节点
        if (postOrderLen == 1) {
            return root;
        }
        // 第二步：根据 后续遍历的根节点去切割 中序遍历的节点(inorder 和 postorder 都由 不同 的值组成)
        // 2.1 找中序遍历中的 index
        int cutPointIndex = 0;
        for (int i = 0; i < inOrderLen; i++) {
            if (inorder[i] == rootValue) {
                cutPointIndex = i;
                break;
            }
        }

        // 2.2 切割中序数组
//        int[] cutPointInOrderLeft = new int[cutPointIndex]; // 中序左数组
        int[] cutPointInOrderLeft = Arrays.copyOfRange(inorder, 0, cutPointIndex);
        /*int[] cutPointInOrderRight = new int[inOrderLen - cutPointIndex]; // 中序右数组
        for (int i = 0, j = 0; i < inOrderLen; i++) {
            // 这里要不等于 左闭右开 [)
            if (i < cutPointIndex) {
                cutPointInOrderLeft[i] = inorder[i];
            }
            // 这里要排除 根节点
            if (i > cutPointIndex) {
                cutPointInOrderRight[j] = inorder[i];
                j++;
            }
        }*/
        int[] cutPointInOrderRight =  Arrays.copyOfRange(inorder, cutPointIndex + 1, inOrderLen);

        // 第三步：根据中序数组切割 后序数组

        // 后序数组的切割点怎么找？
        // 后序数组没有明确的切割元素来进行左右切割，不像中序数组有明确的切割点，切割点左右分开就可以了。此时有一个很重的点，就是中序数组大小一定是和后序数组的大小相同的（这是必然）
        // 删除用掉的最后一个元素
        /*int[] postorder2 = new int[postOrderLen - 1];
        for (int i = 0; i < postOrderLen - 1; i++) {
            postorder2[i] = postorder[i];
        }
        postorder = postorder2;*/
        postorder =  Arrays.copyOfRange(postorder, 0, inOrderLen - 1);
        // 后序左数组  左闭右开
        /*int[] cutPointPostOrderLeft = new int[cutPointInOrderLeft.length];
        for (int i = 0; i < cutPointInOrderLeft.length; i++) {
            cutPointPostOrderLeft[i] = postorder[i];
        }*/
        int[] cutPointPostOrderLeft = Arrays.copyOfRange(postorder, 0, cutPointInOrderLeft.length);
        // 后序右数组 左闭右开
        /*int[] cutPointPostOrderRight = new int[postorder.length - cutPointInOrderLeft.length];
        for (int i = cutPointInOrderLeft.length, j = 0; i < postorder.length; i++) {
            cutPointPostOrderRight[j++] = postorder[i];
        }*/
        int[] cutPointPostOrderRight = Arrays.copyOfRange(postorder, cutPointInOrderLeft.length, postorder.length);
        // 第六步
        root.left = buildTree(cutPointInOrderLeft, cutPointPostOrderLeft);
        root.right = buildTree(cutPointInOrderRight, cutPointPostOrderRight);
        return root;
    }
}
