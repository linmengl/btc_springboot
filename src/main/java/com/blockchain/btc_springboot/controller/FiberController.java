package com.blockchain.btc_springboot.controller;

import com.blockchain.btc_springboot.bean.HttpClientResult;
import com.blockchain.btc_springboot.util.HttpClientUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/fiber")
public class FiberController {

	static int count = 2000;
	static AtomicInteger fail = new AtomicInteger(0);
	static CyclicBarrier barrier = new CyclicBarrier(count);
	static CyclicBarrier barrier2 = new CyclicBarrier(1);
	//static CountDownLatch latch2 = new CountDownLatch(1);

	static ExecutorService executor = Executors.newFixedThreadPool(2000);

	@RequestMapping("/a")
	public void fiber() {
		CountDownLatch latch = new CountDownLatch(count);
		long start = System.currentTimeMillis();
		//testFiber();
		for (int a = 0; a < count; a++) {
			System.out.println(a + 1);
			executor.execute(() -> {
				try {
					barrier.await();
					HttpClientResult res = HttpClientUtils.doPost("http://127.0.0.1:8081/fiber/a/a.c");
				} catch (Exception e) {
					fail.incrementAndGet();
				} finally {

					latch.countDown();
				}
			});

		}
		try {
			//latch2.countDown();
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("fiber执行请求" + count + "次，耗时=" + cost + "ms,失败数=" + fail);

	}


	@RequestMapping("/a2")
	public void thread() {
		CountDownLatch latch = new CountDownLatch(count);
		long start = System.currentTimeMillis();
		//testFiber();
		for (int a = 0; a < count; a++) {
			System.out.println(a + 1);
			executor.execute(() -> {
				try {
					//latch2.await();
					barrier.await();
					HttpClientResult res = HttpClientUtils.doPost("http://127.0.0.1:8081/fiber/a/a3.c");
				} catch (Exception e) {
					fail.incrementAndGet();
				} finally {

					latch.countDown();
				}
			});

		}
		try {
			//latch2.countDown();
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("thread执行请求" + count + "次，耗时=" + cost + "ms,失败数=" + fail);

	}
}
