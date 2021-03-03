package zsc;

import java.util.LinkedList;

public class BinaryTree {
    private BinaryTreeNode root;    //二叉树的根节点

    //构造方法
    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }
    //返回元素的个数
    public int getSize(){
        if (root == null){
            return 0;
        }
        return root.getSize();
    }
    //判断二叉树是否为空
    public boolean isEmpty(){
        return getSize() == 0;
    }

    //返回根节点
    public BinaryTreeNode getRoot(){
        if (root != null){
            return root;
        }
        return null;
    }

    //返回树的高度
    public int getHeight(){
        if (root == null){
            return 0;
        }
        return root.getHeight();
    }
    /******************二叉树先序遍历********************/
    //先序遍历二叉树，把遍历的节点存储到List列表中
    private void preOrderRecusion(BinaryTreeNode root,LinkedList<Object> list){
        if (root == null){
            return;     //空树
        }
        //先访问根节点，把根节点存储到列表中
        list.add(root.getData());
        //递归，先序遍历左子树
        preOrderRecusion(root.getLChild(),list);
        //递归，先序遍历右子树
        preOrderRecusion(root.getRChild(),list);
    }
    //打印当前二叉树的先序遍历序列
    public void preOrder(){
        LinkedList<Object> list = new LinkedList<>();
        preOrderRecusion(root,list);
        System.out.println(list);
    }

    /******************二叉树中序遍历********************/
    //先序遍历二叉树，把遍历的节点存储到List列表中
    private void inOrderRecusion(BinaryTreeNode root,LinkedList<Object> list){
        if (root == null){
            return;     //空树
        }
        //递归，中序遍历左子树
        preOrderRecusion(root.getLChild(),list);
        //访问根节点，把根节点存储到列表中
        list.add(root.getData());
        //递归，中序遍历右子树
        preOrderRecusion(root.getRChild(),list);
    }
    //打印当前二叉树的先序遍历序列
    public void inOrder(){
        LinkedList<Object> list = new LinkedList<>();
        inOrderRecusion(root,list);
        System.out.println(list);
    }
    /******************二叉树后序遍历********************/
    //后序遍历二叉树，把遍历的节点存储到List列表中
    private void postOrderRecusion(BinaryTreeNode root,LinkedList<Object> list){
        if (root == null){
            return;     //空树
        }
        //递归，后序遍历左子树
        preOrderRecusion(root.getLChild(),list);
        //递归，后序遍历右子树
        preOrderRecusion(root.getRChild(),list);
        //访问根节点，把根节点存储到列表中
        list.add(root.getData());
    }
    //打印当前二叉树的后序遍历序列
    public void postOrder(){
        LinkedList<Object> list = new LinkedList<>();
        postOrderRecusion(root,list);
        System.out.println(list);
    }

    /******************二叉树层序遍历********************/
    private void levelOrderTranverse(BinaryTreeNode root,LinkedList<Object> list){
        if (root == null){
            return;
        }
        //定义一个队列，存储结点
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);  //根节点入队
        while(! queue.isEmpty()){
            //把队列头部的节点取出来
            BinaryTreeNode node = queue.poll();
            list.add(node.getData());
            //分别把node的左节点和右节点入队
            if (node.hasLChild()){
                queue.offer(node.getLChild());
            }
            if (node.hasRChild()){
                queue.offer(node.getRChild());
            }
        }
    }
    public void levelOrder(){
        LinkedList<Object> linkedList = new LinkedList<>();
        levelOrderTranverse(root,linkedList);
        System.out.println(linkedList);
    }
}
