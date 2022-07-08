package tags.stackandqueue;

import java.util.*;

/**
 * @author Vin lan
 * @className IsValid
 * @description https://leetcode-cn.com/problems/valid-parentheses/ 20, 有效的括号
 * @createTime 2021-06-23  15:41
 **/
public class IsValid {
    public static void main(String[] args) {
        IsValid obj = new IsValid();
        System.out.println(obj.isValid("()"));
        System.out.println(obj.isValid("()[]{}"));
        System.out.println(obj.isValid("(["));
        System.out.println(obj.isValid("(]"));
        System.out.println(obj.isValid("([)]"));
        System.out.println(obj.isValid("{[]}"));
        System.out.println(obj.isValid("(){}}{"));
    }

    /**
     * 这么裂么
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 6.93% 的用户
     * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 7.81% 的用户
     * 思路：先出现的左括号后匹配（先进后出用栈）
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        /*Stack<String> stack = new Stack<>();
        String top = "";
        char[] cs = s.toCharArray();
        for (int i = 0; i <= cs.length - 1; i++) {
            if("(".equals(String.valueOf(cs[i])) || "[".equals(String.valueOf(cs[i])) || "{".equals(String.valueOf(cs[i]))) {
                stack.push(String.valueOf(cs[i]));
                top = String.valueOf(cs[i]);
            } else if (")".equals(String.valueOf(cs[i])) || "]".equals(String.valueOf(cs[i])) || "}".equals(String.valueOf(cs[i]))) {
                if ("()".equals(top + cs[i]) || "[]".equals(top + cs[i]) || "{}".equals(top + cs[i])) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            top = stack.peek();
                        } else {
                            top = "";
                        }
                    }
                } else {
                    stack.push(String.valueOf(cs[i]));
                    top = String.valueOf(cs[i]);
                }
            }
        }
        return stack.empty();*/

        /**
         * 执行用时：
         * 2 ms, 在所有 Java 提交中击败了79.11%的用户
         * 内存消耗：36.7 MB, 在所有 Java 提交中击败了37.11%的用户
         **/
        // 别人的版本
        if (s.length() % 2 == 0) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (c == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == '}') {
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
        return false;
    }

    /**
     * 官方解法
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

}
