package com.blockchain.btc_springboot.thread.proxy.user;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {

	private Object target;

	public PerformanceHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName() + "."
				+ arg1.getName());
		Object obj = arg1.invoke(target, arg2);// 通过反射机制调用目标对象的方法
		PerformanceMonitor.end();
		return obj;
	}
}
