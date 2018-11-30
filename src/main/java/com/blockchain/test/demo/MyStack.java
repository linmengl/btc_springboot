package com.blockchain.test.demo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MyStack {

	public static void main(String[] args) {
		//Stack<String> stack = new Stack<>();
		//stack.push("a");
		//stack.push("b");
		//stack.push("c");
		//stack.push("d");
		//for (int a= 0;a<6;a++) {
		//	String pop = stack.pop();
		//	System.out.println(pop);
		//}
		//Map<String,String> map = new HashMap<>();
		//int i = 0;
		//while (true){
		//	map.put("a"+(i++),"b");
		//}

		//int res = fbnq(12);
		//System.out.println(res);
		int[] a = {46, 30, 82, 90, 56, 17, 95, 15};
		quick(a,0,a.length-1);
		System.out.println(JSON.toJSON(a));

	}

	public static int fbnq(int a){
		if (a <= 2){
			return 1;
		}
		return fbnq(a -1)+fbnq(a-2);
	}


	private static void quick(int[] a,int left,int right){
		if (left >= right){
			return;
		}

		int key = a[left];
		int i = left;
		int j = right;
		int temp;
		if (i <= j){
			while (a[j] >= key && i<j){
				j--;
			}

			while (a[i] <= key && i<j){
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
		quick(a,0,i-1);
		quick(a,i+1,right);
	}
}
