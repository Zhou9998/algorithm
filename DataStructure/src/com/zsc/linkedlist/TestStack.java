package com.zsc.linkedlist;

import java.util.Stack;

/**
 * 栈stack的基本使用
 *
 * @author zsc
 * @date 2021/4/14 10:26
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("tom");
        stack.add("tom1");
        stack.add("tom2");
        //出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
