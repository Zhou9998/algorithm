package zsc;

public interface MyQueue {
    int getSize();  //返回元素的个数
    boolean isEmpty();  //判断是否为空
    void enQueue(Object e);
    Object deQueue();
    Object peek();  //返回队首元素
}
