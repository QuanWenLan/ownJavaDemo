package com.lanwq.thinkinginjavademo.fifteenthgenerics.exercise;

/**
 * @author Vin lan
 * @className LinkedStack
 * @description TODO
 * @createTime 2020-11-06  17:13
 **/
public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() {
            item = null;
            next = null;
        }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();  // end sentinel,末端哨兵

    public void push(T item) {
        top = new Node<T>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) top = top.next;
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (String s : "Phasers or stun!".split(" "))
            stack.push(s);
        String ss;
        while ((ss = stack.pop()) != null) {
            System.out.println(ss);
        }
    }
}
