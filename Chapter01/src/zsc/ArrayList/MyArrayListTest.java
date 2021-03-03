package zsc.ArrayList;

public class MyArrayListTest {
    public static void main(String[] args) {
        //1)创建一个MyArrayList对象
        MyArrayList list1 = new MyArrayList();
        //2)判断是否为空
        System.out.println(list1.isEmpty());    //true
        System.out.println(list1.getSize());    //0
        //3)添加元素
        list1.insert(0,"aa");
        list1.insert(1,"bb");
        list1.insert(0,"cc");
        System.out.println(list1.isEmpty());    //false
        System.out.println(list1.getSize());    //3
        //4)把线性表中的内容打印输出
        System.out.println(list1);
        //5)判断是否存在
        System.out.println(list1.indexOf("aa"));    //1
        System.out.println(list1.indexOf("bb"));    //2
        System.out.println(list1.indexOf("cc"));    //0
        System.out.println(list1.indexOf("dd"));    //-1
        System.out.println(list1.contains("aa"));   //true
        System.out.println(list1.contains("xx"));   //false
        //6)删除
        list1.remove("dd");
        System.out.println(list1);
        list1.remove("bb");
        System.out.println(list1);
        list1.remove(0);
        System.out.println(list1);
        //7)替换
        list1.insert(0,"xx");
        list1.insert(0,"yy");
        list1.insert(0,"zz");
        System.out.println(list1);  //[zz,yy,xx,aa]
        list1.replace(0,"YY");
        System.out.println(list1);  //[YY,yy,xx,aa]
        //8)返回指定索引的元素
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        //9)在指定的元素前面/后面插入另外的元素
        list1.insertBefore("yy","oo");
        System.out.println(list1);
        list1.insertAfter("oo","zzz");
        System.out.println(list1);
    }
}
