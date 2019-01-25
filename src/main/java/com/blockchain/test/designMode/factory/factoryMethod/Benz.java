package com.blockchain.test.designMode.factory.factoryMethod;

public class Benz extends Car{

	@Override
	public void drive() {
		System.out.println(this.getName()+"----go-----------------------");
	}
}
