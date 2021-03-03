package zsc.LinkStack;

import zsc.MyStack;

public class MyLinkStack implements MyStack {

    private Node top;
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(Object e) {
        Node pNode = new Node(e,top);
        top = pNode;
        size++;
    }

    @Override
    public Object pop() {
        //先判断是否为空
        if (size<1){
            throw new StackOverflowError("栈以空");
        }
        Object oldData = top.data;
        top = top.next;
        size--;
        return oldData;
    }

    @Override
    public Object peek() {
        //先判断是否为空
        if (size<1){
            throw new StackOverflowError("栈以空");
        }
        return top.data;
    }

    private class Node{
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        //从栈顶到栈底的顺序添加各个元素
        for(Node node = top;node != null;node = node.next){
            sb.append(node.data);
            if (node!=null){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
