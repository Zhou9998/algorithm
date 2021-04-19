package com.zsc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author zsc
 * @date 2021/4/16 18:49
 */
public class Queen {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chars = new char[n][n];
        for (char[] c:chars){
            Arrays.fill(c,'.');
        }
        int[] arr = new int[n];
        check(chars, arr, 0, n);
        return res;
    }

    public void check(char[][] chars, int[] arr, int x, int n) {
        if (x == n) {
            List<String> list = new ArrayList<>();
            for (char[] c : chars) {
                list.add(String.valueOf(c));
            }
            res.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[x] = i;
            if (judge(arr, x)) {
                chars[x][i] = 'Q' ;
                check(chars, arr, x + 1, n);
                chars[x][i] = '.' ;
            }


        }
    }

    public boolean judge(int[] arr, int q) {
        for (int i = 0; i < q; i++) {
            if (arr[i] == arr[q] || Math.abs(arr[i] - arr[q]) == Math.abs(i - q)) {
                return false;
            }
        }
        return true;
    }
}
