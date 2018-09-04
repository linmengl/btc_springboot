package com.blockchain.btc_springboot.thread.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoMethodInterceptor implements MethodInterceptor {

	/**
	 * intercept是CGLib定义的Interceptor接口的方法，它拦截所有目标方法的调用，
	 * obj 表示目标类的实例；
	 * method 表示目标类方法的反射对象；
	 * args   表示目标类方法的参数的反射对象；
	 * methodProxy 表示代理类实例；
	 * @param obj
	 * @param method
	 * @param args
	 * @param methodProxy
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("before in cglib");
		Object result = null;
		try{
			result = methodProxy.invokeSuper(obj, args);
		}catch (Exception e){
			System.out.println("get ex:"+e.getMessage());
			throw e;
		}finally {
			System.out.println("after in cglib");
		}
		return result;
	}
}
