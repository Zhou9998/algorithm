package com.zsc.avl;

/**
 * ƽ�������
 *
 * @author zsc
 * @date 2021/4/23 19:57
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //  int[] arr = {4, 3, 6, 5, 7, 8};
        //  int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //����һ��AVLTree����
        AVLTree avlTree = new AVLTree();
        //��ӽڵ�
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //����
        System.out.println("�������");
        avlTree.infixOrder();
        System.out.println("��û����ƽ��֮ǰ");
        System.out.println("���ĸ߶ȣ�" + avlTree.getRoot().height());
        System.out.println("�������ĸ߶ȣ�" + avlTree.getRoot().leftHeight());
        System.out.println("�������ĸ߶ȣ�" + avlTree.getRoot().rightHeight());
    }
}

//����AVL��
class AVLTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
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
        if (target.left != null) {
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

    //�����������ĸ߶�
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //�����������ĸ߶�
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //�����Ըýڵ�Ϊ���ڵ�����ĸ߶�
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //����ת����
    private void leftRotate() {
        //�����µĽڵ㣬�Ե�ǰ���ڵ��ֵ
        Node newNode = new Node(this.value);
        //���µĽڵ������������Ϊ��ǰ�ڵ��������
        newNode.left = this.left;
        //���µĽڵ�����������óɵ�ǰ�ڵ����������������
        newNode.right = this.right.left;
        //�ѵ�ǰ�ڵ��ֵ�滻����������ֵ
        this.value = this.right.value;
        //�ѵ�ǰ�������������óɵ�ǰ�����������������
        this.right = this.right.right;
        //�ѵ�ǰ����������(���ӽ��)���ó��µĽ��
        this.left = newNode;
    }

    //����ת����
    private void rightRotate() {
        //�����µĽڵ㣬�Ե�ǰ���ڵ��ֵ
        Node newNode = new Node(this.value);
        //���µĽڵ������������Ϊ��ǰ�ڵ��������
        newNode.right = this.right;
        //���µĽڵ�����������óɵ�ǰ�ڵ����������������
        newNode.left = this.left.right;
        //�ѵ�ǰ�ڵ��ֵ�滻����������ֵ
        this.value = this.left.value;
        //�ѵ�ǰ�������������óɵ�ǰ�����������������
        this.left = this.left.left;
        //�ѵ�ǰ����������(���ӽ��)���ó��µĽ��
        this.right = newNode;
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
        //�������һ���ڵ������������ĸ߶�-�������ĸ߶� > 1������ת
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //�ȶԵ�ǰ�ڵ���ҽڵ�(������)��������ת
                this.right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        //�������һ���ڵ������������ĸ߶�-�������ĸ߶� > 1������ת
        if (leftHeight() - rightHeight() > 1) {
            //����������������������ĸ߶�>�������������������ĸ߶�
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //�ȶԵ�ǰ�ڵ����ڵ�(������)��������ת
                this.left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
            return;
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
