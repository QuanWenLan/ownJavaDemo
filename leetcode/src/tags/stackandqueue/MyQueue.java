package tags.stackandqueue;

import java.util.Stack;

/**
 * @author Vin lan
 * @className MyQueue
 * @description 232 用栈实现队列
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 * @createTime 2022-07-05  15:28
 **/
public class MyQueue {
    /**
     *     输入栈
      */
    private Stack<Integer> stackIn = new Stack<>();

    /**
     * 输出栈
     */
    private Stack<Integer> stackOut = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        stackIn.push(x);
    }

    /**
     * 获取后删除
     */
    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    /**
     * 获取后不删除
     */
    public int peek() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
        System.out.println(queue.empty());
        System.out.println(queue.peek());
        System.out.println(queue.empty());

    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
