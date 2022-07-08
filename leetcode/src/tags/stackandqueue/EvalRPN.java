package tags.stackandqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author Vin lan
 * @className EvalRPN
 * @description 150 逆波兰表达式求值
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 * @createTime 2022-07-08  14:18
 **/
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> dataStack = new Stack<>();
        for (String token : tokens) {
            if (operators.contains(token)) {
                Integer integer2 = dataStack.pop();
                Integer integer1 = dataStack.pop();
                int tempRes = 0;
                switch (token) {
                    case "+":
                        tempRes = integer1 + integer2;
                        break;
                    case "-":
                        tempRes = integer1 - integer2;
                        break;
                    case "*":
                        tempRes = integer1 * integer2;
                        break;
                    case "/":
                        tempRes = integer1 / integer2;
                        break;
                    default:
                        break;
                }
                dataStack.push(tempRes);
            } else {
                dataStack.push(Integer.parseInt(token));
            }
        }
        return dataStack.pop();
    }
}
