package com.blockchain.test.demo;

import com.alibaba.fastjson.JSON;

public class QuickSort2 {

	public static void main(String[] args) {
		int[] a = {2, 4, 3, 688, 34, 5, 67, 9,39,98,22,1,0,-9};
		//quickSort(a, 0, a.length - 1);
		//maopao2(a);
		xuanze(a);
		System.out.println(JSON.toJSON(a));
	}

	private static void quickSort(int[] a, int left, int right) {
		if (left >= right) {
			return;
		}
		int i = left;
		int j = right;
		int key = a[left];
		int temp;

		while (i < j) {
			while (a[j] >= key && i < j) {
				j--;
			}
			while (a[i] <= key && i < j) {
				i++;
			}
			if (i<j){
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}

		a[left] = a[i];
		a[i] = key;
		quickSort(a,left,i-1);
		quickSort(a,i+1,right);
	}
	private static void maopao2(int[] a){
		int temp;
		boolean flag = true;
		for (int i = 0;i<a.length-1&&flag;i++){
			flag = false;
			for (int j = a.length-1;j>i;j--){
				if (a[j]<a[j-1]){
					temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
					flag = true;
				}
			}
		}
	}

	private static void xuanze(int[] a) {
		int temp;
		int min;
		for (int x = 0; x < a.length - 1; x++) {
			min = x;
			for (int y = x + 1; y < a.length; y++) {
				if (a[x] > a[y]) {
					min = y;
				}
			}
			if (min != x){
				temp = a[x];
				a[x] = a[min];
				a[min] = temp;
			}
		}
	}





}
