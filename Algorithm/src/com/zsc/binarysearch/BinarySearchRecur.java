package com.zsc.binarysearch;

/**
 * �ǵݹ���ֲ����㷨
 *
 * @author zsc
 * @date 2021/4/25 15:47
 */
public class BinarySearchRecur {
    public static void main(String[] args) {
        //����
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println(index);
    }

    /**
     * ���ֲ��ҵķǵݹ�ʵ��
     *
     * @param arr    �����ҵ�����
     * @param target ��Ҫ���ҵ���
     * @return ���ض�Ӧ�±꣬-1��ʾû���ҵ�
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
