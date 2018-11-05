package com.blockchain.test.demo.thread.proxy.example;

import java.util.Random;

public class Birds implements Flyable{
	@Override
	public void fly() {
		long start = System.currentTimeMillis();
		System.out.println("Bird is flying...");
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Fly time = " + (end - start));
	}

}
