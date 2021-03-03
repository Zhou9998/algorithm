package zsc.SingleList;

import sun.plugin2.applet.context.NoopExecutionContext;
import zsc.MyList;
//通过单向链表实现线性表
public class MySingleLink implements MyList {
    private Node head;  //头结点
    private int size;   //保存元素的个数

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void insert(int i, Object e) {
        //判断索引值是否越界
        if (i<0 || i > size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        Node newNode = new Node(e,null);
        //头结点为null的情况,链表不存在,刚刚添加的节点就是头结点
        if (head == null){
            head = newNode;
        }else {
            //在0位置插入节点
            if (i==0){
                newNode.next = head;
                head = newNode;
            }else {
                //插入节点,先找到i-1这个节点
                Node pNode = head;
                for (int x = 1;x<i;x++){
                    pNode = pNode.next;
                }
                //先修改新节点的next指针域，在修改i-1节点的指针域
                newNode.next=pNode.next;
                pNode.next=newNode;
            }
        }
        //元素个数加1
        size++;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e)>=0;
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;  //保存元素e的索引值
        Node pNode = head;
        while (pNode != null){
            if (e==null&&pNode.data==null){
                return i;
            } else if (e!=null&&e.equals(pNode.data)){
                return i;
            }
            i++;
            pNode=pNode.next;
        }
        return -1;
    }

    @Override
    public Object remove(Object e) {
        //找到元素e第一次出现的索引值
        int index = indexOf(e);
        if (index<0){
            return null;
        }
        return remove(index);
    }

    @Override
    public Object remove(int i) {
        //判断是否越界
        if (i<0 || i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        Node pNode = head;
        if (i==0){
            head=head.next;
            size--;
            return pNode.data;
        }
        for (int x = 1;x<i;x++){
            pNode = pNode.next;
        }
        Object old = pNode.next.data;   //保存删除节点的数据
        pNode.next = pNode.next.next;   //修改i-1节点的指针域
        return old;
    }

    @Override
    public Object replace(int i, Object e) {
        checkIndex(i);
        Node pNode = getNode(i);
        Object old = pNode.data;
        pNode.data = e;
        return old;
    }

    @Override
    public Object get(int i) {
        checkIndex(i);
        Node pNode = getNode(i);
        return pNode.data;
    }
    //检查索引值是否越界
    private void checkIndex(int i){
        if (i<0 || i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
    }
    //返回i索引值的元素
    private Node getNode(int i){
        if (i<0||i>=size){
            return null;
        }
        if (i==0){
            return head;
        }
        Node pNode = head;
        for (int x = 1;x <= i;x++){
            pNode = pNode.next;
        }
        return pNode;
    }

    @Override
    public boolean insertBefore(Object p, Object e) {
        //找p的位置
        int index = indexOf(p);
        if (index<0){
            return false;
        }
        insert(index,e);
        return true;
    }

    @Override
    public boolean insertAfter(Object p, Object e) {
        //找p的位置
        int index = indexOf(p);
        if (index<0){
            return false;
        }
        insert(index+1,e);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node pNode = head;
        while (pNode!=null){
            sb.append(pNode.data);
            if (pNode.next!=null){
                sb.append(",");
            }
            pNode = pNode.next;
        }
        sb.append("]");
        return sb.toString();
    }

    //定义一个内部类表示单向链表中的节点
    private class Node{
        Object data;    //保存数据
        Node next;  //下个节点的引用

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
