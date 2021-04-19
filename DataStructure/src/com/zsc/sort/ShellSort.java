package com.zsc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 *
 * @author zsc
 * @date 2021/4/17 18:24
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[80];
        for (int i = 0; i < 80; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        shellSort2(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
        System.out.println(Arrays.toString(arr));
    }

    //对交换式希尔排序进行优化->移位法
    public static void shellSort2(int[] arrs) {
        for (int gap = arrs.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arrs.length; i++) {
                int j = i;
                int temp = arrs[j];
                if (arrs[j] < arrs[j - gap]) {
                    while (j - gap >= 0 && temp < arrs[j - gap]) {
                        //移动
                        arrs[j] = arrs[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到了插入的位置
                    arrs[j] = temp;
                }
            }
        }
    }

    public static void shellSort(int[] arrs) {

        int temp = 0;
        for (int gap = arrs.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrs.length; i++) {
                //遍历各组中所有的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，需要交换
                    if (arrs[j] > arrs[j + gap]) {
                        temp = arrs[j];
                        arrs[j] = arrs[j + gap];
                        arrs[j + gap] = temp;
                    }
                }
            }
        }


        /*
        //希尔排序第一轮,将10个数据分成了5组
        for (int i = arrs.length/2; i < arrs.length; i++) {
            //遍历各组中所有的元素
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，需要交换
                if (arrs[j] > arrs[j + 5]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 5];
                    arrs[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮" + Arrays.toString(arrs));

        //希尔排序第一轮,将10个数据分成了5组
        for (int i = 2; i < arrs.length; i++) {
            //遍历各组中所有的元素
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，需要交换
                if (arrs[j] > arrs[j + 2]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 2];
                    arrs[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮" + Arrays.toString(arrs));

        //希尔排序第一轮,将10个数据分成了5组
        for (int i = 1; i < arrs.length; i++) {
            //遍历各组中所有的元素
            for (int j = i - 1; j >= 0; j--) {
                //如果当前元素大于加上步长后的那个元素，需要交换
                if (arrs[j] > arrs[j + 1]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三轮" + Arrays.toString(arrs));
        */
    }

}
