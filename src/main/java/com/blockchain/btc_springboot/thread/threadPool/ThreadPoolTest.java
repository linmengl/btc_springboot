package com.blockchain.btc_springboot.thread.threadPool;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ThreadPoolTest {


	public static void main(String[] args) {
		DefaultThreadPool threadPool = new DefaultThreadPool(6);
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
