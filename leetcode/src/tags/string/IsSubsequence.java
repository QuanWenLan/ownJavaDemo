package tags.string;

/**
 * @author Lan
 * @createTime 2024-02-21  15:23
 * 392. 判断子序列 https://leetcode.cn/problems/is-subsequence/description/
 **/
public class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence obj = new IsSubsequence();
        System.out.println(obj.isSubsequence("abc", "nahbgdc"));
//        System.out.println(obj.isSubsequence("axc", "ahbgdc"));
    }
    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * "abc" s
     * "vahbgdc" t，输出 true
     * "axc"
     * "ahbgdc"， 输出 false
     */
    // 可以使用双指针就行处理, s 是子序列
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        // fast 指向 t 的初始位置，slow 指向 s 的初始位置
        int fast = 0, slow = 0;
        while (fast < m && slow < n) {
            if (t.charAt(fast) == s.charAt(slow)) {
                slow++;
            }
            fast++;
        }
        // 最后如果slow移动到了末尾则说明s是t的子序列
        return n == slow;
    }
}
