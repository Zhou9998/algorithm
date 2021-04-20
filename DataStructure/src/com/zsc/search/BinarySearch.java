package com.zsc.search;

import java.util.ArrayList;

/**
 * ���ֲ���
 * ע�⣺ʹ�ö��ֲ��ҵ�ǰ��������Ҫ�������
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
     * ���ֲ����㷨
     *
     * @param arr     ����
     * @param left    �������
     * @param right   �ұ�����
     * @param findVal �ҵ��ͷ����±꣬û���ҵ��ͷ���-1
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //��left>rightʱ��˵���ݹ��������飬����û���ҵ�
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {  //���ҵݹ�
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //����ݹ�
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //��left>rightʱ��˵���ݹ��������飬����û���ҵ�
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {  //���ҵݹ�
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //����ݹ�
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //��mid����ֵ�����ɨ�裬�������������ֵԪ�ص��±꣬���뵽ArrayList
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                //����Ͱ�temp���뵽������
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                //����Ͱ�temp���뵽������
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }

    }
}