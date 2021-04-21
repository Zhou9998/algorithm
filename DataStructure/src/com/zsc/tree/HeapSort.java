package com.zsc.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ������
 *
 * @author zsc
 * @date 2021/4/21 9:26
 */
public class HeapSort {
    public static void main(String[] args) {
        //Ҫ�����������������
        //int arr[] = {4, 6, 8, 5, 9};
        // ����Ҫ��80000�������������
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
        }

        System.out.println("����ǰ");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("����ǰ��ʱ����=" + date1Str);

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("����ǰ��ʱ����=" + date2Str);
        //System.out.println("�����=" + Arrays.toString(arr));
    }

    //������ķ���
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("������");
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            //����
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println(Arrays.toString(arr));
    }

    //��һ������(������)��������һ���󶥶�

    /**
     * @param arr    ������������
     * @param i      ��ʾ��Ҷ�ӽڵ��������е�����
     * @param length ��ʾ�Զ��ٸ�Ԫ�ؼ�������
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  //ȡ����ǰԪ�ص�ֵ����������ʱ����
        //��ʼ����
        //1.k = i * 2 + 1ָ��i�ڵ�����ӽڵ�
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {  //˵�����ӽڵ�С�����ӽڵ�
                k++;    //k��ָ�����ӽڵ�
            }
            if (arr[k] > temp) {    //����ӽڵ���ڸ��ڵ�
                arr[i] = arr[k];    //�ѽϴ��ֵ������ǰ�ڵ�
                i = k;  //��iָ��k������ѭ���Ƚ�
            } else {
                break;
            }
        }
        arr[i] = temp;  //��temp�ŵ��������λ��
    }
}
