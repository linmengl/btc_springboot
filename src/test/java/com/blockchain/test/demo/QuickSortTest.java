package com.blockchain.test.demo;

import com.alibaba.fastjson.JSON;

public class QuickSortTest {

	public static void main(String[] args) {
		int[] a = {23,4,34,8,2,7,22,36};
		quickSort(a,0,a.length-1);
		System.out.println(JSON.toJSON(a));
	}

	private static void quickSort(int[] a, int left, int right) {

	}


}
