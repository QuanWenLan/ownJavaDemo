package tags.array;

import java.util.LinkedHashMap;

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
        System.out.println(obj.lengthOfLongestSubstring("pwwkewab"));
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, res = 0, len = s.length();
        LinkedHashMap<Character, Integer> window = new LinkedHashMap<>();
        String maxLenStr = "";

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
            int sub = right - left;
            if (sub > res) {
                maxLenStr = s.substring(left, right);
                System.out.println("最长的字符串为：" + maxLenStr);
            }
            res = Math.max(res, right - left);
            System.out.println("res: " + res);
        }
        return res;
    }
}
