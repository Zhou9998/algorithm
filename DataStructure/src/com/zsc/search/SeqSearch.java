package com.zsc.search;

/**
 * ���Բ���
 *
 * @author zsc
 * @date 2021/4/19 9:25
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr,11);
        if (index == -1){
            System.out.println("û���ҵ�");
        }else {
            System.out.println("�±�Ϊ"+index);
        }
    }

    /**
     * �ҵ�һ������������ֵ���ͷ���
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
