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

        //����ɾ��Ҷ�ӽڵ�
        binarySortTree.delNode(2);
        System.out.println("ɾ����Ķ�����");
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

    //����Ҫɾ���Ľڵ�
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //���Ҹ����
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param right ����Ľڵ�
     * @return ������nodeΪ���ڵ�Ķ�������������С�ڵ��ֵ
     */
    public int delRightTreeMin(Node right) {
        Node target = right;
        //ѭ��������ڵ�
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }


    //ɾ���ڵ�
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.��Ҫ�ҵ�Ҫɾ���Ľڵ�targetNode
            Node targetNode = search(value);
            //���û���ҵ���Ҫɾ���Ľڵ�
            if (targetNode == null) {
                return;
            }
            //���������ֻ��һ�����ڵ�
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //�ҵ�targetNode�ĸ��ڵ�
            Node parent = searchParent(value);
            //���Ҫɾ���Ľڵ���Ҷ�ӽڵ�
            if (targetNode.left == null && targetNode.right == null) {
                //�ж�targetNode�Ǹ��ڵ�����ӽڵ㻹�����ӽڵ�
                if (parent.left != null && parent.left.value == value) {    //���ӽڵ�
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {   //���ӽڵ�
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {  //Ҫɾ���Ľڵ�����������
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { //ɾ���ڵ�ֻ��һ������
                //���Ҫɾ���Ľ�������ӽ��
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {   //���targetNode��parent�����ӽ��
                            parent.left = targetNode.left;
                        } else {    //targetNode��parent�����ӽ��
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {   //���targetNode��parent�����ӽ��
                            parent.left = targetNode.right;
                        } else {    //targetNode��parent�����ӽ��
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
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

    /**
     * ����Ҫɾ���ڵ�ĸ��ڵ�
     *
     * @param value
     * @return ���ص��Ǵ�ɾ���ڵ�ĸ��ڵ㣬���û���򷵻�null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //������ҵ�ֵС�ڵ�ǰ�ڵ��ֵ�����ҵ�ǰ�ڵ�����ӽڵ㲻Ϊ��
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);
            } else if (this.value <= value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
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