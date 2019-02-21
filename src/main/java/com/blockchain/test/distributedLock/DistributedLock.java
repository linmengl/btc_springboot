package com.blockchain.test.distributedLock;

public interface DistributedLock {

	void getLock();

	void unlock();
}
