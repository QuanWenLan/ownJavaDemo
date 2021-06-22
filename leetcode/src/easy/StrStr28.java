package easy;

/**
 * @author Vin lan
 * @className StrStr28
 * @description https://leetcode-cn.com/problems/implement-strstr/ 28题，实现strStr()
 * @createTime 2021-06-22  14:42
 **/
public class StrStr28 {
    public static void main(String[] args) {
        StrStr28 obj = new StrStr28();
        System.out.println(obj.strStr("hello", "lo"));
        System.out.println(obj.strStr("aaaaa", "bba"));
        System.out.println(obj.strStr("", ""));
        System.out.println(obj.strStr("a", "a"));
    }

    /**
     * 比暴力好点
     * 执行用时：1 ms, 在所有 Java 提交中击败了75.82%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了31.55%的用户
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int needleLen = needle.length(), haystackLen = haystack.length();
        for (int i = 0; i <= haystackLen - 1; i++) {
            // 每次比较的时候要有足够的长度来和另一个字符串比较，至少要有另一个字符串的长度的剩余才行
            if (haystackLen - i >= needleLen) {
                String s = haystack.substring(i, needleLen + i);
                if (s.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 使用 KMP（Knuth-Morris-Pratt） 算法, 复杂。。。
     * https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode-solution-ds6y/
     */
}
