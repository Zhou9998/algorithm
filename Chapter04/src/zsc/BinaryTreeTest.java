package zsc;

public class BinaryTreeTest {
    public static void main(String[] args) {
        //创建节点
        BinaryTreeNode root = new BinaryTreeNode("oo");
        //创建为两个节点设置为root根节点的左孩子和右孩子
        BinaryTreeNode xx = new BinaryTreeNode("xx");
        BinaryTreeNode yy = new BinaryTreeNode("yy");
        root.setLChild(xx);
        root.setRChild(yy);
        //创建两个节点，作为xx节点的左孩子和右孩子
        BinaryTreeNode xl = new BinaryTreeNode("xll");
        BinaryTreeNode xr = new BinaryTreeNode("xrr");
        xx.setLChild(xl);
        xx.setRChild(xr);
        //创建一个节点作为yy节点的右孩子
        BinaryTreeNode yr = new BinaryTreeNode("yrr");
        yy.setRChild(yr);

        //创建二叉树
        BinaryTree tree = new BinaryTree(root);
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
        tree.levelOrder();
    }
}
