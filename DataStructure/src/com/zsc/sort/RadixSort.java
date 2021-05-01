package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
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

        int max = arr[0];    //假设第一个数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素进行排序处理
            //定义一个二维数组，表示10个桶
            int[][] buckets = new int[10][arr.length];

            //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据
            int[] bucketElementCounts = new int[10];

            for (int j = 0; j < arr.length; j++) {
                //取出每个元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                buckets[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < buckets.length; k++) {
                //如果桶中有数据，才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶(即第k个一维数组)
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
