package com.zsc.avl;

/**
 * 平衡二叉树
 *
 * @author zsc
 * @date 2021/4/23 19:57
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //  int[] arr = {4, 3, 6, 5, 7, 8};
        //  int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在没有做平衡之前");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}

//创建AVL树
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

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param right 传入的节点
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node right) {
        Node target = right;
        //循环查找左节点
        if (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }


    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要找到要删除的节点targetNode
            Node targetNode = search(value);
            //如果没有找到需要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果二叉树只有一个根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {    //左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {   //右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {  //要删除的节点有左右子树
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { //删除节点只有一个子树
                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {   //如果targetNode是parent的左子结点
                            parent.left = targetNode.left;
                        } else {    //targetNode是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {   //如果targetNode是parent的左子结点
                            parent.left = targetNode.right;
                        } else {    //targetNode是parent的右子结点
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

//node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(this.value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子树的值
        this.value = this.right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        this.right = this.right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        this.left = newNode;
    }

    //右旋转方法
    private void rightRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(this.value);
        //把新的节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        //把新的节点的左子树设置成当前节点的左子树的右子树
        newNode.left = this.left.right;
        //把当前节点的值替换成左子树的值
        this.value = this.left.value;
        //把当前结点的左子树设置成当前结点左子树的左子树
        this.left = this.left.left;
        //把当前结点的右子树(右子结点)设置成新的结点
        this.right = newNode;
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { //找到就是该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            //如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return 返回的是待删除节点的父节点，如果没有则返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);
            } else if (this.value <= value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //添加节点的方法(递归)
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值和当前子树的根节点的值的关系
        if (node.value < this.value) {
            if (this.left == null) { //当前子树左子节点为空
                this.left = node;
            } else {
                this.left.add(node);    //递归的去左子树添加
            }
        } else if (node.value >= this.value) {
            if (this.right == null) { //当前子树右子节点为空
                this.right = node;
            } else {
                this.right.add(node);    //递归的去右子树添加
            }
        }
        //当添加完一个节点后，如果右子树的高度-左子树的高度 > 1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //先对当前节点的右节点(右子树)进行右旋转
                this.right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        //当添加完一个节点后，如果左子树的高度-右子树的高度 > 1，左旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树的高度>它的左子树的左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //先对当前节点的左节点(左子树)进行左旋转
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

    //中序遍历
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
