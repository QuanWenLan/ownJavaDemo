package tags.array;

import java.util.HashMap;

/**
 * @program: javaDemo->LengthOfLongestSubstring
 * @description: 3 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * @author: lanwenquan
 * @date: 2021-11-16 21:54
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        LengthOfLongestSubstring obj = new LengthOfLongestSubstring();
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 什么是滑动窗口？
     *
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     * 作者：powcai
     * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 类似题目：
     * 3. 无重复字符的最长子串
     * 30. 串联所有单词的子串
     * 76. 最小覆盖子串
     * 159. 至多包含两个不同字符的最长子串
     * 209. 长度最小的子数组
     * 239. 滑动窗口最大值
     * 567. 字符串的排列
     * 632. 最小区间
     * 727. 最小窗口子序列
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0,right = 0, res = 0, len = s.length();
        HashMap<Character, Integer> window = new HashMap<>();

        while (right < len) {
            char c = s.charAt(right);
            if (window.containsKey(c)) {
                int value = window.get(c);
                value++;
                window.put(c, value);
            } else {
                window.put(c, 1);
            }
            right++;
            System.out.printf("left: %d, right: %d \n", left, right);
            System.out.println("window 修改前：" + window.toString());
//            这里不能用 if，是因为需要循环取完所有重复的元素才行，不然还是有重复的
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                int value = window.get(d);
                value--;
                window.put(d, value);
                System.out.println("window 修改后：" + window.toString());
                left++;
            }

            res = Math.max(res, right - left);
            System.out.println("res: " + res);
        }
        return res;
    }
}
