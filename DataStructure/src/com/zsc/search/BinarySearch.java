package com.zsc.search;

import java.util.ArrayList;

/**
 * 二分查找
 * 注意：使用二分查找的前提是数组要是有序的
 *
 * @author zsc
 * @date 2021/4/19 9:46
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000,1000,1000, 1234};
        ArrayList<Integer> resIndex = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(resIndex);
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 找到就返回下标，没有找到就返回-1
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {  //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {  //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //想mid索引值的左边扫描，将所有满足查找值元素的下标，加入到ArrayList
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                //否则就把temp放入到集合中
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                //否则就把temp放入到集合中
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }

    }
}