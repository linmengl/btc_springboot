package com.blockchain.btc_springboot.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Demo {
	public static void getMyInstance(){
		//Proxy.newInstance方法直接创建出代理对象
		Collection proxy1 = (Collection) Proxy.newProxyInstance(
				Collection.class.getClassLoader(),
				new Class[]{Collection.class},
				new InvocationHandler() {
					//方法外部指定目标
					List target = new ArrayList<>();
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//在调用代码之前加入系统功能代码
						long startTime = System.currentTimeMillis();
						//睡眠1秒钟
						Thread.sleep(1000);
						//目标方法
						Object retVal = method.invoke(target, args);
						//在调用代码之后加入系统功能代码
						long endTime = System.currentTimeMillis();
						System.out.println( method.getName() + "方法花费了:" + (endTime - startTime) + "毫秒");
						return retVal;
					}
				});

		proxy1.add("a");
		proxy1.add("b");
		proxy1.add("c");
		//3
		System.out.println(proxy1.size());
	}
}
