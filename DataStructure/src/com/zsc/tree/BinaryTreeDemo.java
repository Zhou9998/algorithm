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
        tree.postOrder();
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
    }//ǰ�����

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

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //�ݹ�ɾ���ڵ�
    public void delNode(int no) {
        //�ж����ӽڵ��ǲ���Ҫɾ����
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        //�ж����ӽڵ��ǲ���Ҫɾ����
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        //�����������еݹ�
        if (this.left != null) {
            this.left.delNode(no);
        }
        //�����������еݹ�
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //�ݹ�ɾ���ڵ�
    public void delNode2(int no) {
        //�ж����ӽڵ��ǲ���Ҫɾ����
        if (this.left != null && this.left.getNo() == no) {
            if (this.left.left != null && this.left.right != null) {
                this.left.left.right = this.left.right;
                this.left = this.left.left;
            } else if (this.left.left != null && this.left.right == null) {
                this.left = this.left.left;
            } else if (this.left.left == null && this.left.right != null) {
                this.left = this.left.right;
            } else {
                this.left = null;
            }
            return;
        }
        //�ж����ӽڵ��ǲ���Ҫɾ����
        if (this.right != null && this.right.getNo() == no) {
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
            } else if (this.right.left != null && this.right.right == null) {
                this.right = this.right.left;
            } else if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
            } else {
                this.right = null;
            }
            return;
        }
        //�����������еݹ�
        if (this.left != null) {
            this.left.delNode(no);
        }
        //�����������еݹ�
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //ǰ������ķ���
    public void preOrder() {
        System.out.println(this);   //������ڵ�
        //�ݹ���������ǰ�����
        if (this.left != null) {
            this.left.preOrder();
        }
        //�ݹ���������ǰ�����
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //��������ķ���
    public void infixOrder() {

        //�ݹ����������������
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);   //������ڵ�
        //�ݹ����������������
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //��������ķ���
    public void postOrder() {

        //�ݹ����������������
        if (this.left != null) {
            this.left.postOrder();
        }

        //�ݹ����������������
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);   //������ڵ�
    }

    //ǰ���������
    public HeroNode preOrderSearch(int no) {
        //�Ƚϵ�ǰ�ڵ��ǲ���
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //�����������
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //�Ƚϵ�ǰ�ڵ��ǲ���
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //������������
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //�Ƚϵ�ǰ�ڵ��ǲ���
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
