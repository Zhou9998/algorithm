package com.zsc.huffmancode;

import java.util.*;

/**
 * ����������
 *
 * @author zsc
 * @date 2021/4/21 19:40
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);    //40

        List<Node> nodes = getNodes(contentBytes);
        //System.out.println(nodes);

        Node root = createHuffmanTree(nodes);
        preOrder(root);
    }
    //ǰ�����
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("�úշ������ǿ���");
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        //����һ��ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //�洢ÿһ��byte���ֵĴ���->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //˵��Map��û������ַ�����
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //��ÿһ����ֵ��ת��һ��Node���󲢼��뵽nodes����
        for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //ͨ��һ��list��������Ӧ�Ĺ�������
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            //��С����
            Collections.sort(nodes);
            //ȡ��
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //���Ľڵ����Huffman�ĸ��ڵ�
        return nodes.get(0);
    }
}

//����node�������ݺ�Ȩֵ
class Node implements Comparable<Node> {
    Byte data; // �������(�ַ�)��������'a' => 97 ' ' => 32
    int weight; //Ȩֵ, ��ʾ�ַ����ֵĴ���
    Node left;//
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // ��С��������
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
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
}
