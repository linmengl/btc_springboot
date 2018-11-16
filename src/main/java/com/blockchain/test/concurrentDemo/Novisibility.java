package com.blockchain.test.concurrentDemo;

public class Novisibility {
	private static boolean ready;
	private static int number;

	private static class ReaderThread implements Runnable{

		@Override
		public void run() {
			while (!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		new Thread(new ReaderThread()).start();
		System.out.println("main");
		number = 42;
		ready = true;
	}
}
