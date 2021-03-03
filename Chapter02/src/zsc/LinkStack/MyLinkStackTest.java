package zsc.LinkStack;

public class MyLinkStackTest {

    public static void main(String[] args) {

        //1)创建链栈
        MyLinkStack stack = new MyLinkStack();
        System.out.println(stack.getSize());
        System.out.println(stack.isEmpty());

        //2)压栈
        stack.push("ppp");
        stack.push("aaa");
        stack.push("xxoo");
        stack.push("aaa");

        //3)
        System.out.println(stack);

        //4)出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
