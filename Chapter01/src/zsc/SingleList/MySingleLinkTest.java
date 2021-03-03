package zsc.SingleList;

public class MySingleLinkTest {
    public static void main(String[] args) {
        //1)创建链表
        MySingleLink link = new MySingleLink();
        //2)判断
        System.out.println(link.isEmpty()); //true
        System.out.println(link.getSize()); //0
        //3)插入元素
        link.insert(0,"aa");
        link.insert(0,"bb");
        link.insert(0,"cc");
        link.insert(0,"dd");
        System.out.println(link);
        //4)判断元素是否存在
        System.out.println(link.indexOf("dd")); //0
        System.out.println(link.indexOf("aa")); //3
        System.out.println(link.indexOf("xx")); //-1
        System.out.println(link.indexOf("cc")); //1
        //5)删除节点
        System.out.println(link.remove("xx"));
        System.out.println(link.remove("bb"));
        System.out.println(link);
        System.out.println(link.remove(0));
        System.out.println(link);
        //6)返回元素，元素替换
        System.out.println(link.get(0));
        System.out.println(link.replace(0,"CC"));
        System.out.println(link);
        //7)在指定的元素前面/后面插入元素
        link.insertBefore("aa","bb");
        link.insertAfter("bb","BB");
        System.out.println(link);
    }
}
