package zsc;

//定义接口，定义栈的操作
public interface MyStack {
    int getSize();  //返回元素的个数
    boolean isEmpty();  //判断栈堆是否为空
    void push(Object e);    //把元素e添加到栈中
    Object pop();   //弹栈，出战
    Object peek();  //返回栈顶元素
}
