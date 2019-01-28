package com.blockchain.test.agent.jdk;

import lombok.Data;

@Data
public class Person implements Animal{

	private String name;
	private int age;

	public Person(){
		System.out.println("Person...constructor...");
	}

	public void request() {
		System.out.println("real subject execute request");
	}

	public void hello() {
		System.out.println("hello");
	}

	@Override
	public void eat() {
		System.out.println("person.......");
	}
}
