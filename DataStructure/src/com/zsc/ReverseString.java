package com.zsc;

import java.util.Stack;

/**
 * @author zsc
 * @date 2021/4/17 12:28
 */
public class ReverseString {


    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        String s = " abc      c   a    sjs  hjsahd" ;
        System.out.println(reverseString.reverse(s));
    }

    private String reverse(String s) {
        int i = 0;
        String res = "" ;
        Stack stack = new Stack();
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                while (stack.size() != 0) {
                    res += stack.pop();
                    if (stack.size() == 0) {
                        res += " " ;
                    }
                }
            } else {
                stack.push(s.charAt(i));
            }
            if (i == s.length() - 1) {
                while (stack.size() != 0) {
                    res += stack.pop();
                }
            }
            i++;
        }
        return res;
    }
}
