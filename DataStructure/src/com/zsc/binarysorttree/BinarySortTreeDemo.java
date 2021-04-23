package com.zsc.binarysorttree;

/**
 * 二叉排序树
 *
 * @author zsc
 * @date 2021/4/22 20:50
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点到二叉排序树
        for (int a : arr) {
            binarySortTree.add(new Node(a));
        }

        //中序遍历二叉排序树
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
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
