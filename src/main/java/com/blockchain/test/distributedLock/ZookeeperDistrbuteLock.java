package com.blockchain.test.distributedLock;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {
	@Override
	boolean tryLock() {
		try{
			zkClient.createEphemeral(lockPath);
			return true;
		}catch (Exception e){

		}

		return false;
	}

	@Override
	void waitLock() {
		CountDownLatch countDownLatch=null;

	}
}
