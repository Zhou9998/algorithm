package com.zsc.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 快速排序
 *
 * @author zsc
 * @date 2021/4/17 19:38
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, 10};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;   //左下标
        int r = right;  //右下标
        int pivot = arr[(left + right) / 2];   //中值
        int temp = 0;
        //比pivot值大放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于pivot的值，才推出
            while (arr[r] > pivot) {
                r -= 1;
            }

            //如果l>=r 说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值相等 r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值相等 l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r，必须l++,r--否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
    /**
     * 左右指针法
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int temp = arr[l], i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= temp) {
                j--;
            }
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[l] = arr[i];
        arr[i] = temp;
        quickSort2(arr, l, i - 1);
        quickSort2(arr, j + 1, r);
    }

    /**
     * 填坑法
     *
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort3(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right, key = array[l];
        while (l < r) {
            while (l < r && array[r] >= key) {
                r--;
            }
            array[l] = array[r];
            while (l < r && array[l] <= key) {
                l++;
            }
            array[r] = array[l];
        }
        array[l] = key;
        quickSort3(array, left, l - 1);
        quickSort3(array, l + 1, right);
    }
}

