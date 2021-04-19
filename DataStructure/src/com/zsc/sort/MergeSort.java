package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * �鲢����
 *
 * @author zsc
 * @date 2021/4/18 13:12
 */
public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));*/

        int[] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arrs.length];
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        mergeSort(arrs, 0, arrs.length - 1, temp);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //����ݹ���зֽ�
            mergeSort(arr, left, mid, temp);
            //���ҵݹ���зֽ�
            mergeSort(arr, mid + 1, right, temp);
            //���ϲ�
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * �ϲ��ķ���
     *
     * @param arr   �����ԭʼ����
     * @param left  ����������еĳ�ʼ����
     * @param mid   �м�����
     * @param right �ұ�����
     * @param temp  ����ת������
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;   //��ʼ��i������������еĳ�ʼ������
        int j = mid + 1;    //��ʼ��j���ұ��������еĳ�ʼ����
        int t = 0;  //ָ��temp����ĵ�ǰ����

        //�Ȱ��������ߵ����ݰ��չ�����䵽temp���飬ֱ���������ߵ��������У���һ���������Ϊֹ
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //����ʣ�����ݵ�һ�ߵ���������ȫ����䵽temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //��temp�����Ԫ�ؿ�����arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
