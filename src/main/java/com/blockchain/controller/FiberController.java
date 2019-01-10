package com.blockchain.controller;

import com.blockchain.bean.JsonResult;
import com.blockchain.util.HttpClientUtils;
import com.blockchain.util.Tuple;
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

	private static ExecutorService executor = Executors.newFixedThreadPool(4000);

	@RequestMapping("/a")
	public JsonResult fiber(Integer count) {
		String fiberUrl = "http://127.0.0.1:8081/fiber/a/a.c";
		Tuple<Long,Integer> tuple = concurrentTest(count,fiberUrl);
		System.out.println("fiber执行请求" + count + "次，耗时=" + tuple.getFirst() + "ms,失败数=" + tuple.getSecond());
		return JsonResult.buildSuccessResult("success",null);
	}


	@RequestMapping("/a2")
	public JsonResult thread(Integer count) {
		String threadUrl = "http://127.0.0.1:8081/fiber/a/a3.c";
		Tuple<Long,Integer> tuple = concurrentTest(count,threadUrl);
		System.out.println("thread执行请求" + count + "次，耗时=" + tuple.getFirst() + "ms,失败数=" + tuple.getSecond());
		return JsonResult.buildSuccessResult("success",null);
	}

	private Tuple<Long,Integer> concurrentTest(int count, String url){
		AtomicInteger fail = new AtomicInteger(0);
		CyclicBarrier barrier = new CyclicBarrier(count);
		CountDownLatch latch = new CountDownLatch(count);
		long start = System.currentTimeMillis();
		for (int a = 0; a < count; a++) {
			System.out.println(a + 1);
			executor.execute(() -> {
				try {
					barrier.await();
					HttpClientUtils.doPost(url);
				} catch (Exception e) {
					fail.incrementAndGet();
				} finally {
					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - start;
		return Tuple.of(cost,fail.get());
	}
}
