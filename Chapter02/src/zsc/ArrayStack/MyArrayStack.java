package zsc.ArrayStack;

import javafx.beans.binding.ObjectExpression;
import zsc.MyStack;

import java.util.Arrays;

public class MyArrayStack implements MyStack {
    private Object[] elements;  //定义数组保存栈堆的元素
    public static final int DEFAULT_CAPACITY = 16;  //堆栈的默认容量
    private int top;    //栈顶指针

    public MyArrayStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayStack(int initialCapacity) {
        elements = new Object[initialCapacity];
    }
    //返回元素的数量
    @Override
    public int getSize() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top <= 0;
    }


    @Override
    public void push(Object e) {
        //判断堆栈是否已满，如果已满，需要对其进行扩容
        if (top >= elements.length){
            Object[] newEle = new Object[elements.length*2];
            for (int i = 0; i < elements.length;i++){
                newEle[i]=elements[i];
                elements = newEle;
            }
        }
        elements[top] = e;
        top++;
    }

    @Override
    public Object pop() {
        if (top<=0){
            throw new StackOverflowError("栈已空");
        }
        top--;
        return elements[top];
    }

    @Override
    public Object peek() {
        if (top<=0){
            throw new StackOverflowError("栈已空");
        }
        return elements[top-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        //从栈顶到栈底的顺序添加各个元素
        for(int i = top - 1;i>=0;i--){
            sb.append(elements[i]);
            if (i>0){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
