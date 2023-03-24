package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className RestoreIpAddresses
 * @description 93 复原IP地址
 * @createTime 2022-08-16  14:36
 **/
public class RestoreIpAddresses {

    public static void main(String[] args) {
        RestoreIpAddresses obj = new RestoreIpAddresses();
        List<String> list = obj.restoreIpAddresses("25525511135");
        System.out.println(list);
    }

    /**
     * 存放结果集
     */
    private List<String> result;

    public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        if (s.length() > 15 || s.length() < 4) {
            return result;
        }
        backtracking(0, new StringBuilder(s), 0);
        return result;
    }

    /**
     回溯方法

     */
    /**
     * @param startIndex 开始点
     * @param s          字符串
     * @param pointNum   点的个数
     */
    public void backtracking(int startIndex, StringBuilder s, int pointNum) {
        // 终止条件
        if (pointNum == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                result.add(s.toString());
            }
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 切割一个数字，左闭右闭
            if (isValid(s, startIndex, i)) {
                // i+1 处插入一个点
                s.insert(i + 1, '.');
                // 执行下一次的切割，i+2开始，因为我们加了一个点
                pointNum++;
                backtracking(i + 2, s, pointNum);
                // 回溯
                pointNum--;
                // 删除点
                s.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    /**
     * 段位以0为开头的数字不合法
     * 段位里有非正整数字符不合法
     * 段位如果大于255了不合法
     *
     * @param str   字符串
     * @param start 开始 包括
     * @param end   结束 包括
     * @return 是否有效
     */
    public boolean isValid(StringBuilder str, int start, int end) {
        if (start > end) {
            return false;
        }
        // 为 0 的开头不合法
        if (str.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (; start <= end; start++) {
            if (str.charAt(start) > '9' || str.charAt(start) < '0') {
                return false;
            }
            // 段位大于255
            num = num * 10 + str.charAt(start) - '0';
            if (num > 255) {
                return false;
            }
        }
        return true;
    }
}
