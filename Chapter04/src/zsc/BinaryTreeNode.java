package zsc;

public class BinaryTreeNode {
    private Object data;    //数据域
    private BinaryTreeNode parent;  //父节点指针域
    private BinaryTreeNode lChild;  //左孩子指针域
    private BinaryTreeNode rChild;  //右孩子指针域
    private int height; //以当前节点为根节点的二叉树的高度
    private int size; //以当前节点为根节点的二叉树的所有节点的数量

    /*********构造方法*********/
    //根据指定的数据创建一个节点
    public BinaryTreeNode(Object e){
        data = e;
        parent = null;
        lChild = null;
        rChild = null;
        height = 1; //二叉树的高度从1开始
        size = 1;
    }

    public BinaryTreeNode() {
        this(null);
    }

    /*********判断当前节点的情况*********/
    //判断是否有父节点
    public boolean hasParent(){
        return parent != null;
    }
    //判断是否有左孩子
    public boolean hasLChild(){
        return lChild != null;
    }

    //判断是否有右孩子
    public boolean hasRChild(){
        return rChild != null;
    }

    //判断是否为叶子节点
    public boolean isLeaf(){
        return lChild == null && rChild == null;
    }

    //判断是否为父节点的左孩子
    public boolean isLChild(){
        return parent != null && parent.lChild == this;
    }

    //判断是否为父节点的右孩子
    public boolean isRChild(){
        return parent != null && parent.rChild == this;
    }

    /*********与高度有关的数据*********/
    //返回高度
    public int getHeight(){
        return height;
    }
    //更新当前节点的高度，祖先节点的高度
    public void updateHeight(){
        int newHeight = 0;
        //当前节点的高度 为左子树的高度 或者 右子树的高度 较大的那个加一
        if (hasLChild()){
            newHeight = Math.max(newHeight,getLChild().getHeight() + 1);
        }
        if (hasRChild()){
            newHeight = Math.max(newHeight,getRChild().getHeight() + 1);
        }
        //如果当前节点高度有变化，递归更新祖先节点的高度
        if (newHeight == height){
            return;
        }
        //把新的高度作为当前节点的高度
        height = newHeight;
        //更新祖先节点的高度
        if (hasParent()){
            getParent().updateHeight();
        }
    }
    /*********与size节点个数相关的操作*********/
    //返回以当前节点为根的二叉树的结点数
    public int getSize(){
        return size;
    }
    //更新当前节点及祖先的结点数
    public void updateSize(){
        size = 1;   //当前节点本身
        //累加左子树的节点数
        if (hasLChild()){
            size += getLChild().getSize();
        }
        if (hasRChild()){
            size += getRChild().getSize();
        }
        //递归更新祖先节点数
        if (hasParent()){
            getParent().updateSize();
        }
    }
    /*********与左孩子有关的操作*********/
    //返回左孩子
    public BinaryTreeNode getLChild(){
        return lChild;
    }
    //设置当前节点的左孩子，把原来的左孩子返回
    public BinaryTreeNode setLChild(BinaryTreeNode newLChild){
        BinaryTreeNode oldLChild = this.lChild;
        //先断开当前节点的左孩子
        if (hasLChild()){
            lChild.disInheritence();
        }
        if (newLChild != null){
            newLChild.disInheritence(); //先把参节点断开与原来父节点的关系;
            this.lChild = newLChild;
            newLChild.parent = this;
            this.updateHeight();
            this.updateSize();
        }
        return oldLChild;
    }
    /*********与右孩子有关的操作*********/
    //返回右孩子
    public BinaryTreeNode getRChild(){
        return rChild;
    }
    //设置当前节点的右孩子，把原来的右孩子返回
    public BinaryTreeNode setRChild(BinaryTreeNode newRChild){
        BinaryTreeNode oldRChild = this.lChild;
        //先断开当前节点的左孩子
        if (hasRChild()){
            rChild.disInheritence();
        }
        if (newRChild != null){
            newRChild.disInheritence(); //先把参节点断开与原来父节点的关系;
            this.rChild = newRChild;
            newRChild.parent = this;
            this.updateHeight();
            this.updateSize();
        }
        return oldRChild;
    }
    /*********与父节点有关的操作*********/
    //返回父节点
    public BinaryTreeNode getParent(){
        return parent;
    }
    //断开与父节点的关系
    public void disInheritence(){
        //如果没有父节点
        if (!hasParent()){
            return;
        }
        //如果当前节点是父节点的左孩子
        if (isLChild()){
            parent.lChild = null;
        }
        //如果当前节点是父节点的右孩子
        if (isRChild()){
            parent.rChild = null;
        }
        parent.updateHeight();  //更新父节点的高度
        parent.updateSize();    //更新节点数
        parent = null;          //修改当前节点的父节点
    }
    public Object getData(){
        return this.data;
    }
}
