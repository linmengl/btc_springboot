package com.blockchain.test.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class Recusion {

    public static void main(String[] args) {
        System.out.println((2 + 3) >> 1);

        int[] arr = {1, 2, 3, 11, 15, 16, 4, 5, 6};
        int sum = sum(arr, arr.length);
        System.out.println(sum);
        int sum2 = sum2(arr, 0, arr.length - 1);
        System.out.println(sum2);

        max2(arr, 0, arr.length - 1);
        max22(arr, 0, arr.length - 1);

    }

    private static void max2(int[] arr, int lo, int hi) {
        int x1;
        int x2;
        int temp;
        if (arr[x1 = lo] < arr[x2 = (lo + 1)]) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }
        for (int a = lo + 2; a < hi; a++) {
            if (arr[x2] < arr[a]) {
                if (arr[x1] < arr[x2 = a]) {
                    temp = x1;
                    x1 = x2;
                    x2 = temp;
                }
            }
        }
        System.out.println(arr[x1]);
        System.out.println(arr[x2]);
    }

    private static Map<String, String> max22(int[] arr, int lo, int hi) {
        int x1;//最大
        int x2;//次大
        int temp;
        if (lo + 2 == hi) {
            Map<String, String> map = new HashMap<>();
            x1 = lo;
            x2 = lo + 1;

            if (arr[x1] < arr[x2]) {
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (arr[x2] < arr[hi]) {
                if (arr[x1] < arr[x2 = hi]) {
                    temp = x1;
                    x1 = x2;
                    x2 = temp;
                }
            }
            map.put("x1", String.valueOf(x1));
            map.put("x2", String.valueOf(x2));
            return map;
        }
        if (lo + 3 == hi) {
            Map<String, String> map = new HashMap<>();
            x1 = lo;
            x2 = lo + 1;
            if (arr[x1] < arr[x2]) {
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            for (int i=lo+2;i<hi;i++) {
                if (arr[x2] < arr[i]) {
                    if (arr[x1] < arr[x2 = i]) {
                        temp = x1;
                        x1 = x2;
                        x2 = temp;
                    }
                }
            }
            map.put("x1", String.valueOf(x1));
            map.put("x2", String.valueOf(x2));
            return map;
        }
        int mi = (hi + lo) >> 1;
        Map<String, String> le = max22(arr, lo, mi);
        Map<String, String> ri = max22(arr, mi + 1, hi);
        int x1L = Integer.parseInt(le.get("x1"));
        int x2L = Integer.parseInt(le.get("x2"));
        int x1R = Integer.parseInt(ri.get("x1"));
        int x2R = Integer.parseInt(ri.get("x2"));
        if (arr[x1L] > arr[x1R]) {
            x1 = x1L;
            x2 = x2L > x1R ? x2L : x1R;
        } else {
            x1 = x1R;
            x2 = x2R > x1L ? x2R : x1L;
        }
        Map<String, String> map = new HashMap<>();
        map.put("x1", String.valueOf(x1));
        map.put("x2", String.valueOf(x2));
        System.out.println(x1);
        System.out.println(x2);
        return map;
    }


    public static int sum(int[] arr, int n) {
        return n <= 0 ? 0 : sum(arr, n - 1) + arr[n - 1];
    }

    private static int sum2(int[] arr, int low, int hi) {
        if (low == hi) {
            return arr[low];
        }
        int mi = (low + hi) >> 1;
        return sum2(arr, low, mi) + sum2(arr, mi + 1, hi);
    }
}
