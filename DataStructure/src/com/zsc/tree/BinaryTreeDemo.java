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
        tree.postOrder();
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
    }//前序遍历

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

    //递归删除节点
    public void delNode(int no) {
        //判断左子节点是不是要删除的
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        //判断右子节点是不是要删除的
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        //向左子树进行递归
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树进行递归
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //递归删除节点
    public void delNode2(int no) {
        //判断左子节点是不是要删除的
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
        //判断右子节点是不是要删除的
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
        //向左子树进行递归
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树进行递归
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this);   //输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历的方法
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);   //输出父节点
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历的方法
    public void postOrder() {

        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);   //输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        //比较当前节点是不是
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

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后续遍历查找
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
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
