package com.blockchain.btc_springboot.controller;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.SuspendableCallable;
import com.blockchain.btc_springboot.bean.HttpClientResult;
import com.blockchain.btc_springboot.util.HttpClientUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FiberTest {
	static int count = 2000;

	static AtomicInteger fail = new AtomicInteger(0);
	static AtomicInteger c = new AtomicInteger(0);
	static CountDownLatch latch = new CountDownLatch(count);
	static CountDownLatch latch2 = new CountDownLatch(1);
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2000);
		long start = System.currentTimeMillis();
		//testFiber();
		for (int a = 0; a < count; a ++) {
			//new Fiber<Void>(new SuspendableCallable<Void>() {
			//	@Override
			//	public Void run() throws SuspendExecution, InterruptedException {
			//		try {
			//			//latch2.await();
			//			//System.out.println("haha");
			//			System.out.println(a+1);
			//			HttpClientUtils.doPost("http://127.0.0.1:8081/fiber/a/a.c");
			//
			//		} catch (Exception e) {
			//			fail.incrementAndGet();
			//		}finally {
			//			latch.countDown();
			//		}
			//		return null;
			//	}
			//}).start();
			System.out.println(a+1);
			//new Thread(() -> {
			executor.execute(() -> {
				try {
					latch2.await();
					HttpClientResult res = HttpClientUtils.doPost("http://127.0.0.1:8081/fiber/a/a.c");
				} catch (Exception e) {
					fail.incrementAndGet();
				}finally {
					latch.countDown();
				}
			});

		}
		try {
			latch2.countDown();
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("执行请求"+count+"次，耗时="+cost+"ms,失败数="+fail);

	}

	@Suspendable
	public static void testFiber(){

	}

}
