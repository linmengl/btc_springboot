package com.blockchain.test.demo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo2 {
	//public static void main(String[] args) {
	//	Timer timer = new Timer();
	//	long aa = System.currentTimeMillis()/1000;
	//	System.out.println("sss--"+aa);
	//	timer.schedule(new TimerTask() {
	//		@Override
	//		public void run() {
	//			long a = System.currentTimeMillis()/1000;
	//			System.out.println("aaa--"+a);
	//		}
	//	}, 2000, 4000);
	//}

	public static void main(String[] args) throws Exception{
		//scheduleAtFixedRate();
		String res = executionTask("menglin");
		System.out.println(res);
	}


	static void scheduleAtFixedRate() throws InterruptedException, ExecutionException {
		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

		ScheduledFuture<?> result = executorService.scheduleAtFixedRate(new Runnable() {
			public void run() {

				System.out.println(System.currentTimeMillis()/1000);

			}
		}, 1000, 2000, TimeUnit.MILLISECONDS);

		// 由于是定时任务，一直不会返回
		result.get();
		System.out.println("over");
	}


	private static final ConcurrentMap<Object, Future<String>> taskCache =	new ConcurrentHashMap<Object, Future<String>>();

	private static String executionTask(final String taskName)	throws ExecutionException, InterruptedException {
		while (true) {
			Future<String> future = taskCache.get(taskName); // 1.1,2.1
			if (future == null) {
				Callable<String> task = new Callable<String>() {
					public String call() throws InterruptedException {
						return taskName;
					}
				};
				FutureTask<String> futureTask = new FutureTask<String>(task);
				future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
				if (future == null) {
					future = futureTask;
					futureTask.run(); // 1.4执行任务
				}
			}
			try {
				return future.get(); // 1.5,
			} catch (CancellationException e) {
				taskCache.remove(taskName, future);
			}
		}
	}

}
