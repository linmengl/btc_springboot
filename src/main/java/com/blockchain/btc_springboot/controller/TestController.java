package com.blockchain.btc_springboot.controller;

import com.blockchain.btc_springboot.bean.HttpClientResult;
import com.blockchain.btc_springboot.bean.JsonResult;
import com.blockchain.btc_springboot.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	static int count = 200000;
	static ExecutorService executor = Executors.newFixedThreadPool(10);

	@RequestMapping("/a")
	public JsonResult test(String s){
		//for (int i = 0; i < 10; i ++) {
			CyclicBarrier barrier = new CyclicBarrier(count);
			CountDownLatch latch = new CountDownLatch(count);
			AtomicInteger fail = new AtomicInteger(0);
			long start = System.currentTimeMillis();
			final String url = "http://127.0.0.1:8081/fiber/a/" + s + ".c";
			//testFiber();
			for (int a = 0; a < 200000; a++) {
				//System.out.println(a + 1);
				executor.execute(() -> {
					try {
						//barrier.await();
						HttpClientResult res = HttpClientUtils.doPost(url);
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
			//log.info("请求成功",s);
		//}
		return JsonResult.buildSuccessResult("success",null);
	}


	//static AtomicInteger fail = new AtomicInteger(0);
	//static CyclicBarrier barrier = new CyclicBarrier(count);
	//static CyclicBarrier barrier2 = new CyclicBarrier(1);
	//static CountDownLatch latch2 = new CountDownLatch(1);



	@RequestMapping("/a1")
	public JsonResult fiber() {
		CyclicBarrier barrier = new CyclicBarrier(count);
		AtomicInteger fail = new AtomicInteger(0);
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
		return JsonResult.buildSuccessResult("success",null);

	}


	@RequestMapping("/a2")
	public JsonResult thread() {
		CyclicBarrier barrier = new CyclicBarrier(count);
		AtomicInteger fail = new AtomicInteger(0);
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
		return JsonResult.buildSuccessResult("success",null);
	}
}
