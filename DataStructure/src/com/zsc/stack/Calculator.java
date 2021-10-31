package com.zsc.stack;

/**
 * 计算器
 *
 * @author zsc
 * @date 2021/4/15 14:04
 */
public class Calculator {
    public static void main(String[] args) {
        //完成表达式运算
        String expression = "7*1*3-8/2+4";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;  //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";    //用于拼接多位数
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做响应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果为数字，则直接入栈
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数
                //2.在处理数，需要向expression的表达式index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.需要定义一个字符串变量，用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch已经是express的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index+1,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数[结果]
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //数栈的最后数，pop出，就是结果
        System.out.println("表达式" + expression + "=" + numStack.pop());
    }
}

//创建一个栈
//需要拓展功能
class ArrayStack2 {
    private int maxSize;    //栈的大小
    private int[] stack;    //数组
    private int top = -1;   //表示栈顶，初始化为-1


    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈顶元素
    public int peek() {
        return stack[top];
    }

    //显示栈的情况[遍历栈],遍历时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;  //假定目前的表达式只有+.-.*./
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '/' || val == '*';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;

        }
        return res;
    }
}
