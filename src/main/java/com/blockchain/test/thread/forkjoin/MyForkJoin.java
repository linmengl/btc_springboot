package com.blockchain.test.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyForkJoin extends RecursiveTask<Integer> {

	//阈值
	private static final int THRESHOLD = 2;

	private int[] a;
	private int start;
	private int end;

	public MyForkJoin(int[] a, int start, int end) {
		this.a = a;
		this.start = start;
		this.end = end;
	}


	@Override
	public Integer compute() {
		int sum = 0;
		if ((end - start) <= THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += a[i];
			}
		} else {
			int middle = (start + end) / 2;
			MyForkJoin left = new MyForkJoin(a, start, middle);
			MyForkJoin right = new MyForkJoin(a, middle + 1, end);
			left.fork();
			right.fork();
			int leResult = left.join();
			int riResult = right.join();
			sum = leResult + riResult;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 34, 45, 56};
		ForkJoinPool joinPool = new ForkJoinPool();
		MyForkJoin myForkJoin = new MyForkJoin(a, 0, a.length - 1);
		ForkJoinTask<Integer> submit = joinPool.submit(myForkJoin);
		try {
			System.out.println(submit.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
