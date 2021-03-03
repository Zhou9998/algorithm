package zsc.ArrayQueue;

public class MyArrayQueueTest {
    public static void main(String[] args) {
        MyArrayQueue queue = new MyArrayQueue();
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        System.out.println(queue.peek());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
