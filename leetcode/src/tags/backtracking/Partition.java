package tags.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className Partition
 * @description 131. 分割回文串
 * https://leetcode.cn/problems/palindrome-partitioning/
 * @createTime 2022-08-11  10:37
 **/
public class Partition {
    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * <p>
     * 回文串 是正着读和反着读都一样的字符串
     * 示例：
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     * 提示：
     * 1 <= s.length <= 16
     * s 仅由小写英文字母组成
     */

    public static void main(String[] args) {
        Partition obj = new Partition();
        List<List<String>> list = obj.partition("aab");
        System.out.println(list);
    }

    public List<List<String>> partition(String s) {
        backtracking(0, s);
        return result;
    }

    /**
     * 一条路径的元素
     */
    private LinkedList<String> path = new LinkedList<>();
    /**
     * 所有结果
     */
    private List<List<String>> result = new ArrayList<List<String>>();

    /**
     * 回溯方法
     * startIndex: 切割位置，从0开始
     * s: 需要切割的字符串
     */
    public void backtracking(int startIndex, String s) {
        // 2 确定终止条件，切割完成
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 单层逻辑
        for (int i = startIndex; i < s.length(); i++) {
            // 切割的字符串
            String cutStr = s.substring(startIndex, i + 1);
            // 判断是不是回文串
            if (isPalindrome(cutStr)) {
                path.add(cutStr);
            } else {
                continue;
            }
            backtracking(i + 1, s);
            path.removeLast();
        }
    }

    /**
     * 判断回文字符串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
