package easy;

/**
 * @author Vin lan
 * @className IsPalindrome
 * @description 回文数  https://leetcode-cn.com/problems/palindrome-number/
 * @createTime 2021-06-22  14:08
 **/
public class IsPalindrome9 {
    public static void main(String[] args) {
        IsPalindrome9 obj = new IsPalindrome9();
        System.out.println(obj.isPalindrome(101));
        System.out.println(obj.isPalindrome(12322));
    }


    public boolean isPalindrome(int x) {
        if (x < 0 || x >= Integer.MAX_VALUE) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int initX = x;
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            // 每次求出个位数
            int reverseNum = x % 10;
            x = x / 10;
            sb.append(reverseNum);
        }
        System.out.println(sb.toString());
        if ((initX + "").equalsIgnoreCase(sb.toString())) {
            return true;
        }
        return false;
    }

    // 简单粗暴，看看就行
    public boolean isPalindrome2(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

}
