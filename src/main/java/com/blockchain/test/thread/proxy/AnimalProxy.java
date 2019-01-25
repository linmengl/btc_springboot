package com.blockchain.test.thread.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnimalProxy implements InvocationHandler {

	private Object animal;

	public AnimalProxy(Object object){
		this.animal = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始");
		Object obj = method.invoke(animal,args);
		System.out.println("结束");
		return obj;
	}
}
