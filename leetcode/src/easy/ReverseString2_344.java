package easy;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className ReverseString2_344
 * @description https://leetcode.cn/problems/reverse-string-ii/
 * 反转字符串 2
 * @createTime 2022-07-04  11:16
 **/
public class ReverseString2_344 {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefghi", 3));
    }

    public static String reverseStr(String s, int k) {
        if (s.length() == 0) {
            return  "";
        }

        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length;  i += (2 * k)) {
            int start = i;
            // 这里是判断尾数够不够k个来取决end指针的位置
            int end = Math.min(charArray.length - 1, start + k - 1);
            for (; start < end; ) {
                char temp = charArray[start];
                charArray[start] = charArray[end];
                charArray[end] = temp;

                start++;
                end--;
            }
        }
        return new String(charArray);
    }
}

//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
//
//
// 如果剩余字符少于 k 个，则将剩余字符全部反转。
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
//
//
//
//
// 示例 1：
//
//
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
//
//
// 示例 2：
//
//
//输入：s = "abcd", k = 2
//输出："bacd"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由小写英文组成
// 1 <= k <= 104
//
// Related Topics 双指针 字符串
// 👍 321 👎 0
