package com.blockchain.test.agent;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {


	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Person.class);

		enhancer.setCallback(new MethodInterceptor(){

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("CglibTest...MethodInterceptor...intercept");
				//System.out.println(obj);
				//System.out.println(method);
				//System.out.println(args);
				//System.out.println(proxy);
				//proxy.invoke(obj,args);
				return proxy.invokeSuper(obj,args);
			}
		});
		Person person = (Person)enhancer.create();
		person.hello();
	}
}
