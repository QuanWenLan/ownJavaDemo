package tags.string;

/**
 * @author Vin lan
 * @className ReplaceSpace05
 * @description 05 替换空格
 * @createTime 2022-07-04  12:12
 **/
public class ReplaceSpace {
    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }

    // char 数组从后往前遍历替换
    public static String replaceSpace(String s) {
        if (s.length() == 0) {
            return "";
        }
        //统计空格数量
        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }

        char[] chars = s.toCharArray();
        char[] newChars = new char[s.length() + count * 2];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                newChars[index] = chars[i];
            } else {
                newChars[index] = '%';
                newChars[index + 1] = '2';
                newChars[index + 2] = '0';
                index += 2;
            }
            index++;
        }
        return new String(newChars);
    }
}

//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
//
//
// 示例 1：
//
// 输入：s = "We are happy."
//输出："We%20are%20happy."
//
//
//
// 限制：
//
// 0 <= s 的长度 <= 10000
// Related Topics 字符串
// 👍 307 👎 0
