package com.zsc.linkedlist;

import sun.awt.windows.ThemeReader;

import java.util.Stack;

/**
 * 单向链表
 *
 * @author zsc
 * @date 2021/4/13 14:56
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        //创建一个链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //加入

        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero4);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);

        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList1.list();
        System.out.println( );
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.list();
        System.out.println( );
        HeroNode head1 = singleLinkedList1.getHead();
        HeroNode head2 = singleLinkedList2.getHead();

        collectList(head1,head2);
        singleLinkedList2.list();

        /*HeroNode head = singleLinkedList.getHead();
        reverseList(head);
        singleLinkedList.list();*/


/*
        //显示
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(1, "宋江", "呼保义");
        singleLinkedList.update(newHeroNode);
        //显示
        singleLinkedList.list();

        singleLinkedList.del(4);
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        singleLinkedList.del(3);
        //显示
        singleLinkedList.list();

 */
    }

    /**
     * 查找单链表中的倒数第k个节点
     *
     * @param head
     * @param index
     * @return
     */
    //思路
    //1.编写一个方法，接受head节点，同时接受一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总长度getLength
    //4.得到size后，从链表的第一个开始遍历(size-index)个
    //5.如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空，返回null
        if (head.next == null) {
            return null;    //没有找到
        }
        //第一次遍历得到链表的个数
        int size = getLength(head);
        //第二次遍历 size-index位置，就是倒数第k个元素
        //index的检验
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < (size - index); i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头结点)
     *
     * @param head 链表的头结点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 反转链表
     *
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针，帮助遍历原来的指针
        HeroNode temp = head.next;
        //指向当前节点temp的下一个节点
        HeroNode next = null;
        HeroNode reverseNode = new HeroNode(0, "", "");
        //遍历原本的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (temp != null) {
            next = temp.next;   //暂时保存当前节点的下一个节点
            temp.next = reverseNode.next;   //将temp的下一个节点指向新的链表的最前端
            reverseNode.next = temp;    //将temp连接到新的链表上
            temp = next;    //让temp后移
        }
        //将head.next指向reverseNode.next，实现单链表的反转
        head.next = reverseNode.next;
    }

    /**
     * 逆序打印链表
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return; //空链表，不能打印
        }
        //创建一个stack，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        //将链表所有节点压入栈中
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个链表
     *
     * @param head1
     * @param head2
     * @return
     */
    public static HeroNode collectList(HeroNode head1, HeroNode head2) {
        HeroNode temp = head1.next;
        HeroNode next = null;
        while (temp != null){
            next = temp.next;
            insertNode(temp,head2);
            temp = next;
        }
        return head2;
    }

    /**
     * 将节点有序插入链表
     *
     * @param node
     * @param head
     */
    public static void insertNode(HeroNode node, HeroNode head) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                node.next = null;
                break;
            }
            if (temp.next.no > node.no) {
                node.next = temp.next;
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }
}

//定义一个SingListedList
class SingleLinkedList {
    //先初始化一个头结点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助
        HeroNode temp = head;
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
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则加入不了
        HeroNode temp = head;
        boolean flag = false; //flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {    //位置找到，就在temp的后面插入
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
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;  //注意temp.next == null的情况
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据编号来修改，即no编号不能改
    //说明
    //1、根据newHeroNode的no来修改即可
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助节点
        HeroNode temp = head.next;
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

    //删除节点
    //思路
    //1.head节点不能动，因此我们需要一个temp辅助节点找到待删除的前一个节点
    //2.在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;   //是否找到待删除节点的前一个节点
        while (true) {
            if (temp.next == null) { //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
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
        HeroNode temp = head.next;
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
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickName) {
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