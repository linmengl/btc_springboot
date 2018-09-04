package com.blockchain.btc_springboot.thread.proxy;

public class Dog implements Animal {
	@Override
	public void ee() {
		System.out.println("dog.dog.dog.dog.dog.dog……");
	}

	@Override
	public Object getObj() {
		return new Dog();
	}
}
