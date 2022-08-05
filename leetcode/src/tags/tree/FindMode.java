package tags.tree;

import common.TreeNode;

import java.util.ArrayList;

/**
 * @author Vin lan
 * @className FindMode
 * @description 501. 二叉搜索树中的众数
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/
 * @createTime 2022-08-01  17:38
 **/
public class FindMode {
    /**
     * 前一个节点
     */
    private TreeNode pre = null;
    /**
     * 出现频率
     */
    private int count = 0;
    /**
     * 频率最高的计数
     */
    private int maxCount = 0;
    private ArrayList<Integer> resList = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        searchBST(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    /**
     * 我们需要使用中序遍历来处理二叉搜索树，记住，它是有序的。同时需要使用 pre 指针来记录当前节点的上一个遍历的节点
     */
    void searchBST(TreeNode cur) {
        // 递归结束条件
        if (cur == null) {
            return;
        }
        // 左
        searchBST(cur.left);
        // 中间处理逻辑

        // 如何统计出现的次数？
        if (pre == null) {
            // 为空，第一次出现的节点
            count = 1;
        } else if (pre.val == cur.val) {
            // 前一个节点和当前节点一样
            count++;
        } else { // 两个节点不相同
            count = 1;
        }

        // 如何知道是频率最高的节点？频率count 大于 maxCount的时候，不仅要更新maxCount，而且要清空结果集（以下代码为result数组），因为结果集之前的元素都失效了。
        if (count > maxCount) {
            resList.clear();
            resList.add(cur.val);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(cur.val);
        }
        // 可以思考只有一层的时候，pre 节点如何赋值：遍历到最左边的节点的时候，再进行下一次遍历触发了递归结束条件，返回，此时cur节点是最左边节点
        // 这个时候 pre 即为前一个节点（对于下一个要遍历的节点来说）
        /**
         * {@link GetMinimumDifference} 二叉搜索树的最小绝对差，这个就是使用 前一个节点的范例
         */
        pre = cur;
        // 右
        searchBST(cur.right);
    }
}
