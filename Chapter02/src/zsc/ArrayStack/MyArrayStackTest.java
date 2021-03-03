package zsc.ArrayStack;

public class MyArrayStackTest {

    public static void main(String[] args) {
        //1)创建堆栈
        MyArrayStack stack = new MyArrayStack();
        System.out.println(stack.getSize());
        System.out.println(stack.isEmpty());
        //2)压栈
        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");
        System.out.println(stack.isEmpty());
        System.out.println(stack.getSize());
        System.out.println(stack);

        //3)
        System.out.println(stack.peek());
        //4)出栈
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);

    }
}
