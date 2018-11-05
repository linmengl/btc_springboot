package com.blockchain.test.demo;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ClQTest1 {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");

		String q1 = queue.poll();
		String q2 = queue.poll();
		String q3 = queue.poll();
		System.out.println("kk");
	}


}



