package easy;

/**
 * @author Vin lan
 * @className Reverse7
 * @description 翻转数字，https://leetcode-cn.com/problems/reverse-integer/
 * @createTime 2021-06-18  16:58
 **/
public class Reverse7 {
    public static void main(String[] args) {
        System.out.println(Long.parseLong("9646324351"));
        Reverse7 reverse7 = new Reverse7();
        System.out.println(reverse7.reverse(1534236469));
    }

    /**
     * 真暴力美学
     * 执行用时：
     * 9 ms, 在所有 Java 提交中击败了6.31%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了6.29% 的用户
     */
    public int reverse(int x) {
        if (x > (Math.pow(2, 31) - 1) || x < (Math.pow(2, 31) * -1) || x == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        String xStr = (x + "");
        if (x < 0) {
            xStr = xStr.substring(1);
        }
        int len = xStr.length();
        for (int i = xStr.length() - 1; i >= 0; i--) {
            if ((i == len - 1 || i == 0) && Integer.parseInt(String.valueOf(xStr.charAt(i))) == 0) {
            } else {
                sb.append(xStr.charAt(i));
            }
        }

        long result = x < 0 ? Long.parseLong(sb.toString()) * -1 : Long.parseLong(sb.toString());
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) result;
        }
    }
}
