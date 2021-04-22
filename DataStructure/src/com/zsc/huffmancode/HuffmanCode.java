package com.zsc.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 *
 * @author zsc
 * @date 2021/4/21 19:40
 */
public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
        //String srcFile = "d://src.bmp";
        //String dstFile = "d://src.zip";
        //zipFile(srcFile, dstFile);
        //System.out.println("压缩文件ok~~");

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

        //测试
        getCode(root);
        System.out.println(huffmanCodes);   //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}

        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeBytes));  //[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        */
    }

    /**
     * 二叉树前序遍历
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("该赫夫曼树是空树");
        }
    }
    /**
     * 封装前面所有方法
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);
        //根据nodes创建哈夫曼树
        Node root = createHuffmanTree(nodes);
        //生成对应的哈夫曼编码（根据哈夫曼树）
        Map<Byte, String> huffmanCodes = getCode(root);
        System.out.println(huffmanCodes);
        //根据哈夫曼编码对原始数组进行压缩
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }
    /**
     * 生成二叉树的节点
     *
     * @param bytes
     * @return 存储节点的链表
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //存储每一个byte出现的次数->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //说明Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个Node对象并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 通过一个list，创建对应的哈夫曼树
     *
     * @param nodes
     * @return 哈夫曼树的根节点
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //取出
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //最后的节点就是Huffman的根节点
        return nodes.get(0);
    }

    //1.将哈夫曼编码表存放在Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生成哈夫曼编码表时，需要去拼接路径，定义一个StringBuilder，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：得到所传入的node节点的所有叶子节点的哈夫曼编码，并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径：左子节点：0 右子节点：1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        //将传入的code加入到builder
        builder.append(code);
        if (node != null) {    //不处理
            //判断当前node是叶子节点还是非叶子结点
            if (node.data == null) { //非叶子节点
                //向左递归
                getCode(node.left, "0", builder);
                //向右递归
                getCode(node.right, "1", builder);
            } else {
                //表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    /**
     * 为了调用方便，重载getCode
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
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000(原码)= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));  //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        }
        //将字符串转成byte[]
        //统计返回byte[] huffmanCodeBytes的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {   //每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {    //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2. 赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"
    /**
     * 将byte转成一个二进制的字符串
     *
     * @param b    传入的byte
     * @param flag 是否需要补高位，如果是true表示需要补高位
     * @return byte对应的二进制的字符串，按补码格式返回
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用一个变量保存b
        int temp = b;
        //如果是正数，补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);  //返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 完成对数据的解码
     *
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes对应的二进制的字符串，形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个文件压缩
     *
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dstFile 压缩后存放压缩文件的目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        //创建对象输出流
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
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
     * 完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和 is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
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


//创建node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; //存放数据(字符)本身，比如'a' => 97 ' ' => 32
    int weight; //权值, 表示字符出现的次数
    Node left;//
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

    //前序遍历
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
