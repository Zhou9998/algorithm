package com.zsc.tree;

/**
 * 节点
 *
 * @author zsc
 * @date 2021/4/20 17:56
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //1.如果leftType == 0 表示指向的是左子树，如果是1表示指向前驱结点
    private int leftType;
    //2.如果leftType == 0 表示指向的是右子树，如果是1表示指向后继结点
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
