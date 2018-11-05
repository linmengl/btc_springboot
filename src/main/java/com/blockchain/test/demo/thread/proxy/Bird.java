package com.blockchain.test.demo.thread.proxy;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Bird implements Animal {

	private String name;

	@Override
	public void ee() {
		System.out.println("bird.bird.bird.bird.bird.bird……");
	}

	@Override
	public Object getObj() {
		Bird bird = new Bird();
		bird.setName("小鸟");
		System.out.println("11111111111111111");
		return bird;
	}
}
