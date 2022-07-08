package jizhioffer.learn.easy;

/**
 * @author Vin lan
 * @className ReverseLeftWords58_2
 * @description
 * @createTime 2022-07-05  12:09
 **/
public class ReverseLeftWords58_2 {
    public static void main(String[] args) {
        ReverseLeftWords58_2 obj = new ReverseLeftWords58_2();
        obj.reverseLeftWords("abcdefg", 2);
        obj.reverseLeftWords("lrloseumgh", 6);
    }

    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(s);
        reverseString(sb, 0, s.length() - 1);
        reverseString(sb, 0, len - 1 - n);
        reverseString(sb, len - n, len - 1);
        System.out.println("sb = " + sb);
        return sb.toString();
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }
}
//字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数
//将返回左旋转两位得到的结果"cdefgab"。
//
//
//
// 示例 1：
//
// 输入: s = "abcdefg", k = 2
//输出: "cdefgab"
//
//
// 示例 2：
//
// 输入: s = "lrloseumgh", k = 6
//输出: "umghlrlose"
//
//
//
//
// 限制：
//
//
// 1 <= k < s.length <= 10000
//
// Related Topics 数学 双指针 字符串
// 👍 270 👎 0
