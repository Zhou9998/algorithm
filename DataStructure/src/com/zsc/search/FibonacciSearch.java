package com.zsc.search;

import java.util.Arrays;

/**
 * 쳲������㷨
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

    //��Ϊ����mid = low + F(k-1)-1�������Ҫ�Ȼ�ȡһ��쳲���������
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
     * 쳲������㷨(�ǵݹ�)
     *
     * @param a   ����
     * @param key ������Ҫ���ҵĹؼ�ֵ
     * @return
     */
    //쳲����������㷨
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;  //��ʾ쳲������ָ���ֵ���±�
        int mid = 0;    //���midֵ
        int[] f = fib();    //��ȡ쳲���������
        //��ȡ쳲������ָ���ֵ���±�
        while (high > f[k] - 1) {
            k++;    // k = 5 f[k] = 8
        }
        //��Ϊf[k]���ܴ�������ĳ��ȣ�������Ҫʹ��Arrays���࣬����һ���µ����飬��ָ��temp[]
        int[] temp = Arrays.copyOf(a, f[k]);
        //��Ҫ��a��������һ�������temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //temp = {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        //ʹ��while��ѭ�������ҵ�key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //˵��Ӧ�ü����������ǰ�����
                high = mid - 1;
                //1.ȫ��Ԫ��=ǰ��Ԫ��+����Ԫ��
                //2.f[k] = f[k-1]+f[k-2]
                k--;
            } else if (key > temp[mid]) { //˵��Ӧ�ü���������ĺ������
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
