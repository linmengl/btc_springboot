package com.blockchain.btc_springboot.thread;

import java.util.concurrent.TimeUnit;

/**
 * 创建了10个线程，编号0~9，每个线程调用前一个线程的join()方法，也就是线程0结束了，线程1才能从join()方法中返回,主线程最后结束
 */
public class Join {
	public static void main(String[] args) throws Exception {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			// 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
			Thread thread = new Thread(new Domino(previous), String.valueOf(i));
			thread.start();
			previous = thread;
		}
		previous.join();
		//TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + " terminate.");
	}

	static class Domino implements Runnable {
		private Thread thread;

		public Domino(Thread thread) {
			this.thread = thread;
		}

		public void run() {
			try {
				if (!thread.getName().equals("main")) {
					thread.join();
				}
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + " terminate.");
		}
	}
}
