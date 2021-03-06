package com.blockchain.test.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinCalculator implements Calculator {
	private ForkJoinPool pool;

	private static class SumTask extends RecursiveTask<Long> {
		private long[] numbers;
		private int from;
		private int to;

		public SumTask(long[] numbers, int from, int to) {
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}

		@Override
		protected Long compute() {
			try{
				Thread.sleep(1000);
			}catch (Exception e){

			}
			// 当需要计算的数字小于6时，直接计算结果
			if (to - from < 6) {
				long total = 0;
				for (int i = from; i <= to; i++) {
					total += numbers[i];
				}
				return total;
				// 否则，把任务一分为二，递归计算
			} else {
				int middle = (from + to) / 2;
				SumTask taskLeft = new SumTask(numbers, from, middle);
				SumTask taskRight = new SumTask(numbers, middle + 1, to);
				taskLeft.fork();
				taskRight.fork();
				return taskLeft.join() + taskRight.join();
			}
		}
	}

	public ForkJoinCalculator() {
		// 也可以使用公用的 ForkJoinPool：
		// pool = ForkJoinPool.commonPool()
		pool = new ForkJoinPool();
	}

	@Override
	public long sumUp(long[] numbers) {
		return pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
	}

	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(1,10000000).toArray();


		long c = System.currentTimeMillis();
		ExecutorServiceCalculator calculator2 = new ExecutorServiceCalculator();
		Long sum = calculator2.sumUp(numbers);
		System.out.println(System.currentTimeMillis() - c);
		System.out.println(sum);

		long b = System.currentTimeMillis();
		ForkJoinCalculator calculator = new ForkJoinCalculator();
		long a = calculator.sumUp(numbers);
		System.out.println(System.currentTimeMillis() - b);
		System.out.println(a);
	}
}
