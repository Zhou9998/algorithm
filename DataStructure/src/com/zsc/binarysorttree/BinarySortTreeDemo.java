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

        //测试删除叶子节点
        binarySortTree.delNode(2);
        System.out.println("删除后的二叉树");
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
        while (target.left != null) {
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