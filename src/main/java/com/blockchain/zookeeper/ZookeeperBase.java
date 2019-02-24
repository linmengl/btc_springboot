package com.blockchain.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperBase {

    private static final String CONNECT_ADDR = "192.168.199.144:2181,192.168.199.176:2181,192.168.199.101:2181";

    private static final int SESSION_OUTTIME = 5000;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException {
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //获取事件的状态
                Event.KeeperState state = watchedEvent.getState();
                //获取事件的类型
                Event.EventType type = watchedEvent.getType();
                if (Event.KeeperState.SyncConnected==state){
                    if (Event.EventType.None==type){
                        System.out.println("连接上了");
                        countDownLatch.countDown();
                    }
                }

            }
        });
        try {
            countDownLatch.await();
//            String res = zk.create("/menglin","haode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zk.create("/menglin","haode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,new AsyncCallback.StringCallback(){
                @Override
                public void processResult(int i, String s, Object o, String s1) {
                    System.out.println(i);
                    System.out.println(s);
                    System.out.println(o);
                    System.out.println(s1);
                }
            },"hh");
//            System.out.println(res);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
