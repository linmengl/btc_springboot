package com.blockchain.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
@Configuration
public class ThreadPoolConfig {

	private AtomicInteger count = new AtomicInteger(0);

	@Bean(name = "contractThreadPool")
	public ThreadPoolExecutor taskExecutor() {

		return new ThreadPoolExecutor(2,2,3,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(),
				new ThreadFactoryBuilder().setNameFormat(ThreadPoolConfig.class.getSimpleName() + "-" + count.addAndGet(1)).build()
				//new ThreadFactory() {
				//	private AtomicInteger count = new AtomicInteger(0);
				//
				//	@Override
				//	public Thread newThread(Runnable r) {
				//		Thread t = new Thread(r);
				//		String threadName = ThreadPoolConfig.class.getSimpleName() + "-" + count.addAndGet(1);
				//		t.setName(threadName);
				//		return t;
				//	}
				//}
				);
	}
}
