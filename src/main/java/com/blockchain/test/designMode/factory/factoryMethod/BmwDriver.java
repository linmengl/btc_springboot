package com.blockchain.test.designMode.factory.factoryMethod;

public class BmwDriver extends Driver {
	@Override
	public Car createCar(String car) throws Exception {
		return new Bmw();
	}
}
