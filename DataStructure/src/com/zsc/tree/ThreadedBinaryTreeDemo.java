package com.zsc.tree;

/**
 * ������������
 *
 * @author zsc
 * @date 2021/4/20 17:53
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //����������������Ҫ�ݹ鴴��, ���ڼ򵥴���ʹ���ֶ�����
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //��������������
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //����: ��10�Žڵ����
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10�Ž���ǰ������� =" + leftNode); //3
        System.out.println("10�Ž��ĺ�̽����=" + rightNode); //1

        System.out.println("ʹ���������ķ�ʽ���� ������������");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    //Ϊ��ʵ������������Ҫ����һ��ָ��ǰ�ڵ��ǰ������ָ��
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //�����������������ķ���
    public void threadedList() {
        //����һ���������洢��ǰ�����Ľ�㣬��root��ʼ
        HeroNode node = root;
        while(node != null) {
            //ѭ�����ҵ�leftType == 1�Ľ�㣬��һ���ҵ�����8���
            //�������ű������仯,��Ϊ��leftType==1ʱ��˵���ý���ǰ�����������������Ч���
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //��ӡ��ǰ������
            System.out.println(node);
            //�����ǰ������ָ��ָ����Ǻ�̽��,��һֱ���
            while(node.getRightType() == 1) {
                //��ȡ����ǰ���ĺ�̽��
                node = node.getRight();
                System.out.println(node);
            }
            //�滻��������Ľ��
            node = node.getRight();

        }
    }
    //��д�Զ��������������������ķ���

    /**
     * @param node ��ǰ��Ҫ�������Ľڵ�
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //1.��������������
        threadedNodes(node.getLeft());
        //2.��������ǰ�ڵ�
        //����ǰ�����
        if (node.getLeft() == null) {
            //�õ�ǰ�ڵ����ָ��ָ��ǰ�����
            node.setLeft(pre);
            //�޸ĵ�ǰ�ڵ����ָ������
            node.setLeftType(1);
        }
        //�����̽��
        if (pre != null && pre.getRight() == null) {
            //��ǰ���ڵ����ָ��ָ��ǰ�ڵ�
            pre.setRight(node);
            //�޸�ǰ��������ָ������
            pre.setRightType(1);
        }
        //ÿ����һ���ڵ���õ�ǰ�ڵ�����һ���ڵ��ǰ�����
        pre = node;
        //3.��������������
        threadedNodes(node.getRight());
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

