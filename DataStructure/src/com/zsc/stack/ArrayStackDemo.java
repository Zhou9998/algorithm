package com.zsc.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 *
 * @author zsc
 * @date 2021/4/15 10:48
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        Scanner input = new Scanner(System.in);
        boolean flag =  true;
        while (flag){
            System.out.println("-------M E N U -------");
            System.out.println("请选择您的操作：");
            System.out.println("push:\t数据入栈");
            System.out.println("pop:\t数据出栈");
            System.out.println("show:\t打印栈数据");
            System.out.println("exit:\t退出程序");
            System.out.println("-------M E N U -------");
            String select = input.nextLine();
            switch (select){
                case "push":	// 入栈
                    System.out.println("请输入要入栈的元素：");
                    int num = Integer.parseInt(input.nextLine());
                    stack.push(num);
                    // 就是为了让程序停一下
                    System.out.print("按下任意键继续：");
                    input.nextLine();
                    break;
                case "pop":		// 出栈
                    try{
                        int pop = stack.pop();
                        System.out.println("出栈的元素为：" + pop);
                    } catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    // 就是为了让程序停一下
                    System.out.print("按下任意键继续：");
                    input.nextLine();
                    break;
                case "show":	// 打印数据
                    stack.list();
                    // 程序停一下
                    System.out.print("按下任意键继续：");
                    input.nextLine();
                    break;
                case "exit":	// 退出程序
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    private int maxSize;    //栈的大小
    private int[] stack;    //数组
    private int top = -1;   //表示栈顶，初始化为-1


    public ArrayStack(int maxSize) {
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
}
