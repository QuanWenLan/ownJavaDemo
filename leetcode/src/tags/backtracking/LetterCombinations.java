package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className LetterCombinations
 * @description 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * @createTime 2022-08-09  10:29
 **/
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations obj = new LetterCombinations();
        List<String> list = obj.letterCombinations("23");
        System.out.println(list);
    }
    /**
     * 设置全局列表存储最后的结果
     */
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return list;

    }

    /**
     *
     每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
     */
    StringBuilder temp = new StringBuilder();

    /**
     * 比如digits如果为"23",num 为0，则str表示2对应的 abc
     * @param digits 包含数字2-9的字符串
     * @param numString 对应的映射
     * @param num 从哪个开始
     * 因为本题每一个数字代表的是不同集合，也就是求不同集合之间的组合，而回溯算法：求组合问题！https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html
     * 和回溯算法：求组合总和！ https://programmercarl.com/0216.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CIII.html 都是是求同一个集合中的组合！
     */
    public void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
