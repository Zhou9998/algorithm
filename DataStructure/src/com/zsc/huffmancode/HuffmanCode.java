package com.zsc.huffmancode;

import java.io.*;
import java.util.*;

/**
 * ����������
 *
 * @author zsc
 * @date 2021/4/21 19:40
 */
public class HuffmanCode {
    public static void main(String[] args) {
        //����ѹ���ļ�
        //String srcFile = "d://src.bmp";
        //String dstFile = "d://src.zip";
        //zipFile(srcFile, dstFile);
        //System.out.println("ѹ���ļ�ok~~");

        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] bytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(bytes));

        byte[] sourceBytes = decode(huffmanCodes, bytes);
        System.out.println(new String(sourceBytes));

        /*
        List<Node> nodes = getNodes(contentBytes);
        //System.out.println(nodes);
        Node root = createHuffmanTree(nodes);
        preOrder(root);

        //����
        getCode(root);
        System.out.println(huffmanCodes);   //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}

        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeBytes));  //[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        */
    }

    /**
     * ������ǰ�����
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("�úշ������ǿ���");
        }
    }
    /**
     * ��װǰ�����з���
     *
     * @param bytes ԭʼ���ַ�����Ӧ���ֽ�����
     * @return �������������봦�����ֽ�����
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);
        //����nodes������������
        Node root = createHuffmanTree(nodes);
        //���ɶ�Ӧ�Ĺ��������루���ݹ���������
        Map<Byte, String> huffmanCodes = getCode(root);
        System.out.println(huffmanCodes);
        //���ݹ����������ԭʼ�������ѹ��
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }
    /**
     * ���ɶ������Ľڵ�
     *
     * @param bytes
     * @return �洢�ڵ������
     */
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
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * ͨ��һ��list��������Ӧ�Ĺ�������
     *
     * @param nodes
     * @return ���������ĸ��ڵ�
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //��С��������
            Collections.sort(nodes);
            //ȡ��
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //���Ľڵ����Huffman�ĸ��ڵ�
        return nodes.get(0);
    }

    //1.�����������������Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.�����ɹ����������ʱ����Ҫȥƴ��·��������һ��StringBuilder���洢ĳ��Ҷ�ӽڵ��·��
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * ���ܣ��õ��������node�ڵ������Ҷ�ӽڵ�Ĺ��������룬�����뵽huffmanCodes����
     *
     * @param node          ����ڵ�
     * @param code          ·�������ӽڵ㣺0 ���ӽڵ㣺1
     * @param stringBuilder ����ƴ��·��
     */
    private static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        //�������code���뵽builder
        builder.append(code);
        if (node != null) {    //������
            //�жϵ�ǰnode��Ҷ�ӽڵ㻹�Ƿ�Ҷ�ӽ��
            if (node.data == null) { //��Ҷ�ӽڵ�
                //����ݹ�
                getCode(node.left, "0", builder);
                //���ҵݹ�
                getCode(node.right, "1", builder);
            } else {
                //��ʾ�ҵ���ĳ��Ҷ�ӽڵ�����
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    /**
     * Ϊ�˵��÷��㣬����getCode
     */
    private static Map<Byte, String> getCode(Node root) {
        if (root == null) {
            return null;
        }
        getCode(root.left, "0", stringBuilder);
        getCode(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * ��дһ�����������ַ�����Ӧ��byte[] ���飬ͨ�����ɵĺշ������������һ���շ������� ѹ�����byte[]
     *
     * @param bytes        ��ʱԭʼ���ַ�����Ӧ�� byte[]
     * @param huffmanCodes ���ɵĺշ�������map
     * @return ���غշ������봦���� byte[]
     * ������ String content = "i like like like java do you like a java"; =�� byte[] contentBytes = content.getBytes();
     * ���ص��� �ַ��� "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => ��Ӧ�� byte[] huffmanCodeBytes  ���� 8λ��Ӧһ�� byte,���뵽 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(����) => byte  [�Ƶ�  10101000=> 10101000 - 1 => 10100111(����)=> 11011000(ԭ��)= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //����huffmanCodes��bytesת�ɹ����������Ӧ���ַ���
        StringBuilder stringBuilder = new StringBuilder();
        //����bytes����
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));  //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        }
        //���ַ���ת��byte[]
        //ͳ�Ʒ���byte[] huffmanCodeBytes�ĳ���
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //�����洢ѹ�����byte����
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {   //ÿ8λ��Ӧһ��byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {    //����8λ
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //˼·
    //1. ��huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]��д��ת�� �շ��������Ӧ�Ķ����Ƶ��ַ��� "1010100010111..."
    //2. �շ��������Ӧ�Ķ����Ƶ��ַ��� "1010100010111..." =�� ���� �շ�������  =�� "i like like like java do you like a java"
    /**
     * ��byteת��һ�������Ƶ��ַ���
     *
     * @param b    �����byte
     * @param flag �Ƿ���Ҫ����λ�������true��ʾ��Ҫ����λ
     * @return byte��Ӧ�Ķ����Ƶ��ַ������������ʽ����
     */
    private static String byteToBitString(boolean flag, byte b) {
        //ʹ��һ����������b
        int temp = b;
        //���������������λ
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);  //���ص���temp��Ӧ�Ķ����ƵĲ���
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * ��ɶ����ݵĽ���
     *
     * @param huffmanCodes �����������map
     * @param huffmanBytes ����������õ����ֽ�����
     * @return ����ԭ�����ַ�����Ӧ������
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. �ȵõ� huffmanBytes��Ӧ�Ķ����Ƶ��ַ�������ʽ 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //��byte����ת�ɶ����Ƶ��ַ���
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //�ж��ǲ������һ���ֽ�
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //���ַ�������ָ���ĺշ���������н���
        //�Ѻշ����������е�������Ϊ�����ѯ a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //����һ�����ϣ����byte
        List<Byte> list = new ArrayList<>();
        //i �������ɾ�������,ɨ�� stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // С�ļ�����
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //������ȡ�� key 1
                String key = stringBuilder.substring(i, i + count);//i ��������count�ƶ���ָ��ƥ�䵽һ���ַ�
                b = map.get(key);
                if (b == null) {//˵��û��ƥ�䵽
                    count++;
                } else {
                    //ƥ�䵽
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i ֱ���ƶ��� count
        }
        //��forѭ������������list�оʹ�������е��ַ�  "i like like like java do you like a java"
        //��list �е����ݷ��뵽byte[] ������
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * ��һ���ļ�ѹ��
     *
     * @param srcFile �����ϣ��ѹ�����ļ���ȫ·��
     * @param dstFile ѹ������ѹ���ļ���Ŀ¼
     */
    public static void zipFile(String srcFile, String dstFile) {

        //���������
        OutputStream os = null;
        //�������������
        ObjectOutputStream oos = null;
        //�����ļ���������
        FileInputStream is = null;
        try {
            //�����ļ���������
            is = new FileInputStream(srcFile);
            //����һ����Դ�ļ���Сһ����byte[]
            byte[] b = new byte[is.available()];
            //��ȡ�ļ�
            is.read(b);
            //ֱ�Ӷ�Դ�ļ�ѹ��
            byte[] huffmanBytes = huffmanZip(b);
            //�����ļ��������, ���ѹ���ļ�
            os = new FileOutputStream(dstFile);
            //����һ�����ļ������������ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //�Ѻշ����������ֽ�����д��ѹ���ļ�
            oos.writeObject(huffmanBytes);
            //���������Զ������ķ�ʽд��շ������룬��Ϊ���Ժ����ǻָ�Դ�ļ�ʱʹ��
            //ע��һ��Ҫ�Ѻշ�������д��ѹ���ļ�
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * ��ɶ�ѹ���ļ��Ľ�ѹ
     *
     * @param zipFile ׼����ѹ���ļ�
     * @param dstFile ���ļ���ѹ���ĸ�·��
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //�����ļ�������
        InputStream is = null;
        //����һ������������
        ObjectInputStream ois = null;
        //�����ļ��������
        OutputStream os = null;
        try {
            //�����ļ�������
            is = new FileInputStream(zipFile);
            //����һ���� is�����Ķ���������
            ois = new ObjectInputStream(is);
            //��ȡbyte����huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //��ȡ�շ��������
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //����
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //��bytes ����д�뵽Ŀ���ļ�
            os = new FileOutputStream(dstFile);
            //д���ݵ� dstFile �ļ�
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }
    }

}


//����node�������ݺ�Ȩֵ
class Node implements Comparable<Node> {
    Byte data; //�������(�ַ�)��������'a' => 97 ' ' => 32
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
