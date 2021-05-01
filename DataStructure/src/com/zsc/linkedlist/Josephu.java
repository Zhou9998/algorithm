package com.zsc.linkedlist;

/**
 * 约瑟夫问题
 *
 * @author zsc
 * @date 2021/4/14 18:49
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.boyAdd(25);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 25);
    }
}

//创建环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void boyAdd(int nums) {
        //对nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy tempBoy = null;    //辅助指针，帮助构件环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);   //构成环
                tempBoy = first;
            } else {
                tempBoy.setNext(boy);
                boy.setNext(first);
                tempBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何节点");
            return;
        }
        Boy tempBoy = first;
        while (true) {
            System.out.println("小孩的编号为" + tempBoy.getNo());
            if (tempBoy.getNext() == first) {
                return;
            }
            tempBoy = tempBoy.getNext();
        }
    }

    /**
     * 根据用户输入,计算出出圈顺序
     *
     * @param startNo  表示从第几个节点开始
     * @param countNum 表示数几下
     * @param nums     表示有多少节点在链表中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
        }
        //创建一个辅助指针，帮助完成节点出圈
        Boy helper = first;
        //将helper指向最后一个节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        while (true) {
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.print(first.getNo() + " ");
            first = first.getNext();
            helper.setNext(first);
            if (helper == first) {
                break;
            }
        }
        System.out.println(first.getNo());
    }
}

class Boy {

    private int no; //编号

    private Boy next;   //指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}