package com.blockchain.test.demo.string;

public class Compare {
	public static void main(String[] args) {
		String a = "happyNewYear";
		String b = "Year";
		int pos = 1;
		int i = index(a,b,pos);
		System.out.println(i==-1?"不存在":i);
		int j = index2(a,b,pos);
		System.out.println(j==-1?"不存在":i);

	}

	private static int index(String a, String b, int pos) {
		int i;
		String sub;
		i = pos;
		while (i < a.length() - b.length()+1){
			sub = a.substring(i,b.length()+i);
			if (!sub.equals(b)){
				i++;
			}else {
				return i;
			}
		}
		return -1;
	}

	private static int index2(String a, String b, int pos){
		int i = pos,j = 0;
		int aLength = a.length();
		int bLength = b.length();
		while (i<=aLength && j<=bLength){
			if (a.indexOf(i)==b.indexOf(j)){
				i++;
				j++;
			}else {
				i = i-j+2;
			}
		}
		if (j>bLength){
			return i-bLength;
		}
		return -1;
	}
}
