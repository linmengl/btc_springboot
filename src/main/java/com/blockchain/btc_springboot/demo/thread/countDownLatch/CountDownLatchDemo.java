package com.blockchain.btc_springboot.demo.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
	//赛跑运动员的个数
	private static final int RUNNER_AMOUNT = 5;

	public CountDownLatchDemo() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//对于每位运动员，裁判员发令枪响了后才开始比赛，只有一次发令枪
		CountDownLatch cdl_startgun = new CountDownLatch(1);
		//对于整个比赛，所有赛跑人员跑完后才算结束，有RUNNER_AMOUNT个运动员
		CountDownLatch cdl_runner = new CountDownLatch(RUNNER_AMOUNT);
		Runner[] plays = new Runner[RUNNER_AMOUNT];
		for (int i = 0; i < RUNNER_AMOUNT; i++) {
			plays[i] = new Runner(i + 1, cdl_startgun, cdl_runner);
		}


		//定义特定的线程池，大小为5，每个线程对应一个运动员
		ExecutorService exe = Executors.newFixedThreadPool(RUNNER_AMOUNT);
		for (Runner p : plays) {
			exe.execute(p); //分配线程
			//new Thread(p).start();
		}
		System.out.println("Race begins!");

		//启动所有运动员的线程后，发令枪发令，cdl_startgun归0
		cdl_startgun.countDown();

		try {
			//开始等待所有运动员结束各自的跑步，只有都跑完后才能继续运行，否则一直处于阻塞状态
			cdl_runner.await(); //等待cdl_runner变为0，即为比赛结束
			cdl_runner.await(2,TimeUnit.SECONDS); //等待cdl_runner变为0，即为比赛结束  或者  2s后比赛结束
		} catch (InterruptedException e) {
			//TODO:handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Race ends!");
		}
		exe.shutdown();
	}
}


class Runner implements Runnable {
	private int id;
	private CountDownLatch cdl_startgun;
	private CountDownLatch cdl_runner;

	public Runner(int i, CountDownLatch cdl_startgun, CountDownLatch cdl_runner) {
		// TODO Auto-generated constructor stub
		super();
		this.id = i;
		this.cdl_startgun = cdl_startgun;
		this.cdl_runner = cdl_runner;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//等待发令枪响起，一旦发令枪响过，cdl_startgun归0，程序就可以往下执行
			cdl_startgun.await();
			//等待begin的状态为0
			Thread.sleep((long) (Math.random() * 100));
			System.out.println("Play" + id + " arrived."+"--"+System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//本运动员完成跑步，通知cdl_runner减1
			cdl_runner.countDown();
			//使end状态减1，最终减至0
		}
	}
}