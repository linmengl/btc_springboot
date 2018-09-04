package com.blockchain.btc_springboot.thread.cglib;

/**
 * //目标对象RealSubject，cglib不需要定义目标类的统一接口
 */
public class RealSubject {
	public void request() {
		System.out.println("real subject execute request");
	}

	public void hello() {
		System.out.println("hello");
	}
}
