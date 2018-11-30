package com.blockchain.test.arithmetic;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class QuickSort {

	private static int FM = 0;
	private static int M = 0;


	/**
	 * 46 30 82 90 56 17 95 15
	 *
	 * @param a
	 * @param left
	 * @param right
	 */

	private static void quicksort(int[] a, int left, int right) {
		//如果left等于right，即数组只有一个元素，直接返回
		if (left >= right) {
			return;
		}
		//设置最左边的元素为基准值
		int key = a[left];
		//数组中比key小的放在左边，比key大的放在右边，key值下标为i
		int i = left;
		int j = right;
		while (i < j) {
			//j向左移，直到遇到比key小的值
			while (a[j] >= key && i < j) {
				j--;
			}
			//i向右移，直到遇到比key大的值
			while (a[i] <= key && i < j) {
				i++;
			}
			//i和j指向的元素交换
			if (i < j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		a[left] = a[i];
		a[i] = key;
		quicksort(a, left, i - 1);
		quicksort(a, i + 1, right);
	}


	public static void main(String[] args) {
		int[] a = {46, 30, 82, 90, 56, 17, 95, 15};
		//long l = System.currentTimeMillis();
		//quicksort(a, 0, a.length - 1);
		//System.out.println("quickSort………………" + FM);
		//System.out.println("耗时…………" + (System.currentTimeMillis() - l) + "," + JSON.toJSON(a));
		//int[] b = {46, 30, 82, 90, 56, 17, 95, 15};
		//long l2 = System.currentTimeMillis();
		//quicksortM(b, 0, b.length - 1);
		//System.out.println("quickSort………………" + M);
		//System.out.println("耗时…………" + (System.currentTimeMillis() - l2) + "," + JSON.toJSON(b));
		//maoPao(a);
		//xuanze(a);
		quickSort(a,0,a.length-1);
		System.out.println(JSON.toJSON(a));
		System.out.println(M);
	}

	private static int MP = 0;

	private static void maoPao(int[] a) {
		int temp;
		for (int ii = 0; ii < a.length - 1; ii++) {
			for (int i = 0; i < a.length - 1 - ii; i++) {
				MP++;
				if (a[i] > a[i + 1]) {
					temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
				}
			}
		}
		System.out.println("maopao次数………………" + MP + "," + JSON.toJSON(a));
	}

	private static void xuanze(int[] a) {
		int temp;
		for (int x = 0; x < a.length - 1; x++) {
			for (int y = x + 1; y < a.length; y++) {
				if (a[x] > a[y]) {
					temp = a[x];
					a[x] = a[y];
					a[y] = temp;
				}
			}
		}
	}

	private static void quickSort(int[] a, int left, int right){
		if (left >= right){
			return;
		}
		int temp;
		int key = a[left];
		int i = left;
		int j = right;
		while (i < j){
			M++;
			while (a[j] >= key && i < j){
				j--;
			}
			while (a[i] <= key && i < j){
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
}
