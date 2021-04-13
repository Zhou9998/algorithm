package com.zsc.queue;

import java.util.Scanner;

/**
 * 环形队列
 *
 * @author zsc
 * @date 2021/4/13 12:00
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //    创建一个队列
        CircleArray queue = new CircleArray(4);
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
                    queue.showQueue();
                    break;
                case 'a'://添加数据到队列
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://从队列取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是"+res);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 'h'://查看队列头数据
                    try {
                        int head = queue.headQueue();
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

class CircleArray {
    private int maxSize; //表示数组的最大容量
    private int front;  //队列头部
    private int rear;   //队列尾部
    private int[] arr;  //该数据用于存放数据，模拟队列

    public CircleArray(int arrMaxsize) {
        maxSize = arrMaxsize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //   判断队列是或否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须取模
        rear = (rear + 1) % maxSize;
    }

    //  获取队列的数据，数据出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        //这列需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        int value = arr[front];
        //2.将front后移,考虑取模
        front = (front + 1) % maxSize;
        //3.将临时保存的变量返回
        return value;
    }

    //  显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        //思路:从front开始遍历，遍历(rear + maxSize - front) % maxSize个元素
        for (int i = front; i < front + size(); i++) {
            System.out.println("arr[" + i % maxSize + "]=" + arr[i % maxSize]);
        }
    }

    //  求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //   显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }
}

