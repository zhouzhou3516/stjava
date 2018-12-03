package com.zhou.mjava.leetcode;

import java.util.Stack;

/**
 * 两个栈实现队列
 * @author liqingzhou on 18/9/20
 */
public class QueueByTwoStack {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public static void main(String[] args) {
        QueueByTwoStack queueByTwoStack = new QueueByTwoStack();
        queueByTwoStack.push(1);
        queueByTwoStack.push(2);
        queueByTwoStack.push(3);
        System.out.println(queueByTwoStack.pop());
        queueByTwoStack.push(4);
        System.out.println(queueByTwoStack.pop());
        queueByTwoStack.push(5);
        System.out.println(queueByTwoStack.pop());
        System.out.println(queueByTwoStack.pop());
        System.out.println(queueByTwoStack.pop());


    }

    public void push(int element) {
        if (!s2.isEmpty()) {
            reverse(s2, s1);
        }
        s1.push(element);
    }

    public int pop() {
        if (s2.isEmpty()) {
            reverse(s1, s2);
        }
        return s2.pop();
    }

    public void reverse(Stack notEmpty, Stack empty) {
        while (!notEmpty.isEmpty()) {
            empty.push(notEmpty.pop());
        }

    }

}
