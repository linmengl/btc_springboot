package com.blockchain.btc_springboot.demo.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest1 {
	static CountDownLatch c = new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				c.countDown();
			}
		}).start();
		c.await();
		System.out.println(3);
	}
}
