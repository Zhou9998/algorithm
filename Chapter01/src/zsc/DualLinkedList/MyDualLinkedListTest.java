package zsc.DualLinkedList;

public class MyDualLinkedListTest {
    public static void main(String[] args) {
        MyDualLinkedList linkedList =new MyDualLinkedList();

        System.out.println(linkedList.getSize());
        System.out.println(linkedList.isEmpty());
        linkedList.insert(0,"gg");
        System.out.println(linkedList);
        linkedList.insert(0,"jj");
        System.out.println(linkedList);
        linkedList.insert(1,"dd");
        System.out.println(linkedList);
        linkedList.insert(3,"mm");
        System.out.println(linkedList);

        //测试是否包括某个元素
        System.out.println(linkedList.indexOf("gg"));
        System.out.println(linkedList.indexOf("jj"));
        System.out.println(linkedList.indexOf("DD"));
        System.out.println(linkedList.indexOf("mm"));

        System.out.println(linkedList.remove(0));
        System.out.println(linkedList);

        linkedList.replace(0,"xx");
        System.out.println(linkedList); //[xx,gg,mm]
        linkedList.insertBefore("xx","zz");
        linkedList.insertAfter("gg","yy");
        System.out.println(linkedList);
    }
}
