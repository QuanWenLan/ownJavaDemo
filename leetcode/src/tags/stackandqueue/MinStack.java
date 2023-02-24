package tags.stackandqueue;

import java.util.Stack;

/**
 * @program: javaDemo->MinStack
 * @description: 实现一个最小栈，带有出栈、入栈、取最小元素getMin方法，且时间复杂度是 O(1)
 * @author: lanwenquan
 * @date: 2023-02-01 22:58
 */
public class MinStack {
    public static void main(String[] args) throws Exception {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();


    public void push(Integer value) {
        mainStack.push(value);
        if (minStack.empty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public Integer pop() {
        //如果出栈元素和辅助栈栈顶元素值相等， 辅助栈出栈
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    public int getMin() throws Exception {
        if (mainStack.empty()) {
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }

}
