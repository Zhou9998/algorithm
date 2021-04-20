package com.zsc.tree;

/**
 * �ڵ�
 *
 * @author zsc
 * @date 2021/4/20 17:56
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //1.���leftType == 0 ��ʾָ������������������1��ʾָ��ǰ�����
    private int leftType;
    //2.���leftType == 0 ��ʾָ������������������1��ʾָ���̽��
    private int rightType;


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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
