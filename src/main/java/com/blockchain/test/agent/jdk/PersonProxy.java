package com.blockchain.test.agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonProxy {

	public static void main(String[] args) {

		Animal person = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), Person.class.getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("aaaaa");
				Object invoke = method.invoke(new Person(), args);
				System.out.println("bbbbb");
				return invoke;
			}
		});
		person.eat();
	}
}
