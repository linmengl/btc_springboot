package com.blockchain.btc_springboot.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

	private static AtomicInteger count = new AtomicInteger(0);

	public static ThreadPoolExecutor executor() {
		return new ThreadPoolExecutor(2, 2, 3L,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(20),
				new ThreadFactoryBuilder()
						.setNameFormat(ThreadPoolConfig.class.getSimpleName() + "-" + count.addAndGet(1))
						.build()
		);

	}
}
