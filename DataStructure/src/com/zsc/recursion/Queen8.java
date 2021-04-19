package com.zsc.recursion;

/**
 * 八皇后问题
 *
 * @author zsc
 * @date 2021/4/16 17:26
 */
public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    static int count = 0;
    //定义数组array，保存皇后放置位置的结果
    int[] arr = new int[max];

    public static void main(String[] args) {
        //测试
        Queen8 Queue = new Queen8();
        Queue.check(0);
        System.out.println("一共有多少" + count + "种解法");
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) {
            print(arr);
            return;
        }
        //依次放入皇后并查看是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后n放到该行的第一列
            arr[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //接着放n+1个皇后；
                check(n + 1);
            }
        }
    }


    //查看当我们放置第n个皇后，就去检测该皇后是否与前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //Math.abs(n-i) == Math.abs(arr[n] -arr[i] 参考 y=kx 斜率
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }


    //将皇后摆放的位置打印出来
    private void print(int[] arr) {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
