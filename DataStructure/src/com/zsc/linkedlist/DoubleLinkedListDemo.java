package com.zsc.linkedlist;

/**
 * 双向链表
 *
 * @author zsc
 * @date 2021/4/14 13:13
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试~~~~");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(1, "宋江", "呼保义");
        doubleLinkedList.update(newHeroNode);

        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~~~");
        doubleLinkedList.list();

        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.list();
    }
}


//创建一个双向链表类
class DoubleLinkedList {
    //先初始化一个头结点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //添加一个节点到双向链表最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助
        HeroNode2 temp = head;
        while (temp.next != null) {
            //找到链表的最后
            //if (temp.next == null) {
            //    break;
            //}
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出while循环后，temp就指向了链表最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按编号插入节点
    public void addByOrder(HeroNode2 heroNode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则加入不了
        HeroNode2 temp = head;
        boolean flag = false; //flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }
            if (temp.next.no > heroNode.no) {    //位置找到，就在temp的后面插入
                heroNode.next = temp.next;
                heroNode.pre = temp;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("准备插入的英雄的" + heroNode.no + "已经存在");
        }
    }

    //修改一个节点的内容
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助节点
        HeroNode2 temp = head.next;

        boolean flag = false;  //表示是否找到该节点

        while (true) {
            if (temp == null) {
                break;  //已经遍历完链表
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else { //没有找到这个节点
            System.out.println("没有找到编号为" + heroNode.no + "的节点");
        }
    }

    //对于双向链表，可以直接找到要删除的这个节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;   //是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("要删除的" + no + "节点不存在");
        }
    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }
        //因为头结点不能动，因此需要一个辅助变量temp来遍历
        HeroNode2 temp = head.next;
        while (temp != null) {
            //判断是否到链表最后
            //if (temp == null) {
            //    break;
            //}
            //输出节点的信息
            System.out.println(temp);
            //将tem后移
            temp = temp.next;
        }
    }
}


//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;   //指向后一个节点
    public HeroNode2 pre;    //指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}