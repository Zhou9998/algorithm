package com.zsc.tree;

/**
 * ������
 *
 * @author zsc
 * @date 2021/4/20 12:50
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroNode root = new HeroNode(1, "�ν�");
        HeroNode heroNode2 = new HeroNode(2, "¬����");
        HeroNode heroNode3 = new HeroNode(3, "����");
        HeroNode heroNode4 = new HeroNode(4, "����ʤ");
        HeroNode heroNode5 = new HeroNode(5, "��ʤ");

        //�ֶ�����������
        tree.setRoot(root);
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        /*
        //����
        System.out.println("ǰ�����");
        tree.preOrder();    //ǰ�����
        System.out.println("�������");
        tree.infixOrder();    //�������
        System.out.println("�������");
        tree.postOrder();    //�������
         */

        /*
        //ǰ�����
        System.out.println("ǰ�����");
        HeroNode node1 = tree.preOrderSearch(15);
        if (node1 != null) {
            System.out.println("��ϢΪno=" + node1.getNo() + "��name=" + node1.getName());
        } else {
            System.out.println("û���ҵ�no=" + 15 + "�Ľڵ�");
        }
        //�������
        System.out.println("�������");
        HeroNode node2 = tree.infixOrderSearch(5);
        if (node2 != null) {
            System.out.println("��ϢΪno=" + node2.getNo() + "��name=" + node2.getName());
        } else {
            System.out.println("û���ҵ�no=" + 15 + "�Ľڵ�");
        }
        //��������
        System.out.println("�������");
        HeroNode node3 = tree.postOrderSearch(5);
        if (node3 != null) {
            System.out.println("��ϢΪno=" + node3.getNo() + "��name=" + node3.getName());
        } else {
            System.out.println("û���ҵ�no=" + 15 + "�Ľڵ�");
        }
        */
        System.out.println("ɾ��ǰǰ�����");
        tree.preOrder();
        tree.delNode(5);
        System.out.println("ɾ����ǰ�����");
        tree.preOrder();
    }
}

//����BinaryTree������
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //ǰ�����
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("������Ϊ�գ��޷�������");
        }
    }

    //�������
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("������Ϊ�գ��޷�������");
        }
    }
    //�������
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("������Ϊ�գ��޷�������");
        }
    }

    //ǰ�����
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //�������
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //�������
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //ɾ���ڵ�
    public void delNode(int no) {
        if (root != null) {
            //���ֻ��һ��root�ڵ㣬������Ҫ�ж�root�ǲ�������Ҫɾ���Ľڵ�
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("����������ɾ��~");
        }
    }

    //ɾ���ڵ�
    public void delNode2(int no) {
        if (root != null) {
            //���ֻ��һ��root�ڵ㣬������Ҫ�ж�root�ǲ�������Ҫɾ���Ľڵ�
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode2(no);
            }
        } else {
            System.out.println("����������ɾ��~");
        }
    }
}

