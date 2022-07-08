package tags.string;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className ReverseString344
 * @description 344 反转字符串
 * https://leetcode.cn/problems/reverse-string/
 * @createTime 2022-07-04  11:04
 **/
public class ReverseString {
    public static void main(String[] args) {
        reverseString(new char[]{'h', 'e', 'l', 'l', 'o', 'a'});
    }

    // 使用双指针法
    public static void reverseString(char[] s) {
        if (s.length <= 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        for (; left < right; ) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
        System.out.println("s = " + Arrays.toString(s));
    }
}
//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
//
//
//
// 示例 1：
//
//
//输入：s = ["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
//
//
// 示例 2：
//
//
//输入：s = ["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"]
//
//
//
// 提示：
//
//
// 1 <= s.length <= 105
// s[i] 都是 ASCII 码表中的可打印字符
//
// Related Topics 递归 双指针 字符串
// 👍 614 👎 0

