package com.zsc.dac;

import java.security.PublicKey;

/**
 * ��ŵ��
 *
 * @author zsc
 * @date 2021/4/25 16:21
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    //��ŵ�����ƶ�����
    public static void hanoiTower(int num, char a, char b, char c) {
        //���ֻ��һ����
        if (num == 1) {
            System.out.println("��1���̴�" + a + "->" + c);
        } else {
            //����� n >= 2 ������������ǿ��Կ����������� 1.���±ߵ��� 2.�������
            //1.�Ȱ��������������A��>B���ƶ����̻�ʹ�õ�c��
            hanoiTower(num - 1, a, c, b);
            //2.�����������A->C
            System.out.println("��" + num + "���̴�" + a + "->" + c);
            //3.��B�����е��̴�B->C,�ƶ�����ʹ�õ�a��
            hanoiTower(num - 1, b, a, c);
        }
    }
}
