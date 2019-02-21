package com.blockchain.test.distributedLock;

import kafka.zookeeper.ZooKeeperClient;
import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements DistributedLock {

	private static final String CONNECTION="10.1.23.64:2181";
	protected ZkClient zkClient = new ZkClient(CONNECTION);
	protected String lockPath="/lockPath/child";

	@Override
	public void getLock() {
		if (tryLock()){
			System.out.println("获取成功");
		}else {
			//进行等待
			waitLock();
		}
	}

	abstract boolean tryLock();


	abstract void waitLock();

	@Override
	public void unlock() {
		if (zkClient != null){
			zkClient.close();
			System.out.println("释放zkLock");
		}
	}
}
