package zsc.DualLinkedList;

import zsc.MyList;

public class MyDualLinkedList implements MyList {

    private Node first;
    private Node last;
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
    public void insert(int i, Object e) {
        //1)检查索引值是否越界
        if (i < 0 || i > size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        //2)如果i==0，在头部添加元素
        if (i==0){
            addFirst(e);
        }else if (i==size){
            //3)如果i==size，在尾部添加元素
            addLast(e);
        }else {
            //4)找到i节点，在i节点的前面插入元素
            Node pNode = getNode(i);
            Node prevNode = pNode.prev;
            Node newNode = new Node(e,prevNode,pNode);
            prevNode.next = newNode;
            pNode.prev=newNode;
            size++;
        }
    }

    private Node getNode(int i) {
        Node pNode =first;
        for (int x=0;x<i;x++){
            pNode = pNode.next;
        }
        return pNode;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;  //保存元素e在链表中的索引值
        //依次遍历链表中的各个节点，比较节点的元素与指定的e是否一样
        if (e==null){
            for (Node pNode = first;pNode!=null;pNode=pNode.next){
                if (pNode.data==null){
                    return i;
                }
                i++;
            }
        }else {
            for (Node pNode = first;pNode!=null;pNode=pNode.next){
                if (e.equals(pNode.data)){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public Object remove(Object e) {
        //找到元素e对应的索引值
        int index = indexOf(e);
        if (index<0){
            return null;
        }
        return remove(index);
    }

    @Override
    public Object remove(int i) {
        if (i<0||i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        Node pNode = getNode(i);
        Node prevNode = pNode.prev;
        Node nextNode = pNode.next;
        if (prevNode == null){
            first = nextNode;
        }else {
            prevNode.next = nextNode;
        }
        if (nextNode == null){
            last = prevNode;
        }else {
            nextNode.prev = prevNode;
        }

        size--;
        return pNode.data;
    }

    @Override
    public Object replace(int i, Object e) {
        if (i<0||i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        Node pNode = getNode(i);
        Object oldData = pNode.data;
        pNode.data = e;
        return oldData;
    }

    @Override
    public Object get(int i) {
        if (i<0||i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        return getNode(i).data;
    }

    @Override
    public boolean insertBefore(Object p, Object e) {
        int index = indexOf(p);
        if (index < 0){
            return false;
        }
        insert(index,e);
        return true;
    }

    @Override
    public boolean insertAfter(Object p, Object e) {
        int index = indexOf(p);
        if (index < 0){
            return false;
        }
        insert(index+1,e);
        return true;
    }

    //定义一个内部类来描述双向链表的节点
    private class Node{
        Object data;
        Node prev;
        Node next;
        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    //在链表中经常会有针对头结点与尾节点的元素
    public void addLast(Object e) {
        Node pNode = last;
        //生成一个新节点
        Node newNode = new Node(e,last,null);
        last = newNode;  //修改first指向新的节点
        if (pNode==null){
            first = newNode;
        }else {
            pNode.next = newNode;
        }
        size++;
    }

    public void addFirst(Object e) {
        Node pNode = first;
        //生成一个新节点
        Node newNode = new Node(e,null,first);
        first = newNode;  //修改first指向新的节点
        if (pNode==null){
            last=newNode;
        }else {
            pNode.prev = newNode;
        }
        size++;
    }
    public Object removeFirst(){
        return remove(0);
    }

    public Object removeLast(){
        return remove(size-1);
    }

    public Object getFirst(){
        return get(0);
    }
    public Object getLast(){
        return get(size-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node =first;node!=null;node=node.next){
            sb.append(node.data);
            //元素之间使用逗号分隔
            if (node.next!= null){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
