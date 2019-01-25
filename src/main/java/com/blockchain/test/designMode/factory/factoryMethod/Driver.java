package com.blockchain.test.designMode.factory.factoryMethod;

/**
 * 抽象工厂
 */
public abstract class Driver {
	public abstract Car createCar(String car) throws Exception;
}
