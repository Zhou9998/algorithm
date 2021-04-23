package com.zsc.binarysorttree;

/**
 * ����������
 *
 * @author zsc
 * @date 2021/4/22 20:50
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //ѭ����ӽڵ㵽����������
        for (int a : arr) {
            binarySortTree.add(new Node(a));
        }

        //�����������������
        binarySortTree.infixOrder();
    }
}

//��������������
class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //�������
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("����������Ϊ�գ����ܱ���");
        }
    }
}

//node�ڵ�
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * ����Ҫɾ���Ľ��
     *
     * @param value ϣ��ɾ���Ľ���ֵ
     * @return ����ҵ����ظý�㣬���򷵻�null
     */
    public Node search(int value) {
        if (value == this.value) { //�ҵ����Ǹý��
            return this;
        } else if (value < this.value) {//������ҵ�ֵС�ڵ�ǰ��㣬���������ݹ����
            //������ӽ��Ϊ��
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //������ҵ�ֵ��С�ڵ�ǰ��㣬���������ݹ����
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //��ӽڵ�ķ���(�ݹ�)
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //�жϴ���Ľڵ��ֵ�͵�ǰ�����ĸ��ڵ��ֵ�Ĺ�ϵ
        if (node.value < this.value) {
            if (this.left == null) { //��ǰ�������ӽڵ�Ϊ��
                this.left = node;
            } else {
                this.left.add(node);    //�ݹ��ȥ���������
            }
        } else if (node.value >= this.value) {
            if (this.right == null) { //��ǰ�������ӽڵ�Ϊ��
                this.right = node;
            } else {
                this.right.add(node);    //�ݹ��ȥ���������
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //�������
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
