package com.zsc.tree;

/**
 * ˳�������
 *
 * @author zsc
 * @date 2021/4/20 17:02
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //����˳�������
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

//��дArrBinaryTree��ʵ��˳��洢����������
class ArrBinaryTree {
    private int[] arr;  //�洢���ݽڵ�ķ�Χ

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //����preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    //��дһ�����������˳��洢��������ǰ�����
    /**
     * @param index �����±�
     */
    public void preOrder(int index) {
        //�������Ϊ�գ�����arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("����Ϊ�գ����ܰ��ն�������ǰ�����");
        }
        //�����ǰ���Ԫ��
        System.out.println(arr[index]);
        //����ݹ����
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (index * 2 + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
