package com.blockchain.test.arithmetic;

public class BinarySearch {
	public static void main(String[] args) {
		int[] a = {1,2,3,5,6,8,9,10,11};
		int index = binarySerach(a,4);
		System.out.println(index);
	}

	private static int binarySerach(int[] a, int value) {
		int min = 0;
		int max = a.length - 1;
		int mid = -1;
		int i = 0;
		while (min<=max){
			mid = (min+max)/2;
			if (a[mid]>value){
				max = mid - 1;
			}else if (a[mid]<value){
				min = mid+1;
			}else {
				return mid;
			}
			i++;
		}
		System.out.println(i);
		return a[mid] == value ? mid : -1 ;
	}
}
