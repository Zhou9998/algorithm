package zsc.ArrayQueue;

import zsc.MyQueue;
import zsc.QueueEmptyException;

public class MyArrayQueue implements MyQueue {
    private Object[] elements;  //定义数组存储队列中的元素
    public static final int DEFAULT_CAPACITY = 8;
    private int front;  //队首
    private int rear;   //队尾
    private int size;   //元素的个数

    public MyArrayQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enQueue(Object e) {
        //如果队列已满，可以对数组扩容
        if (size>= elements.length){
            expandQueue();
        }
        elements[rear] = e;
        rear=(rear+1)% elements.length;
        size++;
    }

    private void expandQueue() {
        //定义一个更大的数组
        Object[] newElements = new Object[elements.length * 2];
        //把原来数组的内容复制到新的数组中,从队首开始的元素一次复制到新数组索引值0开始的位置
        for (int i = 0;i < size;i++){
            newElements[i] = elements[front];
            front = (front+1) % elements.length;
        }
        //把原来的数组名指向新的数组
        elements = newElements;
        //调整新的队首与队尾指针
        front = 0;
        rear = size;
    }

    @Override
    public Object deQueue() {
        //如果队列为空
        if (size<=0){
            throw new QueueEmptyException("队列已空");
        }
        //队列不为空，把front指向的元素返回
        Object old = elements[front];
        front = (front+1)% elements.length;
        size--;
        return old;
    }

    @Override
    public Object peek() {
        //如果队列为空
        if (size<=0){
            throw new QueueEmptyException("队列已空");
        }
        return elements[front];
    }
}
