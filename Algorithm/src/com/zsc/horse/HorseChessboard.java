package com.zsc.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * ��ʿ����
 *
 * @author zsc
 * @date 2021/4/29 10:00
 */
public class HorseChessboard {
    private static int X;   //���̵�����
    private static int Y;   //���̵�����
    //����һ�����飬������̵ĸ���λ���Ƿ񱻷��ʹ�
    private static boolean[] visited;
    //ʹ��һ�����ԣ�����Ƿ����̵�����λ�ö������ʹ�
    private static boolean finished;    //���true����ʾ�ɹ�

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;    //�����ʼλ�õ��У���1��ʼ���
        int col = 1;    //�����ʼλ�õ��У���1��ʼ���
        //��������
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];   //��ʼֵ����false
        //����һ�º�ʱ
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, col - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //������̵�������
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * �����ʵ����������㷨
     *
     * @param chessboard
     * @param row        �����ǰλ�õ��� ��0��ʼ
     * @param col        �����ǰλ�õ��� ��0��ʼ
     * @param step       �ǵڼ�������ʼλ�þ��ǵ�һ��
     */
    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;
        visited[row * X + col] = true;  //��Ǹ�λ���Ѿ�������
        //��ȡ��ǰλ�ÿ����ߵ���һ��λ�õļ���
        ArrayList<Point> ps = next(new Point(col, row));
        //��ps������������Ĺ�����Ƕ�ps������Point�������һ����λ�õ���Ŀ�����зǵݼ�����
        sort(ps);
        //����ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); //ȡ����һ�������ߵ�λ��
            //�жϸõ��Ƿ��Ѿ����ʹ�
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //�ж�����Ƿ����������ʹ��   step ��Ӧ���ߵĲ����Ƚ� ��
        //���û�дﵽ���������ʾû��������񣬽�����������0
        //˵��: step < X * Y  ���������������
        //1. ���̵�Ŀǰλ��,��Ȼû������
        //2. ���̴���һ�����ݹ���
        if (step < X * Y && !finished) {
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    /**
     * ���ݵ�ǰλ�ã����������������Щλ��(Point)�������뵽һ������(ArrayList)�У������8��λ��
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //����һ��ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        //����һ��Point
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //�ж����������6���λ��
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //�ж����������7���λ��
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //�ж����������0���λ��
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //�ж����������1���λ��
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //�ж����������2���λ��
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //�ж����������3���λ��
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //�ж����������4���λ��
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //���ݵ�ǰ��һ������һ������ѡ��λ�ã����зǵݼ�����
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //��ȡ��o1����һ��������λ�ø���
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
