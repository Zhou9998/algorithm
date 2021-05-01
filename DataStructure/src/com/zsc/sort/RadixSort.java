package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ��������
 *
 * @author zsc
 * @date 2021/4/18 17:07
 */
public class RadixSort {
    public static void main(String[] args) {
        /*
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
        */
        int[] arrs = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        radixSort(arrs);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }

    public static void radixSort(int[] arr) {

        int max = arr[0];    //�����һ�������������
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //�õ�������Ǽ�λ��
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //���ÿ��Ԫ�ؽ���������
            //����һ����ά���飬��ʾ10��Ͱ
            int[][] buckets = new int[10][arr.length];

            //Ϊ�˼�¼ÿ��Ͱ�У�ʵ�ʴ���˶��ٸ����ݣ�����һ��һά��������¼����Ͱÿ�η��������
            int[] bucketElementCounts = new int[10];

            for (int j = 0; j < arr.length; j++) {
                //ȡ��ÿ��Ԫ�ض�Ӧλ��ֵ
                int digitOfElement = arr[j] / n % 10;
                //���뵽��Ӧ��Ͱ��
                buckets[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < buckets.length; k++) {
                //���Ͱ�������ݣ��ŷ��뵽ԭ����
                if (bucketElementCounts[k] != 0) {
                    //ѭ����Ͱ(����k��һά����)
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = buckets[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
}
