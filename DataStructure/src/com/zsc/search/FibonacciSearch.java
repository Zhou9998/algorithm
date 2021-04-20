package com.zsc.search;

import java.util.Arrays;

/**
 * 斐波那契算法
 *
 * @author zsc
 * @date 2021/4/19 14:06
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 89);
        System.out.println(index);
        System.out.println(Arrays.toString(fib()));//[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
    }

    //因为后面mid = low + F(k-1)-1，因此需要先获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契算法(非递归)
     *
     * @param a   数组
     * @param key 我们需要查找的关键值
     * @return
     */
    //斐波那契查找算法
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;  //表示斐波那契分割数值的下标
        int mid = 0;    //存放mid值
        int[] f = fib();    //获取斐波那契数列
        //获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;    // k = 5 f[k] = 8
        }
        //因为f[k]可能大于数组的长度，引出需要使用Arrays的类，构造一个新的数组，并指向temp[]
        int[] temp = Arrays.copyOf(a, f[k]);
        //需要对a数组的最后一个数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //temp = {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        //使用while来循环处理，找到key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //说明应该继续向数组的前面查找
                high = mid - 1;
                //1.全部元素=前面元素+后面元素
                //2.f[k] = f[k-1]+f[k-2]
                k--;
            } else if (key > temp[mid]) { //说明应该继续向数组的后面查找
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
