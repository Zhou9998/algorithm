package zsc.ArrayList;

import zsc.MyList;

public class MyArrayList implements MyList {
    private Object[] elements;//定义数组保存数据元素
    public static final int DEFAULT_CAPACITY = 16;//数组的默认初始化容量
    private int size; //保存数据元素的个数
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int i) {
        elements = new Object[i];
    }

    //返回元素的个数
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
        //在线性表的i位置插入e
        if (i < 0 || i > size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        //如果数组已满，需要对数组扩容
        if (size >= elements.length){
            expandSpace();
        }
        //从i开始，把元素依次后移
        for (int j = size;j > i;j--){
            elements[j] = elements[j-1];
        }
        elements[i] = e;
        size++;
    }
    //数组扩容
    private void expandSpace() {
        //定义一个更大的数组
        Object[] newElements = new Object[elements.length * 2];
        //把原来数组的内容复制到新的数组当中
        for (int i = 0;i < elements.length;i++){
            newElements[i] = elements[i];
        }
        //让原来的数组名指向新的数组
        elements = newElements;
    }
    //判断当前线性表是否包含元素e
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    //返回元素e在线性表中的索引值，如果不存在返回-1
    @Override
    public int indexOf(Object e) {
        //遍历数组
        if (e==null){
            //线性表中，用户可能添加null
            for (int i = 0;i<size;i++){
                if (elements[i]==null){
                    return i;
                }
            }
        }else {
            for (int i = 0;i<size;i++){
                if (e.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    //在线性表中第一个与e相同的元素
    @Override
    public Object remove(Object e) {
        //获得e在线性表中的索引值
        int index = indexOf(e);
        if (index < 0){
            return null;
        }
        return remove(index);
    }
    //删除指定索引值的元素
    @Override
    public Object remove(int i) {
        //判断i是否越界
        if (i<0 || i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        //把要删除的元素保存起来
        Object old = elements[i];
        //把i+1开始的元素依次迁移
        for (int j = i;j<size-1;j++){
            elements[j]=elements[j+1];
        }
        //把最后的元素置为null
        elements[size-1] = null;
        //修改元素的个数
        size--;
        return old;
    }

    //把索引值为i的元素替换为e
    @Override
    public Object replace(int i, Object e) {
        if (i<0||i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        //替换
        Object old = elements[i];
        elements[i]=e;
        return old;
    }

    //返回指定位置的元素
    @Override
    public Object get(int i) {
        if (i<0||i>=size){
            throw new IndexOutOfBoundsException(i+"越界");
        }
        return elements[i];
    }

    //在指定的元素前插入一个元素
    @Override
    public boolean insertBefore(Object p, Object e) {
        //确定元素p在线性表中的位置
        int index = indexOf(p);
        if (index<0){
            return false;
        }
        insert(index,e);
        //插入元素
        return true;
    }

    @Override
    public boolean insertAfter(Object p, Object e) {
        //确定元素p在线性表中的位置
        int index = indexOf(p);
        if (index<0){
            return false;
        }
        insert(index+1,e);
        //插入元素
        return true;
    }

    @Override
    public String toString() {
        //把线性表中的每个元素连接起来，遍历数组中的已添加的元素
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0;i<size;i++){
            sb.append(elements[i]);
            if (i<size-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
