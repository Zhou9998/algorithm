package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 *
 * @author zsc
 * @date 2021/4/17 14:27
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arrs = {3, 9, -1, 10, -2};
        //测试冒泡排序的速度
        int[] arrs = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arrs[i] = (int) (Math.random() * 80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        bubbleSort(arrs);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);

    }


    //将冒泡排序算法封装成一个方法
    public static void bubbleSort(int[] arrs) {
        int tem = 0;
        for (int i = 1; i < arrs.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arrs.length - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    flag = true;
                    tem = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = tem;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
