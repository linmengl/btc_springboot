package com.blockchain.btc_springboot.demo.thread.threadPool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadPoolTest {


	public static void main(String[] args) {
		DefaultThreadPool<AA> threadPool = new DefaultThreadPool<AA>(6);
		try {
			//Scanner scanner = new Scanner(System.i)
			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				threadPool.execute(new AA(br.readLine()));
			}
		}catch (Exception e) {

		}

	}
}

class AA implements Runnable {
	private String name;

	public AA(String s){
		this.name = s;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(name);
		}catch (Exception e) {
			System.out.println("exception");
		}
		System.out.println(Thread.currentThread().getName());

	}

	@Override
	public String toString() {
		return "AA{" +
				"name='" + name + '\'' +
				'}';
	}
}
