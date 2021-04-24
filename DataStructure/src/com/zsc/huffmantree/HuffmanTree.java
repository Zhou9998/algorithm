package com.zsc.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ��������
 *
 * @author zsc
 * @date 2021/4/21 17:05
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //ǰ�����
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("�úշ������ǿ���");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //1.����arr����
        //2.��arr��ÿ��Ԫ�ع�����һ��node
        //3.��node���뵽ArrayList��
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //���� ��С����
            Collections.sort(nodes);
            //ȡ�����ڵ�Ȩֵ��С�����Ŷ�����
            //(1) ȡ��Ȩֵ��С�Ľ�㣨��������
            Node leftNode = nodes.get(0);
            //(2) ȡ��Ȩֵ�ڶ�С�Ľ�㣨��������
            Node rightNode = nodes.get(1);
            //(3)����һ���µĶ�����
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4)��ArrayListɾ��������Ķ�����
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)��parent���뵽nodes
            nodes.add(parent);
        }

        //���ع���������ͷ���
        return nodes.get(0);
    }
}


class Node implements Comparable<Node> {
    int value;  //�ڵ�Ȩֵ
    char c; //�ַ�
    Node left;  //���ӽڵ�
    Node right; //���ӽڵ�

    public Node(int value) {
        this.value = value;
    }

    //ǰ�����
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //��ʾ��С����ʼ����
        return this.value - node.value;
    }
}
