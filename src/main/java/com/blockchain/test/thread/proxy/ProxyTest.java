package com.blockchain.test.thread.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {


	public static void main(String[] args) {
		Animal animal = (Animal)Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, new AnimalProxy(new Bird()));
		animal.ee();
		Bird bird = (Bird)animal.getObj();
		System.out.println(bird);
	}
}


