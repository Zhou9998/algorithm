package com.zsc.queue;

import java.util.Scanner;

/**
 * 顺序队列
 *
 * @author zsc
 * @date 2021/4/13 9:48
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //    测试一把
        //    创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接受用户收入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //    输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key){
                case 's'://显示队列
                    arrayQueue.showQueue();
                    break;
                case 'a'://添加数据到队列
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://从队列取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是"+res);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'h'://查看队列头数据
                    try {
                        int head = arrayQueue.headQueue();
                        System.out.println("队列头数据是"+head);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列-编写一个ArrayQueue类

class ArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front;  //队列头
    private int rear;   //队列尾部
    private int[] arr;  //该数据用于存放数据，模拟队列

    //  创建队列的一个构造器
    public ArrayQueue(int arrMaxsize) {
        maxSize = arrMaxsize;
        arr = new int[maxSize];
        front = -1;  //指向队列头部，front是指向队列头的前一个位置
        rear = -1;  //指向队列尾部，即数列最后一个数据
    }

    //   判断队列是或否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //   判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //  添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[++rear] = n;
    }

    //  获取队列的数据，数据出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;    //front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "]=" + arr[i]);
        }
    }

    //   显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front + 1];
    }

}
