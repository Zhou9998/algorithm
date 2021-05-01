package com.zsc.tree;

/**
 * 二叉树
 *
 * @author zsc
 * @date 2021/4/20 12:50
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义");
        HeroNode heroNode3 = new HeroNode(3, "吴用");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜");
        HeroNode heroNode5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        tree.setRoot(root);
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        /*
        //测试
        System.out.println("前序遍历");
        tree.preOrder();    //前序遍历
        System.out.println("中序遍历");
        tree.infixOrder();    //中序遍历
        System.out.println("后序遍历");
        tree.postOrder();    //后序遍历
         */

        /*
        //前序查找
        System.out.println("前序查找");
        HeroNode node1 = tree.preOrderSearch(15);
        if (node1 != null) {
            System.out.println("信息为no=" + node1.getNo() + "，name=" + node1.getName());
        } else {
            System.out.println("没有找到no=" + 15 + "的节点");
        }
        //中序查找
        System.out.println("中序查找");
        HeroNode node2 = tree.infixOrderSearch(5);
        if (node2 != null) {
            System.out.println("信息为no=" + node2.getNo() + "，name=" + node2.getName());
        } else {
            System.out.println("没有找到no=" + 15 + "的节点");
        }
        //后续查找
        System.out.println("后序查找");
        HeroNode node3 = tree.postOrderSearch(5);
        if (node3 != null) {
            System.out.println("信息为no=" + node3.getNo() + "，name=" + node3.getName());
        } else {
            System.out.println("没有找到no=" + 15 + "的节点");
        }
        */
        System.out.println("删除前前序遍历");
        tree.preOrder();
        tree.delNode(5);
        System.out.println("删除后前序遍历");
        tree.preOrder();
    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，这里需要判断root是不是所需要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    //删除节点
    public void delNode2(int no) {
        if (root != null) {
            //如果只有一个root节点，这里需要判断root是不是所需要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode2(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }
}

