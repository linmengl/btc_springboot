package com.blockchain.btc_springboot.thread.proxy.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object object;

	public MyInvocationHandler(Object object) {
		this.object = object;
	}

	@Override
	public void invoke(Object proxy, Method method, Object[] args) {
		long start = System.currentTimeMillis();

		try {
			method.invoke(object, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Fly time = " + (end - start));

	}
}
