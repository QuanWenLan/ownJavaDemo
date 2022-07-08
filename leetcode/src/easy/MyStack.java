package easy;

import java.util.LinkedList;

/**
 * @author Vin lan
 * @className MyStack
 * @description https://leetcode.cn/problems/implement-stack-using-queues/
 * 用队列实现栈 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * @createTime 2022-07-05  15:51
 **/
public class MyStack {
    /**
     * 输入队列
     */
    private LinkedList<Integer> listIn = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        listIn.offer(x);
    }

    public int pop() {
        Integer integer = listIn.pollLast();
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    public int top() {
        Integer integer = listIn.peekLast();
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    public boolean empty() {
        return listIn.isEmpty();
    }

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(2);
        int param2 = obj.pop();
        System.out.println(param2);
        int param3 = obj.top();
        System.out.println(param3);
        boolean param4 = obj.empty();
        System.out.println(param4);
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
