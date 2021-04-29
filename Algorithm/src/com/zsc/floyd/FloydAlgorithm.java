package com.zsc.floyd;

import java.util.Arrays;

/**
 * ���������㷨
 *
 * @author zsc
 * @date 2021/4/28 20:03
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        // ���Կ���ͼ�Ƿ񴴽��ɹ�
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //�����ڽӾ���
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //���� Graph ����
        Graph graph = new Graph(vertex, matrix, vertex.length);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex;  //��Ŷ���
    private int[][] dis;    //����Ӹ��������������������ľ���
    private int[][] pre;    //���浽��Ŀ�궥���ǰ������

    public Graph(char[] vertex, int[][] matrix, int length) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //��pre�����ʼ������ŵ���ǰ��������±�
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //��ʾpre�����dis����
    public void show() {
        //Ϊ����ʾ�����Ķ��������Ż�һ�����
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            // �Ƚ�pre���������һ��
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // ���dis�����һ������
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "��" + vertex[i] + "�����·����" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();

        }

    }

    public void floyd() {
        int len = 0;
        //�м䶥��
        for (int i = 0; i < dis.length; i++) {
            //��������
            for (int j = 0; j < dis.length; j++) {
                //���ﶥ��
                for (int k = 0; k < dis.length; k++) {
                    len = dis[j][i] + dis[i][k];
                    if (dis[j][k] > len) {
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }

}