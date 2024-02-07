package tags.tree;

/**
 * @author Lan
 * @createTime 2024-02-05  17:11
 * LCR 152. 验证二叉搜索树的后序遍历序列： https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * JZ33 二叉搜索树的后序遍历序列：https://www.nowcoder.com/share/jump/5391693681707124373489
 **/
public class VerifySequenceBST {
    public static void main(String[] args) {
        int [] sequence = new int [] {5,7,6,9,11,10,8};
        VerifySequenceBST verifySequenceBST = new VerifySequenceBST();
        System.out.println(verifySequenceBST.VerifySequenceOfBST(sequence));
    }
    /**
     * 思路
     * 1 二叉树的后序遍历顺序是：左子树 -> 右子树 -> 根节点
     * 3 因此序列的最后一个数代表了根节点
     * 3 因此我们可以将一个序列划分为3段, 左子树+右子树+根, 例如[4, 8, 6, 12, 16, 14, 10]可以根据根节点的值将其划分为左子树[4, 8, 6],
     * 右子树[12, 16, 14], 根[10], 由于我们是先确定的右子树区间, 因此当左子树区间中出现大于根节点的值时, 序列不合法, 我们再采用分治的思想, 对于每段序列代表的子树, 检查它的左子树和右子树, 当且仅当左右子树都合法时返回true
     */
    public boolean VerifySequenceOfBST(int [] sequence) {
        int len = sequence.length;
        if (len == 0) {// 有点不同的是，leetcode 空数组是返回true
            return false;
        }
        return check(sequence, 0, len - 1);
    }

    public boolean check(int[] sequence, int left, int right) {
        if (left >= right) { // 当前子树只有一个节点
            return true;
        }
        int rootVal = sequence[right];
        // 划分右子树
        int rightSeparator = right - 1;
        while (rightSeparator >= 0 && sequence[rightSeparator] > rootVal) {
            rightSeparator--;
        }

        // 检查左子树是不是存在大于根节点的数
        for(int i = left; i <= rightSeparator; i++) {
            if(sequence[i] > rootVal) return false;
        }

        return check(sequence, left, rightSeparator) && check(sequence, rightSeparator + 1, right - 1);
    }
}
