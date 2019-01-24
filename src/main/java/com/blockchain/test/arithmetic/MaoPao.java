package com.blockchain.test.arithmetic;

import com.alibaba.fastjson.JSON;

public class MaoPao {

    public static void mp(int[] a) {
        int temp, count1 = 0, count2 = 0;
        boolean flag = true;
        int n = a.length;
        for (int i = 0; i < n - 1 && flag; i++) {
            flag = false;
            for (int j = n - 1; j > i; j--) {
                count1++;
                if (a[j - 1] > a[j]) {
                    count2++;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(JSON.toJSON(a));
        System.out.println("-------------------------------------------------------");
    }

    public static void mp2(int[] a) {
        int temp, count1 = 0, count2 = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                count1++;
                if (a[j - 1] > a[j]) {
                    count2++;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(JSON.toJSON(a));
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 72, 9, 11, 2, 6, 93, 4, 66, 7, 70};
//        int[] a = {1, 2, 3, 4, 5,  6, 7, 8, 8,9, 70};
        mp(a);
        int[] b = {1, 3, 72, 9, 11, 2, 6, 93, 4, 66, 7, 70};
        mp2(b);
        int[] c = {1, 3, 72, 9, 11, 2, 6, 93, 4, 66, 7, 70};
        xuanze(c);
//        int[] d = {1, 3, 2, 0};
        int[] d = {1, 3, 72, 9,0, 11, 2, 6, -2, 93, 4, 66, 7, 70};
        insertSortML(d);

    }

    public static void xuanze(int[] a) {
        int temp, count1 = 0, count2 = 0;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                count1++;
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            if (min != i) {
                count2++;
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(JSON.toJSON(a));
        System.out.println("----------------------------------------------");
    }

    public static void insertSort(int[] a) {
        int temp, count1 = 0, count2 = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                temp = a[i];
                int j;
                for (j = i - 1; a[j] > temp && j > 0; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = temp;
            }
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(JSON.toJSON(a));
    }

    public static void insertSortML(int[] a) {
        try {
            int temp, count1 = 0, count2 = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i + 1];
                    int j;
                    for (j = i; j >= 0 && a[j] > temp; j--) {
                        a[j + 1] = a[j];
                    }
                    a[j + 1] = temp;
                }
            }
            System.out.println(JSON.toJSON(a));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
