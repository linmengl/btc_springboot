package com.blockchain.test.offer;

public class Offer1 {
	private static void print(int a[][], int N) {
		int x = 0;//代表左上角坐标
		int y = N - 1;//代表右上角坐标
		while (x < y) {
			for (int k = x; k < y; k++) {
				System.out.print(a[x][k] + " ");
			}
			for (int k = x; k < y; k++) {
				System.out.print(a[k][y] + " ");
			}
			for (int k = y; k > x; k--) {
				System.out.print(a[y][k] + " ");
			}
			for (int k = y; k > x; k--) {
				System.out.print(a[k][x] + " ");
			}
			x++;
			y--;
		}
		if (x == y) {
			System.out.print(a[x][x]);
		}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//int N = 4;
		//int a[][] = new int[N][N];
		//for (int i = 0; i < N; i++) {
		//	for (int j = 0; j < N; j++) {
		//		a[i][j] = i * N + j + 1;
		//	}
		//}
		//print(a,N);
		System.out.println(8&7);
	}
}
