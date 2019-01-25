package com.blockchain.test.designMode.factory.factoryMethod;

public class BenzDriver extends Driver {
	@Override
	public Car createCar(String car) throws Exception {
		return new Benz();
	}
}
