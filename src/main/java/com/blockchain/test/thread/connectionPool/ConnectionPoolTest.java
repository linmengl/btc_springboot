package com.blockchain.test.thread.connectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
	static ConnectionPool pool = new ConnectionPool(10);
	/**
	 * 保证所有ConnectionRunner能够同时开始
	 */
	static CountDownLatch start = new CountDownLatch(1);
	/**
	 * main线程将会等待所有ConnectionRunner结束后才能继续执行
	 */
	static CountDownLatch end;

	public static void main(String[] args) throws Exception {
		// 线程数量，可以修改线程数量进行观察
		int threadCount = 20;
		end = new CountDownLatch(threadCount);
		int count = 20;
		AtomicInteger get = new AtomicInteger();
		AtomicInteger notGet = new AtomicInteger();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnetionRunner(count, get, notGet),	"ConnectionRunnerThread");
			thread.start();
		}
		start.countDown();
		end.await();
		System.out.println("total invoke: " + (threadCount * count));
		System.out.println("get connection: " + get);
		System.out.println("not get connection " + notGet);
	}

	static class ConnetionRunner implements Runnable {
		int count;
		AtomicInteger get;
		AtomicInteger notGet;

		public ConnetionRunner(int count, AtomicInteger get, AtomicInteger notGet) {
			this.count = count;
			this.get = get;
			this.notGet = notGet;
		}

		public void run() {
			try {
				start.await();
			} catch (Exception ex) {
			}
			while (count > 0) {
				try {
					// 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
					// 分别统计连接获取的数量got和未获取到的数量notGot
					Connection connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);//释放链接
							get.incrementAndGet();
						}
					} else {
						notGet.incrementAndGet();
					}
				} catch (Exception ex) {
				} finally {
					count--;
				}
			}
			end.countDown();
		}
	}
}
