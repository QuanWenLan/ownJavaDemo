package tags.tree;

import commom.TreeNode;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className ConstructMaximumBinaryTree
 * @description 654. 最大二叉树
 * https://leetcode.cn/problems/maximum-binary-tree/
 * @createTime 2022-07-19  11:51
 **/
public class ConstructMaximumBinaryTree {
    public static void main(String[] args) {
        ConstructMaximumBinaryTree obj = new ConstructMaximumBinaryTree();
        TreeNode treeNode = obj.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        System.out.println(treeNode.toString());
    }

    /**
     * nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     * - 空数组，无子节点。
     * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     * - 空数组，无子节点。
     * - 只有一个元素，所以子节点是一个值为 1 的节点。
     * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     * - 只有一个元素，所以子节点是一个值为 0 的节点。
     * - 空数组，无子节点。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 2 确定终止条件
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        // 3 确定单层循环
        // 3.1 找到根节点
        int rootIndex = 0;
        int rootValue = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > rootValue) {
                rootValue = nums[i];
                rootIndex = i;
            }
        }
        TreeNode root = new TreeNode(rootValue);

        // 3.2 分割数组
        int[] leftArr = Arrays.copyOfRange(nums, 0, rootIndex);
        int[] rightArr = Arrays.copyOfRange(nums, rootIndex + 1, nums.length);
        if (rootIndex > 0) {
            root.left = constructMaximumBinaryTree(leftArr);
        }
        if (rootIndex < (nums.length - 1)) {
            root.right = constructMaximumBinaryTree(rightArr);
        }

        return root;
    }

    /**
     * 优化
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return constructMaximumBinaryTree1(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {// 没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {// 只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;// 最大值所在位置
        int maxVal = nums[maxIndex];// 最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree1(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree1(nums, maxIndex + 1, rightIndex);
        return root;
    }

}
