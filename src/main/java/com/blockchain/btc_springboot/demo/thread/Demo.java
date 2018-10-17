package com.blockchain.btc_springboot.demo.thread;

import com.alibaba.fastjson.JSON;
import com.blockchain.btc_springboot.config.thread.ThreadPool;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.LongStream;

public class Demo {

	public static void main(String[] args) throws Exception {
		//long[] numbers = LongStream.rangeClosed(1, 20).toArray();
		//System.out.println(JSON.toJSON(numbers));
		//boolean a = true;
		//System.out.println(a);
		//System.out.println(Integer.MAX_VALUE);
		//System.out.println(-1<<29);
		//System.out.println(1<<29);

		System.out.println(Runtime.getRuntime().availableProcessors());
		List<User> users = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setAge(i);
			users.add(user);
		}
		ThreadPoolExecutor executor = ThreadPool.executor();
		for (User user : users) {
			executor.execute(new Task(user));
		}
		Thread.sleep(2000);
		List<Runnable> runnables = executor.shutdownNow();
		for (Runnable r : runnables) {
			Task task = (Task) r;
			System.out.println(task.getUser());
		}

	}

}

@Data
class User {
	private int age;
}

@Data
class Task implements Runnable {

	public Task(User user) {
		this.user = user;
	}

	private User user;

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		System.out.println(user.getAge());
	}
}