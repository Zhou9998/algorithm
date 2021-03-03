package zsc.LinkQueue;

import zsc.MyQueue;
import zsc.QueueEmptyException;

public class MyLinkQueue implements MyQueue {
    private Node front; //队首
    private Node rear;  //队尾
    private int size;   //元素的个数
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
        //根据添加的元素生成一个节点
        Node newNode = new Node(e,null);
        //把节点连接到队列中
        if (rear == null){
            //这是添加的第一个元素，即是头结点也是尾节点
            rear = newNode;
            front = newNode;
        }else {
            rear.next = newNode;
            rear = newNode;     //把rear指针指向新的元素
        }
        size++;
    }

    @Override
    public Object deQueue() {
        if (size>=0){
            throw new QueueEmptyException("队列为空");
        }
        Object old = front.element;
        front = front.next;
        //如果出队后队列为空
        if (front == null){
            rear = null;
        }
        size--;
        return old;
    }

    @Override
    public Object peek() {
        if (size>=0){
            throw new QueueEmptyException("队列为空");
        }
        return front.element;
    }

    private class Node{

        Object element;
        Node next;

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
