package tags.stackandqueue;

import java.util.Stack;

/**
 * @author Vin lan
 * @className RemoveAdjacentDuplicates
 * @description  1047 删除字符串中的所有相邻重复项
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
 * @createTime 2022-07-08  14:17
 **/
public class RemoveAdjacentDuplicates {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
                continue;
            }
            Character peek = stack.peek();
            if (peek == chars[i]) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        if (stack.isEmpty()) {
            return "";
        } else {
            StringBuilder res = new StringBuilder();
            while (!stack.isEmpty()) {
                res.append(stack.pop());
            }
            return res.reverse().toString();
        }
    }
}

