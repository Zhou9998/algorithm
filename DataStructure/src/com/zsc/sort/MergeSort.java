package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序
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
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //到合并
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;   //初始化i，左边有序序列的初始索引，
        int j = mid + 1;    //初始化j，右边有序序列的初始索引
        int t = 0;  //指向temp数组的当前索引

        //先把左右两边的数据按照规则填充到temp数组，直到左右两边的有序序列，有一方处理完毕为止
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
        //把有剩余数据的一边的数据依次全部填充到temp
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
        //将temp数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
