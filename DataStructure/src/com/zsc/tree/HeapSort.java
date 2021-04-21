package com.zsc.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 *
 * @author zsc
 * @date 2021/4/21 9:26
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        //int arr[] = {4, 6, 8, 5, 9};
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        //System.out.println("排序后=" + Arrays.toString(arr));
    }

    //堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println(Arrays.toString(arr));
    }

    //将一个数组(二叉树)，调整成一个大顶堆

    /**
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  //取出当前元素的值，保存在临时变量
        //开始调整
        //1.k = i * 2 + 1指向i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {  //说明左子节点小于右子节点
                k++;    //k就指向右子节点
            }
            if (arr[k] > temp) {    //如果子节点大于父节点
                arr[i] = arr[k];    //把较大的值赋给当前节点
                i = k;  //让i指向k，继续循环比较
            } else {
                break;
            }
        }
        arr[i] = temp;  //将temp放到调整后的位置
    }
}
