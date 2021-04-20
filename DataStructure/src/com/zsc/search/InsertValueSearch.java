package com.zsc.search;

/**
 * 插值算法
 *
 * @author zsc
 * @date 2021/4/19 11:04
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);
    }

    //要求数组有序
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue) {  //向右边递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        }else {
            return mid;
        }
    }
}
