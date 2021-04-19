package com.zsc.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 *
 * @author zsc
 * @date 2021/4/15 19:08
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将中缀表达式转为后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        //2. 因为直接对str进行操作不方便，因此将1+((2+3)*4)-5转换成以中缀形式保存的list
        //3. 将得到的中缀表达式转换位后缀表达式
        String expression = "1+((2+3)*4)-5" ;
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);    //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);   //[1, 2, 3, +, 4, *, +, 5, -]
        int i = calculate(suffixExpressionList);
        System.out.println(i);

        /*
        //先定义给逆波兰表达式
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -" ;
        //1.将"3 4 + 5 × 6 – " 放到ArrayList中
        //2.将ArrayList传给一个方法，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println(res);
        */
    }


    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义栈
        Stack<String> s1 = new Stack<>();    //符号栈
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item : ls) {
            //如果是数字，就加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();   //将"("弹出
            } else {
                //当item的优先级小于等于栈顶运算符的优先级，将
                while (s1.size() != 0 && (Operation.getValue(item) <= Operation.getValue(s1.peek()))) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    /**
     * 将中缀表达式转换成对应的list
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;  //用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c;
        do {

            //如果c是一个非数字，需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;    //i后移
            } else { //如果是一个数，需要考虑多位数

                str = "" ;   //先将str制成"";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) < 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }


    //将逆波兰表达式的数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
        1)从左至右扫描，将3和4压入堆栈；
        2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
        3)将5入栈；
        4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
        5)将6入栈；
        6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    */
    public static int calculate(List<String> ls) {
        //创建一个栈，只需要一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                stack.push("" + res);
            }
        }
        //最后留在栈中的就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}