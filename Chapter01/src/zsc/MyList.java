package zsc;

//通过接口定义一组线性表中的操作
public interface MyList {
    int getSize();  //返回线性表中元素的数据
    boolean isEmpty();  //判断线性表是否为空
    void insert(int i,Object e);    //在线性表的i索引值添加元素e
    boolean contains(Object e);     //判断线性表中是否包含元素e
    int indexOf(Object e);  //返回线性表中元素e的索引值
    Object remove(Object e);    //删除线性表中第一个与e相同的元素，并返回该元素
    Object remove(int i);    //删除线性表中索引为i的元素，并返回该元素
    Object replace(int i,Object e);    //使用元素e替换线性表中i位置的元素，并返回旧的元素
    Object get(int i);  //返回索引值为i的元素
    boolean insertBefore(Object p,Object e);    //在线性表元素p的前面插入元素e
    boolean insertAfter(Object p,Object e);    //在线性表元素p的后面插入元素e
}
